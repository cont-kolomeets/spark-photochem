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

public class GlobalFitGraphPanel extends JPanel{
	
	public Object _syncObject;
	public JLabel _mouseLabel;
	public boolean _enableAABox = true;
	public boolean _enableLowDetail = false;
	public boolean _ifShowCreatedCurve = false;
	public boolean _ifShowAverageValue = false;
	public boolean _ifCalcCurveShifts = false;
	public boolean _ifEnableCurveBounds = false;
	public boolean _ifSolvedCurveCollectionLocked = false;
	public boolean _ifLockGraphPanels = false;
	public boolean _ifCalcContributions = false;
	public boolean _ifShowBackgroundCurves = true;
	public boolean _ifDone = false;
	public boolean _ifShowSlice = false;
	
	public int _gridSizeX=100, _gridSizeY=100;
	public int _gridPosX = Constants._kineticsModeGridPosition.x, _gridPosY = Constants._kineticsModeGridPosition.y;
	//public float _magX1=0,_magY1=0, _magX2=_gridPosX+_gridSizeX, _magY2=_gridPosX+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0;
	public float _levelPosReal = 200f, _levelPosDisplay=0;
	public float _xMax=1000,_xMin=-200,_yMax=0,_yMin=-500,_xScaler=1f,_yScaler=1f, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	public float _zeroPosReal = 200, _zeroPosDisplay=0;
	public float _beforeZeroPosReal = 0, _beforeZeroPosDisplay=0;
	public float _slicePosReal = 0, _slicePosDisplay=0;
	public int _arraySize=0;
	public float _offSetX = 0, _offSetY = 0; 
	public int _nOfCreatedCurvePoints;
	public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	public float _mouseX, _mouseY;
	public float _graphAverage = 0;
	public int _globalFitPosOfSelectedKin=0;
	//public float _globalFitXMax=-10, _globalFitXMin=10, _globalFitYMax=-10, _globalFitYMin=10;
	
	public float[] _xArray;
	public float[] _yArray;
	public ArrayList<float[]> _createdCurveArray;
	public ArrayList<ArrayList<Float>> _kinCollectionX;
	public ArrayList<ArrayList<Float>> _kinCollectionY;
	public ArrayList<Float> _solvedCurveCollectionX;
	public ArrayList<ArrayList<Float>> _solvedCurveCollectionY;
	public ArrayList<ArrayList<Integer>> _markersCollection;
	public ArrayList<ArrayList<ArrayList<Float>>> _solvedCurveContributionCollectionY;

	
	  public Rectangle2D.Float myRect = new Rectangle2D.Float(_gridPosX, _levelPosReal, _gridSizeX, 10);
	  public Rectangle2D.Float myZeroRect = new Rectangle2D.Float(_zeroPosReal,_gridPosY, 10, _gridSizeY);
	  public Rectangle2D.Float myBeforeZeroRect = new Rectangle2D.Float(_beforeZeroPosReal,_gridPosY, 10, _gridSizeY);
	  public Rectangle2D.Float sliceRect = new Rectangle2D.Float(_slicePosReal,_gridPosY, 10, _gridSizeY);
	  public Rectangle2D.Float selectedRect = new Rectangle2D.Float(0,0,0,0);
	  public Rectangle2D.Float beforeZeroSelRect = new Rectangle2D.Float(_gridPosX,_gridPosY,10,_gridSizeY);
	  public Rectangle2D.Float fitMarkerFirstRect = new Rectangle2D.Float(0,0,13,35);
	  public Rectangle2D.Float fitMarkerLastRect = new Rectangle2D.Float(0,0,13,35);
	
	

	
	
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
	    
	    GlobalFitGraphicGrid _grid = new GlobalFitGraphicGrid();
	
	
	
public	GlobalFitGraphPanel()

{
	createGraphPanelInterface();
	
	
	
}
	
public void paint(Graphics g)
{


		
		_ifDone = false;
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if(_enableAABox)
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


		   try
			{
				//System.out.println("------------------------------");
				//System.out.println("GraphPanel : started drawing.");
				drawRect(g2d);
				drawGrid(g2d);
				drawKin(g2d);

				
				//System.out.println("GraphPanel : finished drawing.");
				//System.out.println("------------------------------");
				
				if(_ifEnableCurveBounds)
				{
					if(_kinCollectionX.size()!=0)
						drawMarkers(g2d);
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Global fit graph Panel: Index out of bounds exception!");
			}
			catch(NullPointerException e)
			{
				System.out.println("Global fit graph Panel: Null pointer exception!");
			}
			finally
			{
				_ifDone = true;
			}

			
	
    
 
	
}	
	
	
	
public void createGraphPanelInterface()
{
	this.setDoubleBuffered(true);
	this.setBackground(Color.white);
	this.setBorder(new LineBorder(Color.BLACK,1));
	this.setBorder(BorderFactory.createTitledBorder("Graph Panel"));
	this.setLayout(null);
	_mouseLabel = new JLabel("0.0   0.0");
	_mouseLabel.setBounds(100, 10, 300, 20);
	this.add(_mouseLabel);
	
	

}



public void drawRect(Graphics2D g2d)
{

	
	if(_ifCalcCurveShifts)
	{
		g2d.setPaint(Color.LIGHT_GRAY);
		//beforZeroSelRectangle = new
	    g2d.fill(new Rectangle2D.Float(_gridPosX, _gridPosY, _beforeZeroPosReal-_magPosX-_gridPosX,_gridSizeY));
	}
	
	g2d.setStroke(_tinyDashedStroke);
	g2d.setPaint(new Color(10, 10, 10));		
	g2d.draw(selectedRect);


}




public void drawGrid(Graphics2D g2d)
{
	transferDataToGrid();
	_grid.drawGrid(g2d);
	
}


public void transferDataToGrid()
{
	_grid._xScaler = this._xScaler;
	_grid._yScaler =  this._yScaler;
	_grid._xScalerIni = this._xScalerIni;
	_grid._yScalerIni=  this._yScalerIni;
	_grid._magPosX = this._magPosX;
	_grid._magPosY = this._magPosY;
	_grid._gridPosX = this._gridPosX;
	_grid._gridPosY = this._gridPosY;
	_grid._gridSizeX = this._gridSizeX;
	_grid._gridSizeY = this._gridSizeY;
	_grid._xMin =  this._xMin;
	_grid._yMin = this._yMin;
	_grid._xMax = this._xMax;
	_grid._yMax = this._yMax;
}


public void drawKin(Graphics2D g2d)
{

	int inc;
	
	if(!_enableLowDetail){inc=1;}
	g2d.setPaint(new Color(0,0,255));
	g2d.setStroke(new BasicStroke(1.0f)); 

	if(_ifShowBackgroundCurves)
	{
		for(int j=0; j<_kinCollectionX.size(); j++)
		{
			inc = Math.round(_kinCollectionX.get(j).size()/3000);
			if(inc==0)inc=1;			
			

			g2d.setPaint(new Color(200,200,200));
			g2d.setStroke(new BasicStroke(1f)); 

			
			
			
			
			
			
			
			if(j!=_globalFitPosOfSelectedKin)
			for(int i=inc+1;i<(_kinCollectionX.get(j).size()); i=i+inc)
			{
				g2d.draw(new Line2D.Float((_kinCollectionX.get(j).get(i-inc)-_xMin)*_xScaler+_gridPosX-_magPosX,(_kinCollectionY.get(j).get(i-inc)-_yMin)*_yScaler+_gridPosY-_magPosY,
											(_kinCollectionX.get(j).get(i)-_xMin)*_xScaler+_gridPosX-_magPosX,(_kinCollectionY.get(j).get(i)-_yMin)*_yScaler+_gridPosY-_magPosY)); 
			}

		}
		
		
		
		
		if(!_ifLockGraphPanels)	
		for(int j=0; j<_solvedCurveCollectionY.size(); j++)
		{
			inc = Math.round(_solvedCurveCollectionX.size()/3000);
			if(inc==0)inc=1;			
			

			
			g2d.setStroke(new BasicStroke(1f));
			g2d.setPaint(new Color(250,200,200));
			
			
			
			
			
			
			
			for(int i=2;((i<(_solvedCurveCollectionX.size()))&(i<(_solvedCurveCollectionY.get(j).size()))); i=i+inc)
			{
				g2d.draw(new Line2D.Float((_solvedCurveCollectionX.get(i-1)-_xMin)*_xScaler+_gridPosX-_magPosX,
											(_solvedCurveCollectionY.get(j).get(i-1)-_yMin)*_yScaler+_gridPosY-_magPosY,
											(_solvedCurveCollectionX.get(i)-_xMin)*_xScaler+_gridPosX-_magPosX,
											(_solvedCurveCollectionY.get(j).get(i)-_yMin)*_yScaler+_gridPosY-_magPosY)); 
			}

		}
	}

	
	

	
	//draw selected
	if(_kinCollectionX.size()!=0)
	{
		int j = _globalFitPosOfSelectedKin;
		inc=1;
		g2d.setPaint(new Color(0,0,255));
		g2d.setStroke(new BasicStroke(1.0f));
		
		for(int i=inc+1;i<(_kinCollectionX.get(j).size()); i=i+inc)
		{
			g2d.draw(new Line2D.Float((_kinCollectionX.get(j).get(i-inc)-_xMin)*_xScaler+_gridPosX-_magPosX,(_kinCollectionY.get(j).get(i-inc)-_yMin)*_yScaler+_gridPosY-_magPosY,
										(_kinCollectionX.get(j).get(i)-_xMin)*_xScaler+_gridPosX-_magPosX,(_kinCollectionY.get(j).get(i)-_yMin)*_yScaler+_gridPosY-_magPosY)); 
		}

	}
	
	
	if(!_ifLockGraphPanels)	
	if(_solvedCurveCollectionY.size()!=0)
	{
		int j=_globalFitPosOfSelectedKin;
		inc=1;
		g2d.setPaint(new Color(255,0,0));
		g2d.setStroke(new BasicStroke(1.0f));
		
		for(int i=2;i<(_solvedCurveCollectionX.size()); i=i+inc)
		{
			g2d.draw(new Line2D.Float((_solvedCurveCollectionX.get(i-1)-_xMin)*_xScaler+_gridPosX-_magPosX,(_solvedCurveCollectionY.get(j).get(i-1)-_yMin)*_yScaler+_gridPosY-_magPosY,
										(_solvedCurveCollectionX.get(i)-_xMin)*_xScaler+_gridPosX-_magPosX,(_solvedCurveCollectionY.get(j).get(i)-_yMin)*_yScaler+_gridPosY-_magPosY)); 
		}

	}



	if(_solvedCurveContributionCollectionY!=null)
		if(_ifCalcContributions)
			for(int m=0; m<_solvedCurveContributionCollectionY.size(); m++)
			{
				int j=_globalFitPosOfSelectedKin;
				inc=1;
				if(m<10)
					g2d.setPaint(ColorCollection._colorCollection[m]);
				g2d.setStroke(new BasicStroke(1.0f));
				
				for(int i=2;i<(_solvedCurveCollectionX.size()); i=i+inc)
				{
					g2d.draw(new Line2D.Float((_solvedCurveCollectionX.get(i-1)-_xMin)*_xScaler+_gridPosX-_magPosX,(_solvedCurveContributionCollectionY.get(m).get(j).get(i-1)-_yMin)*_yScaler+_gridPosY-_magPosY,
												(_solvedCurveCollectionX.get(i)-_xMin)*_xScaler+_gridPosX-_magPosX,(_solvedCurveContributionCollectionY.get(m).get(j).get(i)-_yMin)*_yScaler+_gridPosY-_magPosY)); 
				}
			}
	
	
	
	
	
	
	
	
 
 	g2d.setPaint(Color.RED);
	g2d.draw(new Line2D.Float(_zeroPosReal-_magPosX,_gridPosY, _zeroPosReal-_magPosX, _gridPosY + _gridSizeY));
	if(_ifCalcCurveShifts)
	{
		g2d.setPaint(Color.green);
		g2d.draw(new Line2D.Float(_beforeZeroPosReal-_magPosX,_gridPosY, _beforeZeroPosReal-_magPosX, _gridPosY + _gridSizeY));
	}
	else
	{
		g2d.draw(new Line2D.Float(_gridPosX, _levelPosReal-_magPosY, _gridPosX + _gridSizeX, _levelPosReal-_magPosY));
	}
	
	if(_ifShowSlice)
	{
		g2d.setStroke(new BasicStroke(1.5f));
		g2d.setPaint(Color.blue);
		g2d.draw(new Line2D.Float(_slicePosReal-_magPosX,_gridPosY, _slicePosReal-_magPosX, _gridPosY + _gridSizeY));

	}


}
	


public void drawMarkers(Graphics2D g2d)
{
 


 // Draw Fit Markers
	
	float x, y;
	float coef = 1;
	
	//(_kinCollectionX.get(j).get(i-inc)-_xMin)*_xScaler+_gridPosX-_magPosX,
	//(_kinCollectionY.get(j).get(i-inc)-_yMin)*_yScaler+_gridPosY-_magPosY,
	
	x = (_kinCollectionX.get(_globalFitPosOfSelectedKin).get(_markersCollection.get(_globalFitPosOfSelectedKin).get(0))-_xMin)*_xScaler+_gridPosX-_magPosX;
	y = (_kinCollectionY.get(_globalFitPosOfSelectedKin).get(_markersCollection.get(_globalFitPosOfSelectedKin).get(0))-_yMin)*_yScaler+_gridPosY-_magPosY;
	
	g2d.setPaint(new Color(150,0,0));
	g2d.setStroke(new BasicStroke(1.0f)); 
	
	g2d.draw(new Line2D.Float(x, y - 12*coef, x, y + 12*coef));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x - 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x + 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x - 3, y - 12*coef+7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x + 3, y - 12*coef+7));			
	
	x = (_kinCollectionX.get(_globalFitPosOfSelectedKin).get(_markersCollection.get(_globalFitPosOfSelectedKin).get(1))-_xMin)*_xScaler+_gridPosX-_magPosX;
	y = (_kinCollectionY.get(_globalFitPosOfSelectedKin).get(_markersCollection.get(_globalFitPosOfSelectedKin).get(1))-_yMin)*_yScaler+_gridPosY-_magPosY;

	
	g2d.setPaint(new Color(0, 150, 0));
	g2d.setStroke(new BasicStroke(1.0f)); 
	
	g2d.draw(new Line2D.Float(x, y - 12*coef, x, y + 12*coef));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x - 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x + 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x - 3, y - 12*coef+7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x + 3, y - 12*coef+7));			




}
	




public final void set_ifSolvedCurveCollectionLocked(boolean _ifSolvedCurveCollectionLocked)
{
	this._ifSolvedCurveCollectionLocked = _ifSolvedCurveCollectionLocked;
}

public final boolean get_ifSolvedCurveCollectionLocked()
{
	return _ifSolvedCurveCollectionLocked;
}
	


public final void set_ifDone(boolean _ifDone)
{
	this._ifDone = _ifDone;
}

public final boolean get_ifDone()
{
	return _ifDone;
}


public final void set_ifShowSlice(boolean _ifShowSlice)
{
	this._ifShowSlice = _ifShowSlice;
}





public final void set_gridSizeX (int _gridSizeX)
{
	this._gridSizeX = _gridSizeX;
}

public final void set_gridSizeY (int _gridSizeY)
{
	this._gridSizeY = _gridSizeY;
}





public final int get_gridSizeX ()
{
	return _gridSizeX;
}

public final int get_gridSizeY ()
{
	return _gridSizeY;
}






public final void set_xScaler (float _xScaler)
{
	this._xScaler = _xScaler;
}

public final void set_yScaler (float _yScaler)
{
	this._yScaler = _yScaler;
}

public final void set_xScalerIni (float _xScalerIni)
{
	this._xScalerIni = _xScalerIni;
}

public final void set_yScalerIni (float _yScalerIni)
{
	this._yScalerIni = _yScalerIni;
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

public final void set_arraySize (int _arraySize)
{
	this._arraySize = _arraySize;
}

public final void set_nOfCreatedCurvePoints (int _nOfCreatedCurvePoints)
{
	this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
}

public final void set_zeroPosReal (float _zeroPosReal)
{
	this._zeroPosReal = _zeroPosReal;
}

public final void set_beforeZeroPosReal (float _beforeZeroPosReal)
{
	this._beforeZeroPosReal = _beforeZeroPosReal;
}

public final void set_slicePosReal (float _slicePosReal)
{
	this._slicePosReal = _slicePosReal;
}


public final void set_levelPosReal (float _levelPosReal)
{
	this._levelPosReal = _levelPosReal;
}

public final void set_enableLowDetail (boolean _enableLowDetail)
{
	this._enableLowDetail = _enableLowDetail;
}


public final void set_ifShowCreatedCurve (boolean _ifShowCreatedCurve)
{
	this._ifShowCreatedCurve = _ifShowCreatedCurve;
}


public final void set_enableAABox (boolean _enableAABox)
{
	this._enableAABox = _enableAABox;
}

public final void set_createdCurveArray (ArrayList<float[]> _createdCurveArray)
{
	this._createdCurveArray = _createdCurveArray;
}

public final void set_xArray (float[] _xArray)
{
	this._xArray = _xArray;
}

public final void set_yArray (float[] _yArray)
{
	this._yArray = _yArray;
}

public final void set_mouseLabelText(String text)
{
	_mouseLabel.setText(text);
}



public final void set_fitMarkerFirstRealX (float _fitMarkerFirstRealX)
{
	this._fitMarkerFirstRealX = _fitMarkerFirstRealX;
}

public final void set_fitMarkerFirstRealY (float _fitMarkerFirstRealY)
{
	this._fitMarkerFirstRealY = _fitMarkerFirstRealY;
}

public final void set_fitMarkerLastRealX (float _fitMarkerLastRealX)
{
	this._fitMarkerLastRealX = _fitMarkerLastRealX;
}

public final void set_fitMarkerLastRealY (float _fitMarkerLastRealY)
{
	this._fitMarkerLastRealY = _fitMarkerLastRealY;
}





public final void set_myRect(Rectangle2D.Float myRect)
{
	this.myRect = myRect;
}

public final void set_myBeforeZeroRect(Rectangle2D.Float myBeforeZeroRect)
{
	this.myBeforeZeroRect = myBeforeZeroRect;
}

public final void set_sliceRect(Rectangle2D.Float sliceRect)
{
	this.sliceRect = sliceRect;
}

public final void set_myZeroRect(Rectangle2D.Float myZeroRect)
{
	this.myZeroRect = myZeroRect;
}

public final void set_selectedRect(Rectangle2D.Float selectedRect)
{
	this.selectedRect = selectedRect;
}

public final void set_fitMarkerFirstRect(Rectangle2D.Float fitMarkerFirstRect)
{
	this.fitMarkerFirstRect = fitMarkerFirstRect;
}


public final void set_fitMarkerLastRect(Rectangle2D.Float fitMarkerLastRect)
{
	this.fitMarkerLastRect = fitMarkerLastRect;
}






public final Rectangle2D.Float get_myRect()
{
	return myRect;
}

public final Rectangle2D.Float get_myZeroRect()
{
	return myZeroRect;
}

public final Rectangle2D.Float get_myBeforeZeroRect()
{
	return myBeforeZeroRect;
}

public final Rectangle2D.Float get_sliceRect()
{
	return sliceRect;
}

public final Rectangle2D.Float get_selectedRect()
{
	return selectedRect;
}

public final void set_beforeZeroSelRect(Rectangle2D.Float beforeZeroSelRect)
{
	this.beforeZeroSelRect=beforeZeroSelRect;
}


public final Rectangle2D.Float get_fitMarkerFirstRect()
{
	return fitMarkerFirstRect;
}

public final Rectangle2D.Float get_fitMarkerLastRect()
{
	return fitMarkerLastRect;
}

public final void set_fittingColor(Color _fittingColor)
{
	this._fittingColor = _fittingColor;
}

public final Color get_fittingColor()
{
	return _fittingColor;
}










public final void set_offSetX(float _offSetX)
{
	this._offSetX = _offSetX;
}

public final float get_offSetX()
{
	return _offSetX;
}


public final void set_offSetY(float _offSetY)
{
	this._offSetY = _offSetY;
}

public final float get_offSetY()
{
	return _offSetY;
}








public final void set_globalFitPosOfSelectedKin(int _globalFitPosOfSelectedKin)
{
	this._globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin;
}

public final int get_globalFitPosOfSelectedKin()
{
	return _globalFitPosOfSelectedKin;
}


public final void set_ifCalcCurveShifts(boolean _ifCalcCurveShifts)
{
	this._ifCalcCurveShifts = _ifCalcCurveShifts;
}

public final void set_kinCollectionX(ArrayList<ArrayList<Float>> _kinCollectionX)
{
	this._kinCollectionX = _kinCollectionX;
}

public final ArrayList<ArrayList<Float>> get_kinCollectionX()
{
	return _kinCollectionX;
}

public final void set_kinCollectionY(ArrayList<ArrayList<Float>> _kinCollectionY)
{
	this._kinCollectionY = _kinCollectionY;
}

public final ArrayList<ArrayList<Float>> get_kinCollectionY()
{
	return _kinCollectionY;
}



public final void set_solvedCurveCollectionX(ArrayList<Float> _solvedCurveCollectionX)
{
	this._solvedCurveCollectionX = _solvedCurveCollectionX;
}

public final ArrayList<Float> get_solvedCurveCollectionX()
{
	return _solvedCurveCollectionX;
}

public final void set_solvedCurveCollectionY(ArrayList<ArrayList<Float>> _solvedCurveCollectionY)
{
	this._solvedCurveCollectionY = _solvedCurveCollectionY;
}

public final ArrayList<ArrayList<Float>> get_solvedCurveCollectionY()
{
	return _solvedCurveCollectionY;
}




public final boolean get_ifEnableCurveBounds()
{
	return _ifEnableCurveBounds;
}


public final void set_ifEnableCurveBounds(boolean _ifEnableCurveBounds)
{
	this._ifEnableCurveBounds=_ifEnableCurveBounds;
}



public final void set_markersCollection(ArrayList<ArrayList<Integer>> _markersCollection)
{
	this._markersCollection = _markersCollection;
}

public final ArrayList<ArrayList<Integer>> get_markersCollection()
{
	return _markersCollection;
}





public final void set_syncObject(Object _syncObject)
{
	this._syncObject = _syncObject;
}

public final Object get_syncObject()
{
	return _syncObject;
}

public final void set_ifLockGraphPanels(boolean _ifLockGraphPanels)
{
	this._ifLockGraphPanels = _ifLockGraphPanels;
}

public final boolean get_ifLockGraphPanels()
{
	return _ifLockGraphPanels;
}



public final void set_solvedCurveContributionCollectionY(ArrayList<ArrayList<ArrayList<Float>>> _solvedCurveContributionCollectionY)
{
	this._solvedCurveContributionCollectionY = _solvedCurveContributionCollectionY;
}

public final ArrayList<ArrayList<ArrayList<Float>>> get_solvedCurveContributionCollectionY()
{
	return _solvedCurveContributionCollectionY;
}

public final boolean get_ifCalcContributions()
{
	return _ifCalcContributions;
}


public final void set_ifCalcContributions(boolean _ifCalcContributions)
{
	this._ifCalcContributions=_ifCalcContributions;
}


public final boolean get_ifShowBackgroundCurves()
{
	return _ifShowBackgroundCurves;
}


public final void set_ifShowBackgroundCurves(boolean _ifShowBackgroundCurves)
{
	this._ifShowBackgroundCurves=_ifShowBackgroundCurves;
}




}
