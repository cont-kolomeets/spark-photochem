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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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



public class GlobalFitMenuBar extends JMenuBar

{
	
	public static final long serialVersionUID = 001;
	public JMenu _menuFile = new JMenu("File");
	public JMenu _menuSettings = new JMenu("Settings");
	public JMenu _menuAbout = new JMenu("About");
	public JMenuItem _addKinItem = new JMenuItem("Add kin");
	public JMenuItem _clearAllItem = new JMenuItem("Clear all");
	public JMenuItem _openProjectItem = new JMenuItem("Open project");
	public JMenuItem _saveProjectItem = new JMenuItem("Save project");
	public JMenuItem _exitItem = new JMenuItem("Exit");	
	public 	JMenuItem[] _itemArray = new JMenuItem[12];
	
	public JMenuItem _aboutItem = new JMenuItem("About");
	public JCheckBox _enableAABox = new JCheckBox("Enable Antianalizing");
	public JCheckBox _showBackgroundCurvesBox = new JCheckBox("Show background cuves");
	public JCheckBox _enableLowDetailBox = new JCheckBox("Enable Low Detalization");
	public JCheckBox _setSameColorBox = new JCheckBox("Set The Same Color");
	public JPanel _checkMinPanel = new JPanel();
	public JButton _checkIfLocalMinimumButton = new JButton("Check local minimum");
	public JTextField _cubeResolField = new JTextField("2");
	public JTextField _cubeSideField = new JTextField("0.05");
	public JLabel _cubeResolLabel = new JLabel("Res =");
	public JLabel _cubeSideLabel = new JLabel("Side =");
	
	
	public JPanel _hyperPanel = new JPanel();
	public JPanel _hyperOutputPanel = new JPanel();
	public JPanel _hyperGridOutputPanel = new JPanel();
	public JPanel _hyperGridBoxOutputPanel = new JPanel();
	public JButton _calcHyperSpaceButton = new JButton("Calc Hyperspace");
	public JTextField _nOfIterationsField = new JTextField("10");
	public JTextField _hyperCubeSideField = new JTextField("0.1");
	public JLabel _nOfIterationsLabel = new JLabel("Nit =");
	public JLabel _hyperCubeSideLabel = new JLabel("Side =");
	
	public JTextField _hyperLimitField = new JTextField("0.9");
	public JLabel _hyperLimitName = new JLabel("Lim =");

	
	public JLabel _hyperOutputLabel = new JLabel("Output file:");
	public JTextField _hyperOutputField = new JTextField("c:/examples/Testing schemes/HyperspaceOutput.txt");
	public JTextField _hyperGridOutputField = new JTextField("c:/examples/Testing schemes/HyperspaceGridOutput.txt");
	public JLabel _hyperGridOutputLabel = new JLabel("Grid file:");
	public JCheckBox _hyperGridBox = new JCheckBox("Write grid to file");
	
	public JPanel _saveEveryPanel = new JPanel();
	public JLabel _saveEveryLabel = new JLabel("Save every *th point");	
	public JTextField _saveEveryText = new JTextField();
	public int x=1;
	
	
	public GlobalFitMenuBar()
	{
		createMenuBarInterface();
		createCheckIfMinPanelInteraface();
		createHyperPanelInteraface();
	}
	
	

	public void createMenuBarInterface()
	{
		
		this.add(_menuFile);
		this.add(_menuSettings);
		this.add(_menuAbout);

		//_menuFile.setMnemonic('F');


		_menuFile.add(_addKinItem);
		_menuFile.add(_clearAllItem);
		_menuFile.add(_openProjectItem);
		_menuFile.add(_saveProjectItem);
		_menuFile.addSeparator();
		_menuFile.add(_exitItem);
		_menuFile.addSeparator();
		
		
		_menuAbout.add(_aboutItem);

		

		//Settings
		
		_enableAABox.setSelected(true);
		_showBackgroundCurvesBox.setSelected(true);
		_enableLowDetailBox.setSelected(false);
		_setSameColorBox.setSelected(false);
		_saveEveryPanel.setSize(new Dimension(0,30));
		_saveEveryPanel.setLayout(null);
		
		//_saveEveryPanel.add(_saveEveryLabel);
		//_saveEveryPanel.add(_saveEveryText);
		_saveEveryLabel.setBounds(5,0,125,30);
		_saveEveryText.setBounds(125,5,30,20);
		

		//_menuSettings.setLayout(null);		
		_menuSettings.add(_enableAABox);
		_menuSettings.add(_showBackgroundCurvesBox);
		//_menuSettings.add(_checkMinPanel);
		//_menuSettings.add(_hyperPanel);
		//_menuSettings.add(_hyperOutputPanel);
		//_menuSettings.add(_hyperGridOutputPanel);
		//_menuSettings.add(_hyperGridBoxOutputPanel);
			    

		for(int i=0; i<12; i++)
		{
			_itemArray[i] = new JMenuItem("---------");
			_menuFile.add(_itemArray[i]);
		}

	    

		//setJMenuBar(this);
		
	}
	
	public void createCheckIfMinPanelInteraface()
	{
		_checkIfLocalMinimumButton.setBounds(5,0,165,30);
		_cubeResolField.setBounds(215,0,30,30);
		_cubeResolLabel.setBounds(175,0,40,30);
		_cubeSideField.setBounds(285,0,30,30);
		_cubeSideLabel.setBounds(245,0,40,30);
		_checkMinPanel.setLayout(null);
		_checkMinPanel.setBounds(5,30,300,30);
		_checkMinPanel.add(_checkIfLocalMinimumButton);
		_checkMinPanel.add(_cubeResolField);
		_checkMinPanel.add(_cubeResolLabel);
		_checkMinPanel.add(_cubeSideField);
		_checkMinPanel.add(_cubeSideLabel);
	}
	

	public void createHyperPanelInteraface()
	{
		_calcHyperSpaceButton.setBounds(5,0,165,30);
		_nOfIterationsLabel.setBounds(175,0,40,30);
		_nOfIterationsField.setBounds(215,0,30,30);
		_hyperCubeSideLabel.setBounds(245,0,40,30);
		_hyperCubeSideField.setBounds(285,0,30,30);
		
		_hyperLimitName.setBounds(315,0,40,30);
		_hyperLimitField.setBounds(355,0,30,30);

		
		_hyperOutputLabel.setBounds(5,0,70,30);
		_hyperOutputField.setBounds(70,0,315,30);
		_hyperGridBox.setBounds(5,0,150,20);
		_hyperGridOutputLabel.setBounds(5,0,70,30);
		_hyperGridOutputField.setBounds(70,0,315,30);
		
		_hyperPanel.setLayout(null);
		_hyperPanel.setBounds(5,30,390,30);
		_hyperPanel.add(_calcHyperSpaceButton);
		_hyperPanel.add(_nOfIterationsLabel);
		_hyperPanel.add(_nOfIterationsField);
		_hyperPanel.add(_hyperCubeSideField);
		_hyperPanel.add(_hyperCubeSideLabel);
		_hyperPanel.add(_hyperLimitName);
		_hyperPanel.add(_hyperLimitField);

		
		_hyperOutputPanel.setLayout(null);
		_hyperOutputPanel.setBounds(5,60,300,30);
		_hyperOutputPanel.add(_hyperOutputLabel);
		_hyperOutputPanel.add(_hyperOutputField);
		
		_hyperGridOutputPanel.setLayout(null);
		_hyperGridOutputPanel.setBounds(5,90,300,30);
		
		_hyperGridBoxOutputPanel.setLayout(null);
		_hyperGridBoxOutputPanel.setBounds(5,120,300,30);
		
		_hyperGridBoxOutputPanel.add(_hyperGridBox);
		_hyperGridOutputPanel.add(_hyperGridOutputLabel);
		_hyperGridOutputPanel.add(_hyperGridOutputField);


		
		
		
		
		
		
		
	}
	
	

}
