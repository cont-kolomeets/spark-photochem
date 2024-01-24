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
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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

public class Constants {
	
	public static Dimension _screenDimentions = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int _mainFrameInitSizeX = _screenDimentions.width, _mainFrameInitSizeY = _screenDimentions.height-30;
	public static final Point _kineticsModeGridPosition = new Point(60,40);
	public static final Point _kineticsModeErrorGridPosition = new Point(60,20);
	public static final Point _sigmaPanelGridPosition = new Point(80,40);
	public static final Point _slicePanelGridPosition = new Point(60,40);
	public static final int _extGraphPanelGridSizeX = 500;
	public static final int _extGraphPanelGridSizeY = 510;
	public static final int _slicePanelGridSizeX = 500;
	public static final int _slicePanelGridSizeY = 480;
	public static final int _sigmaPanelGridSizeX = 500;
	public static final int _sigmaPanelGridSizeY = 270;
	public static final int _nOfMajorIterationsAbsDefault = 5;
	public static final int _nOfMajorIterationsConstDefault = 5;
	public static final int _slopeValueAbsDefault = 2;	
	public static final int _slopeValueConstDefault = 2;	
	public static final float _cubeSideAbsDefault = 0.5f;
	public static final float _cubeSideConstDefault = 0.5f;
	public static final float _cubeSideConcDefault = 0.5f;
	public static final int _nOfIterationsGradientAbsDefault = 5;
	public static final int _nOfIterationsGradientConstDefault = 5;
	public static final int _nOfIterationsGradientConcDefault = 5;
	public static final int _directNOfIterationsDefault = 5;
	public static final float _directLowLimit = 0.1f;
	public static final float _directHighLimit = 1.9f;
	
	
	
	public Constants()
	{
		
	}

}
