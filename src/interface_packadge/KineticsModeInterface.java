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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public final class KineticsModeInterface extends JPanel{
	
	//public int _paneSizeX=100, _paneSizeY=100;

	public KineticsModeMenuBar _menuBar = new KineticsModeMenuBar();
	public KineticsModeGraphPanel _graphPanel = new KineticsModeGraphPanel();
	public KineticsModeErrorPanel _errorPanel = new KineticsModeErrorPanel();
	public AboutBox _aboutBox = new AboutBox();
	public Fit1expInterface _fit1expInterface  = new Fit1expInterface();
	public Fit2OrderInterface _fit2OrderInterface  = new Fit2OrderInterface();
	public KinecticsModeFit2OrderProductInterface _kinecticsModeFit2OrderProductInterface = new KinecticsModeFit2OrderProductInterface();
	public JPanel _levelPanelKin = new JPanel();
	public JPanel _gridControlPanelKin = new JPanel();
	public JPanel _zeroPanelKin = new JPanel();
	public JPanel _markerPanelKin = new JPanel();
	public ImagePanel _statusOuterPanelKin = new ImagePanel();
	public JPanel _statusInnerPanel = new JPanel();
	public IconCollection _iconCollection = new IconCollection();
	public FontCollection _fontCollection = new FontCollection();
	public JPanel _toolBarKin = new JPanel();
	public String[] _modeString = {"Select mode","Kinetics", "Global Fit"};
	public JComboBox _modeBox = new JComboBox(_modeString);
	public JLabel _status;
	
	public JLabel 
		_levelLabel, 
		_zeroLabel,
		_zeroLabelHS,
		_timeLabel, 
		_smoothKinLabel,
		_fitMarkerFirstLabel,
		_fitMarkerLastLabel; 
	
	// Toolbar buttons
	
	public JButton _openFileButton = new JButton("Open File", _iconCollection._OpenFileIcon);
	public JButton _exitButton = new JButton("Exit", _iconCollection._ExitIcon);
	public JButton _convertFileButton = new JButton("Convert and Save", _iconCollection._SaveFileIcon);
	public JButton _snapShotButton = new JButton(_iconCollection._SnapShotIcon);
	
	
	
	// Control buttons
	public JButton _setLevelUp = new JButton(_iconCollection._UpRedIcon);
	public JButton _setLevelDown = new JButton(_iconCollection._DownRedIcon);
	public JButton _setLevelUpFast = new JButton(_iconCollection._UpBlueIcon);
	public JButton _setLevelDownFast = new JButton(_iconCollection._DownBlueIcon);

	
	public JButton _setLevelZero = new JButton("Zero");
	public JButton _setLineCenter = new JButton("  Lines Center  ", _iconCollection._LinesCenterIcon);
	public JButton _setMagnify = new JButton("          Magnify  ", _iconCollection._MagnifyIcon);
	public JButton _trimGraph = new JButton("              Trim   ", _iconCollection._TrimIcon);
	public JButton _smoothKin = new JButton("              Smooth   ", _iconCollection._TrimIcon);
	public JButton _setSmoothPointsUp = new JButton(_iconCollection._RightRedIcon);
	public JButton _setSmoothPointsDown = new JButton(_iconCollection._LeftRedIcon);

	
	JCheckBoxMenuItem _fixViewBox = new JCheckBoxMenuItem("Fix View");
	
	public JButton _setZeroLeft = new JButton(_iconCollection._LeftRedIcon);
	public JButton _setZeroRight = new JButton(_iconCollection._RightRedIcon);
	public JButton _setZeroLeftFast = new JButton(_iconCollection._LeftBlueIcon);
	public JButton _setZeroRightFast = new JButton(_iconCollection._RigthBlueIcon);
	public JButton _setTimeZero = new JButton("       Zero Time", _iconCollection._TimerIcon);

	public JButton _setFitMarkerFirstLeft = new JButton(_iconCollection._LeftRedIcon);
	public JButton _setFitMarkerFirstRight = new JButton(_iconCollection._RightRedIcon);
	public JButton _setFitMarkerFirstLeftFast = new JButton(_iconCollection._LeftBlueIcon);
	public JButton _setFitMarkerFirstRightFast = new JButton(_iconCollection._RigthBlueIcon);
	
	public JButton _setFitMarkerLastLeft = new JButton(_iconCollection._LeftRedIcon);
	public JButton _setFitMarkerLastRight = new JButton(_iconCollection._RightRedIcon);
	public JButton _setFitMarkerLastLeftFast = new JButton(_iconCollection._LeftBlueIcon);
	public JButton _setFitMarkerLastRightFast = new JButton(_iconCollection._RigthBlueIcon);
	public JButton _1expFitButton = new JButton("1st order");
	public JButton _2expFitButton = new JButton("2 exp Fit");
	public JButton _12OrderFitButton = new JButton("1st+2nd");
	public JButton _2OrderProductFitButton = new JButton("2nd order product");
	public JButton _2OrderFitButton = new JButton("2nd order");
	public JButton _averageButton = new JButton("Average");
	
	
	
	
	
	
	
	
	
	
	
	public KineticsModeInterface()
	{
		
		createKineticsModeInterface();
	}
	
	
	
	
	
	
	
	
	
	
	
	public void createKineticsModeInterface()
	{

		// Add a preWorking panel
		initializeLabels();
		createKineticsModeToolBar();
	
		
		this.setBackground(new Color(200,200,200));
		this.setBorder(new LineBorder(Color.BLACK,1));
		this.setLayout(null);
		
		// Create Working Panels
		
		Color _BGColor1 = new Color(255,255,255);
		Color _BGColor2 = Color.WHITE;
		Color _BGColor3 = new Color(100,100,100);
	
		

		_levelPanelKin.setBackground(_BGColor2);
		_levelPanelKin.setBorder(new LineBorder(Color.BLACK,1));
		_levelPanelKin.setBorder(BorderFactory.createTitledBorder("Set Level"));
		//_levelPanelKin.setBounds(_graphPanelSizeX, 0, 135, 100);
		_levelPanelKin.setLayout(null);		

		_zeroPanelKin.setBackground(_BGColor2);
		_zeroPanelKin.setBorder(new LineBorder(Color.BLACK,1));
		_zeroPanelKin.setBorder(BorderFactory.createTitledBorder("Zero Time"));
		//_zeroPanelKin.setBounds(_graphPanelSizeX, 260, 135, 105);
		_zeroPanelKin.setLayout(null);		

		_markerPanelKin.setBackground(_BGColor2);
		_markerPanelKin.setBorder(new LineBorder(Color.BLACK,1));
		_markerPanelKin.setBorder(BorderFactory.createTitledBorder("Markers"));
		//_markerPanelKin.setBounds(_graphPanelSizeX, 365, 135, 250);
		_markerPanelKin.setLayout(null);		

		
		_gridControlPanelKin.setBackground(_BGColor2);
		_gridControlPanelKin.setBorder(new LineBorder(Color.BLACK,1));
		_gridControlPanelKin.setBorder(BorderFactory.createTitledBorder("Edit"));
		//_gridControlPanelKin.setBounds(_graphPanelSizeX, 100, 135, 160);
		_gridControlPanelKin.setLayout(null);		

		_statusOuterPanelKin.setBackground(_BGColor3);
		_statusOuterPanelKin.setBorder(BorderFactory.createLoweredBevelBorder());
		//_statusOuterPanelKin.setBounds(_graphPanelSizeX, 615, 135, _graphPanelSizeY-150-250-100-115);
		_statusOuterPanelKin.setLayout(null);		
		
		_statusInnerPanel.setBackground(Color.green);
		_statusInnerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		//_statusInnerPanel.setBounds(10, 10, 115, _graphPanelSizeY-150-250-100-100-35);
		_statusInnerPanel.setLayout(null);		
		
		//setKinPanelComponentsBounds();
		


		this.add(_menuBar);
	 	this.add(_toolBarKin);	
		this.add(_graphPanel);
		this.add(_errorPanel);
		this.add(_levelPanelKin);
		this.add(_gridControlPanelKin);
		this.add(_zeroPanelKin);
		this.add(_markerPanelKin);
		this.add(_statusOuterPanelKin);
		this.add(_status);
		_statusOuterPanelKin.add(_statusInnerPanel);
		
		
// Create Control Buttons

		

		

		//_snapShotButton.setHorizontalAlignment(AbstractButton.LEFT);
		_setTimeZero.setHorizontalAlignment(AbstractButton.LEFT);
		//_setTimeZero.setHorizontalTextPosition(AbstractButton.CENTER);
		
		
		
		_setLevelUp.setBorder(new LineBorder(Color.gray,1));
		_setLevelDown.setBorder(new LineBorder(Color.gray,1));
		_setLevelUpFast.setBorder(new LineBorder(Color.gray,1));
		_setLevelDownFast.setBorder(new LineBorder(Color.gray,1));
		_setLevelZero.setBorder(new LineBorder(Color.gray,1));
		//_snapShotButton.setBorder(new LineBorder(Color.gray,1));
		_setLineCenter.setBorder(new LineBorder(Color.gray,1));
		_setMagnify.setBorder(new LineBorder(Color.gray,1));
		_trimGraph.setBorder(new LineBorder(Color.gray,1));
		_smoothKin.setBorder(new LineBorder(Color.gray,1));
		_setSmoothPointsUp.setBorder(new LineBorder(Color.gray,1));
		_setSmoothPointsDown.setBorder(new LineBorder(Color.gray,1));
		_setZeroLeft.setBorder(new LineBorder(Color.gray,1));
		_setZeroRight.setBorder(new LineBorder(Color.gray,1));
		_setZeroLeftFast.setBorder(new LineBorder(Color.gray,1));
		_setZeroRightFast.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerFirstLeft.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerFirstRight.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerFirstLeftFast.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerFirstRightFast.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerLastLeft.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerLastRight.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerLastLeftFast.setBorder(new LineBorder(Color.gray,1));
		_setFitMarkerLastRightFast.setBorder(new LineBorder(Color.gray,1));
		_1expFitButton.setBorder(new LineBorder(Color.gray,1));
		_2expFitButton.setBorder(new LineBorder(Color.gray,1));
		_2OrderFitButton.setBorder(new LineBorder(Color.gray,1));
		_12OrderFitButton.setBorder(new LineBorder(Color.gray,1));
		_averageButton.setBorder(new LineBorder(Color.gray,1));
		_2OrderProductFitButton.setBorder(new LineBorder(Color.gray,1));
		      
	
		
		Dimension _buttonSize = new Dimension(25,25);
		
		_levelPanelKin.add(_setLevelUp);
		_levelPanelKin.add(_setLevelDown);
		_levelPanelKin.add(_setLevelUpFast);
		_levelPanelKin.add(_setLevelDownFast);
		_levelPanelKin.add(_setLevelZero);
		_levelPanelKin.add(_levelLabel);

		_zeroPanelKin.add(_setZeroLeft);
		_zeroPanelKin.add(_setZeroRight);
		_zeroPanelKin.add(_setZeroLeftFast);
		_zeroPanelKin.add(_setZeroRightFast);
		_zeroPanelKin.add(_setTimeZero);
		_zeroPanelKin.add(_zeroLabel);

		_markerPanelKin.add(_setFitMarkerFirstLeft);
		_markerPanelKin.add(_setFitMarkerFirstRight);
		_markerPanelKin.add(_setFitMarkerFirstLeftFast);
		_markerPanelKin.add(_setFitMarkerFirstRightFast);
		_markerPanelKin.add(_setFitMarkerLastLeft);
		_markerPanelKin.add(_setFitMarkerLastRight);
		_markerPanelKin.add(_setFitMarkerLastLeftFast);
		_markerPanelKin.add(_setFitMarkerLastRightFast);
		_markerPanelKin.add(_1expFitButton);
		_markerPanelKin.add(_2expFitButton);
		_markerPanelKin.add(_2OrderFitButton);
		_markerPanelKin.add(_2OrderProductFitButton);
		_markerPanelKin.add(_averageButton);
		_markerPanelKin.add(_fitMarkerFirstLabel);
		_markerPanelKin.add(_fitMarkerLastLabel);
		
		
		
		
		
		
		//_gridControlPanelKin.add(_snapShotButton);
		_gridControlPanelKin.add(_setLineCenter);
		_gridControlPanelKin.add(_setMagnify);
		_gridControlPanelKin.add(_trimGraph);
		_gridControlPanelKin.add(_smoothKin);
		_gridControlPanelKin.add(_smoothKinLabel);
		//_gridControlPanelKin.add(_fixViewBox);
		_gridControlPanelKin.add(_setSmoothPointsUp);
		_gridControlPanelKin.add(_setSmoothPointsDown);
		
		
		_setLevelUp.setBounds(110, 10, _buttonSize.width, _buttonSize.height);
		_setLevelDown.setBounds(110, 38, _buttonSize.width, _buttonSize.height);
		_setLevelUpFast.setBounds(137, 10, _buttonSize.width, _buttonSize.height);
		_setLevelDownFast.setBounds(137, 38, _buttonSize.width, _buttonSize.height);
		_setLevelZero.setBounds(5, 65, 160, 30);
		//_snapShotButton.setBounds(5, 20, 125, 34);
		_setLineCenter.setBounds(5, 20, 160, 35);
		_setMagnify.setBounds(5, 55, 160, 35);
		_trimGraph.setBounds(5, 90, 160, 35);
		_smoothKin.setBounds(5, 125, 160, 35);
		_setSmoothPointsUp.setBounds(140, 165, _buttonSize.width, _buttonSize.height);
		_setSmoothPointsDown.setBounds(115, 165, _buttonSize.width, _buttonSize.height);
		_fixViewBox.setBounds(5, 190, 160, 25);

		_setZeroLeft.setBounds(110, 10, _buttonSize.width, _buttonSize.height);
		_setZeroRight.setBounds(137, 10, _buttonSize.width, _buttonSize.height);
		_setZeroLeftFast.setBounds(110, 38, _buttonSize.width, _buttonSize.height);
		_setZeroRightFast.setBounds(137, 38, _buttonSize.width, _buttonSize.height);
		_setTimeZero.setBounds(5, 65, 160, 35);

		_setFitMarkerFirstLeft.setBounds(110, 10, _buttonSize.width, _buttonSize.height);
		_setFitMarkerFirstRight.setBounds(137, 10, _buttonSize.width, _buttonSize.height);
		_setFitMarkerFirstLeftFast.setBounds(110, 38, _buttonSize.width, _buttonSize.height);
		_setFitMarkerFirstRightFast.setBounds(137, 38, _buttonSize.width, _buttonSize.height);

		int shift = 55;

		_setFitMarkerLastLeft.setBounds(110, 10+shift, _buttonSize.width, _buttonSize.height);
		_setFitMarkerLastRight.setBounds(137, 10+shift, _buttonSize.width, _buttonSize.height);
		_setFitMarkerLastLeftFast.setBounds(110, 38+shift, _buttonSize.width, _buttonSize.height);
		_setFitMarkerLastRightFast.setBounds(137, 38+shift, _buttonSize.width, _buttonSize.height);
		_1expFitButton.setBounds(5, 120, 160, 30);
		_2expFitButton.setBounds(5, 150, 160, 30);
		_2OrderFitButton.setBounds(5, 180, 160, 30);
		_12OrderFitButton.setBounds(5, 210, 160, 30);
		_2OrderProductFitButton.setBounds(5, 210, 160, 30);
		_averageButton.setBounds(5, 240, 160, 30);
		
		
		
		_levelLabel.setBounds(10, 30, 95, 20);
		_levelLabel.setFont(_fontCollection.font1);
		_levelLabel.setBorder(new LineBorder(Color.BLACK,1));
	
		_smoothKinLabel.setBounds(10, 165, 100, 20);
		_smoothKinLabel.setFont(_fontCollection.font1);
		_smoothKinLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		_zeroLabel.setBounds(10, 30, 95, 20);
		_zeroLabel.setFont(_fontCollection.font1);
		_zeroLabel.setBorder(new LineBorder(Color.BLACK,1));

		_fitMarkerFirstLabel.setBounds(10, 30, 95, 20);
		_fitMarkerFirstLabel.setFont(_fontCollection.font1);
		_fitMarkerFirstLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		_fitMarkerLastLabel.setBounds(10, 30+shift, 95, 20);
		_fitMarkerLastLabel.setFont(_fontCollection.font1);
		_fitMarkerLastLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		

		
		//setKinPanelComponentsBounds(Constants._mainFrameInitSizeX, Constants._mainFrameInitSizeY);
		
	}
	
	
	public void createKineticsModeToolBar()
	{
		
		_toolBarKin.setLayout(null);
		_modeBox.setSelectedIndex(1);
       
        _toolBarKin.add(_openFileButton);
		_toolBarKin.add(_exitButton);
		_toolBarKin.add(_convertFileButton);
		_toolBarKin.add(_snapShotButton);
		_toolBarKin.add(_modeBox);

		
		_openFileButton.setBounds(0, 0, 150, 50);
		_exitButton.setBounds(150, 0, 150, 50);
		_convertFileButton.setBounds(300, 0, 200, 50);
		_snapShotButton.setBounds(500, 0, 50, 50);
		int l = 100+80+150+50;
		_modeBox.setBounds(550, 0, Constants._mainFrameInitSizeX-560, 50);
		
 		
	}
	
	
	
	
	public void initializeLabels()
	{
		
		_status = new JLabel("Status: waiting for a file...");
		_levelLabel = new JLabel("0.0000");
		_zeroLabel = new JLabel("0.000");
		_zeroLabelHS = new JLabel("0.000");
		_timeLabel = new JLabel("0");
		_smoothKinLabel = new JLabel("1");
		_fitMarkerFirstLabel = new JLabel("0.000");
		_fitMarkerLastLabel = new JLabel("0.000");

	
	}
	
	   public void setKinPanelComponentsBounds(int _mainFrameSizeX, int _mainFrameSizeY, int _graphPanelSizeX, int _graphPanelSizeY, int _errorPanelSizeX, int _errorPanelSizeY)
	    {

  			//int _graphPanelSizeX = _mainFrameSizeX-175;
   			//int _graphPanelSizeY = _mainFrameSizeY-250;
 			//int _errorPanelSizeX = _mainFrameSizeX-175;
   			//int _errorPanelSizeY = 115;   
   			
			this.setBounds(1, 1, _mainFrameSizeX-11, _mainFrameSizeY-66+30);
			_menuBar.setBounds(0, 0, _mainFrameSizeX, 30);
			_toolBarKin.setBounds(0,30, _mainFrameSizeX, 50);
			_modeBox.setBounds(550, 0, _mainFrameSizeX-560, 50);
			_graphPanel.setBounds(0, 80, _graphPanelSizeX, _graphPanelSizeY);
			_graphPanel.set_gridSizeX(_graphPanelSizeX-80);
			_graphPanel.set_gridSizeY(_graphPanelSizeY-80);
			_errorPanel.set_gridSizeX(_errorPanelSizeX-80);
			_errorPanel.set_gridSizeY(_errorPanelSizeY-35);
			_errorPanel.setBounds(0, _graphPanelSizeY+50+30, _errorPanelSizeX, _errorPanelSizeY);
    		_levelPanelKin.setBounds(_graphPanelSizeX, 80, 170, 100);
    		_gridControlPanelKin.setBounds(_graphPanelSizeX, 180, 170, 195);
    		_zeroPanelKin.setBounds(_graphPanelSizeX, 345+30, 170, 105);
    		_markerPanelKin.setBounds(_graphPanelSizeX, 450+30, 170, 280);
    		int _ySize = _graphPanelSizeY+_errorPanelSizeY-125-280-100-115;
    		if(_ySize<50)_ySize=50;
    		_statusOuterPanelKin.setBounds(_graphPanelSizeX, 730+30, 170, _ySize-58);
    		_statusInnerPanel.setBounds(10, 10, 150, _ySize-80);
    		_status.setBounds(0,  _graphPanelSizeY+50+_errorPanelSizeY+30, _mainFrameSizeX, 20);

   	    			
	    }
	

	public void setStatusText(String text)
	{
		_status.setText(text);
	}
	
	
	
	
	

}
