package math_packadge;

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


public class GlobalFitDirect {

	GlobalFitMath M;
	ArrayList<GlobalFitDirectRectangle> _rectCollection = new ArrayList<GlobalFitDirectRectangle>();
	public int _nOfIterationsPerformed=0;
	int _nOfConst;
	int _nOfEq;
	float[] _constantValues;
	ArrayList<ArrayList<float[]>> _variablesLimitsCollection;
	public int _nOfAlterableValues=0;
	ArrayList<Float> _scaleCollection = new ArrayList<Float>();
	ArrayList<Float> _shiftCollection = new ArrayList<Float>();
	public float _minSigma = 1e9f, _globalSigma = 0;
	public int _smallestRectIndexFinal = -1;
	ArrayList<Float> _distCollection = new ArrayList<Float>();
	ArrayList<Integer> _optimalIndexCollection;
	ArrayList<GlobalFitDirectRectangle> _tempRectStorage;
	ArrayList<Integer> _availDimentions;
	ArrayList<float[]> _surroundingFuncValCollection;
	ArrayList<float[]> _surroundingCentersCollection;
	public float _epsilon=1e-5f;
	public boolean _isBusy = false;
	

	
	public GlobalFitDirect(GlobalFitMath M)
	{
		this.M =M;
	}
	
	public void transferLinks()
	{
		_nOfConst = M.get_nOfConst();
		_nOfEq = M.get_nOfEq();
		_constantValues = M.get_constantValues();
		_variablesLimitsCollection = M.get_variablesLimitsCollection();
	}
	
	
	public void start()
	{
		System.out.println("Initializing DIRect algorithm.");
		transferLinks();
		clearAllDataArrays();
		calcNOfDimentions();
		_nOfIterationsPerformed = 0;
		_minSigma = 1e9f;
		_rectCollection.add(new GlobalFitDirectRectangle(getNOfDimentions(), M));
		setInitialIntervalsForParameters();
		calcFunctionValueForRect(0);
		_rectCollection.get(0).set_isNew(false);
		_rectCollection.get(0).calcDistance();
		_rectCollection.get(0).checkSides();
		//_rectCollection.get(0).printOutInformation();
		_isBusy = true;
		
	}

	public void iteration()
	{
		_nOfIterationsPerformed++;
		System.out.println("*************************Performing iteration " + _nOfIterationsPerformed);
		identifyPotentiallyOptimalUsingK();
		divideOptimalRectangles();
		//System.out.println("N of rectangles = " + _rectCollection.size());
		obtainBestValues();
		
	}
	
	
	public void finish()
	{
		//obtainBestValues();
		//this.calcBestAbsCoeff(30);
		//M.fitRK4();
		_isBusy = false;
	}
	
	
	
	
	public void obtainBestValues()
	{
		float _minFunc=1e9f;
		int _smallestRectIndex=-1;
		
		for(int i=0; i<_rectCollection.size(); i++)
		{
			if(_minFunc>_rectCollection.get(i).get_functionValue())
			{
				_minFunc = _rectCollection.get(i).get_functionValue();
				_smallestRectIndex = i;
			}
		}
		//System.out.println("Smallest = " + _rectCollection.get(_smallestRectIndex).get_functionValue());
		//System.out.println("k1 = " + this.convertBackToRealValues(_smallestRectIndex, 0, 0));
		//System.out.println("k2 = " + this.convertBackToRealValues(_smallestRectIndex, 1, 0));
		
		int _counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_constantValues[i]=convertBackToRealValues(_smallestRectIndex, _counter, 0);
				_counter++;
			}
		}
		M.fitRK4();
		this.calcBestAbsCoeff(30, true);
		//System.out.println("Best abs coeff calculated.");
		//M.fitRK4();
		
	}
	
	
	public void clearAllDataArrays()
	{
		_rectCollection.clear();
		_scaleCollection.clear();
		_shiftCollection.clear();
		_distCollection.clear();
	}
	
	public void identifyPotentiallyOptimal()
	{
		sortToGroups();
		_optimalIndexCollection = new ArrayList<Integer>();
		float[] _eachGroupMinFunc = new float[_distCollection.size()];
		int[] _eachGroupBestIndex = new int[_distCollection.size()];
		int _groupIndex;
		
		for(int i=0; i<_distCollection.size(); i++)
			_eachGroupMinFunc[i]=1e10f;
		
		for(int i=0; i<_rectCollection.size(); i++)
		{
			_groupIndex = _rectCollection.get(i).getGroupIndex();
			
			if(_eachGroupMinFunc[_groupIndex]>_rectCollection.get(i).get_functionValue())
			{
				_eachGroupMinFunc[_groupIndex]=_rectCollection.get(i).get_functionValue();
				_eachGroupBestIndex[_groupIndex]=i;
			}
			
		}
		//System.out.println("Optimal rectangles' indexes:");
		for(int i=0; i<_distCollection.size(); i++)
		{
			if(_eachGroupMinFunc[i]!=1e10f)
			{
				_optimalIndexCollection.add(_eachGroupBestIndex[i]);
				//System.out.println(_eachGroupBestIndex[i]);
			}
		}
	}
	
	
	public void identifyPotentiallyOptimalUsingK()
	{
		float _testFunc;
		float fe = _minSigma*(1f-_epsilon);
		boolean _optimal = true;
		_optimalIndexCollection = new ArrayList<Integer>();
		
		for(int i=0; i<_rectCollection.size(); i++)
		{
			_rectCollection.get(i).calcK(_epsilon, _minSigma);
		}
		
		for(int i=0; i<_rectCollection.size(); i++)
		{
			_optimal = true;
			for(int j=0; j<_rectCollection.size(); j++)
			{
				if(i!=j && _rectCollection.get(i).getDistance()<= _rectCollection.get(j).getDistance())
				{
					_testFunc = _rectCollection.get(i).get_K()*_rectCollection.get(j).getDistance() + fe;
					if(_testFunc>_rectCollection.get(j).get_functionValue())
						_optimal = false;
				}
			}
			if(_optimal)
				_optimalIndexCollection.add(i);
		}
	}
	
	
	public void sortToGroups()
	{
		//System.out.println("Sorting to groups.");
		boolean _foundGroup;
		
		for(int i=0; i<_rectCollection.size(); i++)
		{
			if(!_rectCollection.get(i).isSorted())
			{
				if(_distCollection.size()!=0)
				{
					_foundGroup = false;
					for(int j=0; j<_distCollection.size(); j++)
					{
						if(_rectCollection.get(i).compareValues(_rectCollection.get(i).getDistance(), _distCollection.get(j), 0.01f))
						{
							_rectCollection.get(i).setGroupIndex(j);
							_rectCollection.get(i).set_isSorted(true);
							_foundGroup = true;
							break;
						}
					}
					if(!_foundGroup)
					{
						_distCollection.add(_rectCollection.get(i).getDistance());
						_rectCollection.get(i).setGroupIndex(_distCollection.size()-1);
						_rectCollection.get(i).set_isSorted(true);
					}
				}
				else
				{
					_distCollection.add(_rectCollection.get(i).getDistance());
					_rectCollection.get(i).setGroupIndex(0);
					_rectCollection.get(i).set_isSorted(true);
				}
			}

			//System.out.println("_distCollection.size() = " + _distCollection.size());
			//_rectCollection.get(i).printOutInformation();
		}
	}
	
	
	
	public void divideOptimalRectangles()
	{
		//System.out.println("divideOptimalRectangles. Partition.");
		
		_tempRectStorage = new ArrayList<GlobalFitDirectRectangle>();
		
		for(int i=0; i<_optimalIndexCollection.size(); i++)
		{
			divideRectangle(i);
		}
		
		addNewRectanglesToHeap();
	}
	
	public void addNewRectanglesToHeap()
	{
		for(int i=0; i<_tempRectStorage.size(); i++)
		{
			//GlobalFitDirectRectangle _rect = _tempRectStorage.get(i);
			//_tempRectStorage.remove(i);
			_rectCollection.add(_tempRectStorage.get(i));
		}
		_tempRectStorage.clear();
		//System.out.println("size = " + _rectCollection.size());
	}
	
	
	public void divideRectangle(int _indexInOptimalCollection)
	{
		int _rectIndex = _optimalIndexCollection.get(_indexInOptimalCollection);
		//System.out.println("dividing rectangle with optimal index: " + _rectIndex);
		//System.out.println("this rect is a cube: " + _rectCollection.get(_rectIndex).isCube());
		// _rectCollection.get(_rectIndex).printOutInformation();
		
		
		trySurroundings(_rectIndex);
		sortDimentions();
		createNewRectangles(_rectIndex);
	}
	
	
	public void trySurroundings(int _rectIndex)
	{
		//System.out.println("Trying surroundings.");

		
		//if(_rectCollection.get(_rectIndex).isExploredAround())
		//{
		//	_availDimentions = _rectCollection.get(_rectIndex).getEarlierExploredAvailDimentions();
		//	_surroundingFuncValCollection = _rectCollection.get(_rectIndex).getEarlierExploredFuncValueCollection();
		//	_surroundingCentersCollection = _rectCollection.get(_rectIndex).getEarlierExploredCentersCollection();
		//}
		//else
		{
			_availDimentions = new ArrayList<Integer>();
			_surroundingFuncValCollection = new ArrayList<float[]>();
			_surroundingCentersCollection = new ArrayList<float[]>();
			
			for(int j=0; j<this.getNOfDimentions(); j++)
			{
				if(_rectCollection.get(_rectIndex).ifSideCanBeReduced(j))
				{
					_availDimentions.add(j);
					float[] _temp = new float[2];
					_temp[0] = calcFunctionValueForRectDelta(_rectIndex, j, -1);
					_temp[1] = calcFunctionValueForRectDelta(_rectIndex, j,  1);
					_surroundingFuncValCollection.add(_temp);
					
					_temp = new float[2];
					_temp[0] = calcNewCenter(_rectIndex, j, -1);
					_temp[1] = calcNewCenter(_rectIndex, j,  1);
					_surroundingCentersCollection.add(_temp);
				}
				else
				{
					float[] _temp = new float[2];
					_temp[0] = 1e10f;
					_temp[1] = 1e10f;
					_surroundingFuncValCollection.add(_temp);
					
					_temp = new float[2];
					_temp[0] = _rectCollection.get(_rectIndex).get_intervalCenter(j);
					_temp[1] = _temp[0];
					_surroundingCentersCollection.add(_temp);
				}

			}
			
			//_rectCollection.get(_rectIndex).set_surroundingFuncValCollection(_surroundingFuncValCollection);
			//_rectCollection.get(_rectIndex).set_surroundingCentersCollection(_surroundingCentersCollection);
			//_rectCollection.get(_rectIndex).set_availDimentions(_availDimentions);
			//_rectCollection.get(_rectIndex).set_isExploredAround(true);
		}
		
	}
	
	
	public void sortDimentions()
	{
		//System.out.println("Sorting available dimentions.");
		
		float _minFunction = 1e10f;
		int _bestDimentionIndex = -1;
		float _funcDelta=0;
		ArrayList<Integer> _tempArray = new ArrayList<Integer>();
		
		while(_availDimentions.size()!=0)
		{
			_minFunction = 1e10f;
			
			//System.out.println("_availDimentions.size() "+_availDimentions.size());
			
			for(int j=0; j<_availDimentions.size(); j++)
			{
				for(int k=0; k<2; k++)
				{
					_funcDelta = _surroundingFuncValCollection.get(_availDimentions.get(j))[k];
					//System.out.println("Dimention: " + _availDimentions.get(j) +". Position: " + (-1+2*k) +". _funcDelta "+_funcDelta);
					if(_minFunction>_funcDelta)
					{
						_minFunction=_funcDelta;
						_bestDimentionIndex = j;
						//System.out.println("Best index: "+_bestDimentionIndex);
					}
				}
			}
			
			_tempArray.add(_availDimentions.get(_bestDimentionIndex));
			//System.out.println("best dimention: " + _availDimentions.get(_bestDimentionIndex));
			_availDimentions.remove(_bestDimentionIndex);

		}
		
		_availDimentions = _tempArray;
		
	}
	
	
	
	public void createNewRectangles(int _rectIndex)
	{
		float _parentLongSide = _rectCollection.get(_rectIndex).getLongSideLength();
		float _parentShortSide = _rectCollection.get(_rectIndex).getShortSideLength();
		float _reducedSide = _parentLongSide/3f;
		int _currentDimention = -1;
		float ai, bi, ci;
		int _dimentionNeeded=0;
		//System.out.println("Performing division and creating new rectangles.");
		//System.out.println("_parentLongSide " + _parentLongSide);
		//System.out.println("_parentShortSide " + _parentShortSide);
		ArrayList<Integer> _takenDimentions;
		boolean _taken;
		
		//creating surrounding rectangles
		
		for(int k=0; k<_availDimentions.size(); k++)
		{
			_currentDimention = _availDimentions.get(k);
			
			for(int _index=0; _index<2; _index++)
			{
				GlobalFitDirectRectangle _newRect = new GlobalFitDirectRectangle(this.getNOfDimentions(), M);
				_newRect.setFunctionValAtCenter(_surroundingFuncValCollection.get(_currentDimention)[_index]);
				
				_takenDimentions = new ArrayList<Integer>();
				
				for(int m=0; m<this.getNOfDimentions();m++)
				{
					// N of divisions needed = k+1.
					if(m<_availDimentions.size())
					{
						_dimentionNeeded = _availDimentions.get(m);
						_takenDimentions.add(_dimentionNeeded);
					}
					else
					{
						for(int n=0; n<this.getNOfDimentions();n++)
						{
							_taken = false;
							for(int p=0; p<_takenDimentions.size(); p++)
							{
								if(n==_takenDimentions.get(p))
								{
									_taken = true;
									break;
								}
							}
							if(!_taken)
							{
								_dimentionNeeded = n;
								_takenDimentions.add(_dimentionNeeded);
								break;
							}
								
						}
						
					}
						

					if(m<=k) //reduce side at 1/3
					{
						if(_dimentionNeeded==_currentDimention)
						{
							ci = _surroundingCentersCollection.get(_currentDimention)[_index];
						}
						else
						{
							ci = _rectCollection.get(_rectIndex).getCenterValue(_dimentionNeeded);
						}
						ai = ci - _reducedSide/2f;
						bi = ci + _reducedSide/2f;
						_newRect.set_interval(_dimentionNeeded, ai, bi, ci);
					}
					else // leave it initial
					{
						if(_dimentionNeeded==_currentDimention)
						{
							ci = _surroundingCentersCollection.get(_currentDimention)[_index];
						}
						else
						{
							ci = _rectCollection.get(_rectIndex).getCenterValue(_dimentionNeeded);
						}
						
						if(_rectCollection.get(_rectIndex).ifSideCanBeReduced(_dimentionNeeded))
						{
							ai = ci - _parentLongSide/2f;
							bi = ci + _parentLongSide/2f;
							_newRect.set_interval(_dimentionNeeded, ai, bi, ci);
						}
						else
						{
							ai = ci - _parentShortSide/2f;
							bi = ci + _parentShortSide/2f;
							_newRect.set_interval(_dimentionNeeded, ai, bi, ci);
						}

					}
				}
				
				_newRect.calcDistance();
				_newRect.set_isNew(false);
				_newRect.set_isSorted(false);
				_newRect.checkSides();
				//System.out.println("Creating new rect.");
				//System.out.println("FuncVal = " + _newRect.get_functionValue());
				//System.out.println("k1 = " + this.convertBackToRealValues(_newRect, 0, 0));
				//System.out.println("k2 = " + this.convertBackToRealValues(_newRect, 1, 0));

				//System.out.println("Short side =  " + _newRect.getShortSideLength() + ". Long side = " + _newRect.getLongSideLength() + ". Is cube: " + _newRect.isCube());
				//_newRect.printOutInformation();
				_tempRectStorage.add(_newRect);
			}
			

			
		}
		
		
		//creating the central rectangle
		
		
		GlobalFitDirectRectangle _newRect = new GlobalFitDirectRectangle(this.getNOfDimentions(), M);
		_newRect.setFunctionValAtCenter(_rectCollection.get(_rectIndex).get_functionValue());
		
		for(int m=0; m<this.getNOfDimentions();m++)
		{
			//reduce all available sides at 1/3

			ci = _rectCollection.get(_rectIndex).getCenterValue(m);
			ai = ci - _reducedSide/2f;
			bi = ci + _reducedSide/2f;
			_newRect.set_interval(m, ai, bi, ci);
		}
		
		_newRect.calcDistance();
		_newRect.set_isNew(false);
		_newRect.set_isSorted(false);
		_newRect.checkSides();
		//System.out.println("Central rectangle");
		//System.out.println("Short side =  " + _newRect.getShortSideLength() + ". Long side = " + _newRect.getLongSideLength() + ". Is cube: " + _newRect.isCube());
		//_newRect.printOutInformation();
		//System.out.println("Creating new rect.");
		//System.out.println("FuncVal = " + _newRect.get_functionValue());
		//System.out.println("k1 = " + this.convertBackToRealValues(_newRect, 0, 0));
		//System.out.println("k2 = " + this.convertBackToRealValues(_newRect, 1, 0));

		_rectCollection.set(_rectIndex, _newRect);
	}
	
	
	
	
	
	

	
	public void calcFunctionForNewRects()
	{
		for(int i=0; i<_rectCollection.size(); i++)
		{
			if(_rectCollection.get(i).isNew())
			{
				calcFunctionValueForRect(i);
			}
		}
	}
	
	public void calcNOfDimentions()
	{
		_nOfAlterableValues = 0;

		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_nOfAlterableValues++;
			}
		}
	}
	
	public final int getNOfDimentions()
	{
		return _nOfAlterableValues;
	}
	
	public void calcFunctionValueForRect(int _indexOfRect)  //positions 0 - center; +1 - c+delta; -1 - c-delta
	{
		int _counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				_constantValues[i]=convertBackToRealValues(_indexOfRect, _counter, 0);
				//System.out.println("Converting constants back. Dimention "+ _counter + " = " + _constantValues[i]);
				_counter++;
			}
		}
		M.fitRK4();
		this.calcBestAbsCoeff(20, false);
		_globalSigma = M.calcGlobalSigma();
		//System.out.println("Function value = "+ _globalSigma);

		if(_minSigma>_globalSigma)
			_minSigma = _globalSigma;
		_rectCollection.get(_indexOfRect).setFunctionValAtCenter(_globalSigma);
	}
	
	
	
	
	public float convertBackToRealValues(int _indexOfRect, int _dimention, int _centerPosition)
	{
		return calcNewCenter(_indexOfRect, _dimention, _centerPosition)/_scaleCollection.get(_dimention)+_shiftCollection.get(_dimention);
	}
	
	public float calcNewCenter(int _indexOfRect, int _dimention, int _centerPosition)
	{
		return _rectCollection.get(_indexOfRect).get_intervalCenter(_dimention)+_centerPosition*_rectCollection.get(_indexOfRect).getOneThird(_dimention);
	}
	
	
	public float convertBackToRealValues(GlobalFitDirectRectangle _rect, int _dimention, int _centerPosition)
	{
		return calcNewCenter(_rect, _dimention, _centerPosition)/_scaleCollection.get(_dimention)+_shiftCollection.get(_dimention);
	}
	
	public float calcNewCenter(GlobalFitDirectRectangle _rect, int _dimention, int _centerPosition)
	{
		return _rect.get_intervalCenter(_dimention)+_centerPosition*_rect.getOneThird(_dimention);
	}
	
	
	
	public float calcFunctionValueForRectDelta(int _indexOfRect, int _dimention, int _centerPosition)  //positions 0 - center; +1 - c+delta; -1 - c-delta
	{
		int _counter=0;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				if(_counter == _dimention)
				{
					_constantValues[i]=convertBackToRealValues(_indexOfRect, _counter, _centerPosition);
				}
				else
				{
					_constantValues[i]=convertBackToRealValues(_indexOfRect, _counter, 0);
				}
				_counter++;
			}
		}
		M.fitRK4();
		this.calcBestAbsCoeff(20, false);
		_globalSigma = this.calcGlobalSigma();
		//System.out.println("globalSigma = " + _globalSigma + " k1 = " + _constantValues[0] + "  k2 = " + _constantValues[1]);

		if(_minSigma>_globalSigma)
		{
			_minSigma = _globalSigma;
			//System.out.println("*************MinSigma = " + _minSigma + " k1 = " + _constantValues[0] + "  k2 = " + _constantValues[1]);
			//_smallestRectIndexFinal = _indexOfRect;
		}
			
		
		return _globalSigma;
	}
	
	
	
	

	
    public void calcBestAbsCoeff(int _nOfIterations, boolean _ifFitAnyway)
    {
    	M._globalFitOptimizerAbsCoeffMatrix.restart();
    	
    	for(int w=0; w<M.get_availWaveCollection().size(); w++)
		{
    		M.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;
    		/*if(_ifFitAnyway)
    		{
				M.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterations, Math.round((float)_nOfIterations/2f), 0.5f, true);
    		}
    		else
    			if(M.get_ifContinueToFit())
				{
					M.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterations, Math.round((float)_nOfIterations/2f), 0.5f, false);
				}*/
		}
    }
    
    public float calcGlobalSigma()
    {
		_globalSigma=0;
		//int _maxSteps =  (M._solvedCurveCollectionX.size() - M._posToStartFittingWith);
		int _steps =0;
		int _start, _end;
		float _tempSigma = 0, _sumOpticalDensity, _currentSolvedValue;
		

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
				
				
				_sumOpticalDensity = 0;
				_currentSolvedValue = 0;
				
				for(int m=0; m<_nOfEq; m++)
				{
					_sumOpticalDensity+=  (M._solvedConcentrationCollectionY.get(m).get(i)-M._beforePulseConcentrationValues[m])*M._xSAbsSpecCollectionYFiltered.get(m).get(w);
				}
		
		
				if(M._ifCalcCurveShifts)
				{
					_currentSolvedValue = (_sumOpticalDensity-M._curveShiftCollection.get(w))*M._cuvetteThickness;
				}
				else
				{
					_currentSolvedValue = (_sumOpticalDensity-M._levelPosDisplay)*M._cuvetteThickness;
				}

				
				
				_tempSigma+= Math.pow((M._kinCollectionY.get(w).get(i) - _currentSolvedValue), 2);

			}
			
			
			_tempSigma = (float)Math.sqrt((_tempSigma/_steps));
			
			
			if(M._ifUseWeight)
			{
				_tempSigma = _tempSigma*M._weightCollection.get(w);
			}
			
			_globalSigma += _tempSigma;
		}
		
		return _globalSigma/M._availWaveCollection.size();

    }
	
	
	
	
	
	
	
	
	public void setInitialIntervalsForParameters()
	{
		int _counter=0;
		float ai,bi,ci, scalei, shifti;
		for(int i=0; i<_nOfConst; i++)
		{
			if(_variablesLimitsCollection.get(0).get(i)[0]==1)
			{
				ci = _constantValues[i];
				ai = ci*M.get_directLowLimit();
				bi = ci*M.get_directHighLimit();
				
				//System.out.println("Low limit = " + ai);
				//System.out.println("High limit = " + bi);
				
				if((ai<_variablesLimitsCollection.get(0).get(i)[1])&(_variablesLimitsCollection.get(0).get(i)[1]!=-1))
					ai = _variablesLimitsCollection.get(0).get(i)[1]; 

				if((bi>_variablesLimitsCollection.get(0).get(i)[2])&(_variablesLimitsCollection.get(0).get(i)[2]!=-1))
					bi = _variablesLimitsCollection.get(0).get(i)[2];
				

				shifti = ai;
				scalei = 1e6f/(bi-ai);
				_shiftCollection.add(shifti);
				_scaleCollection.add(scalei);
				
				//System.out.println("Shift for dimention "+ _counter + " = " + shifti);
				//System.out.println("Scale for dimention "+ _counter + " = " + scalei);
				
				ai=0;
				bi=1e6f;
				ci=5e5f;
				
				_rectCollection.get(0).set_interval(_counter, ai, bi, ci);
				
				_counter++;
			}
			
		}
	}
	
	public void printRectCollectionLinesDataOutLines2D(String Path)
	{
		//write to a file;
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(Path));
			String line="";
			for(int i=0; i<_rectCollection.size(); i++)
			{
				GlobalFitDirectRectangle _rect = _rectCollection.get(i);
				
				line = "" + _rect._data.get(0)[0] + "	" + _rect._data.get(1)[0]; //
				out.write(line);
				out.newLine();
				
				line = "" + _rect._data.get(0)[1] + "	" + _rect._data.get(1)[0]; //
				out.write(line);
				out.newLine();
				
				line = "" + _rect._data.get(0)[1] + "	" + _rect._data.get(1)[1]; //
				out.write(line);
				out.newLine();
				
				line = "" + _rect._data.get(0)[0] + "	" + _rect._data.get(1)[1]; //
				out.write(line);
				out.newLine();
				
				line = "" + _rect._data.get(0)[0] + "	" + _rect._data.get(1)[0]; //
				out.write(line);
				out.newLine();
				
				line = "";
				out.write(line);
				out.newLine();
			}
			
			out.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void printRectCollectionLinesDataOutDots(String Path)
	{
		//write to a file;
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(Path));
			String line="";
			for(int i=0; i<_rectCollection.size(); i++)
			{
				GlobalFitDirectRectangle _rect = _rectCollection.get(i);
				
				line = "";
				for(int j=0; j<this.getNOfDimentions(); j++)
					{
						line = line + convertBackToRealValues(i, j, 0) + "	"; 
					}
				
				line = line + _rect.get_functionValue();
				out.write(line);
				out.newLine();
				}
			
			out.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void printRectCollectionLinesDistAndFunc(String Path)
	{
		//write to a file;
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(Path));
			String line="";
			for(int i=0; i<_rectCollection.size(); i++)
			{
				GlobalFitDirectRectangle _rect = _rectCollection.get(i);			

				line = "" + _rect.getDistance() + "	" + _rect.get_functionValue(); 

				out.write(line);
				out.newLine();
				}
			
			out.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	
	public final boolean isBusy()
	{
		return this._isBusy;
	}
	
}
