package interface_packadge;

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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;

import controller_packadge.GlobalFitController;
import controller_packadge.GlobalFitTimer;
import controller_packadge.KineticsModeController;


import math_packadge.GlobalFitMath;
import math_packadge.KineticsModeMath;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 001;
	
	
	public JPanel _mainPain = new JPanel();
	//public JScollPane _mainScrollPain = new JScollPane();
	public KineticsModeInterface _kineticsModeInterface = new KineticsModeInterface();
	public KineticsModeMath _kineticsModeMath = new KineticsModeMath();
	public KineticsModeController _kineticsModeController = new KineticsModeController(_kineticsModeInterface, _kineticsModeMath);

	public GlobalFitInterface _globalFitInterface = new GlobalFitInterface();
	public GlobalFitMath _globalFitMath = new GlobalFitMath();
	public GlobalFitController _globalFitController = new GlobalFitController(_globalFitInterface, _globalFitMath);
	
	
	public int _frameSizeX = Constants._mainFrameInitSizeX, _frameSizeY =  Constants._mainFrameInitSizeY, 
				_rememberFrameSizeX = _frameSizeX, _rememberFrameSizeY = _frameSizeY;
	
	
	public MainFrame()
	{
		createMainFrameInterface();
	}
	
	

	
	
	public void createMainFrameInterface()
	{
		this.setTitle("SPARK");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.getContentPane().setLayout(null);
		

		
		_mainPain.setLayout(null);//new BorderLayout());
		_mainPain.setBounds(0,30, _frameSizeX, _frameSizeY-80);
		_mainPain.setBackground(Color.BLACK);

		
		

		
		
		
		this.getContentPane().add(_mainPain);
		
		showGlobalFitInterface();
	
	}
	
	
	public void showKineticsModeInterface()
	{
		_mainPain.removeAll();
		_mainPain.repaint();
		_mainPain.add(_kineticsModeInterface);

		
	}
	
	public void showGlobalFitInterface()
	{
		_mainPain.removeAll();
		_mainPain.repaint();
		_mainPain.add(_globalFitInterface._globalFitMainInterface);
		
	}
	
	
	
	public void refreshComponentsSize()
	{
		
		int _mainFrameSizeX = this.getSize().width;
		int _mainFrameSizeY = this.getSize().height;
		_mainPain.setBounds(0, 0, _mainFrameSizeX, _mainFrameSizeY-20);
		
		
		
		int _kinModeGraphPanelSizeX = _mainFrameSizeX-175;
		int _kinModeGraphPanelSizeY = _mainFrameSizeY-250;
		int _kinModeErrorPanelSizeX = _mainFrameSizeX-175;
		int _kinModeErrorPanelSizeY = 115;
		
		
		int _globalFitModeGraphPanelSizeX = _mainFrameSizeX-350;
		int _globalFitModeGraphPanelSizeY = _mainFrameSizeY-500;
		int _globalFitModeErrorPanelSizeX = _mainFrameSizeX-350;
		int _globalFitModeErrorPanelSizeY = 115;
		
		
		
		_kineticsModeInterface.setKinPanelComponentsBounds(_mainFrameSizeX, _mainFrameSizeY, _kinModeGraphPanelSizeX, _kinModeGraphPanelSizeY, _kinModeErrorPanelSizeX, _kinModeErrorPanelSizeY);
		_kineticsModeMath.set_gridSizeX(_kinModeGraphPanelSizeX-80);
		_kineticsModeMath.set_gridSizeY(_kinModeGraphPanelSizeY-80);
		_kineticsModeMath.set_gridSizeXError(_kinModeErrorPanelSizeX-80);
		_kineticsModeMath.set_gridSizeYError(_kinModeErrorPanelSizeY-35);
		
		_globalFitInterface._globalFitMainInterface.setGlobalFitPanelComponentsBounds(_mainFrameSizeX, _mainFrameSizeY, _globalFitModeGraphPanelSizeX, _globalFitModeGraphPanelSizeY, _globalFitModeErrorPanelSizeX, _globalFitModeErrorPanelSizeY);
		_globalFitMath.set_gridSizeX(_globalFitModeGraphPanelSizeX-80);
		_globalFitMath.set_gridSizeY(_globalFitModeGraphPanelSizeY-80);
		_globalFitMath.set_gridSizeXError(_globalFitModeErrorPanelSizeX-80);
		_globalFitMath.set_gridSizeYError(80);

		
		
	}
	
	
	

}
