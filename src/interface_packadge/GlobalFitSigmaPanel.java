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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GlobalFitSigmaPanel extends JPanel{
	
	
	public JLabel _mouseLabel;
	public boolean _enableAABox = true;
	//public boolean _enableLowDetail = false;
	//public boolean _ifShowCreatedCurve = false;
	//public boolean _ifShowAverageValue = false;
	//public boolean _ifCalcCurveShifts = false;
	//public boolean _ifEnableCurveBounds = false;
	public int _gridSizeX =Constants._sigmaPanelGridSizeX,  _gridSizeY=Constants._sigmaPanelGridSizeY;
	public int _nCellsXIni=10, _nCellsYIni=10,_nCellsX=20, _nCellsY=20;
	public float _magX1=0,_magY1=0, _magX2=Constants._kineticsModeGridPosition.x+_gridSizeX, _magY2=Constants._kineticsModeGridPosition.x+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0;
	public float _cellSizeX = (_gridSizeX/_nCellsX);
	public float _cellSizeY = (_gridSizeY/_nCellsY);
	public float _levelPosReal = 200f, _levelPosDisplay=0, _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public float _xMax=1000,_xMin=-200,_yMax=0,_yMin=-500,_xScaler=1f,_yScaler=1f, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	public float _zeroPosReal = 200, _zeroPosDisplay=0;
	public float _beforeZeroPosReal = 0, _beforeZeroPosDisplay=0;
	public int _arraySize=0;
	public int _nOfCreatedCurvePoints;
	public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	public float _mouseX, _mouseY;
	public float _graphAverage = 0;
	public int _globalFitPosOfSelectedKin=0;
	//public float _globalFitXMax=-10, _globalFitXMin=10, _globalFitYMax=-10, _globalFitYMin=10;
	

	public ArrayList<Float> _sigmaCollection = new ArrayList<Float>();
	public ArrayList<Float> _availWaveCollection = new ArrayList<Float>();

	
	
	
	
	
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
	    
	
	
	
public	GlobalFitSigmaPanel()

{
	createGraphPanelInterface();
	
	
	
}
	
public void paint(Graphics g)
{
super.paint(g);
 Graphics2D g2d = (Graphics2D) g;

    if(_enableAABox)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    
	drawGrid(g2d);
	drawSigma(g2d);


	
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



/*public void drawRect(Graphics2D g2d)
{

	
	if(_ifCalcCurveShifts)
	{
		g2d.setPaint(Color.LIGHT_GRAY);
		//beforZeroSelRectangle = new
	    g2d.fill(new Rectangle2D.Float(Constants._kineticsModeGridPosition.x, Constants._kineticsModeGridPosition.y, _beforeZeroPosReal-_magPosX-Constants._kineticsModeGridPosition.x,_gridSizeY));
	}
	
	g2d.setStroke(_tinyDashedStroke);
	g2d.setPaint(new Color(10, 10, 10));		
	g2d.draw(selectedRect);


}*/


public void drawGrid(Graphics2D g2d)
{
	String s;
	
	// Draw grid
	g2d.setStroke(_dashedStroke);
	g2d.setPaint(Color.black);		
	g2d.draw(new Rectangle(Constants._sigmaPanelGridPosition.x, Constants._sigmaPanelGridPosition.y,_gridSizeX, _gridSizeY));

	
	_yScalerIni = _yScaler;
	_xScalerIni = _xScaler;
	
	_gridResolX = _gridResolXIni*calcGoodResol(_xScaler/_xScalerIni);
	_gridResolY = _gridResolYIni*calcGoodResol(_yScaler/_yScalerIni);

	_cellSizeX = calcIntX(_gridResolX)*_xScaler;//*_gridSizeY/_gridSizeX;
	_cellSizeY = calcIntY(_gridResolY)*_yScaler;
	_nCellsX = Math.round(_gridSizeX/_cellSizeX)+1;
	_nCellsY = Math.round(_gridSizeY/_cellSizeY)+1;
	
	_xZeroPos = setZero(0,Constants._sigmaPanelGridPosition.x,_xMin,_xScaler);
	_yZeroPos = setLevel(0,Constants._sigmaPanelGridPosition.y,_yMin,_yScaler);

	int _cellShiftX = Math.round((_magPosX-_xZeroPos)/_cellSizeX);
	int _cellShiftY = Math.round((_magPosY-_yZeroPos)/_cellSizeY);
	
	if(_cellSizeX!=0)
	{
		
//Drawing lines			

		for(int i=0; i<(_nCellsX+10); i++)
			if((((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>Constants._sigmaPanelGridPosition.x)&(((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(Constants._sigmaPanelGridPosition.x+_gridSizeX)))
				g2d.draw(new Line2D.Float((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX,Constants._sigmaPanelGridPosition.y,(i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, Constants._sigmaPanelGridPosition.y+_gridSizeY));	   

		
		for(int i=0; i<(_nCellsY+1); i++)
			if((((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>(Constants._sigmaPanelGridPosition.y-5))&(((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(Constants._sigmaPanelGridPosition.y+_gridSizeY+5)))
				g2d.draw(new Line2D.Float(Constants._sigmaPanelGridPosition.x,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY, Constants._sigmaPanelGridPosition.x+_gridSizeX,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY));	   
		
		
//Drawing numbers
		
		
		for(int i=0; (i<_nCellsX+10); i++)
			if((((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>Constants._sigmaPanelGridPosition.x)&(((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(Constants._sigmaPanelGridPosition.x+_gridSizeX)))
			{
				float temp= 0 + calcIntX(_gridResolX)*(i-1+_cellShiftX);
	    		s = ""+temp;
	    		if((s.length()-s.indexOf('.'))>=3)
	    		{s = s.substring(0,s.indexOf('.')+3);}
	    		else
	    			if((s.length()-s.indexOf('.'))>=2)
	    				s = s.substring(0,s.indexOf('.')+2);
	    		g2d.drawString(s, (i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, Constants._sigmaPanelGridPosition.y + _gridSizeY + 20);
				
			}
		
		
		for(int i=0; i<(_nCellsY+2); i++)
			if((((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>(Constants._sigmaPanelGridPosition.y-5))&(((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(Constants._sigmaPanelGridPosition.y+_gridSizeY+5)))
			{
				float temp= 0 - calcIntY(_gridResolY)*(i-1+_cellShiftY);
	    		s = ""+temp;
	    		//if((s.length()-s.indexOf('.'))>=3)
	    		//{s = s.substring(0,s.indexOf('.')+3);}
	    		//else
	    		//	if((s.length()-s.indexOf('.'))>=2)
	    		//		s = s.substring(0,s.indexOf('.')+2);
				s = cutStringAfterPoint(s, 5);
	    		g2d.drawString(s, Constants._sigmaPanelGridPosition.x - 20- s.length()*5, (i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY);
				
			}
		
		
		
		
	}

	
}


public void drawSigma(Graphics2D g2d)
{


	g2d.setPaint(new Color(0,0,255));
	g2d.setStroke(new BasicStroke(1.0f)); 


	for(int j=1; j<_availWaveCollection.size(); j++)
	{

			g2d.draw(new Line2D.Float((_availWaveCollection.get(j-1)-_xMin)*_xScaler+Constants._sigmaPanelGridPosition.x,(_sigmaCollection.get(j-1)-_yMin)*_yScaler+Constants._sigmaPanelGridPosition.y,
										(_availWaveCollection.get(j)-_xMin)*_xScaler+Constants._sigmaPanelGridPosition.x,(_sigmaCollection.get(j)-_yMin)*_yScaler+Constants._sigmaPanelGridPosition.y)); 

	}
	
	for(int j=0; j<_availWaveCollection.size(); j++)
	{
		g2d.draw(new Ellipse2D.Float((_availWaveCollection.get(j)-_xMin)*_xScaler+Constants._sigmaPanelGridPosition.x-3,(_sigmaCollection.get(j)-_yMin)*_yScaler+Constants._sigmaPanelGridPosition.y-3, 6f, 6f));

	}
	
	
	g2d.setPaint(new Color(255,0,0));
	g2d.setStroke(new BasicStroke(1.5f));
	
	g2d.draw(new Ellipse2D.Float((_availWaveCollection.get(_globalFitPosOfSelectedKin)-_xMin)*_xScaler+Constants._sigmaPanelGridPosition.x-5,(_sigmaCollection.get(_globalFitPosOfSelectedKin)-_yMin)*_yScaler+Constants._sigmaPanelGridPosition.y-5, 10f, 10f));

	
	//System.out.println("xMax = " + _xMax);
	//System.out.println("xMin = " + _xMin);
	//System.out.println("yMax = " + _yMax);
	//System.out.println("yMin = " + _yMin);
	//System.out.println("_xScalerSigma = " + _xScaler);
	//System.out.println("_yScalerSigma = " + _yScaler);
	
	

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
	
	return n2/2;
}

public float calcIntY(float resol)
{
	float n2 = 0;
	if ((_yMax-_yMin)>=(0.000001)){n2 = 0.0000002f*resol;}
	if ((_yMax-_yMin)>=(0.000002)){n2 = 0.0000005f*resol;}
	if ((_yMax-_yMin)>=(0.000005)){n2 = 0.000001f*resol;}
	if ((_yMax-_yMin)>=(0.00001)){n2 = 0.000002f*resol;}
	if ((_yMax-_yMin)>=(0.00002)){n2 = 0.000005f*resol;}
	if ((_yMax-_yMin)>=(0.00005)){n2 = 0.000010f*resol;}
	if ((_yMax-_yMin)>=(0.0001)){n2 = 0.00002f*resol;}
	if ((_yMax-_yMin)>=(0.0002)){n2 = 0.00005f*resol;}
	if ((_yMax-_yMin)>=(0.0005)){n2 = 0.0001f*resol;}
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
	

public String cutStringAfterPoint(String s, int _nPoints)
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

	


public void set_gridSizeX (int _gridSizeX)
{
	this._gridSizeX = _gridSizeX;
}

public void set_gridSizeY (int _gridSizeY)
{
	this._gridSizeY = _gridSizeY;
}





public int get_gridSizeX ()
{
	return _gridSizeX;
}

public int get_gridSizeY ()
{
	return _gridSizeY;
}






public void set_xScaler (float _xScaler)
{
	this._xScaler = _xScaler;
}

public void set_yScaler (float _yScaler)
{
	this._yScaler = _yScaler;
}

public void set_xScalerIni (float _xScalerIni)
{
	this._xScalerIni = _xScalerIni;
}

public void set_yScalerIni (float _yScalerIni)
{
	this._yScalerIni = _yScalerIni;
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

public void set_arraySize (int _arraySize)
{
	this._arraySize = _arraySize;
}

public void set_nOfCreatedCurvePoints (int _nOfCreatedCurvePoints)
{
	this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
}

public void set_zeroPosReal (float _zeroPosReal)
{
	this._zeroPosReal = _zeroPosReal;
}

public void set_beforeZeroPosReal (float _beforeZeroPosReal)
{
	this._beforeZeroPosReal = _beforeZeroPosReal;
}

public void set_levelPosReal (float _levelPosReal)
{
	this._levelPosReal = _levelPosReal;
}


public void set_enableAABox (boolean _enableAABox)
{
	this._enableAABox = _enableAABox;
}



public void set_mouseLabelText(String text)
{
	_mouseLabel.setText(text);
}



public void set_fitMarkerFirstRealX (float _fitMarkerFirstRealX)
{
	this._fitMarkerFirstRealX = _fitMarkerFirstRealX;
}

public void set_fitMarkerFirstRealY (float _fitMarkerFirstRealY)
{
	this._fitMarkerFirstRealY = _fitMarkerFirstRealY;
}

public void set_fitMarkerLastRealX (float _fitMarkerLastRealX)
{
	this._fitMarkerLastRealX = _fitMarkerLastRealX;
}

public void set_fitMarkerLastRealY (float _fitMarkerLastRealY)
{
	this._fitMarkerLastRealY = _fitMarkerLastRealY;
}



public void set_fittingColor(Color _fittingColor)
{
	this._fittingColor = _fittingColor;
}

public Color get_fittingColor()
{
	return _fittingColor;
}
/*

public void set_globalFitXMin(float _globalFitXMin)
{
	this._globalFitXMin = _globalFitXMin;
}

public float get_globalFitXMin()
{
	return _globalFitXMin;
}





public void set_globalFitXMax(float _globalFitXMax)
{
	this._globalFitXMax = _globalFitXMax;
}

public float get_globalFitXMax()
{
	return _globalFitXMax;
}



public void set_globalFitYMin(float _globalFitYMin)
{
	this._globalFitYMin = _globalFitYMin;
}

public float get_globalFitYMin()
{
	return _globalFitYMin;
}


public void set_globalFitYMax(float _globalFitYMax)
{
	this._globalFitYMax = _globalFitYMax;
}

public float get_globalFitYMax()
{
	return _globalFitYMax;
}
*/

public void set_globalFitPosOfSelectedKin(int _globalFitPosOfSelectedKin)
{
	this._globalFitPosOfSelectedKin = _globalFitPosOfSelectedKin;
}

public int get_globalFitPosOfSelectedKin()
{
	return _globalFitPosOfSelectedKin;
}



public void set_availWaveCollection(ArrayList<Float> _availWaveCollection)
{
	this._availWaveCollection = _availWaveCollection;
}

public ArrayList<Float> get_availWaveCollection()
{
	return _availWaveCollection;
}


public void set_sigmaCollection(ArrayList<Float> _sigmaCollection)
{
	this._sigmaCollection = _sigmaCollection;
}

public ArrayList<Float> get_sigmaCollection()
{
	return _sigmaCollection;
}


}
