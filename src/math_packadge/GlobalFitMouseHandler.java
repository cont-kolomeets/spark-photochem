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


public class GlobalFitMouseHandler {

	
	GlobalFitMath M;
	
	public GlobalFitMouseHandler(GlobalFitMath M)
	{
		this.M =M;
	}

	// slicePanel mouse events
	
	
	public void slicePanelRecalcMouseLabel()
	{
		
		M._mouseXLabelSlice = (M._mouseXSlice  - Constants._slicePanelGridPosition.x)/M._xScalerSlice + M._xMinSlice;
		M._mouseYLabelSlice = -(M._mouseYSlice - Constants._slicePanelGridPosition.y)/M._yScalerSlice - M._yMinSlice;

	      String s = "" + M._mouseXLabelSlice;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.indexOf('.')+5);
	      s = s + "     " + M._mouseYLabelSlice;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.lastIndexOf('.')+5);
	
	      M._mouseLabelTextSlice = s;
	}
	
	
	
	
	   public void slicePanelMouseMoved(MouseEvent e)
	   {
		   M._mouseXSlice = e.getX();
		   M._mouseYSlice = e.getY();
		   slicePanelRecalcMouseLabel();
	   }
	
	
	
	
	
	
	
	
	
	
	// sigmaPanel mouse events
	
	public void sigmaPanelRecalcMouseLabel()
	{
		
		M._mouseXLabelSigma = (M._mouseXSigma  - Constants._sigmaPanelGridPosition.x)/M._xScalerSigma + M._xMinSigma;
		M._mouseYLabelSigma = -(M._mouseYSigma - Constants._sigmaPanelGridPosition.y)/M._yScalerSigma - M._yMinSigma;

	      String s = "" + M._mouseXLabelSigma;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.indexOf('.')+5);
	      s = s + "     " + M._mouseYLabelSigma;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.lastIndexOf('.')+5);
	
	      M._mouseLabelTextSigma = s;
		
		
	}
	
	
	
	
	   public void sigmaPanelMouseMoved(MouseEvent e)
	   {
		   M._mouseXSigma = e.getX();
		   M._mouseYSigma = e.getY();
		      sigmaPanelRecalcMouseLabel();
	   }
	
	
	
	
	
	
	
	
	// extGraphPanel Mouse events
	
	
	public void extGraphPanelCalcRectanglesPositions()
	{
		
	if(M._absRectCollection.size()!=0)
		for(int i=0; i<M._absRectCollection.get(M._globalFitPosOfSelectedSpec).size(); i++)
		{
			float x;
			float y;
				
			x= (M._availWaveCollection.get(i)-M._xMinExt)*M._xScalerExt+Constants._kineticsModeGridPosition.x-M._magPosXExt-5;
			y= (M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).get(i)-M._yMinExt)*M._yScalerExt+Constants._kineticsModeGridPosition.y-M._magPosYExt-5;
			
			M._absRectCollection.get(M._globalFitPosOfSelectedSpec).get(i).x = x;
			M._absRectCollection.get(M._globalFitPosOfSelectedSpec).get(i).y = y;
		}

	}
	
	
	public Cursor get_cursorExt()
	{
		return M._cursorExt;
	}

	
	
	
	
	public void extGraphPanelRecalcMouseLabel()
	{
		
		M._mouseXLabelExt = (M._mouseXExt + M._magPosXExt - Constants._kineticsModeGridPosition.x)/M._xScalerExt + M._xMinExt;
		M._mouseYLabelExt = -(M._mouseYExt + M._magPosYExt - Constants._kineticsModeGridPosition.y)/M._yScalerExt - M._yMinExt;
	      //_mouseX = (_mouseX + _magPosX - _gridPos.x)/_xScaler + _xMin;
	      String
	      s = "" + M._mouseXLabelExt;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.indexOf('.')+5);
	      s = s + "     " + M._mouseYLabelExt;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.lastIndexOf('.')+5);
	    /*  if(_ifShowAverageValue)
	      {
	    	  s = s + "     Average:  " + _graphAverage;
	    	  if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	    		  s = s.substring(0, s.lastIndexOf('.')+5);
	      }
	      */
	      M._mouseLabelTextExt = s;
		
		
		
		
	}
	
	
	
	
	
	
	
	   public void extGraphPanelMouseMoved(MouseEvent e)
	   {
		   M._mouseXExt = e.getX();
		   M._mouseYExt = e.getY();
		      extGraphPanelCalcRectanglesPositions();
		      

		      		int i=0;
		      	if(M._absRectCollection.size()!=0)
		      		for(int j=0; j<M._absRectCollection.get(M._globalFitPosOfSelectedSpec).size(); j++)
		    		{
		    			if((M._absRectCollection.get(M._globalFitPosOfSelectedSpec).get(j).getBounds2D().contains(M._mouseXExt, M._mouseYExt)))
		    			{
		    				M._cursorExt = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
		    				i=1;
		      				
		       				float y= M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).get(j); //display(on label) Y value
		       				M._absValueOfDraggedMarker = y;
		       				M._wavelengthValueOfDraggedMarker = M._availWaveCollection.get(j);
		       				//M._extValueOfDraggedMarker = M.
		       				M._ifShowAbsValueLabel = true;
		      			}
		    		}
 
		    	   if(i==0)
			      {
		    		   M._cursorExt = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		    		   //SetCursor();
		    		   M._ifShowAbsValueLabel = false;
			      }
		      
		    	   
		    	   if(M._ifEnableMoveAllMarkersMode)
		    		   M._cursorExt = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		    	   
		    	   extGraphPanelRecalcMouseLabel();
		      
	   }
	   
	   
		   
	   
	   
	   
	   
	   
	   public void extGraphPanelMousePressed(MouseEvent e) {
		   M.xExt = e.getX();
		   M.yExt = e.getY();
		  
		      if ((e.getButton()==1))
		      { 
		    		for(int j=0; j<M._absRectCollection.get(M._globalFitPosOfSelectedSpec).size(); j++)
		    		{
		    			if((M._absRectCollection.get(M._globalFitPosOfSelectedSpec).get(j).getBounds2D().contains(M.xExt, M.yExt)))
		    			{
		    				M._numberOfLastClickedRect=j;
		      			}
		    		}

		      } 
		    	  

		      if(e.getButton()==2)
		      {
		    	  M._moveScreen=true;
		      }

		      if(e.getButton()==3)
		      {
		    	  M._xScalerExt = M._xScalerIniExt;
		    	  M._yScalerExt = M._yScalerIniExt;
		    	  M._magPosXExt = 0;
		    	  M._magPosYExt = 0;
		    	  M._beingSel=false;
		    	  M._ifCursorFree = true;
 		      }


	    }

	    public void extGraphPanelMouseReleased(MouseEvent e) 
	    {
	    	M.x2Ext = e.getX();
	    	M.y2Ext = e.getY();
	    	M._ifCursorFree = true;
	    	M._moveExtMarker = false;

	    	M._numberOfDraggedRect=-1;
		      
		      if(M._moveScreen)
		      {
		    	  M._moveScreen=false;
		    	  M._cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		      }
	    
	    }

	    
	    public void extGraphPanelMouseDragged(MouseEvent e) {

	    	
	    	int dx = e.getX() - M.xExt;
	    	int dy = e.getY() - M.yExt;
	    	M._mouseXExt = e.getX();
	    	M._mouseYExt = e.getY();
	    	recalcMouseLabel();
	    	extGraphPanelCalcRectanglesPositions();

	
	    	
    		for(int j=0; j<M._absRectCollection.get(M._globalFitPosOfSelectedSpec).size(); j++)
    		{
    			if((((M._absRectCollection.get(M._globalFitPosOfSelectedSpec).get(j).getBounds2D().contains(M.xExt, M.yExt)))||(j==M._numberOfDraggedRect))&(!M._moveScreen))
    			{
    				float y= M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).get(j); //display(on label) Y value
    				y=y+dy/M._yScalerExt;
    				M._numberOfDraggedRect=j;
    				M._absValueOfDraggedMarker = y;
    				M._wavelengthValueOfDraggedMarker = M._availWaveCollection.get(j);
    				M._ifShowAbsValueLabel = true;
      				
    				M.performSmoothMove(dy);

     				//extGraphPanelRecalcSpecBounds();

    		   	}
    		}

	      
	      if(M._moveScreen)
	      {
	    	  M._magPosXExt -= dx;
	    	  M._magPosYExt -= dy;
    	    
	    	  M._cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    	    //SetCursor();

	      }
	      
	      
	      M.xExt += dx;
	      M.yExt += dy;
	    
	      
	    }
	    
	    
	    
	
	
	
	
	 // globalFitGraphPanel Mouse events
		
		
		public void calcRectanglesPositions()
		{
			M.myRect.y = M._levelPosReal-5-M._magPosY;
			M.myRect.width = M._gridSizeX;
			M.myZeroRect.x = M._zeroPosReal-5-M._magPosX;
			M.myZeroRect.height = M._gridSizeY;
			M.myBeforeZeroRect.x = M._beforeZeroPosReal-5-M._magPosX;
			M.myBeforeZeroRect.height = M._gridSizeY;
			M.sliceRect.x = M._slicePosReal-5-M._magPosX;
			M.sliceRect.height = M._gridSizeY;
			M.beforeZeroSelRect.width = M._beforeZeroPosReal-M.setZero(0,Constants._kineticsModeGridPosition.x, M._xMin, M._xScaler);
			M.beforeZeroSelRect.x = -M._magPosX+M.setZero(0,Constants._kineticsModeGridPosition.x, M._xMin, M._xScaler);
			M.beforeZeroSelRect.height = M._gridSizeY;
		    //fitMarkerFirstRect.x = _fitMarkerFirstRealX-9-_magPosX;
		    //fitMarkerFirstRect.y = _fitMarkerFirstRealY-15-_magPosY;
		    //fitMarkerLastRect.x = _fitMarkerLastRealX-9-_magPosX;
		    //fitMarkerLastRect.y = _fitMarkerLastRealY-15-_magPosY;
		    
		   //System.out.println("marker array size  " + _kinCollectionX.get(_globalFitPosOfSelectedKin).size());
		   // System.out.println("kin array size  " + _markersCollection.size());
		   //System.out.println("_globalFitPosOfSelectedKin  " + _globalFitPosOfSelectedKin);
		   //System.out.println("marker array size _globalFitPosOfSelectedKin " + _markersCollection.get(_globalFitPosOfSelectedKin).size());
		   // System.out.println("marker array 0  " + _markersCollection.get(_globalFitPosOfSelectedKin).get(0));
		   // System.out.println("marker array 1  " + _markersCollection.get(_globalFitPosOfSelectedKin).get(1));
				    
		   try
		   {
			    if(M._kinCollectionX.size()!=0)
			    {
			    	M.fitMarkerFirstRect.x = (M._kinCollectionX.get(M._globalFitPosOfSelectedKin).get(M._markersCollection.get(M._globalFitPosOfSelectedKin).get(0))-M._globalFitXMin)*M._xScaler+Constants._kineticsModeGridPosition.x-M._magPosX-9;
			    	M.fitMarkerFirstRect.y = (M._kinCollectionY.get(M._globalFitPosOfSelectedKin).get(M._markersCollection.get(M._globalFitPosOfSelectedKin).get(0))-M._globalFitYMin)*M._yScaler+Constants._kineticsModeGridPosition.y-M._magPosY-15;
			    	M.fitMarkerLastRect.x = (M._kinCollectionX.get(M._globalFitPosOfSelectedKin).get(M._markersCollection.get(M._globalFitPosOfSelectedKin).get(1))-M._globalFitXMin)*M._xScaler+Constants._kineticsModeGridPosition.x-M._magPosX-9;
			    	M.fitMarkerLastRect.y = (M._kinCollectionY.get(M._globalFitPosOfSelectedKin).get(M._markersCollection.get(M._globalFitPosOfSelectedKin).get(1))-M._globalFitYMin)*M._yScaler+Constants._kineticsModeGridPosition.y-M._magPosY-15;
			    }
		   }
		   catch(ArrayIndexOutOfBoundsException e)
		   {
			   
		   }


		}
		
		
		public Cursor get_cursor()
		{
			return M._cursor;
		}

		
		
		
		
		public void recalcMouseLabel()
		{
			
			M._mouseXLabel = (M._mouseX + M._magPosX - Constants._kineticsModeGridPosition.x)/M._xScaler + M._globalFitXMin;
			M._mouseYLabel = -(M._mouseY + M._magPosY - Constants._kineticsModeGridPosition.y)/M._yScaler - M._globalFitYMin;
		      //_mouseX = (_mouseX + _magPosX - _gridPos.x)/_xScaler + _xMin;
		      String
		      s = "" + M._mouseXLabel;
		      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
		      s = s.substring(0, s.indexOf('.')+5);
		      s = s + "     " + M._mouseYLabel;
		      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
		      s = s.substring(0, s.lastIndexOf('.')+5);
		      if(M._ifShowAverageValue)
		      {
		    	  s = s + "     Average:  " + M._graphAverage;
		    	  if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
		    		  s = s.substring(0, s.lastIndexOf('.')+5);
		      }
		      
		      M._mouseLabelText = s;
			
			
			
			
		}
		
		
		
		
		
		
		
		   public void mouseMoved(MouseEvent e)
		   {
			   M._mouseX = e.getX();
			   M._mouseY = e.getY();
			      calcRectanglesPositions();
			      

			      	int i=0;
			    	   
			      	if(M._ifEnableCurveBounds)
			      	{
			      		if((M.fitMarkerFirstRect.getBounds2D().contains(M._mouseX, M._mouseY)))
				    	   {
			      			M._cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				    		   //SetCursor();
				    		   i=1;
				    		   
				    	   }
				    	   if((M.fitMarkerLastRect.getBounds2D().contains(M._mouseX, M._mouseY))&(i==0))
				    	   {
				    		   M._cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				    		   //SetCursor();
				    		   i=1;
				    		   
				    	   }
			      	}


			    	   if(M.myRect.getBounds2D().contains(M._mouseX, M._mouseY)&(i==0))
			    	   {
			    		   M._cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
			    		   //SetCursor();
			    		   i=1;
			    	   }
			    	   if(M.myZeroRect.getBounds2D().contains(M._mouseX, M._mouseY)&(i==0))
			    	   {
			    		   M._cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			    		   //SetCursor();
			    		  
			    		   i=1;
			    	   }
			    	   
			    	 if(M._ifCalcCurveShifts)
			    	   if(M.myBeforeZeroRect.getBounds2D().contains(M._mouseX, M._mouseY)&(i==0))
			    	   {
			    		   M._cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			    		   //SetCursor();
			    		  
			    		   i=1;
			    	   }

			    	 if(M._ifShowSlice)
			    	   if(M.sliceRect.getBounds2D().contains(M._mouseX, M._mouseY)&(i==0))
			    	   {
			    		   M._cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
			    		   //SetCursor();
			    		  
			    		   i=1;
			    	   }
			    	   
			    	   
			    	   if(i==0)
				      {
			    		   M._cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			    		   //SetCursor();
				      }
			      
				      recalcMouseLabel();
			      
		   }
		   
		   
			   
		   
		   
		   
		   
		   
		   public void mousePressed(MouseEvent e) {
			   M.x = e.getX();
			   M.y = e.getY();
			  
			      if ((e.getButton()==1))
			      { 
			    	  if(((M.myRect.getBounds2D().contains(M.x, M.y))||(M.myZeroRect.getBounds2D().contains(M.x, M.y))||
			    			  (M.myBeforeZeroRect.getBounds2D().contains(M.x, M.y))||(M.fitMarkerFirstRect.getBounds2D().contains(M.x, M.y))||
			    			  (M.sliceRect.getBounds2D().contains(M.x, M.y))||
			    			  (M.fitMarkerLastRect.getBounds2D().contains(M.x, M.y)))&(!M._beingSel)&(M._ifCursorFree))
			    	  {
			    	  
			    		  M._startSel=false;
			    		  M._moveLevel=false;
			    		  M._moveZero=false;
			    		  M._moveBeforeZero=false;
			    		  M._moveSlice=false;
			    		  M._moveFitMarkerFirst=false;
			    		  M._moveFitMarkerLast=false;


				    	   int i=0;
				    	   
					      	if(M._ifEnableCurveBounds)
					      	{
						    	   if((M.fitMarkerFirstRect.getBounds2D().contains(M.x, M.y)))
						    	   {
						    		   M._moveFitMarkerFirst = true;
						    		   i=1;
						    	   }
						    	   
						    	   if((M.fitMarkerLastRect.getBounds2D().contains(M.x, M.y))&(i==0))
						    	   {
						    		   M._moveFitMarkerLast = true;
						    		   i=1;
						    	   }
					      	}

						    if(M._ifShowSlice)
						    	   if(M.sliceRect.getBounds2D().contains(M.x, M.y)&(i==0))
						    	   {
						    		   M._moveSlice=true;
						    		   i=1;
						    	   }

				    	   if(M.myRect.getBounds2D().contains(M.x, M.y)&(i==0))
				    	   {
				    		   M._moveLevel=true;
				    		   i=1;
				    	   }

				    	   if(M.myZeroRect.getBounds2D().contains(M.x, M.y)&(i==0))
				    	   {
				    		   M._moveZero=true;
				    		   i=1;
				    	   }
				    
				    if(M._ifCalcCurveShifts)
				    	   if(M.myBeforeZeroRect.getBounds2D().contains(M.x, M.y)&(i==0))
				    	   {
				    		   M._moveBeforeZero=true;
				    		   i=1;
				    	   }

				    	   if(i==1)M._ifCursorFree = false;
			    	   
			    	  }
			    	  else
			    		  if((M._beingSel==false)&(M._ifCursorFree))
			    		  {
			    			  M.x1=M.x;
			    			  M.y1=M.y;
			    			  M.selectedRect.x = M.x1;
			    			  M.selectedRect.y = M.y1;
			    			  M.x2=M.x;
			    			  M.y2=M.y;
			    			  M._startSel=true;
			    			  M._beingSel=true;
			    			  M._moveLevel=false;
			    			  M._moveZero=false;
			    			  M._moveBeforeZero=false;
			    			  M._moveSlice=false;
			    			  M._moveFitMarkerFirst=false;
			    			  M._moveFitMarkerLast=false;
			    			  M._movingFitMarkerFirst = false;
			    			  M._movingFitMarkerLast = false;


			    			  
			    		  }			      

			      } 
			    	  

			      if(e.getButton()==2)
			      {
			    	  M._moveScreen=true;
			      }

			      if(e.getButton()==3)
			      {
			    	  resetGraphPanelParameters();
			    	  M._ifNeedToRepaint = true;
			      }


		    }

		   
		   public void resetGraphPanelParameters()
		   {
			   M._xScaler = M._xScalerIni;
			   M._yScaler = M._yScalerIni;
			   M._magPosX = 0;
			   M._magPosY = 0;
			   M._beingSel=false;
			   M._ifCursorFree = true;
			   M.selectedRect.x = 0;
			   M.selectedRect.y = 0;
			   M.selectedRect.width = 0;
			   M.selectedRect.height = 0;
			   M._levelPosReal = M.setLevel(M._levelPosDisplay, Constants._kineticsModeGridPosition.y, M._globalFitYMin, M._yScaler);
			   M._zeroPosReal = M.setZero(M._zeroPosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);
			   M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);
			   M._slicePosReal = M.setZero(M._slicePosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);

		   }
		   
		   
		   
		   
		   
		   
		   
		   
		   
		    public void mouseReleased(MouseEvent e) 
		    {
		    	M.x2 = e.getX();
		    	M.y2 = e.getY();
		    	M._ifCursorFree = true;
		    	M._moveFitMarkerFirst = false;
		    	M._moveFitMarkerLast = false;
			      if(M._startSel&M._beingSel)
			      {
			    	  M._startSel=false;
			    	  M._beingSel=false;
			    	  if((M.x2-M.x1)<0){M.c=M.x1;M.x1=M.x2;M.x2=M.c;}
			    	  if((M.y2-M.y1)<0){M.c=M.y1;M.y1=M.y2;M.y2=M.c;}
			    	  
			    	  M.selectedRect.x = M.x1;
			    	  M.selectedRect.y = M.y1;
			    	  M.selectedRect.width = M.x2-M.x1;
			    	  M.selectedRect.height = M.y2-M.y1;
		  
			    	  M._magX1=M.x1;
			    	  M._magX2=M.x2;
			    	  M._magY1=M.y1;
			    	  M._magY2=M.y2;

			    	 // repaint();
				    	  
			      }

			      if(M._moveScreen)
			      {
			    	  M._moveScreen=false;
			    	  M._cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			    	  //SetCursor();
			      }
		    
		    }

		    
		    public void mouseDragged(MouseEvent e) {

		    	
		    	int dx = e.getX() - M.x;
		    	int dy = e.getY() - M.y;
		    	M._mouseX = e.getX();
		    	M._mouseY = e.getY();
		    	recalcMouseLabel();
		    	calcRectanglesPositions();

		     
		    	
			      if(M._startSel&M._beingSel)
			      {
			    	  //_startSel=false;
			    	 // _beingSel=false;
			    	 //if((x2-x1)<0){c=x1;x1=x2;x2=c;}
			    	 //if((y2-y1)<0){c=y1;y1=y2;y2=c;}
			    	  M.x2+=dx;
			    	  M.y2+=dy;
			    	  
			    	  if((M.x2-M.x1)<0)
			    	  {
			    		  M.selectedRect.x = M.x2;
			    	  }
			    	  else
			    		  M.selectedRect.x = M.x1;
			    	  
			    	  if((M.y2-M.y1)<0)
			    	  {
			    		  M.selectedRect.y = M.y2;
			    	  }
			    	  else
			    		  M.selectedRect.y = M.y1;
			    	  
			    	  
			    	  
			    	  M.selectedRect.width = Math.abs(M.x2-M.x1);
			    	  M.selectedRect.height = Math.abs(M.y2-M.y1);
		  
			    	  //_magX1=x1;
			    	  //_magX2=x2;
			    	  //_magY1=y1;
			    	  //_magY2=y2;

			    	 // repaint();
				    	  
			      }

		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		      if ((M.myRect.getBounds2D().contains(M.x, M.y))&(!M._beingSel)&(M._moveLevel)) 
		      {
		    	  M.myRect.y += dy;
		    	  M._levelPosDisplay = M._levelPosDisplay - dy/M._yScaler;
		    	  M._levelPosReal = M.setLevel(M._levelPosDisplay, Constants._kineticsModeGridPosition.y, M._globalFitYMin, M._yScaler);

		      }

		      if ((M.myZeroRect.getBounds2D().contains(M.x, M.y))&(!M._beingSel)&(M._moveZero)) 
		      {
		    	  M.myZeroRect.x = +dx;
		    	  M._zeroPosDisplay = M._zeroPosDisplay + dx/M._xScaler;
		    	  M._zeroPosReal = M.setZero(M._zeroPosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);
		      }

		  if(M._ifCalcCurveShifts)
		      if ((M.myBeforeZeroRect.getBounds2D().contains(M.x, M.y))&(!M._beingSel)&(M._moveBeforeZero)) 
		      {
		    	  M.myBeforeZeroRect.x = +dx;
		    	  M._beforeZeroPosDisplay = M._beforeZeroPosDisplay + dx/M._xScaler;
		    	  M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);
		      }
		      
		  if(M._ifShowSlice)
			  if ((M.sliceRect.getBounds2D().contains(M.x, M.y))&(!M._beingSel)&(M._moveSlice)) 
		      {
		    	  M.sliceRect.x = +dx;
		    	  M._slicePosDisplay = M._slicePosDisplay + dx/M._xScaler;
		    	  M._slicePosReal = M.setZero(M._slicePosDisplay, Constants._kineticsModeGridPosition.x, M._globalFitXMin, M._xScaler);
		    	  calcCurrentTimePoint();
		      }
		      

		      if(M._ifEnableCurveBounds)
		      {
			      if (((M.fitMarkerFirstRect.getBounds2D().contains(M.x, M.y))||(M._moveFitMarkerFirst))&(!M._beingSel)&(!M._moveLevel)&(!M._moveBeforeZero)&(!M._moveSlice)&(!M._moveZero)&(!M._moveFitMarkerLast)) 
			      {
			    	  M._markersCollection.get(M._globalFitPosOfSelectedKin).set(0, M.findMarkerArrayPos(M._mouseX));
			    	  //checkmarkers();
			      }
			      
			      
			      if (((M.fitMarkerLastRect.getBounds2D().contains(M.x, M.y))||(M._moveFitMarkerLast))&(!M._beingSel)&(!M._moveLevel)&(!M._moveBeforeZero)&(!M._moveSlice)&(!M._moveZero)&(!M._moveFitMarkerFirst)) 
			      {
			    	  M._markersCollection.get(M._globalFitPosOfSelectedKin).set(1, M.findMarkerArrayPos(M._mouseX));
			    	  //checkmarkers();
			      }
		      }

				
		      
		      if(M._moveScreen)
		      {
		    	  M._magPosX -= dx;
		    	M._magPosY -= dy;
	    	    
		    	M._cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
	    	    //SetCursor();

		      }
		      
		      
		      M.x += dx;
		      M.y += dy;
		    
		      
		    }	
	

	
	
	public void checkmarkers()
	{
		boolean _result = true;
		for(int w=0; w<M._availWaveCollection.size(); w++)
		{
			if((M._markersCollection.get(w).get(0)>M._markersCollection.get(w).get(1))||(M._kinCollectionX.get(w).get(M._markersCollection.get(w).get(0))<M._zeroPosDisplay))
			{
				_result = false;
			}
		}
		
		M._ifStatusFine = _result;

			//System.out.println(_ifStatusFine);
	}
	
	

	
	public void calcCurrentTimePoint()
	{
		
		if(M._kinCollectionX.size()>1)
		{
			int _result = -1;
			float _interval = (M._kinCollectionX.get(0).get(5)-M._kinCollectionX.get(0).get(4))/2;
			
			for(int i=1; i<(M._kinCollectionX.get(0).size()-1);i++)
			{
				if((M._slicePosDisplay>=(M._kinCollectionX.get(0).get(i)-_interval))&(M._slicePosDisplay<(M._kinCollectionX.get(0).get(i+1)-_interval)))
				{
					_result = i;
				}
			}

			M._globalFitPosOfCurrentTimePoint = _result;
			
			//System.out.println("Pos of closest = " + _result);
			//System.out.println("Value = " + _kinCollectionY.get(0).get(_result));
		}
	
	}
	
	
	
}
