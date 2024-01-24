package interface_packadge;

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



public class GlobalFitProgressBarMonitoring implements ActionListener, PropertyChangeListener 
{
	
	public GlobalFitOptimizationInBackground _optimizationInBackground;
	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;

	
	
	public GlobalFitProgressBarMonitoring(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
	
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_globalFitController1 = _globalFitController;
	}
	
	

    
    
    
    public void actionPerformed(ActionEvent evt) 
    
    {
     	
    	globalFitInitialAction();

    }
	
	
    public void propertyChange(PropertyChangeEvent evt) 
    {
        //System.out.println("prop = " + evt.getPropertyName());
    	
    	if (("progress" == evt.getPropertyName())) 
    		UpdateProgressBars();
    } 
	
    
    
    
    
    public void globalFitInitialAction()
    {
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!Initialization!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	
    		_globalFitInterface1._globalFitFittingFrame._progressPanel.remove(_globalFitInterface1._globalFitFittingFrame._startFittingButton);
			_globalFitInterface1._globalFitFittingFrame._progressPanel.add(_globalFitInterface1._globalFitFittingFrame._stopFittingButton);
			_globalFitInterface1._globalFitFittingFrame._progressPanel.repaint();
			
			_globalFitController1.setWaitingCursorForAllWindows(false);


			_globalFitController1.readSetLimitsTableContent();

			
			_globalFitMath1.set_ifContinueToFit(true);
			_globalFitMath1.readDiffEqLabelsData(true);
			_globalFitController1.readThicknessValue();
			
			if(_globalFitMath1.get_ifCalcCurveShifts())
				_globalFitMath1.calcCurveShifts();
			if(_globalFitMath1.get_ifUseWeight())
				_globalFitMath1.calcWeightsForCurves();
			
			_globalFitMath1.fitRK4();
			
			_globalFitController1.lockGraphPanels(true);
			
			_optimizationInBackground = new GlobalFitOptimizationInBackground(_globalFitInterface1, _globalFitMath1, _globalFitController1);
			_optimizationInBackground.addPropertyChangeListener(this);
			_optimizationInBackground.execute();
    	
    }
    

      
    
	public void UpdateProgressBars()
	{
		
		_globalFitInterface1._globalFitFittingFrame._progressBarFast.setValue(Math.round(_globalFitMath1.get_fastBarProgress()));
		_globalFitInterface1._globalFitFittingFrame._progressBarFast.repaint();
		_globalFitInterface1._globalFitFittingFrame._progressBarSlow.setValue(Math.round(_globalFitMath1.get_slowBarProgress()));
		_globalFitInterface1._globalFitFittingFrame._progressBarSlow.repaint();

	}
    
    
    
    
    
    
} 