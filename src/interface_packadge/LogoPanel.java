package interface_packadge;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.lang.*;

public class LogoPanel extends JPanel {
	
	JFrame frame1 = new JFrame();
	public void paint(Graphics g)
	{
		super.paint(g);
		 Graphics2D g2d = (Graphics2D) g;

		 


		 
		//Toolkit tk = Toolkit.getDefaultToolkit();
		try
		{
			JFrame frame1 = new JFrame();
			URL url = frame1.getClass().getResource("/images/Logo19.png"); 
			Image _logoImage = ImageIO.read(url);
			g2d.drawImage(_logoImage,0,0,this);
		}
		catch(IOException e)
		{
			
		}

		
	}

}
