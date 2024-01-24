package math_packadge;

import interface_packadge.GlobalFitInterface;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.SwingWorker;

import controller_packadge.GlobalFitController;



public class GlobalFitOptimizationInBackground extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */
	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;
	GlobalFitDirect _globalFitDirect;
	GlobalFitOptimizerInitConcGradient _globalFitOptimizerInitConcGradient;
	GlobalFitOptimizerAdvancedGradient _globalFitOptimizerAdvancedGradient;
	
	float progressSlow = 0f;
    float progressFast = 0f;
    int _length=0;
   
    int _temp=-1;
	int _tempConc = 1;
    
	int _nOfIterationsGradientConst;
	int _nOfChancesConst;
	float _cubeSideConst;
	float _cubeSideAbs;
	public int _nOfChancesAbs;
	int _nOfIterationsGradientAbs;
	
	
	
	
	public GlobalFitOptimizationInBackground(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
	
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_globalFitController1 = _globalFitController;
	
		assignVariables();
	}
	

	
	public void assignVariables()
	{
		_globalFitDirect = new GlobalFitDirect(_globalFitMath1);
		_globalFitOptimizerInitConcGradient = new GlobalFitOptimizerInitConcGradient(_globalFitMath1, _globalFitController1);
		_globalFitOptimizerAdvancedGradient = new GlobalFitOptimizerAdvancedGradient(_globalFitMath1, _globalFitController1);
		
		_nOfIterationsGradientConst = _globalFitMath1.get_nOfIterationsGradientConst();
		_nOfChancesConst = Math.round((float)_nOfIterationsGradientConst/2f);
		_cubeSideConst = _globalFitMath1.get_cubeSideConst();
		_nOfIterationsGradientAbs = _globalFitMath1.get_nOfIterationsGradientAbs();
		_cubeSideAbs = _globalFitMath1.get_cubeSideAbs();
		_nOfChancesAbs = Math.round((float)_nOfIterationsGradientAbs/2f);
	}
	
	
	
	@Override
    public Void doInBackground() 
    {
    	doInBackgroundInitialSettings();
             
            while (progressSlow < 100) 
            {

            	progressFast=0;
				_globalFitMath1.set_fastBarProgress(Math.round(progressFast));
				
			
				algorithmDefining();
				
				//progressSlow=110;
				//_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
				//_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(0); //w - wavelength;


				_globalFitController1.globalFitRefreshAllOutPuts(true);

				takeCareOfProgress();
				
				//break;
				if(progressSlow>=100) break;
            }
            

            if(!checkForUncertainty())
                calculateErrors();
            CompleteProgress();
            _globalFitController1.globalFitRefreshErrorLabels();
            _globalFitController1.extGraphPanelRefresh();
            //_globalFitController1.graphPanelRefresh();
            
            return null;
     }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done()
    {
    	globalFitDone();
    }
    
    
    public void doInBackgroundInitialSettings()
    {
    	progressSlow = 0f;
        progressFast = 0f;

         setProgress(0);
        _globalFitMath1.set_slowBarProgress(progressSlow);
        
		if(_globalFitMath1.get_ifAlterAbsCoeff())
			_length += _globalFitMath1.get_availWaveCollection().size();
		if(_globalFitMath1.get_ifAlterConst())
			_length +=_globalFitMath1.get_nOfConst();
    }
    
    public void takeCareOfProgress()
    {
		if(!_globalFitMath1.get_ifContinueToFit() && !_globalFitDirect.isBusy())
		{
			CompleteProgress();
		}
    }
    
    
    public boolean checkForUncertainty()
    {
    	boolean _result = false;
    	
    	if(_globalFitMath1._ifAlterAbsCoeff)
    		if(_globalFitMath1.checkForUncertainty())
    		{
    			//_globalFitInterface1._globalFitMainInterface._status.setForeground(Color.red);
    			//_globalFitInterface1._globalFitMainInterface._status.setText("WARNING! UNCERTAINTY for absorbance coefficiens!");
    			//_globalFitInterface1._globalFitMainInterface._status.setForeground(Color.black);
    			_globalFitController1.warningMessage("WARNING! UNCERTAINTY for absorbance coefficiens!" + "\n" + "Try to fix some parameters.");
    			_result = true;
    		}
    	
    	return _result;

    }
    
    public void calculateErrors()
    {
    	_globalFitInterface1._globalFitMainInterface._status.setText("Calculating Errors. Please wait.");
		_globalFitController1.setWaitingCursorForAllWindows(true);
		
    	_globalFitMath1._globalFitErrorCalculator.calcErrorsForVariables();
    	
    	_globalFitInterface1._globalFitMainInterface._status.setText("Done.");
    }
    
    public void increaseProgressSlow(float _inc)
    {
    	progressSlow += _inc;
        _globalFitMath1.set_slowBarProgress(progressSlow);
        setProgress(Math.min(Math.round(progressSlow), 100));
    }
    
    
    public void increaseProgresFast(float _inc)
    {
    	_temp = _temp*(-1);
    	setProgress(Math.min(Math.round(progressSlow+1+_temp), 100));
		progressFast+= _inc;
		_globalFitMath1.set_fastBarProgress(Math.round(progressFast));
    }
    
    
    
    public void algorithmDefining()
    {
		if((!_globalFitMath1.get_ifAlterAbsCoeff())&(!_globalFitMath1.get_ifAlterConst())&(!_globalFitMath1.get_ifAlterInitConcentration()))
			increaseProgressSlow(110);
    	
    	if((_globalFitMath1.get_ifAlterAbsCoeff())&(!_globalFitMath1.get_ifAlterConst())&(!_globalFitMath1.get_ifAlterInitConcentration()))
			performGlobalFitLightAlgorithmForAbsCoeff();
		
		if((!_globalFitMath1.get_ifAlterAbsCoeff())&(_globalFitMath1.get_ifAlterConst())&(!_globalFitMath1.get_ifAlterInitConcentration()))
			performGlobalFitLightAlgorithmForConst();
		
		if((_globalFitMath1.get_ifAlterAbsCoeff())&(_globalFitMath1.get_ifAlterConst())&(!_globalFitMath1.get_ifAlterInitConcentration()))
		{
			if(_globalFitMath1.get_algorithmToBeUsed().equals("light"))
			{
				performGlobalFitLightAlgorithmForAbsCoeffAndConst();
			}
			if(_globalFitMath1.get_algorithmToBeUsed().equals("advanced gradient"))
			{
				performGlobalFitAdvancedAlgorithmGradient();
			}
			if(_globalFitMath1.get_algorithmToBeUsed().equals("advanced direct"))
			{
				performGlobalFitAdvancedAlgorithmDirect();
			}
		}
		
		if(_globalFitMath1.get_ifAlterInitConcentration())
		{
			performGlobalFitInitialConcentrationsGradient();
		}
    }

    
    /*-----------------------------------------------------------------*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*-----------------------------------------------------------------*/     

    
    public void performGlobalFitLightAlgorithmForAbsCoeff()
    {
       	System.out.println("**************Performing light algorithm for abs coeff******************");
        
		_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
		
       	
       	for(int w=0; w<_globalFitMath1.get_availWaveCollection().size(); w++)
			{
					if(_globalFitMath1.get_ifContinueToFit())
					{
						//_globalFitMath1.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterationsGradientAbs, _nOfChancesAbs, _cubeSideAbs, false);
						_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;
						increaseProgresFast(Math.round(100/_length));
					}
				
			}
			_globalFitMath1.fitRK4();
			increaseProgressSlow(110);
    }
    
    
    
    
    public void performGlobalFitLightAlgorithmForConst()
    {
			
    	System.out.println("**************Performing light algorithm for const******************");
    	
    	if(_globalFitMath1.get_ifContinueToFit())
				_globalFitMath1.performGlobalFitConstFindGradientMinimum(_nOfIterationsGradientConst, _nOfChancesConst, _cubeSideConst);
    	increaseProgressSlow(5);
   			
    }
    
    
    
    
    public void performGlobalFitLightAlgorithmForAbsCoeffAndConst()
    {
       	System.out.println("**************Performing light algorithm for abs coeff and const******************");
        
       	_globalFitMath1._globalFitOptimizerAbsCoeffMatrix.restart();
       	
       	for(int w=0; w<_globalFitMath1.get_availWaveCollection().size(); w++)
			{
					if(_globalFitMath1.get_ifContinueToFit())
					{
						//_globalFitMath1.performGlobalFitAbsCoeffFindGradientMinimum(w, _nOfIterationsGradientAbs, _nOfChancesAbs, _cubeSideAbs, false);
						_globalFitMath1.performGlobalFitAbsCoeffMatrixOptimization(w); //w - wavelength;
						increaseProgresFast(Math.round(100/_length));
					}
				
			}
			_globalFitMath1.fitRK4();
			
			
	    if(_globalFitMath1.get_ifContinueToFit())
			_globalFitMath1.performGlobalFitConstFindGradientMinimum(_nOfIterationsGradientConst, _nOfChancesConst, _cubeSideConst);
		 			
	    increaseProgressSlow(5);
			
    }
    
    
    public void performGlobalFitAdvancedAlgorithmDirect()
    {
    	if(!_globalFitDirect.isBusy())
    	{
    		_globalFitDirect.start();
    		_length = _globalFitMath1.get_directNOfIterations();
    		_globalFitInterface1._globalFitMainInterface._status.setText("Performing direct algothirm. Please wait...");
    	}
    	else
    	{
    		if(_globalFitMath1.get_ifContinueToFit())
    		{
    			_globalFitDirect.iteration();
    			increaseProgressSlow(100f/(float)_length);
    		}
    		else
    		{
    			_globalFitDirect.finish();
    			_globalFitInterface1._globalFitMainInterface._status.setText("Terminated by user.");
    			System.out.println("Terminated by user.");
      			this.CompleteProgress();
    		}
    		
    		if(_globalFitMath1.get_ifContinueToFit() && _globalFitDirect.isBusy())
    		if(progressSlow>=99.5)
    		{
    			_globalFitDirect.finish();
    			//_globalFitDirect.printRectCollectionLinesDataOutDots("c:/Examples/Testing schemes/RectCollectionOutputDots.txt");
    			//_globalFitDirect.printRectCollectionLinesDataOutLines2D("c:/Examples/RectCollectionOutputLines.txt");
    			//_globalFitDirect.printRectCollectionLinesDistAndFunc("c:/Examples/RectCollectionDistAndFunc.txt");
    			_globalFitInterface1._globalFitMainInterface._status.setText("Finished.");
      			System.out.println("Finished.");
    			this.CompleteProgress();
    		}
    	}
    }
    
    /*-----------------------------------------------------------------*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*-----------------------------------------------------------------*/
    
    

    
    
    public void CompleteProgress()
    {
			progressSlow = 100;
			_globalFitMath1.set_slowBarProgress(Math.round(progressSlow));
			setProgress(Math.min(Math.round(progressSlow), 100));
			_globalFitController1.setWaitingCursorForAllWindows(false);
			_globalFitInterface1._globalFitMainInterface._status.setText("Operation completed.");
    }
          
    
    
    
    /*-----------------------------------------------------------------*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*-----------------------------------------------------------------*/
    
    
     



    
    public void globalFitInitialConcentrationsGradientUpdateProgressMinor()
    {
		_tempConc = _tempConc*(-1);
		setProgress(Math.min(Math.round((progressSlow+1+_tempConc)), 100));
		progressFast+= 100f/(float)_globalFitOptimizerInitConcGradient._lengthConc;
		_globalFitMath1.set_fastBarProgress(progressFast);
    }
    
    public void globalFitInitialConcentrationsGradientUpdateProgressMajor()
    {
    	progressFast = 0;
    	progressSlow+= 100f/(float)_globalFitOptimizerInitConcGradient._nOfIterationsGradientConc;
		setProgress(Math.min(Math.round(progressSlow), 100));
		_globalFitMath1.set_fastBarProgress(progressFast);
		_globalFitMath1.set_slowBarProgress(progressSlow);
    }
    

    

    
    public void performGlobalFitInitialConcentrationsGradient()
    {
	

		System.out.println("********Started altering concentration********");
		_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientSetIntialValues();		
	
		for(int k=0; (k<_globalFitOptimizerInitConcGradient._nOfIterationsGradientConc)&(_globalFitMath1.get_ifContinueToFit()); k++)
		{
				if(k>0)
				{
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientResetValues();
				}
			
				for(int j=0; (j<Math.pow((_globalFitOptimizerInitConcGradient._cubeResolutionConc+1),_globalFitOptimizerInitConcGradient._nOfAlterableValuesConc)&(_globalFitMath1.get_ifContinueToFit())); j++)
				{
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientSetTrialValues();
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientMoveAlongGrid();
					_globalFitMath1.fitRK4();
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientCalcConstAndAbsCoef(5);
					_globalFitOptimizerInitConcGradient._globalSigmaConc = _globalFitMath1.calcGlobalSigma();
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientCheckForBetterSigma();	
					_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientBackingUpData();
					_globalFitController1.globalFitRefreshAllOutPuts(true);
					globalFitInitialConcentrationsGradientUpdateProgressMinor();
				}
				globalFitInitialConcentrationsGradientUpdateProgressMajor();

				
				if(Math.abs(_globalFitOptimizerInitConcGradient._globalSigmaLastConc-_globalFitOptimizerInitConcGradient._minGlobalSigmaConc)<0.0001f)
				{
					_globalFitOptimizerInitConcGradient._chanceConc++;
					if(_globalFitOptimizerInitConcGradient._chanceConc>_globalFitOptimizerInitConcGradient._nOfChancesConc)
					{
						k = _globalFitOptimizerInitConcGradient._nOfIterationsGradientConc;
						CompleteProgress();
						System.out.println("Have reached accuracy limit!!!!!!");
					}
				}


				
		}	
		_globalFitOptimizerInitConcGradient.globalFitInitialConcentrationsGradientPlacingDataBack();

		CompleteProgress();
    
    }


    /*-----------------------------------------------------------------*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*--                                                             --*/
    /*-----------------------------------------------------------------*/

    
    

    
    public void globalFitAdvancedAlgorithmGradientUpdateProgressMinor()
    {
		_temp = _temp*(-1);
		progressFast+= 100f/(float)_globalFitOptimizerAdvancedGradient._lengthConst;
		_globalFitMath1.set_fastBarProgress(Math.round(progressFast));
		setProgress(Math.min(Math.round(progressSlow+1+_temp), 100));
    }
    

    
    public void globalFitAdvancedAlgorithmGradientUpdateProgressMajor()
    {
		_temp = _temp*(-1);
		progressFast = 0;
		progressSlow += 100f/(float)_globalFitOptimizerAdvancedGradient._nOfIterationsGradientConst;
		_globalFitMath1.set_fastBarProgress(Math.round(progressFast));
		_globalFitMath1.set_slowBarProgress(Math.round(progressSlow));
		setProgress(Math.min(Math.round(progressSlow+1+_temp), 100));
    }
    
 
    
    
    public void performGlobalFitAdvancedAlgorithmGradient()
    {
    	System.out.println("**************Performing advanced algorithm gradient descent******************");
     	
    	_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientSetInitialValues();

    		for(int k=0; ((k<_globalFitOptimizerAdvancedGradient._nOfIterationsGradientConst)&(_globalFitMath1.get_ifContinueToFit())); k++)
			{
				if(k>0)
				{
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientResetInitialValues();
				}

				for(int j=0; (j<Math.pow((_globalFitOptimizerAdvancedGradient._cubeResolutionConst+1),_globalFitOptimizerAdvancedGradient._nOfAlterableValues)&(_globalFitMath1.get_ifContinueToFit())); j++)
				{
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientSetTrialValues();
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientMoveAlongGrid();
					_globalFitMath1.fitRK4();
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientCalcBestAbsCoeff();     	
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientCalcGlobalSigma();
					_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientCheckForBetterSigma();
					globalFitAdvancedAlgorithmGradientUpdateProgressMinor();
				}
				_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientRememberBestValues();
				globalFitAdvancedAlgorithmGradientUpdateProgressMajor();

				_globalFitOptimizerAdvancedGradient.globalFitAdvancedAlgorithmGradientDemonstrationalRedraw();

				if(k!=_globalFitOptimizerAdvancedGradient._nOfIterationsGradientConst)
				{
					_globalFitController1.globalFitRefreshAllOutPuts(true);
				}
				
				
				if(Math.abs(_globalFitOptimizerAdvancedGradient._globalSigmaLastConst-_globalFitOptimizerAdvancedGradient._minGlobalSigmaConst)<0.0001f)
				{
					_globalFitOptimizerAdvancedGradient._chanceConst++;
					if(_globalFitOptimizerAdvancedGradient._chanceConst>_globalFitOptimizerAdvancedGradient._nOfChancesConst)
					{
						k = _globalFitOptimizerAdvancedGradient._nOfIterationsGradientConst;
						CompleteProgress();
						System.out.println("Have reached accuracy limit!!!!!!");
					}
				}

			}
			CompleteProgress();

    }
    
    
    
    
    public void globalFitDone()
    {
        Toolkit.getDefaultToolkit().beep();
			_globalFitInterface1._globalFitFittingFrame._progressPanel.remove(_globalFitInterface1._globalFitFittingFrame._stopFittingButton);
			_globalFitInterface1._globalFitFittingFrame._progressPanel.add(_globalFitInterface1._globalFitFittingFrame._startFittingButton);
			_globalFitInterface1._globalFitFittingFrame._progressPanel.repaint();
			
			_globalFitMath1.set_ifContinueToFit(false);
			_globalFitController1.lockGraphPanels(false);  //enable graph panels to repaint
			
			//fitRK4();
			
			_globalFitController1.fillSetLimitsTableData();
   	
    }
    
    
    
    

}    
