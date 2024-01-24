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


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class GlobalFitSliceFrame extends JFrame{

	
	public int _sliceFrameSizeX=800, _sliceFrameSizeY = 700;
	
	
	
	public GlobalFitSlicePanel _slicePanel = new GlobalFitSlicePanel();
	public JPanel _sliceToolBar = new JPanel();
	public JPanel _currentTimePanel = new JPanel();
	public JPanel _timeStepPanel = new JPanel();
	public JPanel _optionsPanel = new JPanel();
	public JPanel _xSLabelsPanel = new JPanel();
	



	public JButton _saveSpecButton = new JButton("Save Transient", IconCollection._SaveSetIcon);
	public JButton _saveAllSpecButton = new JButton("Save All", IconCollection._SaveOneIcon);
	public JButton _copyToClipButton = new JButton("Copy to Clipboard", IconCollection._CopyToClipboard);
	public JButton _closeButton = new JButton("Close", IconCollection._ExitIcon);
	
	
	
	public JLabel _currentTimeNameLabel = new JLabel("Current time:");
	public JLabel _currentTimeValueLabel = new JLabel("0");	
	
	public JLabel _timeStepNameLabel = new JLabel("Time step:");
	public JLabel _timeStepValueLabel = new JLabel("0");	
	
	public JButton _currentTimeUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _currentTimeDownButton = new JButton(IconCollection._ArrowGrayDown);
	public JButton _timeStepUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _timeStepDownButton = new JButton(IconCollection._ArrowGrayDown);
	
	public JCheckBox _showTransientSpecBox = new JCheckBox("Show transient spectrum");
	public JCheckBox _showCalcSpecBox = new JCheckBox("Show calculated spectrum");
	public JCheckBox _showContributionsBox = new JCheckBox("Show contributions");	

	public JLabel[] _xSLabelsArray = new JLabel[10];
	public JTextField[] _xSNamesArray = new JTextField[10];
	public JLabel _aboveXsLabel = new JLabel("Display");
	public JLabel _xSNameLabel = new JLabel("Name");
	public JButton _refreshButton = new JButton("Refresh", IconCollection._RefreshIcon);

	
	public JButton _vertScaleUp = new JButton(IconCollection._ScaleUpIcon);
	public JButton _vertScaleDown = new JButton(IconCollection._ScaleDownIcon);
	public JButton _horScaleUp = new JButton(IconCollection._ScaleUpIcon);
	public JButton _horScaleDown = new JButton(IconCollection._ScaleDownIcon);
	public JButton _showAllButton = new JButton(IconCollection._RescaleIcon);

	
	Color _BGColor1 = new Color(200,200,200);
	Color _BGColor2 = Color.WHITE;
	Color _BGColor3 = new Color(100,100,100);
	
	
	public GlobalFitSliceFrame()
	{
		createGlobalFitExtFrameInterface();
	}
	

	
	public void createGlobalFitExtFrameInterface()
	{
		this.setTitle("Transient spectra");
		this.setSize(new Dimension(_sliceFrameSizeX,_sliceFrameSizeY));
		//this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_sliceFrameSizeX, Constants._screenDimentions.height-_sliceFrameSizeY-30);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		create_sliceToolBarInterface();
		create_currentTimePanelInterface();
		create_timeStepPanelInterface();
		create_optionsPanelInterface();
		create_xSLabelsPanelInterface();
		createSlicePanelInterface();

		
		this.add(_sliceToolBar);
		this.add(_slicePanel);
		this.add(_currentTimePanel);
		this.add(_timeStepPanel);
		this.add(_optionsPanel);
		this.add(_xSLabelsPanel);
		this.add(_refreshButton);
		
		
		
		setComponentsBounds(_sliceFrameSizeX, _sliceFrameSizeY);
	
		
	}
	
	public void setComponentsBounds(int _extFrameSizeX, int _extFrameSizeY)
	{
		
		
		_sliceToolBar.setBounds(0,0, _extFrameSizeX, 51);	
		_slicePanel.setBounds(200,50, _extFrameSizeX-205,_extFrameSizeY-130);
		_currentTimePanel.setBounds(0,50,200,50);
		_timeStepPanel.setBounds(0,100,200,50);
		_optionsPanel.setBounds(0,150,200,90);
		_xSLabelsPanel.setBounds(0,240,200,380);
		
		_refreshButton.setBounds(_extFrameSizeX-150, _extFrameSizeY-80, 130, 40);
		
		///_slicePanel.set_gridSizeX(_extFrameSizeX-300);
		//_slicePanel.set_gridSizeY(_extFrameSizeY-205);
	}
	
	
	
	public void create_sliceToolBarInterface()
	{
		_sliceToolBar.setBackground(new Color(220, 220, 220));
		_sliceToolBar.setBorder(BorderFactory.createEtchedBorder());
		_sliceToolBar.setLayout(null);
		
		_saveSpecButton.setBounds(0,0,170,50);
		_saveAllSpecButton.setBounds(170,0,170,50);
		_copyToClipButton.setBounds(340,0,170,50);
		_closeButton.setBounds(510,0,120,50);

		_saveSpecButton.setToolTipText("Save only transient spectrum");
		_saveAllSpecButton.setToolTipText("Save all spectra");
		_copyToClipButton.setToolTipText("Copy spectra to clipboard");
		_closeButton.setToolTipText("Close this window");
		

		_sliceToolBar.add(_saveSpecButton);
		_sliceToolBar.add(_saveAllSpecButton);
		_sliceToolBar.add(_copyToClipButton);
		_sliceToolBar.add(_closeButton);

	}
	
	
	public void create_currentTimePanelInterface()
	{
		_currentTimePanel.setBackground(_BGColor2);
		_currentTimePanel.setLayout(null);
		_currentTimePanel.setBorder(BorderFactory.createTitledBorder("Current time"));
		
		_currentTimeValueLabel.setBounds(5,17,150,27);
		_currentTimeValueLabel.setBorder(BorderFactory.createEtchedBorder());
		_currentTimeValueLabel.setFont(FontCollection.font1_17);
		
		_currentTimeUpButton.setBounds(155,17,25,13);
		_currentTimeDownButton.setBounds(155,30,25,13);
		
		//_currentTimePanel.add(_currentTimeNameLabel);
		_currentTimePanel.add(_currentTimeValueLabel);
		_currentTimePanel.add(_currentTimeUpButton);
		_currentTimePanel.add(_currentTimeDownButton);
	}
	
	
	
	public void	create_timeStepPanelInterface()
	{
		_timeStepPanel.setBackground(_BGColor2);
		_timeStepPanel.setLayout(null);
		_timeStepPanel.setBorder(BorderFactory.createTitledBorder("time step"));
		
		
		_timeStepValueLabel.setBounds(5,17,150,27);
		_timeStepValueLabel.setBorder(BorderFactory.createEtchedBorder());
		_timeStepValueLabel.setFont(FontCollection.font1_17);
		
		_timeStepUpButton.setBounds(155,17,25,13);
		_timeStepDownButton.setBounds(155,30,25,13);		
		
		//_timeStepPanel.add(_timeStepNameLabel);
		_timeStepPanel.add(_timeStepValueLabel);
		_timeStepPanel.add(_timeStepUpButton);
		_timeStepPanel.add(_timeStepDownButton);
	}
	
	public void create_optionsPanelInterface()
	{
		_optionsPanel.setBackground(_BGColor2);
		_optionsPanel.setBorder(new LineBorder(Color.BLACK,1));
		_optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		_optionsPanel.setLayout(null);
		
		_showTransientSpecBox.setBounds(5, 20, 180, 20);
		_showTransientSpecBox.setSelected(true);
		_showCalcSpecBox.setBounds(5, 40, 180, 20);
		_showContributionsBox.setBounds(5, 60, 180, 20);
		
	
		_optionsPanel.add(_showTransientSpecBox);
		_optionsPanel.add(_showCalcSpecBox);
		_optionsPanel.add(_showContributionsBox);
		
		
		
		
	}
	
	

	public void create_xSLabelsPanelInterface()
	{
		
		_xSLabelsPanel.setBackground(_BGColor2);
		_xSLabelsPanel.setBorder(new LineBorder(Color.BLACK,1));
		_xSLabelsPanel.setBorder(BorderFactory.createTitledBorder("Components"));
		_xSLabelsPanel.setLayout(null);
		
		
		String s;

		for(int i=0; i<10; i++)
		{
			s="X" + (i+1);
			_xSLabelsArray[i] = new JLabel(s);
			_xSLabelsArray[i].setBounds(10,40+30*(i),60,30);
			_xSLabelsArray[i].setBorder(BorderFactory.createLineBorder(ColorCollection._colorCollection[i],2));
			//_xSLabelsArray[i].setBorder(true);

		}
		
		for(int i=0; i<10; i++)
		{
			
			_xSNamesArray[i] = new JTextField();
			_xSNamesArray[i].setEditable(false);
			_xSNamesArray[i].setBounds(10+65,40+30*(i),120,30);

		}
		
		//for(int i=0; i<2; i++)
		//{
			//_xSLabelsPanel.add(_xSLabelsArray[i]);
			//_xSLabelsPanel.add(_xSNamesArray[i]);
		//}
		
		
		
		_aboveXsLabel.setBounds(15,20,50,20);
		_aboveXsLabel.setForeground(Color.red);
		
		_xSNameLabel.setBounds(80,20,50,20);
		_xSNameLabel.setForeground(Color.blue);
			
		
		
		//_xSLabelsPanel.add(_aboveXsLabel);
		//_xSLabelsPanel.add(_xSNameLabel);
	}
	

	public void createSlicePanelInterface()
	{
		
		_showAllButton.setBounds(205, this.getSize().height-105, 20,20);
		_horScaleDown.setBounds(235, this.getSize().height-105, 20,20);
		_horScaleUp.setBounds(255, this.getSize().height-105, 20,20);
		_vertScaleDown.setBounds(205, this.getSize().height-135, 20,20);
		_vertScaleUp.setBounds(205, this.getSize().height-155, 20,20);

		

		this.add(_showAllButton);
		this.add(_horScaleDown);
		this.add(_horScaleUp);
		this.add(_vertScaleDown);
		this.add(_vertScaleUp);

		
		
	}
	

	public void displayComponentsLabels(int _n)
	{
		_xSLabelsPanel.add(_aboveXsLabel);
		_xSLabelsPanel.add(_xSNameLabel);
		
		for(int i=0; i<10; i++)
		{
			_xSLabelsPanel.remove(_xSLabelsArray[i]);
			_xSLabelsPanel.remove(_xSNamesArray[i]);
		}
		
		for(int i=0; i<_n; i++)
		{
			_xSLabelsPanel.add(_xSLabelsArray[i]);
			_xSLabelsPanel.add(_xSNamesArray[i]);
		}
	}
	
	public void hideComponentsLabels()
	{
		_xSLabelsPanel.removeAll();
	}
	
	
	
	
	public void set_xSLabelsArray(JLabel[] _xSLabelsArray)
	{
		this._xSLabelsArray = _xSLabelsArray;
	}
	
	public JLabel[] get_xSLabelsArray()
	{
		return _xSLabelsArray;
	}
	
	public void set_xSNamesArray(JTextField[] _xSNamesArray)
	{
		this._xSNamesArray = _xSNamesArray;
	}
	
	public JTextField[] get_xSNamesArray()
	{
		return _xSNamesArray;
	}
	
	
	
	
	
	
	
	
}
