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


public class GlobalFitDefineRelationsFrame extends JFrame {
	
	public int _frameSizeX=240, _frameSizeY = 250-175+40;
	
	public JPanel _mainPane = new JPanel();
	public JPanel _nOfEqPanel = new JPanel();
	public JPanel _eqPanel = new JPanel();
	
	public JTextField[] _eqLabelsArray;
	public JLabel[] _namesArray;
	public JLabel _nOfEqLabel = new JLabel("0");;
	
	public JButton _nOfEqUpButton = new JButton(IconCollection._ArrowGrayUp);
	public JButton _nOfEqDownButton = new JButton(IconCollection._ArrowGrayDown);
	public JLabel _nOfEqLabelName = new JLabel("Number of Equations");
	public JButton _acceptButton = new JButton("Accept", IconCollection._AcceptIcon);
	
	
	Dimension _buttonSize = new Dimension(25,13);
	
	
	
	public GlobalFitDefineRelationsFrame()
	{
		createGlobalFitDefineRelationsFrameInterface();
	}

	
	
	
	public void createGlobalFitDefineRelationsFrameInterface()
	{
		
		this.setTitle("Xi(0) Relations");
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_frameSizeX*2, 30);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		this.add(_mainPane);
		_mainPane.setBounds(0,0,_frameSizeX, _frameSizeY-30);
		_mainPane.setLayout(null);
		
		
		createMainPaneInterface();

		
		
		
		

	}
	
	
	
	public void createMainPaneInterface()
	{
		_nOfEqPanel.setBounds(1,1,_frameSizeX-7, 40);
		_nOfEqPanel.setBorder(BorderFactory.createEtchedBorder());
		_nOfEqPanel.setLayout(null);
		
		_eqPanel.setBounds(1,41,_frameSizeX-7, 0);
		_eqPanel.setBorder(BorderFactory.createEtchedBorder());
		_eqPanel.setLayout(null);
		
		
		
		
		_nOfEqLabelName.setBounds(10, 5, 160, 30);
		_nOfEqLabelName.setFont(FontCollection.font1_15);
		
		_nOfEqLabel.setBounds(170, 5, 30, 26);
		_nOfEqLabel.setFont(FontCollection.font1_15);
		_nOfEqLabel.setBorder(new LineBorder(Color.BLACK,1));
		
		int _vShift = 5;
		_nOfEqUpButton.setBounds(200, _vShift, _buttonSize.width, _buttonSize.height);
		_nOfEqDownButton.setBounds(200, _vShift+_buttonSize.height, _buttonSize.width, _buttonSize.height);

		
		
		
		
		_eqLabelsArray = new JTextField[10];
		_namesArray = new JLabel[10];
		for(int i=0; i<10; i++)
		{
			_eqLabelsArray[i] = new JTextField("X" + (i+1) + "(0)");
			_eqLabelsArray[i].setBounds(50, 5+30*i, 175, 25);
			_eqLabelsArray[i].setBackground(new Color(150, 200, 255));
			_namesArray[i] = new JLabel("X" + (i+1) + "(0) =");
			_namesArray[i].setBounds(5, 5+30*i, 45, 25);
			
		}
		
		_nOfEqPanel.add(_nOfEqLabelName);
		_nOfEqPanel.add(_nOfEqLabel);
		_nOfEqPanel.add(_nOfEqUpButton);
		_nOfEqPanel.add(_nOfEqDownButton);
		
		
		_acceptButton.setBounds(_frameSizeX-130, _frameSizeY-70, 120, 35);
		//_eqPanel.add(_namesArray[0]);
		//_eqPanel.add(_namesArray[1]);
		//_eqPanel.add(_eqLabelsArray[0]);
		//_eqPanel.add(_eqLabelsArray[1]);
		
		_mainPane.add(_nOfEqPanel);
		_mainPane.add(_eqPanel);
		_mainPane.add(_acceptButton);
		
	}
	
	
	
	
	
	
}
