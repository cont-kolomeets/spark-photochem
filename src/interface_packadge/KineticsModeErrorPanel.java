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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class KineticsModeErrorPanel extends JPanel{
	
	
	//public JLabel _mouseLabel;
	public boolean _enableAABox = true;
	//public boolean _enableLowDetail = false;
	public boolean _ifShowCreatedCurve = false;
	//public boolean _ifShowAverageValue = false;
	public int _gridSizeX, _gridSizeY;
	//public int _nCellsXIni=10, _nCellsYIni=10,_nCellsX=20, _nCellsY=20;
	//public float _magX1=0,_magY1=0, _magX2=Constants._kineticsModeGridPosition.x+_gridSizeX, _magY2=Constants._kineticsModeGridPosition.x+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0;
	//public float _cellSizeX = (_gridSizeX/_nCellsX);
	//public float _cellSizeY = (_gridSizeY/_nCellsY);
	//public float _levelPosReal = -10f, _levelPosDisplay, _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public float _xMax=500,_xMin=0,_yMax=0,_yMin=-500,_xScaler=2f,_yScaler=2f; //_yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	//public float _zeroPosReal = 0, _zeroPosDisplay=0;
	//public float _beforeZeroPosReal = 0, _beforeZeroPosDisplay=0;
	//public int _arraySize=0;
	public int _nOfCreatedCurvePoints;
	//public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	//public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	//public float _mouseX, _mouseY;
	//public float _graphAverage = 0;
	public int _centerPos;
	
	//public float[] _xArray;
	//public float[] _yArray;
	public ArrayList<float[]> _createdCurveArray;
	
	
	
	
	
	
	
	public Color _fittingColor = Color.red;
	  float strokeThickness = 1.0f;
	  float miterLimit = 10f;
	  float[] dashPattern = { 5f };
	  float[] dashPattern1 = { 2f };
	  float dashPhase = 5f;
	    BasicStroke _dashedStroke = new BasicStroke(strokeThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,	    		
	        miterLimit, dashPattern, dashPhase);
	    BasicStroke _tinyDashedStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,	    		
		        miterLimit, dashPattern1, dashPhase);
	    
	    Cursor _cursor;
	    
	
	
	
public	KineticsModeErrorPanel()

{
	createErrorPanelInterface();
	
	
	
}
	
public void paint(Graphics g)
{
super.paint(g);
 Graphics2D g2d = (Graphics2D) g;

    if(_enableAABox)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	//drawRect(g2d);
	drawGrid(g2d);
	drawKin(g2d);
	//drawMarkers(g2d);
	
}	
	
	
public void createErrorPanelInterface()
{
	this.setDoubleBuffered(true);
	this.setBackground(Color.white);
	this.setBorder(new LineBorder(Color.BLACK,1));
	this.setBorder(BorderFactory.createTitledBorder("Error Panel"));
	this.setLayout(null);
	//_mouseLabel = new JLabel("0.0   0.0");
	//_mouseLabel.setBounds(100, 10, 300, 20);
	//this.add(_mouseLabel);
	
}


public void drawKin(Graphics2D g2d)
{
		
		_centerPos = Math.round(_gridSizeY/2);
		
		if(_ifShowCreatedCurve)
		{
			g2d.setPaint(Color.black);
			g2d.setStroke(new BasicStroke(0.5f)); 

			g2d.draw(new Line2D.Float(Constants._kineticsModeErrorGridPosition.x, _centerPos+Constants._kineticsModeErrorGridPosition.y, Constants._kineticsModeErrorGridPosition.x+_gridSizeX, _centerPos+Constants._kineticsModeErrorGridPosition.y));
			
			g2d.setPaint(Color.red);
			g2d.setStroke(new BasicStroke(0.5f)); 
			

		 for(int i=1;i<(_nOfCreatedCurvePoints); i++)
		 {
			 g2d.draw(new Line2D.Float(_createdCurveArray.get(0)[i-1]*_xScaler+Constants._kineticsModeErrorGridPosition.x-_magPosX,-_createdCurveArray.get(1)[i-1]*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos,_createdCurveArray.get(0)[i]*_xScaler+Constants._kineticsModeErrorGridPosition.x - _magPosX,-_createdCurveArray.get(1)[i]*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos)); 
		 }
		}
	 
		

}



public void drawGrid(Graphics2D g2d)
{
	//String s;
	
	
	
	// Draw grid
	g2d.setStroke(_dashedStroke);
	g2d.setPaint(Color.GRAY);		
	g2d.draw(new Rectangle(Constants._kineticsModeErrorGridPosition.x, Constants._kineticsModeErrorGridPosition.y,_gridSizeX, _gridSizeY));


	String _max = "" + _yMax;
	_max = cutStringAfterPoint(_max, 4);
	g2d.drawString(_max, 5, Constants._kineticsModeErrorGridPosition.y+10);
	String _min = "" + _yMin;
	_min = cutStringAfterPoint(_min, 4);
	g2d.drawString(_min, 5, Constants._kineticsModeErrorGridPosition.y + _gridSizeY);



}


public  static String cutStringAfterPoint(String s, int _nPoints)
{
	String s1="";
	//int _cutSize=0;
	boolean ok = false;

	if(s.indexOf('E')!=-1)
		s1 = s.substring(s.lastIndexOf('E'));

	
		while((!ok)&(_nPoints>=0))
		{

			if(( s.length() -s.indexOf('.'))>=(_nPoints+1))
			{
				s = s.substring(0,s.indexOf('.')+_nPoints+1);
				ok = true;
			}
			_nPoints--;
		}


		
	
	return (s+s1);
}













public void set_gridSizeX (int _gridSizeX)
{
	this._gridSizeX = _gridSizeX;
}

public void set_gridSizeY (int _gridSizeY)
{
	this._gridSizeY = _gridSizeY;
}

public void set_xScaler (float _xScaler)
{
	this._xScaler = _xScaler;
}

public void set_yScaler (float _yScaler)
{
	this._yScaler = _yScaler;
}


public void set_xMin (float _xMin)
{
	this._xMin = _xMin;
}

public void set_yMin (float _yMin)
{
	this._yMin = _yMin;
}

public void set_xMax (float _xMax)
{
	this._xMax = _xMax;
}

public void set_yMax (float _yMax)
{

	this._yMax = _yMax;
}

public void set_magPosX (float _magPosX)
{
	this._magPosX = _magPosX;
}

public void set_magPosY (float _magPosY)
{
	this._magPosY = _magPosY;
}

public void set_nOfCreatedCurvePoints (int _nOfCreatedCurvePoints)
{
	this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
}


public void set_enableAABox (boolean _enableAABox)
{
	this._enableAABox = _enableAABox;
}

	
public void set_createdCurveArray (ArrayList<float[]> _createdCurveArray)
{
	this._createdCurveArray = _createdCurveArray;
}
	


public void set_fittingColor(Color _fittingColor)
{
	this._fittingColor = _fittingColor;
}

public Color get_fittingColor()
{
	return _fittingColor;
}


public void set_ifShowCreatedCurve (boolean _ifShowCreatedCurve)
{
	this._ifShowCreatedCurve = _ifShowCreatedCurve;
}





}
