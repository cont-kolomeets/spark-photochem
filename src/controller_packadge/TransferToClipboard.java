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



    public class TransferToClipboard implements ClipboardOwner
    {
    	GlobalFitInterface _globalFitInterface1;
    	GlobalFitMath _globalFitMath1;
    	GlobalFitController _parentClass1;
    	
    	public TransferToClipboard(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
    	{
    		
    		_globalFitInterface1 = _globalFitInterface;
    		_globalFitMath1 = _globalFitMath;
    		_parentClass1 = _parentClass;
    	}
    	
    	   public void lostOwnership( Clipboard aClipboard, Transferable aContents) 
    	   
    	   {
    		     //do nothing
    	   }
    	   
    	   public void setClipboardContentsKinetics()
    	   {
    		    
    		   String s="";
    		   
   		       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
   		    
    		    JTextArea jt = new JTextArea();
    		    
    		   int kinPos = _globalFitMath1.get_globalFitPosOfSelectedKin();
    		    
    		   for(int i=-1; i<_globalFitMath1.get_kinCollectionX().get(kinPos).size(); i++)
    		   {
    			   // wavelength + raw kinetics
    			   if(i==-1)
    				   s += "nm	kin";
    			   else
    				   s += _globalFitMath1.get_kinCollectionX().get(kinPos).get(i) + "	" + (-_globalFitMath1.get_kinCollectionY().get(kinPos).get(i));
    			   
    			   // calculated plot
    			   if(_globalFitMath1.get_solvedCurveCollectionX().size()!=0){
    				   if(i==-1)
    					   s += "	fit";
    				   else
    					   s += "	" + (-_globalFitMath1.get_solvedCurveCollectionY().get(kinPos).get(i));
    			   }
    				   
    			   
    			   // contributions
    			   if(_globalFitMath1.get_solvedCurveContributionCollectionY().size()>0)
    				   for(int j=0; j<_globalFitMath1.get_solvedCurveContributionCollectionY().size();j++){
    					   if(i==-1)
        					   s += "	X" + (j+1);
        				   else
        					   s += "	" + (-_globalFitMath1.get_solvedCurveContributionCollectionY().get(j).get(kinPos).get(i));
    				   }
    				   				   
    			   // calculation error plot
    			   if(_globalFitMath1.get_solvedCurveErrorCollectionY().size()!=0)	{
    				   if(i==-1)
    					   s += "	error";
    				   else
    					   s += "	" + (-_globalFitMath1.get_solvedCurveErrorCollectionY().get(kinPos).get(i));
    			   }
    			   
    			   s = s + "\n"; 		      			   
    		   }
    		   
     		    StringSelection stringSelection = new StringSelection(s);
   		   	   

     		    
    		    clipboard.setContents(stringSelection, this );


    	   }   
    	   
    	   public void setClipboardContentsSpectra()
    	   {
   		    
    		   String s="";
    		   
   		       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
   		    
    		   JTextArea jt = new JTextArea();
    		    
    		   for(int i=0; i<_globalFitMath1.get_availWaveCollection().size(); i++)
    		   {
       			   s= s + _globalFitMath1.get_availWaveCollection().get(i) + "	";
       			        		   
    			   
    			   if(_globalFitInterface1._globalFitSliceFrame._showTransientSpecBox.isSelected())
        			   s= s + (-_globalFitMath1.get_kinCollectionY().get(i).get(_globalFitMath1.get_globalFitPosOfCurrentTimePoint())) + "	";
        		   if(_globalFitInterface1._globalFitSliceFrame._showCalcSpecBox.isSelected())
        		   {
        			   if(_globalFitMath1.get_solvedCurveCollectionY().size()!=0)
            			   s= s + (-_globalFitMath1.get_solvedCurveCollectionY().get(i).get(_globalFitMath1.get_globalFitPosOfCurrentTimePoint())) + "	";
        		   }
            	   if(_globalFitInterface1._globalFitSliceFrame._showContributionsBox.isSelected())
            	   {
           			   if(_globalFitMath1.get_solvedCurveContributionCollectionY().size()!=0)
           				   if(_globalFitMath1.get_solvedCurveContributionCollectionY().get(0).size()!=0)
           					   for(int j=0; j<_globalFitMath1.get_solvedCurveContributionCollectionY().size(); j++)
           						   s= s + (-_globalFitMath1.get_solvedCurveContributionCollectionY().get(j).get(i).get(_globalFitMath1.get_globalFitPosOfCurrentTimePoint())) + "	";
            		   
            	   }
 
            	   s = s + "\n";
    		   }
    		   
     		    StringSelection stringSelection = new StringSelection(s);
     		    clipboard.setContents(stringSelection, this );
    	   }
    	
    	
    }
    
