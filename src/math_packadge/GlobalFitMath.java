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


public class GlobalFitMath extends JFrame{
	
	
	public JButton _updateProgressBarsButton = new JButton();
	public JButton _continueToFitButton = new JButton();
	
	public int _nOfEq = 2;
	public int _nOfConst = 2;
	public int _nOfEqOpened = 1;
	public int _nOfConstOpened = 1;
	public int _nOfEqRel = 0;
	
	
	public File file, newFile;
	public String _filePath="";
	public File saveName, openName;
	public boolean _saveNameEmpty = true;
	public boolean _ifShowCreatedCurve = false;
	public boolean _ifShowSlice = false;
	public Color _fittingColor = Color.red;
	
	public Object _syncObject = new Object();
	
	
	public float[] _xArray;
	public float[] _yArray;
	public float[] _xArrayError;
	public float[] _yArrayError;
	public float[] _xArrayIni;
	public float[] _yArrayIni;
	public float[] _tempArrayX;
	public float[] _tempArrayY;
	public float[] _tempArrayXIni;
	public float[] _tempArrayYIni;
	public ArrayList<float[]> _createdCurveArray, _createdCurveArrayError;
	public ArrayList<String> _openedRecent = new ArrayList<String>();
	
	public float _xMax=1000,_xMin=0,_yMax=0,_yMin=-1,_xScaler=1,_yScaler=1, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1,_yScalerIni=1;
	public float _xMaxExt=-10,_xMinExt=10,_yMaxExt=-10,_yMinExt=10,_xScalerExt,_yScalerExt, _yZeroPosExt=0f,_xZeroPosExt=0f,_xScalerIniExt=1f,_yScalerIniExt=1f;
	public float _xMaxSlice=-10,_xMinSlice=10,_yMaxSlice=-10,_yMinSlice=10,_xScalerSlice,_yScalerSlice, _yZeroPosSlice=0f,_xZeroPosSlice=0f,_xScalerIniSlice=1f,_yScalerIniSlice=1f;
	public float _xMaxSigma=-10,_xMinSigma=10,_yMaxSigma=-10,_yMinSigma=10,_xScalerSigma,_yScalerSigma;
	public float _xMaxError=-10,_xMinError=10,_yMaxError=-10,_yMinError=10,_xScalerError,_yScalerError, _yZeroPosError=0f,_xZeroPosError=0f,_xScalerIniError,_yScalerIniError;
	public int _arraySize=0, _trimmedArraySize=0;
	public int _gridSizeX=Constants._mainFrameInitSizeX-100, _gridSizeY=Constants._mainFrameInitSizeY-600, _gridSizeXError, _gridSizeYError, 
				_gridSizeXExt=Constants._extGraphPanelGridSizeX, _gridSizeYExt=Constants._extGraphPanelGridSizeY,
				_gridSizeXSlice=Constants._slicePanelGridSizeX, _gridSizeYSlice=Constants._slicePanelGridSizeY,
				_gridSizeXSigma =Constants._sigmaPanelGridSizeX,  _gridSizeYSigma=Constants._sigmaPanelGridSizeY;
	public float _magX1=0,_magY1=0, _magX2=Constants._kineticsModeGridPosition.x+_gridSizeX, _magY2=Constants._kineticsModeGridPosition.y+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0, _magPosXError = 0, _magPosYError = 0, _magPosXExt = 0, _magPosYExt = 0, _magPosXSlice = 0, _magPosYSlice = 0;
	public int _size = 100000;
	public int _globalFitPosOfSelectedKin=0; //0,1,2,3,4,5,6,7,8,9;
	public float _globalFitXMax=-10000, _globalFitXMin=10000, _globalFitYMax=-10000, _globalFitYMin=10000;
	public int _fitMarkerFirstPos = 1, _fitMarkerLastPos = 1;
	public int _markerShiftStep = 5, _markerShiftStepFast = 100;
	public float _zeroPosReal = 0f, _zeroPosDisplay=0;
	public float _beforeZeroPosReal = 0f, _beforeZeroPosDisplay=0;
	public float _slicePosReal = 0f, _slicePosDisplay=0;
	public float _levelPosReal = 0f, _levelPosDisplay=0f, _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public int _saveEvery=1;
	public int _globalFitSelectedWavelength=500;
	public int _globalFitPosOfSelectedSpec=0;
	public int _nOfPointsSmoothMove=0;
	public int _posToStartFittingWith=-1; //0,1,2,3,4,5,6,7, but it starts with 1, because _kinCollectionX.get(w).get(0) = kin wavelength;
	public float _zeroShiftStep = 1000, _zeroShiftStepFast = 10000, _levelShiftStep = 0.001f, _levelShiftStepFast = 0.1f;
	public ArrayList<Integer> _logArray, _colorArray;
	public int _nOfCreatedCurvePoints, _nOfCreatedCurvePointsError;
	public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	public int _nOfSmoothPoints = 0, _nOfVertSmoothPoints = 1, _posOfSelectedSpectrum = 1, _posFirstMarker = 0, _posLastMarker = 0,_rememberPosFirstMarker = 0, _rememberPosLastMarker = 0;
	public float _A, _k, _D0;
	public float _a10, _a20, _a30;
	public float _sigmaA2 = 0, _sigma1=0, _sigma2=0, _sigma3=0, _sigmaSum=0;
	public int _fitRK4Accuracy=1;
	public String _algorithmToBeUsed = "light";
	public int _globalFitPosOfCurrentTimePoint=1;
	public int _timeStep=1;
	
	
	public Rectangle2D.Float myRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x, _levelPosReal, _gridSizeX, 10);
	public Rectangle2D.Float myZeroRect = new Rectangle2D.Float(_zeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	public Rectangle2D.Float selectedRect = new Rectangle2D.Float(0,0,0,0);
	public Rectangle2D.Float myBeforeZeroRect = new Rectangle2D.Float(_beforeZeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	public Rectangle2D.Float sliceRect = new Rectangle2D.Float(_slicePosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	public Rectangle2D.Float beforeZeroSelRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x,Constants._kineticsModeGridPosition.y,10,_gridSizeY);
	public Rectangle2D.Float fitMarkerFirstRect = new Rectangle2D.Float(0,0,13,35);
	public Rectangle2D.Float fitMarkerLastRect = new Rectangle2D.Float(0,0,13,35);
	
	public ArrayList<ArrayList<Rectangle2D.Float>> _absRectCollection = new ArrayList<ArrayList<Rectangle2D.Float>>();



	
	
	//public ArrayList<ArrayList<Float>> _kinCollectionXIni = new ArrayList<ArrayList<Float>>();
	//public ArrayList<ArrayList<Float>> _kinCollectionYIni = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _kinCollectionX = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _kinCollectionY = new ArrayList<ArrayList<Float>>();
	public ArrayList<float[]> _kinShiftCollection = new ArrayList<float[]>();
	public ArrayList<Float> _solvedCurveCollectionX = new ArrayList<Float>();
	public ArrayList<ArrayList<Float>> _solvedCurveCollectionY = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<ArrayList<Float>>> _solvedCurveContributionCollectionY = new ArrayList<ArrayList<ArrayList<Float>>>();
	public ArrayList<ArrayList<Float>> _solvedCurveErrorCollectionY = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _solvedConcentrationCollectionY = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFiltered = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _xSAbsSpecErrorCollection = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFilteredCopyUndo = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFilteredCopyRedo = new ArrayList<ArrayList<Float>>();
	public ArrayList<Float> _weightCollection = new ArrayList<Float>();
	public ArrayList<Float> _curveShiftCollection = new ArrayList<Float>();
	public ArrayList<Float> _availWaveCollection = new ArrayList<Float>();
	public ArrayList<Float> _sigmaCollection = new ArrayList<Float>();
	public ArrayList<ArrayList<Integer>> _markersCollection = new ArrayList<ArrayList<Integer>>();
	
	public ArrayList<ArrayList<float[]>> _variablesLimitsCollection = new ArrayList<ArrayList<float[]>>();
	// 1st row: 0 - const; 1 - abs coeff; 2 - init conc; 3 - burnt conc;
	// 2nd row: for constants: 10 constants arrays
	// 3rd row: for constants and conc: 0 - (1/0)enabled/disabled; 1 - min; 2 - max, 3 - (1/0)dependent/independent;
	// for abs coeff: 0 - enabled/disabled; 1 - limits in percentage;
	
	
	public boolean _ifSolvedCurveCollectionLocked = false;
	public boolean _ifLockGraphPanels = false;
	//for reading the input information
	
	public String[] _eqLabelsContent = new String[10];
	public String[] _constantLabelsContent = new String[10];
	public String[] _concentrationLabelsContent = new String[10];
	public String[] _beforePulseConcentrationLabelsContent = new String[10];
	public String[] _concEqLabelsContent = new String[10];
	public String[] _xSNamesArray = new String[10];
	public float[] _constantValues = new float[10];
	public float[] _constantErrorValues = new float[10];
	public float[] _concentrationValues = new float[10]; // Xs
	public float[] _concentrationValuesIni = new float[10]; // Xs
	public float[] _beforePulseConcentrationValues = new float[10]; // Xs
	public float[] _derivativeFuncValues = new float[10];

	public ArrayList<ArrayList<ArrayList<Float>>> _funcCollection = new ArrayList<ArrayList<ArrayList<Float>>>();
	// first row - statements of diffirential equations (1..10), second row - elements of the statements(-2*k1*X1*X2), thrid row - content of the elements (-2*k1, X1, X2)
	public ArrayList<ArrayList<ArrayList<Float>>> _concFuncCollection = new ArrayList<ArrayList<ArrayList<Float>>>();
	// first row - statements of equations (1..10), second row - elements of the statements(2*X2(0)), thrid row - content of the elements (2, X2(0))
	public float _absValueOfDraggedMarker=0;
	public float _wavelengthValueOfDraggedMarker=0;
	public boolean _ifShowAbsValueLabel = false;
	public boolean _ifShowExtInputPanel = false;
	public boolean _ifEnableSmoothMove = false;
	public boolean _ifKeepAboveZero = true;
	public boolean _ifEnableMoveAllMarkersMode = false;
	public boolean _ifContinueToFit = false;
	public boolean _ifAlterAbsCoeff = false;
	public boolean _ifAlterConst = false;
	public boolean _ifAlterInitConcentration = false;
	public boolean _ifAlterBurntConcentration = false;
	public boolean _ifUseWeight = false;
	public boolean _ifCalcCurveShifts = false;
	public boolean _ifEnableCurveBounds = false;
	public boolean _ifStatusFine = true;
	public boolean _ifCalcSigmaWithWeights = false;
	public boolean _ifCalcContributions = false;
	public boolean _ifShowTransientSpec = true;
	public boolean _ifShowCalcSpec = false;
	public boolean _ifShowContributionsSpec = false;
	public boolean _ifShowBackgroundCurves = true;
	public boolean _ifShowErrorExt = false;
	public boolean _ifShowErrorExtWithBars = true;
	
	public boolean _ifUpdateProgressBars = false;	
	public float _slowBarProgress = 0;
	public float _fastBarProgress = 0;
	public float _globalSigma=0;
	public int _nOfMajorIterationsAbs=Constants._nOfMajorIterationsAbsDefault, _nOfMinorIterationsAbs=7,
				_nOfMajorIterationsConst=Constants._nOfMajorIterationsConstDefault, _nOfMinorIterationsConst=7,
				_slopeValue=Constants._slopeValueAbsDefault, _slopeValueConst = Constants._slopeValueConstDefault,
				_nOfIterationsGradientAbs = Constants._nOfIterationsGradientAbsDefault,
				_nOfIterationsGradientConst = Constants._nOfIterationsGradientConstDefault,
				_nOfIterationsGradientConc = Constants._nOfIterationsGradientConcDefault,
				_directNOfIterations = Constants._directNOfIterationsDefault;
	public float _cubeSideAbs = Constants._cubeSideAbsDefault, _cubeSideConst = Constants._cubeSideConstDefault, _cubeSideConc = Constants._cubeSideConcDefault,
				_directLowLimit = Constants._directLowLimit, _directHighLimit = Constants._directHighLimit; 
	public float _solvedCurveTimeScale = 1E6f;
	public float _cuvetteThickness = 1;
	public float _constantShiftValue = 0.05f;
	
	//mouse proccessing
	public float _mouseX, _mouseY, _mouseXLabel, _mouseYLabel, _mouseXExt, _mouseYExt, _mouseXLabelExt, _mouseYLabelExt, _mouseXSigma, _mouseYSigma, _mouseXLabelSigma, _mouseYLabelSigma,
				_mouseXSlice, _mouseYSlice, _mouseXLabelSlice, _mouseYLabelSlice;
	public String _mouseLabelText, _mouseLabelTextExt, _mouseLabelTextSlice, _mouseLabelTextSigma;
	public boolean _ifShowAverageValue=false;
	public boolean _ifNeedToRepaint=false;
	public float _graphAverage=0;
	public int x,y,x1,y1,x2,y2,c, xExt,yExt,x1Ext,y1Ext,x2Ext,y2Ext,cExt, _numberOfDraggedRect=0, _numberOfLastClickedRect=0,
				xSlice,ySlice,x1Slice,y1Slice,x2Slice,y2Slice,cSlice;
	public boolean _startSel = false, _beingSel=false, _moveScreen=false, _moveLevel = false, _moveZero=false, _moveBeforeZero=false, _moveSlice=false, _moveFitMarkerFirst = false, _moveFitMarkerLast = false,
	   			_movingFitMarkerFirst = false, _movingFitMarkerLast = false, _ifCursorFree = true, _moveExtMarker=false;
				

	Cursor _cursor, _cursorExt,  _cursorSlice;
	
	public GlobalFitFileHelper _globalFitFileHelper = new GlobalFitFileHelper(this);
	public GlobalFitRK4Solver _globalFitRK4Solver = new GlobalFitRK4Solver(this);
	public GlobalFitMouseHandler _globalFitMouseHandler = new GlobalFitMouseHandler(this);
	public GlobalFitHyperSpace _globalFitHyperSpace = new GlobalFitHyperSpace(this);
	public GlobalFiOptimizerAbsCoeffSimple _globalFiOptimizerAbsCoeffSimple = new GlobalFiOptimizerAbsCoeffSimple(this);
	public GlobalFitOptimizerAbsCoeffGradient _globalFitOptimizerAbsCoeffGradient = new GlobalFitOptimizerAbsCoeffGradient(this);
	public GlobalFitOptimizerConstSimple _globalFitOptimizerConstSimple = new GlobalFitOptimizerConstSimple(this);
	public GlobalFitOptimizerConstWideRange _globalFitOptimizerConstWideRange = new GlobalFitOptimizerConstWideRange(this);
	public GlobalFitOptimizerConstGradient _globalFitOptimizerConstGradient = new GlobalFitOptimizerConstGradient(this);
	public GlobalFitErrorCalculator _globalFitErrorCalculator = new GlobalFitErrorCalculator(this);
	public GlobalFitGlobalSigmaCalculator _globalFitGlobalSigmaCalculator = new GlobalFitGlobalSigmaCalculator(this);
	public GlobalFitOptimizerAbsCoeffMatrix _globalFitOptimizerAbsCoeffMatrix = new GlobalFitOptimizerAbsCoeffMatrix(this);	
	public GlobalFitPanelScaleCalculator _globalFitPanelScaleCalculator = new GlobalFitPanelScaleCalculator(this);	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public GlobalFitMath()
	{

		
		
		fillLabelsContent();
		addNewXDefaultSpec();
		addNewXDefaultSpec();
		extGraphPanelSetDefaultBounds();
		slicePanelSetDefaultBounds();
		createVariablesLimitsArray();
		graphPanelSetDefaultBounds();
		//fillAbsCollections();
		//addNoise();
		
	}
	
	
	public void fillAbsCollections()
	{
		ArrayList<Float> _tempArrayY;
		ArrayList<Float> _tempArrayError;
		ArrayList<Rectangle2D.Float> _tempArrayRect = new ArrayList<Rectangle2D.Float>();
		Rectangle2D.Float tempRect = new Rectangle2D.Float(0, 0, 10, 30);
		
		
		for(int j=0; j<_nOfEq; j++)
		{
			_tempArrayY = new ArrayList<Float>();
			_tempArrayError = new ArrayList<Float>();
			_xSAbsSpecCollectionYFiltered.add(_tempArrayY);
			_xSAbsSpecErrorCollection.add(_tempArrayError);
			
			_tempArrayRect = new ArrayList<Rectangle2D.Float>();
			_absRectCollection.add(_tempArrayRect);
		}
		
		float f;
		for(int i=20;i<80; i++)
		{
			f=i*10;
			_availWaveCollection.add(f);
			for(int j=0; j<_nOfEq; j++)
			{
				_xSAbsSpecCollectionYFiltered.get(j).add(0f);
				_xSAbsSpecErrorCollection.get(j).add(0f);
				tempRect = new Rectangle2D.Float(0, 0, 10, 30);
				_absRectCollection.get(j).add(tempRect);
			}

		}
		
		
		extGraphPanelSetDefaultBounds();
		//extGraphPanelRecalcSpecBounds();
	}
	
	public float calcTimeInterval()
	{
		float _result=-1;
		
		if(this._kinCollectionX.size()!=0)
		{
			_result = _kinCollectionX.get(0).get(5) - _kinCollectionX.get(0).get(4);
		}
		return _result;
	}
	
	
	public void extGraphPanelSetDefaultBounds()
	{
		_globalFitPanelScaleCalculator.extGraphPanelSetDefaultBounds();
	}
	
	
	
	public void slicePanelSetDefaultBounds()
	{
		_globalFitPanelScaleCalculator.slicePanelSetDefaultBounds();
	}
	
	
	public void graphPanelSetDefaultBounds()
	{
		_globalFitPanelScaleCalculator.graphPanelSetDefaultBounds();
		
	}
	
	public void extGraphPanelRecalcSpecBounds()
	{
		_globalFitPanelScaleCalculator.extGraphPanelRecalcSpecBounds();
	}
		
		
	public void slicePanelRecalcSpecBounds()
	{
		_globalFitPanelScaleCalculator.slicePanelRecalcSpecBounds();
		
	}
	
	
	public void extGraphPanelSetXSSpecBoundsX()
	{
		_globalFitPanelScaleCalculator.extGraphPanelSetXSSpecBoundsX();
	}

	
	
	public void slicePanelSetXSSpecBoundsX()
	{
		_globalFitPanelScaleCalculator.slicePanelSetXSSpecBoundsX();
	}
	
	public void calcErrorPanelBoundsY()
	{
		_globalFitPanelScaleCalculator.calcErrorPanelBoundsY();
	}
	
	
	
	
	public void calcSigmaPanelBounds()
	{
		_globalFitPanelScaleCalculator.calcSigmaPanelBounds();
	} 
	
	
	
	
	
	
	
	public void addNewXDefaultSpec()
	{
		ArrayList<Float> _tempArrayY = new ArrayList<Float>();
		ArrayList<Float> _tempArrayError = new ArrayList<Float>();
		ArrayList<Rectangle2D.Float> _tempArrayRect = new ArrayList<Rectangle2D.Float>();
		Rectangle2D.Float tempRect;
		
		for(int i=0; i<_availWaveCollection.size(); i++)
		{
			_tempArrayY.add(0f);
			_tempArrayError.add(0f);
			
			tempRect = new Rectangle2D.Float(0, 0, 10, 30);
			_tempArrayRect.add(tempRect);
		}
		
		_xSAbsSpecCollectionYFiltered.add(_tempArrayY);
		_xSAbsSpecErrorCollection.add(_tempArrayError);
		_absRectCollection.add(_tempArrayRect);
		
		
	}
	
	public void removeOneXSpec()
	{
		int _index = _xSAbsSpecCollectionYFiltered.size()-1;
		
		_xSAbsSpecCollectionYFiltered.remove(_index);
		_xSAbsSpecErrorCollection.remove(_index);
		_absRectCollection.remove(_absRectCollection.size()-1);
	}
	
	public void restoreSelectedXSpec()
	{
		ArrayList<Float> _tempArrayY = new ArrayList<Float>();
		ArrayList<Float> _tempArrayError = new ArrayList<Float>();
		ArrayList<Rectangle2D.Float> _tempArrayRect = new ArrayList<Rectangle2D.Float>();
		Rectangle2D.Float tempRect;
		
		for(int i=0; i<_availWaveCollection.size(); i++)
		{
			_tempArrayY.add(0f);
			_tempArrayError.add(0f);
			
			tempRect = new Rectangle2D.Float(0, 0, 10, 30);
			_tempArrayRect.add(tempRect);
		}
		
		_xSAbsSpecCollectionYFiltered.set(_globalFitPosOfSelectedSpec, _tempArrayY);
		_xSAbsSpecErrorCollection.set(_globalFitPosOfSelectedSpec, _tempArrayError);
		_absRectCollection.set(_globalFitPosOfSelectedSpec, _tempArrayRect);
		
	}
	
	
	public void restoreAllXSSpec()
	{
		_xSAbsSpecCollectionYFiltered.clear();
		_xSAbsSpecErrorCollection.clear();
		_absRectCollection.clear();
		
		for(int j=0; j<_nOfEq; j++)
		{
			ArrayList<Float> _tempArrayY = new ArrayList<Float>();
			ArrayList<Float> _tempArrayError = new ArrayList<Float>();
			ArrayList<Rectangle2D.Float> _tempArrayRect = new ArrayList<Rectangle2D.Float>();
			Rectangle2D.Float tempRect;
			
			for(int i=0; i<_availWaveCollection.size(); i++)
			{
				_tempArrayY.add(0f);
				_tempArrayError.add(0f);
				
				tempRect = new Rectangle2D.Float(0, 0, 10, 30);
				_tempArrayRect.add(tempRect);
			}
			
			_xSAbsSpecCollectionYFiltered.add(_tempArrayY);
			_xSAbsSpecErrorCollection.add(_tempArrayError);
			_absRectCollection.add(_tempArrayRect);
		}
		
		
	}
	
	
	
	
	
	
	public void addNewWavelengthInXSSpec(float _wavelength)
	{
		ArrayList<ArrayList<Float>> _tempSuperArrayY = new ArrayList<ArrayList<Float>>();				
		ArrayList<ArrayList<Float>> _tempSuperArrayError = new ArrayList<ArrayList<Float>>();				
		ArrayList<ArrayList<Rectangle2D.Float>> _tempSuperArrayRect = new ArrayList<ArrayList<Rectangle2D.Float>>();				
		ArrayList<Float> _tempArrayY;
		ArrayList<Float> _tempArrayError;
		ArrayList<Rectangle2D.Float> _tempArrayRect;
		Rectangle2D.Float tempRect;
		
		
		_availWaveCollection.add(_wavelength);

		
		//_availWaveCollection = sortArrayList_Float_Array(_availWaveCollection);
		
		for(int i=0; i<_nOfEq; i++)
		{
			_tempArrayY = _xSAbsSpecCollectionYFiltered.get(i);
			_tempArrayY.add(0f);
			_tempSuperArrayY.add(_tempArrayY);
			
			_tempArrayError = _xSAbsSpecErrorCollection.get(i);
			_tempArrayError.add(0f);
			_tempSuperArrayError.add(_tempArrayError);			
			
			_tempArrayRect=_absRectCollection.get(i);
			tempRect = new Rectangle2D.Float(0, 0, 10, 30);
			_tempArrayRect.add(tempRect);
			_tempSuperArrayRect.add(_tempArrayRect);
		}
		
		_xSAbsSpecCollectionYFiltered.clear();
		_xSAbsSpecCollectionYFiltered=_tempSuperArrayY;
		_xSAbsSpecErrorCollection.clear();
		_xSAbsSpecErrorCollection=_tempSuperArrayError;
		_absRectCollection.clear();
		_absRectCollection=_tempSuperArrayRect;

		
		sortFilteredArrays();
	}
	
	
	
	
	public ArrayList<Float> sortArrayList_Float_Array(ArrayList<Float> _array)
	{
		ArrayList<Float> _tempArray = new ArrayList<Float>();

		float _value, _minValue=0; 
		int _index, _neededIndex;
		
		//int cycle=0;
		while(_array.size()!=0)
		{
			//cycle++;
			_index=0;
			_neededIndex=0;
			_value = _array.get(0);
			
			for(int j=0; j<_array.size(); j++)
			{
				if((_value>_array.get(j)))//&(_value>_minValue))
				{
					_value = _array.get(j);
					_neededIndex = _index;
					
				}
				_index++;
			}

			_array.remove(_neededIndex);
			_tempArray.add(_value);
		}
		

		return _tempArray;
		
		
	}
	
	
	
	public void sortFilteredArrays()
	{
		ArrayList<Float> _tempArrayWave = new ArrayList<Float>();
		ArrayList<Float> _tempArrayY;
		ArrayList<ArrayList<Float>> _tempSuperArrayY = new ArrayList<ArrayList<Float>>();				
		ArrayList<Float> _tempArrayError;
		ArrayList<ArrayList<Float>> _tempSuperArrayError = new ArrayList<ArrayList<Float>>();				

		float _value, _minValue=0; 
		int _index, _neededIndex;

		
		for(int i=0; i<_nOfEq; i++)
		{
			_tempArrayY = new ArrayList<Float>();
			_tempSuperArrayY.add(_tempArrayY);
			_tempArrayError = new ArrayList<Float>();
			_tempSuperArrayError.add(_tempArrayError);
		}
		
		while(_availWaveCollection.size()!=0)
		{
			_index=0;
			_neededIndex=0;
			_value = _availWaveCollection.get(0);
			//System.out.println(" initial value = " + _value);			

			for(int j=0; j<_availWaveCollection.size(); j++)
			{
				
				if((_value>_availWaveCollection.get(j)))//&(_value>_minValue))
				{
					_value = _availWaveCollection.get(j);
					_neededIndex = _index;
				}
				_index++;
			}

			
			//System.out.println("value = " + _value);
			//System.out.println("neededIndex = " + _neededIndex);
			_availWaveCollection.remove(_neededIndex);
			_tempArrayWave.add(_value);
			for(int i=0; i<_nOfEq; i++)
			{
				_tempSuperArrayY.get(i).add(_xSAbsSpecCollectionYFiltered.get(i).get(_neededIndex));
				_xSAbsSpecCollectionYFiltered.get(i).remove(_neededIndex);
				_tempSuperArrayError.get(i).add(_xSAbsSpecErrorCollection.get(i).get(_neededIndex));
				_xSAbsSpecErrorCollection.get(i).remove(_neededIndex);
			}
			
		}
		
		_xSAbsSpecCollectionYFiltered.clear();
		_xSAbsSpecCollectionYFiltered=_tempSuperArrayY;
		_xSAbsSpecErrorCollection.clear();
		_xSAbsSpecErrorCollection=_tempSuperArrayError;
		_availWaveCollection.clear();
		_availWaveCollection = _tempArrayWave;
		
		
	}	
	
	
	
	
	public void removeOneWavelengthFromXSSpec()
	{
		

		
		_availWaveCollection.remove(_globalFitPosOfSelectedKin);
		for(int i=0; i< _nOfEq; i++)
		{
			_xSAbsSpecCollectionYFiltered.get(i).remove(_globalFitPosOfSelectedKin);
			_xSAbsSpecErrorCollection.get(i).remove(_globalFitPosOfSelectedKin);
			_absRectCollection.get(i).remove(_globalFitPosOfSelectedKin);
		}
	}
	
	
	
	
	
	
	public void performSmoothMove(float dyMouse)
	{
		//f= a*e^(-(  ((x-b)^2)/(2*c^2)  ));
			float y = 0f;

			float a, b, c;	

			
			
			if(!_ifEnableMoveAllMarkersMode)
			{
				if(_ifEnableSmoothMove)
				{
					for(int i=-_numberOfLastClickedRect; i<(_xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).size()-_numberOfLastClickedRect); i++)
					{
						if(((_numberOfLastClickedRect+i)>=0)&( _numberOfLastClickedRect+i)< _xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).size() )
						{
							y= _xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).get(_numberOfLastClickedRect+i); 
							y=y+dyMouse/_yScalerExt*(float)Math.pow(Math.E, -Math.pow(i, 2)/(2*Math.pow(_nOfPointsSmoothMove/24+0.01, 2)));
							if((y>0)&(_ifKeepAboveZero))
								y=0;
							
							_xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).set(_numberOfLastClickedRect+i, y);
						}
					}

				}
				else
				{
					y =  _xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).get(_numberOfLastClickedRect); //display(on label) Y value
					y=y+dyMouse/_yScalerExt;
					if((y>0)&(_ifKeepAboveZero))
						y=0;
					_xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).set(_numberOfLastClickedRect, y);

				}
			}
			else
			{
				y= _xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).get(_numberOfLastClickedRect)+0.000001f;
				float _multiplyingCoeff = (dyMouse/_yScalerExt+y)/(y);
				
				for(int i=0; i<_xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).size(); i++)
				{
						
						y= _xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).get(i); 
						y=y*_multiplyingCoeff;
						//if((y>0)&(_ifKeepAboveZero))
						//	y=0;
						
						_xSAbsSpecCollectionYFiltered.get(_globalFitPosOfSelectedSpec).set(i, y);
				}

				
				
			}

			
			
			
			
			
			
			//_nOfPointsSmoothMove=1;
		
		
	}
	
	
	
	public void makeCopyOf_xSAbsSpecCollectionYFiltered()
	{
		_xSAbsSpecCollectionYFilteredCopyUndo = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> _tempArray;
		float a;
		
		for(int i=0; i<_xSAbsSpecCollectionYFiltered.size(); i++)
		{
			 _tempArray = new ArrayList<Float>();
			 for(int j=0; j<_xSAbsSpecCollectionYFiltered.get(i).size(); j++)
			 {
				 float b = _xSAbsSpecCollectionYFiltered.get(i).get(j);
				 _tempArray.add(b*1);
			 }
			 _xSAbsSpecCollectionYFilteredCopyUndo.add(_tempArray);
		}
		
		
		
	}
	
	
	public void performUndo()
	{
		_xSAbsSpecCollectionYFilteredCopyRedo = new ArrayList<ArrayList<Float>>();
		_xSAbsSpecCollectionYFilteredCopyRedo = _xSAbsSpecCollectionYFiltered;
		_xSAbsSpecCollectionYFiltered = new ArrayList<ArrayList<Float>>();
		_xSAbsSpecCollectionYFiltered = _xSAbsSpecCollectionYFilteredCopyUndo;
		System.out.println("Undo");
	}
	
	
	public void performRedo()
	{
		_xSAbsSpecCollectionYFiltered = new ArrayList<ArrayList<Float>>();
		_xSAbsSpecCollectionYFiltered = _xSAbsSpecCollectionYFilteredCopyRedo;
		System.out.println("Redo");
	}
	
	
	

	
	
	public String openFile() 
	{
		return _globalFitFileHelper.openFile();
	}
	

	public String globalFitOpenProject() 
	{
		return _globalFitFileHelper.globalFitOpenProject();
	}

	
	public void addOpenRecent(String _filePath)
	{
		_globalFitFileHelper.addOpenRecent(_filePath);
	}

	public void readOpenedRecentFile()
	{
		_globalFitFileHelper.readOpenedRecentFile();
	}

	
	public void addNoise()
	{
		_globalFitFileHelper.addNoise();
	}
	

	public String globalFitOpenModel() 
	{
		return _globalFitFileHelper.globalFitOpenModel();
	}
	

	
	public String globalFitOpenSpectra() 
	{
		return _globalFitFileHelper.globalFitOpenSpectra();
	}

	public String globalFitOpenOneSpectrum() 
	{
		return _globalFitFileHelper.globalFitOpenOneSpectrum();
	}

	public void globalFitSaveProject()
	{
		_globalFitFileHelper.globalFitSaveProject();
	}
	

	public void globalFitSaveModel()
	{
		_globalFitFileHelper.globalFitSaveModel();
	}

	public void globalFitSaveAllSpec()
	{
		_globalFitFileHelper.globalFitSaveAllSpec();
	}

	
	public void globalFitSaveOneSpec()
	{
		_globalFitFileHelper.globalFitSaveOneSpec();
	}
	
	public void globalFitSaveOneTransientSlice()
	{
		_globalFitFileHelper.globalFitSaveOneTransientSlice();
	}
	
	
	public void globalFitSaveAllSlices()
	{
		_globalFitFileHelper.globalFitSaveAllSlices();
	}
	

	public void proccessGlobalFitProjectFile(File file)
	{
		_globalFitFileHelper.proccessGlobalFitProjectFile(file);
	}
	
	
	public void proccessGlobalFitModelFile(File file)
	{
		_globalFitFileHelper.proccessGlobalFitModelFile(file);
	}
	
	
	public void proccessGlobalFitSpectraFile(File file)
	{
		_globalFitFileHelper.proccessGlobalFitSpectraFile(file);
	}
	
		
	public void proccessGlobalFitOneSpectrumFile(File file, String _fileType)
	{
		_globalFitFileHelper.proccessGlobalFitOneSpectrumFile(file, _fileType);
	}
	

	public int findIndexOfWavelengthAvailable(float _wavelength)
	{
		return _globalFitFileHelper.findIndexOfWavelengthAvailable(_wavelength);
	}

	public void proccessGlobalFitKinFile(File file)
	{
		_globalFitFileHelper.proccessGlobalFitKinFile(file);
	}
	

	
	public void sortKinCollections()
	{
		ArrayList<float[]> _tempArrayY = new ArrayList<float[]>();
		//float[] temp = new float[4];
		//temp[0]=_xMin; temp[1]=_xMax; temp[2]=_yMin; temp[3]= _yMax;
		
		ArrayList<ArrayList<Float>> _tempSuperArrayY = new ArrayList<ArrayList<Float>>();
		ArrayList<ArrayList<Float>> _tempSuperArrayX = new ArrayList<ArrayList<Float>>();	
		ArrayList<ArrayList<Integer>> _tempMarkersArrayY = new ArrayList<ArrayList<Integer>>();	

		int _index, _neededIndex;
		float _value;
		
		while(_kinCollectionX.size()!=0)
		{
			_value = _kinCollectionX.get(0).get(0);
			_index = 0;
			_neededIndex = 0;
			
			for(int j=0; j<_kinCollectionX.size(); j++)
			{
				if(_value>_kinCollectionX.get(j).get(0))
				{
					_neededIndex = _index;
				}
				_index++;
			}
			_tempSuperArrayX.add(_kinCollectionX.get(_neededIndex));
			_tempSuperArrayY.add(_kinCollectionY.get(_neededIndex));
			_tempArrayY.add(_kinShiftCollection.get(_neededIndex));
			_tempMarkersArrayY.add(_markersCollection.get(_neededIndex));
			_kinCollectionX.remove(_neededIndex);
			_kinCollectionY.remove(_neededIndex);
			_kinShiftCollection.remove(_neededIndex);
			_markersCollection.remove(_neededIndex);
		}
		_kinCollectionX.clear();
		_kinCollectionX = _tempSuperArrayX;
		_kinCollectionY.clear();
		_kinCollectionY = _tempSuperArrayY;
		_kinShiftCollection.clear();
		_kinShiftCollection = _tempArrayY;
		_markersCollection.clear();
		_markersCollection = _tempMarkersArrayY;
		
	}

	
	public void globalFitRecalcScale()
	{
		_globalFitPanelScaleCalculator.globalFitRecalcScale();
	}

	
	public void globalFitClearAll()
	{
		//_kinCollectionXIni = new ArrayList<ArrayList<Float>>();
		//_kinCollectionYIni = new ArrayList<ArrayList<Float>>();
		_kinCollectionX = new ArrayList<ArrayList<Float>>();
		_kinCollectionY = new ArrayList<ArrayList<Float>>();
		_kinShiftCollection = new ArrayList<float[]>();
		_markersCollection = new ArrayList<ArrayList<Integer>>();
		
		_solvedCurveCollectionX = new ArrayList<Float>();
		_solvedCurveCollectionY = new ArrayList<ArrayList<Float>>();
		_solvedCurveContributionCollectionY = new ArrayList<ArrayList<ArrayList<Float>>>();
		//_xSAbsSpecCollectionXIni = new ArrayList<ArrayList<Float>>();
		//_xSAbsSpecCollectionYIni = new ArrayList<ArrayList<Float>>();
		_xSAbsSpecCollectionYFiltered = new ArrayList<ArrayList<Float>>();
		_xSAbsSpecErrorCollection = new ArrayList<ArrayList<Float>>();
		_availWaveCollection = new ArrayList<Float>();
		_sigmaCollection = new ArrayList<Float>();
		_absRectCollection = new ArrayList<ArrayList<Rectangle2D.Float>>();
		
		_globalFitXMax=-10;
		_globalFitXMin=10;
		_globalFitYMax=-10;
		_globalFitYMin=10;
		
		
		for(int i=0; i<_nOfEq; i++)
			addNewXDefaultSpec();
		
		extGraphPanelSetDefaultBounds();

		
	}
	

	
	 public void magnifyGraph()
	 {
		 
		 float _sX,_sY;
			//_graphPanel._gridResol = _graphPanel._gridResol*2;
			_sX = _gridSizeX/(_magX2-_magX1);
			_sY = _gridSizeY/(_magY2-_magY1);
			_xScaler = _xScaler*_sX;
			_yScaler = _yScaler*_sY;

			_magPosX = _magPosX*_sX+(_magX1-Constants._kineticsModeGridPosition.x)*_sX;//_graphPanel._xScaler/_graphPanel._xScalerIni;
			_magPosY = _magPosY*_sY+(_magY1-Constants._kineticsModeGridPosition.y)*_sY;//_graphPanel._yScaler/_graphPanel._yScalerIni;

			
			selectedRect.x = 0;
			selectedRect.y = 0;
			selectedRect.width = 0;
			selectedRect.height = 0;
			shiftLevel(0);
			shiftZero(0);
			shiftBeforeZero(0);
			shiftSlice(0);
		 
	 }
	
	
		public void shiftLevel(float shift)
		{
			_levelPosDisplay = _levelPosDisplay + shift;
			_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y, _globalFitYMin, _yScaler);
			myRect.y = _levelPosReal-5;

		}

		public void shiftZero(float shift)
		{
			_zeroPosDisplay = _zeroPosDisplay + shift;
			_zeroPosReal = setZero(_zeroPosDisplay, Constants._kineticsModeGridPosition.x, _globalFitXMin, _xScaler);
			myZeroRect.x = _zeroPosReal-5;

			
		}

		public void shiftBeforeZero(float shift)
		{
			_beforeZeroPosDisplay = _beforeZeroPosDisplay + shift;
			_beforeZeroPosReal = setZero(_beforeZeroPosDisplay, Constants._kineticsModeGridPosition.x, _globalFitXMin, _xScaler);
			myBeforeZeroRect.x = _beforeZeroPosReal-5;

		}
		
		
		public void shiftSlice(float shift)
		{
			_slicePosDisplay = _slicePosDisplay + shift;
			_slicePosReal = setZero(_slicePosDisplay, Constants._kineticsModeGridPosition.x, _globalFitXMin, _xScaler);
			sliceRect.x = _slicePosReal-5;

		}
	
	

	
	
	public void shiftGlobalFitKinWavelength(int shift)
	{
		_globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin + shift;
		if(_globalFitPosOfSelectedKin <0)_globalFitPosOfSelectedKin =0;
		if(_globalFitPosOfSelectedKin >(_kinCollectionX.size()-1))_globalFitPosOfSelectedKin =_kinCollectionX.size()-1;
		
		//_globalFitWavelengthLabel.setText("" + _kinCollectionX.get(_graphPanel._globalFitPosOfSelectedKin).get(0));
		
		//repaint();
	}
	
	
	
	
	public void deleteGlobalFitKin()
	{
		
		if(_kinCollectionX.size()!=1)
		{
			removeOneWavelengthFromXSSpec();

			_kinCollectionX.remove(_globalFitPosOfSelectedKin);
			//_kinCollectionXIni.remove(_globalFitPosOfSelectedKin);
			_kinCollectionY.remove(_globalFitPosOfSelectedKin);
			//_kinCollectionYIni.remove(_globalFitPosOfSelectedKin);
			_kinShiftCollection.remove(_globalFitPosOfSelectedKin);
			_markersCollection.remove(_globalFitPosOfSelectedKin);
			
			_solvedCurveContributionCollectionY = new ArrayList<ArrayList<ArrayList<Float>>>();
			
			if(_solvedCurveCollectionY.size()!=0 && _globalFitPosOfSelectedKin<_solvedCurveCollectionY.size())
				_solvedCurveCollectionY.remove(_globalFitPosOfSelectedKin);
			if(_solvedCurveErrorCollectionY.size()!=0 && _globalFitPosOfSelectedKin<_solvedCurveErrorCollectionY.size())
				_solvedCurveErrorCollectionY.remove(_globalFitPosOfSelectedKin);
			if(_weightCollection.size()!=0  && _globalFitPosOfSelectedKin<_weightCollection.size())
				_weightCollection.remove(_globalFitPosOfSelectedKin);
			if(_sigmaCollection.size()!=0  && _globalFitPosOfSelectedKin<_sigmaCollection.size())
				_sigmaCollection.remove(_globalFitPosOfSelectedKin);
			if(_curveShiftCollection.size()!=0  && _globalFitPosOfSelectedKin<_curveShiftCollection.size())
				_curveShiftCollection.remove(_globalFitPosOfSelectedKin);
			
			
			_globalFitPosOfSelectedKin--;
			if(_globalFitPosOfSelectedKin<0)
			_globalFitPosOfSelectedKin = 0;
			_numberOfLastClickedRect--;
			if(_numberOfLastClickedRect<0)
				_numberOfLastClickedRect = 0;
			shiftGlobalFitKinWavelength(0);
			globalFitRecalcScale();
		}
		else
		{
			globalFitClearAll();
			graphPanelSetDefaultBounds();
		}


	}
	
	

	

	public float setLevel(float _levelPosDisplay, int _gridPosY, float _yMin, float _yScaler)
	{
		return _gridPosY - (_yMin+_levelPosDisplay)*_yScaler;
	}
	
	
	
	
	public float setZero(float _zeroPosDisplay, int _gridPosX, float _xMin, float _xScaler)
	{
		return _gridPosX - (_xMin-_zeroPosDisplay)*_xScaler;
	}
	
	
	public void recalcFitMarkerFirst(boolean _moveOnlyY)
	{

			if(!_moveOnlyY)
			{
			_fitMarkerFirstDisplayX = _xArrayIni[_fitMarkerFirstPos];
			 _fitMarkerFirstRealX = setZero(_xArrayIni[_fitMarkerFirstPos],Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
			}

			 _fitMarkerFirstDisplayY = _yArrayIni[_fitMarkerFirstPos];
			 _fitMarkerFirstRealY = setLevel(-_yArrayIni[_fitMarkerFirstPos],Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
	}
	
	
	
	
	public boolean checkFitMarkerPos()
	{

			if((_fitMarkerFirstDisplayX<_fitMarkerLastDisplayX)&(_fitMarkerFirstDisplayX>_zeroPosDisplay)&(_fitMarkerLastDisplayX>_zeroPosDisplay))
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	
	
	
	public void recalcFitMarkerLast(boolean _moveOnlyY)
	{
			if(!_moveOnlyY)
			{
			_fitMarkerLastDisplayX = _xArrayIni[_fitMarkerLastPos];
			_fitMarkerLastRealX = setZero(_xArrayIni[_fitMarkerLastPos],Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
			}
				
			
			 _fitMarkerLastDisplayY = _yArrayIni[_fitMarkerLastPos];
			 _fitMarkerLastRealY = setLevel(-_yArrayIni[_fitMarkerLastPos],Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
	}
	

	
	
	public int findMarkerArrayPos(float displayX)
	{
		int index=1;
		
		//x = (_kinCollectionX.get(_globalFitPosOfSelectedKin).get(_markersCollection.get(_globalFitPosOfSelectedKin).get(0))-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX;

		
		displayX = (displayX - Constants._kineticsModeGridPosition.x + _magPosX)/_xScaler + _globalFitXMin;
		
		for(int i=2; i<_kinCollectionX.get(_globalFitPosOfSelectedKin).size(); i++)
		{
			if((displayX>_kinCollectionX.get(_globalFitPosOfSelectedKin).get(i-1))&(displayX<=_kinCollectionX.get(_globalFitPosOfSelectedKin).get(i)))
				{
				index = i;
				//counter = 1;
				}
			
		}
		//if(counter==0)index=-1;
		
		if(displayX<_kinCollectionX.get(_globalFitPosOfSelectedKin).get(1))index=1;
		if(displayX>_kinCollectionX.get(_globalFitPosOfSelectedKin).get((_kinCollectionX.get(_globalFitPosOfSelectedKin).size()-1)))index=(_kinCollectionX.get(_globalFitPosOfSelectedKin).size()-1);
		return index;
		
	}

	
	
	
	
	public void fillLabelsContent()
	{
		for(int i=0; i<10; i++)
		{
			_constantLabelsContent[i]="0";
			_concentrationLabelsContent[i]="0";
			_beforePulseConcentrationLabelsContent[i]="0";
			
		}
	}
	
	

	
	
	
	public String readDiffEqLabelsData (boolean readLabelsContent)
	{
		return _globalFitRK4Solver.readDiffEqLabelsData(readLabelsContent);
	}
	
	
	public Float findConstantName(String s)
	{
		return _globalFitRK4Solver.findConstantName(s);
	}
	
	
	public final int readConcentrationDependencies()
	{
		return _globalFitRK4Solver.readConcentrationDependencies();
	}
	
	
    public float getConcentrationFromDependence(int _functionNumber)
    {
    	return _globalFitRK4Solver.getConcentrationFromDependence(_functionNumber);
    }
	
	
	
	
	
	public void findIndependentConcentrations()
	{
		_globalFitRK4Solver.findIndependentConcentrations();
	}
	
	
	
	
	
	public Float calcDerivFunctionValue(int _functionNumber, int _step, float[] k1, float[] k2, float[] k3, float[] k4)
	{
		return _globalFitRK4Solver.calcDerivFunctionValue(_functionNumber, _step, k1, k2, k3, k4);
	}
	
	
	
	public void fitRK4()//boolean ifRunForTheFirstTime)
	{
		_globalFitRK4Solver.fitRK4();
	}
	
	
	
	
	public int findClosestPosOfZeroLine()
	{
		return _globalFitRK4Solver.findClosestPosOfZeroLine();
	}
	
	
	
	
	public void fillContributionArrays()
	{
		_globalFitRK4Solver.fillContributionArrays();
	}
	
	
	
	
	public void recalcOpticalDensityCollections()
	{
		_globalFitRK4Solver.recalcOpticalDensityCollections();
	}
	
	

	
	public void calcWeightsForCurves()
	{
		_globalFitRK4Solver.calcWeightsForCurves();
	}
	
	
	public int findClosestPosOfBeforeZeroLine()
	{
		return _globalFitRK4Solver.findClosestPosOfBeforeZeroLine();
	}
	
	
	public void calcCurveShifts()
	{
		_globalFitRK4Solver.calcCurveShifts();
	}
	
	

	
	
	
	public float performGlobalFitAbsCoeff(int w, int _nOfMajorIterationsAbs, int _nOfMinorIterationsAbs)
	{
		
		return _globalFiOptimizerAbsCoeffSimple.performGlobalFitAbsCoeff(w, _nOfMajorIterationsAbs, _nOfMinorIterationsAbs);
	}
		

	
	public void performGlobalFitAbsCoeffMatrixOptimization(int w)
	{
		_globalFitOptimizerAbsCoeffMatrix.globalFitAbsCoeffMatrixOptimization(w);
	}
	
	
	public boolean checkForUncertainty()
	{
		return _globalFitOptimizerAbsCoeffMatrix.ifThereMightBeUncertaintyAbsCoeff();
	}
	
	public float performGlobalFitAbsCoeffFindGradientMinimum(int w, int _nOfMajorIterations, int _nOfChances, float _cubeSide, boolean _ifFitAnyway)
	{
		
		return _globalFitOptimizerAbsCoeffGradient.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfMajorIterations, _nOfChances, _cubeSide, _ifFitAnyway);
	}

		
	
	
	public float performGlobalFitConstFindGradientMinimum(int _nOfMajorIterations, int _nOfChances, float _cubeSide)
	{
		return _globalFitOptimizerConstGradient.performGlobalFitConstFindGradientMinimum(_nOfMajorIterations, _nOfChances, _cubeSide);
		
	}
	
	
	
	public float performGlobalFitConstSimpleRange(int _nOfMajorIterationsConst, int _nOfMinorIterationsConst)   // k - n of major iterations;
	{
		return _globalFitOptimizerConstSimple.performGlobalFitConstSimpleRange(_nOfMajorIterationsConst, _nOfMinorIterationsConst);
			
	}
	
		
		
	public float performGlobalFitConstWideRange(int _nOfMajorIterationsConst, int _nOfMinorIterationsConst)   // k - n of major iterations;
	{
		return _globalFitOptimizerConstWideRange.performGlobalFitConstWideRange(_nOfMajorIterationsConst, _nOfMinorIterationsConst);
	}
		
	
		
		

	public String performGlobalFitHyperSpace(int _nOfMinorIterations, float _cubeSide, String Path, String PathGrid, float _limit)
	{
		return _globalFitHyperSpace.performGlobalFitHyperSpace(_nOfMinorIterations, _cubeSide, Path, PathGrid, _limit);
	}
		
		
	public boolean checkIfLocalMinimum(int _cubeResol, float _cubeSide)
	{
		return _globalFitHyperSpace.checkIfLocalMinimum(_cubeResol, _cubeSide);
	}
		
		
		
		
	public float calcGlobalSigma()
	{
		return _globalFitGlobalSigmaCalculator.calcGlobalSigma();
	}
		
		
		
	public float calcGlobalSigmaAtOneWavelength(int w, boolean _ifCalcWithWeight)
	{
		return _globalFitGlobalSigmaCalculator.calcGlobalSigmaAtOneWavelength(w, _ifCalcWithWeight);
	}		
		
		

	
	
	public void createVariablesLimitsArray()
	{
		_variablesLimitsCollection = new  ArrayList<ArrayList<float[]>>(); 
		ArrayList<float[]> _tempArray = new ArrayList<float[]>();
		float[] _temp = new float[6];
		
		for(int i=0; i<4; i++)
		{
			_tempArray = new ArrayList<float[]>();
			
			for(int j=0; j<10; j++)
			{
				_temp = new float[6];
				_temp[0]=1; //variable
				_temp[1]=-1; // min value disabled
				_temp[2]=-1; // max value disabled
				_temp[3]=0; // no dependency
				_tempArray.add(_temp);
			}
			
			_variablesLimitsCollection.add(_tempArray);
		}
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public final void set_nOfEq(int _nOfEq)
	{
		this._nOfEq = _nOfEq;
	}
	
	public final int get_nOfEq()
	{
		return _nOfEq;
	}

	public final void set_nOfEqRel(int _nOfEqRel)
	{
		this._nOfEqRel = _nOfEqRel;
	}
	
	public final int get_nOfEqRel()
	{
		return _nOfEqRel;
	}
	
	
	public final void set_nOfConst(int _nOfConst)
	{
		this._nOfConst = _nOfConst;
	}
	
	public final int get_nOfConst()
	{
		return _nOfConst;
	}

	
	public final void set_nOfConstOpened(int _nOfConstOpened)
	{
		this._nOfConstOpened = _nOfConstOpened;
	}
	
	public final int get_nOfConstOpened()
	{
		return _nOfConstOpened;
	}

	
	public final void set_nOfEqOpened(int _nOfEqOpened)
	{
		this._nOfEqOpened = _nOfEqOpened;
	}
	
	public final int get_nOfEqOpened()
	{
		return _nOfEqOpened;
	}

	
	
	
	
	
	public final void set_gridSizeX(int _gridSizeX)
	{
		this._gridSizeX = _gridSizeX;;
	}

	public final void set_gridSizeY(int _gridSizeY)
	{
		this._gridSizeY = _gridSizeY;
	}


	public final void set_gridSizeXError(int _gridSizeXError)
	{
		this._gridSizeXError = _gridSizeXError;
	}

	public final void set_gridSizeYError(int _gridSizeYError)
	{
		this._gridSizeYError = _gridSizeYError;
	}
	
	public final float get_xScaler()
	{
		return _xScaler;
	}

	public final float get_yScaler()
	{
		return _yScaler;
	}

	
	public final void set_xScaler(float _xScaler)
	{
		this._xScaler = _xScaler;
	}


	public final void set_yScaler(float _yScaler)
	{
		this._yScaler = _yScaler;
	}
	
	public final void set_magPosX(float _magPosX)
	{
		this._magPosX = _magPosX;
	}
	
	
	public final void set_magPosY(float _magPosY)
	{
		this._magPosY = _magPosY;
	}
	
	
	public final void set_magPosXExt(float _magPosXExt)
	{
		this._magPosXExt = _magPosXExt;
	}
	
	
	public final void set_magPosYExt(float _magPosYExt)
	{
		this._magPosYExt = _magPosYExt;
	}
	
	
	
	public final void set_magPosXSlice(float _magPosXSlice)
	{
		this._magPosXSlice = _magPosXSlice;
	}
	
	
	public final void set_magPosYSlice(float _magPosYSlice)
	{
		this._magPosYSlice = _magPosYSlice;
	}
	
	
	public final float get_xScalerError()
	{
		return _xScalerError;
	}

	public final float get_yScalerError()
	{
		return _yScalerError;
	}
	
	public final float get_xScalerIni()
	{
		return _xScalerIni;
	}

	public final float get_yScalerIni()
	{
		return _yScalerIni;
	}

	public final float get_xMin ()
	{
		return _xMin;
	}

	public final float get_yMin ()
	{
		return _yMin;
	}

	public final float get_xMax ()
	{
		return _xMax;
	}

	public final float get_yMax ()
	{

		return _yMax;
	}

	public final float get_magPosX ()
	{
		return _magPosX;
	}

	public final float get_magPosY ()
	{
		return _magPosY;
	}

	public final int get_arraySize ()
	{
		return _arraySize;
	}

	
	
	public final float get_xMinError ()
	{
		return _xMinError;
	}

	public final float get_yMinError ()
	{
		return _yMinError;
	}

	public final float get_xMaxError ()
	{
		return _xMaxError;
	}

	public final float get_yMaxError ()
	{

		return _yMaxError;
	}

	public final float get_magPosXError ()
	{
		return _magPosXError;
	}

	public final float get_magPosYError ()
	{
		return _magPosYError;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public final void set_gridSizeXExt(int _gridSizeXExt)
	{
		this._gridSizeXExt = _gridSizeXExt;
	}

	public final void set_gridSizeYExt(int set_gridSizeYExt)
	{
		this._gridSizeYExt = set_gridSizeYExt;
	}

	
	public final float get_xScalerExt()
	{
		return _xScalerExt;
	}

	public final float get_yScalerExt()
	{
		return _yScalerExt;
	}
	
	public final void set_xScalerExt(float _xScalerExt)
	{
		this._xScalerExt=_xScalerExt;
	}
	
	public final void set_yScalerExt(float _yScalerExt)
	{
		this._yScalerExt=_yScalerExt;
	}

	public final void set_xScalerIniExt(float _xScalerIniExt)
	{
		this._xScalerIniExt=_xScalerIniExt;
	}
	
	public final void set_yScalerIniExt(float _yScalerIniExt)
	{
		this._yScalerIniExt=_yScalerIniExt;
	}
	
	public final float get_xScalerIniExt()
	{
		return _xScalerIniExt;
	}

	public final float get_yScalerIniExt()
	{
		return _yScalerIniExt;
	}

	public final float get_xMinExt ()
	{
		return _xMinExt;
	}

	public final float get_yMinExt ()
	{
		return _yMinExt;
	}

	public final float get_xMaxExt ()
	{
		return _xMaxExt;
	}

	public final float get_yMaxExt ()
	{

		return _yMaxExt;
	}

	public final float get_magPosXExt ()
	{
		return _magPosXExt;
	}

	public final float get_magPosYExt ()
	{
		return _magPosYExt;
	}
	
	
	public final void set_yMaxExt (float _yMaxExt )
	{
		this._yMaxExt =_yMaxExt ;
	}
	
	public final void set_yMinExt (float _yMinExt  )
	{
		this._yMinExt  =_yMinExt  ;
	}	
	
	
	public final void set_xMaxExt (float _xMaxExt )
	{
		this._xMaxExt =_xMaxExt ;
	}	
	
	
	public final void set_xMinExt (float _xMinExt )
	{
		this._xMinExt =_xMinExt ;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public final void set_gridSizeXSlice(int _gridSizeXSlice)
	{
		this._gridSizeXSlice = _gridSizeXSlice;
	}

	public final void set_gridSizeYSlice(int set_gridSizeYSlice)
	{
		this._gridSizeYSlice = set_gridSizeYSlice;
	}

	
	public final float get_xScalerSlice()
	{
		return _xScalerSlice;
	}

	public final float get_yScalerSlice()
	{
		return _yScalerSlice;
	}
	
	public final void set_xScalerSlice(float _xScalerSlice)
	{
		this._xScalerSlice=_xScalerSlice;
	}
	
	public final void set_yScalerSlice(float _yScalerSlice)
	{
		this._yScalerSlice=_yScalerSlice;
	}

	public final void set_xScalerIniSlice(float _xScalerIniSlice)
	{
		this._xScalerIniSlice=_xScalerIniSlice;
	}
	
	public final void set_yScalerIniSlice(float _yScalerIniSlice)
	{
		this._yScalerIniSlice=_yScalerIniSlice;
	}
	
	public final float get_xScalerIniSlice()
	{
		return _xScalerIniSlice;
	}

	public final float get_yScalerIniSlice()
	{
		return _yScalerIniSlice;
	}

	public final float get_xMinSlice ()
	{
		return _xMinSlice;
	}

	public final float get_yMinSlice ()
	{
		return _yMinSlice;
	}

	public final float get_xMaxSlice ()
	{
		return _xMaxSlice;
	}

	public final float get_yMaxSlice ()
	{

		return _yMaxSlice;
	}

	public final float get_magPosXSlice ()
	{
		return _magPosXSlice;
	}

	public final float get_magPosYSlice ()
	{
		return _magPosYSlice;
	}
	
	
	public final void set_yMaxSlice (float _yMaxSlice )
	{
		this._yMaxSlice =_yMaxSlice ;
	}
	
	public final void set_yMinSlice (float _yMinSlice  )
	{
		this._yMinSlice  =_yMinSlice  ;
	}	
	
	
	public final void set_xMaxSlice (float _xMaxSlice )
	{
		this._xMaxSlice =_xMaxSlice ;
	}	
	
	
	public final void set_xMinSlice (float _xMinSlice )
	{
		this._xMinSlice =_xMinSlice ;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public final void set_gridSizeXSigma(int _gridSizeXSigma)
	{
		this._gridSizeXSigma = _gridSizeXSigma;
	}

	public final void set_gridSizeYSigma(int set_gridSizeYSigma)
	{
		this._gridSizeYSigma = set_gridSizeYSigma;
	}

	
	public final float get_xScalerSigma()
	{
		return _xScalerSigma;
	}

	public final float get_yScalerSigma()
	{
		return _yScalerSigma;
	}
	
	public final void set_xScalerSigma(float _xScalerSigma)
	{
		this._xScalerSigma=_xScalerSigma;
	}
	
	public final void set_yScalerSigma(float _yScalerSigma)
	{
		this._yScalerSigma=_yScalerSigma;
	}



	public final float get_xMinSigma ()
	{
		return _xMinSigma;
	}

	public final float get_yMinSigma ()
	{
		return _yMinSigma;
	}

	public final float get_xMaxSigma ()
	{
		return _xMaxSigma;
	}

	public final float get_yMaxSigma ()
	{

		return _yMaxSigma;
	}

	
	
	public final void set_yMaxSigma (float _yMaxSigma )
	{
		this._yMaxSigma =_yMaxSigma ;
	}
	
	public final void set_yMinSigma (float _yMinSigma  )
	{
		this._yMinSigma  =_yMinSigma  ;
	}	
	
	
	public final void set_xMaxSigma (float _xMaxSigma )
	{
		this._xMaxSigma =_xMaxSigma ;
	}	
	
	
	public final void set_xMinSigma (float _xMinSigma )
	{
		this._xMinSigma =_xMinSigma ;
	}	
	
	
	
	
	
	
	
	
	
	
	
	public final ArrayList<String> get_openedRecent ()
	{
		return _openedRecent;
	}
		
	
	public final void set_openedRecent (ArrayList<String> _openedRecent )
	{
		this._openedRecent =_openedRecent ;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
//	public final void get_nOfCreatedCurvePoints ()
//	{
//		return _nOfCreatedCurvePoints;
//	}



	public final int get_markerShiftStep ()
	{
		return _markerShiftStep;
	}
	
	public final int get_markerShiftStepFast ()
	{
		return _markerShiftStepFast;
	}
	
	public final float get_levelShiftStep ()
	{
		return _levelShiftStep;
	}
	
	public final float get_levelShiftStepFast ()
	{
		return _levelShiftStepFast;
	}
	
	public final float get_zeroShiftStep ()
	{
		return _zeroShiftStep;
	}
	
	public final float get_zeroShiftStepFast ()
	{
		return _zeroShiftStepFast;
	}
	
	
	public final void set_levelPosDisplay (float _levelPosDisplay)
	{
		this._levelPosDisplay = _levelPosDisplay;
	}
	
	
	public final void set_levelPosReal (float _levelPosReal)
	{
		this._levelPosReal = _levelPosReal;
	}
	
	public final void set_zeroPosDisplay(float _zeroPosDisplay)
	{
		this._zeroPosDisplay = _zeroPosDisplay;
	}
	
	public final void set_zeroPosReal(float _zeroPosReal)
	{
		this._zeroPosReal = _zeroPosReal;
	}
	
	
	public final void set_xScalerIni(float _xScalerIni)
	{
		this._xScalerIni = _xScalerIni;
	}
	
	public final void set_yScalerIni(float _yScalerIni)
	{
		this._yScalerIni = _yScalerIni;
	}
	
	
	
	
	public final float get_levelPosDisplay()
	{
		return _levelPosDisplay;
	}
	
	public final float get_levelPosReal()
	{
		return _levelPosReal;
	}
	
	public final float get_zeroPosDisplay()
	{
		return _zeroPosDisplay;
	}
	
	public final float get_zeroPosReal()
	{
		return _zeroPosReal;
	}
	
	public final  Rectangle2D.Float get_selectedRect()
	{
		return selectedRect;
	}
	
	public final  Rectangle2D.Float get_beforeZeroSelRect()
	{
		return beforeZeroSelRect;
	}
	
	
	
	
	public final  float get_fitMarkerFirstDisplayX()
	{
		return _fitMarkerFirstDisplayX;
	}
	
	public final  float get_fitMarkerLastDisplayX()
	{
		return _fitMarkerLastDisplayX;
	}

	
	
	
	
	
	public final float get_fitMarkerFirstRealX ()
	{
		return _fitMarkerFirstRealX;
	}

	public final float get_fitMarkerFirstRealY ()
	{
		return _fitMarkerFirstRealY;
	}

	public final float get_fitMarkerLastRealX ()
	{
		return _fitMarkerLastRealX;
	}

	public final float get_fitMarkerLastRealY ()
	{
		return _fitMarkerLastRealY;
	}
	
	
	public final String get_mouseLabelText ()
	{
		return _mouseLabelText;
	}
	

	public final String get_mouseLabelTextExt ()
	{
		return _mouseLabelTextExt;
	}
		
	
	
	public final float get_mouseXExt ()
	{
		return _mouseXExt;
	}
	
	public final float get_mouseYExt ()
	{
		return _mouseYExt;
	}
	
	public final float get_mouseXLabelExt ()
	{
		return _mouseXLabelExt;
	}
	
	public final float get_mouseYLabelExt ()
	{
		return _mouseYLabelExt;
	}
	
	
	
	
	
	
	
	
	public final String get_mouseLabelTextSlice ()
	{
		return _mouseLabelTextSlice;
	}
		
	
	
	public final float get_mouseXSlice ()
	{
		return _mouseXSlice;
	}
	
	public final float get_mouseYSlice ()
	{
		return _mouseYSlice;
	}
	
	public final float get_mouseXLabelSlice ()
	{
		return _mouseXLabelSlice;
	}
	
	public final float get_mouseYLabelSlice ()
	{
		return _mouseYLabelSlice;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public final String get_mouseLabelTextSigma ()
	{
		return _mouseLabelTextSigma;
	}
		
	
	
	public final float get_mouseXSigma ()
	{
		return _mouseXSigma;
	}
	
	public final float get_mouseYSigma ()
	{
		return _mouseYSigma;
	}
	
	public final float get_mouseXLabelSigma ()
	{
		return _mouseXLabelSigma;
	}
	
	public final float get_mouseYLabelSigma ()
	{
		return _mouseYLabelSigma;
	}
	


	public final float[] get_xArray ()
	{
		return _xArray;
	}

	public final float[] get_yArray ()
	{
		return _yArray;
	}


	public final void set_saveEvery(int _saveEvery)
	{
		this._saveEvery = _saveEvery;
	}
	
	
	public final void set_ifNeedToRepaint(boolean _ifNeedToRepaint)
	{
		this._ifNeedToRepaint = _ifNeedToRepaint;
	}
	
	public final boolean get_ifNeedToRepaint()
	{
		return _ifNeedToRepaint;
	}
	
	
	
	public final void set_ifSolvedCurveCollectionLocked(boolean _ifSolvedCurveCollectionLocked)
	{
		this._ifSolvedCurveCollectionLocked = _ifSolvedCurveCollectionLocked;
	}
	
	public final boolean get_ifSolvedCurveCollectionLocked()
	{
		return _ifSolvedCurveCollectionLocked;
	}
	
	public final void set_ifLockGraphPanels(boolean _ifLockGraphPanels)
	{
		this._ifLockGraphPanels = _ifLockGraphPanels;
	}
	
	public final boolean get_ifLockGraphPanels()
	{
		return _ifLockGraphPanels;
	}
	
	
	public final void set_ifShowCreatedCurve(boolean _ifShowCreatedCurve)
	{
		this._ifShowCreatedCurve = _ifShowCreatedCurve;
	}
	
	public final boolean get_ifShowCreatedCurve()
	{
		return _ifShowCreatedCurve;
	}

	
	
	public final void set_fittingColor(Color _fittingColor)
	{
		this._fittingColor = _fittingColor;
	}
	
	public final Color get_fittingColor()
	{
		return _fittingColor;
	}
	
	
	public final void set_globalFitSelectedWavelength(int _globalFitSelectedWavelength)
	{
		this._globalFitSelectedWavelength = _globalFitSelectedWavelength;
	}
	
	public final int get_globalFitSelectedWavelength()
	{
		return _globalFitSelectedWavelength;
	}
	
	
	
	public final void set_createdCurveArray( ArrayList<float[]> _createdCurveArray)
	{
		this._createdCurveArray = _createdCurveArray;
	}
	
	public final  ArrayList<float[]> get_createdCurveArray()
	{
		return _createdCurveArray;
	}
	
	public final  ArrayList<float[]> get_createdCurveArrayError()
	{
		return _createdCurveArrayError;
	}
	
	public final void set_nOfCreatedCurvePoints(int _nOfCreatedCurvePoints)
	{
		this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
	}
	
	public final int get_nOfCreatedCurvePoints()
	{
		return _nOfCreatedCurvePoints;
	}
	
	public final int get_nOfCreatedCurvePointsError()
	{
		return _nOfCreatedCurvePointsError;
	}
	
	
	public final void set_A(float _A)
	{
		this._A = _A;
	}
	
	public final float get_A()
	{
		return _A;
	}
	
	
	public final void set_fitRK4Accuracy(int _fitRK4Accuracy)
	{
		this._fitRK4Accuracy = _fitRK4Accuracy;
	}
	
	public final int get_fitRK4Accuracy()
	{
		return _fitRK4Accuracy;
	}
	
	public final void set_algorithmToBeUsed(String _algorithmToBeUsed)
	{
		this._algorithmToBeUsed = _algorithmToBeUsed;
	}
	
	public final String get_algorithmToBeUsed()
	{
		return _algorithmToBeUsed;
	}
	
	
	
	
	
	public final void set_k(float _k)
	{
		this._k = _k;
	}
	
	public final float get_k()
	{
		return _k;
	}
	
	public final void set_D0(float _D0)
	{
		this._D0 = _D0;
	}
	
	public final float get_D0()
	{
		return _D0;
	}
	

	
	public final void set_cuvetteThickness(float _cuvetteThickness)
	{
		this._cuvetteThickness = _cuvetteThickness;
	}
	
	public final float get_cuvetteThickness()
	{
		return _cuvetteThickness;
	}
	
	public final void set_solvedCurveTimeScale(float _solvedCurveTimeScale)
	{
		this._solvedCurveTimeScale = _solvedCurveTimeScale;
	}
	
	public final float get_solvedCurveTimeScale()
	{
		return _solvedCurveTimeScale;
	}
	
	
	public final void set_constantShiftValue(float _constantShiftValue)
	{
		this._constantShiftValue = _constantShiftValue;
	}
	
	public final float get_constantShiftValue()
	{
		return _constantShiftValue;
	}
	
	
	
	
	
	
	
	public final float get_a10()
	{
		return _a10;
	}
	
	public final float get_a20()
	{
		return _a20;
	}
	
	public final float get_a30()
	{
		return _a30;
	}
	
	public final float get_sigma1()
	{
		return _sigma1;
	}
	
	public final float get_sigma2()
	{
		return _sigma2;
	}
	
	public final float get_sigma3()
	{
		return _sigma3;
	}
	

	public final void set_globalFitXMin(float _globalFitXMin)
	{
		this._globalFitXMin = _globalFitXMin;
	}

	public final float get_globalFitXMin()
	{
		return _globalFitXMin;
	}



	public final void set_nOfMajorIterationsAbs(int _nOfMajorIterationsAbs)
	{
		this._nOfMajorIterationsAbs = _nOfMajorIterationsAbs;
	}

	public final int get_nOfMajorIterationsAbs()
	{
		return _nOfMajorIterationsAbs;
	}
	
	public final void set_nOfMinorIterationsAbs(int _nOfMinorIterationsAbs)
	{
		this._nOfMinorIterationsAbs = _nOfMinorIterationsAbs;
	}

	public final int get_nOfMinorIterationsAbs()
	{
		return _nOfMinorIterationsAbs;
	}
	
	
	public final void set_syncObject(Object _syncObject)
	{
		this._syncObject = _syncObject;
	}

	public final Object get_syncObject()
	{
		return _syncObject;
	}
	
	
	
	
	public final void set_nOfMajorIterationsConst(int _nOfMajorIterationsConst)
	{
		this._nOfMajorIterationsConst = _nOfMajorIterationsConst;
	}

	public final int get_nOfMajorIterationsConst()
	{
		return _nOfMajorIterationsConst;
	}
	
	public final void set_nOfMinorIterationsConst(int _nOfMinorIterationsConst)
	{
		this._nOfMinorIterationsConst = _nOfMinorIterationsConst;
	}

	public final int get_nOfMinorIterationsConst()
	{
		return _nOfMinorIterationsConst;
	}
	
	
	
	
	
	
	
	
	public final void set_slopeValue(int _slopeValue)
	{
		this._slopeValue = _slopeValue;
	}

	public final int get_slopeValue()
	{
		return _slopeValue;
	}
	
	
	public final void set_slopeValueConst(int _slopeValueConst)
	{
		this._slopeValueConst = _slopeValueConst;
	}

	public final int get_slopeValueConst()
	{
		return _slopeValueConst;
	}
	
	
	
	public final void set_nOfIterationsGradientConst(int _nOfIterationsGradientConst)
	{
		this._nOfIterationsGradientConst = _nOfIterationsGradientConst;
	}

	public final int get_nOfIterationsGradientConst()
	{
		return _nOfIterationsGradientConst;
	}
	
	public final void set_nOfIterationsGradientAbs(int _nOfIterationsGradientAbs)
	{
		this._nOfIterationsGradientAbs = _nOfIterationsGradientAbs;
	}

	public final int get_nOfIterationsGradientAbs()
	{
		return _nOfIterationsGradientAbs;
	}
	
	
	public final void set_nOfIterationsGradientConc(int _nOfIterationsGradientConc)
	{
		this._nOfIterationsGradientConc = _nOfIterationsGradientConc;
	}

	public final int get_nOfIterationsGradientConc()
	{
		return _nOfIterationsGradientConc;
	}
	
	
	
	
	
	public final void set_cubeSideAbs(float _cubeSideAbs)
	{
		this._cubeSideAbs = _cubeSideAbs;
	}

	public final float get_cubeSideAbs()
	{
		return _cubeSideAbs;
	}
	
	
	public final void set_directHighLimit(float _directHighLimit)
	{
		this._directHighLimit = _directHighLimit;
	}

	public final float get_directHighLimit()
	{
		return _directHighLimit;
	}
	
	public final void set_directLowLimit(float _directLowLimit)
	{
		this._directLowLimit = _directLowLimit;
	}

	public final float get_directLowLimit()
	{
		return _directLowLimit;
	}
	
	
	
	public final void set_directNOfIterations(int _directNOfIterations)
	{
		this._directNOfIterations = _directNOfIterations;
	}

	public final int get_directNOfIterations()
	{
		return _directNOfIterations;
	}
	
	
	
	public final void set_cubeSideConst(float _cubeSideConst)
	{
		this._cubeSideConst = _cubeSideConst;
	}

	public final float get_cubeSideConst()
	{
		return _cubeSideConst;
	}
	
	public final void set_cubeSideConc(float _cubeSideConc)
	{
		this._cubeSideConc = _cubeSideConc;
	}

	public final float get_cubeSideConc()
	{
		return _cubeSideConc;
	}
	


	public final void set_globalFitXMax(float _globalFitXMax)
	{
		this._globalFitXMax = _globalFitXMax;
	}

	public final float get_globalFitXMax()
	{
		return _globalFitXMax;
	}

	
	public final float get_beforeZeroPosReal()
	{
		return _beforeZeroPosReal;
	}
	
	public final float get_slicePosReal()
	{
		return _slicePosReal;
	}
	
	public final float get_absValueOfDraggedMarker()
	{
		return _absValueOfDraggedMarker;
	}
	
	public final int get_numberOfDraggedRect()
	{
		return _numberOfDraggedRect;
	}
	
	public final int get_numberOfLastClickedRect()
	{
		return _numberOfLastClickedRect;
	}
	
	
	
	
	public final float get_wavelengthValueOfDraggedMarker()
	{
		return _wavelengthValueOfDraggedMarker;
	}
	

	public final void set_globalFitYMin(float _globalFitYMin)
	{
		this._globalFitYMin = _globalFitYMin;
	}

	public final float get_globalFitYMin()
	{
		return _globalFitYMin;
	}


	public final void set_globalFitYMax(float _globalFitYMax)
	{
		this._globalFitYMax = _globalFitYMax;
	}

	public final float get_globalFitYMax()
	{
		return _globalFitYMax;
	}


	public final void set_globalFitPosOfSelectedKin(int _globalFitPosOfSelectedKin)
	{
		this._globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin;
	}

	public final int get_globalFitPosOfSelectedKin()
	{
		return _globalFitPosOfSelectedKin;
	}

	public final void set_kinCollectionX(ArrayList<ArrayList<Float>> _kinCollectionX)
	{
		this._kinCollectionX = _kinCollectionX;
	}

	public final ArrayList<ArrayList<Float>> get_kinCollectionX()
	{
		return _kinCollectionX;
	}
	
	
	
	
	public final void set_markersCollection(ArrayList<ArrayList<Integer>> _markersCollection)
	{
		this._markersCollection = _markersCollection;
	}

	public final ArrayList<ArrayList<Integer>> get_markersCollection()
	{
		return _markersCollection;
	}
	
	
	public final boolean get_ifShowSlice()
	{
		return this._ifShowSlice;
	}

	public final void set_ifShowSlice(boolean _ifShowSlice)
	{
		this._ifShowSlice = _ifShowSlice;
	}

	
	
	public final boolean get_ifShowAbsValueLabel()
	{
		return _ifShowAbsValueLabel;
	}

	public final boolean get_ifShowExtInputPanel()
	{
		return _ifShowExtInputPanel;
	}
	
	public final void set_ifShowExtInputPanel(boolean _ifShowExtInputPanel)
	{
		this._ifShowExtInputPanel=_ifShowExtInputPanel;
	}
	
	
	public final boolean get_ifEnableSmoothMove()
	{
		return _ifEnableSmoothMove;
	}
	
	public final void set_ifEnableSmoothMove(boolean _ifEnableSmoothMove)
	{
		this._ifEnableSmoothMove=_ifEnableSmoothMove;
	}
	
	
	
	public final boolean get_ifKeepAboveZero()
	{
		return _ifKeepAboveZero;
	}
	
	public final void set_ifKeepAboveZero(boolean _ifKeepAboveZero)
	{
		this._ifKeepAboveZero=_ifKeepAboveZero;
	}
	
	
	public final boolean get_ifEnableMoveAllMarkersMode()
	{
		return _ifEnableMoveAllMarkersMode;
	}
	
	public final void set_ifEnableMoveAllMarkersMode(boolean _ifEnableMoveAllMarkersMode)
	{
		this._ifEnableMoveAllMarkersMode=_ifEnableMoveAllMarkersMode;
	}
	
	
	
	public final boolean get_ifContinueToFit()
	{
		return _ifContinueToFit;
	}
	
	public final void set_ifContinueToFit(boolean _ifContinueToFit)
	{
		this._ifContinueToFit=_ifContinueToFit;
	}
	
	
	
	
	public final boolean get_ifAlterAbsCoeff()
	{
		return _ifAlterAbsCoeff;
	}
	
	public final void set_ifAlterAbsCoeff(boolean _ifAlterAbsCoeff)
	{
		this._ifAlterAbsCoeff=_ifAlterAbsCoeff;
	}
	
	public final boolean get_ifAlterConst()
	{
		return _ifAlterConst;
	}
	
	public final void set_ifAlterConst(boolean _ifAlterConst)
	{
		this._ifAlterConst=_ifAlterConst;
	}
	
	public final boolean get_ifAlterInitConcentration()
	{
		return _ifAlterInitConcentration;
	}
	
	public final void set_ifAlterInitConcentration(boolean _ifAlterInitConcentration)
	{
		this._ifAlterInitConcentration=_ifAlterInitConcentration;
	}
	
	
	public final boolean get_ifAlterBurntConcentration()
	{
		return _ifAlterBurntConcentration;
	}
	
	public final void set_ifAlterBurntConcentration(boolean _ifAlterBurntConcentration)
	{
		this._ifAlterBurntConcentration=_ifAlterBurntConcentration;
	}
	

	public final boolean get_ifUseWeight()
	{
		return _ifUseWeight;
	}
	
	public final void set_ifUseWeight(boolean _ifUseWeight)
	{
		this._ifUseWeight=_ifUseWeight;
	}	
	

	
	public final void set_ifCalcCurveShifts(boolean _ifCalcCurveShifts)
	{
		this._ifCalcCurveShifts=_ifCalcCurveShifts;
	}
	
	public final boolean get_ifCalcCurveShifts()
	{
		return _ifCalcCurveShifts;
	}
	
	
	
	public final boolean get_ifUpdateProgressBars()
	{
		return _ifUpdateProgressBars;
	}
	
	
	public final void set_ifUpdateProgressBars(boolean _ifUpdateProgressBars)
	{
		this._ifUpdateProgressBars=_ifUpdateProgressBars;
	}
	
	
	
	public final boolean get_ifEnableCurveBounds()
	{
		return _ifEnableCurveBounds;
	}
	
	
	public final void set_ifEnableCurveBounds(boolean _ifEnableCurveBounds)
	{
		this._ifEnableCurveBounds=_ifEnableCurveBounds;
	}
	
	
	
	public final boolean get_ifStatusFine()
	{
		return _ifStatusFine;
	}
	
	
	public final void set_ifStatusFine(boolean _ifStatusFine)
	{
		this._ifStatusFine=_ifStatusFine;
	}
	
	
	public final boolean get_ifCalcSigmaWithWeights()
	{
		return _ifCalcSigmaWithWeights;
	}
	
	
	public final void set_ifCalcSigmaWithWeights(boolean _ifCalcSigmaWithWeights)
	{
		this._ifCalcSigmaWithWeights=_ifCalcSigmaWithWeights;
	}
	
	public final boolean get_ifCalcContributions()
	{
		return _ifCalcContributions;
	}
	
	
	public final void set_ifCalcContributions(boolean _ifCalcContributions)
	{
		this._ifCalcContributions=_ifCalcContributions;
	}
	
	
	public final boolean get_ifShowBackgroundCurves()
	{
		return _ifShowBackgroundCurves;
	}
	
	
	public final void set_ifShowBackgroundCurves(boolean _ifShowBackgroundCurves)
	{
		this._ifShowBackgroundCurves=_ifShowBackgroundCurves;
	}
	
	
	public final boolean get_ifShowErrorExt()
	{
		return _ifShowErrorExt;
	}
	
	
	public final void set_ifShowErrorExt(boolean _ifShowErrorExt)
	{
		this._ifShowErrorExt=_ifShowErrorExt;
	}
	
	public final void set_ifShowErrorExtWithBars(boolean _ifShowErrorExtWithBars)
	{
		this._ifShowErrorExtWithBars=_ifShowErrorExtWithBars;
	}
	
	
	public final boolean get_ifShowErrorExtWithBars()
	{
		return _ifShowErrorExtWithBars;
	}
	
	public final boolean get_ifShowContributionsSpec()
	{
		return _ifShowContributionsSpec;
	}
	
	
	public final void set_ifShowContributionsSpec(boolean _ifShowContributionsSpec)
	{
		this._ifShowContributionsSpec=_ifShowContributionsSpec;
	}
	
	
	
	public final boolean get_ifShowCalcSpec()
	{
		return _ifShowCalcSpec;
	}
	
	
	public final void set_ifShowCalcSpec(boolean _ifShowCalcSpec)
	{
		this._ifShowCalcSpec=_ifShowCalcSpec;
	}
	
	
	public final boolean get_ifShowTransientSpec()
	{
		return _ifShowTransientSpec;
	}
	
	
	public final void set_ifShowTransientSpec(boolean _ifShowTransientSpec)
	{
		this._ifShowTransientSpec=_ifShowTransientSpec;
	}
	
	

	
	
	
	public final void set_slowBarProgress(float _slowBarProgress)
	{
		this._slowBarProgress = _slowBarProgress;
	}

	public final float get_slowBarProgress()
	{
		return _slowBarProgress;
	}
	
	
	public final void set_fastBarProgress(float _fastBarProgress)
	{
		this._fastBarProgress = _fastBarProgress;
	}

	public final float get_fastBarProgress()
	{
		return _fastBarProgress;
	}
	
	public final float get_globalSigma()
	{
		return _globalSigma;
	}
	
	
	
	
	public final void set_kinCollectionY(ArrayList<ArrayList<Float>> _kinCollectionY)
	{
		this._kinCollectionY = _kinCollectionY;
	}

	public final ArrayList<ArrayList<Float>> get_kinCollectionY()
	{
		return _kinCollectionY;
	}



	public final void set_solvedCurveCollectionX(ArrayList<Float> _solvedCurveCollectionX)
	{
		this._solvedCurveCollectionX = _solvedCurveCollectionX;
	}

	public final ArrayList<Float> get_solvedCurveCollectionX()
	{
		return _solvedCurveCollectionX;
	}

	public final void set_solvedCurveCollectionY(ArrayList<ArrayList<Float>> _solvedCurveCollectionY)
	{
		this._solvedCurveCollectionY = _solvedCurveCollectionY;
	}

	public final ArrayList<ArrayList<Float>> get_solvedCurveCollectionY()
	{
		return _solvedCurveCollectionY;
	}

	
	public final void set_solvedCurveErrorCollectionY(ArrayList<ArrayList<Float>> _solvedCurveErrorCollectionY)
	{
		this._solvedCurveErrorCollectionY = _solvedCurveErrorCollectionY;
	}

	public final ArrayList<ArrayList<Float>> get_solvedCurveErrorCollectionY()
	{
		return _solvedCurveErrorCollectionY;
	}

	
	public final void set_solvedCurveContributionCollectionY(ArrayList<ArrayList<ArrayList<Float>>> _solvedCurveContributionCollectionY)
	{
		this._solvedCurveContributionCollectionY = _solvedCurveContributionCollectionY;
	}

	public final ArrayList<ArrayList<ArrayList<Float>>> get_solvedCurveContributionCollectionY()
	{
		return _solvedCurveContributionCollectionY;
	}
	
	
	
	public final void set_solvedConcentrationCollectionY(ArrayList<ArrayList<Float>> _solvedConcentrationCollectionY)
	{
		this._solvedConcentrationCollectionY = _solvedConcentrationCollectionY;
	}

	public final ArrayList<ArrayList<Float>> get_solvedConcentrationCollectionY()
	{
		return _solvedConcentrationCollectionY;
	}
	
	
	
	
	
	public final void set_eqLabelsContent(String[] _eqLabelsContent)
	{
		this._eqLabelsContent = _eqLabelsContent;
	}

	public final String[] get_eqLabelsContent()
	{
		return _eqLabelsContent;
	}
	
	
	public final void set_concEqLabelsContent(String[] _concEqLabelsContent)
	{
		this._eqLabelsContent = _concEqLabelsContent;
	}

	public final String[] get_concEqLabelsContent()
	{
		return _concEqLabelsContent;
	}
	
	
	
	
	
	public final void set_xSNamesArray(String[] _xSNamesArray)
	{
		this._xSNamesArray = _xSNamesArray;
	}

	public final String[] get_xSNamesArray()
	{
		return _xSNamesArray;
	}
	
	
	
	
	
	
	
	
	
	
	
	public final void set_constantLabelsContent(String[] _constantLabelsContent)
	{
		this._constantLabelsContent = _constantLabelsContent;
	}

	public final String[] get_constantLabelsContent()
	{
		return _constantLabelsContent;
	}
	
	public final void set_concentrationLabelsContent(String[] _concentrationLabelsContent)
	{
		this._concentrationLabelsContent = _concentrationLabelsContent;
	}

	public final String[] get_concentrationLabelsContent()
	{
		return _concentrationLabelsContent;
	}
	
	public final void set_beforePulseConcentrationLabelsContent(String[] _beforePulseConcentrationLabelsContent)
	{
		this._beforePulseConcentrationLabelsContent = _beforePulseConcentrationLabelsContent;
	}

	public final String[] get_beforePulseConcentrationLabelsContent()
	{
		return _beforePulseConcentrationLabelsContent;
	}
	
	public final void set_constantValues(float[] _constantValues)
	{
		this._constantValues = _constantValues;
	}

	public final float[] get_constantValues()
	{
		return _constantValues;
	}
	
	
	public final float[] get_constantErrorValues()
	{
		return _constantErrorValues;
	}
	
	public final void set_beforePulseConcentrationValues(float[] _beforePulseConcentrationValues)
	{
		this._beforePulseConcentrationValues = _beforePulseConcentrationValues;
	}

	public final float[] get_beforePulseConcentrationValues()
	{
		return _beforePulseConcentrationValues;
	}
	
	
	
	
	
	
	
	
	public final void set_concentrationValues(float[] _concentrationValues)
	{
		this._concentrationValues = _concentrationValues;
	}

	public final float[] get_concentrationValues()
	{
		return _concentrationValues;
	}
	
	public final void set_derivativeFuncValues(float[] _derivativeFuncValues)
	{
		this._derivativeFuncValues = _derivativeFuncValues;
	}

	public final float[] get_derivativeFuncValues()
	{
		return _derivativeFuncValues;
	}
	
	public final ArrayList<Float> get_weightCollection()
	{
		return _weightCollection;
	}
	
	
	
	
	public final void set_funcCollection(ArrayList<ArrayList<ArrayList<Float>>> _funcCollection)
	{
		this._funcCollection = _funcCollection;
	}

	public final ArrayList<ArrayList<ArrayList<Float>>> get_funcCollection()
	{
		return _funcCollection;
	}
	
	
	
	
	
	

	public final void set_globalFitPosOfSelectedSpec(int _globalFitPosOfSelectedSpec)
	{
		this._globalFitPosOfSelectedSpec = _globalFitPosOfSelectedSpec;
	}

	public final int get_globalFitPosOfSelectedSpec()
	{
		return _globalFitPosOfSelectedSpec;
	}

	
	
	
	
	public final void set_posToStartFittingWith(int _posToStartFittingWith)
	{
		this._posToStartFittingWith = _posToStartFittingWith;
	}

	public final int get_posToStartFittingWith()
	{
		return _posToStartFittingWith;
	}
	
	
	
	
	
	
	
	public final void set_nOfPointsSmoothMove(int _nOfPointsSmoothMove)
	{
		this._nOfPointsSmoothMove = _nOfPointsSmoothMove;
	}

	public final int get_nOfPointsSmoothMove()
	{
		return _nOfPointsSmoothMove;
	}
	
	
	
	public final void set_currentDirectoryOpen(File file)
	{
		this.file = file;
	}

	public final File get_currentDirectoryOpen()
	{
		return file;
	}
	
	public final void set_currentDirectorySave(File saveName)
	{
		this.saveName = saveName;
	}

	public final File get_currentDirectorySave()
	{
		return saveName;
	}
	
	

	public final void set_xSAbsSpecCollectionYFiltered(ArrayList<ArrayList<Float>> _xSAbsSpecCollectionYFiltered)
	{
		this._xSAbsSpecCollectionYFiltered = _xSAbsSpecCollectionYFiltered;
	}

	public final ArrayList<ArrayList<Float>> get_xSAbsSpecCollectionYFiltered()
	{
		return _xSAbsSpecCollectionYFiltered;
	}
	
	
	public final void set_xSAbsSpecErrorCollection(ArrayList<ArrayList<Float>> _xSAbsSpecErrorCollection)
	{
		this._xSAbsSpecErrorCollection = _xSAbsSpecErrorCollection;
	}

	public final ArrayList<ArrayList<Float>> get_xSAbsSpecErrorCollection()
	{
		return _xSAbsSpecErrorCollection;
	}

	public final void set_concFuncCollection(ArrayList<ArrayList<ArrayList<Float>>> _concFuncCollection)
	{
		this._concFuncCollection = _concFuncCollection;
	}

	public final ArrayList<ArrayList<ArrayList<Float>>> get_concFuncCollection()
	{
		return _concFuncCollection;
	}
	

	public final void set_availWaveCollection(ArrayList<Float> _availWaveCollection)
	{
		this._availWaveCollection = _availWaveCollection;
	}

	public final ArrayList<Float> get_availWaveCollection()
	{
		return _availWaveCollection;
	}

	public final void set_curveShiftCollection(ArrayList<Float> _curveShiftCollection)
	{
		this._curveShiftCollection = _curveShiftCollection;
	}

	public final ArrayList<Float> get_curveShiftCollection()
	{
		return _curveShiftCollection;
	}

	
	
	
	
	
	
	public final ArrayList<ArrayList<float[]>> get_variablesLimitsCollection()
	{
		return _variablesLimitsCollection;
	}
	
	public final void set_sigmaCollection(ArrayList<Float> _sigmaCollection)
	{
		this._sigmaCollection = _sigmaCollection;
	}

	public final ArrayList<Float> get_sigmaCollection()
	{
		return _sigmaCollection;
	}
	
	
	public final int get_globalFitPosOfCurrentTimePoint()
	{
		return this._globalFitPosOfCurrentTimePoint;
	}
	
	public final void set_globalFitPosOfCurrentTimePoint(final int _globalFitPosOfCurrentTimePoint)
	{
		this._globalFitPosOfCurrentTimePoint=_globalFitPosOfCurrentTimePoint;
	}
	
	public final int get_timeStep()
	{
		return this._timeStep;
	}
	
	public final void set_timeStep(final int _timeStep)
	{
		this._timeStep=_timeStep;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// slicePanel MouseEvents
	
	
	
	public void slicePanelMouseMoved(MouseEvent e)
	{
		_globalFitMouseHandler.slicePanelMouseMoved(e);
	}	
	
	
	
	
	// sigmaPanel Mouse Events
	
	
	
	
   public void sigmaPanelMouseMoved(MouseEvent e)
   {
	   _globalFitMouseHandler.sigmaPanelMouseMoved(e);
   }
	
	
	
	// extGraphPanel Mouse events
	
	
	public void extGraphPanelCalcRectanglesPositions()
	{
		_globalFitMouseHandler.extGraphPanelCalcRectanglesPositions();
	}
	
	
	public Cursor get_cursorExt()
	{
		return _globalFitMouseHandler.get_cursorExt();
	}


	
   public void extGraphPanelMouseMoved(MouseEvent e)
   {
	   _globalFitMouseHandler.extGraphPanelMouseMoved(e);
   }
	   
	   
	   public void extGraphPanelMousePressed(MouseEvent e) {
		   
		   _globalFitMouseHandler.extGraphPanelMousePressed(e);
	   }

	    public void extGraphPanelMouseReleased(MouseEvent e) 
	    {
	    	_globalFitMouseHandler.extGraphPanelMouseReleased(e);
	    }

	    
	    public void extGraphPanelMouseDragged(MouseEvent e) {
	    	
	    	_globalFitMouseHandler.extGraphPanelMouseDragged(e);
	    }
	    
	    
	    
	
	
	
	
	 // globalFitGraphPanel Mouse events
		
		
		public void calcRectanglesPositions()
		{
			_globalFitMouseHandler.calcRectanglesPositions();
		}
		
		
		public Cursor get_cursor()
		{
			return _globalFitMouseHandler.get_cursor();
		}
		
		

			
		
		   public void mouseMoved(MouseEvent e)
		   {
			   _globalFitMouseHandler.mouseMoved(e);
		   }
		   

		   
		   public void mousePressed(MouseEvent e) {
			   
			   _globalFitMouseHandler.mousePressed(e);
		   }

		   
		   public void resetGraphPanelParameters()
		   {
			   _globalFitMouseHandler.resetGraphPanelParameters();
		   }
		   
		   
		    public void mouseReleased(MouseEvent e) 
		    {
		    	_globalFitMouseHandler.mouseReleased(e);
		    }

		    
		    public void mouseDragged(MouseEvent e) {
		    	
		    	_globalFitMouseHandler.mouseDragged(e);
		    }	
	

	
	
	public void checkmarkers()
	{
		_globalFitMouseHandler.checkmarkers();
	}
	
	
	
	
	
	
}
