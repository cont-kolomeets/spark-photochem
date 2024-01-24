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
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GlobalFitGraphPanelScaleFrame extends JFrame{

	
	public int _frameSizeX=190, _frameSizeY = 120;
	
	
	
	public JLabel _xMinLabelName = new JLabel(" X min =");
	public JLabel _yMinLabelName = new JLabel(" Y min =");
	public JLabel _xMaxLabelName = new JLabel(" X max =");
	public JLabel _yMaxLabelName = new JLabel(" Y max =");
	
	public JTextField _xMinField = new JTextField();
	public JTextField _yMinField = new JTextField();
	public JTextField _xMaxField = new JTextField();
	public JTextField _yMaxField = new JTextField();
	
	public JButton _enterButton = new JButton(IconCollection._ArrowReturnIcon);
	
	public GlobalFitGraphPanelScaleFrame()
	{
		
		createGlobalFitGraphPanelScaleFrameInterface();
	}
	
	
	public void createGlobalFitGraphPanelScaleFrameInterface()
	{
		
		
		this.setTitle("XY");
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-500, 100);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		_xMinLabelName.setBounds(5,5, 50, 20);
		_xMinField.setBounds(55,5, 70, 20);
		
		
		_xMaxLabelName.setBounds(5,25, 50, 20);
		_xMaxField.setBounds(55,25, 70, 20);
		
		_yMinLabelName.setBounds(5,45, 50, 20);
		_yMinField.setBounds(55,45, 70, 20);
		
		
		_yMaxLabelName.setBounds(5,65, 50, 20);
		_yMaxField.setBounds(55,65, 70, 20);
		
		_enterButton.setBounds(130,5, 50, 80);
		
		
		
		
		
		this.add(_xMinLabelName);
		this.add(_xMinField);
		this.add(_xMaxLabelName);
		this.add(_xMaxField);
		this.add(_yMinLabelName);
		this.add(_yMinField);
		this.add(_yMaxLabelName);
		this.add(_yMaxField);
		this.add(_enterButton);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
