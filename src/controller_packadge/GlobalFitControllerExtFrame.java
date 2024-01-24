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


public class GlobalFitControllerExtFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerExtFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addExtFrameListeners()
	{
		
		
		
		
		_globalFitInterface1._globalFitExtFrame.addComponentListener(new ComponentAdapter() 
	    {
			 
	        public void componentResized(ComponentEvent evt) {
	          Component c = (Component) evt.getSource();
	  		
	          
	          //_globalFitMath1.set_gridSizeXExt(_globalFitInterface1._globalFitExtFrame.getSize().width-300);
	          //_globalFitMath1.set_gridSizeYExt(_globalFitInterface1._globalFitExtFrame.getSize().height-235);
	          //_globalFitMath1.extGraphPanelRecalcSpecBounds();
	          _globalFitInterface1._globalFitExtFrame.setComponentsBounds(_globalFitInterface1._globalFitExtFrame.getSize().width,_globalFitInterface1._globalFitExtFrame.getSize().height);
	          
	        }
	    });
		
		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._saveAllSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_parentClass1.getXSLabelsContent();
				_globalFitMath1.globalFitSaveAllSpec();
				_parentClass1.setWindowsOnTop();
    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._saveSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_parentClass1.getXSLabelsContent();
				_globalFitMath1.globalFitSaveOneSpec();
				_parentClass1.setWindowsOnTop();
    		}});
			
		
		_globalFitInterface1._globalFitExtFrame._openSeveralSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_globalFitMath1.globalFitOpenSpectra();
				_parentClass1.extGraphPanelRefresh();
				_parentClass1.setAllLabelsContent();

				_parentClass1.setWindowsOnTop();

				//_globalFitMath1.globalFitSaveOneSpec();
    		}});
		
		
		
		_globalFitInterface1._globalFitExtFrame._openSpecButton.addActionListener(new ActionListener() {
	    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
				_parentClass1.pushWindowsBack();
				_globalFitMath1.globalFitOpenOneSpectrum();
				_parentClass1.extGraphPanelRefresh();
        		//setAllLabelsContent();

				_parentClass1.setWindowsOnTop();

				//_globalFitMath1.globalFitSaveOneSpec();
    		}});
			
		
		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[0].addActionListener(new ActionListener() {
    	
			@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[0].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=0)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(0);
    			_parentClass1.extGraphPanelRefresh();

    		}});
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[1].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[1].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=1)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(1);
    			//_globalFitInterface1._globalFitExtFrame._xSButtonsArray[1].setBackground(Color.green);
    			_parentClass1.extGraphPanelRefresh();

    		}});		
		
		
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[2].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[2].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=2)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(2);
    			_parentClass1.extGraphPanelRefresh();
 
    		}});			
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[3].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[3].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=3)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(3);
    			_parentClass1.extGraphPanelRefresh();

    		}});			
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[4].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[4].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=4)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(4);
    			_parentClass1.extGraphPanelRefresh();
 
    		}});		
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[5].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[5].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=5)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(5);
    			_parentClass1.extGraphPanelRefresh();
 
    		}});		
		
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[6].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[6].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=6)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(6);
    			_parentClass1.extGraphPanelRefresh();
    
    		}});		
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[7].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[7].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=7)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(7);
    			_parentClass1.extGraphPanelRefresh();
   
    		}});		
						
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[8].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[8].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=8)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(8);
    			_parentClass1.extGraphPanelRefresh();
    
    		}});		
							
		
		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[9].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[9].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
    			if(_globalFitMath1.get_globalFitPosOfSelectedSpec()!=9)
    			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[_globalFitMath1.get_globalFitPosOfSelectedSpec()].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
    			_globalFitMath1.set_globalFitPosOfSelectedSpec(9);
    			_parentClass1.extGraphPanelRefresh();

    		}});		
							
			
		
		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._showAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			if(_globalFitMath1.get_kinCollectionX().size()!=0)
    			{
        			_globalFitMath1.extGraphPanelRecalcSpecBounds();
        			_globalFitMath1.set_magPosXExt(0);
        			_globalFitMath1.set_magPosYExt(0);
        			_parentClass1.extGraphPanelRefresh();
    			}

    	
    		}});	
		
		
		_globalFitInterface1._globalFitExtFrame._vertScaleDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_yScalerExt(_globalFitMath1.get_yScalerExt()/2);
    			_globalFitMath1.set_yScalerIniExt(_globalFitMath1.get_yScalerIniExt()/2);
    			_globalFitMath1.set_yMinExt(_globalFitMath1.get_yMinExt()*2);
    		    			
    			_parentClass1.extGraphPanelRefresh();
    		
    		}});	
			
		_globalFitInterface1._globalFitExtFrame._vertScaleUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_yScalerExt(_globalFitMath1.get_yScalerExt()*2);
    			_globalFitMath1.set_yScalerIniExt(_globalFitMath1.get_yScalerIniExt()*2);
    			_globalFitMath1.set_yMinExt(_globalFitMath1.get_yMinExt()/2);
    		    			
    			_parentClass1.extGraphPanelRefresh();
    
    		}});			
		
		_globalFitInterface1._globalFitExtFrame._horScaleDown.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_xScalerExt(_globalFitMath1.get_xScalerExt()/2);
    			_globalFitMath1.set_xScalerIniExt(_globalFitMath1.get_xScalerIniExt()/2);
    			_globalFitMath1.set_xMaxExt(_globalFitMath1.get_xMinExt()+(_globalFitMath1.get_xMaxExt()-_globalFitMath1.get_xMinExt())*2);
    		    			
    			_parentClass1.extGraphPanelRefresh();
    	
    		}});	
			
		
		_globalFitInterface1._globalFitExtFrame._horScaleUp.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_xScalerExt(_globalFitMath1.get_xScalerExt()*2);
    			_globalFitMath1.set_xScalerIniExt(_globalFitMath1.get_xScalerIniExt()*2);
    			_globalFitMath1.set_xMaxExt(_globalFitMath1.get_xMinExt()+(_globalFitMath1.get_xMaxExt()-_globalFitMath1.get_xMinExt())/2);
    		    			
    			_parentClass1.extGraphPanelRefresh();

    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._moveAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			_globalFitMath1.set_ifEnableMoveAllMarkersMode(!_globalFitMath1.get_ifEnableMoveAllMarkersMode());
    			_globalFitInterface1._globalFitExtFrame._multiplyModeOnLabel.setVisible(_globalFitMath1.get_ifEnableMoveAllMarkersMode());
    		}});
		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._restoreSelectedButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.restoreSelectedXSpec();
    		    			
    			_parentClass1.extGraphPanelRefresh();
    	
    		}});
		
		_globalFitInterface1._globalFitExtFrame._restoreAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.restoreAllXSSpec();
    		    			
    			_parentClass1.extGraphPanelRefresh();
    		
    		}});		
		
		
		
		_globalFitInterface1._globalFitExtFrame._closeButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame.setVisible(false);
    		}});		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._smoothMoveBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitExtFrame._smoothMoveBox.isSelected();
    			_globalFitMath1.set_ifEnableSmoothMove(_option);
    			_globalFitInterface1._globalFitExtFrame._nSmoothPointsSlider.setEnabled(_option);
    			if(_option)
    			{
        			_globalFitMath1.set_nOfPointsSmoothMove(_globalFitInterface1._globalFitExtFrame._nSmoothPointsSlider.getValue());
    			}
    			else
    			{
        			_globalFitMath1.set_nOfPointsSmoothMove(0);
    			}
    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._keepAboveZeroBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitExtFrame._keepAboveZeroBox.isSelected();
    			_globalFitMath1.set_ifKeepAboveZero(_option);
    			
    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._showErrorsBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitExtFrame._showErrorsBox.isSelected();
    			_globalFitInterface1._globalFitExtFrame._showErrorsComboBox.setEnabled(_option);
    			_globalFitMath1.set_ifShowErrorExt(_option);
    			_parentClass1.extGraphPanelRefresh();
    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._showErrorsComboBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = (_globalFitInterface1._globalFitExtFrame._showErrorsComboBox.getSelectedIndex()==0);
    			_globalFitMath1.set_ifShowErrorExtWithBars(_option);
    			_parentClass1.extGraphPanelRefresh();
    		}});
		
		
		_globalFitInterface1._globalFitExtFrame._undoButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			_globalFitMath1.performUndo();
    			_parentClass1.extGraphPanelRefresh();
    		
    			_globalFitInterface1._globalFitExtFrame._redoButton.setEnabled(true);
    			_globalFitInterface1._globalFitExtFrame._undoButton.setEnabled(false);
    			
    		}});
		
		_globalFitInterface1._globalFitExtFrame._redoButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			_globalFitMath1.performRedo();
    			_parentClass1.extGraphPanelRefresh();
    	
    			_globalFitInterface1._globalFitExtFrame._redoButton.setEnabled(false);
    			_globalFitInterface1._globalFitExtFrame._undoButton.setEnabled(true);
    			
    		}});
		
		
		
		_globalFitInterface1._globalFitExtFrame._nSmoothPointsSlider.addChangeListener(new ChangeListener() {
    		@Override
        	public void stateChanged(ChangeEvent e) 
    		{
    			//boolean _option = _globalFitInterface1._globalFitExtFrame._smoothMoveBox.isSelected();
    			//_globalFitMath1.set_ifEnableSmoothMove(_option);
    			//_globalFitInterface1._globalFitExtFrame._nSmoothPointsSlider.setEnabled(_option);
    			_globalFitMath1.set_nOfPointsSmoothMove(_globalFitInterface1._globalFitExtFrame._nSmoothPointsSlider.getValue());
    		}});
		
		
		

		
		
		_globalFitInterface1._globalFitExtFrame._fitRK4Button.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.fitRK4();
     		}});
		
		
		
		
		
		_globalFitInterface1._globalFitExtFrame._updateAutoBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected();
    			_globalFitInterface1._globalFitMainInterface._updateAutoBox.setSelected(_option);

    		}});
		
		
		
		
		
		
		
	}
}