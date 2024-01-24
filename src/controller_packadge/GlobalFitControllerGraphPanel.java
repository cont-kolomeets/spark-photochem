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


public class GlobalFitControllerGraphPanel {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerGraphPanel(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void addGraphPanelListeners()
	{
		
		_globalFitInterface1._globalFitMainInterface._scaleButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitGraphPanelScaleFrame.setVisible(true);
    			putRealScaleValuesToFields();
    		}});
		
		_globalFitInterface1._globalFitMainInterface._seeAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.resetGraphPanelParameters();
    			_parentClass1.graphPanelRefresh();

    		}});


		_globalFitInterface1._globalFitMainInterface._magnifyButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			_globalFitMath1.magnifyGraph();
    			_parentClass1.graphPanelRefresh();

    			
    		}});	
        
		_globalFitInterface1._globalFitMainInterface._showSliceButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			if(_globalFitMath1.get_kinCollectionX().size()>1)
    			{
    				_parentClass1.prepareSliceFrameToShow();
    			}
    			else
    			{
    				_parentClass1.pushWindowsBack();
    				JOptionPane.showMessageDialog(_globalFitInterface1._globalFitMainInterface, "Add more kinetic curves");
    				_parentClass1.setWindowsOnTop();
    			}
   			
    		}});	
		
		
	}
	
	
	
	
	

	public void putRealScaleValuesToFields()
	{
	      //_mouseX = (_mouseX + _magPosX - Constants._kineticsModeGridPosition.x)/_xScaler + _globalFitXMin;
	      //_mouseY = -(_mouseY + _magPosY - Constants._kineticsModeGridPosition.y)/_yScaler - _globalFitYMin;
		String _text="";
		float a;
		
		a = (_globalFitMath1.get_magPosX()/_globalFitMath1.get_xScaler() + _globalFitMath1.get_globalFitXMin());
		_text = "" + a;
		_text = MethodsCollection.cutStringAfterPoint(_text, 3);
		_globalFitInterface1._globalFitGraphPanelScaleFrame._xMinField.setText(_text);
		
		a = ((_globalFitInterface1._globalFitMainInterface._graphPanel.get_gridSizeX() + _globalFitMath1.get_magPosX())/_globalFitMath1.get_xScaler() + _globalFitMath1.get_globalFitXMin());
		_text = "" + a;
		_text = MethodsCollection.cutStringAfterPoint(_text, 3);
		_globalFitInterface1._globalFitGraphPanelScaleFrame._xMaxField.setText(_text);
		
		
		a = -(_globalFitMath1.get_magPosY()/_globalFitMath1.get_yScaler() + _globalFitMath1.get_globalFitYMin());
		_text = "" + a;
		_text = MethodsCollection.cutStringAfterPoint(_text, 3);
		_globalFitInterface1._globalFitGraphPanelScaleFrame._yMaxField.setText(_text);
		
		a = -((_globalFitInterface1._globalFitMainInterface._graphPanel.get_gridSizeY() + _globalFitMath1.get_magPosY())/_globalFitMath1.get_yScaler() + _globalFitMath1.get_globalFitYMin());
		_text = "" + a;
		_text = MethodsCollection.cutStringAfterPoint(_text, 3);
		_globalFitInterface1._globalFitGraphPanelScaleFrame._yMinField.setText(_text);

		
		
	}
	
	
	
	
	
	
	
	
	
}