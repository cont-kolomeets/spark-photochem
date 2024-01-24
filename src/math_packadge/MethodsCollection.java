package math_packadge;

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
import java.awt.Cursor;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;


import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.RuntimeException;

public class MethodsCollection {

	
	public static String cutStringAfterPoint(String s, int _nPoints)
	{
		String s1="", l = "";
		
		if(s.indexOf('.')!=-1)
		{
			if(s.indexOf('E')!=-1)
			{
				s1 = s.substring(s.lastIndexOf('E'));
				s = s.substring(0, s.lastIndexOf('E'));
				l = s.substring((s.indexOf('.')+1));
				if(l.length()>_nPoints)
					s = s.substring(0, (s.indexOf('.')+ 1 + _nPoints));
				
			}
			else
			{
				l = s.substring((s.indexOf('.')+1));
				if(l.length()>_nPoints)
					s = s.substring(0, (s.indexOf('.')+ 1 + _nPoints));
			}

		}

		
		return (s+s1);
	}
	
}
