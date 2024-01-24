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

public class GlobalFitHyperSpace {
	
	public boolean _ifWriteGrid = false;
	
	
	GlobalFitMath M;
	
	public GlobalFitHyperSpace(GlobalFitMath M)
	{
		this.M =M;
	}
	
	public String performGlobalFitHyperSpace(int _nOfMinorIterations, float _cubeSide, String Path, String PathGrid, float _limit)
	{
		System.out.println("********Hyper space*************");
		//System.out.println("Starting global fit const.");
		

		//int _nOfMinorIterations = 10;
		//float _cubeSide = 0.08f;
		
		String line="";
		int _counter=0, _nOfAlterableValues=0;
		float _centerGlobalSigma=0;
		int _nOfMinima = 0;
		boolean _thisIsLocalMin = false;
		int _cubeResolution = 2;
		int _skip=0;
		
		ArrayList<float[]> _valueCollection = new ArrayList<float[]>();
		ArrayList<float[]> _tempList = new ArrayList<float[]>();
		
		M.readDiffEqLabelsData(true);
		M.fitRK4();

				
				
			
			//write to a file;
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
				
				BufferedWriter outGrid = new BufferedWriter(new FileWriter("c:/1.txt"));
				if(_ifWriteGrid)
					outGrid = new BufferedWriter(new FileWriter(PathGrid, false));
				
				_counter=0;
				if(M._ifAlterConst)	
					for(int i=0; i<M._nOfConst; i++)
					{
							float[] _valueArray = new float[7];
							_valueCollection.add(_valueArray);
							
							_valueCollection.get(_counter)[0] = M._constantValues[i];
							_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*_limit;
							_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterations;
						
							_valueCollection.get(_counter)[5] = 0;
							_valueCollection.get(_counter)[6] = 0;
							_counter++;
							_nOfAlterableValues++;
					}
					
				if(M._ifAlterAbsCoeff)		
					for(int i=0; i<M._nOfEq; i++)
					{
							float[] _valueArray = new float[7];
							_valueCollection.add(_valueArray);
							
							_valueCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(0);
							_valueCollection.get(_counter)[1] = _valueCollection.get(_counter)[0]*_limit;
							_valueCollection.get(_counter)[2] = 2*_valueCollection.get(_counter)[1]/_nOfMinorIterations;
						
							_valueCollection.get(_counter)[5] = 0;
							_valueCollection.get(_counter)[6] = 0;
							_counter++;
							_nOfAlterableValues++;
						
					}
					
				
					System.out.println("Output file: " + Path);
					System.out.println("N of parameters: " + _nOfAlterableValues);
					System.out.println("Ready to perform " + Math.pow((_nOfMinorIterations+1),_nOfAlterableValues) + " cycles.");
					System.out.println("Total amount of cycles is " + Math.pow((_nOfMinorIterations+1),_nOfAlterableValues)*Math.pow((_cubeResolution+1),_nOfAlterableValues) + " cycles.");
					float _time =  (float)(Math.pow((_nOfMinorIterations+1),_nOfAlterableValues)*Math.pow((_cubeResolution+1),_nOfAlterableValues)*0.0015f*3f);
					if(_time <= 200)
					{
						System.out.println("Estimation time is " + _time + " seconds.");
					}
					if(_time > 200 && _time <= (200*60))
					{
						System.out.println("Estimation time is " + _time/60f + " min.");
					}
					if(_time > (200*60))
					{
						System.out.println("Estimation time is " + _time/3600f + " hours.");
					}
				
					
					if(_nOfAlterableValues!=0)
						for(int j=0; j<Math.pow((_nOfMinorIterations+1),_nOfAlterableValues); j++)
						{

							if((((float)j/100f)-Math.round((float)j/100f))==0)
								System.out.print("*");
							if((((float)j/1000f)-Math.round((float)j/1000f))==0)
								System.out.println("  " + j + " ");
							
							_counter=0;
							if(M._ifAlterConst)	
							for(int i=0; i<M._nOfConst; i++)
							{
									_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_nOfMinorIterations/2 -_valueCollection.get(_counter)[5])*_valueCollection.get(_counter)[2];
									if(_valueCollection.get(_counter)[3]<0)
										_valueCollection.get(_counter)[3] = 10;									
									M._constantValues[i] =_valueCollection.get(_counter)[3];
									_counter++;
							}
							
							if(M._ifAlterAbsCoeff)	
							for(int i=0; i<M._nOfEq; i++)
							{
									_valueCollection.get(_counter)[3] = _valueCollection.get(_counter)[0] - (_nOfMinorIterations/2 -_valueCollection.get(_counter)[5])*_valueCollection.get(_counter)[2];
									if(_valueCollection.get(_counter)[3]>0)
										_valueCollection.get(_counter)[3] = -10;
									M._xSAbsSpecCollectionYFiltered.get(i).set(0,_valueCollection.get(_counter)[3]);
									_counter++;
							}
							
							
							
							
							
							
							
							_valueCollection.get(0)[5]++;
							
							for(int n=1; n<_nOfAlterableValues; n++)
							{
								if((_valueCollection.get(n-1)[5]>_nOfMinorIterations)&(_nOfAlterableValues>n))
								{
									_valueCollection.get(n-1)[5]=0;
									_valueCollection.get(n)[5]++;
								}
							}

							
							if(j>_skip)
							{
								//readDiffEqLabelsData(false);
								M.fitRK4();
								
				////////////////////////////////////////////////////////////////
								
								M._globalSigma=M.calcGlobalSigma();
								_centerGlobalSigma = M._globalSigma;

								
								
								//write to a file
								line = "";
								for(int p=0; p<_nOfAlterableValues; p++)
									line +=  _valueCollection.get(p)[3] + "	";
								line+= _centerGlobalSigma;
								outGrid.write(line);
								outGrid.newLine();
								
								
								
								//System.out.println(_constantValues[1] + "	" + _constantValues[2] + "	" + _globalSigma);
								/*float[] temp1 = new float[_nOfAlterableValues+2];
								temp1[0] =_globalSigma;
								for(int l=0; l<_nOfConst; l++)
									temp1[l+1] = _constantValues[l];
								for(int l=0; l<_nOfEq; l++)
									temp1[l+1+_nOfConst] = -_xSAbsSpecCollectionYFiltered.get(l).get(0);;
								_tempList.add(temp1);
								
								*/
								
								
								
								
								
								
								//Hyper cube
								
								
								ArrayList<float[]> _cubeCollection = new ArrayList<float[]>();
								
								_counter=0;
								if(M._ifAlterConst)	
								for(int i=0; i<M._nOfConst; i++)
								{
										float[] _valueArray = new float[7];
										_cubeCollection.add(_valueArray);
										
										_cubeCollection.get(_counter)[0] = M._constantValues[i];
										_cubeCollection.get(_counter)[1] = _cubeCollection.get(_counter)[0]*_cubeSide;
										_cubeCollection.get(_counter)[2] = _cubeCollection.get(_counter)[1];
									
										_cubeCollection.get(_counter)[5] = 0;
										_counter++;
								}
								
								if(M._ifAlterAbsCoeff)	
								for(int i=0; i<M._nOfEq; i++)
								{
										float[] _valueArray = new float[7];
										_cubeCollection.add(_valueArray);
										
										_cubeCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(0);
										_cubeCollection.get(_counter)[1] = _cubeCollection.get(_counter)[0]*_cubeSide;
										_cubeCollection.get(_counter)[2] = _cubeCollection.get(_counter)[1];
									
										_cubeCollection.get(_counter)[5] = 0;
										_cubeCollection.get(_counter)[6] = 0;
										_counter++;
								}

								
								
								_thisIsLocalMin = true;
								
								for(int f=0; f<Math.pow((_cubeResolution+1),_nOfAlterableValues); f++) //3^N iterations
								{
									_counter=0;
									if(M._ifAlterConst)	
									for(int i=0; i<M._nOfConst; i++)
									{
											_cubeCollection.get(_counter)[3] = _cubeCollection.get(_counter)[0] - (_cubeResolution-(_cubeCollection.get(_counter)[5]+1))*_cubeCollection.get(_counter)[2];
											M._constantValues[i] = _cubeCollection.get(_counter)[3];
											_counter++;
									}
									
									if(M._ifAlterAbsCoeff)	
									for(int i=0; i<M._nOfEq; i++)
									{
											_cubeCollection.get(_counter)[3] = _cubeCollection.get(_counter)[0] - (_cubeResolution-(_cubeCollection.get(_counter)[5]+1))*_cubeCollection.get(_counter)[2];
											M._xSAbsSpecCollectionYFiltered.get(i).set(0, _cubeCollection.get(_counter)[3]);
											_counter++;
									}
									
									
									_cubeCollection.get(0)[5]++;
									
									for(int n=1; n<_nOfAlterableValues; n++)
									{
										if((_cubeCollection.get(n-1)[5]>_cubeResolution)&(_nOfAlterableValues>n))
										{
											_cubeCollection.get(n-1)[5]=0;
											_cubeCollection.get(n)[5]++;
										}
									}
									
									M.fitRK4();
									M._globalSigma=M.calcGlobalSigma();
									

									
									
									
									
									
									if(M._globalSigma<_centerGlobalSigma)
									{
										_thisIsLocalMin = false;
									}
									

								}
								
								if(_thisIsLocalMin)
								{
									_nOfMinima++;
									line = "*********************************";
									out.write(line);
									out.newLine();
									System.out.println(line);
									line = "N of iteration = " + j;
									out.write(line);
									out.newLine();
									System.out.println(line);
									line = "Found local minimum. Coordinates:";
									out.write(line);
									out.newLine();
									System.out.println(line);
									for(int t=0; t<_nOfAlterableValues; t++)
									{
										line = "" + Math.abs(_valueCollection.get(t)[3]);
										out.write(line);
										out.newLine();
										System.out.println(line);
									}
										
								}
							}
							
		
							

						}
							
			////////////////////////////////////////////////////////////////				
						line = "*********************************";
						out.write(line);
						out.newLine();
						System.out.println(line);							
						
						line = "N of minima = " + _nOfMinima;
						out.write(line);
						out.newLine();
						System.out.println(line);
			
				
				
				
				
			/*	for(int i=0; i<_tempList.size(); i++)
				{
					line = "";
					for(int l=0; l<(_nOfAlterableValues+1); l++)
						line+= _tempList.get(i)[l] + "	";
					
					out.write(line);
					out.newLine();
				}
				
				*/
				
				out.close();
				outGrid.close();
			}
			catch(IOException e)
			{
				
			}
	
		return line + ". For more information see the output file.";	
			
	}

	
	
	
	
	public boolean checkIfLocalMinimum(int _cubeResol, float _cubeSide)
	{
		System.out.println("********Checking if this is local minimum*************");

		int _counter=0, _nOfAlterableValues=0;
		float _centerGlobalSigma=0;
		int _nOfMinima = 0;
		boolean _thisIsLocalMin = false;
		int _cubeResolution = _cubeResol;
		boolean _ifInside = false;

		
		
		M.readDiffEqLabelsData(true);
		M.fitRK4();
						
		////////////////////////////////////////////////////////////////
						
		M._globalSigma=M.calcGlobalSigma();
						_centerGlobalSigma = M._globalSigma;
						
						//Hyper cube
						
						
						ArrayList<float[]> _cubeCollection = new ArrayList<float[]>();
						
						_counter=0;
						if(M._ifAlterConst)	
						for(int i=0; i<M._nOfConst; i++)
						{
							if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
							{
								float[] _valueArray = new float[7];
								_cubeCollection.add(_valueArray);
								
								_cubeCollection.get(_counter)[0] = M._constantValues[i];
								_cubeCollection.get(_counter)[1] = _cubeCollection.get(_counter)[0]*_cubeSide*2/_cubeResolution;
								_cubeCollection.get(_counter)[2] = _cubeCollection.get(_counter)[1];
							
								_cubeCollection.get(_counter)[5] = 0;
								_counter++;
								_nOfAlterableValues++;
							}
								
						}
						
						if(M._ifAlterAbsCoeff)	
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
							{
								float[] _valueArray = new float[7];
								_cubeCollection.add(_valueArray);
								
								_cubeCollection.get(_counter)[0] = M._xSAbsSpecCollectionYFiltered.get(i).get(0);
								_cubeCollection.get(_counter)[1] = _cubeCollection.get(_counter)[0]*_cubeSide*2/_cubeResolution;
								_cubeCollection.get(_counter)[2] = _cubeCollection.get(_counter)[1];
							
								_cubeCollection.get(_counter)[5] = 0;
								_cubeCollection.get(_counter)[6] = 0;
								_counter++;
								_nOfAlterableValues++;
							}

						}

						
						
						_thisIsLocalMin = true;
						
					if(_nOfAlterableValues!=0)
						for(int f=0; f<Math.pow((_cubeResolution+1),_nOfAlterableValues); f++) //3^N iterations
						{
							_ifInside=true;
							for(int i=0; i<_nOfAlterableValues;i++)
							{
								if(_cubeCollection.get(i)[5]==_cubeResolution || _cubeCollection.get(i)[5]==0)
									_ifInside=false;
							}
							
							
							if(!_ifInside)
							{
								_counter=0;
								if(M._ifAlterConst)	
								for(int i=0; i<M._nOfConst; i++)
								{
									if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
									{
										_cubeCollection.get(_counter)[3] = _cubeCollection.get(_counter)[0] - (_cubeResolution-(_cubeCollection.get(_counter)[5]+_cubeResolution/2))*_cubeCollection.get(_counter)[2];
										M._constantValues[i] = _cubeCollection.get(_counter)[3];
										_counter++;
									}

								}
								
								if(M._ifAlterAbsCoeff)	
								for(int i=0; i<M._nOfEq; i++)
								{
									if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
									{
										_cubeCollection.get(_counter)[3] = _cubeCollection.get(_counter)[0] - (_cubeResolution-(_cubeCollection.get(_counter)[5]+_cubeResolution/2))*_cubeCollection.get(_counter)[2];
										M._xSAbsSpecCollectionYFiltered.get(i).set(0, _cubeCollection.get(_counter)[3]);
										_counter++;
									}

								}
							}

							
							
							_cubeCollection.get(0)[5]++;
							
							for(int n=1; n<_nOfAlterableValues; n++)
							{
								if((_cubeCollection.get(n-1)[5]>_cubeResolution)&(_nOfAlterableValues>n))
								{
									_cubeCollection.get(n-1)[5]=0;
									_cubeCollection.get(n)[5]++;
								}
							}
							
							
							if(!_ifInside)
							{
								M.fitRK4();
								M._globalSigma=M.calcGlobalSigma();
								
								if(M._globalSigma<_centerGlobalSigma)
								{
									_thisIsLocalMin = false;
								}
							}

							

						}
						
						if(_thisIsLocalMin)
						{
							_nOfMinima++;
							System.out.println("*********************************");
							System.out.println("Found local minimum.");
							//for(int t=0; t<_nOfAlterableValues; t++)
							//	System.out.println(Math.abs(_valueCollection.get(t)[3]));
						}
						else
						{
							System.out.println("*********************************");
							System.out.println("This is NOT a local minimum.");
						}
						
						
						_counter=0;
						if(M._ifAlterConst)	
						for(int i=0; i<M._nOfConst; i++)
						{
							if(M._variablesLimitsCollection.get(0).get(i)[0]==1)
							{
								M._constantValues[i] = _cubeCollection.get(_counter)[0];
								_counter++;
							}

						}
						
						if(M._ifAlterAbsCoeff)	
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._variablesLimitsCollection.get(1).get(i)[0]==1)
							{
								M._xSAbsSpecCollectionYFiltered.get(i).set(0, _cubeCollection.get(_counter)[0]);
								_counter++;
							}
						}
						M.fitRK4();

	
		return _thisIsLocalMin;	
			
	}
	
	
	

}
