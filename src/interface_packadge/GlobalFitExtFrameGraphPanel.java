package interface_packadge;


import math_packadge.*;

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

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GlobalFitExtFrameGraphPanel extends JPanel{
	
	
	public JLabel _mouseLabel;
	public boolean _enableAABox = true;
	public boolean _enableLowDetail = false;
	public boolean _ifShowCreatedCurve = false;
	public boolean _ifShowAverageValue = false;
	public boolean _ifSolvedCurveCollectionLocked = false;
	public boolean _ifDone = false;
	public boolean _ifShowErrorExt = false;
	public boolean _ifShowErrorExtWithBars = true;
	public int _gridSizeX=Constants._extGraphPanelGridSizeX, _gridSizeY=Constants._extGraphPanelGridSizeY;
	public int _nCellsXIni=10, _nCellsYIni=10,_nCellsX=20, _nCellsY=20;
	public float _magX1=0,_magY1=0, _magX2=Constants._kineticsModeGridPosition.x+_gridSizeX, _magY2=Constants._kineticsModeGridPosition.x+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0;
	public float _cellSizeX = (_gridSizeX/_nCellsX);
	public float _cellSizeY = (_gridSizeY/_nCellsY);
	public float _levelPosReal = 0f, _levelPosDisplay=0, _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public float _xMax=500,_xMin=0,_yMax=0,_yMin=-500,_xScaler=0f,_yScaler=0f, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	public float _zeroPosReal = 0, _zeroPosDisplay=0;
	public float _beforeZeroPosReal = 0, _beforeZeroPosDisplay=0;
	public int _arraySize=0;
	public int _globalFitPosOfSelectedKin=0;
	public int _nOfCreatedCurvePoints;
	public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	public float _mouseX, _mouseY;
	public float _graphAverage = 0;
	public int _globalFitPosOfSelectedSpec=1; 
	public float _globalFitXMax=-10, _globalFitXMin=10, _globalFitYMax=-10, _globalFitYMin=10;
	
	public float[] _xArray;
	public float[] _yArray;
	public ArrayList<float[]> _createdCurveArray;
	public ArrayList<Float> _xSAbsSpecCollectionX;
	public ArrayList<ArrayList<Float>> _xSAbsSpecCollectionY;
	public ArrayList<ArrayList<Float>> _xSAbsSpecErrorCollection;
	
	  public Rectangle2D.Float myRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x, _levelPosReal, _gridSizeX, 10);
	  public Rectangle2D.Float myZeroRect = new Rectangle2D.Float(_zeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	  public Rectangle2D.Float myBeforeZeroRect = new Rectangle2D.Float(_beforeZeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	  public Rectangle2D.Float selectedRect = new Rectangle2D.Float(0,0,0,0);
	  public Rectangle2D.Float beforeZeroSelRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x,Constants._kineticsModeGridPosition.y,10,_gridSizeY);
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
	    
	
	
	
public	GlobalFitExtFrameGraphPanel()

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
	    drawRect(g2d);
		drawGrid(g2d);
		drawErrors(g2d);
		drawSpec(g2d);

		//drawMarkers(g2d);
	}
	catch(IndexOutOfBoundsException e)
	{
		System.out.println("Global fit ext graph Panel: Index out of bounds exception!");
	
	}
	catch(NullPointerException e)
	{
		System.out.println("Global fit ext graph Panel: Null pointer exception!");
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
	g2d.setStroke(_tinyDashedStroke);
	g2d.setPaint(Color.DARK_GRAY);		
	g2d.draw(selectedRect);
	
    //g2d.fill(myRect);

	
}


public void drawGrid(Graphics2D g2d)
{
	String s;
	
	// Draw grid
	g2d.setStroke(_dashedStroke);
	g2d.setPaint(Color.black);		
	g2d.draw(new Rectangle(Constants._kineticsModeGridPosition.x, Constants._kineticsModeGridPosition.y,_gridSizeX, _gridSizeY));

	
	_gridResolX = _gridResolXIni*calcGoodResol(_xScaler/_xScalerIni);
	_gridResolY = _gridResolYIni*calcGoodResol(_yScaler/_yScalerIni);

	_cellSizeX = calcIntX(_gridResolX)*_xScaler;
	_cellSizeY = calcIntY(_gridResolY)*_yScaler;
	_nCellsX = Math.round(_gridSizeX/_cellSizeX)+1;
	_nCellsY = Math.round(_gridSizeY/_cellSizeY)+1;
	
	_xZeroPos = setZero(0,Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
	_yZeroPos = setLevel(0,Constants._kineticsModeGridPosition.y,_yMin,_yScaler);

	int _cellShiftX = Math.round((_magPosX-_xZeroPos)/_cellSizeX);
	int _cellShiftY = Math.round((_magPosY-_yZeroPos)/_cellSizeY);
	
	if(_cellSizeX!=0)
	{
		
//Drawing lines			

		for(int i=0; i<_nCellsX; i++)
			if((((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>Constants._kineticsModeGridPosition.x)&(((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(Constants._kineticsModeGridPosition.x+_gridSizeX)))
				g2d.draw(new Line2D.Float((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX,Constants._kineticsModeGridPosition.y,(i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, Constants._kineticsModeGridPosition.y+_gridSizeY));	   

		
		for(int i=0; i<_nCellsY; i++)
			if((((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>(Constants._kineticsModeGridPosition.y))&(((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(Constants._kineticsModeGridPosition.y+_gridSizeY)))
				g2d.draw(new Line2D.Float(Constants._kineticsModeGridPosition.x,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY, Constants._kineticsModeGridPosition.x+_gridSizeX,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY));	   
		
		
//Drawing numbers
		
		
		for(int i=0; (i<_nCellsX+1); i++)
			if((((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>Constants._kineticsModeGridPosition.x)&(((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(Constants._kineticsModeGridPosition.x+_gridSizeX)))
			{
				float temp= 0 + calcIntX(_gridResolX)*(i-1+_cellShiftX);
	    		s = ""+temp;
	    		if((s.length()-s.indexOf('.'))>=3)
	    		{s = s.substring(0,s.indexOf('.')+3);}
	    		else
	    			if((s.length()-s.indexOf('.'))>=2)
	    				s = s.substring(0,s.indexOf('.')+2);
	    		g2d.drawString(s, (i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, Constants._kineticsModeGridPosition.y + _gridSizeY + 20);
				
			}
		
		
		for(int i=0; (i<_nCellsY+2); i++)
			if((((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>(Constants._kineticsModeGridPosition.y))&(((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(Constants._kineticsModeGridPosition.y+_gridSizeY+10)))
			{
				float temp= 0 - calcIntY(_gridResolY)*(i-1+_cellShiftY);
	    		s = ""+temp;
	    		//if((s.length()-s.indexOf('.'))>=3)
	    		//{s = s.substring(0,s.indexOf('.')+3);}
	    		//else
	    		//	if((s.length()-s.indexOf('.'))>=2)
	    		//		s = s.substring(0,s.indexOf('.')+2);
				s = MethodsCollection.cutStringAfterPoint(s, 5);
	    		g2d.drawString(s, Constants._kineticsModeGridPosition.x - 10- s.length()*5, (i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY);
				
			}
		
		
		
		
	}

	
}


public void drawSpec(Graphics2D g2d)
{

		//int inc=1;
		
		//if(!_enableLowDetail){inc=1;}
		//g2d.setStroke(new BasicStroke(1.0f)); 


		for(int j=0; j<_xSAbsSpecCollectionY.size(); j++)
		{
			//inc = Math.round(_xSAbsSpecCollectionX.size()/3000);
			//if(inc==0)inc=1;			
			
			if(j==_globalFitPosOfSelectedSpec)
			{
				g2d.setStroke(new BasicStroke(3.0f)); 
				g2d.setPaint(new Color(255,0,0));
			}
			else
			{
				g2d.setStroke(new BasicStroke(1.0f)); 
				g2d.setPaint(new Color(20,20,50));
			}
			
			
	//drawing spectra
			if(_xSAbsSpecCollectionX.size()>1)
			{
				for(int i=0;i<(_xSAbsSpecCollectionX.size()-1); i++)
				{
					
					g2d.draw(new Line2D.Float((_xSAbsSpecCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX,
												(_xSAbsSpecCollectionY.get(j).get(i)-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY,
												(_xSAbsSpecCollectionX.get(i+1)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX,
												(_xSAbsSpecCollectionY.get(j).get(i+1)-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY)); 
				
				}

			}
	//drawing circles

			if(_xSAbsSpecCollectionX.size()>0)
			{
				for(int i=0;i<(_xSAbsSpecCollectionX.size()); i++)
				{
					//if(i!=_globalFitPosOfSelectedKin)
					g2d.draw(new Ellipse2D.Float((_xSAbsSpecCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX-4,
												(_xSAbsSpecCollectionY.get(j).get(i)-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY-4, 8f, 8f));
				}
			}

			
		}
		
		
		
		if(_xSAbsSpecCollectionX.size()>0)
		{

			g2d.setStroke(new BasicStroke(2.0f)); 
			g2d.setPaint(new Color(255,0,0));
			
			g2d.draw(new Ellipse2D.Float((_xSAbsSpecCollectionX.get(_globalFitPosOfSelectedKin)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX-8,
											(_xSAbsSpecCollectionY.get(_globalFitPosOfSelectedSpec).get(_globalFitPosOfSelectedKin)-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY-8, 16f, 16f));
			
		
		}
		
	 
	 	//g2d.setPaint(Color.RED);
		//g2d.draw(new Line2D.Float(Constants._kineticsModeGridPosition.x, _levelPosReal-_magPosY, Constants._kineticsModeGridPosition.x + _gridSizeX, _levelPosReal-_magPosY));
		//g2d.draw(new Line2D.Float(_zeroPosReal-_magPosX,Constants._kineticsModeGridPosition.y, _zeroPosReal-_magPosX, Constants._kineticsModeGridPosition.y + _gridSizeY));



}
	
	

public void drawErrors(Graphics2D g2d)
{
	if(_xSAbsSpecCollectionX.size()>0)
	{
		if(this._ifShowErrorExt)
		{
			if(this._ifShowErrorExtWithBars)
			{	
				drawBars(g2d);
			}
			else
			{
				if(_xSAbsSpecCollectionX.size()>1)
					drawArea(g2d);
			}
		}
	}
}



public void drawBars(Graphics2D g2d)
{
	g2d.setStroke(new BasicStroke(1.0f)); 
	g2d.setPaint(new Color(200,0,0));
	float error, x1, y1, y2;
	
	for(int i=0;i<(_xSAbsSpecCollectionX.size()); i++)
	{
		error = _xSAbsSpecErrorCollection.get(_globalFitPosOfSelectedSpec).get(i);
		x1 = (_xSAbsSpecCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX;
		y1 = (_xSAbsSpecCollectionY.get(_globalFitPosOfSelectedSpec).get(i)+error-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY;
		y2 = (_xSAbsSpecCollectionY.get(_globalFitPosOfSelectedSpec).get(i)-error-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY;
		
		
		g2d.draw(new Line2D.Float(x1, y1, x1, y2));
		g2d.draw(new Line2D.Float(x1-5, y1, x1+5, y1));
		g2d.draw(new Line2D.Float(x1-5, y2, x1+5, y2));
	}
}


public void drawArea(Graphics2D g2d)
{
    float x, y, error;
	Polygon p = new Polygon();
   	for(int i=0;i<(_xSAbsSpecCollectionX.size()); i++)
   	{
   		error = _xSAbsSpecErrorCollection.get(_globalFitPosOfSelectedSpec).get(i);
   		x = (_xSAbsSpecCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX;
  		y = (_xSAbsSpecCollectionY.get(_globalFitPosOfSelectedSpec).get(i)+error-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY;
   		p.addPoint(Math.round(x),Math.round(y));
   	}
    	
   	for(int i=_xSAbsSpecCollectionX.size()-1;i>-1; i--)
   	{
   		error = _xSAbsSpecErrorCollection.get(_globalFitPosOfSelectedSpec).get(i);
   		x = (_xSAbsSpecCollectionX.get(i)-_xMin)*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX;
  		y = (_xSAbsSpecCollectionY.get(_globalFitPosOfSelectedSpec).get(i)-error-_yMin)*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY;
   		p.addPoint(Math.round(x),Math.round(y));
   	}
   	
	g2d.setStroke(new BasicStroke(1.0f));
	g2d.setPaint(new Color(50, 223, 248));
    //g2d.drawPolygon(p);
    g2d.fill(p);
}












public void drawMarkers(Graphics2D g2d)
{
 


 // Draw Fit Markers
	
	float x, y;
	float coef = 1;
	
	x = _fitMarkerFirstRealX-_magPosX;
	y = _fitMarkerFirstRealY-_magPosY;
	
	g2d.setPaint(new Color(150,0,0));
	g2d.setStroke(new BasicStroke(1.0f)); 
	
	g2d.draw(new Line2D.Float(x, y - 12*coef, x, y + 12*coef));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x - 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x + 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x - 3, y - 12*coef+7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x + 3, y - 12*coef+7));			
	
	x = _fitMarkerLastRealX-_magPosX;
	y = _fitMarkerLastRealY-_magPosY;
	
	g2d.setPaint(new Color(0, 150, 0));
	g2d.setStroke(new BasicStroke(1.0f)); 
	
	g2d.draw(new Line2D.Float(x, y - 12*coef, x, y + 12*coef));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x - 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y + 12*coef, x + 3, y + 12*coef-7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x - 3, y - 12*coef+7));			
	g2d.draw(new Line2D.Float(x, y - 12*coef, x + 3, y - 12*coef+7));			




}
	

public float calcGoodResol(float resol)
{
	float a;
	a = resol;
	if((a>0)&(a<=1.5))a=1;
	if((a>1.5)&(a<=3))a=2;
	if((a>3)&(a<=7))a=5;
	if((a>7)&(a<=15))a=10;
	if((a>15)&(a<=30))a=20;
	if((a>30)&(a<=70))a=50;
	if(a>70)a=100;
	
return 1/a;
}




public float calcIntX(float resol)
{
	float n2 = 0;
	if ((_xMax-_xMin)>=(0.001)){n2 = 0.0002f*resol;}
	if ((_xMax-_xMin)>=(0.002)){n2 = 0.0005f*resol;}
	if ((_xMax-_xMin)>=(0.005)){n2 = 0.001f*resol;}
	if ((_xMax-_xMin)>=(0.01)){n2 = 0.002f*resol;}
	if ((_xMax-_xMin)>=(0.02)){n2 = 0.005f*resol;}
	if ((_xMax-_xMin)>=(0.05)){n2 = 0.010f*resol;}
	if ((_xMax-_xMin)>=(0.1)){n2 = 0.02f*resol;}
	if ((_xMax-_xMin)>=(0.2)){n2 = 0.05f*resol;}
	if ((_xMax-_xMin)>=(0.5)){n2 = 0.1f*resol;}
	if ((_xMax-_xMin)>=(1)){n2 = 0.2f*resol;}
	if ((_xMax-_xMin)>=(2)){n2 = 0.5f*resol;}
	if ((_xMax-_xMin)>=(5)){n2 = 1*resol;}
	if ((_xMax-_xMin)>=(10)){n2 = 2*resol;}
	if ((_xMax-_xMin)>=(20)){n2 = 5*resol;}
	if ((_xMax-_xMin)>=(50)){n2 = 10*resol;}
	if ((_xMax-_xMin)>=(100)){n2 = 20*resol;}
	if ((_xMax-_xMin)>=(200)){n2 = 50*resol;}
	if ((_xMax-_xMin)>=(500)){n2 = 100*resol;}
	if ((_xMax-_xMin)>=(1000)){n2 = 200*resol;}
	if ((_xMax-_xMin)>=(2000)){n2 = 500*resol;}
	if ((_xMax-_xMin)>=(5000)){n2 = 1000*resol;}
	if ((_xMax-_xMin)>=(10000)){n2 = 2000*resol;}
	if ((_xMax-_xMin)>=(20000)){n2 = 5000*resol;}
	if ((_xMax-_xMin)>=(50000)){n2 = 10000*resol;}
	if ((_xMax-_xMin)>=(100000)){n2 = 20000*resol;}
	if ((_xMax-_xMin)>=(200000)){n2 = 50000*resol;}
	if ((_xMax-_xMin)>=(500000)){n2 = 100000*resol;}
	if ((_xMax-_xMin)>=(5000000)){n2 = 1000000*resol;}
	if ((_xMax-_xMin)>=(50000000)){n2 = 10000000*resol;}
	if ((_xMax-_xMin)>=(500000000)){n2 = 100000000*resol;}
	
	return n2;
}

public float calcIntY(float resol)
{
	float n2 = 0;
	if ((_yMax-_yMin)>=(0.001)){n2 = 0.0002f*resol;}
	if ((_yMax-_yMin)>=(0.002)){n2 = 0.0005f*resol;}
	if ((_yMax-_yMin)>=(0.005)){n2 = 0.001f*resol;}
	if ((_yMax-_yMin)>=(0.01)){n2 = 0.002f*resol;}
	if ((_yMax-_yMin)>=(0.02)){n2 = 0.005f*resol;}
	if ((_yMax-_yMin)>=(0.05)){n2 = 0.010f*resol;}
	if ((_yMax-_yMin)>=(0.1)){n2 = 0.02f*resol;}
	if ((_yMax-_yMin)>=(0.2)){n2 = 0.05f*resol;}
	if ((_yMax-_yMin)>=(0.5)){n2 = 0.1f*resol;}
	if ((_yMax-_yMin)>=(1)){n2 = 0.2f*resol;}
	if ((_yMax-_yMin)>=(2)){n2 = 0.5f*resol;}
	if ((_yMax-_yMin)>=(5)){n2 = 1f*resol;}
	if ((_yMax-_yMin)>=(10)){n2 = 2f*resol;}
	if ((_yMax-_yMin)>=(20)){n2 = 5f*resol;}
	if ((_yMax-_yMin)>=(50)){n2 = 10f*resol;}
	if ((_yMax-_yMin)>=(100)){n2 = 20f*resol;}
	if ((_yMax-_yMin)>=(200)){n2 = 50f*resol;}
	if ((_yMax-_yMin)>=(500)){n2 = 100f*resol;}
	if ((_yMax-_yMin)>=(1000)){n2 = 200f*resol;}
	if ((_yMax-_yMin)>=(2000)){n2 = 500f*resol;}
	if ((_yMax-_yMin)>=(5000)){n2 = 1000f*resol;}
	if ((_yMax-_yMin)>=(10000)){n2 = 2000f*resol;}
	if ((_yMax-_yMin)>=(20000)){n2 = 5000f*resol;}
	if ((_yMax-_yMin)>=(50000)){n2 = 10000f*resol;}
	if ((_yMax-_yMin)>=(100000)){n2 = 20000f*resol;}
	if ((_yMax-_yMin)>=(200000)){n2 = 50000f*resol;}
	if ((_yMax-_yMin)>=(500000)){n2 = 100000f*resol;}
	if ((_yMax-_yMin)>=(5000000)){n2 = 1000000f*resol;}
	if ((_yMax-_yMin)>=(50000000)){n2 = 10000000f*resol;}
	if ((_yMax-_yMin)>=(500000000)){n2 = 100000000f*resol;}

	
	return n2;
}

public float setLevel(float _levelPosDisplay, int _gridPosY, float _yMin, float _yScaler)
{
	return _gridPosY - (_yMin+_levelPosDisplay)*_yScaler;
}




public float setZero(float _zeroPosDisplay, int _gridPosX, float _xMin, float _xScaler)
{
	return _gridPosX - (_xMin-_zeroPosDisplay)*_xScaler;
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

public final Rectangle2D.Float get_selectedRect()
{
	return selectedRect;
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

/*
public final void set_globalFitXMin(float _globalFitXMin)
{
	this._globalFitXMin = _globalFitXMin;
}

public final float get_globalFitXMin()
{
	return _globalFitXMin;
}





public final void set_globalFitXMax(float _globalFitXMax)
{
	this._globalFitXMax = _globalFitXMax;
}

public final float get_globalFitXMax()
{
	return _globalFitXMax;
}



public final void set_globalFitYMin(float _globalFitYMin)
{
	this._globalFitYMin = _globalFitYMin;
}

public final float get_globalFitYMin()
{
	return _globalFitYMin;
}


public final void set_globalFitYMax(float _globalFitYMax)
{
	this._globalFitYMax = _globalFitYMax;
}

public final float get_globalFitYMax()
{
	return _globalFitYMax;
}
*/

public final void set_globalFitPosOfSelectedSpec(int _globalFitPosOfSelectedSpec)
{
	this._globalFitPosOfSelectedSpec = _globalFitPosOfSelectedSpec;
}

public final int get_globalFitPosOfSelectedSpec()
{
	return _globalFitPosOfSelectedSpec;
}

public final void set_xSAbsSpecCollectionX(ArrayList<Float> _xSAbsSpecCollectionX)
{
	this._xSAbsSpecCollectionX = _xSAbsSpecCollectionX;
}

public final ArrayList<Float> get_xSAbsSpecCollectionX()
{
	return _xSAbsSpecCollectionX;
}

public final void set_xSAbsSpecCollectionY(ArrayList<ArrayList<Float>> _xSAbsSpecCollectionY)
{
	this._xSAbsSpecCollectionY = _xSAbsSpecCollectionY;
}

public final ArrayList<ArrayList<Float>> get_xSAbsSpecCollectionY()
{
	return _xSAbsSpecCollectionY;
}


public final void set_globalFitPosOfSelectedKin(int _globalFitPosOfSelectedKin)
{
	this._globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin;
}

public final int get_globalFitPosOfSelectedKin()
{
	return _globalFitPosOfSelectedKin;
}

public final void set_ifDone(boolean _ifDone)
{
	this._ifDone = _ifDone;
}

public final boolean get_ifDone()
{
	return _ifDone;
}

public final boolean get_ifShowErrorExtWithBars()
{
	return _ifShowErrorExtWithBars;
}


public final void set_ifShowErrorExtWithBars(boolean _ifShowErrorExtWithBars)
{
	this._ifShowErrorExtWithBars=_ifShowErrorExtWithBars;
}


public final boolean get_ifShowErrorExt()
{
	return _ifShowErrorExt;
}


public final void set_ifShowErrorExt(boolean _ifShowErrorExt)
{
	this._ifShowErrorExt=_ifShowErrorExt;
}




public final void set_xSAbsSpecErrorCollection(ArrayList<ArrayList<Float>> _xSAbsSpecErrorCollection)
{
	this._xSAbsSpecErrorCollection = _xSAbsSpecErrorCollection;
}

public final ArrayList<ArrayList<Float>> get_xSAbsSpecErrorCollection()
{
	return _xSAbsSpecErrorCollection;
}




}
