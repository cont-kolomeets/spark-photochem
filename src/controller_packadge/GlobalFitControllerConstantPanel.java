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


public class GlobalFitControllerConstantPanel {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerConstantPanel(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addConstantPanelListener()
	{

		
			_globalFitInterface1._globalFitMainInterface._constantLabelsArray[0].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[0].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[0] = text;
	    			}
		      }
		      });
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[1].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[1].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[1] = text;
	    			}
		      }
		      });
		      
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[2].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[2].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[2] = text;
	    			}
		      }
		      });
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[3].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[3].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[3] = text;
	    			}
		      }
		      });
		      
			_globalFitInterface1._globalFitMainInterface._constantLabelsArray[4].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[4].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[4] = text;
	    			}
		      }
		      });
			
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[5].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[5].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[5] = text;
	    			}
		      }
		      });
		      
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[6].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[6].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[6] = text;
	    			}
		      }
		      });
		      
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[7].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[7].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[7] = text;
	    			}
		      }
		      });
		      
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[8].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[8].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[8] = text;
	    			}
		      }
		      });
		      
		      
		      _globalFitInterface1._globalFitMainInterface._constantLabelsArray[9].addCaretListener(new CaretListener() {
		      public void caretUpdate(CaretEvent caretEvent) {

		    	  	String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[9].getText();
		    	  	
	    			if(text!="")
	    			{	
	    				_globalFitMath1.get_constantLabelsContent()[9] = text;
	    			}
		      }
		      });
		
		
		
		
		///////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[0].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			//timer = new Timer();
    			
    			shiftConstantValue(0, _globalFitMath1.get_constantShiftValue());
    			
    			//SwingTimerTask task1 = new SwingTimerTask() {
    			//	public void doRun() 
    			//	{
     					//if(_globalFitInterface1._globalFitMainInterface._constantValueUpButton[0].getModel().isPressed())
    					//{
        		//			Toolkit.getDefaultToolkit().beep();
        	   // 			shiftConstantValue(0, _globalFitMath1.get_constantShiftValue());
    					//}
    					//else
    					//{
    						//this.cancel();
    					//}

    		//		}};
    				
    		//	timer.schedule(task1, 0, 1000);
    			
    		}});
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[1].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(1, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[2].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(2, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[3].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(3, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[4].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(4, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[5].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(5, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[6].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(6, _globalFitMath1.get_constantShiftValue());}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[7].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(7, _globalFitMath1.get_constantShiftValue());}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[8].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(8, _globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueUpButton[9].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(9, _globalFitMath1.get_constantShiftValue());}});
	
		
		
		///////////////
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[0].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(0, -_globalFitMath1.get_constantShiftValue());}});
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[1].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(1, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[2].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(2, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[3].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(3, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[4].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(4, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[5].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(5, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[6].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(6, -_globalFitMath1.get_constantShiftValue());}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[7].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(7, -_globalFitMath1.get_constantShiftValue());}});
		
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[8].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(8, -_globalFitMath1.get_constantShiftValue());}});
		
		
		_globalFitInterface1._globalFitMainInterface._constantValueDownButton[9].addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftConstantValue(9, -_globalFitMath1.get_constantShiftValue());}});
	
		
		
		
	}
	
	
	

	
	
	
	
	
	
	
	
	public void shiftConstantValue(int _numberOfConstant, float _coeff)
	{
		String text = _globalFitInterface1._globalFitMainInterface._constantLabelsArray[_numberOfConstant].getText();
		//System.out.println("initial text = " + text);
		//text = cutStringAfterPoint(text, 3);
		float _value = Float.parseFloat(text);
		_value = _value*(1+_coeff);
		_globalFitMath1.get_constantValues()[_numberOfConstant]= _value;
		text = MethodsCollection.cutStringAfterPoint("" + _value, 3);
		_globalFitInterface1._globalFitMainInterface._constantLabelsArray[_numberOfConstant].setText(text);
		if(_globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected())
		_parentClass1.fitRK4();
	}
	
}