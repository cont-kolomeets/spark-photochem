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


public class GlobalFitOptimizerAbsCoeffMatrix {

	
	GlobalFitMath M;
	public int _nElements;
	Matrix Amodified, AIni, K, E, modifyingMatrix;
	public double _determinant;
	int _counter=0;
	int _start=0, _end=0;
	public boolean _modificationsNeeded = false;
	
	public GlobalFitOptimizerAbsCoeffMatrix(GlobalFitMath M)
	{
		this.M =M;
	}

	
		
	public void globalFitAbsCoeffMatrixOptimization(int w)
	{
		if(M._ifEnableCurveBounds)
		{
			//System.out.println("Using boudns");
			calcStartAndEnd(w);
			calcAMatrixSelectively(_nElements);
			_determinant = AIni.det();
			if(_determinant!=0)
			{
				_determinant = AIni.det();
				System.out.println("det = " + _determinant);
				AIni = AIni.inverse();
				E = new Matrix(_nElements, 1);
				calcKArraySelectively(w);
				E = AIni.times(K);
				setAbsCoef(w);
			}
		}
		else
		{
			if(_determinant!=0)
			{
				E = new Matrix(_nElements, 1);
				calcKArraySelectively(w);
				E = AIni.times(K);
				setAbsCoef(w);
			}
		}

	}
	
	
	public void setAbsCoef(int w)
	{
		int _counterI = 0;
		
		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
			{
				M._xSAbsSpecCollectionYFiltered.get(i).set(w, -(float)Math.abs(E.get(_counterI, 0)));//-(float)Math.abs(E.get(i, 0))
				_counterI++;
			}
	}
	
	public void calcStartAndEnd(int w)
	{
		
		if(M._ifEnableCurveBounds)
		{
			_start = M._markersCollection.get(w).get(0);
			_end = M._markersCollection.get(w).get(1);
		}
		else
		{
			_start = M._posToStartFittingWith;
			_end = M._kinCollectionX.get(0).size();
		}
	}
	
	
	public void restart()
	{		
		_nElements = 0;
		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
				_nElements++;
		
		
		if(M._ifEnableCurveBounds)
		{
			//
		}
		else
		{
			calcStartAndEnd(0);
			calcAMatrixSelectively(_nElements);
			_determinant = AIni.det();
			if(_determinant!=0)
			{
				_determinant = AIni.det();
				//System.out.println("det = " + _determinant);
				AIni = AIni.inverse();
			}
		}

	}

	
	public boolean absCoeffIsVariable(int _index)
	{
		return (M._variablesLimitsCollection.get(1).get(_index)[0]==1);
	}
	
	public void calcAMatrix()
	{
		AIni = new Matrix(M._nOfEq, M._nOfEq);

		
		for(int i=0; i<M._nOfEq; i++)
			for(int j=0; j<M._nOfEq; j++)
				AIni.set(i, j, (double)calcAij(i,j));
		
		//AIni.print(20,20);
	}
	
	
	public void createModifyingMatrix(int w)
	{
		modifyingMatrix = new Matrix(M._nOfEq, M._nOfEq);
		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
			{
				modifyingMatrix.set(i,i,1);
			}
			else
			{
				modifyingMatrix.set(i,i,M._xSAbsSpecCollectionYFiltered.get(i).get(w));
			}
	}
	
	public void modifyInitialMatrix()
	{
		//AIni.print(20,20);
		Amodified = AIni.copy();
		//Amodified.print(20,20);
		Amodified = Amodified.times(modifyingMatrix);
		//Amodified.print(20,20);
	}
	
	public void calcInverseForModifiedMatrix()
	{
		Amodified = Amodified.inverse();
	}
	

	public void checkIfModificationsNeeded()
	{
		this._modificationsNeeded = false;
		for(int i=0; i<M._nOfEq; i++)
			if(!absCoeffIsVariable(i))
				_modificationsNeeded = true;
	}

	
	/*public void calcKArray(int w)
	{
		K = new Matrix(M._nOfEq, 1);
		
		for(int i=0; i<M._nOfEq; i++)
			K.set(i, 0, calcKi(i, w));
	}*/
	
	
	public void calcKArraySelectively(int w)
	{
		K = new Matrix(_nElements, 1);
		int _counterI = 0;

		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
			{
				K.set(_counterI, 0, calcKiAddingFixedDensity(i, w));
				_counterI++;
			}

	}
	
	
	
	
	public double calcAij(int i, int j)
	{
		double _result=0;

		for(int t=_start; t<_end; t++)
		{
			_result += Math.pow(M._cuvetteThickness, 2)*(M._solvedConcentrationCollectionY.get(i).get(t)-M._beforePulseConcentrationValues[i])*(M._solvedConcentrationCollectionY.get(j).get(t)-M._beforePulseConcentrationValues[j]);
			
		}
		
		return _result;
	}
	
	/*public double calcKi(int i, int w)
	{
		double _result=0;
		
		for(int t=0; t<M._kinCollectionX.get(0).size(); t++)
		{
			_result += (M._solvedConcentrationCollectionY.get(i).get(t)-M._beforePulseConcentrationValues[i])*M._kinCollectionY.get(w).get(t);
		}
		
		return _result;
	}*/
	
	
	
	
	public double calcKiAddingFixedDensity(int i, int w)
	{
		double _result=0;
		double _yt = 0;
		
		for(int t=_start; t<_end; t++)
		{
			_yt=M._kinCollectionY.get(w).get(t);
			
			if(M._ifCalcCurveShifts)
			{
				_yt+=M._curveShiftCollection.get(w);
			}
			else
			{
				_yt+=M._levelPosDisplay;
			}
			
			for(int n=0; n<M._nOfEq; n++)
				if(!absCoeffIsVariable(n))
				{
					_yt-=M._cuvetteThickness*M._xSAbsSpecCollectionYFiltered.get(n).get(w)*(M._solvedConcentrationCollectionY.get(n).get(t)-M._beforePulseConcentrationValues[n]);
				}
			
			_result += M._cuvetteThickness*(M._solvedConcentrationCollectionY.get(i).get(t)-M._beforePulseConcentrationValues[i])*_yt;
		}
		
		return _result;
	}
	
	
	
	
	
	public boolean ifThereMightBeUncertaintyAbsCoeff()
	{
		boolean _result = false;

		_nElements = 0;
		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
				_nElements++;
		
		calcAMatrixSelectively(_nElements);
		
		_determinant = AIni.det();

		if(_determinant==0)
			_result = true;
		
		if(_result)
			System.out.println("High probability of uncertainty for Abs Coeff!");
		
		return _result;
	}
	
	
	
	
	public void calcAMatrixSelectively(int _nElements)
	{
		AIni = new Matrix(_nElements, _nElements);
		
		int _counterI=0;
		int _counterJ=0;

		
		for(int i=0; i<M._nOfEq; i++)
			if(absCoeffIsVariable(i))
			{
				_counterJ=0;
				for(int j=0; j<M._nOfEq; j++)
					if(absCoeffIsVariable(j))
					{
						AIni.set(_counterI, _counterJ, (double)calcAij(i,j));
						_counterJ++;
					}
					
				_counterI++;
			}
			
		
		//AIni.print(20,20);
	}
	
	
	
	
	public double getDeterminant()
	{
		return this._determinant;
	}
	
	
	
	
	
}
