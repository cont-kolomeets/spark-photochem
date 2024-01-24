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



public class GlobalFitExtFrame extends JFrame{

	
	public int _extFrameSizeX=800, _extFrameSizeY = 740;
	
	
	
	public GlobalFitExtFrameGraphPanel _extGraphPanel = new GlobalFitExtFrameGraphPanel();
	public JPanel _extToolBar = new JPanel();
	public JPanel _extFXPanel = new JPanel();
	public JPanel _extErrorPanel = new JPanel();
	public JPanel _extFSmoothPanel = new JPanel();
	//public JPanel _extInputPanel = new JPanel();
	
	


	public JButton _openSpecButton = new JButton("Open One", IconCollection._OpenFileIcon);
	public JButton _openSeveralSpecButton = new JButton("Open Set", IconCollection._OpenSetIcon);
	public JButton _saveSpecButton = new JButton("Save One", IconCollection._SaveSetIcon);
	public JButton _saveAllSpecButton = new JButton("Save All", IconCollection._SaveOneIcon);
	public JButton _closeButton = new JButton("Close", IconCollection._ExitIcon);
	

	public JButton[] _xSButtonsArray = new JButton[10];
	public JTextField[] _xSNamesArray = new JTextField[10];
	public JLabel _aboveXsLabel = new JLabel("Display");
	public JLabel _xSNameLabel = new JLabel("Name");
	
	public JButton _restoreAllButton = new JButton("Clear All Spec", IconCollection._ClearAllSpecIcon);
	public JButton _restoreSelectedButton = new JButton("Clear Selected", IconCollection._ClearOneIcon);
	
	
	public JCheckBox _updateAutoBox = new JCheckBox("Update Automaticaly");
	public JCheckBox _smoothMoveBox = new JCheckBox("Create bands");
	public JCheckBox _keepAboveZeroBox = new JCheckBox("Keep above zero");
	public JCheckBox _showErrorsBox = new JCheckBox("Show errors:");


	public String[] _list = {"bars", "area"};
	public JComboBox _showErrorsComboBox = new JComboBox(_list);
	
	
	public JSlider _nSmoothPointsSlider = new JSlider();
	public JLabel _nSmoothPointsSliderName = new JLabel("Gauss width / nm");
	
	
	public JButton _undoButton = new JButton(IconCollection._UndoIcon);
	public JButton _redoButton = new JButton(IconCollection._RedoIcon);

	
	public JButton _vertScaleUp = new JButton(IconCollection._ScaleUpIcon);
	public JButton _vertScaleDown = new JButton(IconCollection._ScaleDownIcon);
	public JButton _horScaleUp = new JButton(IconCollection._ScaleUpIcon);
	public JButton _horScaleDown = new JButton(IconCollection._ScaleDownIcon);
	public JButton _showAllButton = new JButton(IconCollection._RescaleIcon);
	public JButton _moveAllButton = new JButton(IconCollection._MultiplyModeIcon);
	public JLabel _multiplyModeOnLabel = new JLabel("Multiply mode is ON!");
	public JButton _fitRK4Button = new JButton("Fit RK4", IconCollection._FitRK4Icon);
	
	public JLabel _absValueLabel = new JLabel("0");
	public JLabel _wavelengthValueLabel = new JLabel("0");
	
	Color _BGColor1 = new Color(200,200,200);
	Color _BGColor2 = Color.WHITE;
	Color _BGColor3 = new Color(100,100,100);
	
	
	public GlobalFitExtFrame()
	{
		createGlobalFitExtFrameInterface();
	}
	

	
	public void createGlobalFitExtFrameInterface()
	{
		this.setTitle("Abs setting");
		this.setSize(new Dimension(_extFrameSizeX,_extFrameSizeY));
		//this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_extFrameSizeX, Constants._screenDimentions.height-_extFrameSizeY-30);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		create_extToolBarInterface();
		create_extFXPanelInterface();
		create_extFSmoothPanelInterface();
		create_extErrorPanelInterface();
		createGraphPanelInterface();

		
		this.add(_extToolBar);
		this.add(_extGraphPanel);
		this.add(_extFXPanel);
		this.add(_extErrorPanel);
		this.add(_extFSmoothPanel);
		
		
		setComponentsBounds(_extFrameSizeX, _extFrameSizeY);
	
		
	}
	
	public void setComponentsBounds(int _extFrameSizeX, int _extFrameSizeY)
	{
		
		
		_extToolBar.setBounds(0,0, _extFrameSizeX, 50);	
		_extGraphPanel.setBounds(200,50, _extFrameSizeX-205,_extFrameSizeY-130);
		_extFXPanel.setBounds(0,50,200,410);
		_extErrorPanel.setBounds(0,460,200,50);
		_extFSmoothPanel.setBounds(0,510,200,195);
		_extGraphPanel.set_gridSizeX(Constants._extGraphPanelGridSizeX);
		_extGraphPanel.set_gridSizeY(Constants._extGraphPanelGridSizeY);
		_fitRK4Button.setBounds(this.getSize().width-160, this.getSize().height-80, 150, 40);

	}
	
	
	
	public void create_extToolBarInterface()
	{
		_extToolBar.setBackground(_BGColor2);
		_extToolBar.setLayout(null);
		
		
		
		
		_openSpecButton.setBounds(0,0,150,50);
		_openSeveralSpecButton.setBounds(150,0,150,50);
		_saveSpecButton.setBounds(300,0,150,50);
		_saveAllSpecButton.setBounds(450,0,150,50);
		_closeButton.setBounds(600,0,120,50);
		
		
		
		_openSpecButton.setToolTipText("Open one spectrum in .csv or .txt");
		_openSeveralSpecButton.setToolTipText("Open several spectra");
		_saveSpecButton.setToolTipText("Save selected spectrum");
		_saveAllSpecButton.setToolTipText("Save all spectra");
		_closeButton.setToolTipText("Close this window");
		
		
		
		_extToolBar.add(_openSpecButton);
		_extToolBar.add(_openSeveralSpecButton);
		_extToolBar.add(_saveSpecButton);
		_extToolBar.add(_saveAllSpecButton);
		_extToolBar.add(_closeButton);

	}
	
	
	
	
	public void create_extFXPanelInterface()
	{
		_extFXPanel.setBackground(_BGColor2);
		_extFXPanel.setBorder(new LineBorder(Color.BLACK,1));
		_extFXPanel.setBorder(BorderFactory.createTitledBorder("Components"));
		//_gridControlPanelKin.setBounds(_graphPanelSizeX, 100, 135, 160);
		_extFXPanel.setLayout(null);
		
		
		String s;
		//_xSNamesArray
		//_aboveXsLabel
		//_xSNameLabel
		//_restoreAllButton
		//_restoreSelectedButton
		
		for(int i=0; i<10; i++)
		{
			s="X" + (i+1);
			_xSButtonsArray[i] = new JButton(s);
			_xSButtonsArray[i].setBounds(10,40+29*(i),60,29);
			_xSButtonsArray[i].setBorderPainted(true);

		}
		
		for(int i=0; i<10; i++)
		{
			
			_xSNamesArray[i] = new JTextField();
			_xSNamesArray[i].setBounds(10+65,40+29*(i),120,29);

		}
		
		for(int i=0; i<2; i++)
		{
			_extFXPanel.add(_xSButtonsArray[i]);
			_extFXPanel.add(_xSNamesArray[i]);
		}
		

		_xSButtonsArray[0].setBorder(BorderFactory.createLineBorder(new Color(200,0,0), 3));
		
		
		_aboveXsLabel.setBounds(15,20,50,20);
		_aboveXsLabel.setForeground(Color.red);
		
		_xSNameLabel.setBounds(80,20,50,20);
		_xSNameLabel.setForeground(Color.blue);
			
		_restoreAllButton.setBounds(10,370,180,35);
		_restoreSelectedButton.setBounds(10,335,180,35);
		
		
		_restoreSelectedButton.setToolTipText("Set all values of absorption coefficient to zero for selected spectrum");
		_restoreAllButton.setToolTipText("Set all values of absorption coefficient to zero for all spectra");
			
		
		_extFXPanel.add(_restoreAllButton);
		_extFXPanel.add(_restoreSelectedButton);
		_extFXPanel.add(_aboveXsLabel);
		_extFXPanel.add(_xSNameLabel);
			
		
		
		
		
	}
	
	
	public void create_extErrorPanelInterface()
	{
		_extErrorPanel.setBackground(_BGColor2);
		_extErrorPanel.setBorder(new LineBorder(Color.BLACK,1));
		_extErrorPanel.setBorder(BorderFactory.createTitledBorder("Errors"));
		_extErrorPanel.setLayout(null);
		
		
		_showErrorsBox.setBounds(10,20,100, 23);
		_showErrorsComboBox.setBounds(110,20,85, 23);
		_showErrorsComboBox.setEnabled(false);
		
		_extErrorPanel.add(this._showErrorsBox);
		_extErrorPanel.add(this._showErrorsComboBox);
	}
	
	
	
	
	public void create_extFSmoothPanelInterface()
	{
		_extFSmoothPanel.setBackground(_BGColor2);
		_extFSmoothPanel.setBorder(new LineBorder(Color.BLACK,1));
		_extFSmoothPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		_extFSmoothPanel.setLayout(null);
		
		
		
		_smoothMoveBox.setBounds(10,60,180, 20);
		_smoothMoveBox.setSelected(false);
		
		_keepAboveZeroBox.setBounds(10,40,180, 20);
		_keepAboveZeroBox.setSelected(true);
		
		_updateAutoBox.setBounds(10,20,180, 20);
		_updateAutoBox.setSelected(false);
		
		_nSmoothPointsSlider.setBounds(10,80,180, 60);
		//_nSmoothPointsSlider.setName("N of points");
		_nSmoothPointsSlider.setMinimum(0);
		_nSmoothPointsSlider.setMaximum(300);
		_nSmoothPointsSlider.setPaintLabels(true);
		_nSmoothPointsSlider.setPaintTicks(true);
		_nSmoothPointsSlider.setPaintTrack(true);
		_nSmoothPointsSlider.setSnapToTicks(true);
		_nSmoothPointsSlider.setValue(0);
		_nSmoothPointsSlider.setEnabled(false);
		
		_nSmoothPointsSlider.setMajorTickSpacing(100);
		_nSmoothPointsSlider.setMinorTickSpacing(20);
		_nSmoothPointsSliderName.setBounds(50, 140, 100, 20);
		
		_undoButton.setBounds(10,160,90,30);
		_undoButton.setEnabled(false);
		_redoButton.setBounds(100,160,90,30);
		_redoButton.setEnabled(false);
		
		_extFSmoothPanel.add(_smoothMoveBox);
		_extFSmoothPanel.add(_updateAutoBox);
		_extFSmoothPanel.add(_keepAboveZeroBox);
		_extFSmoothPanel.add(_nSmoothPointsSlider);
		_extFSmoothPanel.add(_nSmoothPointsSliderName);
		_extFSmoothPanel.add(_undoButton);
		_extFSmoothPanel.add(_redoButton);
	}
	

	public void createGraphPanelInterface()
	{
		
		_fitRK4Button.setBounds(this.getSize().width-160, this.getSize().height-80, 150, 40);
		
		//int _crossPosX=300, _crossPosY=this.getSize().height-80;
		_showAllButton.setBounds(205, this.getSize().height-105, 20,20);
		_horScaleDown.setBounds(235, this.getSize().height-105, 20,20);
		_horScaleUp.setBounds(255, this.getSize().height-105, 20,20);
		_vertScaleDown.setBounds(205, this.getSize().height-135, 20,20);
		_vertScaleUp.setBounds(205, this.getSize().height-155, 20,20);
		_moveAllButton.setBounds(205, this.getSize().height-185, 20,20);
		
		_multiplyModeOnLabel.setBounds(205, this.getSize().height-70, 150 , 20);
		_multiplyModeOnLabel.setForeground(Color.magenta);
		_multiplyModeOnLabel.setVisible(false);
		
		this.add(_moveAllButton);
		this.add(_multiplyModeOnLabel);
		this.add(_fitRK4Button);
		this.add(_showAllButton);
		this.add(_horScaleDown);
		this.add(_horScaleUp);
		this.add(_vertScaleDown);
		this.add(_vertScaleUp);

		
		
	}
	

	
	
	
	
	public void set_xSButtonsArray(JButton[] _xSButtonsArray)
	{
		this._xSButtonsArray = _xSButtonsArray;
	}
	
	public JButton[] get_xSButtonsArray()
	{
		return _xSButtonsArray;
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
