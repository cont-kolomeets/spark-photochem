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





public class KineticsModeMenuBar extends JMenuBar{
	
	
	public static final long serialVersionUID = 001;
	public JMenu _menuFile = new JMenu("File");
	public JMenu _menuSettings = new JMenu("Settings");
	public JMenu _menuAbout = new JMenu("About");
	public JMenuItem _openFileItem = new JMenuItem("Open File");	
	public JMenuItem _exitItem = new JMenuItem("Exit");	
	public JMenuItem _aboutItem = new JMenuItem("About");
	public JCheckBox _enableAABox = new JCheckBox("Enable Antianalizing");
	public JCheckBox _enableLowDetailBox = new JCheckBox("Enable Low Detalization");
	public JCheckBox _setSameColorBox = new JCheckBox("Set The Same Color");
	public JPanel _saveEveryPanel = new JPanel();
	public JLabel _saveEveryLabel = new JLabel("Save every *th point");	
	public JTextField _saveEveryText = new JTextField();
	public int x=1;
	
	//MainFrame _frame = new MainFrame();
	
	public KineticsModeMenuBar()
	{
		createMenuBarInterface();
	}
	
	
	
	
	
	
	
	
	
	public void createMenuBarInterface()
	{
		
		this.add(_menuFile);
		this.add(_menuSettings);
		this.add(_menuAbout);

		//_menuFile.setMnemonic('F');


		_menuFile.add(_openFileItem);
	   // _openFileItem.setMnemonic('o');
		_menuFile.add(_exitItem);
	   // _exitItem.setMnemonic('e');
		_menuAbout.add(_aboutItem);

		

		//Settings
		
		_enableAABox.setSelected(true);
		_enableLowDetailBox.setSelected(false);
		_setSameColorBox.setSelected(false);
		_saveEveryPanel.setSize(new Dimension(0,30));
		_saveEveryPanel.setLayout(null);
		//_saveEveryPanel.setBackground(Color.DARK_GRAY);
		_saveEveryPanel.add(_saveEveryLabel);
		_saveEveryPanel.add(_saveEveryText);
		_saveEveryLabel.setBounds(5,0,125,30);
		_saveEveryText.setBounds(125,5,30,20);
		
		
		_menuSettings.add(_enableAABox);
		_menuSettings.add(_enableLowDetailBox);
		_menuSettings.add(_setSameColorBox);
		_menuSettings.add(_saveEveryPanel);
			    

		

	    
		//setJMenuBar(this);
		
	}
	
	
	
	
	

}
