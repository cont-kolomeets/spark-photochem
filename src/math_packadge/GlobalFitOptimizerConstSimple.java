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


public class GlobalFitOptimizerConstSimple {

	
	GlobalFitMath M;
	
	public GlobalFitOptimizerConstSimple(GlobalFitMath M)
	{
		this.M =M;
	}


	
	public float performGlobalFitConstSimpleRange(int _nOfMajorIterationsConst, int _nOfMinorIterationsConst)   // k - n of major iterations;
	{
		//System.out.println("*********************");
		//System.out.println("Starting global fit const.");
		
		_nOfMajorIterationsConst = 1;
		_nOfMinorIterationsConst = 20;
		//fitRK4();
		//readDiffEqLabelsData(true);
		//fitRK4();



		
		float _minGlobalSigma=10000, _sumOpticalDensity=0, _tempSigma = 0, _globalSigmaLast=M._globalSigma*10;
		//float _initialValue, _dValue, _valueStep, _trialValue, _bestValue=0; // float[6] , _initialValue - 0; _dValue - 1; _valueStep - 2; _trialValue - 3; _bestValue - 4; count - 5; ifMinOutOfBounds - 6;
		int _counter=0, _nOfAlterableValues=0;
		float _minLimit=0, _maxLimit=0;
		int _start=0, _end=0, _maxSteps = 0, _steps=0;
		
		ArrayList<float[]> _valueCollection = new ArrayList<float[]>();
		ArrayList<float[]> _tempList = new ArrayList<float[]>();
		
		
		//_minGlobalSigma_nOfVariables=_xSAbsSpecCollectionYFiltered.size();
		
		_counter=0;
			for(int i=0; i<M._nOfConst; i++)
			{
				if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
				{
					float[] _valueArray = new float[7];
					_valueCollection.add(_valueArray);
					
					//_minLimit=-1;
					//_maxLimit=-1;
					
					_valueCollection.get(_counter)[0] = M._constantValues[i];
					_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*0.9f;
					_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsConst;
					
					_minLimit = _valueCollection.get(_counter)[0] - (_nOfMinorIterationsConst/2 -0)*_valueCollection.get(_counter)[2];
					if((_minLimit<M._variablesLimitsCollection.get(0).get(i)[1])&(M._variablesLimitsCollection.get(0).get(i)[1]!=-1))
						_minLimit = M._variablesLimitsCollection.get(0).get(i)[1]; 
						
					_maxLimit = _valueCollection.get(_counter)[0] + (_nOfMinorIterationsConst/2)*_valueCollection.get(_counter)[2];
					if((_maxLimit>M._variablesLimitsCollection.get(0).get(i)[2])&(M._variablesLimitsCollection.get(0).get(i)[2]!=-1))
						_maxLimit = M._variablesLimitsCollection.get(0).get(i)[2];
						
					
					_valueCollection.get(_counter)[0] = (_minLimit + _maxLimit)/2;
					_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0] - _minLimit;
					_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsConst;
					
				
					_valueCollection.get(_counter)[5] = 0;
					_valueCollection.get(_counter)[6] = 0;
					_counter++;
					_nOfAlterableValues++;

				}
				
			}
		
		

		
			for(int k=0; k<_nOfMajorIterationsConst; k++)
			{
				
				if(k>0)
				{
					_counter=0;
					for(int i=0; i<M._nOfConst; i++)
					{
						
						if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
						{
							_valueCollection.get(_counter)[0] = M._constantValues[i];
							
							if(_valueCollection.get(_counter)[6] ==0)
							{
								_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[1]/M._slopeValueConst;
							}
							else
							{
								//System.out.println("Min is out of bounds");
							}
							

							_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsConst;
							
							
							
							
							
							_minLimit = _valueCollection.get(_counter)[0] - (_nOfMinorIterationsConst/2 -0)*_valueCollection.get(_counter)[2];
							if((_minLimit<M._variablesLimitsCollection.get(0).get(i)[1])&(M._variablesLimitsCollection.get(0).get(i)[1]!=-1))
								_minLimit = M._variablesLimitsCollection.get(0).get(i)[1]; 
								
							_maxLimit = _valueCollection.get(_counter)[0] + (_nOfMinorIterationsConst/2)*_valueCollection.get(_counter)[2];
							if((_maxLimit>M._variablesLimitsCollection.get(0).get(i)[2])&(M._variablesLimitsCollection.get(0).get(i)[2]!=-1))
								_maxLimit = M._variablesLimitsCollection.get(0).get(i)[2];
								
							
							_valueCollection.get(_counter)[0] = (_minLimit + _maxLimit)/2;
							_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0] - _minLimit;
							_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterationsConst;
							
							_valueCollection.get(_counter)[5] = 0;
							_valueCollection.get(_counter)[6] = 0;
							_counter++;
						}

			
					}
					_globalSigmaLast=M._globalSigma;
					_minGlobalSigma=10000;
				}

				
				
				for(int j=0; j<Math.pow((_nOfMinorIterationsConst+1),_nOfAlterableValues); j++)
				{

					
					_counter=0;
					for(int i=0; i<M._nOfConst; i++)
					{
						if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
						{
							_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_nOfMinorIterationsConst/2 -_valueCollection.get(_counter)[5])*_valueCollection.get(_counter)[2];
							if(_valueCollection.get(_counter)[3]<0)_valueCollection.get(_counter)[3]=100;  //gives a chance
							M._constantValues[i] =_valueCollection.get(_counter)[3];
							_counter++;
						}


					}
					
					
					_valueCollection.get(0)[5]++;
					
					
					
					
					for(int n=1; n<_nOfAlterableValues; n++)
					{
						if((_valueCollection.get(n-1)[5]>_nOfMinorIterationsConst)&(_nOfAlterableValues>n))
						{
							_valueCollection.get(n-1)[5]=0;
							_valueCollection.get(n)[5]++;
						}
					}

					
					//readDiffEqLabelsData(false);
					M.fitRK4();
					
	////////////////////////////////////////////////////////////////
					
					M._globalSigma=0;
					_maxSteps =  (M._solvedCurveCollectionX.size() - M._posToStartFittingWith);
					_steps =0;
					
					
					
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
						
						_tempSigma = 0;
						
						
						
						for(int i= _start; i<_end; i++)
						{
							_tempSigma+= Math.pow((M._kinCollectionY.get(w).get(i) - M._solvedCurveCollectionY.get(w).get(i)), 2);
						}
						
						
						_tempSigma = (float)Math.sqrt((_tempSigma/_steps));
						
						if(M._ifUseWeight)
						{
							_tempSigma = _tempSigma*M._weightCollection.get(w);
						}
						
						M._globalSigma += _tempSigma;
					}

					//System.out.println(_constantValues[1] + "	" + _constantValues[2] + "	" + _globalSigma);
					float[] temp1 = new float[_nOfAlterableValues+2];
					temp1[0] =M._globalSigma;
					for(int l=0; l<_nOfAlterableValues; l++)
						temp1[l+1] = M._constantValues[l];
					_tempList.add(temp1);
					
	////////////////////////////////////////////////////////////////				
					
					
					if(_minGlobalSigma>M._globalSigma)
					{
						_minGlobalSigma = M._globalSigma;	
						for(int i=0; i<_nOfAlterableValues; i++)
						{
							_valueCollection.get(i)[4] = _valueCollection.get(i)[3];
							
							
							if((_valueCollection.get(i)[5]==1)||(_valueCollection.get(i)[5]==(_nOfMinorIterationsConst+1)))
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
				
				
				//if(Math.abs(_globalSigma/_globalSigmaLast-1)<0.01)
				//{
				//	k = _nOfMajorIterationsConst;
				//}
				_counter=0;
				for(int i=0; i<M._nOfConst; i++)
				{
					if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
					{
						M._constantValues[i] =_valueCollection.get(_counter)[4];
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
	
		return M._globalSigma;	
			
	}
}
