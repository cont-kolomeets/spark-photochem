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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ToolTipManager;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class GlobalFitMainInterface extends JPanel{

	
	public GlobalFitMenuBar _menuBar = new GlobalFitMenuBar();
	public GlobalFitGraphPanel _graphPanel = new GlobalFitGraphPanel();
	public GlobalFitErrorPanel _errorPanel = new GlobalFitErrorPanel();
	
    private final JTabbedPane _tabbedPanel = new JTabbedPane();
	
	public JTextField[] _eqLabelsArray, _constantLabelsArray, _concentrationLabelsArray, _beforePulseConcentrationLabelsArray;;
	public JLabel[] _constantLabelsNameArray = new JLabel[10];
	public JTextField[] _constantErrorLabelsArray = new JTextField[10];
	public JLabel[] _eqLabelsNameArray = new JLabel[10];
	public JLabel _status;
	public JTextField _thicknessField;
	public JLabel _thicknessLabelName1 = new JLabel("L =");
	public JLabel _thicknessLabelName2 = new JLabel("cm");;
	public String[] _showModeString = {"absorbance","concentration"};
	public JLabel _showModeBoxName= new JLabel("Show");
	public JComboBox _showModeBox = new JComboBox(_showModeString);
	public JCheckBox _normToZeroBox = new JCheckBox("Normalize to zero before pulse");
	public JCheckBox _setCurveBoundsBox = new JCheckBox("Set kinetic curve bounds");
	public JCheckBox _updateAutoBox = new JCheckBox("Update RK4 results automaticaly");
	public JCheckBox _displayContributionsBox = new JCheckBox("Display contributions");
	public String[] _scaleString = {"mcs", "ms", "s"};
	public JLabel _scaleBoxName= new JLabel("Time scale: ");
	public JComboBox _scaleModeBox = new JComboBox(_scaleString);
	public JPanel _statusPanel = new JPanel();
	
	public JLabel _nOfEqLabel, _nOfConstLabel, _globalFitWavelengthLabel, _accuracyLabel;
	public JPanel _toolBarGlobalFit = new JPanel();
	public int _globalFitSelectedWavelength = 500;
	
	//public JPanel _extFramePane = new JPanel();
	public JPanel _diffEqPanel = new JPanel();
	public JPanel _diffEqControlPanel = new JPanel();
	public JPanel _diffEqControl1Panel = new JPanel();
	public JPanel _modelPanel = new JPanel();
	public JPanel _constantsPanel = new JPanel();
	public JPanel _kinControlPanel = new JPanel();
	//public JPanel _extFXPanel = new JPanel();
	//public JPanel _extFSmoothPanel = new JPanel();
	
	public String[] _modeString = {"Select mode","Kinetics", "Global Fit"};
	public JComboBox _modeBox = new JComboBox(_modeString);
	
	
	public JButton[] _constantValueUpButton = new JButton[10];
	public JButton[] _constantValueDownButton = new JButton[10];

	
	
	public JButton _addKinButton = new JButton("Add Kin", IconCollection._PlusIcon);
	public JButton _clearAllButton = new JButton("Clear All", IconCollection._ClearAllSpecIcon);
	public JButton _openProjectButton = new JButton("Open Project", IconCollection._OpenSetIcon);
	public JButton _saveProjectButton = new JButton("Save Project", IconCollection._SaveSetIcon);
	public JButton _snapShotButton = new JButton("Snapshot", IconCollection._SnapShotIcon);
	public JButton _exitButton = new JButton("Exit", IconCollection._ExitIcon);
	
	public JButton _nOfEqUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _nOfEqDownButton = new JButton(IconCollection._ArrowGrayDown);
	public JButton _nOfConstUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _nOfConstDownButton = new JButton(IconCollection._ArrowGrayDown);
	
	public JButton _accuracyUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _accuracyDownButton = new JButton(IconCollection._ArrowGrayDown);

	public JButton _openModelButton = new JButton("Open Model", IconCollection._OpenModelIcon);
	public JButton _saveModelButton = new JButton("Save Model", IconCollection._SaveModelIcon);
	public JButton _startFittingButton = new JButton("Global Fit", IconCollection._GlobalFitIcon);
	public JButton _setExtButton = new JButton("Set Abs", IconCollection._ShowSpectraIcon);
	public JButton _fitRK4Button = new JButton("Fit RK4", IconCollection._SpectraParametersIcon);
	
	public JLabel _nOfEqLabelName = new JLabel("Number of Equations");
	public JLabel _nOfConstLabelName = new JLabel("Number of Constants");
	public JLabel _accuracyLabelName = new JLabel("Fit RK4 accuracy");
	
	public JButton _scaleButton = new JButton(IconCollection._RescaleIcon1);
	public JButton _seeAllButton = new JButton(IconCollection._SeeAllIcon);
	
	// kinControlPanel
	
	public JButton _wavelengthUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _wavelengthDownButton = new JButton(IconCollection._ArrowGrayDown);

	public JButton _deleteKinButton = new JButton("Del Selected", IconCollection._DeleteIcon);
	public JButton _magnifyButton = new JButton(IconCollection._MagnifyIcon1);
	public JButton _saveFitButton = new JButton("Kin + Fit", IconCollection._SaveFitIcon);
	
	public JButton _showSliceButton = new JButton("Spec section", IconCollection._ShowSlice);
	
	public JLabel _globalSigma = new JLabel("0");	
	public JLabel _globalSigmaName = new JLabel("Global sigma =");
	public JButton _showSigmaButton = new JButton("Show");
	
	Color _BGColor1 = new Color(240,240,240);
	Color _BGColor2 = new Color(230,230,230);
	Color _BGColor3 = new Color(100,100,100);
	Color _DarkGray = new Color(50,50,50);
	
	Dimension _buttonSize = new Dimension(25,13);
	
	
	
	public GlobalFitMainInterface()
	{
		createGlobalFitModeInterface();
	}
	
	
	public void createGlobalFitModeInterface()
	{
		
		ToolTipManager.sharedInstance().setInitialDelay(3000);
		ToolTipManager.sharedInstance().setDismissDelay(2000);
		
		initializeLabels();
		createGlobalFitToolBar();
		
		this.setBackground(new Color(200,200,200));
		this.setBorder(new LineBorder(Color.BLACK,1));
		this.setLayout(null);
		
// Create Working Panels
		
		//createdTabbedPanelInterface(); 
		create_diffEqControlPanelInterface();	
		create_constantsPanelInterface();   
		create_kinControlPanelInterface(); 
		create_modelPanelInterface();
		create_diffEqPanelInterface();
		createGraphPanelInterface();
		
		this.add(_menuBar);
	 	this.add(_toolBarGlobalFit);	
		//this.add(_tabbedPanel);
		this.add(_graphPanel);
		this.add(_errorPanel);
		this.add(_diffEqPanel);
		this.add(_diffEqControlPanel);
		this.add(_modelPanel);
		this.add(_kinControlPanel);
		this.add(_constantsPanel);
		this.add(_status);

	
		//setGlobalFitPanelComponentsBounds(Constants._mainFrameInitSizeX, Constants._mainFrameInitSizeY);
	
		
		
	}
	
	

	public void createGraphPanelInterface()
	{
		_graphPanel.add(_seeAllButton);
		_graphPanel.add(_scaleButton);
		_graphPanel.add(_magnifyButton);
		_graphPanel.add(_showSliceButton);
		
		_seeAllButton.setToolTipText("Rescale to show all");
		_scaleButton.setToolTipText("Specify visual area");
		_magnifyButton.setToolTipText("Magnify selected area");
		_showSliceButton.setToolTipText("Show transient spectra");
	}

	public void createdTabbedPanelInterface()
	{
		ButtonTabComponent b = new ButtonTabComponent(_tabbedPanel);
		
		_tabbedPanel.removeAll();
        /*for (int i = 0; i < 5; i++) {
            String title = "Tab " + i;
            _tabbedPanel.add(title, new JLabel(title));
            //_tabbedPanel.setTabComponentAt(i, );
        }*/
        String title = "Basic";
        _tabbedPanel.add(title, new JLabel(title));
		_tabbedPanel.setTabComponentAt(0, b);

        
	}




	public void createGlobalFitToolBar()
	{
		
		_toolBarGlobalFit.setLayout(null);
		_toolBarGlobalFit.setBackground(new Color(215, 215, 215));
		_toolBarGlobalFit.setBorder(BorderFactory.createEtchedBorder());
		
		_toolBarGlobalFit.add(_addKinButton);
		_toolBarGlobalFit.add(_clearAllButton);
		_toolBarGlobalFit.add(_openProjectButton);
		_toolBarGlobalFit.add(_saveProjectButton);
		_toolBarGlobalFit.add(_snapShotButton);
		_toolBarGlobalFit.add(_exitButton);
		//_toolBarGlobalFit.add(_modeBox);
		
		
		
		
		
		_addKinButton.setBounds(0, 0, 120, 50);
		//_addKinButton.setHorizontalAlignment(SwingConstants.LEFT);
		_clearAllButton.setBounds(120, 0, 120, 50);
		_openProjectButton.setBounds(240, 0, 150, 50);
		_saveProjectButton.setBounds(390, 0, 150, 50);
		_snapShotButton.setBounds(540, 0, 130, 50);
		_exitButton.setBounds(670, 0, 100, 50);
		
		_addKinButton.setToolTipText("Add new kinetic curve");
		_clearAllButton.setToolTipText("Clear all kinetic curves");
		_openProjectButton.setToolTipText("Open new project");
		_saveProjectButton.setToolTipText("Save current project");
		_snapShotButton.setToolTipText("Take a snapshot");
		_exitButton.setToolTipText("Exit program");
		//int l = 120+120+120+50;
		//_modeBox.setBounds(l, 0, Constants._mainFrameInitSizeX-l-10, 50);
	
	}


	public void initializeLabels()
	{
		_status = new JLabel("Status: waiting for a file...");
		_nOfEqLabel = new JLabel("2");
		_nOfConstLabel = new JLabel("2");
		_accuracyLabel = new JLabel("1");
		_globalFitWavelengthLabel = new JLabel("0");	
		

		_eqLabelsArray = new JTextField[10];
		_constantLabelsArray = new JTextField[10];
		_concentrationLabelsArray = new JTextField[10];
		_beforePulseConcentrationLabelsArray = new JTextField[10];

		for(int i=0; i<10; i++)
		{
			_eqLabelsArray[i] = new JTextField("0");
			_constantLabelsArray[i] = new JTextField("0");
			_constantErrorLabelsArray[i] = new JTextField("0");
			_concentrationLabelsArray[i] = new JTextField("0");
			_beforePulseConcentrationLabelsArray[i] = new JTextField("0");
		}
		
		

	}
	
	

   public void setGlobalFitPanelComponentsBounds(int _mainFrameSizeX, int _mainFrameSizeY, int _graphPanelSizeX, int _graphPanelSizeY, int _errorPanelSizeX, int _errorPanelSizeY)
    {
			//int _graphPanelSizeX = _mainFrameSizeX-350;
			//int _graphPanelSizeY = _mainFrameSizeY-500;
			//int _errorPanelSizeX = _graphPanelSizeX;
			//int _errorPanelSizeY = 115;   
			
	   			
		this.setBounds(1, 1, _mainFrameSizeX-11, _mainFrameSizeY-66+30);
		_menuBar.setBounds(0, 0, _mainFrameSizeX, 30);
		_modeBox.setBounds(640, 0, Constants._mainFrameInitSizeX-640-10, 50);
		_toolBarGlobalFit.setBounds(0,30, _mainFrameSizeX, 51);
		//_modeBox.setBounds(380, 0, _mainFrameSizeX-380-10, 50);
	   	_graphPanel.setBounds(0, 80, _graphPanelSizeX, _graphPanelSizeY);
	   	_tabbedPanel.setBounds(0, 80, _graphPanelSizeX, _graphPanelSizeY);
		_scaleButton.setBounds(_graphPanelSizeX-30,10,25,25);
		_seeAllButton.setBounds(_graphPanelSizeX-80,10,25,25);
		_magnifyButton.setBounds(_graphPanelSizeX-55,10,25,25);
		_showSliceButton.setBounds(_graphPanelSizeX-225,10,145,25);
	   	
		_errorPanel.setBounds(0, _graphPanelSizeY+80, _errorPanelSizeX, _errorPanelSizeY);
		_graphPanel.set_gridSizeX(_graphPanelSizeX-80);
		_graphPanel.set_gridSizeY(_graphPanelSizeY-80);
		_errorPanel.set_gridSizeX(_graphPanelSizeX-80);
		_errorPanel.set_gridSizeY(80);

    	_diffEqPanel.setBounds(0, _graphPanelSizeY+_errorPanelSizeY+80, _errorPanelSizeX, _mainFrameSizeY-_graphPanelSizeY-_errorPanelSizeY-140);
    	_diffEqControlPanel.setBounds(_graphPanelSizeX, _graphPanelSizeY+_errorPanelSizeY+80, 340, _mainFrameSizeY-_graphPanelSizeY-_errorPanelSizeY-140);
    	_diffEqControl1Panel.setBounds(10,115, 320, 125);

    	_kinControlPanel.setBounds(_graphPanelSizeX, 130+95, 340, 60);
    	_modelPanel.setBounds(_graphPanelSizeX, 80, 340, 145);
    	_constantsPanel.setBounds(_graphPanelSizeX, 190+95, 340, _mainFrameSizeY-155-190-(_mainFrameSizeY-_graphPanelSizeY-_errorPanelSizeY-140));
   		_status.setBounds(0, _mainFrameSizeY-60, _mainFrameSizeX, 20);
    			
    }


   public void create_diffEqPanelInterface()
   {
	   
	   
		_diffEqPanel.setBackground(_BGColor2);
		_diffEqPanel.setBorder(new LineBorder(Color.BLACK,1));
		_diffEqPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(_DarkGray,1), "Equations"));
		_diffEqPanel.setLayout(null);	
		
		
		
				for(int i=0; i<10; i++)
		{

			
			_eqLabelsArray[i].setFont(FontCollection.font1_15);
			_eqLabelsArray[i].setBorder(new LineBorder(Color.BLACK,1));
			_concentrationLabelsArray[i].setFont(FontCollection.font1_15);
			_concentrationLabelsArray[i].setBorder(new LineBorder(Color.BLACK,1));
			_beforePulseConcentrationLabelsArray[i].setFont(FontCollection.font1_15);
			_beforePulseConcentrationLabelsArray[i].setBorder(new LineBorder(Color.BLACK,1));

			if(i<5)
			{
				_eqLabelsArray[i].setBounds(10, 50+i*35, 250, 30);
				_concentrationLabelsArray[i].setBounds(270, 50+i*35, 80, 30);
				_beforePulseConcentrationLabelsArray[i].setBounds(360, 50+i*35, 80, 30);
			}
			else
			{
				_eqLabelsArray[i].setBounds(500, 50+(i-5)*35,250, 30);
				_concentrationLabelsArray[i].setBounds(760, 50+(i-5)*35, 80, 30);
				_beforePulseConcentrationLabelsArray[i].setBounds(850, 50+(i-5)*35, 80, 30);

			}
		}
		
		

		
		_eqLabelsNameArray[0] = new JLabel("dXi/dt=");
		_eqLabelsNameArray[0].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[0].setBounds(10, 20, 60, 30);
		
		_eqLabelsNameArray[1] = new JLabel("Xi(0)");
		_eqLabelsNameArray[1].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[1].setBounds(270, 20, 40, 30);
		
		_eqLabelsNameArray[2] = new JLabel("Xi before pulse");
		_eqLabelsNameArray[2].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[2].setBounds(360, 20, 120, 30);
		
		
		
		_eqLabelsNameArray[3] = new JLabel("dXi/dt=");
		_eqLabelsNameArray[3].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[3].setBounds(500, 20, 60, 30);
		
		_eqLabelsNameArray[4] = new JLabel("Xi(0)");
		_eqLabelsNameArray[4].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[4].setBounds(760, 20, 40, 30);
		
		_eqLabelsNameArray[5] = new JLabel("Xi before pulse");
		_eqLabelsNameArray[5].setFont(FontCollection.font1_15);
		_eqLabelsNameArray[5].setBounds(850, 20, 120, 30);
		
		
				for(int i=0; i<2; i++)
		{
			_diffEqPanel.add(_eqLabelsArray[i]);
			_diffEqPanel.add(_concentrationLabelsArray[i]);
			_diffEqPanel.add(_beforePulseConcentrationLabelsArray[i]);
		}
		
		
		
		_diffEqPanel.add(_eqLabelsNameArray[0]);
		_diffEqPanel.add(_eqLabelsNameArray[1]);	
		_diffEqPanel.add(_eqLabelsNameArray[2]);
	   
   }
   

   public void create_modelPanelInterface()
   {
	   
		_modelPanel.setBackground(_BGColor2);
		_modelPanel.setBorder(new LineBorder(Color.BLACK,1));
		_modelPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(_DarkGray,1), "Model"));
		_modelPanel.setLayout(null);	
			
			
		_nOfEqUpButton.setBorder(new LineBorder(Color.gray,1));
		_nOfEqDownButton.setBorder(new LineBorder(Color.gray,1));
		_nOfConstUpButton.setBorder(new LineBorder(Color.gray,1));
		_nOfConstDownButton.setBorder(new LineBorder(Color.gray,1));
		_accuracyUpButton.setBorder(new LineBorder(Color.gray,1));
		_accuracyDownButton.setBorder(new LineBorder(Color.gray,1));			
		
		_openModelButton.setBorder(new LineBorder(Color.gray,1));
		_saveModelButton.setBorder(new LineBorder(Color.gray,1));
		_startFittingButton.setBorder(new LineBorder(Color.gray,1));	
			
			int _vShift = 20;
		_nOfEqUpButton.setBounds(200, _vShift, _buttonSize.width, _buttonSize.height);
		_nOfEqDownButton.setBounds(200, _vShift+_buttonSize.height, _buttonSize.width, _buttonSize.height);
		_nOfConstUpButton.setBounds(200, _vShift+_buttonSize.height*2, _buttonSize.width, _buttonSize.height);
		_nOfConstDownButton.setBounds(200, _vShift+_buttonSize.height*3, _buttonSize.width, _buttonSize.height);
		_accuracyUpButton.setBounds(200, _vShift+_buttonSize.height*4, _buttonSize.width, _buttonSize.height);
		_accuracyDownButton.setBounds(200, _vShift+_buttonSize.height*5, _buttonSize.width, _buttonSize.height);
		_openModelButton.setBounds(10, 100, 107, 35);
		_saveModelButton.setBounds(118, 100, 107, 35);		
		_startFittingButton.setBounds(230, 20, 100, 115);
		
			
		_openModelButton.setToolTipText("Open new model");
		_saveModelButton.setToolTipText("Save current model");
		_startFittingButton.setToolTipText("Open global fit window");
		
		
		
		_nOfEqLabelName.setBounds(10, 20, 160, 30);
		_nOfEqLabelName.setFont(FontCollection.font1_15);
		
		_nOfConstLabelName.setBounds(10, 50, 160, 20);
		_nOfConstLabelName.setFont(FontCollection.font1_15);
		
		_accuracyLabelName.setBounds(10, 76, 160, 20);
		_accuracyLabelName.setFont(FontCollection.font1_15);
			
		_nOfEqLabel.setBounds(170, 20, 30, 26);
		_nOfEqLabel.setFont(FontCollection.font1_15);
		_nOfEqLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		_nOfConstLabel.setBounds(170, 46, 30, 26);
		_nOfConstLabel.setFont(FontCollection.font1_15);
		_nOfConstLabel.setBorder(new LineBorder(Color.BLACK,1));
	   
		
		_accuracyLabel.setBounds(170, 72, 30, 26);
		_accuracyLabel.setFont(FontCollection.font1_15);
		_accuracyLabel.setBorder(new LineBorder(Color.BLACK,1));
			
		_modelPanel.add(_nOfEqUpButton);
		_modelPanel.add(_nOfEqDownButton);
		_modelPanel.add(_nOfConstUpButton );
		_modelPanel.add(_nOfConstDownButton);
		_modelPanel.add(_accuracyUpButton );
		_modelPanel.add(_accuracyDownButton);
		
		_modelPanel.add(_openModelButton);
		_modelPanel.add(_saveModelButton);
		_modelPanel.add(_startFittingButton);
		//_modelPanel.add(_setExtButton);
		_modelPanel.add(_nOfEqLabelName);
		_modelPanel.add(_nOfConstLabelName);
		_modelPanel.add(_nOfEqLabel);
		_modelPanel.add(_nOfConstLabel);	
		_modelPanel.add(_accuracyLabel);
		_modelPanel.add(_accuracyLabelName);
			
			

   }

   public void create_kinControlPanelInterface()
   {
		
		_kinControlPanel.setBackground(_BGColor2);
		_kinControlPanel.setBorder(new LineBorder(Color.BLACK,1));
		_kinControlPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(_DarkGray,1), "Kinetic Curve Edit"));
		_kinControlPanel.setLayout(null);

		_wavelengthUpButton.setBorder(new LineBorder(Color.gray,1));
		_wavelengthDownButton.setBorder(new LineBorder(Color.gray,1));
		_deleteKinButton.setBorder(new LineBorder(Color.gray,1));
		//_magnifyButton.setBorder(new LineBorder(Color.gray,1));


		_wavelengthUpButton.setBounds(70, 20, 25, 15);
		_wavelengthDownButton.setBounds(70, 35, 25, 15);
		_deleteKinButton.setBounds(100, 18, 115, 35);
		
		_saveFitButton.setBounds(100+115, 18, 115, 35);
		

		
		_deleteKinButton.setToolTipText("Delete current kinetic curve");
		_saveFitButton.setToolTipText("Save current kinetic and fitting curves");
		
		
		_globalFitWavelengthLabel.setBounds(10, 20, 60, 30);
		_globalFitWavelengthLabel.setFont(FontCollection.font1_15);
		_globalFitWavelengthLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		_kinControlPanel.add(_wavelengthUpButton);		
		_kinControlPanel.add(_wavelengthDownButton);	
		_kinControlPanel.add(_deleteKinButton);	
		_kinControlPanel.add(_saveFitButton);	
		_kinControlPanel.add(_globalFitWavelengthLabel);
		//_kinControlPanel.add(_displayContributionsBox);
	   
	   
   }
   


   public void create_constantsPanelInterface()
   {
	   
		


		_constantsPanel.setBackground(_BGColor2);
		_constantsPanel.setBorder(new LineBorder(Color.BLACK,1));
		_constantsPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(_DarkGray,1), "Constants"));
		_constantsPanel.setLayout(null);		

		
		

		for(int i=0; i<10; i++)
		{
			_constantLabelsNameArray[i] = new JLabel("k" + (i+1) + "=");
			_constantLabelsNameArray[i].setFont(FontCollection.font1_15);
			_constantLabelsNameArray[i].setBounds(10, 20+i*35, 40, 30);
			
			_constantLabelsArray[i].setFont(FontCollection.font1_15);
			_constantLabelsArray[i].setBorder(new LineBorder(Color.BLACK,1));
			_constantLabelsArray[i].setBounds(50, 20+i*35, 120, 30);

			_constantErrorLabelsArray[i].setFont(FontCollection.font1_15);
			_constantErrorLabelsArray[i].setBorder(new LineBorder(Color.BLACK,1));
			_constantErrorLabelsArray[i].setBounds(215, 20+i*35, 100, 30);		
			_constantErrorLabelsArray[i].setEditable(false);
			
			
			_constantValueUpButton[i] = new JButton(IconCollection._ArrowGrayUp);
			//_constantValueUpButton[i].setMultiClickThreshhold(0);
			_constantValueUpButton[i].setBounds(170, 20+i*35, 30, 15);
			//_constantValueUpButton[i]
			
			_constantValueDownButton[i] = new JButton(IconCollection._ArrowGrayDown);
			_constantValueDownButton[i].setBounds(170, 20+i*35+15, 30, 15);

		}
		
		
		
		for(int i=0; i<2; i++)
		{
			_constantsPanel.add(_constantLabelsArray[i]);
			_constantsPanel.add(_constantErrorLabelsArray[i]);
			_constantsPanel.add(_constantLabelsNameArray[i]);
			_constantsPanel.add(_constantValueUpButton[i]);
			_constantsPanel.add(_constantValueDownButton[i]);
		}
		
	   
   }
   
   public void create_diffEqControlPanelInterface()
   {
		
		
		_diffEqControlPanel.setBackground(_BGColor2);
		_diffEqControlPanel.setBorder(new LineBorder(Color.BLACK,1));
		_diffEqControlPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(_DarkGray,1), "Options"));
		_diffEqControlPanel.setLayout(null);
		
		_diffEqControl1Panel.setBackground(_BGColor2);
		_diffEqControl1Panel.setBorder(new LineBorder(Color.BLACK,1));
		_diffEqControl1Panel.setBorder(BorderFactory.createEtchedBorder());
		_diffEqControl1Panel.setLayout(null);
		
		
		_setExtButton.setBorder(new LineBorder(Color.gray,1));
		_setExtButton.setBounds(170, 20, 165, 55);
		
		_fitRK4Button.setBorder(new LineBorder(Color.gray,1));
		_fitRK4Button.setBounds(5, 20, 165, 55);
		

		_thicknessLabelName1.setFont(FontCollection.font1_15);
		_thicknessLabelName1.setBounds(10,10,30,25);
		
		_thicknessField = new JTextField("1");
		_thicknessField.setFont(FontCollection.font1_15);
		_thicknessField.setBounds(45,10,60,25);
		
		_thicknessLabelName2.setFont(FontCollection.font1_15);
		_thicknessLabelName2.setBounds(110,10,30,25);
		
		_scaleBoxName.setFont(FontCollection.font1_15);
		_scaleBoxName.setBounds(165,10,90,25);
		
		_scaleModeBox.setBounds(250,10,60,25);
		_scaleModeBox.setFont(FontCollection.font1_15);
		
		
		_updateAutoBox.setBounds(5,40,260,20);
		_updateAutoBox.setFont(FontCollection.font1_14);
		
		_normToZeroBox.setBounds(5,60,260,20);
		_normToZeroBox.setFont(FontCollection.font1_14);
		
		_setCurveBoundsBox.setBounds(5,80,260,20);
		_setCurveBoundsBox.setFont(FontCollection.font1_14);
		
		_displayContributionsBox.setBounds(5,100,260,20);
		_displayContributionsBox.setFont(FontCollection.font1_14);
		
		
		_globalSigmaName.setBounds(10,80,100,30);
		_globalSigma.setBounds(100,80,150,30);
		_globalSigma.setBackground(Color.white);
		_globalSigma.setOpaque(true);
		_globalSigma.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.DARK_GRAY));
		
		_showSigmaButton.setBounds(260,85,70,25);
		
		

		
		
		_setExtButton.setToolTipText("Open optical spectra window");
		_fitRK4Button.setToolTipText("Show solution of differential equations");
		_showSigmaButton.setToolTipText("Show dependence of deviation value on wavelength");
		_globalSigma.setToolTipText("Value of global deviation");
		_scaleBoxName.setToolTipText("Specify units on x-axis");
		_thicknessField.setToolTipText("Insert cuvette thickness");
		
		
		
		
		
		
		
		_statusPanel.setBounds(270, 50,40,65);
		_statusPanel.setBackground(Color.green);
		_statusPanel.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.darkGray));
		_statusPanel.setVisible(false);
		
		_diffEqControlPanel.add(_diffEqControl1Panel);
		
		_diffEqControlPanel.add(_setExtButton);
		_diffEqControlPanel.add(_fitRK4Button);
		_diffEqControlPanel.add(_globalSigmaName);
		_diffEqControlPanel.add(_globalSigma);			
		_diffEqControlPanel.add(_showSigmaButton);
		
		
		_diffEqControl1Panel.add(_thicknessLabelName1);
		_diffEqControl1Panel.add(_thicknessField);
		_diffEqControl1Panel.add(_thicknessLabelName2);
		_diffEqControl1Panel.add(_scaleBoxName);
		_diffEqControl1Panel.add(_scaleModeBox);
		_diffEqControl1Panel.add(_updateAutoBox);
		_diffEqControl1Panel.add(_normToZeroBox);
		_diffEqControl1Panel.add(_setCurveBoundsBox);
		_diffEqControl1Panel.add(_statusPanel);
		_diffEqControl1Panel.add(_displayContributionsBox);
		
	   
	   
   }
   

   
   
	public void setStatusText(String text)
	{
		_status.setText(text);
	}
	
	
	
	
	public void set_constantLabelsArray(JTextField[] _constantLabelsArray)
	{
		this._constantLabelsArray = _constantLabelsArray;
	}
	
	public JTextField[] get_constantLabelsArray()
	{
		return _constantLabelsArray;
	}
	
	public void set_constantErrorLabelsArray(JTextField[] _constantErrorLabelsArray)
	{
		this._constantErrorLabelsArray = _constantErrorLabelsArray;
	}
	
	public JTextField[] get_constantErrorLabelsArray()
	{
		return _constantErrorLabelsArray;
	}
	
	
	
	
	public void set_constantValueUpButton(JButton[] _constantValueUpButton)
	{
		this._constantValueUpButton = _constantValueUpButton;
	}
	
	public JButton[] get_constantValueUpButton()
	{
		return _constantValueUpButton;
	}
	
	
	public void set_constantValueDownButton(JButton[] _constantValueDownButton)
	{
		this._constantValueDownButton = _constantValueDownButton;
	}
	
	public JButton[] get_constantValueDownButton()
	{
		return _constantValueDownButton;
	}	
	
	
	
	
	
	
	
	public void set_constantLabelsNameArray(JLabel[] _constantLabelsNameArray)
	{
		this._constantLabelsNameArray = _constantLabelsNameArray;
	}
	
	public JLabel[] get_constantLabelsNameArray()
	{
		return _constantLabelsNameArray;
	}
	

	public void set_eqLabelsArray(JTextField[] _eqLabelsArray)
	{
		this._eqLabelsArray = _eqLabelsArray;
	}
	
	public JTextField[] get_eqLabelsArray()
	{
		return _eqLabelsArray;
	}
	
	public void set_eqLabelsNameArray(JLabel[] _eqLabelsNameArray)
	{
		this._eqLabelsNameArray = _eqLabelsNameArray;
	}
	
	public JLabel[] get_eqLabelsNameArray()
	{
		return _eqLabelsNameArray;
	}

	public void set_concentrationLabelsArray(JTextField[] _concentrationLabelsArray)
	{
		this._concentrationLabelsArray = _concentrationLabelsArray;
	}
	
	public JTextField[] get_concentrationLabelsArray()
	{
		return _concentrationLabelsArray;
	}
	

	public void set_beforePulseConcentrationLabelsArray(JTextField[] _beforePulseConcentrationLabelsArray)
	{
		this._beforePulseConcentrationLabelsArray = _beforePulseConcentrationLabelsArray;
	}
	
	public JTextField[] get_beforePulseConcentrationLabelsArray()
	{
		return _beforePulseConcentrationLabelsArray;
	}
	
	
}
