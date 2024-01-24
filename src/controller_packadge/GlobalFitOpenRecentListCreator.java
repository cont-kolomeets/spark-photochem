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


public class GlobalFitOpenRecentListCreator {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitOpenRecentListCreator(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void createOpenedRecentList()
	{

		
		_globalFitMath1.readOpenedRecentFile();
		if(_globalFitMath1.get_openedRecent().size()!=0)
		{
			_globalFitMath1.set_currentDirectoryOpen(new File(_globalFitMath1.get_openedRecent().get(_globalFitMath1.get_openedRecent().size()-1)));
			_globalFitMath1.set_currentDirectorySave(new File(_globalFitMath1.get_openedRecent().get(_globalFitMath1.get_openedRecent().size()-1)));
			
		}
		for(int i=0; i<12; i++)
		{
			
			_globalFitInterface1._globalFitMainInterface._menuBar._menuFile.remove(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i]);
		}
		
		for(int i=0; i<_globalFitMath1.get_openedRecent().size(); i++)
		{
			_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i] = new JMenuItem(_globalFitMath1.get_openedRecent().get(i));
			_globalFitInterface1._globalFitMainInterface._menuBar._menuFile.add(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i]);
		}
		
		
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[0].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[0].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[1].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[1].getText()));
    	}});
		


		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[2].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[2].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[3].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[3].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[4].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[4].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[5].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[5].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[6].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[6].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[7].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[7].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[8].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[8].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[9].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[9].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[10].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[10].getText()));
    	}});
		
		
		_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[11].addActionListener( new ActionListener()
		{
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	
    	{   
    		openRecentProject(new File(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[11].getText()));
    	}});
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void refreshOpenedRecentList()
	{
		
		for(int i=0; i<12; i++)
		{
			_globalFitInterface1._globalFitMainInterface._menuBar._menuFile.remove(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i]);
		}
		
		for(int i=0; i<_globalFitMath1.get_openedRecent().size(); i++)
		{
			_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i] = new JMenuItem(_globalFitMath1.get_openedRecent().get(i));
			_globalFitInterface1._globalFitMainInterface._menuBar._menuFile.add(_globalFitInterface1._globalFitMainInterface._menuBar._itemArray[i]);
		}
	}
	
	
	
	
	public void openRecentProject(File file)
	{
		_globalFitMath1.proccessGlobalFitProjectFile(file);
		String fileName = file.getPath();
		_globalFitInterface1._globalFitMainInterface._status.setText("Opened project file: " + fileName);
		if(_globalFitMath1.get_kinCollectionX().size()!=0)
		{
       		_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel.setText("" + _globalFitMath1.get_kinCollectionX().get(_globalFitMath1.get_globalFitPosOfSelectedKin()).get(0));
       		_parentClass1.graphPanelRefresh();
       		_parentClass1.extGraphPanelRefresh();
       		_parentClass1.setAllLabelsContent();

		}
		
		
		_globalFitMath1.set_currentDirectoryOpen(file);
		_globalFitMath1.set_currentDirectorySave(file);
		
  		for(int i=0; i<9; i++)
   			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[i].setBorder(BorderFactory.createLineBorder(new Color(150,150,150), 1));
  		_globalFitInterface1._globalFitExtFrame._xSButtonsArray[0].doClick();

		
		//createOpenedRecentList();
		//refreshOpenedRecentList();
	}
}