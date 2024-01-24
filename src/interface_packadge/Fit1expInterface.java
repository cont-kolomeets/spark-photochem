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



public class Fit1expInterface extends JFrame{

	public JPanel _1expFittingStatusPanel = new JPanel();
	public JLabel _1expFitFormulaLabel, _1expFitD0ErrLabel,  _1expFitAErrLabel,  _1expFitKErrLabel, _1expFitFromToLabel, _nOfEqLabel, _nOfConstLabel, _globalFitWavelengthLabel;
	public JTextField _1expFitD0ValLabel, _1expFitAValLabel,_1expFitKValLabel;
	
	public JCheckBox _1expD0CheckBox = new JCheckBox("D0");
	public JCheckBox _1expACheckBox = new JCheckBox("A");
	public JCheckBox _1expKCheckBox = new JCheckBox("k");
	
	public JButton _fitButton = new JButton("Fit");
	public JButton _createButton = new JButton("Create curve");
	public JButton _pasteButton = new JButton("Paste");
	
	public JPanel _fromToPane = new JPanel();
	public JPanel _paramPane = new JPanel();
	
	public static final int _1expFitFrameSizeX = 380, _1expFitFrameSizeY = 340;

	
	
	public Fit1expInterface()
	{
		create1expFitInterface();
	}
	
	public void create1expFitInterface()
	{

	
		this.setTitle("1exp fitting");
		this.setSize(new Dimension(_1expFitFrameSizeX,_1expFitFrameSizeY));
		this.setLocation(500, 400);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		
		
		_1expFitFormulaLabel = new JLabel("D = D0 + A*exp(-k*t)", JLabel.CENTER);
		_1expFitD0ValLabel = new JTextField("");
		_1expFitAValLabel = new JTextField("");
		_1expFitKValLabel = new JTextField("");
		_1expFitD0ErrLabel = new JLabel("");
		_1expFitAErrLabel = new JLabel("");
		_1expFitKErrLabel = new JLabel("");
		_1expFitFromToLabel = new JLabel("From 0.0   To 0.0", JLabel.CENTER);
		
		_paramPane.setBounds(5, 70, 280, 130);
		_paramPane.setBorder(BorderFactory.createEtchedBorder());
		_paramPane.setLayout(null);
		
		

		_fromToPane.setBounds(5, 200, 280, 30);
		_fromToPane.setBorder(BorderFactory.createEtchedBorder());
		_fromToPane.setLayout(null);

		
		_1expFittingStatusPanel.setBounds(290, 5, 30, 295);
		_1expFittingStatusPanel.setBackground(Color.green);
		_1expFittingStatusPanel.setBorder(BorderFactory.createEtchedBorder());
		//_fittingStatusPanel.setLayout(null);
		
		
		

		
		

		
		
		_fitButton.setBorder(new LineBorder(Color.gray,1));
		_pasteButton.setBorder(new LineBorder(Color.gray,1));
		_createButton.setBorder(new LineBorder(Color.gray,1));
		
		

		
	
		
		
		//Dimension _buttonSize = new Dimension(25,26);
	
		
		_fitButton.setBounds(155, 240, 130, 30);
		_pasteButton.setBounds(155, 270, 130, 30);
		_createButton.setBounds(5, 240, 150, 60);

		_1expD0CheckBox.setFont(FontCollection.font1);
		_1expD0CheckBox.setSelected(true);
		_1expD0CheckBox.setBounds(5, 10, 50, 30);
		_1expACheckBox.setFont(FontCollection.font1);
		_1expACheckBox.setSelected(true);
		_1expACheckBox.setBounds(5, 50, 50, 30);
		_1expKCheckBox.setFont(FontCollection.font1);
		_1expKCheckBox.setSelected(true);
		_1expKCheckBox.setBounds(5, 90, 50, 30);
	

		
		_1expFitFormulaLabel.setBounds(5, 5, 280, 60);
		_1expFitFormulaLabel.setFont(FontCollection.font3);
		_1expFitFormulaLabel.setOpaque(true);
		_1expFitFormulaLabel.setForeground(Color.black);
		//_1expFitFormulaLabel.setHorizontalTextPosition(JLabel.CENTER);
		//_1expFitFormulaLabel.setBackground(Color.WHITE);
		_1expFitFormulaLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		_1expFitD0ValLabel.setBounds(65, 10, 100, 25);
		_1expFitD0ValLabel.setEditable(false);
		_1expFitD0ValLabel.setFont(FontCollection.font1);
		_1expFitD0ValLabel.setOpaque(true);
		_1expFitD0ValLabel.setBackground(Color.WHITE);
		_1expFitD0ValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_1expFitD0ErrLabel.setBounds(175, 10, 90, 25);
		_1expFitD0ErrLabel.setFont(FontCollection.font1);
		_1expFitD0ErrLabel.setOpaque(true);
		_1expFitD0ErrLabel.setBackground(Color.WHITE);
		_1expFitD0ErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
		_1expFitAValLabel.setBounds(65, 50, 100, 25);
		_1expFitAValLabel.setFont(FontCollection.font1);
		_1expFitAValLabel.setOpaque(true);
		_1expFitAValLabel.setBackground(Color.WHITE);
		_1expFitAValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_1expFitAErrLabel.setBounds(175, 50, 90, 25);
		_1expFitAErrLabel.setFont(FontCollection.font1);
		_1expFitAErrLabel.setOpaque(true);
		_1expFitAErrLabel.setBackground(Color.WHITE);
		_1expFitAErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
		_1expFitKValLabel.setBounds(65,90, 100, 25);
		_1expFitKValLabel.setFont(FontCollection.font1);
		_1expFitKValLabel.setOpaque(true);
		_1expFitKValLabel.setBackground(Color.WHITE);
		_1expFitKValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_1expFitKErrLabel.setBounds(175, 90, 90, 25);
		_1expFitKErrLabel.setFont(FontCollection.font1);
		_1expFitKErrLabel.setOpaque(true);
		_1expFitKErrLabel.setBackground(Color.WHITE);
		_1expFitKErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
		_1expFitFromToLabel.setFont(FontCollection.font1);
		_1expFitFromToLabel.setBounds(0, 0, 300, 30);
		//_1expFitFromToLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		
		this.add(_1expFitFormulaLabel);
		this.add(_paramPane);
		this.add(_fromToPane);
		
		this.add(_fitButton);
		this.add(_pasteButton);
		this.add(_createButton);
		this.add(_1expFittingStatusPanel);
		_paramPane.add(_1expFitD0ValLabel);
		_paramPane.add(_1expFitAValLabel);
		_paramPane.add(_1expFitKValLabel);
		_paramPane.add(_1expFitD0ErrLabel);
		_paramPane.add(_1expFitAErrLabel);
		_paramPane.add(_1expFitKErrLabel);
		_paramPane.add(_1expD0CheckBox);
		_paramPane.add(_1expACheckBox);
		_paramPane.add(_1expKCheckBox);
		_fromToPane.add(_1expFitFromToLabel);
		

		
	}
}
