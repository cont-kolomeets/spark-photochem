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


public class GlobalFitControllerKinControlPanel {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerKinControlPanel(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void addKinControlButtonListeners()
	{
		
		
		_globalFitInterface1._globalFitMainInterface._deleteKinButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			if(_globalFitMath1.get_kinCollectionX().size()!=0)
    			{
        			_globalFitMath1.deleteGlobalFitKin();
        			_parentClass1.graphPanelRefresh();
        			_parentClass1.errorPanelRefresh();

        			if(_globalFitMath1.get_kinCollectionX().size()>0)
        			_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel.setText("" + _globalFitMath1.get_kinCollectionX().get(_globalFitMath1.get_globalFitPosOfSelectedKin()).get(0));
        			_globalFitMath1.extGraphPanelSetXSSpecBoundsX();
        			_parentClass1.extGraphPanelRefresh();
    			}

    			
    		}});
        	
		
		_globalFitInterface1._globalFitMainInterface._saveFitButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			if(_globalFitMath1.get_kinCollectionX().size()!=0)
    			{
    				_parentClass1._transferToClipboard.setClipboardContentsKinetics();
        			_globalFitInterface1._globalFitMainInterface._status.setText(" Kinetic curve and fitting result have been copied to clipboard.");
    			}
     			
    		}});
		
		

        
		_globalFitInterface1._globalFitMainInterface._wavelengthUpButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftGlobalFitKinWavelength(1);}});		
		
		_globalFitInterface1._globalFitMainInterface._wavelengthDownButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftGlobalFitKinWavelength(-1);}});
		
		
	}
	
	
	
	
	
	
	public void shiftGlobalFitKinWavelength(int shift)
	{
		
		if(_globalFitMath1.get_kinCollectionX().size()!=0)
		{
			_globalFitMath1.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin() + shift);
			if(_globalFitMath1.get_globalFitPosOfSelectedKin() <0)_globalFitMath1.set_globalFitPosOfSelectedKin(0);
			if(_globalFitMath1.get_globalFitPosOfSelectedKin() >(_globalFitMath1.get_kinCollectionX().size()-1))_globalFitMath1.set_globalFitPosOfSelectedKin(_globalFitMath1.get_kinCollectionX().size()-1);
			
			_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel.setText("" + _globalFitMath1.get_kinCollectionX().get(_globalFitMath1.get_globalFitPosOfSelectedKin()).get(0));
			
			_parentClass1.graphPanelRefresh();

			_parentClass1.errorPanelRefresh();

			if(_globalFitInterface1._globalFitSigmaFrame.isVisible())
			{
				_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.repaint();
			}
			if(_globalFitInterface1._globalFitExtFrame.isVisible())
			{
				_parentClass1.extGraphPanelRefresh();
			}
			if(_globalFitInterface1._globalFitSliceFrame.isVisible())
			{
				_parentClass1.slicePanelRefresh();
			}
		}


	}
	
	
}