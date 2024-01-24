package interface_packadge;

import math_packadge.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;



public class GlobalFitGraphicGrid {

	public int _gridSizeX=100, _gridSizeY=100;
	public int _nCellsXIni=10, _nCellsYIni=10,_nCellsX=20, _nCellsY=20;
	public float _cellSizeX = (_gridSizeX/_nCellsX);
	public float _cellSizeY = (_gridSizeY/_nCellsY);
	
	public float _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public float _xMax=1000,_xMin=-200,_yMax=0,_yMin=-500,_xScaler=1f,_yScaler=1f, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni=1f,_yScalerIni=1f;
	public float _magPosX = 0, _magPosY = 0;
	
	  float strokeThickness = 1.0f;
	  float miterLimit = 10f;
	  float[] dashPattern = { 5f };
	  float[] dashPattern1 = { 2f };
	  float dashPhase = 5f;
	    BasicStroke _dashedStroke = new BasicStroke(strokeThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,	    		
	        miterLimit, dashPattern, dashPhase);
	    BasicStroke _tinyDashedStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,	    		
		        miterLimit, dashPattern1, dashPhase);
	
	public int _gridPosX=0, _gridPosY = 0;
	
	
	

	    
	
	
	public void drawGrid(Graphics2D g2d)
	{
		String s;
		
		// Draw grid
		g2d.setStroke(_dashedStroke);
		g2d.setPaint(Color.black);		
		g2d.draw(new Rectangle(_gridPosX, _gridPosY,_gridSizeX, _gridSizeY));

		
		_gridResolX = _gridResolXIni*calcGoodResol(_xScaler/_xScalerIni);
		_gridResolY = _gridResolYIni*calcGoodResol(_yScaler/_yScalerIni);

		_cellSizeX = calcIntX(_gridResolX)*_xScaler;
		_nCellsX = Math.round(_gridSizeX/_cellSizeX)+2;
		
		if(_nCellsX>30)
		{
			float m=(float)_nCellsX/10f;
			_gridResolX *= m;
			_cellSizeX = calcIntX(_gridResolX)*_xScaler;
			_nCellsX = Math.round(_gridSizeX/_cellSizeX)+2;
		}
		
		_cellSizeY = calcIntY(_gridResolY)*_yScaler;
		_nCellsY = Math.round(_gridSizeY/_cellSizeY)+2;
		
		if(_nCellsY>15)
		{
			float m=(float)_nCellsY/10f;
			_gridResolY *= m;
			_cellSizeY = calcIntY(_gridResolY)*_yScaler;
			_nCellsY = Math.round(_gridSizeY/_cellSizeY)+2;
		}
		
		_xZeroPos = setZero(0,_gridPosX,_xMin,_xScaler);
		_yZeroPos = setLevel(0,_gridPosY,_yMin,_yScaler);

		int _cellShiftX = Math.round((_magPosX-_xZeroPos)/_cellSizeX);
		int _cellShiftY = Math.round((_magPosY-_yZeroPos)/_cellSizeY);
		
		if(_cellSizeX!=0)
		{
			
	//Drawing lines			
			float _skip=1;

			if(_nCellsX>20)
			{
				//float m=(float)_nCellsX/10f;
				//_gridResolX = _gridResolXIni*calcGoodResol(m*_xScaler/_xScalerIni);
				//_cellSizeX = calcIntX(_gridResolX)*_xScaler*m;
				//_gridResolX=_gridResolXIni*calcGoodResol(_xScaler/_xScalerIni)*m;
				//_nCellsX = Math.round(_gridSizeX/_cellSizeX)+2;
				//_xZeroPos = setLevel(0,_gridPosX,_xMin,_xScaler);
				//_cellShiftX = Math.round((_magPosX-_xZeroPos)/_cellSizeX);
			}
			
			for(int i=-2; i<(_nCellsX+2); i++)
				if((((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>_gridPosX)&(((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(_gridPosX+_gridSizeX)))
					g2d.draw(new Line2D.Float((i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX,_gridPosY,(i+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, _gridPosY+_gridSizeY));	   


			if(_nCellsY>10)
			{
				//float m=(float)_nCellsY/10f;
				//_gridResolY = _gridResolYIni*calcGoodResol(m*_yScaler/_yScalerIni);
				//_cellSizeY = calcIntY(_gridResolY)*_yScaler*m;
				//_gridResolY=_gridResolYIni*calcGoodResol(_yScaler/_yScalerIni)*m;
				//_nCellsY = Math.round(_gridSizeY/_cellSizeY)+2;
				//_yZeroPos = setLevel(0,_gridPosY,_yMin,_yScaler);
				//_cellShiftY = Math.round((_magPosY-_yZeroPos)/_cellSizeY);
			}

			
			for(int i=-2; i<(_nCellsY+2); i++)
				if((((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>_gridPosY)&(((i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(_gridPosY+_gridSizeY)))
					g2d.draw(new Line2D.Float(_gridPosX,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY, _gridPosX+_gridSizeX,(i+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY));	   
			
			
	//Drawing numbers
			
			
			for(int i=0; (i<_nCellsX+1); i++)
				if((((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)>_gridPosX)&(((i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX)<(_gridPosX+_gridSizeX)))
				{
					float temp= 0 + calcIntX(_gridResolX)*(i-1+_cellShiftX);
		    		s = ""+temp;
		    		if((s.length()-s.indexOf('.'))>=3)
		    		{s = s.substring(0,s.indexOf('.')+3);}
		    		else
		    			if((s.length()-s.indexOf('.'))>=2)
		    				s = s.substring(0,s.indexOf('.')+2);
		    		g2d.drawString(s, (i-1+_cellShiftX)*_cellSizeX+_xZeroPos-_magPosX, _gridPosY + _gridSizeY + 20);
					
				}
			
			
			for(int i=0; (i<_nCellsY+1); i++)
				if((((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)>_gridPosY)&(((i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY)<(_gridPosY+_gridSizeY)))
				{
					float temp= 0 - calcIntY(_gridResolY)*(i-1+_cellShiftY);
		    		s = ""+temp;
		    		//if((s.length()-s.indexOf('.'))>=3)
		    		//{s = s.substring(0,s.indexOf('.')+3);}
		    		//else
		    		//	if((s.length()-s.indexOf('.'))>=2)
		    		//		s = s.substring(0,s.indexOf('.')+2);
					s = MethodsCollection.cutStringAfterPoint(s, 5);
		    		g2d.drawString(s, _gridPosX - 20- s.length()*5, (i-1+_cellShiftY)*_cellSizeY+_yZeroPos-_magPosY);
					
				}
			
			
			
			
		}

		
	}
	
	
	
	
	
	public float setLevel(float _levelPosDisplay, int _gridPosY, float _yMin, float _yScaler)
	{
		return _gridPosY - (_yMin+_levelPosDisplay)*_yScaler;
	}




	public float setZero(float _zeroPosDisplay, int _gridPosX, float _xMin, float _xScaler)
	{
		return _gridPosX - (_xMin-_zeroPosDisplay)*_xScaler;
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
		float _difference = _xMax-_xMin;
		
		if((_gridSizeX/_gridSizeY)>1.2)
			_difference = _difference*_gridSizeY/_gridSizeX;
		
		if ((_difference)>=(1)){n2 = 0.2f*resol;}
		if ((_difference)>=(2)){n2 = 0.5f*resol;}
		if ((_difference)>=(5)){n2 = 1*resol;}
		if ((_difference)>=(10)){n2 = 2*resol;}
		if ((_difference)>=(20)){n2 = 5*resol;}
		if ((_difference)>=(50)){n2 = 10*resol;}
		if ((_difference)>=(100)){n2 = 20*resol;}
		if ((_difference)>=(200)){n2 = 50*resol;}
		if ((_difference)>=(500)){n2 = 100*resol;}
		if ((_difference)>=(1000)){n2 = 200*resol;}
		if ((_difference)>=(2000)){n2 = 500*resol;}
		if ((_difference)>=(5000)){n2 = 1000*resol;}
		if ((_difference)>=(10000)){n2 = 2000*resol;}
		if ((_difference)>=(20000)){n2 = 5000*resol;}
		if ((_difference)>=(50000)){n2 = 10000*resol;}
		if ((_difference)>=(100000)){n2 = 20000*resol;}
		if ((_difference)>=(200000)){n2 = 50000*resol;}
		if ((_difference)>=(500000)){n2 = 100000*resol;}
		
		
		//
		//	n2=n2*_;
		
		return n2;
	}

	public float calcIntY(float resol)
	{
		float n2 = 0;
		float _difference = _yMax-_yMin;
		if ((_difference)>=(0.001)){n2 = 0.0002f*resol;}
		if ((_difference)>=(0.002)){n2 = 0.0005f*resol;}
		if ((_difference)>=(0.005)){n2 = 0.001f*resol;}
		if ((_difference)>=(0.01)){n2 = 0.002f*resol;}
		if ((_difference)>=(0.02)){n2 = 0.005f*resol;}
		if ((_difference)>=(0.05)){n2 = 0.010f*resol;}
		if ((_difference)>=(0.1)){n2 = 0.02f*resol;}
		if ((_difference)>=(0.2)){n2 = 0.05f*resol;}
		if ((_difference)>=(0.5)){n2 = 0.1f*resol;}
		if ((_difference)>=(1)){n2 = 0.2f*resol;}
		if ((_difference)>=(2)){n2 = 0.5f*resol;}
		if ((_difference)>=(5)){n2 = 1f*resol;}
		if ((_difference)>=(10)){n2 = 2f*resol;}
		if ((_difference)>=(20)){n2 = 5f*resol;}
		if ((_difference)>=(50)){n2 = 10f*resol;}
		if ((_difference)>=(100)){n2 = 20f*resol;}
		if ((_difference)>=(200)){n2 = 50f*resol;}
		if ((_difference)>=(500)){n2 = 100f*resol;}
		if ((_difference)>=(1000)){n2 = 200f*resol;}
		if ((_difference)>=(2000)){n2 = 500f*resol;}
		if ((_difference)>=(5000)){n2 = 1000f*resol;}
		if ((_difference)>=(10000)){n2 = 2000f*resol;}
		if ((_difference)>=(20000)){n2 = 5000f*resol;}
		if ((_difference)>=(50000)){n2 = 10000f*resol;}
		if ((_difference)>=(100000)){n2 = 20000f*resol;}
		if ((_difference)>=(200000)){n2 = 50000f*resol;}
		if ((_difference)>=(500000)){n2 = 100000f*resol;}
		
		return n2;
	}

	
	
}
