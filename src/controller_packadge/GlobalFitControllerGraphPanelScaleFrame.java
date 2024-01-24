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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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


public class GlobalFitControllerGraphPanelScaleFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerGraphPanelScaleFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void addGraphPanelScaleFrameListeners()
	{
		
		_globalFitInterface1._globalFitGraphPanelScaleFrame._enterButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(false);
    			rescaleGraphPanel();
    		}});

		
		_globalFitInterface1._globalFitGraphPanelScaleFrame._xMaxField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) 
    		{
    		    int key = e.getKeyCode();
   		        
    		    if (key == KeyEvent.VK_ENTER) 
    		    {
        			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(false);
        			rescaleGraphPanel();
    		    }
    		}});
		
		_globalFitInterface1._globalFitGraphPanelScaleFrame._xMinField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) 
    		{
    		    int key = e.getKeyCode();
   		        
    		    if (key == KeyEvent.VK_ENTER) 
    		    {
        			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(false);
        			rescaleGraphPanel();
    		    }
    		}});
		
		_globalFitInterface1._globalFitGraphPanelScaleFrame._yMaxField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) 
    		{
    		    int key = e.getKeyCode();
   		        
    		    if (key == KeyEvent.VK_ENTER) 
    		    {
        			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(false);
        			rescaleGraphPanel();
    		    }
    		}});
		
		_globalFitInterface1._globalFitGraphPanelScaleFrame._yMinField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) 
    		{
    		    int key = e.getKeyCode();
   		        
    		    if (key == KeyEvent.VK_ENTER) 
    		    {
        			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(false);
        			rescaleGraphPanel();
    		    }
    		}});
		
		
	}
	
	
	
	
	public void rescaleGraphPanel()
	{
		
		try
		{
			float _minX, _maxX, _minY, _maxY;;
			float a, b, c, _result, _offSetX, _offSetY;
			//try
			//{
				_minX = Float.parseFloat(_globalFitInterface1._globalFitGraphPanelScaleFrame._xMinField.getText());
				_maxX = Float.parseFloat(_globalFitInterface1._globalFitGraphPanelScaleFrame._xMaxField.getText());
				a = _minX - _globalFitMath1.get_globalFitXMin();
				b = _globalFitInterface1._globalFitMainInterface._graphPanel.get_gridSizeX();
				c = _maxX - _globalFitMath1.get_globalFitXMin();

	
				
				_offSetX = _maxX-_minX-(_globalFitMath1.get_globalFitXMax()-_globalFitMath1.get_globalFitXMin());
				
				_result = b/(c-a);
				_globalFitMath1.set_xScaler(_result);

				//_globalFitMath1.set_xScalerIni(_result);
				
				_result = a*b/(c-a);
				_globalFitMath1.set_magPosX(_result);
				
				
				
				
				_maxY = -Float.parseFloat(_globalFitInterface1._globalFitGraphPanelScaleFrame._yMinField.getText());
				_minY = -Float.parseFloat(_globalFitInterface1._globalFitGraphPanelScaleFrame._yMaxField.getText());
				a = _minY - _globalFitMath1.get_globalFitYMin();
				b = _globalFitInterface1._globalFitMainInterface._graphPanel.get_gridSizeY();
				c = _maxY - _globalFitMath1.get_globalFitYMin();


				_offSetY = _maxY-_minY-(_globalFitMath1.get_globalFitYMax()-_globalFitMath1.get_globalFitYMin());
				
				_result = b/(c-a);
				_globalFitMath1.set_yScaler(_result);
				//_globalFitMath1.set_yScalerIni(_result);	
				
				_result = a*b/(c-a);
				_globalFitMath1.set_magPosY(_result);
			
				

				_globalFitMath1.set_levelPosReal(_globalFitMath1.setLevel(_globalFitMath1.get_levelPosDisplay(),Constants._kineticsModeGridPosition.y,_globalFitMath1.get_globalFitYMin(),_globalFitMath1.get_yScaler()));
				_globalFitMath1.set_zeroPosReal(_globalFitMath1.setZero(_globalFitMath1.get_zeroPosDisplay(),Constants._kineticsModeGridPosition.x,_globalFitMath1.get_globalFitXMin(),_globalFitMath1.get_xScaler()));
				//_globalFitInterface1._globalFitMainInterface._graphPanel.set_offSetX(_offSetX);
				//_globalFitInterface1._globalFitMainInterface._graphPanel.set_offSetY(_offSetY);
				
				
				_parentClass1.graphPanelRefresh();

				
		}
		catch(NumberFormatException e)
		{
			_parentClass1.pushWindowsBack();
			JOptionPane.showMessageDialog(_globalFitInterface1._globalFitMainInterface, "Can't read values!");
			_parentClass1.setWindowsOnTop();
		}
		


		
	}
	
	
	
	public void graphPanelClearOffSet()
	{
		_globalFitInterface1._globalFitMainInterface._graphPanel.set_offSetX(0f);
		_globalFitInterface1._globalFitMainInterface._graphPanel.set_offSetY(0f);
	}
}