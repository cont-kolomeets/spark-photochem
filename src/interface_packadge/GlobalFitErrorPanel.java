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

public class GlobalFitErrorPanel extends JPanel{
	
	
	public boolean _enableAABox = true;
	public boolean _ifShowCreatedCurve = false;
	public boolean _ifEnableCurveBounds = false;
	public boolean _ifSolvedCurveCollectionLocked = false;
	public boolean _ifDone = false;
	public int _gridSizeX, _gridSizeY;
	public float _magPosX = 0, _magPosY = 0;
	public float _xMax=500,_xMin=0,_yMax=0,_yMin=-500,_xScaler=2f,_yScaler=2f; //_yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	public int _globalFitPosOfSelectedKin=0;
	public int _posToStartFittingWith=0;
	public int _centerPos;
	
	
	
	public ArrayList<Float> _solvedCurveCollectionX = new ArrayList<Float>();
	public ArrayList<ArrayList<Float>> _solvedCurveErrorCollectionY = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Integer>> _markersCollection = new ArrayList<ArrayList<Integer>>();
	
	
	
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
	    
	
	
	
public	GlobalFitErrorPanel()

{
	createErrorPanelInterface();
	
	
	
}
	
public synchronized void paint(Graphics g)
{
	_ifDone = false;
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;

    if(_enableAABox)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


	try
	{
		//drawRect(g2d);
		drawGrid(g2d);
		drawCurves(g2d);
		//drawMarkers(g2d);
	}
	catch(IndexOutOfBoundsException e)
	{
		System.out.println("Global fit error Panel: Index out of bounds exception!");
	
	}
	catch(NullPointerException e)
	{
		System.out.println("Global fit error Panel: Null pointer exception!");
	}
	finally
	{
		_ifDone = true;
	}
	
}	
	
	
public void createErrorPanelInterface()
{
	this.setDoubleBuffered(true);
	this.setBackground(Color.white);
	this.setBorder(new LineBorder(Color.BLACK,1));
	this.setBorder(BorderFactory.createTitledBorder("Error Panel"));
	this.setLayout(null);
	
}

/*
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


*/



public void drawCurves(Graphics2D g2d)
{
	
	
	int inc=1;
	int _start=0, _end=0;
	
	
	_centerPos = Math.round(_gridSizeY/2);
	g2d.setPaint(Color.black);
	g2d.setStroke(new BasicStroke(0.5f)); 

	g2d.draw(new Line2D.Float(Constants._kineticsModeErrorGridPosition.x, _centerPos+Constants._kineticsModeErrorGridPosition.y, Constants._kineticsModeErrorGridPosition.x+_gridSizeX, _centerPos+Constants._kineticsModeErrorGridPosition.y));

	
	
	for(int j=0; j<_solvedCurveErrorCollectionY.size(); j++)
	{
		//inc = Math.round(_solvedCurveCollectionX.size()/3000);
		//if(inc==0)inc=1;			
		

		
		g2d.setStroke(new BasicStroke(0.5f));
		g2d.setPaint(new Color(200,150,150));
		
		
		if(_ifEnableCurveBounds)
		{
			_start = _markersCollection.get(j).get(0)+1;
			_end = _markersCollection.get(j).get(1);
		}
		else
		{
			_start = _posToStartFittingWith+1;
			_end = _solvedCurveCollectionX.size();
		}
		
		
		
		if(j!=_globalFitPosOfSelectedKin)
		for(int i=_start;i<_end; i=i+inc)
		{
			g2d.draw(new Line2D.Float((_solvedCurveCollectionX.get(i-1)-_xMin)*_xScaler+Constants._kineticsModeErrorGridPosition.x-_magPosX,
										(_solvedCurveErrorCollectionY.get(j).get(i-1))*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos,
										(_solvedCurveCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeErrorGridPosition.x-_magPosX,
										(_solvedCurveErrorCollectionY.get(j).get(i))*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos)); 
		}

	}
	
	

	
	//draw selected

	
	if(_solvedCurveErrorCollectionY.size()!=0)
	{
		int j=_globalFitPosOfSelectedKin;
		inc=1;
		g2d.setPaint(new Color(255,0,0));
		g2d.setStroke(new BasicStroke(1.0f));
		
		
		
		
		if(_ifEnableCurveBounds)
		{
			_start = _markersCollection.get(j).get(0)+1;
			_end = _markersCollection.get(j).get(1);
		}
		else
		{
			_start = _posToStartFittingWith+1;
			_end = _solvedCurveCollectionX.size();
		}
		
		
		
		
		for(int i=_start;i<_end; i=i+inc)
		{
			g2d.draw(new Line2D.Float((_solvedCurveCollectionX.get(i-1)-_xMin)*_xScaler+Constants._kineticsModeErrorGridPosition.x-_magPosX,
										(_solvedCurveErrorCollectionY.get(j).get(i-1))*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos,
										(_solvedCurveCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeErrorGridPosition.x-_magPosX,
										(_solvedCurveErrorCollectionY.get(j).get(i))*_yScaler+Constants._kineticsModeErrorGridPosition.y+_centerPos)); 
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


	String _max = "" + (-_yMin);
	_max = cutStringAfterPoint(_max, 4);
	g2d.drawString(_max, 5, Constants._kineticsModeErrorGridPosition.y+10);
	String _min = "" + (-_yMax);
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









public final void set_ifSolvedCurveCollectionLocked(boolean _ifSolvedCurveCollectionLocked)
{
	this._ifSolvedCurveCollectionLocked = _ifSolvedCurveCollectionLocked;
}

public final boolean get_ifSolvedCurveCollectionLocked()
{
	return _ifSolvedCurveCollectionLocked;
}





public final void set_gridSizeX (int _gridSizeX)
{
	this._gridSizeX = _gridSizeX;
}

public final void set_gridSizeY (int _gridSizeY)
{
	this._gridSizeY = _gridSizeY;
}

public final void set_xScaler (float _xScaler)
{
	this._xScaler = _xScaler;
}

public final void set_yScaler (float _yScaler)
{
	this._yScaler = _yScaler;
}


public final void set_xMin (float _xMin)
{
	this._xMin = _xMin;
}

public final void set_yMin (float _yMin)
{
	this._yMin = _yMin;
}

public final void set_xMax (float _xMax)
{
	this._xMax = _xMax;
}

public final void set_yMax (float _yMax)
{

	this._yMax = _yMax;
}

public final void set_magPosX (float _magPosX)
{
	this._magPosX = _magPosX;
}

public final void set_magPosY (float _magPosY)
{
	this._magPosY = _magPosY;
}
/*
public final void set_nOfCreatedCurvePoints (int _nOfCreatedCurvePoints)
{
	this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
}


public final void set_enableAABox (boolean _enableAABox)
{
	this._enableAABox = _enableAABox;
}

	
public final void set_createdCurveArray (ArrayList<float[]> _createdCurveArray)
{
	this._createdCurveArray = _createdCurveArray;
}
	
*/

public final void set_fittingColor(Color _fittingColor)
{
	this._fittingColor = _fittingColor;
}

public final Color get_fittingColor()
{
	return _fittingColor;
}


public final void set_ifShowCreatedCurve (boolean _ifShowCreatedCurve)
{
	this._ifShowCreatedCurve = _ifShowCreatedCurve;
}



public final void set_solvedCurveCollectionX(ArrayList<Float> _solvedCurveCollectionX)
{
	this._solvedCurveCollectionX = _solvedCurveCollectionX;
}

public final ArrayList<Float> get_solvedCurveCollectionX()
{
	return _solvedCurveCollectionX;
}



public final void set_solvedCurveErrorCollectionY(ArrayList<ArrayList<Float>> _solvedCurveErrorCollectionY)
{
	this._solvedCurveErrorCollectionY = _solvedCurveErrorCollectionY;
}

public final ArrayList<ArrayList<Float>> get_solvedCurveErrorCollectionY()
{
	return _solvedCurveErrorCollectionY;
}







public final void set_globalFitPosOfSelectedKin(int _globalFitPosOfSelectedKin)
{
	this._globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin;
}

public final int get_globalFitPosOfSelectedKin()
{
	return _globalFitPosOfSelectedKin;
}



public final void set_posToStartFittingWith(int _posToStartFittingWith)
{
	this._posToStartFittingWith=_posToStartFittingWith;
}


public final void set_markersCollection(ArrayList<ArrayList<Integer>> _markersCollection)
{
	this._markersCollection = _markersCollection;
}

public final ArrayList<ArrayList<Integer>> get_markersCollection()
{
	return _markersCollection;
}





public final boolean get_ifEnableCurveBounds()
{
	return _ifEnableCurveBounds;
}


public final void set_ifEnableCurveBounds(boolean _ifEnableCurveBounds)
{
	this._ifEnableCurveBounds=_ifEnableCurveBounds;
}



public final void set_ifDone(boolean _ifDone)
{
	this._ifDone = _ifDone;
}

public final boolean get_ifDone()
{
	return _ifDone;
}


}
