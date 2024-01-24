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




public class KinecticsModeFit2OrderProductInterface extends JFrame{

	public JLabel _2OrderFitFormulaLabel, _2OrderFitD0ErrLabel,  _2OrderFitAErrLabel,  _2OrderFitKErrLabel, _2OrderFitFromToLabel;
	public JTextField _2OrderFitD0ValLabel, _2OrderFitAValLabel,_2OrderFitKValLabel;

	public JCheckBox _2OrderD0CheckBox = new JCheckBox("A0");
	public JCheckBox _2OrderACheckBox = new JCheckBox("Ai");
	public JCheckBox _2OrderKCheckBox = new JCheckBox("k");
	
	public final int _2OrderFitFrameSizeX = 330, _2OrderFitFrameSizeY = 340;
	
	
	public JPanel _paramPane = new JPanel();
	public JPanel _fromToPane = new JPanel();
	
	
	public JButton _fitButton = new JButton("Fit");
	public JButton _createButton = new JButton("Create curve");
	public JButton _pasteButton = new JButton("Paste");
	
	public JPanel _2OrderFittingStatusPanel = new JPanel();
	
	
	
	
	
	public KinecticsModeFit2OrderProductInterface()
	{
		create2OrderFitInterface();
	}
	
	
	public void create2OrderFitInterface()
	{

	
		this.setTitle("2nd order fit product");
		this.setSize(new Dimension(_2OrderFitFrameSizeX,_2OrderFitFrameSizeY));
		this.setLocation(500, 400);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		
		
		
		_2OrderFitFormulaLabel = new JLabel("D=(A0+k*Ai*t)/(1+k*t)", JLabel.CENTER);
		_2OrderFitD0ValLabel = new JTextField("0");
		_2OrderFitAValLabel = new JTextField("0");
		_2OrderFitKValLabel = new JTextField("0");
		_2OrderFitD0ErrLabel = new JLabel("0");
		_2OrderFitAErrLabel = new JLabel("0");
		_2OrderFitKErrLabel = new JLabel("0");
		_2OrderFitFromToLabel = new JLabel("From 0.0   To 0.0", JLabel.CENTER);
		
		
			
	
		_paramPane.setBounds(5, 70, 280, 130);
		_paramPane.setBorder(BorderFactory.createEtchedBorder());
		_paramPane.setLayout(null);
		
		

		_fromToPane.setBounds(5, 200, 280, 30);
		_fromToPane.setBorder(BorderFactory.createEtchedBorder());
		_fromToPane.setLayout(null);

		
		_2OrderFittingStatusPanel.setBounds(290, 5, 30, 295);
		_2OrderFittingStatusPanel.setBackground(Color.green);
		_2OrderFittingStatusPanel.setBorder(BorderFactory.createEtchedBorder());
		//_fittingStatusPanel.setLayout(null);
		
		
		_fitButton.setBorder(new LineBorder(Color.gray,1));
		_pasteButton.setBorder(new LineBorder(Color.gray,1));
		_createButton.setBorder(new LineBorder(Color.gray,1));
		
		

		
		
		

		
		
		//Dimension _buttonSize = new Dimension(25,26);
	
		
		_fitButton.setBounds(155, 240, 130, 30);
		_pasteButton.setBounds(155, 270, 130, 30);
		_createButton.setBounds(5, 240, 150, 60);

		_2OrderD0CheckBox.setFont(FontCollection.font1);
		_2OrderD0CheckBox.setSelected(true);
		_2OrderD0CheckBox.setBounds(5, 10, 50, 30);
		_2OrderACheckBox.setFont(FontCollection.font1);
		_2OrderACheckBox.setSelected(true);
		_2OrderACheckBox.setBounds(5, 50, 60, 30);
		_2OrderKCheckBox.setFont(FontCollection.font1);
		_2OrderKCheckBox.setSelected(true);
		_2OrderKCheckBox.setBounds(5, 90, 50, 30);
	

		
		_2OrderFitFormulaLabel.setBounds(5, 5, 280, 60);
		_2OrderFitFormulaLabel.setFont(FontCollection.font3);
		_2OrderFitFormulaLabel.setOpaque(true);
		_2OrderFitFormulaLabel.setForeground(Color.black);
		//_1expFitFormulaLabel.setHorizontalTextPosition(JLabel.CENTER);
		//_1expFitFormulaLabel.setBackground(Color.WHITE);
		_2OrderFitFormulaLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		_2OrderFitD0ValLabel.setBounds(65, 10, 100, 25);
		//_2OrderFitD0ValLabel.setEditable(false);
		_2OrderFitD0ValLabel.setFont(FontCollection.font1);
		_2OrderFitD0ValLabel.setOpaque(true);
		_2OrderFitD0ValLabel.setBackground(Color.WHITE);
		_2OrderFitD0ValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_2OrderFitD0ErrLabel.setBounds(175, 10, 90, 25);
		_2OrderFitD0ErrLabel.setFont(FontCollection.font1);
		_2OrderFitD0ErrLabel.setOpaque(true);
		_2OrderFitD0ErrLabel.setBackground(Color.WHITE);
		_2OrderFitD0ErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
		_2OrderFitAValLabel.setBounds(65, 50, 100, 25);
		_2OrderFitAValLabel.setFont(FontCollection.font1);
		_2OrderFitAValLabel.setOpaque(true);
		_2OrderFitAValLabel.setBackground(Color.WHITE);
		_2OrderFitAValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_2OrderFitAErrLabel.setBounds(175, 50, 90, 25);
		_2OrderFitAErrLabel.setFont(FontCollection.font1);
		_2OrderFitAErrLabel.setOpaque(true);
		_2OrderFitAErrLabel.setBackground(Color.WHITE);
		_2OrderFitAErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
		_2OrderFitKValLabel.setBounds(65,90, 100, 25);
		_2OrderFitKValLabel.setFont(FontCollection.font1);
		_2OrderFitKValLabel.setOpaque(true);
		_2OrderFitKValLabel.setBackground(Color.WHITE);
		_2OrderFitKValLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		_2OrderFitKErrLabel.setBounds(175, 90, 90, 25);
		_2OrderFitKErrLabel.setFont(FontCollection.font1);
		_2OrderFitKErrLabel.setOpaque(true);
		_2OrderFitKErrLabel.setBackground(Color.WHITE);
		_2OrderFitKErrLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			
		_2OrderFitFromToLabel.setFont(FontCollection.font1);
		_2OrderFitFromToLabel.setBounds(0, 0, 300, 30);
		//_1expFitFromToLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		
		this.add(_2OrderFitFormulaLabel);
		this.add(_paramPane);
		this.add(_fromToPane);
		
		this.add(_fitButton);
		this.add(_pasteButton);
		this.add(_createButton);
		this.add(_2OrderFittingStatusPanel);
		_paramPane.add(_2OrderFitD0ValLabel);
		_paramPane.add(_2OrderFitAValLabel);
		_paramPane.add(_2OrderFitKValLabel);
		_paramPane.add(_2OrderFitD0ErrLabel);
		_paramPane.add(_2OrderFitAErrLabel);
		_paramPane.add(_2OrderFitKErrLabel);
		_paramPane.add(_2OrderD0CheckBox);
		_paramPane.add(_2OrderACheckBox);
		_paramPane.add(_2OrderKCheckBox);
		_fromToPane.add(_2OrderFitFromToLabel);
		
	}
	
	
	
	
	
}
