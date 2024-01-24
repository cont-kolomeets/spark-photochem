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

public class GlobalFitInputExtFrame extends JFrame{

	public int _frameSizeX=215, _frameSizeY = 65+30;
	
	
	
	public JButton _inputExtButton = new JButton(IconCollection._ArrowReturnIcon);
	public JLabel _inputExtWavelengthLabelName1 = new JLabel("l =");
	public JLabel _inputExtWavelengthLabel = new JLabel("400.0");
	public JLabel _inputExtWavelengthLabelName2 = new JLabel("nm");
	public JLabel _inputExtFieldName1 = new JLabel("e =");
	public JTextField _inputExtField = new JTextField("0");
	public JLabel _inputExtFieldName2 = new JLabel("M-1cm-1");
	
	
	
	public GlobalFitInputExtFrame()
	{
		createGlobalFitInputExtFrameInterface();
	}
	
	public void createGlobalFitInputExtFrameInterface()
	{


		
		this.setTitle("Abs");
		//this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		_inputExtWavelengthLabelName1.setBounds(5, 0, 20, 30);		
		_inputExtWavelengthLabel.setBounds(25,0,40,30);
		_inputExtWavelengthLabelName2.setBounds(65, 0, 40, 30);
		
		_inputExtButton.setBounds (160, 5, 50, 55);

		_inputExtFieldName1.setBounds(5,30,20,30);
		_inputExtField.setBounds(25,30,80,30);
		_inputExtFieldName2.setBounds(105,30,50,30);
		
		
		
		
		
		this.add(_inputExtButton);
		this.add(_inputExtWavelengthLabel);
		this.add(_inputExtWavelengthLabelName1);
		this.add(_inputExtWavelengthLabelName2);
		this.add(_inputExtField);
		this.add(_inputExtFieldName1);
		this.add(_inputExtFieldName2);



		
		
		
		
	}
	
	
}
