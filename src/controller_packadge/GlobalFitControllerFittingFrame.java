package controller_packadge;


import interface_packadge.*;
import controller_packadge.*;
import math_packadge.*;



import java.awt.*;
import java.io.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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


public class GlobalFitControllerFittingFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerFittingFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addGlobalFitFittingFrameListeners()
	{
		

		
		_globalFitInterface1._globalFitFittingFrame._setLimitsButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.fillSetLimitsTableData();
    			_globalFitInterface1._globalFitSetLimitsFrame.setVisible(true);
     		}});
		

		
		_globalFitInterface1._globalFitFittingFrame._startFittingButton.addActionListener(_parentClass1._globalFitProgressBarMonitoring);
		
		
		
		
		
		
		
		
		_globalFitInterface1._globalFitFittingFrame._stopFittingButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			System.out.println("Operation stoped!!!!!!!!!!!");
      			_globalFitInterface1._globalFitFittingFrame._progressPanel.remove(_globalFitInterface1._globalFitFittingFrame._stopFittingButton);
      			_globalFitInterface1._globalFitFittingFrame._progressPanel.add(_globalFitInterface1._globalFitFittingFrame._startFittingButton);
      			_globalFitInterface1._globalFitFittingFrame._progressPanel.repaint();
      			_parentClass1.setWaitingCursorForAllWindows(true);
				_globalFitInterface1._globalFitMainInterface._status.setText("Please wait. Program needs to finish procedures.");
      			
       			_globalFitMath1.set_ifContinueToFit(false);
       			//performGlobalFit();
    			//fillSetLimitsTableData();
    			
     		}});
		
		
		
		_globalFitInterface1._globalFitFittingFrame._alterAbsCoeffBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitMath1.set_ifAlterAbsCoeff(_globalFitInterface1._globalFitFittingFrame._alterAbsCoeffBox.isSelected());
     		checkBoxes();
    	}});
		
		
		
		
		_globalFitInterface1._globalFitFittingFrame._alterConstantsBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitMath1.set_ifAlterConst(_globalFitInterface1._globalFitFittingFrame._alterConstantsBox.isSelected());
     		checkBoxes();
    	}});
		
		
		
		
		_globalFitInterface1._globalFitFittingFrame._alterInitConcentrationBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitMath1.set_ifAlterInitConcentration(_globalFitInterface1._globalFitFittingFrame._alterInitConcentrationBox.isSelected());
     		_globalFitInterface1._globalFitFittingFrame._defineRelationsButton.setEnabled(_globalFitInterface1._globalFitFittingFrame._alterInitConcentrationBox.isSelected());
     		checkBoxes();
    	}});
		
		
		
	
		
		
		_globalFitInterface1._globalFitFittingFrame._defineRelationsButton.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_globalFitInterface1._globalFitDefineRelationsFrame.setVisible(true);
    	}});
		
		
		
		
		_globalFitInterface1._globalFitFittingFrame._accuracySlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			int _n =_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue()*3;
    			
    			_globalFitMath1.set_nOfMinorIterationsAbs(jumpUpToClosestEven(_n+2));
    			_globalFitMath1.set_nOfMinorIterationsConst(jumpUpToClosestEven(_n+2));
    			
    			
    			_globalFitMath1.set_nOfIterationsGradientAbs(_n);
    			_globalFitMath1.set_nOfIterationsGradientConst(_n);
    			_globalFitMath1.set_nOfIterationsGradientConc(_n);
    			
    			_n =_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue();
       			_globalFitMath1.set_directNOfIterations(_n);
    			
    			_n =1;
    			_globalFitMath1.set_cubeSideAbs((0.5f/(float)_n));
    			_globalFitMath1.set_cubeSideConst((0.5f/(float)_n));
    			_globalFitMath1.set_cubeSideConc((0.5f/(float)_n));

       		 
    			
    			
    		}});
		
		
		_globalFitInterface1._globalFitFittingFrame._useWeightBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitMath1.set_ifUseWeight(_globalFitInterface1._globalFitFittingFrame._useWeightBox.isSelected());
     		//_globalFitInterface1._globalFitSigmaFrame._ifCalcWeightsBox.setSelected(_globalFitInterface1._globalFitFittingFrame._useWeightBox.isSelected());

    	}});
	
		
		
		
		
		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
       		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.isSelected());
       		_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.isSelected());
       		setSettingsPanel();
       		//_globalFitInterface1._globalFitFittingFrame.setAdvancedGradientPanel();
      		
       		algorithmSettingsSetAdvancedValues();
    	}});
		
		
		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.isSelected());
    		_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.isSelected());
      		_globalFitInterface1._globalFitFittingFrame.setBasicPanel();

      		algorithmSettingsSetDefaultValues();
    	}});
		
		
		
		_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.isSelected());
    		_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.setSelected(_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.isSelected());
      		_globalFitInterface1._globalFitFittingFrame.setBasicPanel();
      		setSettingsPanel();
      		algorithmSettingsSetDefaultValues();
    	}});
		

	
		
		_globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		int _index = _globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.getSelectedIndex();
    		
    		if(_index==0)
    		{
    			_globalFitMath1.set_algorithmToBeUsed("advanced gradient");
    			setSettingsPanel();
    		}    		
    		if(_index==1)
    		{
    			_globalFitMath1.set_algorithmToBeUsed("advanced direct");
    			setSettingsPanel();
    		}

    		
    		
    	}});
		
		
	
		
		_globalFitInterface1._globalFitFittingFrame._minorIterationsSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_nOfMinorIterationsAbs(_globalFitInterface1._globalFitFittingFrame._minorIterationsSlider.getValue());
    			_globalFitMath1.set_nOfMinorIterationsConst(_globalFitInterface1._globalFitFittingFrame._minorIterationsSlider.getValue());

    		}});
		
		
		_globalFitInterface1._globalFitFittingFrame._majorIterationsSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_nOfMajorIterationsAbs(_globalFitInterface1._globalFitFittingFrame._majorIterationsSlider.getValue());
    			_globalFitMath1.set_nOfMajorIterationsConst(_globalFitInterface1._globalFitFittingFrame._majorIterationsSlider.getValue());

    		}});
		
		
		_globalFitInterface1._globalFitFittingFrame._slopeSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_slopeValue(_globalFitInterface1._globalFitFittingFrame._slopeSlider.getValue());
    			_globalFitMath1.set_slopeValueConst(_globalFitInterface1._globalFitFittingFrame._slopeSlider.getValue());

    		}});
		
		
		
		_globalFitInterface1._globalFitFittingFrame._directNOfIterationsSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_directNOfIterations(_globalFitInterface1._globalFitFittingFrame._directNOfIterationsSlider.getValue());
    		}});

		
		_globalFitInterface1._globalFitFittingFrame._directHighLimitSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_directHighLimit(_globalFitInterface1._globalFitFittingFrame._directHighLimitSlider.getValue()*0.01f);
    		}});
		
		_globalFitInterface1._globalFitFittingFrame._directLowLimitSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			_globalFitMath1.set_directLowLimit(_globalFitInterface1._globalFitFittingFrame._directLowLimitSlider.getValue()*0.01f);
    		}});


		
		
		
		_globalFitInterface1._globalFitFittingFrame._nOfIterationsSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			int _n =_globalFitInterface1._globalFitFittingFrame._nOfIterationsSlider.getValue();
    			_globalFitMath1.set_nOfIterationsGradientAbs(_n);
    			_globalFitMath1.set_nOfIterationsGradientConst(_n);
    			_globalFitMath1.set_nOfIterationsGradientConc(_n);
      		}});
		
		
		_globalFitInterface1._globalFitFittingFrame._cubeSideSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			int _n =_globalFitInterface1._globalFitFittingFrame._cubeSideSlider.getValue();
    			_globalFitMath1.set_cubeSideAbs((0.5f/(float)_n));
    			_globalFitMath1.set_cubeSideConst((0.5f/(float)_n));
    			_globalFitMath1.set_cubeSideConc((0.5f/(float)_n));
      		}});
			
		
	}
	
	
	
	
	
	
	
	
	
	public void setSettingsPanel()
	{
		boolean a,b,c,d;
		a = _globalFitMath1.get_ifAlterAbsCoeff();
		b = _globalFitMath1.get_ifAlterConst();
		c = _globalFitMath1.get_ifAlterInitConcentration();

		d = _globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.isSelected();
		int i,j;
		i = _globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.getSelectedIndex();
		
		if(!d)
		{
			_globalFitInterface1._globalFitFittingFrame.setBasicPanel();
		}
		else
		{
			if(i==1)
			{
				_globalFitInterface1._globalFitFittingFrame.setAdvancedDirectPanel();
			}
			else
			{
				_globalFitInterface1._globalFitFittingFrame.setAdvancedGradientPanel();
			}
		}
	}
	
	
	public void algorithmSettingsSetDefaultValues()
	{
		_globalFitMath1.set_slopeValue(Constants._slopeValueAbsDefault);
  		_globalFitMath1.set_slopeValueConst(Constants._slopeValueConstDefault);
  		_globalFitMath1.set_nOfMajorIterationsAbs(Constants._nOfMajorIterationsAbsDefault);
		_globalFitMath1.set_nOfMajorIterationsConst(Constants._nOfMajorIterationsConstDefault);
		_globalFitMath1.set_nOfMinorIterationsAbs(_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue()+2);
		_globalFitMath1.set_nOfMinorIterationsConst(_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue()+2);
		
		int _n =_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue()*3;
		_globalFitMath1.set_nOfIterationsGradientAbs(_n);
		_globalFitMath1.set_nOfIterationsGradientConst(_n);
		_globalFitMath1.set_nOfIterationsGradientConc(_n);
		
		_n =_globalFitInterface1._globalFitFittingFrame._accuracySlider.getValue();
		_globalFitMath1.set_directNOfIterations(_n);
		_globalFitMath1.set_directLowLimit(0.1f);
		_globalFitMath1.set_directLowLimit(1.9f);
		
		_n =1;
		_globalFitMath1.set_cubeSideAbs((0.5f/(float)_n));
		_globalFitMath1.set_cubeSideConst((0.5f/(float)_n));
		_globalFitMath1.set_cubeSideConc((0.5f/(float)_n));

	}
	
	public void algorithmSettingsSetAdvancedValues()
  		{
   			_globalFitMath1.set_nOfMinorIterationsAbs(_globalFitInterface1._globalFitFittingFrame._minorIterationsSlider.getValue());
			_globalFitMath1.set_nOfMinorIterationsConst(_globalFitInterface1._globalFitFittingFrame._minorIterationsSlider.getValue());
  			_globalFitMath1.set_nOfMajorIterationsAbs(_globalFitInterface1._globalFitFittingFrame._majorIterationsSlider.getValue());
			_globalFitMath1.set_nOfMajorIterationsConst(_globalFitInterface1._globalFitFittingFrame._majorIterationsSlider.getValue());
   			_globalFitMath1.set_slopeValue(_globalFitInterface1._globalFitFittingFrame._slopeSlider.getValue());
			_globalFitMath1.set_slopeValueConst(_globalFitInterface1._globalFitFittingFrame._slopeSlider.getValue());
			
			int _n =_globalFitInterface1._globalFitFittingFrame._nOfIterationsSlider.getValue();
			_globalFitMath1.set_nOfIterationsGradientAbs(_n);
			_globalFitMath1.set_nOfIterationsGradientConst(_n);
			_globalFitMath1.set_nOfIterationsGradientConc(_n);
			_globalFitMath1.set_directNOfIterations(_n);

			
			_n =_globalFitInterface1._globalFitFittingFrame._cubeSideSlider.getValue();
			_globalFitMath1.set_cubeSideAbs((0.5f/(float)_n));
			_globalFitMath1.set_cubeSideConst((0.5f/(float)_n));
			_globalFitMath1.set_cubeSideConc((0.5f/(float)_n));
			
			float a = _globalFitInterface1._globalFitFittingFrame._directHighLimitSlider.getValue();
			_globalFitMath1.set_directHighLimit(a*0.01f);
			a = _globalFitInterface1._globalFitFittingFrame._directLowLimitSlider.getValue();
			_globalFitMath1.set_directLowLimit(a*0.01f);

			
			
  		}
	
	
	
	
	public void checkBoxes()
	{
		boolean a,b,c;
		a = _globalFitMath1.get_ifAlterAbsCoeff();
		b = _globalFitMath1.get_ifAlterConst();
		c = _globalFitMath1.get_ifAlterInitConcentration();
		

		
		if(a & b & !c)
		{
			_globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.setEnabled(true);
		}
		else
		{
			_globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.setEnabled(false);
			_globalFitInterface1._globalFitFittingFrame._selectAlgorithmBox.setSelectedIndex(0);
			_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox.setSelected(false);
			_globalFitInterface1._globalFitFittingFrame._advancedSettingsBox1.setSelected(false);
			_globalFitInterface1._globalFitFittingFrame._advancedSettingsDirectBox.setSelected(false);
			_globalFitInterface1._globalFitFittingFrame.setBasicPanel();
		}
		
		
		if(b)
		{
			_globalFitInterface1._globalFitFittingFrame._slopeSlider.setEnabled(true);
		}
		else
		{
			_globalFitInterface1._globalFitFittingFrame._slopeSlider.setEnabled(false);
		}
		
	}
	
	
	
	public int jumpUpToClosestEven(float i)
	{
		int _result;
		float a;
		a = i/2;
		if((a-Math.round(i/2))!=0)
			i++;
		
		_result = Math.round(i);
		
		//System.out.println(i);
		return _result;
		
	}
	
	
}