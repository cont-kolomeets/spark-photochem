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


public class GlobalFitControllerMenuBar {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerMenuBar(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}

	
	
	public void addMenuBarListeners()
	{
		
		
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._addKinItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_parentClass1.openFile();
    	}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._clearAllItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
			_globalFitMath1.globalFitClearAll();
			_globalFitMath1.graphPanelSetDefaultBounds();
			_parentClass1.graphPanelRefresh();

    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._openProjectItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{   
    		_parentClass1.openProject();

    	}});
		
		_globalFitInterface1._globalFitMainInterface._menuBar._saveProjectItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_parentClass1.pushWindowsBack();
    		_parentClass1.getXSLabelsContent();
    		_globalFitMath1.globalFitSaveProject();
    		_parentClass1.setWindowsOnTop();
    	}});
		
		_globalFitInterface1._globalFitMainInterface._menuBar._exitItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		System.exit(0);
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._enableAABox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitInterface1._globalFitMainInterface._graphPanel.set_enableAABox(_globalFitInterface1._globalFitMainInterface._menuBar._enableAABox.isSelected());
    		_globalFitInterface1._globalFitMainInterface._graphPanel.repaint();
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._showBackgroundCurvesBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitMath1.set_ifShowBackgroundCurves(_globalFitInterface1._globalFitMainInterface._menuBar._showBackgroundCurvesBox.isSelected());
     		_parentClass1.graphPanelRefresh();
    	}});
		
		_globalFitInterface1._globalFitMainInterface._menuBar._checkIfLocalMinimumButton.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_globalFitInterface1._globalFitMainInterface._status.setText("Please wait...");
    		_globalFitInterface1._globalFitMainInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		int _cubeResol = Integer.parseInt(_globalFitInterface1._globalFitMainInterface._menuBar._cubeResolField.getText());
    		float _cubeSide = Float.parseFloat(_globalFitInterface1._globalFitMainInterface._menuBar._cubeSideField.getText());
    		
    		if(_globalFitMath1.checkIfLocalMinimum(_cubeResol, _cubeSide))
    		{
    			_globalFitInterface1._globalFitMainInterface._status.setText("Calculation with hypercube resolution " + _cubeResol + " and side "+_cubeSide+". Found a local minimum.");	
    		}
    		else
    		{
    			_globalFitInterface1._globalFitMainInterface._status.setText("Calculation with hypercube resolution " + _cubeResol + " and side "+_cubeSide+". This is NOT a local minimum.");	
    		}
    		_globalFitInterface1._globalFitMainInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    	}});
		
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._calcHyperSpaceButton.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		int _nOfIterations = Integer.parseInt(_globalFitInterface1._globalFitMainInterface._menuBar._nOfIterationsField.getText());
    		float _cubeSide = Float.parseFloat(_globalFitInterface1._globalFitMainInterface._menuBar._hyperCubeSideField.getText());
    		float _limit = Float.parseFloat(_globalFitInterface1._globalFitMainInterface._menuBar._hyperLimitField.getText());
    		String Path = _globalFitInterface1._globalFitMainInterface._menuBar._hyperOutputField.getText();
    		String PathGrid = _globalFitInterface1._globalFitMainInterface._menuBar._hyperGridOutputField.getText();
    		String _result = _globalFitMath1.performGlobalFitHyperSpace(_nOfIterations, _cubeSide, Path, PathGrid, _limit);
   
    		_globalFitInterface1._globalFitMainInterface._status.setText(_result);
    	}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._hyperGridBox.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		_globalFitMath1._globalFitHyperSpace._ifWriteGrid = _globalFitInterface1._globalFitMainInterface._menuBar._hyperGridBox.isSelected();
    	}});
		
		
		
		
		
		
		
		
		
		
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._aboutItem.addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
     		_globalFitInterface1._aboutBox.setVisible(true);
    	}});
		
	}
	
	
	
	
}
