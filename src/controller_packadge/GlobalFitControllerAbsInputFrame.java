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


public class GlobalFitControllerAbsInputFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerAbsInputFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addAbsInputFrameListenters()
	{
		


		
		
		_globalFitInterface1._globalFitInputExtFrame._inputExtButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.makeCopyOf_xSAbsSpecCollectionYFiltered();
    			_globalFitInterface1._globalFitExtFrame._undoButton.setEnabled(true);
    			
    			String text = _globalFitInterface1._globalFitInputExtFrame._inputExtField.getText();

    			try
    			{
        			float _value = _globalFitMath1.get_xSAbsSpecCollectionYFiltered().get(_globalFitMath1.get_globalFitPosOfSelectedSpec()).get(_globalFitMath1.get_numberOfLastClickedRect()) + Float.parseFloat(text);
        			
        			_globalFitMath1.performSmoothMove(-_value*_globalFitMath1.get_yScalerExt());

    			}
    			catch(NumberFormatException a)
    			{
    				_parentClass1.pushWindowsBack();
    				JOptionPane.showMessageDialog(_globalFitInterface1._globalFitMainInterface, "Can't read the number!");
    				_parentClass1.setWindowsOnTop();
    			}
    			//_globalFitMath1.get_xSAbsSpecCollectionYFiltered().get(_globalFitMath1.get_globalFitPosOfSelectedSpec()).set(_globalFitMath1.get_numberOfLastClickedRect(), -_value);
				 _globalFitMath1.set_ifShowExtInputPanel(false);
    			_globalFitInterface1._globalFitInputExtFrame.setVisible(false);
    			_parentClass1.extGraphPanelRefresh();
    			
    			if(_globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected())
    				_parentClass1.fitRK4();
    		}});
		
		
		
		_globalFitInterface1._globalFitInputExtFrame._inputExtField.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) 
    		{
    		    int key = e.getKeyCode();
    		    String text = _globalFitInterface1._globalFitInputExtFrame._inputExtField.getText();
   		        
    		    if ((key == KeyEvent.VK_ENTER)&(!text.equals(""))) 
    		    {
        			//System.out.println("Enter pressed");
    		    	_globalFitMath1.makeCopyOf_xSAbsSpecCollectionYFiltered();
    		    	_globalFitInterface1._globalFitExtFrame._undoButton.setEnabled(true);
    		    	
        			
        			try
        			{
            			float _value = _globalFitMath1.get_xSAbsSpecCollectionYFiltered().get(_globalFitMath1.get_globalFitPosOfSelectedSpec()).get(_globalFitMath1.get_numberOfLastClickedRect()) + Float.parseFloat(text);
            			
            			_globalFitMath1.performSmoothMove(-_value*_globalFitMath1.get_yScalerExt());

        			}
        			catch(NumberFormatException a)
        			{
        				_parentClass1.pushWindowsBack();
        				JOptionPane.showMessageDialog(_globalFitInterface1._globalFitMainInterface, "Can't read the number!");
        				_parentClass1.setWindowsOnTop();
        			}
    		    	
        			//_globalFitMath1.get_xSAbsSpecCollectionYFiltered().get(_globalFitMath1.get_globalFitPosOfSelectedSpec()).set(_globalFitMath1.get_numberOfLastClickedRect(), -_value);
   				 	_globalFitMath1.set_ifShowExtInputPanel(false);
        			_globalFitInterface1._globalFitInputExtFrame.setVisible(false);
        			_parentClass1.extGraphPanelRefresh();
        			
       			 if(_globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected())
       				_parentClass1.fitRK4();
    		    }
    		}});
		
		
		/*_globalFitInterface1._globalFitInputExtFrame.addComponentListener(new ComponentAdapter() 
	    {
			 
	        public void componentHidden(ComponentEvent evt) {
	          Component c = (Component) evt.getSource();
	          
	          System.out.println("Hidden!");//_globalFitInterface1._globalFitInputExtFrame.isFocusOwner());
	        }
	    });*/
		
		
	}
}