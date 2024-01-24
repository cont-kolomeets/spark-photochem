package math_packadge;


import interface_packadge.*;

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


public class GlobalFitPanelScaleCalculator {

	
	GlobalFitMath M;
	
	public GlobalFitPanelScaleCalculator(GlobalFitMath M)
	{
		this.M =M;
	}
	

	
	
	public void extGraphPanelSetDefaultBounds()
	{
		M._xMaxExt = 800;
		M._xMinExt = 200;
		M._yMaxExt = 0;
		M._yMinExt = -10000;
		
		M._xScalerExt = M._gridSizeXExt/(M._xMaxExt-M._xMinExt);
		M._yScalerExt = M._gridSizeYExt/(M._yMaxExt-M._yMinExt);
		M._xScalerIniExt = M._xScalerExt;
		M._yScalerIniExt = M._yScalerExt;
	}
	
	
	
	public void slicePanelSetDefaultBounds()
	{
		M._xMaxSlice = 800;
		M._xMinSlice = 200;
		M._yMaxSlice = 0;
		M._yMinSlice = -10000;
		
		M._xScalerSlice = M._gridSizeXSlice/(M._xMaxSlice-M._xMinSlice);
		M._yScalerSlice = M._gridSizeYSlice/(M._yMaxSlice-M._yMinSlice);
		M._xScalerIniSlice = M._xScalerSlice;
		M._yScalerIniSlice = M._yScalerSlice;
	}
	
	
	public void graphPanelSetDefaultBounds()
	{

		M._xMin=-5;
		M._xMax=100;
		M._yMin=-1;
		M._yMax=0.1f;
		M._magPosX=0;
		M._magPosY=0;

		M._globalFitXMin=-5;
		M._globalFitXMax=100;
		M._globalFitYMin=-1;
		M._globalFitYMax=0.1f;
	
			
		M._xScaler = M._gridSizeX/(M._globalFitXMax-M._globalFitXMin);
		M._yScaler = M._gridSizeY/(M._globalFitYMax-M._globalFitYMin);
		M._xScalerIni = M._xScaler;
		M._yScalerIni = M._yScaler;
			
	
			 
		M._levelPosDisplay = 0f;
		M._zeroPosDisplay = 0f;
		M._beforeZeroPosDisplay = 0f;
		M._slicePosDisplay = 0f;
		M._levelPosReal = M.setLevel(M._levelPosDisplay,Constants._kineticsModeGridPosition.y,M._globalFitYMin,M._yScaler);
		M._zeroPosReal = M.setZero(M._zeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._slicePosReal =M.setZero(M._slicePosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);

		M.myRect.y = M._levelPosReal-5-M._magPosY;
		M.myZeroRect.x = M._zeroPosReal-5-M._magPosX;
		M.myBeforeZeroRect.x = M._beforeZeroPosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
		M.sliceRect.x = M._slicePosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
		M._yZeroPos = Math.round(M._levelPosReal);
		M._xZeroPos = Math.round(M._zeroPosReal);

	}
	
	public void extGraphPanelRecalcSpecBounds()
	{
		M._xMaxExt = -10000;
		M._xMinExt = 100000;
		M._yMaxExt = 0f;
		M._yMinExt = 10000;
		float _xPoint, _yPoint;
		
		
		
		
		
		if(M._availWaveCollection.size()==1)
		{
			M._xMaxExt = M._availWaveCollection.get(0)+100;
			M._xMinExt = M._availWaveCollection.get(0)-100;
			
			for(int i=0; i<M._xSAbsSpecCollectionYFiltered.size(); i++)
			{
				_yPoint = M._xSAbsSpecCollectionYFiltered.get(i).get(0);
				//if(_yMaxExt<_yPoint){_yMaxExt=_yPoint;}
				if(M._yMinExt>_yPoint){M._yMinExt=_yPoint;}
			}
			
		}
		else
		{
			for(int j=0; j<M._availWaveCollection.size(); j++)
			{
				_xPoint = M._availWaveCollection.get(j);
				if(M._xMaxExt<_xPoint){M._xMaxExt=_xPoint;}
				if(M._xMinExt>_xPoint){M._xMinExt=_xPoint;}
				
				
				for(int i=0; i<M._xSAbsSpecCollectionYFiltered.size(); i++)
				{
					_yPoint = M._xSAbsSpecCollectionYFiltered.get(i).get(j);
					//if(_yMaxExt<_yPoint){_yMaxExt=_yPoint;}
					if(M._yMinExt>_yPoint){M._yMinExt=_yPoint;}
				}
			}
			M._xMaxExt +=1;
			M._xMinExt -=1;
		}
		

		//_yMaxExt +=1;
		M._yMinExt -=1;
		
		M._xScalerExt =M. _gridSizeXExt/(M._xMaxExt-M._xMinExt);
		M._yScalerExt = M._gridSizeYExt/(M._yMaxExt-M._yMinExt);
		M._xScalerIniExt =M. _xScalerExt;
		M._yScalerIniExt = M._yScalerExt;
	}
		
		
	public void slicePanelRecalcSpecBounds()
	{
		M._xMaxSlice = -10000;
		M._xMinSlice = 100000;
		
		
		
		
		if(M.get_ifShowTransientSpec())
		{
			M._yMaxSlice = this.M._globalFitYMax*1.1f;
			M._yMinSlice = this.M._globalFitYMin*1.1f;
		}

		
		float _xPoint, _yPoint;

		for(int j=0; j<M._availWaveCollection.size(); j++)
		{
			_xPoint = M._availWaveCollection.get(j);
			if(M._xMaxSlice<_xPoint){M._xMaxSlice=_xPoint;}
			if(M._xMinSlice>_xPoint){M._xMinSlice=_xPoint;}
			
			if(M.get_ifShowCalcSpec())
			{
				if(M._solvedCurveCollectionY.size()!=0)
				{
					_yPoint = M._solvedCurveCollectionY.get(j).get(M.get_globalFitPosOfCurrentTimePoint());
					if(M._yMaxSlice<_yPoint){M._yMaxSlice=_yPoint;}
					if(M._yMinSlice>_yPoint){M._yMinSlice=_yPoint;}
				}
			}
			
			if(M.get_ifShowContributionsSpec())
			{
				for(int k=0; k<M._solvedCurveContributionCollectionY.size(); k++)
				{
					_yPoint = M._solvedCurveContributionCollectionY.get(k).get(j).get(M.get_globalFitPosOfCurrentTimePoint());
					if(M._yMaxSlice<_yPoint){M._yMaxSlice=_yPoint;}
					if(M._yMinSlice>_yPoint){M._yMinSlice=_yPoint;}
				}
			}			
			
		}
		M._xMaxSlice +=1;
		M._xMinSlice -=1;
		
		M._xScalerSlice = M._gridSizeXSlice/(M._xMaxSlice-M._xMinSlice);
		M._yScalerSlice = M._gridSizeYSlice/(M._yMaxSlice-M._yMinSlice);
		M._xScalerIniSlice = M._xScalerSlice;
		M._yScalerIniSlice = M._yScalerSlice;
	}
	
	
	public void extGraphPanelSetXSSpecBoundsX()
	{
		M._xMaxExt = -10000;
		M._xMinExt = 100000;
		float _xPoint;
		
		if(M._availWaveCollection.size()==1)
		{
			M._xMaxExt = M._availWaveCollection.get(0)+100;
			M._xMinExt = M._availWaveCollection.get(0)-100;
			
		}
		
		//if(_availWaveCollection.size()==2)
		//{
		//	_xMaxExt = _availWaveCollection.get(0)-50;
		//	_xMinExt = _availWaveCollection.get(1)+50;
		//	
		//}
		
		if(M._availWaveCollection.size()>1)
		{
			for(int j=0; j<M._availWaveCollection.size(); j++)
			{
				_xPoint = M._availWaveCollection.get(j);
				if(M._xMaxExt<_xPoint){M._xMaxExt=_xPoint;}
				if(M._xMinExt>_xPoint){M._xMinExt=_xPoint;}

			}
			M._xMaxExt +=1;
			M._xMinExt -=1;
		}

	
		
		M._xScalerExt = M._gridSizeXExt/(M._xMaxExt-M._xMinExt);
		M._xScalerIniExt = M._xScalerExt;
		
	}

	
	
	public void slicePanelSetXSSpecBoundsX()
	{
		
	}
	
	public void calcErrorPanelBoundsY()
	{
		int _start=0, _end=0;
		float _deviation;
		
		M._yMaxError=-10000;
		M._yMinError = 10000;
		
	if(M._solvedCurveCollectionY.size()!=0)
		for(int w=0; w<M._availWaveCollection.size(); w++)
		{
			
			if(M._ifEnableCurveBounds)
			{
				_start = M._markersCollection.get(w).get(0);
				_end = M._markersCollection.get(w).get(1);
			}
			else
			{
				_start = M._posToStartFittingWith;
				_end = M._solvedCurveCollectionX.size();
			}
			
			//System.out.println("start  " + _start);
			for(int i=_start; i<_end; i++)
			{
				_deviation = M._kinCollectionY.get(w).get(i) - M._solvedCurveCollectionY.get(w).get(i);
				if(M._yMaxError<_deviation){M._yMaxError=_deviation;}
				if(M._yMinError>_deviation){M._yMinError=_deviation;}
			}
			
			
			
		}
		
		float a = Math.max(Math.abs(M._yMaxError), Math.abs(M._yMinError));
		M._yScalerError = M._gridSizeYError/(a)/2;
	}
	
	
	
	
	public void calcSigmaPanelBounds()
	{
		//int _start=0, _end=0;
		//float _deviation;
		float _xPoint=0, _yPoint=0;
		
		M._yMaxSigma=-10000;
		M._yMinSigma = 10000;
		M._xMaxSigma=-10000;
		M._xMinSigma = 10000;
		

		for(int w=0; w<M._availWaveCollection.size(); w++)
		{
			
				_xPoint = M._availWaveCollection.get(w);
				_yPoint = M._sigmaCollection.get(w);

				if(M._xMaxSigma<_xPoint){M._xMaxSigma=_xPoint;}
				if(M._xMinSigma>_xPoint){M._xMinSigma=_xPoint;}
				//if(_yMaxSigma<_yPoint){_yMaxSigma=_yPoint;}
				if(M._yMinSigma>_yPoint){M._yMinSigma=_yPoint;}
		}
		
		M._xMaxSigma+=0.01;
		M._xMinSigma-=0.01;
		M._yMaxSigma=0;
		M._yMinSigma-=1e-5f;
		//float a = Math.max(Math.abs(_yMaxError), Math.abs(_yMinError));
		
		M._xScalerSigma = M._gridSizeXSigma/(M._xMaxSigma - M._xMinSigma);
		M._yScalerSigma = M._gridSizeYSigma/(M._yMaxSigma - M._yMinSigma);
		
		

		
	} 
	
	
	
	public void globalFitRecalcScale()
	{
	
	
		M._globalFitXMin=100000;
		M._globalFitXMax=-100000;
		M._globalFitYMin=100000;
		M._globalFitYMax=-100000;
		M._magPosX=0;
		M._magPosY=0;
	
		//System.out.println("kin shift coll size = " + _kinShiftCollection.size());

		
		
		
		
		for(int i=0; i<M._kinShiftCollection.size(); i++)
		{
			M._xMin = M._kinShiftCollection.get(i)[0];
			M._xMax = M._kinShiftCollection.get(i)[1];
			M._yMin = M._kinShiftCollection.get(i)[2];
			M._yMax = M._kinShiftCollection.get(i)[3];
			
			//System.out.println(_kinShiftCollection.get(i)[2] + "  " + _kinShiftCollection.get(i)[3]);
		
			if(M._globalFitXMax<M._xMax){M._globalFitXMax=M._xMax;}	
			if(M._globalFitYMax<M._yMax){M._globalFitYMax=M._yMax;}
			if(M._globalFitXMin>M._xMin){M._globalFitXMin=M._xMin;}	
			if(M._globalFitYMin>M._yMin){M._globalFitYMin=M._yMin;}			
		
		}
		
		
		
		
		M._xScaler = M._gridSizeX/(M._globalFitXMax-M._globalFitXMin);
		M._yScaler = M._gridSizeY/(M._globalFitYMax-M._globalFitYMin);
		M._xScalerIni = M._xScaler;
		M._yScalerIni = M._yScaler;
		
		M._levelPosReal = M.setLevel(M._levelPosDisplay,Constants._kineticsModeGridPosition.y,M._globalFitYMin,M._yScaler);
		M._zeroPosReal = M.setZero(M._zeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._slicePosReal = M.setZero(M._slicePosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);

		
		
		
	}
	
	
	
	
	
	
	
}