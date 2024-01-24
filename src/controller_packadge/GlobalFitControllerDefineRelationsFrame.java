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


public class GlobalFitControllerDefineRelationsFrame {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerDefineRelationsFrame(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	public void addGlobalFitDefineRelationsFrameListeners()
	{
		_globalFitInterface1._globalFitDefineRelationsFrame._nOfEqUpButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftNOfEqRelations(1);}});
		
		_globalFitInterface1._globalFitDefineRelationsFrame._nOfEqDownButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) {shiftNOfEqRelations(-1);}});
		
		_globalFitInterface1._globalFitDefineRelationsFrame._acceptButton.addActionListener(new ActionListener() {
    		@Override
        	public void actionPerformed(ActionEvent e) 
    		{
    			checkConcentrationDependencies();
    		}});
		
		
	}
	
	public void checkConcentrationDependencies()
	{
		for(int i=0; i<_globalFitMath1.get_nOfEqRel(); i++)
		{
			_globalFitMath1.get_concEqLabelsContent()[i] = _globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[i].getText();
		}
		int _error = _globalFitMath1.readConcentrationDependencies();
		_globalFitMath1.findIndependentConcentrations();
		
		
		if(_error!=-1)
		{
			_globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[_error].setBackground(Color.red);
		}
		else
		{
			for(int j=0; j<_globalFitMath1.get_nOfEqRel(); j++)
			{
				if(_globalFitMath1.get_variablesLimitsCollection().get(2).get(j)[3]==0)
					_globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[j].setBackground(new Color(150, 200, 255));
				if(_globalFitMath1.get_variablesLimitsCollection().get(2).get(j)[3]==1)
					_globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[j].setBackground(Color.green);
			}
				
		}
		
		
		
		
		//System.out.println("result = " + _globalFitMath1.getConcentrationFromDependence(0));
	}
	
	
	
	
	
	
	
	
	
	public void shiftNOfEqRelations(int shift)
	{
		_globalFitMath1.set_nOfEqRel(_globalFitMath1.get_nOfEqRel() + shift);
		if(_globalFitMath1.get_nOfEqRel()<0)_globalFitMath1.set_nOfEqRel(0);
		if(_globalFitMath1.get_nOfEqRel() >_globalFitMath1.get_nOfEq())_globalFitMath1.set_nOfEqRel(_globalFitMath1.get_nOfEq());
		
		
	
		if(shift==1)
		{
			_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.add(_globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[_globalFitMath1.get_nOfEqRel()-1]);
			_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.add(_globalFitInterface1._globalFitDefineRelationsFrame._namesArray[_globalFitMath1.get_nOfEqRel()-1]);
		}
		else
		{
			_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.remove(_globalFitInterface1._globalFitDefineRelationsFrame._eqLabelsArray[_globalFitMath1.get_nOfEqRel()]);
			_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.remove(_globalFitInterface1._globalFitDefineRelationsFrame._namesArray[_globalFitMath1.get_nOfEqRel()]);
		}
		_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.repaint();

		String s="" + _globalFitMath1.get_nOfEqRel();
		_globalFitInterface1._globalFitDefineRelationsFrame._nOfEqLabel.setText(s);

		_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.setBounds(1,41,_globalFitInterface1._globalFitDefineRelationsFrame._frameSizeX-7, 5+30*_globalFitMath1.get_nOfEqRel());
		_globalFitInterface1._globalFitDefineRelationsFrame.setSize(_globalFitInterface1._globalFitDefineRelationsFrame._frameSizeX, 70+_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.getSize().height+45);
		_globalFitInterface1._globalFitDefineRelationsFrame._mainPane.setBounds(0, 0, _globalFitInterface1._globalFitDefineRelationsFrame._frameSizeX, 70+_globalFitInterface1._globalFitDefineRelationsFrame._eqPanel.getSize().height-30+45);
		_globalFitInterface1._globalFitDefineRelationsFrame._acceptButton.setBounds(_globalFitInterface1._globalFitDefineRelationsFrame._frameSizeX-130, 5+30*_globalFitMath1.get_nOfEqRel() + _globalFitInterface1._globalFitDefineRelationsFrame._frameSizeY-70, 120, 35);
		
		
	}
	
	
	
}