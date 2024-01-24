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


public class GlobalFitControllerToolBar {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerToolBar(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	
	public void addToolBarListeners()
	{
		
		_globalFitInterface1._globalFitMainInterface._addKinButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			_parentClass1.openFile();
        	}});
		
		_globalFitInterface1._globalFitMainInterface._clearAllButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {
    			_globalFitMath1.globalFitClearAll();
    			_globalFitMath1.graphPanelSetDefaultBounds();
    			_parentClass1.graphPanelRefresh();
    			_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel.setText("0");
 
        		}});

		
		_globalFitInterface1._globalFitMainInterface._exitButton.addActionListener( new ActionListener()
    		{
        	@Override
        	public void actionPerformed(ActionEvent e) 
        	{
        		System.exit(0);
        	}});		

		
		
		_globalFitInterface1._globalFitMainInterface._saveProjectButton.addActionListener( new ActionListener()
	    	{
	    	@Override
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		_parentClass1.pushWindowsBack();
	    		_parentClass1.getXSLabelsContent();
	    		_globalFitMath1.globalFitSaveProject();
	    		_parentClass1.setWindowsOnTop();
	    		_parentClass1.createOpenedRecentList();
	    	}});		
	        
		
		_globalFitInterface1._globalFitMainInterface._openProjectButton.addActionListener( new ActionListener()
    	{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{
    		_parentClass1.openProject();    		
    	}});
		
		_globalFitInterface1._globalFitMainInterface._snapShotButton.addActionListener( new ActionListener()
    	{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{
    		getSnapShot();    		
    	}});		
		
		
		

		
		
		
	}
	
	
	
	
	public void paintGraphPanelSelectevely(Graphics2D g2d)
	{
	    _globalFitInterface1._globalFitMainInterface._graphPanel._mouseLabel.setText("");
	    _globalFitInterface1._globalFitMainInterface._showSliceButton.setVisible(false);
	    _globalFitInterface1._globalFitMainInterface._magnifyButton.setVisible(false);
	    _globalFitInterface1._globalFitMainInterface._seeAllButton.setVisible(false);
	    _globalFitInterface1._globalFitMainInterface._scaleButton.setVisible(false);
	    _globalFitInterface1._globalFitMainInterface._graphPanel.paint(g2d);
	    _globalFitInterface1._globalFitMainInterface._showSliceButton.setVisible(true);
	    _globalFitInterface1._globalFitMainInterface._magnifyButton.setVisible(true);
	    _globalFitInterface1._globalFitMainInterface._seeAllButton.setVisible(true);
	    _globalFitInterface1._globalFitMainInterface._scaleButton.setVisible(true);
	}
	
	
	
	public void getSnapShot()
	{
		BufferedImage off_Image = (BufferedImage) _globalFitInterface1._globalFitMainInterface._graphPanel.createImage( _globalFitInterface1._globalFitMainInterface._graphPanel.getSize().width, _globalFitInterface1._globalFitMainInterface._graphPanel.getSize().height);
	    Graphics2D g2d = off_Image.createGraphics();
	    
	    paintGraphPanelSelectevely(g2d);
		
		String format = "gif";
        File saveFile = new File("snapshot." + format);
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(saveFile);
        int rval = chooser.showSaveDialog(_globalFitInterface1._globalFitMainInterface);
        if (rval == JFileChooser.APPROVE_OPTION) {
            saveFile = chooser.getSelectedFile();
            /* Write the filtered image in the selected format,
             * to the file chosen by the user.
             */
            try {
                ImageIO.write(off_Image, format, saveFile);
            } catch (IOException ex) {}
            
        }


		
	}
}