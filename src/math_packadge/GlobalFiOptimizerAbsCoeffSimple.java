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


public class GlobalFiOptimizerAbsCoeffSimple {

	
	GlobalFitMath M;
	float _globalSigma;
	
	public GlobalFiOptimizerAbsCoeffSimple(GlobalFitMath M)
	{
		this.M =M;
	}






	public float performGlobalFitAbsCoeff(int w, int _nOfMajorIterationsAbs, int _nOfMinorIterationsAbs)
	{
		System.out.println("*********Test************");
		//System.out.println("Starting global fit abs coeff.");
		
		_nOfMajorIterationsAbs = 1;
		_nOfMinorIterationsAbs = 20;
		//readDiffEqLabelsData(true);
		//fitRK4();

		float _minGlobalSigma=10000,  _sumOpticalDensity=0, _globalSigmaLast=1E9f, _deviation;
		int _nOfVariables=0, _nOfAlterableValues=0,_counter=0;
		float _slopeSpeed=2;
		//float _initialValue, _dValue, _valueStep, _trialValue, _bestValue=0; // float[5] , _initialValue - 0; _dValue - 1; _valueStep - 2; _trialValue - 3; _bestValue - 4; count - 5; ifMinOutOfBounds - 6;
		int _start=0, _end=0, _maxSteps = 0, _steps=0;
		
		
		ArrayList<float[]> _valueCollection = new ArrayList<float[]>();
		ArrayList<float[]> _tempList = new ArrayList<float[]>();
		
		

			
			_valueCollection = new ArrayList<float[]>();
			
			_minGlobalSigma=10000;
			_globalSigmaLast=_globalSigma*10;
			
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
					_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*0.9f;
					_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
					_valueCollection.get(_counter)[5] = 0;
					_valueCollection.get(_counter)[6] = 0;
					_nOfAlterableValues++;
					_counter++;
				}
			}
			
			
			


			

			for(int k=0; k<_nOfMajorIterationsAbs; k++)
			{
				if(k>0)
				{
					_counter=0;
					for(int i=0; i<_nOfVariables; i++)
					{
						if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
						{
							_valueCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(w);
							if(_valueCollection.get(_counter)[6] ==0)
							{
								_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[1]/M._slopeValue;
							}
							else
							{
								//System.out.println("Min is out of bounds");
							}
							
							_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsAbs;
							_valueCollection.get(_counter)[5] = 0;
							_valueCollection.get(_counter)[6] = 0;
							_counter++;
						}
					}

					_globalSigmaLast=_globalSigma;
					//_minGlobalSigma=10000;
				}

				
				
				for(int j=0; j<Math.pow((_nOfMinorIterationsAbs+1),_nOfAlterableValues); j++)
				{

					
					
					_counter=0;
					for(int i=0; i<_nOfVariables; i++)
					{
						if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
						{
							_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_nOfMinorIterationsAbs/2 -_valueCollection.get(_counter)[5])*_valueCollection.get(_counter)[2];
							if(_valueCollection.get(_counter)[3]>0)_valueCollection.get(_counter)[3]=-10;  //gives a chance
							M._xSAbsSpecCollectionYFiltered.get(i).set(w, _valueCollection.get(_counter)[3]);
							_counter++;
						}
					}
					
					
					_valueCollection.get(0)[5]++;
					
					
					
					
					for(int n=1; n<_nOfAlterableValues; n++)
					{
						if((_valueCollection.get(n-1)[5]>_nOfMinorIterationsAbs)&(_nOfAlterableValues>n))
						{
							_valueCollection.get(n-1)[5]=0;
							_valueCollection.get(n)[5]++;
						}
					}
					
	////////////////////////////////////////////////////////////////////////
					
					_globalSigma=0;
					//_maxSteps =  (_solvedCurveCollectionX.size() - _posToStartFittingWith);
					
					
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
						_globalSigma+= Math.pow(_deviation, 2);
					}
					
					_globalSigma = _globalSigma/_steps;
					
					float[] temp1 = new float[_nOfAlterableValues+2];
					temp1[0] =_globalSigma;
					for(int l=0; l<_nOfAlterableValues; l++)
						temp1[l+1] = -M._xSAbsSpecCollectionYFiltered.get(l).get(w);
					_tempList.add(temp1);
					
					
	//////////////////////////////////////////////////////////////////////////
					
					if(_minGlobalSigma>_globalSigma)
					{
						_minGlobalSigma = _globalSigma;	
						
						//System.out.println("Major " + k + " Minor " + j + " Min sigma value = " + _minGlobalSigma);
						for(int i=0; i<_nOfAlterableValues; i++)
						{
							_valueCollection.get(i)[4] = _valueCollection.get(i)[3];
							
							if((_valueCollection.get(i)[5]==0)||(_valueCollection.get(i)[5]==1)||(_valueCollection.get(i)[5]==(_nOfMinorIterationsAbs+1))||(_valueCollection.get(i)[5]==(_nOfMinorIterationsAbs)))
							{
								_valueCollection.get(i)[6]=1;
							}
							else
							{
								_valueCollection.get(i)[6]=0;
							}
						}
					}
						
			

				}
				

					
				_counter=0;
				for(int i=0; i<M._xSAbsSpecCollectionYFiltered.size(); i++)
				{
					if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
					{
						M._xSAbsSpecCollectionYFiltered.get(i).set(w, _valueCollection.get(_counter)[4]);
						_counter++;
					}

				}

			}

			
			
			
			//write to a file;
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("c:/examples/Testing schemes/output.txt"));
				String line="";
				for(int i=0; i<_tempList.size(); i++)
				{
					line = "";
					for(int l=0; l<(_nOfAlterableValues+1); l++)
						line+= _tempList.get(i)[l] + "	";
					
					out.write(line);
					out.newLine();
				}
				
				out.close();
			}
			catch(IOException e)
			{
				
			}
			
			
			
			

			
		
		return _globalSigma;
		
		//fitRK4();
		
		
		

		
	}




}
