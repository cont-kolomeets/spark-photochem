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


public class GlobalFitControllerSliceFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerSliceFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addGlobalFitSliceFrameListeners()
	{
		_globalFitInterface1._globalFitSliceFrame._currentTimeUpButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftCurrentTimePoint(1);
    		}});
		
		_globalFitInterface1._globalFitSliceFrame._currentTimeDownButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftCurrentTimePoint(-1);
    		}});
		
		_globalFitInterface1._globalFitSliceFrame._timeStepUpButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftTimeStep(1);
    		}});
		
		_globalFitInterface1._globalFitSliceFrame._timeStepDownButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			shiftTimeStep(-1);
    		}});
		
		
		_globalFitInterface1._globalFitSliceFrame._closeButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitSliceFrame.setVisible(false);
    			_globalFitMath1.set_ifShowSlice(false);
    			_parentClass1.graphPanelRefresh();
    		}});
		
		
		_globalFitInterface1._globalFitSliceFrame._showCalcSpecBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitSliceFrame._showCalcSpecBox.isSelected();
    			_globalFitInterface1._globalFitSliceFrame._slicePanel.set_ifShowCalculatedSpec(_option);
    			_globalFitMath1.set_ifShowCalcSpec(_option);
    			_parentClass1.slicePanelRefresh();
    		}});
		
		
		_globalFitInterface1._globalFitSliceFrame._showContributionsBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitSliceFrame._showContributionsBox.isSelected();
    			_globalFitInterface1._globalFitSliceFrame._slicePanel.set_ifShowContributions(_option);
    			_globalFitMath1.set_ifShowContributionsSpec(_option);
    			if(_option)
    			{
    	   			_globalFitInterface1._globalFitSliceFrame.displayComponentsLabels(_globalFitMath1.get_nOfEq());

    			}
    			else
    			{
    				_globalFitInterface1._globalFitSliceFrame.hideComponentsLabels();
    			}
 
    			
    			_parentClass1.slicePanelRefresh();
    		}});	
		
		_globalFitInterface1._globalFitSliceFrame._showTransientSpecBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitSliceFrame._showTransientSpecBox.isSelected();
    			_globalFitInterface1._globalFitSliceFrame._slicePanel.set_ifShowTransientSpec(_option);
    			_globalFitMath1.set_ifShowTransientSpec(_option);
    			_parentClass1.slicePanelRefresh();
    		}});	
		
		
		
		
		_globalFitInterface1._globalFitSliceFrame._saveSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_globalFitMath1.globalFitSaveOneTransientSlice();
				_parentClass1.setWindowsOnTop();
    		}});



		_globalFitInterface1._globalFitSliceFrame._saveAllSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_globalFitMath1.globalFitSaveAllSlices();
				_parentClass1.setWindowsOnTop();
    		}});



		_globalFitInterface1._globalFitSliceFrame._copyToClipButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			if(_globalFitMath1.get_kinCollectionX().size()!=0)
    			{
    				_parentClass1._transferToClipboard.setClipboardContentsSpectra();
        			_globalFitInterface1._globalFitMainInterface._status.setText(" Spectra have been copied to clipboard.");
    			}
     			
    		}});
		
		
		
		_globalFitInterface1._globalFitSliceFrame._showAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			if(_globalFitMath1.get_kinCollectionX().size()!=0)
    			{
        			_globalFitMath1.slicePanelRecalcSpecBounds();
        			_globalFitMath1.set_magPosXSlice(0);
        			_globalFitMath1.set_magPosYSlice(0);
        			_parentClass1.slicePanelRefresh();
    			}

    	
    		}});	
		
		
		_globalFitInterface1._globalFitSliceFrame._vertScaleDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_yScalerSlice(_globalFitMath1.get_yScalerSlice()/2);
    			_globalFitMath1.set_yScalerIniSlice(_globalFitMath1.get_yScalerIniSlice()/2);
    			_globalFitMath1.set_yMinSlice(_globalFitMath1.get_yMinSlice()*2);
    		    			
    			_parentClass1.slicePanelRefresh();
    		
    		}});	
			
		_globalFitInterface1._globalFitSliceFrame._vertScaleUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_yScalerSlice(_globalFitMath1.get_yScalerSlice()*2);
    			_globalFitMath1.set_yScalerIniSlice(_globalFitMath1.get_yScalerIniSlice()*2);
    			_globalFitMath1.set_yMinSlice(_globalFitMath1.get_yMinSlice()/2);
    		    			
    			_parentClass1.slicePanelRefresh();
    
    		}});			
		
		_globalFitInterface1._globalFitSliceFrame._horScaleDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_xScalerSlice(_globalFitMath1.get_xScalerSlice()/2);
    			_globalFitMath1.set_xScalerIniSlice(_globalFitMath1.get_xScalerIniSlice()/2);
    			_globalFitMath1.set_xMaxSlice(_globalFitMath1.get_xMinSlice()+(_globalFitMath1.get_xMaxSlice()-_globalFitMath1.get_xMinSlice())*2);
    		    			
    			_parentClass1.slicePanelRefresh();
    	
    		}});	
			
		
		_globalFitInterface1._globalFitSliceFrame._horScaleUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_xScalerSlice(_globalFitMath1.get_xScalerSlice()*2);
    			_globalFitMath1.set_xScalerIniSlice(_globalFitMath1.get_xScalerIniSlice()*2);
    			_globalFitMath1.set_xMaxSlice(_globalFitMath1.get_xMinSlice()+(_globalFitMath1.get_xMaxSlice()-_globalFitMath1.get_xMinSlice())/2);
    		    			
    			_parentClass1.slicePanelRefresh();

    		}});
		
		
		_globalFitInterface1._globalFitSliceFrame._refreshButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.prepareSliceFrameToShow();
    		}});
		

		
		
		_globalFitInterface1._globalFitSliceFrame.addComponentListener(new ComponentAdapter() 
	    {
			 
	        public void componentHidden(ComponentEvent evt) {
	        	_globalFitMath1.set_ifShowSlice(false);
	        	_parentClass1.graphPanelRefresh();
	        }
	    });
		

		
		
		
		
		
	}
	
	
	
	
	
	public void shiftTimeStep(int _shift)
	{
		int _ts = _globalFitMath1.get_timeStep();
		float _interval = _globalFitMath1.calcTimeInterval();
		
		_ts = _ts+_shift;
		if(_ts<1)
			_ts = 1;
		
		if(_ts>100)
			_ts=100;
		
		_globalFitMath1.set_timeStep(_ts);
		_globalFitInterface1._globalFitSliceFrame._timeStepValueLabel.setText(MethodsCollection.cutStringAfterPoint(""+_ts*_interval, 5));
	}
	
	
	
	
	
	public void shiftCurrentTimePoint(int _shift)
	{
		int _ts = _globalFitMath1.get_timeStep();
		int _pos = _globalFitMath1.get_globalFitPosOfCurrentTimePoint();
		
		_pos=_pos+_ts*_shift;
		if(_pos<1)
			_pos=1;
		if(_pos>(_globalFitMath1.get_kinCollectionX().get(0).size()-1))
			_pos = _globalFitMath1.get_kinCollectionX().get(0).size()-1;
		
		_globalFitMath1.set_globalFitPosOfCurrentTimePoint(_pos);
		try
		{
			_globalFitInterface1._globalFitSliceFrame._currentTimeValueLabel.setText(MethodsCollection.cutStringAfterPoint(""+_globalFitMath1.get_kinCollectionX().get(0).get(_pos),5));
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			
		}
		
		_globalFitMath1._slicePosDisplay = _globalFitMath1.get_kinCollectionX().get(0).get(_pos);
		_globalFitMath1._slicePosReal = _globalFitMath1.setZero(_globalFitMath1._slicePosDisplay, Constants._kineticsModeGridPosition.x, _globalFitMath1._globalFitXMin, _globalFitMath1._xScaler);

		_parentClass1.slicePanelRefresh();
		_parentClass1.graphPanelRefresh();
	}
	
	
	
	
	
	
	
	
}