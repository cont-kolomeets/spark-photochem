package controller_packadge;


import interface_packadge.*;
import controller_packadge.*;
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.TimerTask;
import java.util.Timer;
import java.beans.*;
import java.util.Random;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;


public class GlobalFitControllerPanelRefresher {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerPanelRefresher(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	
	public void graphPanelRefresh()
	{
		//System.out.println("I'm in graphPanelRefresh method!");
		
		//synchronized(_globalFitMath1.get_syncObject())
		//{
			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_arraySize(_globalFitMath1.get_arraySize());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_xScaler(_globalFitMath1.get_xScaler());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_yScaler(_globalFitMath1.get_yScaler());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_xScalerIni(_globalFitMath1.get_xScalerIni());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_yScalerIni(_globalFitMath1.get_yScalerIni());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_xMin(_globalFitMath1.get_globalFitXMin());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_yMin(_globalFitMath1.get_globalFitYMin());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_xMax(_globalFitMath1.get_globalFitXMax());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_yMax(_globalFitMath1.get_globalFitYMax());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_magPosX(_globalFitMath1.get_magPosX());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_magPosY(_globalFitMath1.get_magPosY());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_levelPosReal(_globalFitMath1.get_levelPosReal());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_zeroPosReal(_globalFitMath1.get_zeroPosReal());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_beforeZeroPosReal(_globalFitMath1.get_beforeZeroPosReal());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_slicePosReal(_globalFitMath1.get_slicePosReal());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_kinCollectionX(_globalFitMath1.get_kinCollectionX());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_kinCollectionY(_globalFitMath1.get_kinCollectionY());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_solvedCurveCollectionX(_globalFitMath1.get_solvedCurveCollectionX());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_solvedCurveCollectionY(_globalFitMath1.get_solvedCurveCollectionY());	
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_solvedCurveContributionCollectionY(_globalFitMath1.get_solvedCurveContributionCollectionY());	
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_fitMarkerFirstRealX(_globalFitMath1.get_fitMarkerFirstRealX());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_fitMarkerFirstRealY(_globalFitMath1.get_fitMarkerFirstRealY());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_fitMarkerLastRealX(_globalFitMath1.get_fitMarkerLastRealX());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_fitMarkerLastRealY(_globalFitMath1.get_fitMarkerLastRealY());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_selectedRect(_globalFitMath1.get_selectedRect());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_ifCalcCurveShifts(_globalFitMath1.get_ifCalcCurveShifts());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_ifCalcContributions(_globalFitMath1.get_ifCalcContributions());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_ifEnableCurveBounds(_globalFitMath1.get_ifEnableCurveBounds());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_ifShowBackgroundCurves(_globalFitMath1.get_ifShowBackgroundCurves());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_ifShowSlice(_globalFitMath1.get_ifShowSlice());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_markersCollection(_globalFitMath1.get_markersCollection());
				_globalFitInterface1._globalFitMainInterface._graphPanel.set_syncObject(_globalFitMath1.get_syncObject());
				

				_globalFitInterface1._globalFitMainInterface._graphPanel.repaint();
			}


		//}
		
	}
	


	
	
	public void extGraphPanelRefresh()
	{

			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xScaler(_globalFitMath1.get_xScalerExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_yScaler(_globalFitMath1.get_yScalerExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xScalerIni(_globalFitMath1.get_xScalerIniExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_yScalerIni(_globalFitMath1.get_yScalerIniExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xMin(_globalFitMath1.get_xMinExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_yMin(_globalFitMath1.get_yMinExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xMax(_globalFitMath1.get_xMaxExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_yMax(_globalFitMath1.get_yMaxExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_magPosX(_globalFitMath1.get_magPosXExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_magPosY(_globalFitMath1.get_magPosYExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xSAbsSpecCollectionX(_globalFitMath1.get_availWaveCollection());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xSAbsSpecCollectionY(_globalFitMath1.get_xSAbsSpecCollectionYFiltered());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_xSAbsSpecErrorCollection(_globalFitMath1.get_xSAbsSpecErrorCollection());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_globalFitPosOfSelectedSpec(_globalFitMath1.get_globalFitPosOfSelectedSpec());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_ifShowErrorExt(_globalFitMath1.get_ifShowErrorExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_ifShowErrorExtWithBars(_globalFitMath1.get_ifShowErrorExtWithBars());

				_globalFitInterface1._globalFitExtFrame._extGraphPanel.repaint();
				_globalFitInterface1._globalFitExtFrame.repaint();
			}


		
	
	}
	
	
	
	
	
	
	public void slicePanelRefresh()
	{

			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_xScaler(_globalFitMath1.get_xScalerSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_yScaler(_globalFitMath1.get_yScalerSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_xScalerIni(_globalFitMath1.get_xScalerIniSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_yScalerIni(_globalFitMath1.get_yScalerIniSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_xMin(_globalFitMath1.get_xMinSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_yMin(_globalFitMath1.get_yMinSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_xMax(_globalFitMath1.get_xMaxSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_yMax(_globalFitMath1.get_yMaxSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_magPosX(_globalFitMath1.get_magPosXSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_magPosY(_globalFitMath1.get_magPosYSlice());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_availWaveCollection(_globalFitMath1.get_availWaveCollection());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_kinCollectionY(_globalFitMath1.get_kinCollectionY());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_solvedCurveCollectionY(_globalFitMath1.get_solvedCurveCollectionY());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_solvedCurveContributionCollectionY(_globalFitMath1.get_solvedCurveContributionCollectionY());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitSliceFrame._slicePanel.set_globalFitPosOfCurrentTimePoint(_globalFitMath1.get_globalFitPosOfCurrentTimePoint());
				//_globalFitInterface1._globalFitMainInterface._graphPanel.set_selectedRect(_globalFitMath1.get_selectedRect());
			 	
				_globalFitInterface1._globalFitSliceFrame._slicePanel.repaint();
				_globalFitInterface1._globalFitSliceFrame.repaint();
			}


		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void errorPanelRefresh()
	{
		

			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				if(_globalFitMath1.get_ifEnableCurveBounds())
					_globalFitMath1.calcErrorPanelBoundsY();
				
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_xScaler(_globalFitMath1.get_xScaler());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_yScaler(_globalFitMath1.get_yScalerError());
				//_globalFitInterface1._globalFitMainInterface._errorPanel.set_xScalerIni(_globalFitMath1.get_xScalerErrorIni());
				//_globalFitInterface1._globalFitMainInterface._errorPanel.set_yScalerIni(_globalFitMath1.get_yScalerIni());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_xMin(_globalFitMath1.get_globalFitXMin());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_yMin(_globalFitMath1.get_yMinError());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_xMax(_globalFitMath1.get_globalFitXMax());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_yMax(_globalFitMath1.get_yMaxError());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_magPosX(_globalFitMath1.get_magPosX());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_magPosY(_globalFitMath1.get_magPosY());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_solvedCurveCollectionX(_globalFitMath1.get_solvedCurveCollectionX());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_solvedCurveErrorCollectionY(_globalFitMath1.get_solvedCurveErrorCollectionY());	
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_posToStartFittingWith(_globalFitMath1.get_posToStartFittingWith());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_markersCollection(_globalFitMath1.get_markersCollection());
				_globalFitInterface1._globalFitMainInterface._errorPanel.set_ifEnableCurveBounds(_globalFitMath1.get_ifEnableCurveBounds());
				
			
				_globalFitInterface1._globalFitMainInterface._errorPanel.repaint();
			}


		
		

	}
	
	
	
	public void sigmaPanelSetParameters()
	{
		
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_xScaler(_globalFitMath1.get_xScalerSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_yScaler(_globalFitMath1.get_yScalerSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_xMin(_globalFitMath1.get_xMinSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_yMin(_globalFitMath1.get_yMinSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_xMax(_globalFitMath1.get_xMaxSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_yMax(_globalFitMath1.get_yMaxSigma());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_availWaveCollection(_globalFitMath1.get_availWaveCollection());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_sigmaCollection(_globalFitMath1.get_sigmaCollection());
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
	}
	
	
	
	
}