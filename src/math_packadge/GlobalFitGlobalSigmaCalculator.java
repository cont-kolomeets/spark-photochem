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


public class GlobalFitGlobalSigmaCalculator {

	
	GlobalFitMath M;
	
	public GlobalFitGlobalSigmaCalculator(GlobalFitMath M)
	{
		this.M =M;
	}
	
	
	
	public float calcGlobalSigma()
	{
		//float _sumOpticalDensity;
		int _start=0, _end=0, _maxSteps = 0, _steps=0;
		float _localSigma=0;
		
		M._sigmaCollection = new ArrayList<Float>();
		
		M._globalSigma=0;
		//_maxSteps =  (_solvedCurveCollectionX.size() - _posToStartFittingWith);
		
		for(int w=0; w<M._availWaveCollection.size(); w++)
		{
			
			if(M._ifEnableCurveBounds)
			{
				_start = M._markersCollection.get(w).get(0);
				_end = M._markersCollection.get(w).get(1);
				_steps = _end-_start;
			}
			else
			{
				_start = M._posToStartFittingWith;
				_end = M._solvedCurveCollectionX.size();
				_steps = _end-_start;
			}
			
			
			_localSigma = 0;
			
			for(int i= _start; i<_end; i++)
			{
				_localSigma+= Math.pow((M._kinCollectionY.get(w).get(i) - M._solvedCurveCollectionY.get(w).get(i)), 2);
			}
				
			_localSigma = (float)Math.sqrt((_localSigma/_steps));
				
				
			if(M._ifUseWeight)
			{
				_localSigma = _localSigma*M._weightCollection.get(w);
			}

			
			
			if(M._ifCalcSigmaWithWeights && M._ifUseWeight)
			{
				M._sigmaCollection.add(-_localSigma);
			}
			
			if(!M._ifCalcSigmaWithWeights && M._ifUseWeight)
			{
				M._sigmaCollection.add(-_localSigma/M._weightCollection.get(w));
			}
			
			
			if(!M._ifCalcSigmaWithWeights && !M._ifUseWeight)
			{
				M._sigmaCollection.add(-_localSigma);
			}
			
			
			if(M._ifCalcSigmaWithWeights && !M._ifUseWeight)
			{
				M._sigmaCollection.add(-_localSigma*M._weightCollection.get(w));
			}
			

			
			
			M._globalSigma+=_localSigma;
		}
		M._globalSigma = M._globalSigma/M._availWaveCollection.size();
		
		return M._globalSigma;

	}
	
	
	
	public float calcGlobalSigmaAtOneWavelength(int w, boolean _ifCalcWithWeight)
	{
		int _start=0, _end=0, _steps=0;
		float _localSigma=0;

		
		if(M._ifEnableCurveBounds)
		{
			_start = M._markersCollection.get(w).get(0);
			_end = M._markersCollection.get(w).get(1);
			_steps = _end-_start;
		}
		else
		{
			_start = M._posToStartFittingWith;
			_end = M._solvedCurveCollectionX.size();
			_steps = _end-_start;
		}
			
			
		_localSigma = 0;
			
		for(int i= _start; i<_end; i++)
		{
			_localSigma+= Math.pow((M._kinCollectionY.get(w).get(i) - M._solvedCurveCollectionY.get(w).get(i)), 2);
		}
			
		_localSigma = (float)Math.sqrt((_localSigma/_steps));
				
			
		if(_ifCalcWithWeight)
			if(M._ifUseWeight)
			{
				_localSigma = _localSigma*M._weightCollection.get(w);
			}
		
		return _localSigma;

	}
	
}

