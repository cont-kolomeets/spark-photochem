package math_packadge;

import interface_packadge.*;

import java.awt.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.RuntimeException;


public class GlobalFitErrorCalculator {

	
	GlobalFitMath M;
	float _globalSigma;
	float _localSigma;	
	float _newLocalSigma, _newGlobalSigma;
	float _initialJump = 0.05f;
	float _sigmaBounds = 0.05f;
	float _variableBoundsIni = 0.005f;
	float _variableBounds = _variableBoundsIni;
	float _targetHitAccuracy = 0.01f;
	SearchingRange _searchingRange = new SearchingRange();

	
	public GlobalFitErrorCalculator(GlobalFitMath M)
	{
		this.M =M;
	}
	
	
	public void calcErrorsForVariables()
	{
		
		if(M.get_ifAlterAbsCoeff())
		{
			calcErrorsForAbsCoeff();
		}
		if(M.get_ifAlterConst())
		{
			calcErrorsForConst();
		}
	}
	
	
	public void calcErrorsForAbsCoeff()
	{

		//System.out.println("Calculating errors for abs coeff");
		for(int w=0; w<M.get_availWaveCollection().size(); w++)
		{		
			_localSigma = M.calcGlobalSigmaAtOneWavelength(w, false);
			
			//System.out.println("wavelength = " + M.get_availWaveCollection().get(w) + "  local sigma = " + _localSigma);
			
			for(int nX=0; nX<M.get_nOfEq(); nX++)
			{
				calcErrorForCertainAbsCoeff(nX, w);
			}
		}
		
	}
	
	public void calcErrorForCertainAbsCoeff(int _indexOfX, int w)
	{
		float _initialVal = getAbsCoeff(_indexOfX, w);
		
		//System.out.println("Initail value = " + _initialVal);
		
		defineAndSearchWithinRangeAbsCoeff(_indexOfX, w, _initialVal);

		
		setAbsCoeff(_indexOfX, w, _initialVal);
		calcNewLocalSigma(w);
	}
	
	
	public void defineAndSearchWithinRangeAbsCoeff(int _indexOfX, int w, float _initialVal)
	{
		_variableBounds = _variableBoundsIni;
		float _previousTrialAbsCoeff = _initialVal;
		float _previousTrialLocalSigma = _localSigma;
		_newLocalSigma = _localSigma;
		
		for(int trials=0; trials<10; trials++)
		{
			//System.out.println("****************trial " + trials);
			_previousTrialAbsCoeff = getAbsCoeff(_indexOfX, w);
			_previousTrialLocalSigma = _newLocalSigma;
			setAbsCoeff(_indexOfX, w, _initialVal);
			_variableBounds *= 2*(trials+1);
			incAbsCoeff(_indexOfX, w, _variableBounds);
			
			//System.out.println("New value = " + getAbsCoeff(_indexOfX, w));
			
			calcNewLocalSigma(w);
			
			//System.out.println("New sigma = " + _newLocalSigma);
			
			if(newSigmaCloseEnoughToTheTarget())
				break;
			if(targetSigmaWithinBounds())
				break;
		}
		
		if(newSigmaCloseEnoughToTheTarget())
		{
			//System.out.println("----------Close enough");
			setAbsCoeffError(_indexOfX, w, Math.abs(_initialVal-getAbsCoeff(_indexOfX, w)));
			//System.out.println("error =  " + Math.abs(_initialVal-getAbsCoeff(_indexOfX, w)));
		}
		else
		{
			//System.out.println("-----------Within bounds");
			_searchingRange.setStartAbsCoeff(_previousTrialAbsCoeff);
			_searchingRange.setEndAbsCoeff(getAbsCoeff(_indexOfX, w));
			_searchingRange.setStartSigma(_previousTrialLocalSigma);
			_searchingRange.setEndSigma(_newLocalSigma);
			
			searchWithinTheRange(_indexOfX, w, _initialVal);
		}
	}
	
	public void searchWithinTheRange(int _indexOfX, int w, float _initialVal)
	{
		float _interpolatedAbsCoeff;
		
		float e1 = _searchingRange.getStartAbsCoeff();
		float e2 = _searchingRange.getEndAbsCoeff();
		float s1 = _searchingRange.getStartSigma();
		float s2 = _searchingRange.getEndSigma();
		float sx = calcNessesarySigma();
		
		//System.out.println("e1 = " + e1);
		//System.out.println("e2 = " + e2);
		//System.out.println("s1 = " + s1);
		//System.out.println("s2 = " + s2);
		//System.out.println("sx = " + sx);
		
		_interpolatedAbsCoeff = e1+(sx-s1)*(e2-e1)/(s2-s1);
		setAbsCoeffError(_indexOfX, w, Math.abs(_initialVal-_interpolatedAbsCoeff));
		
		//System.out.println("error =  " + Math.abs(_initialVal-_interpolatedAbsCoeff));
	}

	

	
	
	
	public void calcNewLocalSigma(int w)
	{
		M.recalcOpticalDensityCollections();
		_newLocalSigma = M.calcGlobalSigmaAtOneWavelength(w, false);

	}
	public boolean targetSigmaWithinBounds()
	{
		return (Math.abs(_localSigma-_newLocalSigma)>(_localSigma*_sigmaBounds));
	}
	
	
	public boolean newSigmaCloseEnoughToTheTarget()
	{
		return (Math.abs(_localSigma*(1+_sigmaBounds)-_newLocalSigma)<=(_targetHitAccuracy*_localSigma));
	}
	
	
	public float calcNessesarySigma()
	{
		return _localSigma*(1+_sigmaBounds);
	}
	
	
	public void calcNewGlobalSigma()
	{
		M.fitRK4();
		_newGlobalSigma = M.calcGlobalSigma();

	}
	public boolean targetSigmaWithinBoundsConst()
	{
		return (Math.abs(_globalSigma-_newGlobalSigma)>(_globalSigma*_sigmaBounds));
	}
	
	
	public boolean newSigmaCloseEnoughToTheTargetConst()
	{
		return (Math.abs(_globalSigma*(1+_sigmaBounds)-_newGlobalSigma)<=(_targetHitAccuracy*_globalSigma));
	}
	
	
	public float calcNessesarySigmaConst()
	{
		return _globalSigma*(1+_sigmaBounds);
	}
	
	
	
	public void calcErrorsForConst()
	{
		//System.out.println("Calculating errors for const");
		
		_globalSigma = M.calcGlobalSigma();
		
		for(int i=0; i<M.get_nOfConst(); i++)
		{
			calcErrorForCertainConst(i);
		}
	}
	
	
	
	public void calcErrorForCertainConst(int _IndexOfK)
	{
		float _initialVal = getConst(_IndexOfK);
		
		//System.out.println("Initail value = " + _initialVal);
		
		defineAndSearchWithinRangeConst(_IndexOfK, _initialVal);

		setConst(_IndexOfK, _initialVal);
		calcNewGlobalSigma();
	}
	
	
	public void defineAndSearchWithinRangeConst(int _indexOfK, float _initialVal)
	{
		_variableBounds = _variableBoundsIni;
		float _previousTrialConst = _initialVal;
		float _previousTrialGlobalSigma = _globalSigma;
		_newGlobalSigma = _globalSigma;
		
		for(int trials=0; trials<10; trials++)
		{
			//System.out.println("****************trial " + trials);
			_previousTrialConst = getConst(_indexOfK);
			_previousTrialGlobalSigma = _newGlobalSigma;
			setConst(_indexOfK, _initialVal);
			_variableBounds *= 2*(trials+1);
			incConst(_indexOfK, _variableBounds);
			
			//System.out.println("New value = " + getConst(_indexOfK));
			
			calcNewGlobalSigma();
			
			//System.out.println("New sigma = " + _newGlobalSigma);
			
			if(newSigmaCloseEnoughToTheTargetConst())
				break;
			if(targetSigmaWithinBoundsConst())
				break;
		}
		
		if(newSigmaCloseEnoughToTheTargetConst())
		{
			//System.out.println("----------Close enough");
			setConstError(_indexOfK, Math.abs(_initialVal-getConst(_indexOfK)));
			//System.out.println("error =  " + Math.abs(_initialVal-getConst(_indexOfK)));
		}
		else
		{
			//System.out.println("-----------Within bounds");
			_searchingRange.setStartConst(_previousTrialConst);
			_searchingRange.setEndConst(getConst(_indexOfK));
			_searchingRange.setStartSigma(_previousTrialGlobalSigma);
			_searchingRange.setEndSigma(_newGlobalSigma);
			
			searchWithinTheRangeConst(_indexOfK, _initialVal);
		}
	}
	
	public void searchWithinTheRangeConst(int _indexOfK, float _initialVal)
	{
		float _interpolatedConst;
		
		float k1 = _searchingRange.getStartConst();
		float k2 = _searchingRange.getEndConst();
		float s1 = _searchingRange.getStartSigma();
		float s2 = _searchingRange.getEndSigma();
		float sx = calcNessesarySigmaConst();
		
		//System.out.println("k1 = " + k1);
		//System.out.println("k2 = " + k2);
		//System.out.println("s1 = " + s1);
		//System.out.println("s2 = " + s2);
		//System.out.println("sx = " + sx);
		
		_interpolatedConst = k1+(sx-s1)*(k2-k1)/(s2-s1);
		setConstError(_indexOfK, Math.abs(_initialVal-_interpolatedConst));
		
		//System.out.println("error =  " + Math.abs(_initialVal-_interpolatedConst));
	}
	
	
	
	
	
	
	
	
	
	
	float getConst(final int _index)
	{
		return M._constantValues[_index];
	}
	
	void setConst(final int _index, final float _val)
	{
		 M._constantValues[_index] = _val;
	}
	
	
	void incConst(final int _indexOfK, final float shift)
	{
		float value = M._constantValues[_indexOfK];
		M._constantValues[_indexOfK] = (value*(1+shift));
	}
	
	void setConstError(final int _index, final float _val)
	{
		 M._constantErrorValues[_index] = _val;
	}
	
	
	
	float getAbsCoeff(final int _indexOfX, final int w)
	{
		return M._xSAbsSpecCollectionYFiltered.get(_indexOfX).get(w);
	}
	
	void setAbsCoeff(final int _indexOfX, final int w, final float val)
	{
		M._xSAbsSpecCollectionYFiltered.get(_indexOfX).set(w, val);
	}
	
	void incAbsCoeff(final int _indexOfX, final int w, final float shift)
	{
		float value = M._xSAbsSpecCollectionYFiltered.get(_indexOfX).get(w);
		M._xSAbsSpecCollectionYFiltered.get(_indexOfX).set(w, (value*(1+shift)));
	}
	
	void setAbsCoeffError(final int _indexOfX, final int w, final float val)
	{
		M._xSAbsSpecErrorCollection.get(_indexOfX).set(w, val);
	}
	
	public class SearchingRange
	{
		private float[] _searchingRangeAbsCoeff = new float[2];
		private float[] _searchingRangeConst = new float[2];
		private float[] _searchingRangeSigma = new float[2];
		
		public SearchingRange()
		{
			
		}
		
		public void setStartAbsCoeff(final float _val)
		{
			_searchingRangeAbsCoeff[0] = _val;
		}
		
		public float getStartAbsCoeff()
		{
			return _searchingRangeAbsCoeff[0];
		}
		
		public void setEndAbsCoeff(final float _val)
		{
			_searchingRangeAbsCoeff[1] = _val;
		}
		
		public float getEndAbsCoeff()
		{
			return _searchingRangeAbsCoeff[1];
		}
		
		
		public void setStartConst(final float _val)
		{
			_searchingRangeConst[0] = _val;
		}
		
		public float getStartConst()
		{
			return _searchingRangeConst[0];
		}
		
		public void setEndConst(final float _val)
		{
			_searchingRangeConst[1] = _val;
		}
		
		public float getEndConst()
		{
			return _searchingRangeConst[1];
		}
		
		public void setStartSigma(final float _val)
		{
			_searchingRangeSigma[0] = _val;
		}
		
		public float getStartSigma()
		{
			return _searchingRangeSigma[0];
		}
		
		public void setEndSigma(final float _val)
		{
			_searchingRangeSigma[1] = _val;
		}
		
		public float getEndSigma()
		{
			return _searchingRangeSigma[1];
		}
		
	}
	
	
}