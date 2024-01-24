package math_packadge;

import interface_packadge.*;
import controller_packadge.*;



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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;




import java.util.TimerTask;
import java.util.Timer;


import java.beans.*;
import java.util.Random;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;



public class GlobalFitOptimizerInitConcGradient {
	

	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;

	
	
	//float _initialValue, _dValue, _valueStep, _trialValue, _bestValue=0; // float[6] , _initialValue - 0; _dValue - 1; _valueStep - 2; _trialValue - 3; _bestValue - 4; count - 5; ifMinOutOfBounds - 6;
	
	boolean _ifSigmaChangedConc = true;
	public int _cubeResolutionConc = 2;
	public int _chanceConc = 0;
	int _slopeSpeedConc = 1;
	ArrayList<float[]> _valueCollectionConc = new ArrayList<float[]>();
	ArrayList<Float> _constValuesCopy = new ArrayList<Float>();
	ArrayList<Float> _concentrationValuesCopy = new ArrayList<Float>();
	ArrayList<Float> _tempArrayConc = new ArrayList<Float>();
	ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFilteredCopy = new ArrayList<ArrayList<Float>>();	
	public int _counterConc=0, _nOfAlterableValuesConc=0;
	public float _minLimitConc=0, _maxLimitConc=0;
	public float _globalSigmaConc=1e9f;
	public float _minGlobalSigmaConc=1e9f, _globalSigmaLastConc = 1e9f;	
	public int _lengthConc = 0;
	
	
	
	public int _nOfIterationsGradientConst;
	public int _nOfChancesConc;
	float _cubeSideConc;
	public int _nOfIterationsGradientConc;
	public int _nOfMajorIterationsConc;
	public int _nOfChancesConst;
	float _cubeSideConst;
	int _nOfEq;
	float _cubeSideAbs;
	public int _nOfChancesAbs;
	int _nOfIterationsGradientAbs;
	ArrayList<ArrayList<float[]>> _variablesLimitsCollection;
	ArrayList<Float> _availWaveCollection;
	public int _nOfConst;
	float[] _constantValues;
	ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFiltered;
	public int _nOfMinorIterationsConcentration;
	public int _nOfMajorIterationsConcentration;
	public int _slopeValueConc;
	float[] _concentrationValues;
	public ArrayList<ArrayList<ArrayList<Float>>> _concFuncCollection;	
	
	
	
	public GlobalFitOptimizerInitConcGradient(GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
	
		_globalFitMath1 = _globalFitMath;
		_globalFitController1 = _globalFitController;
		
		assignVariables();
		
	}
	
	
	
	public void assignVariables()
	{
		_nOfIterationsGradientConst = _globalFitMath1.get_nOfIterationsGradientConst();
		_nOfChancesConc = Math.round((float)_nOfIterationsGradientConst/2f);
		_cubeSideConc= _globalFitMath1.get_cubeSideConc();
		_nOfIterationsGradientConc = _globalFitMath1.get_nOfIterationsGradientConc();
		_nOfMajorIterationsConc = _globalFitMath1.get_nOfMajorIterationsConst();
		_nOfChancesConst = Math.round((float)_nOfIterationsGradientConst/2f);
		_cubeSideConst = _globalFitMath1.get_cubeSideConst();
		_nOfEq = _globalFitMath1.get_nOfEq();
		_variablesLimitsCollection = _globalFitMath1.get_variablesLimitsCollection();
		_availWaveCollection = _globalFitMath1.get_availWaveCollection();
		_nOfConst = _globalFitMath1.get_nOfConst();
		_constantValues = _globalFitMath1.get_constantValues();
		_xSAbsSpecCollectionYFiltered = _globalFitMath1.get_xSAbsSpecCollectionYFiltered();
		_nOfMinorIterationsConcentration=_globalFitMath1.get_nOfMinorIterationsAbs();
		_nOfMajorIterationsConcentration=5+ Math.round(_globalFitMath1.get_nOfMinorIterationsAbs()/2);
		_slopeValueConc = _globalFitMath1.get_slopeValue();
		_concentrationValues = _globalFitMath1.get_concentrationValues();
		_concFuncCollection = _globalFitMath1.get_concFuncCollection();
		_nOfIterationsGradientAbs = _globalFitMath1.get_nOfIterationsGradientAbs();
		_cubeSideAbs = _globalFitMath1.get_cubeSideAbs();
		_nOfChancesAbs = Math.round((float)_nOfIterationsGradientAbs/2f);
	}
	
	
	
    public void globalFitInitialConcentrationsGradientSetIntialValues()
    {
		
    	_chanceConc = 0;
    	_minGlobalSigmaConc=10000;
    	
    	_nOfAlterableValuesConc=0;
		_counterConc=0;
		
		for(int i=0; i<_nOfEq; i++)
		{

			if((_variablesLimitsCollection.get(2).get(i)[0]==1)&(_variablesLimitsCollection.get(2).get(i)[3]==0))
			{
				float[] _valueArray = new float[7];
				_valueCollectionConc.add(_valueArray);
				
				
				_valueCollectionConc.get(_counterConc)[0] = _concentrationValues[i];
				_valueCollectionConc.get(_counterConc)[1] = _valueCollectionConc.get(_counterConc)[0]*_cubeSideConc;
				_valueCollectionConc.get(_counterConc)[2] = _valueCollectionConc.get(_counterConc)[1];//2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
				_valueCollectionConc.get(_counterConc)[5] = 0;
				_valueCollectionConc.get(_counterConc)[6] = 0;
				_nOfAlterableValuesConc++;
				_counterConc++;
			}
		}
		
		_lengthConc=((int)Math.pow((_cubeResolutionConc+1),_nOfAlterableValuesConc));
    }
	
	
	
	
    
    public void globalFitInitialConcentrationsGradientResetValues()
    {

    	_counterConc=0;
		for(int i=0; i<_nOfEq; i++)
		{
			if((_variablesLimitsCollection.get(2).get(i)[0]==1)&(_variablesLimitsCollection.get(2).get(i)[3]==0))
			{
				_slopeSpeedConc = 1;
				if(_valueCollectionConc.get(_counterConc)[6]==1)
				{
					_slopeSpeedConc = 2;
					//System.out.println("Reduced step for " + i);
				}
					
				
				_valueCollectionConc.get(_counterConc)[0] = _concentrationValues[i];
				_valueCollectionConc.get(_counterConc)[1] = _valueCollectionConc.get(_counterConc)[1]/_slopeSpeedConc;
				_valueCollectionConc.get(_counterConc)[2] = _valueCollectionConc.get(_counterConc)[1];
				_valueCollectionConc.get(_counterConc)[5] = 0;
				_valueCollectionConc.get(_counterConc)[6] = 0;
				_counterConc++;
				
				_globalSigmaLastConc = _minGlobalSigmaConc;
			}
		}
		


    }
	
	
    
    
    public void globalFitInitialConcentrationsGradientSetTrialValues()
    {
    	_counterConc=0;
			for(int i=0; i<_nOfEq; i++)
			{
				if((_variablesLimitsCollection.get(2).get(i)[0]==1)&(_variablesLimitsCollection.get(2).get(i)[3]==0))
				{
					_valueCollectionConc.get(_counterConc)[3] = _valueCollectionConc.get(_counterConc)[0] - (_cubeResolutionConc-(_valueCollectionConc.get(_counterConc)[5]+1))*_valueCollectionConc.get(_counterConc)[2];
					
					if((_valueCollectionConc.get(_counterConc)[3]<_variablesLimitsCollection.get(2).get(i)[1])&(_variablesLimitsCollection.get(2).get(i)[1]!=-1))
						_valueCollectionConc.get(_counterConc)[3] = _variablesLimitsCollection.get(2).get(i)[1]; 
					
					if((_valueCollectionConc.get(_counterConc)[3]>_variablesLimitsCollection.get(2).get(i)[2])&(_variablesLimitsCollection.get(2).get(i)[2]!=-1))
						_valueCollectionConc.get(_counterConc)[3] = _variablesLimitsCollection.get(2).get(i)[2]; 
					
					
					_concentrationValues[i] =_valueCollectionConc.get(_counterConc)[3];
					System.out.println("Trial value INDEPENDENT= " + _concentrationValues[i]);
					_counterConc++;
				}
				
				if((_variablesLimitsCollection.get(2).get(i)[0]==1)&(_variablesLimitsCollection.get(2).get(i)[3]==1))
				{
					_concentrationValues[i] = _globalFitMath1.getConcentrationFromDependence(i);
					System.out.println("Trial value DEPENDENT= " + _concentrationValues[i]);
				}
			}
    }
    
    
    

    
    
    
    
    
    public void globalFitInitialConcentrationsGradientMoveAlongGrid()
    {
		_valueCollectionConc.get(0)[5]++;
		
		for(int n=1; n<_nOfAlterableValuesConc; n++)
		{
			if((_valueCollectionConc.get(n-1)[5]>_cubeResolutionConc)&(_nOfAlterableValuesConc>n))
			{
				_valueCollectionConc.get(n-1)[5]=0;
				_valueCollectionConc.get(n)[5]++;
			}
		}
    }
    
    public void globalFitInitialConcentrationsGradientCalcConstAndAbsCoef(int _nOfCycles)
    {
      	for(int i=0; i<_nOfCycles; i++)
      	{
      		if(_globalFitMath1.get_ifAlterAbsCoeff())
      		{
      			_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
      			
      			for(int w=0; w<_globalFitMath1.get_availWaveCollection().size(); w++)
    			{
      				_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;	
      				//if(_globalFitMath1.get_ifContinueToFit())
    				//		_globalFitMath1.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterationsGradientAbs, _nOfChancesAbs, _cubeSideAbs, false);
    			}
      		}

			_globalFitMath1.fitRK4();
			
			if(_globalFitMath1.get_ifAlterConst())
				if(_globalFitMath1.get_ifContinueToFit())
				//_globalFitMath1.performGlobalFitConstSimpleRange(_globalFitMath1.get_nOfMajorIterationsConst(), _globalFitMath1.get_nOfMinorIterationsConst());
					_globalFitMath1.performGlobalFitConstFindGradientMinimum(_nOfIterationsGradientConst, _nOfChancesConst, _cubeSideConst);
			 
      	}



    }
    
    
    public void globalFitInitialConcentrationsGradientCheckForBetterSigma()
    {
		
		if(_minGlobalSigmaConc>_globalSigmaConc)
		{
			_minGlobalSigmaConc = _globalSigmaConc;	
			
			//System.out.println("Major " + k + " Minor " + j + " Min sigma value = " + _minGlobalSigma);
			for(int i=0; i<_nOfAlterableValuesConc; i++)
			{
				_valueCollectionConc.get(i)[4] = _valueCollectionConc.get(i)[3];
				
				if((Math.abs(_valueCollectionConc.get(i)[4])==Math.abs(_valueCollectionConc.get(i)[0]))||(Math.abs(_valueCollectionConc.get(i)[2])>=Math.abs(_valueCollectionConc.get(i)[4]*0.4))) //if parameter value hasn't change or if step is too big
				{
					//System.out.println("Need to reduce slope for " + i);
					_valueCollectionConc.get(i)[6] = 1;
				}
			}
			
			globalFitInitialConcentrationsGradientBackingUpData();
		}

    }
    
    public void globalFitInitialConcentrationsGradientBackingUpData()
    {
		if(_globalFitMath1.get_ifAlterAbsCoeff())
		{
			_xSAbsSpecCollectionYFilteredCopy = new ArrayList<ArrayList<Float>>();
				for(int m=0; m<_nOfEq; m++)
				{
				_tempArrayConc = new ArrayList<Float>();
				
					for(int w=0; w<_availWaveCollection.size(); w++)
				{
							_tempArrayConc.add(_xSAbsSpecCollectionYFiltered.get(m).get(w));
					}
					_xSAbsSpecCollectionYFilteredCopy.add(_tempArrayConc);

				}
					
		}
		
		
		if(_globalFitMath1.get_ifAlterConst())
		{
			_constValuesCopy = new ArrayList<Float>();

			for(int m=0; m<_nOfConst; m++)
			{
				_constValuesCopy.add(_constantValues[m]);
				//System.out.println("BACK UP Constant: " + _constantValues()[m]);
			}
		}


		_counterConc=0;
		for(int i=0; i<_nOfEq; i++)
		{
			if((_variablesLimitsCollection.get(2).get(i)[0]==1)&(_variablesLimitsCollection.get(2).get(i)[3]==0))
			{
				_concentrationValues[i] =_valueCollectionConc.get(_counterConc)[4];
				_counterConc++;
			}
		}
		
		
		_concentrationValuesCopy = new ArrayList<Float>();
		for(int m=0; m<_nOfEq; m++)
		{
			_concentrationValuesCopy.add(_concentrationValues[m]);
		}
		
		//System.out.println("data backed up");

    }
    
	
    public void globalFitInitialConcentrationsGradientPlacingDataBack()
    {
		for(int i=0; i<_nOfEq; i++)
		{
			_concentrationValues[i] = _concentrationValuesCopy.get(i);
		}

		if(_globalFitMath1.get_ifAlterAbsCoeff())
		{
			for(int m=0; m<_nOfEq; m++)
				{
					for(int w=0; w<_availWaveCollection.size(); w++)
					{
						_xSAbsSpecCollectionYFiltered.get(m).set(w, _xSAbsSpecCollectionYFilteredCopy.get(m).get(w));
					}
				}
		}
		
		if(_globalFitMath1.get_ifAlterConst())
		{
			for(int m=0; m<_nOfConst; m++)
			{
				_constantValues[m] = _constValuesCopy.get(m);
				//System.out.println("Setting Constants: " + _constantValues[m]);
			}
		}
    }
    
    
    
    
    
    
    
    
    
    
    
	
	
	
}