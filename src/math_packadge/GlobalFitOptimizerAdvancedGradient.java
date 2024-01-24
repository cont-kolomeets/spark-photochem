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



public class GlobalFitOptimizerAdvancedGradient {
	

	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;
	
	
	public int _lengthConst=0;
	public  float _minGlobalSigmaConst=10000, _sumOpticalDensity=0, _tempSigma = 0, _globalSigma=1E9f, _globalSigmaLastConst = 1E9f;
	//float _initialValue, _dValue, _valueStep, _trialValue, _bestValue=0; // float[6] , _initialValue - 0; _dValue - 1; _valueStep - 2; _trialValue - 3; _bestValue - 4; count - 5; ifMinOutOfBounds - 6;
	public int _counter=0, _nOfAlterableValues=0;
	float _minLimit=0, _maxLimit=0, _currentSlope = 2;
	int _start=0, _end=0, _maxSteps = 0, _steps=0;
	float _expStep = 1, _deviation, _currentSolvedValue;
	ArrayList<float[]> _valueCollection = new ArrayList<float[]>();
	public int _cubeResolutionConst = 2;
	public int _chanceConst = 0;
	int _slopeSpeedConst = 1;	
	boolean _ifSigmaChanged = true;
	
	int _nOfConst;
	int _nOfEq;
	float[] _constantValues;
	int _nOfMinorIterationsConst;
	int _nOfMajorIterationsConst;
	int _slopeValueConst;
	int _posToStartFittingWith;
	float _levelPosDisplay;
	float _cuvetteThickness;
	float[] _beforePulseConcentrationValues;
	int _nOfIterationsGradientAbs;
	public int _nOfIterationsGradientConst;
	float _cubeSideAbs;
	float _cubeSideConst;
	public int _nOfChancesConst;
	public int _nOfChancesAbs;
	ArrayList<ArrayList<float[]>> _variablesLimitsCollection;
	ArrayList<ArrayList<Float>> _solvedCurveCollectionY;
	ArrayList<ArrayList<Float>> _kinCollectionY;
	ArrayList<ArrayList<Integer>> _markersCollection;
	ArrayList<Float> _weightCollection;
	ArrayList<Float> _availWaveCollection;
	ArrayList<Float> _solvedCurveCollectionX;
	ArrayList<ArrayList<Float>> _solvedConcentrationCollectionY;
	ArrayList<Float> _curveShiftCollection;
	ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFiltered;
	boolean _ifEnableCurveBounds;
	boolean _ifUseWeight;			
	boolean _ifCalcCurveShifts;
	
	

	public GlobalFitOptimizerAdvancedGradient(GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
	
		_globalFitMath1 = _globalFitMath;
		_globalFitController1 = _globalFitController;
		
		assignVariables();
		
	}
	
	
	
	
	
	public void assignVariables()
	{
		_nOfConst = _globalFitMath1.get_nOfConst();
		_nOfEq = _globalFitMath1.get_nOfEq();
		_constantValues = _globalFitMath1.get_constantValues();
		_nOfMinorIterationsConst = _globalFitMath1.get_nOfMinorIterationsConst();
		_nOfMajorIterationsConst = _globalFitMath1.get_nOfMajorIterationsConst();
		_slopeValueConst = _globalFitMath1.get_slopeValueConst();
		_posToStartFittingWith = _globalFitMath1.get_posToStartFittingWith();
		_levelPosDisplay = _globalFitMath1.get_levelPosDisplay();
		_cuvetteThickness = _globalFitMath1.get_cuvetteThickness();
		_beforePulseConcentrationValues = _globalFitMath1.get_beforePulseConcentrationValues();
		_nOfIterationsGradientAbs = _globalFitMath1.get_nOfIterationsGradientAbs();
		_nOfIterationsGradientConst = _globalFitMath1.get_nOfIterationsGradientConst();
		_cubeSideAbs = _globalFitMath1.get_cubeSideAbs();
		_cubeSideConst = _globalFitMath1.get_cubeSideConst();
		_nOfChancesConst = Math.round((float)_nOfIterationsGradientConst/2f);
		_nOfChancesAbs = Math.round((float)_nOfIterationsGradientAbs/2f);
		_variablesLimitsCollection = _globalFitMath1.get_variablesLimitsCollection();
		_solvedCurveCollectionY = _globalFitMath1.get_solvedCurveCollectionY();
		_kinCollectionY = _globalFitMath1.get_kinCollectionY();
		_markersCollection = _globalFitMath1.get_markersCollection();
		_weightCollection = _globalFitMath1.get_weightCollection();
		_availWaveCollection = _globalFitMath1.get_availWaveCollection();
		_solvedCurveCollectionX = _globalFitMath1.get_solvedCurveCollectionX();
		_solvedConcentrationCollectionY = _globalFitMath1.get_solvedConcentrationCollectionY();
		_curveShiftCollection = _globalFitMath1.get_curveShiftCollection();
		_xSAbsSpecCollectionYFiltered = _globalFitMath1.get_xSAbsSpecCollectionYFiltered();
		_ifEnableCurveBounds = _globalFitMath1.get_ifEnableCurveBounds();
		_ifUseWeight = _globalFitMath1.get_ifUseWeight();			
		_ifCalcCurveShifts = _globalFitMath1.get_ifCalcCurveShifts();
	}
	
	
	
	
    public void globalFitAdvancedAlgorithmGradientSetInitialValues()
    {
    	_chanceConst = 0;
    	_minGlobalSigmaConst=10000;
    	
		_nOfAlterableValues=0;
		_counter=0;
		
		for(int i=0; i<_nOfConst; i++)
		{

			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				float[] _valueArray = new float[7];
				_valueCollection.add(_valueArray);
				
				
				_valueCollection.get(_counter)[0] = _constantValues[i];
				if(_valueCollection.get(_counter)[0]<(10))
					_valueCollection.get(_counter)[0] = 10;
				_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*_cubeSideConst;
				_valueCollection.get(_counter)[2] = _valueCollection.get(_counter)[1];//2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
				_valueCollection.get(_counter)[5] = 0;
				_valueCollection.get(_counter)[6] = 0;
				_nOfAlterableValues++;
				_counter++;
			}
		}
		
		_lengthConst=((int)Math.pow((_cubeResolutionConst+1),_nOfAlterableValues));
    }
    
    public void globalFitAdvancedAlgorithmGradientResetInitialValues()
    {
		_counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_slopeSpeedConst = 1;
				if(_valueCollection.get(_counter)[6]==1)
				{
					_slopeSpeedConst = 2;
					//System.out.println("Reduced step for " + i);
				}
					
				
				_valueCollection.get(_counter)[0] = _constantValues[i];
				_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[1]/_slopeSpeedConst;
				_valueCollection.get(_counter)[2] = _valueCollection.get(_counter)[1];
				_valueCollection.get(_counter)[5] = 0;
				_valueCollection.get(_counter)[6] = 0;
				_counter++;
				
				_globalSigmaLastConst = _minGlobalSigmaConst;
			}
		}
    }
    
    public void globalFitAdvancedAlgorithmGradientSetTrialValues()
    {
		_counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_cubeResolutionConst-(_valueCollection.get(_counter)[5]+1))*_valueCollection.get(_counter)[2];
				if(_valueCollection.get(_counter)[3]<0)_valueCollection.get(_counter)[3]=10;  //gives a chance
				
				
				if((_valueCollection.get(_counter)[3]<_variablesLimitsCollection.get(0).get(i)[1])&(_variablesLimitsCollection.get(0).get(i)[1]!=-1))
					_valueCollection.get(_counter)[3] = _variablesLimitsCollection.get(0).get(i)[1]; 
				
				if((_valueCollection.get(_counter)[3]>_variablesLimitsCollection.get(0).get(i)[2])&(_variablesLimitsCollection.get(0).get(i)[2]!=-1))
					_valueCollection.get(_counter)[3] = _variablesLimitsCollection.get(0).get(i)[2]; 
				
				_constantValues[i] = _valueCollection.get(_counter)[3];
				_counter++;
			}
		}
    }
    
    public void  globalFitAdvancedAlgorithmGradientMoveAlongGrid()
    {
		_valueCollection.get(0)[5]++;
		
		for(int n=1; n<_nOfAlterableValues; n++)
		{
			if((_valueCollection.get(n-1)[5]>_cubeResolutionConst)&(_nOfAlterableValues>n))
			{
				_valueCollection.get(n-1)[5]=0;
				_valueCollection.get(n)[5]++;
			}
		}
    }
    
    public void globalFitAdvancedAlgorithmGradientCalcBestAbsCoeff()
    {
    	_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
    	
    	for(int w=0; w<_globalFitMath1.get_availWaveCollection().size(); w++)
		{
				/*if(_globalFitMath1.get_ifContinueToFit())
				{
					_globalFitMath1.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterationsGradientAbs, _nOfChancesAbs, _cubeSideAbs, false);
				}*/
				
    		_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;
		}
    }
    
    public void globalFitAdvancedAlgorithmGradientCalcGlobalSigma()
    {
		_globalSigma=0;
		_maxSteps =  (_solvedCurveCollectionX.size() - _posToStartFittingWith);
		_steps =0;
		

		for(int w=0; w<_availWaveCollection.size(); w++)
		{
			
			if(_ifEnableCurveBounds)
			{
				_start = _markersCollection.get(w).get(0);
				_end = _markersCollection.get(w).get(1);
				_steps = _end-_start;
			}
			else
			{
				_start = _posToStartFittingWith;
				_end = _solvedCurveCollectionX.size();
				_steps = _end-_start;
			}
			
			_tempSigma = 0;
			
			
			
			for(int i= _start; i<_end; i++)
			{
				
				
				_sumOpticalDensity = 0;
				_currentSolvedValue = 0;
				
				for(int m=0; m<_nOfEq; m++)
				{
					_sumOpticalDensity+=  (_solvedConcentrationCollectionY.get(m).get(i)-_beforePulseConcentrationValues[m])*_xSAbsSpecCollectionYFiltered.get(m).get(w);
				}
		
		
				if(_ifCalcCurveShifts)
				{
					_currentSolvedValue = (_sumOpticalDensity-_curveShiftCollection.get(w))*_cuvetteThickness;
				}
				else
				{
					_currentSolvedValue = (_sumOpticalDensity-_levelPosDisplay)*_cuvetteThickness;
				}

				
				
				_tempSigma+= Math.pow((_kinCollectionY.get(w).get(i) - _currentSolvedValue), 2);

			}
			
			
			_tempSigma = (float)Math.sqrt((_tempSigma/_steps));
			
			
			if(_ifUseWeight)
			{
				_tempSigma = _tempSigma*_weightCollection.get(w);
			}
			
			_globalSigma += _tempSigma;
		}

    }
    
    public void globalFitAdvancedAlgorithmGradientCheckForBetterSigma()
    {
		if(_minGlobalSigmaConst>_globalSigma)
		{
			_minGlobalSigmaConst = _globalSigma;	
			
			//System.out.println("Major " + k + " Minor " + j + " Min sigma value = " + _minGlobalSigma);
			for(int i=0; i<_nOfAlterableValues; i++)
			{
				_valueCollection.get(i)[4] = _valueCollection.get(i)[3];
			}
		}

    }
	
	
    public void globalFitAdvancedAlgorithmGradientRememberBestValues()
    {
		_counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_constantValues[i] =_valueCollection.get(_counter)[4];
				
				if((Math.abs(_valueCollection.get(_counter)[4])==Math.abs(_valueCollection.get(_counter)[0]))||(Math.abs(_valueCollection.get(_counter)[2])>=Math.abs(_valueCollection.get(_counter)[4]*0.4))) //if parameter value hasn't change or if step is too big
				{
					//System.out.println("Need to reduce slope for " + i);
					_valueCollection.get(_counter)[6] = 1;
				}
				
				_counter++;
			}
			
		}
    }
	
	
	
    public void globalFitAdvancedAlgorithmGradientDemonstrationalRedraw()
    {
    	_globalFitMath1.fitRK4();
    	_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
    	for(int w=0; w<_globalFitMath1.get_availWaveCollection().size(); w++)
		{
    		{
    			_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;
    			//_globalFitMath1.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterationsGradientAbs*2, _nOfChancesAbs*2, _cubeSideAbs, true);
			}
    		
		}
    	//_globalFitMath1.fitRK4();
    }
	
	
	
	
	
	
	
	
}