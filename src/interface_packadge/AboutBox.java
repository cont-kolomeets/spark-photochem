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
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class AboutBox extends JFrame{
	
	


	private JTextArea _aboutText1 = new JTextArea();
	private JTextArea _aboutText2 = new JTextArea();
	private JTextArea _aboutText3 = new JTextArea();
	
	private JPanel _aboutPanel = new JPanel();
	public final int _aboutPanelSizeX = 680, _aboutPanelSizeY = 294;
	public LogoPanel _logoPanel = new LogoPanel();
	
	
	
	public AboutBox()
	{
		createAboutBoxInterface();
		createLogoPanleInterface();
	}

	
	
	public void createAboutBoxInterface()
	{
		this.setTitle("About this program");
		this.setSize(new Dimension(_aboutPanelSizeX,_aboutPanelSizeY));
		this.setLocation(500, 300);
		
		
		
		_aboutPanel.setBackground(new Color(255,255,255));
		_aboutPanel.setBorder(new LineBorder(Color.BLACK,1));
		_aboutPanel.setBorder(BorderFactory.createEtchedBorder());
		_aboutPanel.setBounds(0, 0, _aboutPanelSizeX, _aboutPanelSizeY);
		_aboutPanel.setLayout(null);		
		
		
		
		
		
		this.add(_aboutPanel);

		
		
		
		
		_aboutText1.setFont(FontCollection.fontAbout1);
		_aboutText1.setBackground(Color.white);//new Color(100,150, 220));
		_aboutText1.setBorder(BorderFactory.createEtchedBorder());
		_aboutText1.setEditable(false);
		_aboutText1.setText("");
		_aboutText1.setForeground(Color.red);
		_aboutText1.append("SPARK\n");
		_aboutText1.setBounds(0, 0, 400, 40);		
		
		_aboutText2.setFont(FontCollection.fontAbout2);
		_aboutText2.setBackground(Color.white);//new Color(100,150, 220));
		_aboutText2.setBorder(BorderFactory.createEtchedBorder());
		_aboutText2.setEditable(false);
		_aboutText2.setText("");
		_aboutText2.setForeground(Color.black);
		_aboutText2.append("Software for Photochemical kinetic Analysis\n");
		_aboutText2.append("using Runge-Kutta method with global optimization.\n");
		_aboutText2.setBounds(0, 40, 400, 50);		
		
		
		
		
		
		
		
		
		_aboutText3.setFont(FontCollection.fontAbout3);
		_aboutText3.setBackground(Color.white);//new Color(100,150, 220));
		_aboutText3.setBorder(BorderFactory.createEtchedBorder());
		_aboutText3.setEditable(false);
		_aboutText3.setText("");
		_aboutText3.setForeground(Color.black);
		//_aboutText3.append("SPARK\n");
		//_aboutText3.append("Software for Photochemical Analysis using Runge-Kutta method.\n");
		_aboutText3.append("This program has been created\n");
		_aboutText3.append("In the Laboratory of Photochemistry," + "\n");
		_aboutText3.append("Institute of Chemical Kinetics and Combustion\n");
		_aboutText3.append("Russia, Novosibirsk. Release 2.2 from 2011.11.30\n");
		_aboutText3.append("e-mail: kolomeets@live.ru\n");
		_aboutText3.append(" \n");
		_aboutText3.append("This product has no copyright.\n");
		_aboutText3.setBounds(0, 90, 400, 170);		


		
		
		
		
		
		
		
		
		
		
		_aboutPanel.add(_aboutText1);
		_aboutPanel.add(_aboutText2);
		_aboutPanel.add(_aboutText3);
		_aboutPanel.add(_logoPanel);
		_aboutPanel.repaint();
		_logoPanel.repaint();
	}
	
	
	
	
	
	
	
	
	public void createLogoPanleInterface()
	{
		_logoPanel.setBackground(Color.white);
		//_logoPanel.setBorder(new LineBorder(Color.BLACK,1));
		_logoPanel.setBorder(BorderFactory.createEmptyBorder());
		_logoPanel.setBounds(410, 2, 250, 250);
		//_logoPanel.setLayout(null);	
	}
}
