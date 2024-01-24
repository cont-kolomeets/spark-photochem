package interface_packadge;
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


public class ImagePanel  extends JPanel {

	private static final long serialVersionUID = 1003;
	
	
	public void paint(Graphics g)
	{

		super.paint(g);
		 Graphics2D g2d = (Graphics2D) g;

			 
		 Toolkit tk = Toolkit.getDefaultToolkit();
		Image _StripesImage = tk.getImage("rsc/Stripes3.JPG");

			
				g2d.drawImage(_StripesImage,0,0,this);
				
	}

}
