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


public class GlobalFitOptimizerAbsCoeffGradient {

	
	GlobalFitMath M;
	
	public GlobalFitOptimizerAbsCoeffGradient(GlobalFitMath M)
	{
		this.M =M;
	}
	
	
	
	
	
	public float performGlobalFitAbsCoeffFindGradientMinimum(int w, int _nOfMajorIterations, int _nOfChances, float _cubeSide, boolean _ifFitAnyway)
	{
		//System.out.println("*********************");
		//System.out.println("N of iterations abs = " + _nOfMajorIterations);
		//System.out.println("N of chances abs = " + _nOfChances);
		//System.out.println("cube side abs = " + _cubeSide);


		float _minGlobalSigma=10000,  _sumOpticalDensity=0, _globalSigmaLast=1E9f, _deviation;
		int _nOfVariables=0, _nOfAlterableValues=0,_counter=0;
		float _slopeSpeed=1;
		//float _initialValue, _dValue, _valueStep, _trialValue, _bestValue=0; // float[5] , _initialValue - 0; _dValue - 1; _valueStep - 2; _trialValue - 3; _bestValue - 4; count - 5; IfNeededToReduceStep - 6;
		int _start=0, _end=0, _maxSteps = 0, _steps=0;
		//int _nOfMinorIterationsAbs=2;
		int _cubeResolution=2;
		boolean _ifSigmaChanged = true;
		int _nOfReductions=0, _chance=0;
		boolean _ifContinueToFit = true;
		
		ArrayList<float[]> _valueCollection = new ArrayList<float[]>();
		
		
			ArrayList<String> _stringCollection = new ArrayList<String>();

			
			_valueCollection = new ArrayList<float[]>();
			

			if(_ifFitAnyway)
			{
				_ifContinueToFit = true;
			}
			else
			{
				_ifContinueToFit = M._ifContinueToFit;
			}
			
			
			_nOfVariables=M._xSAbsSpecCollectionYFiltered.size();
			_nOfAlterableValues=0;
			_counter=0;
			
			for(int i=0; i<_nOfVariables; i++)
			{

				if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
				{
					float[] _valueArray = new float[7];
					_valueCollection.add(_valueArray);
					
					
					_valueCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(w);
					if(_valueCollection.get(_counter)[0]>(-100))
						_valueCollection.get(_counter)[0] = -100;
					_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*_cubeSide;
					_valueCollection.get(_counter)[2] = _valueCollection.get(_counter)[1];//2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
					_valueCollection.get(_counter)[5] = 0;
					_valueCollection.get(_counter)[6] = 0;
					_nOfAlterableValues++;
					_counter++;
				}
			}
			
			
			
			//System.out.println("initial value 1 = " + _valueCollection.get(0)[0]);
			//System.out.println("initial value 2 = " + _valueCollection.get(1)[0]);
			
			

			

			for(int k=0; (k<_nOfMajorIterations)&(_ifContinueToFit); k++)
			{
				
				//System.out.println("*************************************MAJOR iteration " + k);

				
				if(k>0)
				{
					_counter=0;
					for(int i=0; i<_nOfVariables; i++)
					{
						if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
						{
							_slopeSpeed = 1;
							if(_valueCollection.get(_counter)[6]==1)
							{
								_slopeSpeed = 2;
								//System.out.println("Reduced step for " + i);
							}
								
							
							_valueCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(w);
							_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[1]/_slopeSpeed;
							_valueCollection.get(_counter)[2] = _valueCollection.get(_counter)[1];//2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
							_valueCollection.get(_counter)[5] = 0;
							_valueCollection.get(_counter)[6] = 0;
							_counter++;
							
							_globalSigmaLast = _minGlobalSigma;
						}
					}

					
					//System.out.println("New initial value 1 = " + _valueCollection.get(0)[0]);
					//System.out.println("New initial value 2 = " + _valueCollection.get(1)[0]);
					

					//_globalSigmaLast=_globalSigma;
					//_minGlobalSigma=10000;
				}

				
				
				for(int j=0; j<Math.pow((_cubeResolution+1),_nOfAlterableValues); j++) //3^N iterations
				{

					//System.out.println("--------------------------------------minor iteration " + j);
					
					_counter=0;
					for(int i=0; i<_nOfVariables; i++)
					{
						if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
						{
							_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_cubeResolution-(_valueCollection.get(_counter)[5]+1))*_valueCollection.get(_counter)[2];
							if(_valueCollection.get(_counter)[3]>0)_valueCollection.get(_counter)[3]=-10;  //gives a chance
							M._xSAbsSpecCollectionYFiltered.get(i).set(w, _valueCollection.get(_counter)[3]);
							_counter++;
							
							//System.out.println("trial value " + i + " = " + _valueCollection.get(i)[3]);	
						}
					}
					
					
					
					
					
					_valueCollection.get(0)[5]++;
					
					
					
					
					for(int n=1; n<_nOfAlterableValues; n++)
					{
						if((_valueCollection.get(n-1)[5]>_cubeResolution)&(_nOfAlterableValues>n))
						{
							_valueCollection.get(n-1)[5]=0;
							_valueCollection.get(n)[5]++;
						}
					}
					
	////////////////////////////////////////////////////////////////////////
					
					M._globalSigma=0;
					_maxSteps =  (M._solvedCurveCollectionX.size() - M._posToStartFittingWith);
					
					
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
					
					
					
					
					for(int i= _start; i<_end; i++)
					{
						
						_sumOpticalDensity = 0;
						for(int m=0; m<M._nOfEq; m++)
						{
							_sumOpticalDensity+=  (M._solvedConcentrationCollectionY.get(m).get(i)-M._beforePulseConcentrationValues[m])*M._xSAbsSpecCollectionYFiltered.get(m).get(w);
						}
						
						if(M._ifCalcCurveShifts)
						{
							_sumOpticalDensity = (_sumOpticalDensity-M._curveShiftCollection.get(w))*M._cuvetteThickness;
						}
						else
						{
							_sumOpticalDensity = (_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness;
						}

						_deviation = (M._kinCollectionY.get(w).get(i) - _sumOpticalDensity);
						M._globalSigma+= Math.pow(_deviation, 2);
					}
					
					M._globalSigma = M._globalSigma*_maxSteps/_steps;
					
					//System.out.println("Current global sigma = " + _globalSigma);
	
	//////////////////////////////////////////////////////////////////////////
					
					if(_minGlobalSigma>M._globalSigma)
					{
						
	
						
						_minGlobalSigma = M._globalSigma;	
						
						//System.out.println("Major " + k + " Minor " + j + " Min sigma value = " + _minGlobalSigma);
						for(int i=0; i<_nOfAlterableValues; i++)
						{
							_valueCollection.get(i)[4] = _valueCollection.get(i)[3];
						}
					}
						
			

				}
				
				//_stringCollection.add(("" + -_valueCollection.get(1)[0] + "	" + -_valueCollection.get(0)[0]) + "	" + _minGlobalSigma);
					
				_counter=0;
				for(int i=0; i<M._xSAbsSpecCollectionYFiltered.size(); i++)
				{
					if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
					{
						M._xSAbsSpecCollectionYFiltered.get(i).set(w, _valueCollection.get(_counter)[4]);
						
						if((Math.abs(_valueCollection.get(_counter)[4])==Math.abs(_valueCollection.get(_counter)[0]))||(Math.abs(_valueCollection.get(_counter)[2])>=Math.abs(_valueCollection.get(_counter)[4]*0.4))) //if parameter value hasn't change or if step is too big
						{
							//System.out.println("Need to reduce slope for " + i);
							_valueCollection.get(_counter)[6] = 1;
						}
							
						//System.out.println("best value " + _counter + " = " + _valueCollection.get(_counter)[4]);
						
						
						_counter++;
					}

				}
				
				
				if(Math.abs(_globalSigmaLast-_minGlobalSigma)<0.0001f)
				{
					_chance++;
					if(_chance>_nOfChances)
						k = _nOfMajorIterations;
				}

		

				

			}
			
			//System.out.println("Best value 1 = " + _xSAbsSpecCollectionYFiltered.get(0).get(w));
			//System.out.println("Best value 2 = " + _xSAbsSpecCollectionYFiltered.get(1).get(w));
			
			//for(int i=0; i<_stringCollection.size(); i++)
				//System.out.println(_stringCollection.get(i));
		
		return M._globalSigma;
		
		
		
		
		

		
	}


}
