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


public class GlobalFitControllerDiffEqPanel {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerDiffEqPanel(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void addDiffEqPanelListeners()
	{
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[0].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[0].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[0] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[0].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[0].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[0] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[0].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[0].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[0] = text;
		    			}
			      }});

		
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[1].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[1].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[1] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[1].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[1].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[1] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[1].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[1].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[1] = text;
		    			}
			      }});
			
			
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[2].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[2].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[2] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[2].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[2].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[2] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[2].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[2].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[2] = text;
		    			}
			      }});
			
			
			
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[3].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[3].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[3] = text;
		    			}
			      }
			      });
			

			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[3].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[3].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[3] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[3].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[3].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[3] = text;
		    			}
			      }});
					
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[4].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[4].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[4] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[4].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[4].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[4] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[4].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[4].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[4] = text;
		    			}
			      }});
					
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[5].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[5].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[5] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[5].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[5].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[5] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[5].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[5].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[5] = text;
		    			}
			      }});
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[6].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[6].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[6] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[6].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[6].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[6] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[6].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[6].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[6] = text;
		    			}
			      }});
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[7].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[7].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[7] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[7].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[7].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[7] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[7].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[7].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[7] = text;
		    			}
			      }});
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[8].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[8].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[8] = text;
		    			}
			      }
			      });
			

			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[8].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[8].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[8] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[8].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[8].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[8] = text;
		    			}
			      }});
			
	//////////////////////////		
			
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[9].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent) {

	    				String text = _globalFitInterface1._globalFitMainInterface._eqLabelsArray[9].getText();
	    				
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_eqLabelsContent()[9] = text;
		    			}
			      }
			      });
			
	
			
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[9].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[9].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_concentrationLabelsContent()[9] = text;
		    			}
			      }});

			
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[9].addCaretListener(new CaretListener() {
			      public void caretUpdate(CaretEvent caretEvent)
			      {

			    	  	String text = _globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[9].getText();
			    	  	
		    			if(text!="")
		    			{	
		    				_globalFitMath1.get_beforePulseConcentrationLabelsContent()[9] = text;
		    			}
			      }});
			
			
			
			
			
	}
}