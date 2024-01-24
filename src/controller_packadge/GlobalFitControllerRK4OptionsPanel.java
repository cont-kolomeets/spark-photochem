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


public class GlobalFitControllerRK4OptionsPanel {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerRK4OptionsPanel(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	public void addRK4OptionsListener()
	{
		
		_globalFitInterface1._globalFitMainInterface._fitRK4Button.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.fitRK4();

    			
    		
    		}});		

		
		_globalFitInterface1._globalFitMainInterface._setExtButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitInterface1._globalFitExtFrame.setVisible(true);
    			_globalFitInterface1._globalFitExtFrame.toFront();
    			_parentClass1.extGraphPanelRefresh();

    		}});		
		
		
		
		_globalFitInterface1._globalFitMainInterface._showSigmaButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.showSigma();
    		}});	
		
		
		
		
		

		
		
		_globalFitInterface1._globalFitMainInterface._updateAutoBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			boolean _option = _globalFitInterface1._globalFitMainInterface._updateAutoBox.isSelected();
    			_globalFitInterface1._globalFitExtFrame._updateAutoBox.setSelected(_option);

    		}});	
		
		
		_globalFitInterface1._globalFitMainInterface._normToZeroBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			
    			_globalFitMath1.set_ifCalcCurveShifts(_globalFitInterface1._globalFitMainInterface._normToZeroBox.isSelected());
    			_parentClass1.graphPanelRefresh();


    		}});	
		
		_globalFitInterface1._globalFitMainInterface._setCurveBoundsBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{

    			_globalFitMath1.set_ifEnableCurveBounds(_globalFitInterface1._globalFitMainInterface._setCurveBoundsBox.isSelected());
    			_globalFitInterface1._globalFitMainInterface._statusPanel.setVisible(_globalFitInterface1._globalFitMainInterface._setCurveBoundsBox.isSelected());
    			_globalFitMath1.checkmarkers();
    			_parentClass1.checkStatus();
    			_parentClass1.graphPanelRefresh();


    		}});	
		
		_globalFitInterface1._globalFitMainInterface._displayContributionsBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_globalFitMath1.set_ifCalcContributions(_globalFitInterface1._globalFitMainInterface._displayContributionsBox.isSelected());
    			changeLabelsColor();
    		}});
		
		
		_globalFitInterface1._globalFitMainInterface._scaleModeBox.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			int i=_globalFitInterface1._globalFitMainInterface._scaleModeBox.getSelectedIndex();
    			
    			if(i==0)
    			{
    				_globalFitMath1.set_solvedCurveTimeScale(1E6f);
    			}
    			
    			if(i==1)
    			{
    				_globalFitMath1.set_solvedCurveTimeScale(1E3f);
    			}
    			
    			if(i==2)
    			{
    				_globalFitMath1.set_solvedCurveTimeScale(1f);
    			}
 
    		}});	
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void changeLabelsColor()
	{
		if(_globalFitInterface1._globalFitMainInterface._displayContributionsBox.isSelected())
		{
			for(int m=0; m<_globalFitMath1.get_nOfEq() ;m++)
			{
				_globalFitInterface1._globalFitMainInterface._eqLabelsArray[m].setBorder(BorderFactory.createLineBorder(ColorCollection._colorCollection[m],2));
			}
		}
		else
		{
			for(int m=0; m<_globalFitMath1.get_nOfEq() ;m++)
			{
				_globalFitInterface1._globalFitMainInterface._eqLabelsArray[m].setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}

		}
	}
	
	
	
	
}