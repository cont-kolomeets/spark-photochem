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
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.beans.*;
import java.util.Random;


public class GlobalFitSigmaFrame extends JFrame{

	
	public int _frameSizeX=600, _frameSizeY = 400;

	public GlobalFitSigmaPanel _sigmaPanel = new GlobalFitSigmaPanel();
	public JCheckBox _ifCalcWeightsBox = new JCheckBox("Show sigma considering curve weights");
	
	Color _BGColor1 = new Color(255,255,255);
	Color _BGColor2 = Color.WHITE;
	Color _BGColor3 = new Color(100,100,100);
	
	
	//public JLabel _rangeLabel = new JLabel("Set range, %");
	
	
	public GlobalFitSigmaFrame()
	{
		createGlobalFitFittingFrameInterface();
		createSigmaPanelInterface();

	}
	
	public void createGlobalFitFittingFrameInterface()
	{
		this.setTitle("Sigma value");
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_frameSizeX*2, 400);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		
		
		_sigmaPanel.setBounds(0,0, _frameSizeX, _frameSizeY-60);
		_ifCalcWeightsBox.setBounds(10, _frameSizeY-55, 350, 20);
		
		this.add(_sigmaPanel);
		this.add(_ifCalcWeightsBox);
		
	}
	

	
	public void createSigmaPanelInterface()
	{
		_sigmaPanel.setBackground(_BGColor2);
		_sigmaPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_sigmaPanel.setLayout(null);
	}

	
	
	
}
