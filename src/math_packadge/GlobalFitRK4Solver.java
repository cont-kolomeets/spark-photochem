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


public class GlobalFitRK4Solver {

	
	GlobalFitMath M;
	
	public GlobalFitRK4Solver(GlobalFitMath M)
	{
		this.M =M;
	}
	
	

	
	public String readDiffEqLabelsData (boolean readLabelsContent)
	{
		String _result="";
		//System.out.println("Reading input content");
		M._funcCollection.clear();
		
		
		if(readLabelsContent)
		{
			for(int i=0; i<M._nOfConst; i++)
			{
				if(!M._constantLabelsContent[i].equals(""))
				{
					try
					{
						M._constantValues[i] = Float.parseFloat(M._constantLabelsContent[i].replace(',', '.'));
						//System.out.println("k" + i +"= "+ _constantValues[i]);
					}
					catch(NumberFormatException e)
					{
						_result = "Can't read constants!";
						System.out.println("Can't read constants!");
						//throw new NumberFormatException();
						//JOptionPane.showMessageDialog(this, "Can't read constants!");
					}

				}
			}
		}

		
		for(int i=0; i<M._nOfEq; i++)   // run through all equations
		{

			
			if(readLabelsContent)
			{
				if(M._beforePulseConcentrationLabelsContent[i]!="")
				{
					try
					{
						M._beforePulseConcentrationValues[i] = Float.parseFloat(M._beforePulseConcentrationLabelsContent[i].replace(',', '.'));
						//System.out.println("dX" + i +"= "+ _beforePulseConcentrationValues[i]);
					}
					catch(NumberFormatException e)
					{
						_result = "Can't read dXi pulse values!";
						System.out.println("Can't read dXi pulse values!");
						//throw new NumberFormatException();
						//JOptionPane.showMessageDialog(this, "Can't read dXi pulse values!");
					}

				}
				
				
				if(!M._concentrationLabelsContent[i].equals(""))
				{
					try
					{
						M._concentrationValues[i] = Float.parseFloat(M._concentrationLabelsContent[i].replace(',', '.'));//-_beforePulseConcentrationValues[i];
						//_concentrationValuesIni[i] = _concentrationValues[i];
						//System.out.println("X" + i +"(0)= "+ _concentrationValues[i]);
					}
					catch(NumberFormatException e)
					{
						_result = "Can't read initial concentrations!";
						System.out.println("Can't read initial concentrations!");
						//throw new NumberFormatException();
						//JOptionPane.showMessageDialog(this, "Can't read initial concentrations!");
					}

				}
			}
			
			
			
			if(!M._concentrationLabelsContent[i].equals(""))
			{
				M._concentrationValuesIni[i] = M._concentrationValues[i];
			}
			



		
		
		ArrayList<float[]> _functionElements = new ArrayList<float[]>();
		
		String s = M._eqLabelsContent[i];   // -2*k1*X1*X2-k2*X1*X2+2*k3*X2*X2
		String s1;
		int _currentIndex;
		String[] _elements;
		float _constantMultiplier;
		int _XsNumberCounter;
		ArrayList<Integer> _XsNumber;
		ArrayList<Float> _funcCollection3rdRow;
		ArrayList<ArrayList<Float>> _funcCollection2ndRow = new ArrayList<ArrayList<Float>>();

		
		try
		{
			s = s.replace('x', 'X');	// convert xs to Xs(UpperCase);
			s = s.replace('K', 'k');	// convert Ks to ks(LowerCase);
			s = s.replace(',', '.');	// replaces "," with "."
			s = s.replace('*', 'Z');	// replaces "," with "."
			if(s.indexOf("-")!=0) s="+" + s;




		

		
		//System.out.println("funciton " + i +" = "+ s);
		//System.out.println("length " + i +" = "+ s.length());
		//System.out.println("+ at " + i +" = "+ s.indexOf("+",1));
		
		int cycle=1;
		while(cycle==1)   //run through all elements in the equation; repeat until the string is over
		{
			
			_currentIndex=s.indexOf("-", 1);
			if (_currentIndex==-1)
				_currentIndex=s.indexOf("+", 1);
			if (_currentIndex==-1)
				_currentIndex=s.length();
			
			
			//System.out.println("indext " + i +" = "+ _currentIndex);
		
			if(_currentIndex==0) break;
			
			 s1=s.substring(0,_currentIndex);
			 s=s.substring(_currentIndex, s.length());
			
			 
			//System.out.println("s1 " + i +" = "+ s1);
			//System.out.println("s " + i +" = "+ s);

			 // first cut the sign off
			 _constantMultiplier=1;
			 
			 _elements = s1.split("Z");  // s1=="2*k1*X1*X2"
			 if(_elements[0].indexOf("-")==0)
				 _constantMultiplier=-1;
			 _elements[0]= _elements[0].substring(1,_elements[0].length());
			 
			 for(int j=0; j<_elements.length; j++)
			 {
					//System.out.println("element " + j +" = "+ _elements[j]);
			 }
			 
			 
			 // Analize s1;

			 // after splitting "2", "k1", "X1", "X2"
			 // go through the _elements array, identify the content, and write into _functionElements
	
				 
			 //_XsNumberCounter=0;
			 _XsNumber = new  ArrayList<Integer>();
			 _funcCollection3rdRow = new  ArrayList<Float>();
			 int _numberOfK = -1;
	 
			 

				 for(int j=0; j<_elements.length; j++) // 4 cycles
				 {
					 //CharSequence c="k";
					 if(_elements[j].contains("k"))
					 {
						 //_constantMultiplier*=_constantValues[Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1]; //get the number of the constant and it's value
						 _numberOfK = Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1; // remember the number of the k


						 
						 //System.out.println("constant number =" + _elements[j].substring(1,_elements[j].length()));
						 //System.out.println("constant value =" + _constantValues[Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1]);
						 //System.out.println("multiplier ="+ _constantMultiplier);
					 }
					 if(_elements[j].contains("(0)"))
					 {
						 _constantMultiplier*=M._concentrationValuesIni[Integer.parseInt(_elements[j].substring(1,_elements[j].length()-3))-1]; //get the number of the constant and it's value
						 
						 //System.out.println("constant number =" + _elements[j].substring(1,_elements[j].length()));
						 //System.out.println("constant value =" + _constantValues[Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1]);
						 //System.out.println("multiplier ="+ _constantMultiplier);
					 }				 
					 if((_elements[j].contains("X"))&(!_elements[j].contains("(0)")))
					 {
						 _XsNumber.add(Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1); // remember the number of the X
						 //System.out.println("number of X ="+ Integer.parseInt(_elements[j].substring(1,_elements[j].length())));
					 }	
					 if((!_elements[j].contains("k"))&(!_elements[j].contains("X"))&(!_elements[j].contains("(0)")))
					 	 _constantMultiplier*=Float.parseFloat(_elements[j].substring(0,_elements[j].length())); // add to the constant multiplier some coefficient
				 }
				 
			 


				//System.out.println("total multiplier ="+ _constantMultiplier);
			 
			 
			 //result: _cM = -1*2*k1(value); _XsNumber={1, 2}
			 //now fill the 3rd row of the _funcCollection;
			 //first goes the constant multiplier;
			 _funcCollection3rdRow.add(_constantMultiplier);
			 _funcCollection3rdRow.add(_numberOfK + 0f);
			 //now add the number of Xs(their values change every itteration step)
			 for(int k=0; k<_XsNumber.size(); k++)
				 _funcCollection3rdRow.add(0f+_XsNumber.get(k));
			 // the result: get(0)==-1*2*k1; get(1)==1; get(2)==2
			 
			 
			//now add the 3rd row to the 2nd one
			 _funcCollection2ndRow.add(_funcCollection3rdRow);
			 


		} 
		
		}
		catch(NullPointerException e)
		{
			_result = "Check differntial equations!!!";
		}
		catch(NumberFormatException e)
		{
			 _result = "Check differntial equations!!!";
			 //throw new NumberFormatException();
			 //JOptionPane.showMessageDialog(this, "Check differntial equations!!!");
		}
		
		//when the 2nd row is filled add it to the 1st row, and then go to the next equation
		M._funcCollection.add(_funcCollection2ndRow);
		
		
		}	
		
		
		
		
		
		
		
		//k1*X1-k2*X1+k3*X1*X1;
				
		
		
		//when press Start fitting it starts to solve diff equations
		
		
		return _result;
	

		
	}
	
	
	public Float findConstantName(String s)
	{
		int _number=0;
		
		if(s.indexOf("k")!=-1)
		{
			char c=s.charAt(s.indexOf("k")+1);
			String s1 = "" + c;
			_number = Integer.parseInt(s1);
		}
		
		return M._constantValues[_number];
			
	}
	
	
	
	
	
	
	
	
	
	public final int readConcentrationDependencies()
	{
		int _result=-1;

		M._concFuncCollection.clear();
		
		
		
		for(int i=0; i<M._nOfEq; i++)
		{
			if(!M._concentrationLabelsContent[i].equals(""))
			{
				try
				{
					M._concentrationValues[i] = Float.parseFloat(M._concentrationLabelsContent[i].replace(',', '.'));//-_beforePulseConcentrationValues[i];
				}
				catch(NumberFormatException e)
				{
					System.out.println("Can't read initial concentrations!");
				}

			}	
			
			if(!M._concentrationLabelsContent[i].equals(""))
			{
				M._concentrationValuesIni[i] = M._concentrationValues[i];
			}
		}


		
		
		for(int i=0; i<M._nOfEqRel; i++)   // run through all equations
		{

			

		
		
		String s = M._concEqLabelsContent[i];   // -2*k1*X1*X2-k2*X1*X2+2*k3*X2*X2
		String s1;
		int _currentIndex;
		String[] _elements;
		float _constantMultiplier;
		int _XsNumberCounter;
		ArrayList<Integer> _XsNumber;
		ArrayList<Float> _concFuncCollection3rdRow;
		ArrayList<ArrayList<Float>> _concFuncCollection2ndRow = new ArrayList<ArrayList<Float>>();

		
		try
		{
			s = s.replace('x', 'X');	// convert xs to Xs(UpperCase);
			s = s.replace(',', '.');	// replaces "," with "."
			s = s.replace('*', 'Z');	// replaces "," with "."
			if(s.indexOf("-")!=0) s="+" + s;




		

		
		//System.out.println("funciton " + i +" = "+ s);
		//System.out.println("length " + i +" = "+ s.length());
		//System.out.println("+ at " + i +" = "+ s.indexOf("+",1));
		
		int cycle=1;
		while(cycle==1)   //run through all elements in the equation; repeat until the string is over
		{
			
			_currentIndex=s.indexOf("-", 1);
			if (_currentIndex==-1)
				_currentIndex=s.indexOf("+", 1);
			if (_currentIndex==-1)
				_currentIndex=s.length();
			
			
			//System.out.println("indext " + i +" = "+ _currentIndex);
		
			if(_currentIndex==0) break;
			
			 s1=s.substring(0,_currentIndex);
			 s=s.substring(_currentIndex, s.length());
			
			 
			//System.out.println("s1 " + i +" = "+ s1);
			//System.out.println("s " + i +" = "+ s);

			 // first cut the sign off
			 _constantMultiplier=1;
			 
			 _elements = s1.split("Z");  // s1=="2*k1*X1*X2"
			 if(_elements[0].indexOf("-")==0)
				 _constantMultiplier=-1;
			 _elements[0]= _elements[0].substring(1,_elements[0].length());
			 
			 //for(int j=0; j<_elements.length; j++)
			 //{
			 //		System.out.println("element " + j +" = "+ _elements[j]);
			 //}
			 
			 
			 // Analize s1;

			 // after splitting "2", "k1", "X1", "X2"
			 // go through the _elements array, identify the content, and write into _functionElements
	
				 
			 //_XsNumberCounter=0;
			 _XsNumber = new  ArrayList<Integer>();
			 _concFuncCollection3rdRow = new  ArrayList<Float>();
	 
			 

				 for(int j=0; j<_elements.length; j++) // 4 cycles
				 {

					 if(_elements[j].contains("(0)"))
					 {
						 _XsNumber.add(Integer.parseInt(_elements[j].substring(1,_elements[j].length()-3))-1);
						 //System.out.println("constant number =" + _elements[j].substring(1,_elements[j].length()));
						 //System.out.println("constant value =" + _constantValues[Integer.parseInt(_elements[j].substring(1,_elements[j].length()))-1]);
						 //System.out.println("multiplier ="+ _constantMultiplier);
					 }				 

					 if((!_elements[j].contains("(0)")))
					 	 _constantMultiplier*=Float.parseFloat(_elements[j].substring(0,_elements[j].length())); // add to the constant multiplier some coefficient
				 }
				 
			 


				//System.out.println("total multiplier ="+ _constantMultiplier);
			 
			 
			 //result: _cM = -1*2*k1(value); _XsNumber={1, 2}
			 //now fill the 3rd row of the _concFuncCollection;
			 //first goes the constant multiplier;
			 _concFuncCollection3rdRow.add(_constantMultiplier);
			 //now add the number of Xs(their values change every itteration step)
			 for(int k=0; k<_XsNumber.size(); k++)
				 _concFuncCollection3rdRow.add(0f+_XsNumber.get(k));
			 // the result: get(0)==-1*2*k1; get(1)==1; get(2)==2
			 
			 
			//now add the 3rd row to the 2nd one
			 _concFuncCollection2ndRow.add(_concFuncCollection3rdRow);
			 


		} 
		
		}
		catch(NullPointerException e)
		{
			_result = i;
		}
		catch(NumberFormatException e)
		{
			 _result = i;
			 //throw new NumberFormatException();
			 //JOptionPane.showMessageDialog(this, "Check differntial equations!!!");
		}
		
		//when the 2nd row is filled add it to the 1st row, and then go to the next equation
		M._concFuncCollection.add(_concFuncCollection2ndRow);
		
		
		}	
		
		return _result;
	

		
	}
	
	
    public float getConcentrationFromDependence(int _functionNumber)
    {
		float _sumResult=-1f,_multipliedResult=-1f;
		
		// takes number of equaton, step, _concentrationValues, _constantValues, and ArrayList with coefficients for Xs;

			
		
			_sumResult = 0;
			if(M._concFuncCollection.size()!=0)
			for(int i=0; i<M._concFuncCollection.get(_functionNumber).size()/*i - number of summed elements in the equation(2nd row)*/; i++) //run through the 2nd row;
			{
				
				//System.out.println("func number: " + _functionNumber + " element number: " + i);
				
				_multipliedResult=1f;
				for(int j=0; j<M._concFuncCollection.get(_functionNumber).get(i).size()/*j - number of multiplied subelements in an element(3rd row)*/; j++)
				{
					if(j==0)
					{
						_multipliedResult*=M._concFuncCollection.get(_functionNumber).get(i).get(j); // multiply by the constant factor
						//System.out.println("Obtained muliplied result from constant: " + _multipliedResult);
					}
					
					
					if(j>0)
					{
						int _numberOfX = Math.round(M._concFuncCollection.get(_functionNumber).get(i).get(j));
						//System.out.println("Obtained number of X: " + _numberOfX);
						//System.out.println("Concentration value: " + _concentrationValues[_numberOfX]);
						//System.out.println("Obtained muliplied result: " + _multipliedResult);

						_multipliedResult =(float)_multipliedResult * (float)M._concentrationValues[_numberOfX]; // multiply by the value from concentrations' array;
						
						//System.out.println("Obtained muliplied result from concentration: " + _multipliedResult);
					}
				}
				//having finished multiplying add the result to the sum;
				_sumResult+=_multipliedResult;
			}
			//having finished summing return the sum result;
			
			System.out.println("Final: " + _sumResult);
			
			return _sumResult;

    }
	
	
	
	
	
	public void findIndependentConcentrations()
	{
		String name, temp;
		
		for(int i=0; i<M._nOfEqRel; i++)
		{
			name = "X"+(i+1)+"(0)";
			name = name.toLowerCase();
			temp = M._concEqLabelsContent[i].toLowerCase();
			
			//System.out.println("name = " + name + "  temp = " + temp);
			if(temp.equals(name))
			{
				M._variablesLimitsCollection.get(2).get(i)[3]=0;
			}
			else
			{
				M._variablesLimitsCollection.get(2).get(i)[3]=1;
			}
		}
		
		
		
		
		
	}
	
	
	
	
	
	public Float calcDerivFunctionValue(int _functionNumber, int _step, float[] k1, float[] k2, float[] k3, float[] k4)
	{
		float _sumResult,_multipliedResult;
		
		// takes number of equaton, step, _concentrationValues, _constantValues, and ArrayList with coefficients for Xs;

			
		
			_sumResult = 0;
			for(int i=0; i<M._funcCollection.get(_functionNumber).size()/*number of summed elements in the equation(2nd row)*/; i++) //run through the 2nd row;
			{
				
				//System.out.println("func number: " + _functionNumber + " element number: " + i);
				
				_multipliedResult=1;
				for(int j=0; j<M._funcCollection.get(_functionNumber).get(i).size()/*number of multiplied subelements in an element(3rd row)*/; j++)
				{
					if(j==0)
					{
						_multipliedResult*=M._funcCollection.get(_functionNumber).get(i).get(j); // multiply by the constant factor
						//System.out.println("Obtained muliplied result from constant: " + _multipliedResult);
					}
					
					if(j==1)
					{
						int _numberOfK = Math.round(M._funcCollection.get(_functionNumber).get(i).get(j));
						if(_numberOfK!=-1)
						{
							_multipliedResult*=M._constantValues[_numberOfK]; // multiply by the value of a constast
						}

					}
					
					if(j>1)
					{
						int _numberOfX = Math.round(M._funcCollection.get(_functionNumber).get(i).get(j));
						//System.out.println("Obtained number of X: " + _numberOfX);
						//System.out.println("Concentration value: " + _concentrationValues[_numberOfX]);
						
						if(_step==1)
						{
							_multipliedResult*=M._concentrationValues[_numberOfX]; // multiply by the value from concentrations' array;
							//System.out.println("Multiplied result at step 1 " + _multipliedResult);
						}

						if(_step==2)
						{
							_multipliedResult*=(M._concentrationValues[_numberOfX]+0.5f*k1[_numberOfX]); // multiply by the value from concentrations' array adding k1 for this X;
							//System.out.println("Multiplied result at step 2 " + _multipliedResult);
						}
						if(_step==3)
						{
							_multipliedResult*=(M._concentrationValues[_numberOfX]+0.5f*k2[_numberOfX]); // multiply by the value from concentrations' array adding k2 for this X;
							//System.out.println("Multiplied result at step 3 " + _multipliedResult);
						}
						if(_step==4)
						{
							_multipliedResult*=(M._concentrationValues[_numberOfX]+k3[_numberOfX]); // multiply by the value from concentrations' array adding k3 for this X;
							//System.out.println("Multiplied result at step 4 " + _multipliedResult);
						}
					
					}
				}
				//having finished multiplying add the result to the sum;
				_sumResult+=_multipliedResult;
			}
			//having finished summing return the sum result;
			
			return _sumResult;
	
	}
	
	
	
	public void fitRK4()//boolean ifRunForTheFirstTime)
	{
		
		//synchronized(_syncObject)
		//{
			M._ifSolvedCurveCollectionLocked = true;
			
			
			float _step = 1E-8f;
			float _requiredStep=0;
			float _time = 0f, _deviation=0;
			int _nOfSteps = 100; // obtained from curves
			float[] k1= new float[10], k2= new float[10], k3= new float[10], k4 = new float[10];
			float[] _delta = new float[10]; // delta for each X
			ArrayList<Float> _tempArrayY;
			ArrayList<Float> _tempErrorArrayY;
			ArrayList<Float> _tempConcArrayY;
			float _sumOpticalDensity;
			int _cycleCounter = 0, _neededCycleCounter = 0;
			
			
			
			M._yMinError=1000;
			M._yMaxError=-1000;


			for(int i=0; i<M._nOfEq; i++)
			{
				M._concentrationValuesIni[i] = M._concentrationValues[i];
			}
			
			
			//if(ifRunForTheFirstTime)
			M._posToStartFittingWith = findClosestPosOfZeroLine();
			
			_requiredStep = (M._kinCollectionX.get(0).get(2)-M._kinCollectionX.get(0).get(1))/M._solvedCurveTimeScale/M._fitRK4Accuracy;
			
			_step = _requiredStep;
			
			
			
			
			
			M._solvedCurveCollectionY.clear();
			M._solvedCurveContributionCollectionY.clear();
			M._solvedCurveErrorCollectionY.clear();
			M._solvedCurveCollectionX.clear();
			M._solvedConcentrationCollectionY.clear();
			
			
			
			//X
			for(int j=0; j<(M._posToStartFittingWith); j++)
			{
				M._solvedCurveCollectionX.add(M._kinCollectionX.get(0).get(j));
			}
			M._solvedCurveCollectionX.add(M._kinCollectionX.get(0).get(M._posToStartFittingWith));
	
			
			//Y
			for(int i=0; i<M._availWaveCollection.size(); i++)
			{
				_tempArrayY = new ArrayList<Float>();
				_tempErrorArrayY = new ArrayList<Float>();
				
				
				for(int j=0; j<(M._posToStartFittingWith); j++)
				{
					if(M._ifCalcCurveShifts)
					{
						_tempArrayY.add(-M._curveShiftCollection.get(i));
						_tempErrorArrayY.add(-M._curveShiftCollection.get(i));
					}
					else
					{
						_tempArrayY.add(-M._levelPosDisplay);
						_tempErrorArrayY.add(-M._levelPosDisplay);
					}

				}
					
				_sumOpticalDensity = 0;
				//calc sum optical density at each wavelength
				for(int k=0; k<M._nOfEq; k++)
				{
					_sumOpticalDensity+=  M._concentrationValues[k]*M._xSAbsSpecCollectionYFiltered.get(k).get(i)-M._beforePulseConcentrationValues[k]*M._xSAbsSpecCollectionYFiltered.get(k).get(i);
				}
				
				if(M._ifCalcCurveShifts)
				{
					_sumOpticalDensity = (_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness;
				}
				else
					_sumOpticalDensity = (_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness;
				
				
				_tempArrayY.add(_sumOpticalDensity);
				
				
				
				_deviation = (M._kinCollectionY.get(i).get(M._posToStartFittingWith) - _sumOpticalDensity);
				if(M._yMaxError<_deviation){M._yMaxError=_deviation;}
				if(M._yMinError>_deviation){M._yMinError=_deviation;}
				
				
				_tempErrorArrayY.add(_deviation);
				M._solvedCurveCollectionY.add(_tempArrayY);
				M._solvedCurveErrorCollectionY.add(_tempErrorArrayY);

			}

			for(int i=0; i<M._nOfEq; i++)
			{
				_tempConcArrayY = new ArrayList<Float>();
				
				for(int j=0; j<(M._posToStartFittingWith); j++)
				{
					_tempConcArrayY.add(0f);
				}
				_tempConcArrayY.add(M._concentrationValues[i]);
				M._solvedConcentrationCollectionY.add(_tempConcArrayY);
			}
				

			_nOfSteps=(M._kinCollectionX.get(0).size()-M._posToStartFittingWith-1)*M._fitRK4Accuracy;
			
			
			_cycleCounter = M._fitRK4Accuracy;
			_neededCycleCounter = 0;
			for(int j=0; j<_nOfSteps; j++)
			{
				for(int i=0; i<M._nOfEq; i++)
				{
					k1[i]=_step*calcDerivFunctionValue(i,1,k1,k2,k3,k4);
				}
				for(int i=0; i<M._nOfEq; i++)
				{
					k2[i]=_step*calcDerivFunctionValue(i,2,k1,k2,k3,k4);
				}
				for(int i=0; i<M._nOfEq; i++)
				{
					k3[i]=_step*calcDerivFunctionValue(i,3,k1,k2,k3,k4);
				}
				for(int i=0; i<M._nOfEq; i++)
				{
					k4[i]=_step*calcDerivFunctionValue(i,4,k1,k2,k3,k4);
				}
				for(int i=0; i<M._nOfEq; i++)
				{
					_delta[i]=(k1[i]+2*k2[i]+2*k3[i]+k4[i])/6;
				}
					
					
					
					
					
					
					
				
				

				
				
				
				for(int i=0; i<M._nOfEq; i++)
				{
					M._concentrationValues[i]=M._concentrationValues[i]+_delta[i];
				}			
				
				
				
				
				_cycleCounter++;
				if(_cycleCounter>=M._fitRK4Accuracy)
					_cycleCounter=0;
				

				
				if(_cycleCounter==0)
				{


					
					for(int i=0; i<M._nOfEq; i++)
					{
						M._solvedConcentrationCollectionY.get(i).add(M._concentrationValues[i]);
					}
					
					
					

					
					
					M._solvedCurveCollectionX.add(M._kinCollectionX.get(0).get(M._posToStartFittingWith+_neededCycleCounter+1));
					for(int i=0; i<M._availWaveCollection.size(); i++)
					{
						_sumOpticalDensity = 0;
						//calc sum optical density at each wavelength
						for(int k=0; k<M._nOfEq; k++)
						{
							_sumOpticalDensity+=  (M._concentrationValues[k]-M._beforePulseConcentrationValues[k])*M._xSAbsSpecCollectionYFiltered.get(k).get(i);
						
						}
						if(M._ifCalcCurveShifts)
						{
							M._solvedCurveCollectionY.get(i).add((_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness);
						}
						else
						{
							M._solvedCurveCollectionY.get(i).add((_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness);
						}
						_deviation = (M._kinCollectionY.get(i).get(M._posToStartFittingWith+_neededCycleCounter+1) - M._solvedCurveCollectionY.get(i).get(M._posToStartFittingWith+_neededCycleCounter+1));
						M._solvedCurveErrorCollectionY.get(i).add(_deviation);
						
	
						if(M._yMaxError<_deviation){M._yMaxError=_deviation;}
						if(M._yMinError>_deviation){M._yMinError=_deviation;}

					}
					
					_neededCycleCounter++;

				}
				
				
				
				
			}
			
			float a = Math.max(Math.abs(M._yMaxError), Math.abs(M._yMinError));
			M._yScalerError = M._gridSizeYError/(a)/2;
			
			for(int i=0; i<M._nOfEq; i++)
			{
				M._concentrationValues[i] = M._concentrationValuesIni[i];
			}

			
			//System.out.println("_solvedConcentrationCollectionY " + _solvedConcentrationCollectionY.get(0).size());
			//System.out.println("_solvedCurveErrorCollectionY " + _solvedCurveErrorCollectionY.get(0).size());
			
			
			M._ifSolvedCurveCollectionLocked = false;
			
		//}
		
	
	}
	
	
	
	
	public int findClosestPosOfZeroLine()
	{
		int _result = -1;
		float _interval = (M._kinCollectionX.get(0).get(5)-M._kinCollectionX.get(0).get(4))/2;
		
		for(int i=1; i<(M._kinCollectionX.get(0).size()-1);i++)
		{
			if((M._zeroPosDisplay>=(M._kinCollectionX.get(0).get(i)-_interval))&(M._zeroPosDisplay<(M._kinCollectionX.get(0).get(i+1)-_interval)))
			{
				_result = i;
			}
		}
		
		
		//System.out.println("Pos of closest = " + _result);
		//System.out.println("Value = " + _kinCollectionY.get(0).get(_result));
		return _result;
	}
	
	
	
	public void recalcOpticalDensityCollections()
	{
		float _sumOpticalDensity;
		ArrayList<Float> _tempArrayY;
		
		M._solvedCurveCollectionY.clear();
		
		for(int i=0; i<M._availWaveCollection.size(); i++)
		{
			_tempArrayY = new ArrayList<Float>();
			
			
			for(int j=0; j<(M._posToStartFittingWith); j++)
			{
				if(M._ifCalcCurveShifts)
				{
					_tempArrayY.add(-M._curveShiftCollection.get(i));
				}
				else
				{
					_tempArrayY.add(-M._levelPosDisplay);
				}

			}
			

			
			_sumOpticalDensity = 0;
			//calc sum optical density at each wavelength
			for(int k=0; k<M._nOfEq; k++)
			{
				_sumOpticalDensity+=  M._concentrationValues[k]*M._xSAbsSpecCollectionYFiltered.get(k).get(i)-M._beforePulseConcentrationValues[k]*M._xSAbsSpecCollectionYFiltered.get(k).get(i);
			}
			
			if(M._ifCalcCurveShifts)
			{
				_sumOpticalDensity = (_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness;
			}
			else
				_sumOpticalDensity = (_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness;
			
			
			_tempArrayY.add(_sumOpticalDensity);
			

			M._solvedCurveCollectionY.add(_tempArrayY);

		}
		
		for(int j=(M._posToStartFittingWith+1); j<M._kinCollectionX.get(0).size(); j++)
		{
			for(int i=0; i<M._availWaveCollection.size(); i++)
			{
				_sumOpticalDensity = 0;
				//calc sum optical density at each wavelength
				for(int k=0; k<M._nOfEq; k++)
				{
					_sumOpticalDensity+=  (M._solvedConcentrationCollectionY.get(k).get(j)-M._beforePulseConcentrationValues[k])*M._xSAbsSpecCollectionYFiltered.get(k).get(i);
				
				}
				if(M._ifCalcCurveShifts)
				{
					M._solvedCurveCollectionY.get(i).add((_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness);
				}
				else
				{
					M._solvedCurveCollectionY.get(i).add((_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness);
				}


			}
		}

	}
	
	
	
	public void fillContributionArrays()
	{
		ArrayList<ArrayList<Float>> _tempSuperArrayY;
		ArrayList<Float> _tempArrayY;
		float _sumOpticalDensity;
		int _nOfSteps = 0;
		
		M._solvedCurveContributionCollectionY.clear();
		// prepare intitial part
		for(int m=0; m<M._nOfEq; m++)
		{
			_tempSuperArrayY = new ArrayList<ArrayList<Float>>();
			M._solvedCurveContributionCollectionY.add(_tempSuperArrayY);
			
			for(int i=0; i<M._availWaveCollection.size(); i++)
			{
				_tempArrayY = new ArrayList<Float>();
				
				for(int j=0; j<(M._posToStartFittingWith); j++)
				{
					if(M._ifCalcCurveShifts)
					{
						_tempArrayY.add(-M._curveShiftCollection.get(i));
					}
					else
					{
						_tempArrayY.add(-M._levelPosDisplay);
					}

				}
					

				//calc sum optical density at each wavelength
				_sumOpticalDensity =  M._concentrationValues[m]*M._xSAbsSpecCollectionYFiltered.get(m).get(i)-M._beforePulseConcentrationValues[m]*M._xSAbsSpecCollectionYFiltered.get(m).get(i);
				
				if(M._ifCalcCurveShifts)
				{
					_sumOpticalDensity = (_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness;
				}
				else
					_sumOpticalDensity = (_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness;
	
				_tempArrayY.add(_sumOpticalDensity);
				M._solvedCurveContributionCollectionY.get(m).add(_tempArrayY);


			}
			
			//_nOfSteps=_kinCollectionX.get(0).size()-_posToStartFittingWith-1;
			
			for(int j=(M._posToStartFittingWith+1); j<M._kinCollectionX.get(0).size(); j++)
			{
				for(int i=0; i<M._availWaveCollection.size(); i++)
				{
					//calc sum optical density at each wavelength
					_sumOpticalDensity =  (M._solvedConcentrationCollectionY.get(m).get(j)-M._beforePulseConcentrationValues[m])*M._xSAbsSpecCollectionYFiltered.get(m).get(i);

					if(M._ifCalcCurveShifts)
					{
						M._solvedCurveContributionCollectionY.get(m).get(i).add((_sumOpticalDensity-M._curveShiftCollection.get(i))*M._cuvetteThickness);
					}
					else
					{
						M._solvedCurveContributionCollectionY.get(m).get(i).add((_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness);
					}
				}
			}
			
		}
	}
	
	
	/*
	
	public void recalcConcentrationsToOpticalDensity()
	{
		float _sumOpticalDensity;
		
		for(int i=0; i<M._availWaveCollection.size(); i++)
		{
			for(int j=0; j<M._solvedCurveCollectionX.size(); j++)
			{
				_sumOpticalDensity = 0;
				for(int k=0; k<M._nOfEq; k++)
				{
					_sumOpticalDensity+= M._solvedConcentrationCollectionY.get(k).get(j)*M._xSAbsSpecCollectionYFiltered.get(k).get(i);
				}
				M._solvedCurveCollectionY.get(i).add(_sumOpticalDensity*M._cuvetteThickness);
			}

			
		}

		
	}
	
	*/
	

	
	
	
	
	
	
	
	public void calcWeightsForCurves()
	{
		int _tailLength=0;
		float _meanValue=0;
		float _meanDeviation=0;
		float _minValue=1e9f;
		
		M._weightCollection = new ArrayList<Float>();
		_tailLength = Math.round(M._kinCollectionX.get(0).size()/10);
		
		//System.out.println("tail length = " + _tailLength);
		
		for(int i=0; i<M._availWaveCollection.size(); i++)
		{
			_meanValue=0;
			for(int j=1; j<_tailLength; j++)
			{
				_meanValue+=M._kinCollectionY.get(i).get(M._kinCollectionX.get(i).size()-j);
			}
			_meanValue = _meanValue/(_tailLength-1);
			
			_meanDeviation=0;
			for(int j=1; j<_tailLength; j++)
			{
				_meanDeviation+=Math.abs(M._kinCollectionY.get(i).get(M._kinCollectionX.get(i).size()-j)-_meanValue);
			}
			_meanDeviation = _meanDeviation/(_tailLength-1);
			M._weightCollection.add(_meanDeviation);
			if(_minValue>_meanDeviation){_minValue=_meanDeviation;}
		}

		for(int i=0; i<M._weightCollection.size(); i++)
		{
			_meanDeviation = _minValue/M._weightCollection.get(i);
			M._weightCollection.set(i, _meanDeviation);
			//System.out.println("w = " + _availWaveCollection.get(i) + " d = " + _meanDeviation);

		}
		
	}
	
	
	public int findClosestPosOfBeforeZeroLine()
	{
		int _result = -1;
		for(int i=1; i<(M._kinCollectionX.get(0).size()-1);i++)
		{
			if((M._beforeZeroPosDisplay>=M._kinCollectionX.get(0).get(i))&(M._beforeZeroPosDisplay<M._kinCollectionX.get(0).get(i+1)))
			{
				_result = i;
			}
		}
		return _result;
	}
	
	
	public void calcCurveShifts()
	{
		int _pos = findClosestPosOfBeforeZeroLine();
		float _meanValue=0;
		
		M._curveShiftCollection = new ArrayList<Float>();
		
		
		
		for(int i=0; i<M._availWaveCollection.size(); i++)
		{
			
			_meanValue=0;
			for(int j=1; j<_pos; j++)
			{
				_meanValue+=M._kinCollectionY.get(i).get(j);
			}
			_meanValue = _meanValue/(_pos-1);
			
			
			M._curveShiftCollection.add(-_meanValue);
			//System.out.println(_availWaveCollection.get(i) + "  " + _meanValue);
		}
		


		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
