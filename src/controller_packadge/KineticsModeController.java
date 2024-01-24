package controller_packadge;



import interface_packadge.KineticsModeInterface;

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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;

import math_packadge.KineticsModeMath;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class KineticsModeController {//extends JPanel implements ActionListener{
	
	
	KineticsModeInterface _kineticsModeInterface1 = new KineticsModeInterface();
	KineticsModeMath _kineticsModeMath1 = new KineticsModeMath();
	KineticsModeMovingAdapter ma = new KineticsModeMovingAdapter();
	
	
	
	
	
	public KineticsModeController(KineticsModeInterface _kineticsModeInterface, KineticsModeMath _kineticsModeMath)
	{
		
		
		_kineticsModeInterface1 = _kineticsModeInterface;
		_kineticsModeMath1 = _kineticsModeMath;
		
		//showDefaultGrid();
		addListeners();
		add1expFitListeners();
		add2OrderFitListeners();
		add2OrderProductFitListeners();
		
	}
	
	

	/*public void showDefaultGrid()
	{
		_kineticsModeMath1.setDefaultGrid();
		graphPanelSetParameters();
		_kineticsModeInterface1._graphPanel.repaint();
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel, _kineticsModeMath1.get_levelPosDisplay(), 3);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._zeroLabel, _kineticsModeMath1.get_zeroPosDisplay(), 3);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerFirstLabel,_kineticsModeMath1.get_fitMarkerFirstDisplayX(), 2);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerLastLabel,_kineticsModeMath1.get_fitMarkerLastDisplayX(), 2);

	}*/
	
	public void addListeners()
	{
		
		_kineticsModeInterface1._graphPanel.addMouseMotionListener(ma);
		_kineticsModeInterface1._graphPanel.addMouseListener(ma);
		
		
		// Toolbar listeners
		
		
		
		_kineticsModeInterface1._openFileButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			openFile();
    		}});
		
		
		
		_kineticsModeInterface1._exitButton.addActionListener( new ActionListener()
    		{
        	@Override
        	public void actionPerformed(ActionEvent e) {System.exit(0);
        	}});		
		_kineticsModeInterface1._convertFileButton.addActionListener( new ActionListener()
        	{
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		String _savedName = _kineticsModeMath1.convertAndSaveKinetics();
        		_kineticsModeInterface1._status.setText("Converted successfuly. Saved as: " + _savedName);

        	}});		
		
		_kineticsModeInterface1._snapShotButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
   
    			int x = _kineticsModeInterface1._graphPanel.getSize().width;
     			int y = _kineticsModeInterface1._graphPanel.getSize().height;

    			BufferedImage off_Image = (BufferedImage)_kineticsModeInterface1._graphPanel.createImage(x, y);
    			_kineticsModeInterface1._graphPanel.set_mouseLabelText("");
    			Graphics2D g2d = off_Image.createGraphics();
    		    _kineticsModeInterface1._graphPanel.paint(g2d);
    		    _kineticsModeMath1.getSnapShot(off_Image);
    		}});		

        //_kineticsModeInterface._modeBox.addActionListener(this);
        
        
		
		
		
		
		
		
		
		
		
		// Control buttons' listeners
		
		
		_kineticsModeInterface1._setLevelUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftLevel(_kineticsModeMath1.get_levelShiftStep());
    		}});		

		_kineticsModeInterface1._setLevelDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftLevel(-_kineticsModeMath1.get_levelShiftStep());
    		}});		
		
		_kineticsModeInterface1._setLevelUpFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftLevel(_kineticsModeMath1.get_levelShiftStepFast());
     		}});		
		
		_kineticsModeInterface1._setLevelDownFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftLevel(-_kineticsModeMath1.get_levelShiftStepFast());
    		}});		
		
		_kineticsModeInterface1._setLevelZero.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftLevel(-_kineticsModeMath1.get_levelPosDisplay());
 		}});		


		_kineticsModeInterface1._setLineCenter.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeMath1.centerLines();
    			checkFitMarkerPos();
    			graphPanelSetParameters();
    			_kineticsModeInterface1._graphPanel.repaint();
      		}});		

		_kineticsModeInterface1._setMagnify.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeMath1.magnifyGraph();
    			graphPanelSetParameters();
    			_kineticsModeInterface1._graphPanel.set_selectedRect(_kineticsModeMath1.get_selectedRect());
    			_kineticsModeInterface1._graphPanel.repaint();	
      		}});		
					
		_kineticsModeInterface1._trimGraph.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeMath1.trimGraph();
    			graphPanelSetParameters();
    			checkFitMarkerPos();
    			_kineticsModeInterface1._status.setText("Trimming done");
    			_kineticsModeInterface1._graphPanel.repaint();
	
      		}});		
		

		_kineticsModeInterface1._smoothKin.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeMath1.smoothOneKineticCurve();
    			_kineticsModeInterface1._graphPanel.repaint();
      		}});		

		_kineticsModeInterface1._setSmoothPointsUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftNSmoothKinPoints(1);}});		
		
		_kineticsModeInterface1._setSmoothPointsDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftNSmoothKinPoints(-1);}});
 
		
		/*_kineticsModeInterface1._fixViewBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_graphPanel._ifFixViewOn = !_graphPanel._ifFixViewOn;
    		}});		
		
		
*/		
		_kineticsModeInterface1._setZeroLeft.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftZero(-_kineticsModeMath1._zeroShiftStep);}});		

		_kineticsModeInterface1._setZeroRight.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftZero(_kineticsModeMath1._zeroShiftStep);}});		
		
		_kineticsModeInterface1._setZeroLeftFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftZero(-_kineticsModeMath1._zeroShiftStepFast);}});		
		
		_kineticsModeInterface1._setZeroRightFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftZero(_kineticsModeMath1._zeroShiftStepFast);}});		
		
		_kineticsModeInterface1._setTimeZero.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftZero(-_kineticsModeMath1._zeroPosDisplay);
    		}});		

		

		
		_kineticsModeInterface1._setFitMarkerFirstLeft.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerFirst(-_kineticsModeMath1._markerShiftStep);}});		

		_kineticsModeInterface1._setFitMarkerFirstRight.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerFirst(_kineticsModeMath1._markerShiftStep);}});		
		
		_kineticsModeInterface1._setFitMarkerFirstLeftFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerFirst(-_kineticsModeMath1._markerShiftStepFast);}});		
		
		_kineticsModeInterface1._setFitMarkerFirstRightFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerFirst(_kineticsModeMath1._markerShiftStepFast);}});		
		
		
		
		_kineticsModeInterface1._setFitMarkerLastLeft.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerLast(-_kineticsModeMath1._markerShiftStep);}});		

		_kineticsModeInterface1._setFitMarkerLastRight.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerLast(_kineticsModeMath1._markerShiftStep);}});		
		
		_kineticsModeInterface1._setFitMarkerLastLeftFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerLast(-_kineticsModeMath1._markerShiftStepFast);}});		
		
		_kineticsModeInterface1._setFitMarkerLastRightFast.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftFitMarkerLast(_kineticsModeMath1._markerShiftStepFast);}});		
	

		
		
		_kineticsModeInterface1._averageButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeMath1.set_ifShowCreatedCurve(true);
    			_kineticsModeMath1.calcAverage();
    			_kineticsModeInterface1._status.setText("Average value.");
    			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
    			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
    			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
    			_kineticsModeInterface1._graphPanel.set_mouseLabelText(_kineticsModeMath1.get_mouseLabelText());
    			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
    			_kineticsModeInterface1._graphPanel.repaint();
      		}});
	
		
		
		
		_kineticsModeInterface1._1expFitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeInterface1._fit1expInterface.setVisible(true);
     		}});		
		
		
		
		
		
		
		
		_kineticsModeInterface1._2OrderFitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeInterface1._fit2OrderInterface.setVisible(true);
     		}});		

		
		_kineticsModeInterface1._2OrderProductFitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface.setVisible(true);
     		}});	
		
		
		
		
/*		_kineticsModeInterface1._12OrderFitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			show12OrderFitInterface();
     		}});		
		
		
	
		
		
		
		
		
		*/
		
		
		
		
		
		
		// menu bar controllers
		
		_kineticsModeInterface1._menuBar._openFileItem.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			openFile();
             }
       	});
		
		
		_kineticsModeInterface1._menuBar._exitItem.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
           		System.exit(0);
            }
       	});
	    
		_kineticsModeInterface1._menuBar._enableAABox.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    			{
    			_kineticsModeInterface1._graphPanel._enableAABox=!_kineticsModeInterface1._graphPanel._enableAABox;
      			_kineticsModeInterface1._errorPanel._enableAABox=!_kineticsModeInterface1._errorPanel._enableAABox;
       			_kineticsModeInterface1._graphPanel.repaint();
    			_kineticsModeInterface1._errorPanel.repaint();
   					
    			}});
		
		_kineticsModeInterface1._menuBar._enableLowDetailBox.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    			{

    			}});


		_kineticsModeInterface1._menuBar._setSameColorBox.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    			{

    			}});
		
		_kineticsModeInterface1._menuBar._saveEveryText.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		   			
		    	    String text = _kineticsModeInterface1._menuBar._saveEveryText.getText();
		
		    	    int _saveEvery=1;
	    			if(text!="")
	    			{	
	    				_saveEvery = Integer.parseInt(text);
					}
		    	  	if(_saveEvery<=0)_saveEvery=1;
		    	  	_kineticsModeMath1.set_saveEvery(_saveEvery);
	    			System.out.println("Save every " + _saveEvery + " point");
		      }
		      });
		
		_kineticsModeInterface1._menuBar._aboutItem.addActionListener(new ActionListener() 
		{
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_kineticsModeInterface1._aboutBox.setVisible(true);
            }
       	});

		
		
		
		
	}
	
	
	
	
	public void add1expFitListeners()
	{
		_kineticsModeInterface1._fit1expInterface.addWindowListener(new WindowAdapter()
		{
			
			public void windowActivated(WindowEvent e)
			{
				renewLabelContent();
			}
		});
		
		
		
		
		
		
		_kineticsModeInterface1._fit1expInterface._fitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
    			fit1exp();
       		}});		
		

		_kineticsModeInterface1._fit1expInterface._createButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
    			_kineticsModeMath1.set_fittingMode("1exp Fit");
    			_kineticsModeMath1.fillCreatedCurveArray();
    			_kineticsModeInterface1._status.setText("Create curve.");
    			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
    			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
    			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
    			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
    			_kineticsModeInterface1._graphPanel.repaint();

    		}});		
		
		
		
		
		
		
		_kineticsModeInterface1._fit1expInterface._1expFitKValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		   			
		    	  	String text = _kineticsModeInterface1._fit1expInterface._1expFitKValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
		    	  		{text = "0" + text;}
		    	  	else
		    	  		{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
		    	  	
	    			if(text!="")
	    			{	
	    				_kineticsModeMath1.set_k(Float.parseFloat(text));
					}
	    			System.out.println("k: " + _kineticsModeMath1.get_k());
		      }
		      });

		_kineticsModeInterface1._fit1expInterface._1expFitAValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		  			
		    	  	String text = _kineticsModeInterface1._fit1expInterface._1expFitAValLabel.getText();

		    	  	if(text.indexOf('-')==-1)
	    	  		{text = "0" + text;}
	    	  	else
	    	  		{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
		    	  	
	    			if(text!="")
	    			{
	    				_kineticsModeMath1.set_A(Float.parseFloat(text));
	    			}
	    			System.out.println("A: " + _kineticsModeMath1.get_A());
	  		      }
		      });
	}
	
	
	
	public void graphPanelSetParameters()
	{
		
		_kineticsModeInterface1._graphPanel.set_arraySize(_kineticsModeMath1.get_arraySize());
		_kineticsModeInterface1._graphPanel.set_xScaler(_kineticsModeMath1.get_xScaler());
		_kineticsModeInterface1._graphPanel.set_yScaler(_kineticsModeMath1.get_yScaler());
		_kineticsModeInterface1._graphPanel.set_xScalerIni(_kineticsModeMath1.get_xScalerIni());
		_kineticsModeInterface1._graphPanel.set_yScalerIni(_kineticsModeMath1.get_yScalerIni());
		_kineticsModeInterface1._graphPanel.set_xMin(_kineticsModeMath1.get_xMin());
		_kineticsModeInterface1._graphPanel.set_yMin(_kineticsModeMath1.get_yMin());
		_kineticsModeInterface1._graphPanel.set_xMax(_kineticsModeMath1.get_xMax());
		_kineticsModeInterface1._graphPanel.set_yMax(_kineticsModeMath1.get_yMax());
		_kineticsModeInterface1._graphPanel.set_magPosX(_kineticsModeMath1.get_magPosX());
		_kineticsModeInterface1._graphPanel.set_magPosY(_kineticsModeMath1.get_magPosY());
		_kineticsModeInterface1._graphPanel.set_levelPosReal(_kineticsModeMath1.get_levelPosReal());
		_kineticsModeInterface1._graphPanel.set_zeroPosReal(_kineticsModeMath1.get_zeroPosReal());
		_kineticsModeInterface1._graphPanel.set_xArray(_kineticsModeMath1.get_xArray());
		_kineticsModeInterface1._graphPanel.set_yArray(_kineticsModeMath1.get_yArray());
		_kineticsModeInterface1._graphPanel.set_fitMarkerFirstRealX(_kineticsModeMath1.get_fitMarkerFirstRealX());
		_kineticsModeInterface1._graphPanel.set_fitMarkerFirstRealY(_kineticsModeMath1.get_fitMarkerFirstRealY());
		_kineticsModeInterface1._graphPanel.set_fitMarkerLastRealX(_kineticsModeMath1.get_fitMarkerLastRealX());
		_kineticsModeInterface1._graphPanel.set_fitMarkerLastRealY(_kineticsModeMath1.get_fitMarkerLastRealY());
		_kineticsModeInterface1._graphPanel.set_selectedRect(_kineticsModeMath1.get_selectedRect());
	}
	
	
	public void errorPanelSetParameters()
	{
		_kineticsModeInterface1._errorPanel.set_xScaler(_kineticsModeMath1.get_xScaler());
		_kineticsModeInterface1._errorPanel.set_yScaler(_kineticsModeMath1.get_yScalerError());
		_kineticsModeInterface1._errorPanel.set_xMin(_kineticsModeMath1.get_xMin());
		_kineticsModeInterface1._errorPanel.set_yMin(_kineticsModeMath1.get_yMinError());
		_kineticsModeInterface1._errorPanel.set_xMax(_kineticsModeMath1.get_xMax());
		_kineticsModeInterface1._errorPanel.set_yMax(_kineticsModeMath1.get_yMaxError());
		_kineticsModeInterface1._errorPanel.set_magPosX(_kineticsModeMath1.get_magPosXError());
		_kineticsModeInterface1._errorPanel.set_magPosY(_kineticsModeMath1.get_magPosY());
	}

	
	public void shiftLevel(float shift)
	{
		_kineticsModeMath1.shiftLevel(shift);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel, _kineticsModeMath1.get_levelPosDisplay(), 3);
		_kineticsModeInterface1._graphPanel.set_levelPosReal(_kineticsModeMath1.get_levelPosReal());
		_kineticsModeInterface1._graphPanel.repaint();
		renewLabelContent();
	}
	
	public void shiftZero(float shift)
	{
		_kineticsModeMath1.shiftZero(shift);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._zeroLabel, _kineticsModeMath1.get_zeroPosDisplay(), 3);
		_kineticsModeInterface1._graphPanel.set_zeroPosReal(_kineticsModeMath1.get_zeroPosReal());
		_kineticsModeInterface1._graphPanel.repaint();
		
		//setLabelText(_zeroLabel, _graphPanel._zeroPosDisplay, 1);
		//setLabelText(_zeroLabelHS, _graphPanel._zeroPosDisplay, 1);
		checkFitMarkerPos();	
	}
	
	
	public void openFile()
	{
		_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(false);
		String _fileName = _kineticsModeMath1.openFile();
		System.out.println("FileNameis: " + _fileName);
		_kineticsModeInterface1.setStatusText("Opened file:  " + _fileName);
		graphPanelSetParameters();
		_kineticsModeInterface1._graphPanel.repaint();
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel, _kineticsModeMath1.get_levelPosDisplay(), 3);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._zeroLabel, _kineticsModeMath1.get_zeroPosDisplay(), 3);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerFirstLabel,_kineticsModeMath1.get_fitMarkerFirstDisplayX(), 2);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerLastLabel,_kineticsModeMath1.get_fitMarkerLastDisplayX(), 2);
	}
	
	public void shiftFitMarkerFirst(int shift)
	{
		_kineticsModeMath1.shiftFitMarkerFirst(shift);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerFirstLabel,_kineticsModeMath1.get_fitMarkerFirstDisplayX(), 2);
		_kineticsModeInterface1._graphPanel.set_fitMarkerFirstRealX(_kineticsModeMath1.get_fitMarkerFirstRealX());
		_kineticsModeInterface1._graphPanel.set_fitMarkerFirstRealY(_kineticsModeMath1.get_fitMarkerFirstRealY());
		//_1expFitFromToLabel.setText("From " + _fitMarkerFirstLabel.getText() + "    To " + _fitMarkerLastLabel.getText());
		checkFitMarkerPos();

		_kineticsModeInterface1._graphPanel.repaint();

		
	}
	
	public void shiftFitMarkerLast(int shift)
	{
		_kineticsModeMath1.shiftFitMarkerLast(shift);
		_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerLastLabel,_kineticsModeMath1.get_fitMarkerLastDisplayX(), 2);
		_kineticsModeInterface1._graphPanel.set_fitMarkerLastRealX(_kineticsModeMath1.get_fitMarkerLastRealX());
		_kineticsModeInterface1._graphPanel.set_fitMarkerLastRealY(_kineticsModeMath1.get_fitMarkerLastRealY());
		//_1expFitFromToLabel.setText("From " + _fitMarkerFirstLabel.getText() + "    To " + _fitMarkerLastLabel.getText());
		checkFitMarkerPos();
		  
		_kineticsModeInterface1._graphPanel.repaint();
	}

	
	 public void checkFitMarkerPos()
	 {
		 if(_kineticsModeMath1.checkFitMarkerPos())
		 {
				_kineticsModeInterface1._statusInnerPanel.setBackground(Color.green);
				//_kineticsModeInterface1._statusHSInnerPanel.setBackground(Color.red);
		 }
		 else
		 {
				_kineticsModeInterface1._statusInnerPanel.setBackground(Color.red);
				//_kineticsModeInterface1._statusHSInnerPanel.setBackground(Color.green);

		 }
	 }
	
	public void shiftNSmoothKinPoints(int shift)
	{
		_kineticsModeMath1.shiftNSmoothKinPoints(shift);
		String s;
		int a = _kineticsModeMath1._nOfSmoothPoints*2+1;
		s = "" + a;
		_kineticsModeInterface1._smoothKinLabel.setText(s);
		//_smoothKinHSLabel.setText(s);
	}
	
	
	
	public void renewLabelContent()
	{
		String s;
		

		_kineticsModeInterface1._fit1expInterface._1expFitD0ValLabel.setText(_kineticsModeInterface1._levelLabel.getText());
		s = "" + _kineticsModeMath1.get_A();
		_kineticsModeInterface1._fit1expInterface._1expFitAValLabel.setText(s);
		s = "" + _kineticsModeMath1.get_k();
		_kineticsModeInterface1._fit1expInterface._1expFitKValLabel.setText(s);


		_kineticsModeInterface1._fit2OrderInterface._2OrderFitD0ValLabel.setText(_kineticsModeInterface1._levelLabel.getText());
		s = "" + _kineticsModeMath1.get_A();
		_kineticsModeInterface1._fit2OrderInterface._2OrderFitAValLabel.setText(s);
		s = "" + _kineticsModeMath1.get_k();
		_kineticsModeInterface1._fit2OrderInterface._2OrderFitKValLabel.setText(s);
		
		
		s = "" + _kineticsModeMath1.get_k();
		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitKValLabel.setText(s);
		

	
	
	}
	
	
	public void add2OrderFitListeners()
	{
		_kineticsModeInterface1._fit2OrderInterface.addWindowListener(new WindowAdapter()
		{
			
			public void windowActivated(WindowEvent e)
			{
				renewLabelContent();
				
			}
		});
		
		
		
		_kineticsModeInterface1._fit2OrderInterface._fitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
    			fit2Order();
    			

    		
    		}});		
		

		_kineticsModeInterface1._fit2OrderInterface._createButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
      			_kineticsModeMath1.set_fittingMode("2Order Fit");
    			_kineticsModeMath1.fillCreatedCurveArray();
    			_kineticsModeInterface1._status.setText("Create curve.");
    			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
    			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
    			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
    			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
    			_kineticsModeInterface1._graphPanel.repaint();

    		}});		
		
		
		
		
		
		
		_kineticsModeInterface1._fit2OrderInterface._2OrderFitKValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) 
		      {

		   			String text = _kineticsModeInterface1._fit2OrderInterface._2OrderFitKValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
	    	  			{text = "0" + text;}
		    	  	else
	    	  			{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
	    			if(text!="")
	    			{	
	    				_kineticsModeMath1.set_k(Float.parseFloat(text));
					}
		      }
		      });

		_kineticsModeInterface1._fit2OrderInterface._2OrderFitAValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		  			String text = _kineticsModeInterface1._fit2OrderInterface._2OrderFitAValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
	    	  			{text = "0" + text;}
		    	  	else
	    	  			{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
	    			if(text!="")
	    			{
	    				_kineticsModeMath1.set_A(Float.parseFloat(text));
	    			}
	    			//System.out.println("A: " + _graphPanel._A);
	  		      }
		      });
	}
	
	
	
	
	
	
	
	
	
	
	public void add2OrderProductFitListeners()
	{
		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface.addWindowListener(new WindowAdapter()
		{
			
			public void windowActivated(WindowEvent e)
			{
				renewLabelContent();
				
			}
		});
		
		
		
		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._fitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
    			fit2OrderProduct();
    			

    		
    		}});		
		

		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._createButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e)
    		
    		{
      			_kineticsModeMath1.set_fittingMode("2Order Fit Product");
    			_kineticsModeMath1.fillCreatedCurveArray();
    			_kineticsModeInterface1._status.setText("Create curve.");
    			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
    			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
    			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
    			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
    			_kineticsModeInterface1._graphPanel.repaint();

    		}});		
		
		
		
		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitD0ValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) 
		      {

		   			String text = _kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitD0ValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
	    	  			{text = "0" + text;}
		    	  	else
	    	  			{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
	    			if(text!="")
	    			{	
	    				_kineticsModeMath1.set_D0(Float.parseFloat(text));
					}
		      }
		      });
		
		
		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitKValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) 
		      {

		   			String text = _kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitKValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
	    	  			{text = "0" + text;}
		    	  	else
	    	  			{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
	    			if(text!="")
	    			{	
	    				_kineticsModeMath1.set_k(Float.parseFloat(text));
					}
		      }
		      });

		_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitAValLabel.addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		  			String text = _kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitAValLabel.getText();
		    	  	if(text.indexOf('-')==-1)
	    	  			{text = "0" + text;}
		    	  	else
	    	  			{if(text.indexOf('-')==0)text = "-" + "0" + text.substring(1);}
	    			if(text!="")
	    			{
	    				_kineticsModeMath1.set_A(Float.parseFloat(text));
	    			}
	    			//System.out.println("A: " + _graphPanel._A);
	  		      }
		      });
	}
	
	
	
	
	
	
	
	
	
	
	public boolean checkCorrectParameters()
	{
		boolean b=true;
	
			if(_kineticsModeInterface1._statusInnerPanel.getBackground()==Color.red)
			{
				b=false;
				JOptionPane.showMessageDialog(_kineticsModeInterface1, "Check fitting parameters!");
			}
		return b;
	}
	
	
	public void fit1exp()
	{
		if(checkCorrectParameters())
		{
			_kineticsModeMath1.set_fittingMode("1exp Fit");
			_kineticsModeMath1.set_if1expACheckBoxSelected(_kineticsModeInterface1._fit1expInterface._1expACheckBox.isSelected());
				_kineticsModeMath1.set_if1expKCheckBoxSelected(_kineticsModeInterface1._fit1expInterface._1expKCheckBox.isSelected());
				_kineticsModeMath1.set_if1expD0CheckBoxSelected(_kineticsModeInterface1._fit1expInterface._1expD0CheckBox.isSelected());
			    				
			
			
			_kineticsModeMath1.firstOrderFitting();
			//_errorPanel._xScaler = _graphPanel._xScaler;
			//_errorPanel._yScaler = _graphPanel._yScaler;
			//_errorPanel._ifShowCreatedCurve = true;
			
			if(_kineticsModeMath1.get_ifFittingSuccessful())
				{
				_kineticsModeInterface1._fit1expInterface._1expFittingStatusPanel.setBackground(Color.green);
				_kineticsModeInterface1._fit1expInterface._1expFittingStatusPanel.repaint();
				}
			else
			{
				_kineticsModeInterface1._fit1expInterface._1expFittingStatusPanel.setBackground(Color.red);
				_kineticsModeInterface1._fit1expInterface._1expFittingStatusPanel.repaint();
			}

  			_kineticsModeInterface1._status.setText("1st Order Exponential Fitting.");
			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
			//_kineticsModeInterface1._graphPanel.set_mouseLabelText(_kineticsModeMath1.get_mouseLabelText());
			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
			graphPanelSetParameters();
			_kineticsModeInterface1._graphPanel.repaint();
			
			_kineticsModeInterface1._errorPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArrayError());
			_kineticsModeInterface1._errorPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePointsError());
			_kineticsModeInterface1._errorPanel.set_ifShowCreatedCurve(true);
			errorPanelSetParameters();
			_kineticsModeInterface1._errorPanel.repaint();

			
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel,_kineticsModeMath1._levelPosDisplay, 3);
			
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._fit1expInterface._1expFitAValLabel, _kineticsModeMath1.get_a20(), 6);
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._fit1expInterface._1expFitKValLabel, _kineticsModeMath1.get_a30(), 6);
	  	  

			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit1expInterface._1expFitD0ErrLabel, _kineticsModeMath1.get_sigma1(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit1expInterface._1expFitAErrLabel, _kineticsModeMath1.get_sigma2(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit1expInterface._1expFitKErrLabel, _kineticsModeMath1.get_sigma3(), 3);
			
			_kineticsModeInterface1._fit1expInterface._1expFitD0ValLabel.setText(_kineticsModeInterface1._levelLabel.getText());
	  	  	

			}
		
	}
	
	
	
	
	
	public void fit2Order()
	{
		if(checkCorrectParameters())
		{
			
			
			
			
			_kineticsModeMath1.set_fittingMode("2Order Fit");
			_kineticsModeMath1.set_if2OrderACheckBoxSelected(_kineticsModeInterface1._fit2OrderInterface._2OrderACheckBox.isSelected());
				_kineticsModeMath1.set_if2OrderKCheckBoxSelected(_kineticsModeInterface1._fit2OrderInterface._2OrderKCheckBox.isSelected());
				_kineticsModeMath1.set_if2OrderD0CheckBoxSelected(_kineticsModeInterface1._fit2OrderInterface._2OrderD0CheckBox.isSelected());
			    				
			
			
			_kineticsModeMath1.secondOrderFitting();

			
			if(_kineticsModeMath1.get_ifFittingSuccessful())
				{
				_kineticsModeInterface1._fit2OrderInterface._2OrderFittingStatusPanel.setBackground(Color.green);
				_kineticsModeInterface1._fit2OrderInterface._2OrderFittingStatusPanel.repaint();
				}
			else
			{
				_kineticsModeInterface1._fit2OrderInterface._2OrderFittingStatusPanel.setBackground(Color.red);
				_kineticsModeInterface1._fit2OrderInterface._2OrderFittingStatusPanel.repaint();
			}

  			_kineticsModeInterface1._status.setText("2nd Order Fitting.");
			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
			//_kineticsModeInterface1._graphPanel.set_mouseLabelText(_kineticsModeMath1.get_mouseLabelText());
			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
			graphPanelSetParameters();
			_kineticsModeInterface1._graphPanel.repaint();
			
			_kineticsModeInterface1._errorPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArrayError());
			_kineticsModeInterface1._errorPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePointsError());
			_kineticsModeInterface1._errorPanel.set_ifShowCreatedCurve(true);
			errorPanelSetParameters();
			_kineticsModeInterface1._errorPanel.repaint();

			
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel,_kineticsModeMath1._levelPosDisplay, 3);
			
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._fit2OrderInterface._2OrderFitAValLabel, _kineticsModeMath1.get_a20(), 6);
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._fit2OrderInterface._2OrderFitKValLabel, _kineticsModeMath1.get_a30(), 6);
	  	  

			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit2OrderInterface._2OrderFitD0ErrLabel, _kineticsModeMath1.get_sigma1(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit2OrderInterface._2OrderFitAErrLabel, _kineticsModeMath1.get_sigma2(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._fit2OrderInterface._2OrderFitKErrLabel, _kineticsModeMath1.get_sigma3(), 3);
			
			_kineticsModeInterface1._fit2OrderInterface._2OrderFitD0ValLabel.setText(_kineticsModeInterface1._levelLabel.getText());
	  	  	
				
			
		}
		
		
		
	}
	
	
	
	public void fit2OrderProduct()
	{
		if(checkCorrectParameters())
		{
			
			
			
			
			_kineticsModeMath1.set_fittingMode("2Order Fit Product");
			_kineticsModeMath1.set_if2OrderACheckBoxSelected(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderACheckBox.isSelected());
				_kineticsModeMath1.set_if2OrderKCheckBoxSelected(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderKCheckBox.isSelected());
				_kineticsModeMath1.set_if2OrderD0CheckBoxSelected(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderD0CheckBox.isSelected());
			    				
			
			
			_kineticsModeMath1.secondOrderProductFitting();

			
			if(_kineticsModeMath1.get_ifFittingSuccessful())
				{
				_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFittingStatusPanel.setBackground(Color.green);
				_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFittingStatusPanel.repaint();
				}
			else
			{
				_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFittingStatusPanel.setBackground(Color.red);
				_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFittingStatusPanel.repaint();
			}

  			_kineticsModeInterface1._status.setText("2nd Order Fitting.");
			_kineticsModeInterface1._graphPanel.set_fittingColor(_kineticsModeMath1.get_fittingColor());
			_kineticsModeInterface1._graphPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArray());
			_kineticsModeInterface1._graphPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePoints());
			//_kineticsModeInterface1._graphPanel.set_mouseLabelText(_kineticsModeMath1.get_mouseLabelText());
			_kineticsModeInterface1._graphPanel.set_ifShowCreatedCurve(true);
			graphPanelSetParameters();
			_kineticsModeInterface1._graphPanel.repaint();
			
			_kineticsModeInterface1._errorPanel.set_createdCurveArray(_kineticsModeMath1.get_createdCurveArrayError());
			_kineticsModeInterface1._errorPanel.set_nOfCreatedCurvePoints(_kineticsModeMath1.get_nOfCreatedCurvePointsError());
			_kineticsModeInterface1._errorPanel.set_ifShowCreatedCurve(true);
			errorPanelSetParameters();
			_kineticsModeInterface1._errorPanel.repaint();

			
			//_kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel,_kineticsModeMath1._levelPosDisplay, 3);
			
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitD0ValLabel, _kineticsModeMath1.get_a10(), 5);
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitAValLabel, _kineticsModeMath1.get_a20(), 5);
			_kineticsModeMath1.setTextFieldText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitKValLabel, _kineticsModeMath1.get_a30(), 5);
	  	  

			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitD0ErrLabel, _kineticsModeMath1.get_sigma1(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitAErrLabel, _kineticsModeMath1.get_sigma2(), 3);
			_kineticsModeMath1.setLabelText(_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitKErrLabel, _kineticsModeMath1.get_sigma3(), 3);
			
			//_kineticsModeInterface1._kinecticsModeFit2OrderProductInterface._2OrderFitD0ValLabel.setText(_kineticsModeInterface1._levelLabel.getText());
	  	  	
				
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class KineticsModeMovingAdapter extends MouseAdapter
	{
		
		public void mouseMoved(MouseEvent e)
		{
			//_kineticsModeMath1.calcRectanglesPositions();
			_kineticsModeMath1.mouseMoved(e);
			_kineticsModeInterface1._graphPanel.set_mouseLabelText(_kineticsModeMath1.get_mouseLabelText());
			_kineticsModeInterface1._graphPanel.setCursor(_kineticsModeMath1.get_cursor());
			//_kineticsModeInterface1._graphPanel.repaint();
			
		}

		public void mousePressed(MouseEvent e)
		{
			_kineticsModeMath1.mousePressed(e);
			if(_kineticsModeMath1.get_ifNeedToRepaint())
			{
				
				graphPanelSetParameters();
				_kineticsModeInterface1._graphPanel.repaint();
				_kineticsModeMath1.set_ifNeedToRepaint(false);
			}
			
		}
		
		public void mouseReleased(MouseEvent e)
		{
			_kineticsModeMath1.mouseReleased(e);

				graphPanelSetParameters();
				_kineticsModeInterface1._graphPanel.repaint();
				_kineticsModeMath1.set_ifNeedToRepaint(false);

		}
		
		 public void mouseDragged(MouseEvent e)
		 {
			 _kineticsModeMath1.mouseDragged(e);
			 _kineticsModeInterface1._graphPanel.setCursor(_kineticsModeMath1.get_cursor());
			 
			 graphPanelSetParameters();
			 checkFitMarkerPos();
			 _kineticsModeMath1.setLabelText(_kineticsModeInterface1._levelLabel, _kineticsModeMath1.get_levelPosDisplay(), 3);
			 _kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerFirstLabel,_kineticsModeMath1.get_fitMarkerFirstDisplayX(), 2);
			 _kineticsModeMath1.setLabelText(_kineticsModeInterface1._fitMarkerLastLabel,_kineticsModeMath1.get_fitMarkerLastDisplayX(), 2);
			 _kineticsModeMath1.setLabelText(_kineticsModeInterface1._zeroLabel, _kineticsModeMath1.get_zeroPosDisplay(), 3);
			 _kineticsModeInterface1._graphPanel.repaint();
			 renewLabelContent();
			  //FileAnalizer._1expFitD0ValLabel.setText(FileAnalizer._levelLabel.getText());
			  //FileAnalizer._2OrderFitD0ValLabel.setText(FileAnalizer._levelLabel.getText());
			  //FileAnalizer._12OrderFitD0ValLabel.setText(FileAnalizer._levelLabel.getText());
	    	//  FileAnalizer.setLabelText( FileAnalizer._zeroLabel,_zeroPosDisplay, 1);
	    	 // FileAnalizer.setLabelText( FileAnalizer._zeroLabelHS,_zeroPosDisplay, 1);
	    	 // checkFitMarkerPos();
	    	  //FileAnalizer.setLabelText(FileAnalizer._beforeZeroLabel,_beforeZeroPosDisplay, 1);
	    	 // FileAnalizer.setLabelText(FileAnalizer._fitMarkerFirstLabel,_fitMarkerFirstDisplayX, 1);
	    	  //FileAnalizer._1expFitFromToLabel.setText("From " + FileAnalizer._fitMarkerFirstLabel.getText() + "    To " + FileAnalizer._fitMarkerLastLabel.getText());
	    	 // FileAnalizer._2OrderFitFromToLabel.setText("From " + FileAnalizer._fitMarkerFirstLabel.getText() + "    To " + FileAnalizer._fitMarkerLastLabel.getText());
	    	 // FileAnalizer._12OrderFitFromToLabel.setText("From " + FileAnalizer._fitMarkerFirstLabel.getText() + "    To " + FileAnalizer._fitMarkerLastLabel.getText());


		 }
		
		 

		 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
