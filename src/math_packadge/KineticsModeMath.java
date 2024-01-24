package math_packadge;

import interface_packadge.Constants;

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


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


public class KineticsModeMath extends JFrame{

	public File file, newFile;
	public File saveName, openName;
	public boolean _saveNameEmpty = true;
	public boolean _ifShowCreatedCurve = false;
	public Color _fittingColor = Color.red;
	
	public boolean _if1expD0CheckBoxSelected = false;
	public boolean _if1expACheckBoxSelected = false;
	public boolean _if1expKCheckBoxSelected = false;
	public boolean _if2OrderD0CheckBoxSelected = false;
	public boolean _if2OrderACheckBoxSelected = false;
	public boolean _if2OrderKCheckBoxSelected = false;
	public boolean _ifFittingSuccessful = true;
	//public boolean _showDefaultInteface = true;
	
	
	public String _fittingMode = "None";
	
	//boolean _markersAlarm = false;
	
	public float[] _xArray;
	public float[] _yArray;
	public float[] _xArrayError;
	public float[] _yArrayError;
	public float[] _xArrayIni;
	public float[] _yArrayIni;
	public float[] _tempArrayX;
	public float[] _tempArrayY;
	public float[] _tempArrayXIni;
	public float[] _tempArrayYIni;
	public ArrayList<float[]> _createdCurveArray, _createdCurveArrayError;
	public ArrayList<Integer> _logArray, _colorArray;
	public int _nOfCreatedCurvePoints, _nOfCreatedCurvePointsError;
	public float _xMax=-10,_xMin=10,_yMax=-10,_yMin=10,_xScaler,_yScaler, _yZeroPos=0f,_xZeroPos=0f,_xScalerIni,_yScalerIni;
	public float _xMaxError=-10,_xMinError=10,_yMaxError=-10,_yMinError=10,_xScalerError,_yScalerError, _yZeroPosError=0f,_xZeroPosError=0f,_xScalerIniError,_yScalerIniError;
	public int _arraySize=0, _trimmedArraySize=0;
	public int _gridSizeX, _gridSizeY, _gridSizeXError, _gridSizeYError;
	public float _magX1=0,_magY1=0, _magX2=Constants._kineticsModeGridPosition.x+_gridSizeX, _magY2=Constants._kineticsModeGridPosition.y+_gridSizeY;
	public float _magPosX = 0, _magPosY = 0, _magPosXError = 0, _magPosYError = 0;
	public int _size = 100000;
	public float _fitMarkerFirstRealX = -100, _fitMarkerFirstRealY = -100, _fitMarkerLastRealX = -100, _fitMarkerLastRealY = -100;
	public float _fitMarkerFirstDisplayX = 0, _fitMarkerFirstDisplayY = 0, _fitMarkerLastDisplayX = 0, _fitMarkerLastDisplayY = 0;
	public int _fitMarkerFirstPos = 1, _fitMarkerLastPos = 1;
	public int _markerShiftStep = 5, _markerShiftStepFast = 100;
	public float _zeroPosReal = 0f, _zeroPosDisplay=0;
	public float _beforeZeroPosReal = 0f, _beforeZeroPosDisplay=0;
	public float _levelPosReal = 0f, _levelPosDisplay=0f, _gridResolXIni = 1f, _gridResolYIni = 1f, _gridResolX = 1f, _gridResolY = 1f;
	public int _saveEvery=1;
	public float _zeroShiftStep = 1000, _zeroShiftStepFast = 10000, _levelShiftStep = 0.001f, _levelShiftStepFast = 0.1f;
	public int _nOfSmoothPoints = 0, _nOfVertSmoothPoints = 1, _posOfSelectedSpectrum = 1, _posFirstMarker = 0, _posLastMarker = 0,_rememberPosFirstMarker = 0, _rememberPosLastMarker = 0;
	public float _A, _k, _D0;
	public float _a10, _a20, _a30;
	public float _sigmaA2 = 0, _sigma1=0, _sigma2=0, _sigma3=0, _sigmaSum=0;
	
	
	public Rectangle2D.Float myRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x, _levelPosReal, _gridSizeX, 10);
	public Rectangle2D.Float myZeroRect = new Rectangle2D.Float(_zeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	public Rectangle2D.Float selectedRect = new Rectangle2D.Float(0,0,0,0);
	public Rectangle2D.Float myBeforeZeroRect = new Rectangle2D.Float(_beforeZeroPosReal,Constants._kineticsModeGridPosition.y, 10, _gridSizeY);
	public Rectangle2D.Float beforeZeroSelRect = new Rectangle2D.Float(Constants._kineticsModeGridPosition.x,Constants._kineticsModeGridPosition.y,10,_gridSizeY);
	public Rectangle2D.Float fitMarkerFirstRect = new Rectangle2D.Float(0,0,13,35);
	public Rectangle2D.Float fitMarkerLastRect = new Rectangle2D.Float(0,0,13,35);
	
	
	//mouse proccessing
	public float _mouseX, _mouseY;
	public String _mouseLabelText;
	public boolean _ifShowAverageValue=false;
	public boolean _ifNeedToRepaint=false;
	public float _graphAverage=0;
	private int x,y,x1,y1,x2,y2,c;
	boolean _startSel = false, _beingSel=false, _moveScreen=false, _moveLevel = false, _moveZero=false, _moveBeforeZero=false, _moveFitMarkerFirst = false, _moveFitMarkerLast = false,
	   			_movingFitMarkerFirst = false, _movingFitMarkerLast = false, _ifCursorFree = true;

	Cursor _cursor;
	
	
	public KineticsModeMath()
	{
		
	}
	
	
	
	public String openFile() 
	{
	    String s="No file",s1="TimeSeries";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Data files", "txt", "TimeSeries", "asc");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(file);
	    int returnVal = chooser.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	       file = chooser.getSelectedFile();
	       
	       if(_saveNameEmpty)
	       {
	    	   _saveNameEmpty = false;
	    	   saveName = file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=file.getName();
	       s1 = s.substring(s.lastIndexOf('.')+1);
	     	       
	    		   charSeq = "TimeSeries";
	    		   charSeq1 = "txt";
	    		   charSeq2 = "asc";
	    		   if((s.contains(charSeq))||(s.contains(charSeq1))||(s.contains(charSeq2)))
	    		   {
		    		   System.out.println("Open file .TimeSeries");
		    		   
		    		   processTimeSeriesFile(file);
		    		   //_graphPanel.repaint();
		    		   
	    		   }
	    		   else
	    		   {
	    			   JOptionPane.showMessageDialog(this, "Select diferent mode!");
	    		   }
	    		   
    
	    		   
	    	   }
	    return s;
	    }
	
	
	
	
	
	
	public void processTimeSeriesFile(File file) //fills the arrays with data from file
	{

		_xArrayIni = new float[_size];
		_yArrayIni = new float[_size];
		_xArray = new float[_size];
		_yArray = new float[_size];
		_arraySize = 0;
		_xMin=100000;
		_xMax=-100000;
		_yMin=100000;
		_yMax=-100000;
		_magPosX=0;
		_magPosY=0;
		
		int _lc = 0, extent1;
		String s,s1,s2,s3;
		float _xPoint=0, _yPoint = 0;
		String[] values;		

		System.out.println("Start reading the .TimeSeries File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			
			String line;
			String fileType="raw";
			
			
			while ((line = in.readLine()) != null)
			{ 
				_lc++; // Line counter

				// Identifying the file type
				values = line.split(" ");		//split the string

				if (_lc==1)
				{
					values = line.split(" ");		//split the string
					s=line.substring(0,5);
					//System.out.println(s);
					CharSequence _charSeq = "Start";

					if(s.contains(_charSeq))
					{
						System.out.println("The file was identified as 'raw TimeSeries'");
						fileType = "raw";
					}
					else
					{
						System.out.println("The file was identified as 'modified'");
						fileType = "modified";
					}
						
						s = file.getName();
						s = s.substring(s.lastIndexOf('.')+1);
						_charSeq = "asc";

						if(s.contains(_charSeq))
						{
							System.out.println("The file was identified as 'asc'");
							fileType = "asc";
						}

				
				}
				
				
				if(fileType=="raw")
				{
					if(_lc<=5)continue; // Need lines after the 5th
					
					_arraySize++;
					values = line.split("	");		//split the string
					s = values[1];	
					s1 = s.substring(0, s.indexOf('E')-1); //Remove the part after E
					s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
					extent1 = Integer.parseInt(s2);
					s3 = values[2].replace(',','.');					
					_xPoint = (float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					_yPoint = -Float.parseFloat(s3);
					_xArrayIni[_lc-5]= _xPoint;
					_yArrayIni[_lc-5] = _yPoint;
					if(_xMax<_xPoint){_xMax=_xPoint;}
					if(_yMax<_yPoint){_yMax=_yPoint;}
					if(_xMin>_xPoint){_xMin=_xPoint;}
					if(_yMin>_yPoint){_yMin=_yPoint;}
				}
				

				if(fileType=="modified")
				{
					_arraySize++;
					if(line.indexOf("	")!=-1)
					{
						values = line.split("	");		//split the string
					}
					else
					{
						values = line.split(" ");		//split the string
					}

					s = values[0];	
					if(s.indexOf('E')!=-1)
					{
						s1 = s.substring(0, s.indexOf('E')-1); //Remove the part after E
						s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
						extent1 = Integer.parseInt(s2);
						_xPoint = (float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					}
					else
					{
						s = values[0].replace(',','.');	
						_xPoint = Float.parseFloat(s);						
					}
					
									
					s = values[1];	
					if(s.indexOf('E')!=-1)
					{
						s1 = s.substring(0, s.indexOf('E')-1); //Remove the part after E
						s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
						extent1 = Integer.parseInt(s2);
						_yPoint = -(float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					}
					else
					{
						s = values[1].replace(',','.');	
						_yPoint = -Float.parseFloat(s);						
					}

					_xArrayIni[_lc]= _xPoint;
					_yArrayIni[_lc] = _yPoint;
					if(_xMax<_xPoint){_xMax=_xPoint;}
					if(_yMax<_yPoint){_yMax=_yPoint;}
					if(_xMin>_xPoint){_xMin=_xPoint;}
					if(_yMin>_yPoint){_yMin=_yPoint;}
				}
			
			
				if(fileType=="asc")
				{
					_arraySize++;
					values = line.split(" ");		//split the string
					//System.out.println("0: " + values[0] +" 1: " + values[1] +" 2: " + values[2]);

					s = values[1];	

					if(s.indexOf('E')!=-1)
					{
						s1 = s.substring(0, s.indexOf('E')); //Remove the part after E
						//System.out.println("s1 = " + s1);
						if(s1.equals("0.0000"))
							s1="0";
						s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
						//System.out.println("s2 = " + s2);
						if(s2.indexOf("+")!=-1)
							s2 = s2.substring(1, s2.length());
						if((s2.equals("+0000"))||(s2.equals("0000")))
							s2="0";
						extent1 = Integer.parseInt(s2);
						_xPoint = (float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					}
					else
					{
						s = values[1].replace(',','.');	
						_xPoint = Float.parseFloat(s);						
					}
					
									
					int pos =2;
					if(values[2].length()==0)pos=3;
					
					s = values[pos];	
					if(s.indexOf('E')!=-1)
					{
						s1 = s.substring(0, s.indexOf('E')); //Remove the part after E
						if(s1.equals("0.0000"))
							s1="0";
						s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
						if(s2.indexOf("+")!=-1)
							s2 = s2.substring(1, s2.length());
						if((s2.equals("+0000"))||(s2.equals("0000")))
							s2="0";
						extent1 = Integer.parseInt(s2);
						_yPoint = -(float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					}
					else
					{
						s = values[pos].replace(',','.');	
						_yPoint = -Float.parseFloat(s);						
					}


					_xArrayIni[_lc]= _xPoint;
					_yArrayIni[_lc] = _yPoint;
					if(_xMax<_xPoint){_xMax=_xPoint;}
					if(_yMax<_yPoint){_yMax=_yPoint;}
					if(_xMin>_xPoint){_xMin=_xPoint;}
					if(_yMin>_yPoint){_yMin=_yPoint;}
				}
			

				
					
				
				
				
				
				
				
				
			
			
			}
			in.close(); //closing files

			
			_xScaler = _gridSizeX/(_xMax-_xMin);
			_yScaler = _gridSizeY/(_yMax-_yMin);
			_xScalerIni = _xScaler;
			_yScalerIni = _yScaler;
			
			_markerShiftStep = Math.round(_arraySize/300);
			_markerShiftStepFast = Math.round(_arraySize/20);
			
			_zeroShiftStep = (_xMax-_xMin)/500;
			_zeroShiftStepFast = Math.round((_xMax-_xMin)/20);

			
			for(int i=1;i<=(_arraySize); i++)
			 {
				 _xArray[i]=_xArrayIni[i]; 
				 _yArray[i]=_yArrayIni[i]; 
				 _xArray[i]=_xArray[i]-_xMin; 
				 _yArray[i]= _yArray[i]-_yMin;
			 }
			
			 _levelPosDisplay = 0f;
			 _zeroPosDisplay = 0f;
			 _levelPosReal = setLevel(_levelPosDisplay,Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
			 _zeroPosReal = setZero(_zeroPosDisplay,Constants._kineticsModeGridPosition.x,_xMin,_xScaler);

			 myRect.y = _levelPosReal-5-_magPosY;
			 myZeroRect.x = _zeroPosReal-5-_magPosX;
			 
			 
			 _yZeroPos = Math.round(_levelPosReal);
			 _xZeroPos = Math.round(_zeroPosReal);
			 
			 
			 _fitMarkerFirstPos = 1;
			 _fitMarkerLastPos = _arraySize-5;
			 
			 recalcFitMarkerFirst(false);
			 recalcFitMarkerLast(false);
	    	 //checkFitMarkerPos();
			 
			 
			 
			 
			System.out.println("The file was converted into an array");

		
	
		
		
		} catch (IOException e) 
		
		{
			
			//JOptionPane.showConfirmDialog(this,"Can't open the file.");

		} 
		
		
	}
	
	
	public void setDefaultGrid() //fills the arrays with data from file
	{
		_xMin=-2;
		_xMax=10;
		_yMin=-2;
		_yMax=10;
		_magPosX=0;
		_magPosY=0;
		_xArray = new float[5];
		_yArray = new float[5];
		
			_arraySize=3;
			
			_xArray[1]= -2;
			_yArray[1]= -2;
			_xArray[2]= 10;
			_yArray[2]= 10;
			_xScaler = _gridSizeX/(_xMax-_xMin);
			_yScaler = _gridSizeY/(_yMax-_yMin);
			_xScalerIni = _xScaler;
			_yScalerIni = _yScaler;
			
			 _levelPosDisplay = 0f;
			 _zeroPosDisplay = 0f;
			 _levelPosReal = setLevel(_levelPosDisplay,Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
			 _zeroPosReal = setZero(_zeroPosDisplay,Constants._kineticsModeGridPosition.x,_xMin,_xScaler);

			 myRect.y = _levelPosReal-5-_magPosY;
			 myZeroRect.x = _zeroPosReal-5-_magPosX;
			 
			 
			 _yZeroPos = Math.round(_levelPosReal);
			 _xZeroPos = Math.round(_zeroPosReal);
		
	}
	
	
	
	
	
	
	public String convertAndSaveKinetics() 
	{	

		//int _lc = 0, extent1;
		String newName="";
		//float number = 0;
		//String[] values;		
		

		String _filePath = file.getPath();
		newName = _filePath.subSequence(0, _filePath.lastIndexOf('.'))+ "_converted.txt";

        File saveFile = new File(newName);
        JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt, asc", "txt", "asc");
	    chooser.setFileFilter(filter);
        chooser.setSelectedFile(saveFile);
        chooser.setCurrentDirectory(saveName);
        int rval = chooser.showSaveDialog(this);
        
        
        
        if ((rval == JFileChooser.APPROVE_OPTION)) 
        {
            int reply = JOptionPane.YES_OPTION;
        	saveFile = chooser.getSelectedFile();
            if(saveFile.exists())
            {
                String message = "The file already exists! Overwrite?";
                String title = "Warning!";
            	reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                
            }
                
            if (reply == JOptionPane.YES_OPTION)
            {
                newName = saveFile.getPath();
                saveName = saveFile;
               //file = saveFile;
        		System.out.println("New Path is: " + newName);
               
                
    		
    		try 
    			{ 
    				boolean check = false;
    				if(newName.substring(newName.length()-3, newName.length()).equals("txt"))
    					{
    						
    						check = true;
    						
    						BufferedReader in = new BufferedReader(new FileReader(file));
    						BufferedWriter out = new BufferedWriter(new FileWriter(newName));

    						System.out.println("Saving...");
    						String line;
    						int counter = _saveEvery-1;
    						float _yValue,_xValue;
    						 for(int i=1;i<=(_arraySize); i++)
    						 {
    								_xValue = _xArrayIni[i]-_zeroPosDisplay;	
    								_yValue = -_yArrayIni[i]-_levelPosDisplay;	
    							line = "" + _xValue + "	" + _yValue; // Compile a new line

    							
    							
    							if(_saveEvery>1)
    								{
    									counter++;
    									if(counter == _saveEvery)
    									{
    										counter=0;
    										out.write(line);
    										out.newLine();

    									}	
    								}
    							else
    							{
    								out.write(line);
    								out.newLine();
    							}
    								
    						 }
    							in.close(); //closing files
    							out.close();
    							
    							System.out.println("Saved as txt.");
    						//write status

    					}
    				
    				if(newName.substring(newName.length()-3, newName.length()).equals("asc"))
    				{
    					check = true;
    					BufferedReader in = new BufferedReader(new FileReader(file));
    					BufferedWriter out = new BufferedWriter(new FileWriter(newName));

    					System.out.println("Saving...");
    					String lineX, lineY;
    					int counter = _saveEvery-1;
    					float _yValue,_xValue;
    					 for(int i=1;i<=(_arraySize); i++)
    					 {
    							_xValue = _xArrayIni[i]-_zeroPosDisplay;	
    							_yValue = -_yArrayIni[i]-_levelPosDisplay;	
    						lineX = " " + _xValue;
    						if(lineX.indexOf('E')==-1)
    						{
    							lineX+= "E+0000 ";
    						}
    						else
    						{
    							lineX+= " ";
    						}
    						
    						lineY = "" + _yValue;
    						if(lineY.indexOf('E')==-1)
    						{
    							lineY+= "E+0000 ";
    						}

    						lineX += lineY;
    						
    						if(_saveEvery>1)
    							{
    								counter++;
    								if(counter == _saveEvery)
    								{
    									counter=0;
    									out.write(lineX);
    									out.newLine();

    								}	
    							}
    						else
    						{
    							out.write(lineX);
    							out.newLine();
    						}
    							
    					 }
    						in.close(); //closing files
    						out.close();

    					//write status
    						System.out.println("Saved as asc.");
    				}
    				
    				
    				if(!check)
    				{
    					JOptionPane.showMessageDialog(this, "Can't save kinetics! Change format!");
    				}

    			
    			} catch (IOException e) { } 
            }
        

			
        }
        
        return newName;
	}
	
	
	
	
	
	
	public float setLevel(float _levelPosDisplay, int _gridPosY, float _yMin, float _yScaler)
	{
		return _gridPosY - (_yMin+_levelPosDisplay)*_yScaler;
	}
	
	
	
	
	public float setZero(float _zeroPosDisplay, int _gridPosX, float _xMin, float _xScaler)
	{
		return _gridPosX - (_xMin-_zeroPosDisplay)*_xScaler;
	}
	
	
	public void recalcFitMarkerFirst(boolean _moveOnlyY)
	{

			if(!_moveOnlyY)
			{
			_fitMarkerFirstDisplayX = _xArrayIni[_fitMarkerFirstPos];
			 _fitMarkerFirstRealX = setZero(_xArrayIni[_fitMarkerFirstPos],Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
			}

			 _fitMarkerFirstDisplayY = _yArrayIni[_fitMarkerFirstPos];
			 _fitMarkerFirstRealY = setLevel(-_yArrayIni[_fitMarkerFirstPos],Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
	}
	
	
	
	
	public boolean checkFitMarkerPos()
	{

			if((_fitMarkerFirstDisplayX<_fitMarkerLastDisplayX)&(_fitMarkerFirstDisplayX>_zeroPosDisplay)&(_fitMarkerLastDisplayX>_zeroPosDisplay))
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	
	
	
	public void recalcFitMarkerLast(boolean _moveOnlyY)
	{
			if(!_moveOnlyY)
			{
			_fitMarkerLastDisplayX = _xArrayIni[_fitMarkerLastPos];
			_fitMarkerLastRealX = setZero(_xArrayIni[_fitMarkerLastPos],Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
			}
				
			
			 _fitMarkerLastDisplayY = _yArrayIni[_fitMarkerLastPos];
			 _fitMarkerLastRealY = setLevel(-_yArrayIni[_fitMarkerLastPos],Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
	}
	
	
	
	
	
	
	public void getSnapShot(BufferedImage off_Image)
	{
		
		String format = "gif";
        File saveFile = new File("savedimage."+format);
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(saveFile);
        int rval = chooser.showSaveDialog(this);
        if (rval == JFileChooser.APPROVE_OPTION) {
            saveFile = chooser.getSelectedFile();
            /* Write the filtered image in the selected format,
             * to the file chosen by the user.
             */
            try {
                ImageIO.write(off_Image, format, saveFile);
            } catch (IOException ex) {}
            
        }
	}
	
	
	
	public void shiftLevel(float shift)
	{
		_levelPosDisplay = _levelPosDisplay + shift;
		_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y, _yMin, _yScaler);
		myRect.y = _levelPosReal-5;
	}
	
	

	public void shiftZero(float shift)
	{
		_zeroPosDisplay = _zeroPosDisplay + shift;
		_zeroPosReal = setZero(_zeroPosDisplay, Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
		myZeroRect.x = _zeroPosReal-5;
		
	}
	
	
	
	
	
	
	public void setLabelText(JLabel _label, float _a, int _nPoints)
	{
		
		String s, s1="";

		s = "" + _a;
		if(s.indexOf('E')!=-1)
			s1 = s.substring(s.lastIndexOf('E'));
		
		if((( s.length() -s.indexOf('.'))>=(_nPoints+1)))
		s = s.substring(0,s.indexOf('.')+_nPoints+1);
		_label.setText((s+s1));
		
	}
	
	public void setTextFieldText(JTextField _label, float _a, int _nPoints)
	{
		
		String s, s1="";

		s = "" + _a;
		if(s.indexOf('E')!=-1)
			s1 = s.substring(s.lastIndexOf('E'));
		
		if((( s.length() -s.indexOf('.'))>=(_nPoints+1)))
		s = s.substring(0,s.indexOf('.')+_nPoints+1);
		_label.setText((s+s1));
		
	}
	
	
	
	
	
	public void centerLines()
	{
			_zeroPosDisplay = _xArrayIni[Math.round(_arraySize/2)];
			_levelPosDisplay = -_yArrayIni[Math.round(_arraySize/2)];
			_zeroPosReal = setZero(_zeroPosDisplay,Constants._kineticsModeGridPosition.x,_xMin,_xScaler);
			_levelPosReal = setLevel(_levelPosDisplay,Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
	}
	
	
	
	
	 public void magnifyGraph()
	 {
		 
		 float _sX,_sY;
			//_graphPanel._gridResol = _graphPanel._gridResol*2;
			_sX = _gridSizeX/(_magX2-_magX1);
			_sY = _gridSizeY/(_magY2-_magY1);
			_xScaler = _xScaler*_sX;
			_yScaler = _yScaler*_sY;

			_magPosX = _magPosX*_sX+(_magX1-Constants._kineticsModeGridPosition.x)*_sX;//_graphPanel._xScaler/_graphPanel._xScalerIni;
			_magPosY = _magPosY*_sY+(_magY1-Constants._kineticsModeGridPosition.y)*_sY;//_graphPanel._yScaler/_graphPanel._yScalerIni;

			
			selectedRect.x = 0;
			selectedRect.y = 0;
			selectedRect.width = 0;
			selectedRect.height = 0;
			shiftLevel(0);
			shiftZero(0);
			//shiftBeforeZero(0);
			shiftFitMarkerFirst(0);
			shiftFitMarkerLast(0);
	 
	 }
	
	
	 
		public void trimGraph()
		{
			float _pointX, _pointY;
				//_graphPanel._tempArrayX = new float[10000];
				//_graphPanel._tempArrayY = new float[10000];
				_tempArrayXIni = new float[_size];
				_tempArrayYIni = new float[_size];
			    			
			_trimmedArraySize = 0;
			_xMin=10000000;
			_xMax=-10000000;
			_yMin=10000000;
			_yMax=-10000000;
			
			
	//Fill the temp Array
			for(int i=1; i<=_arraySize; i++)
			{
					_pointX = _xArray[i]*_xScaler+Constants._kineticsModeGridPosition.x-_magPosX;
					_pointY = _yArray[i]*_yScaler+Constants._kineticsModeGridPosition.y-_magPosY;
				    				if((_pointX>(Constants._kineticsModeGridPosition.x-5))&(_pointX<(Constants._kineticsModeGridPosition.x+_gridSizeX+5))
				    						&(_pointY>Constants._kineticsModeGridPosition.y-5)&(_pointY<(Constants._kineticsModeGridPosition.y+_gridSizeY+5)))
				    				{
				    					_trimmedArraySize++;
				    					_tempArrayXIni[_trimmedArraySize]=_xArrayIni[i];					
				    					_tempArrayYIni[_trimmedArraySize]=_yArrayIni[i];					

				    					if(_xMax<_xArrayIni[i]){_xMax=_xArrayIni[i];}
				    					if(_yMax<_yArrayIni[i]){_yMax=_yArrayIni[i];}
				    					if(_xMin>_xArrayIni[i]){_xMin=_xArrayIni[i];}
				    					if(_yMin>_yArrayIni[i]){_yMin=_yArrayIni[i];}
				    					
				    				}
	//Clean the working arrays       			    
				        _xArray[i]=0;
				        _yArray[i]=0;
				       	_xArrayIni[i]=0;
				       	_yArrayIni[i]=0;
				      		       			  
			}
			_arraySize=_trimmedArraySize;
			_markerShiftStep = Math.round(_arraySize/300);
			_markerShiftStepFast = Math.round(_arraySize/20);

			


			_xScaler = _gridSizeX/(_xMax-_xMin);
			_yScaler = _gridSizeY/(_yMax-_yMin);
			_xScalerIni = _xScaler;
			_yScalerIni = _yScaler;

			_magPosX = 0;
			_magPosY = 0;
			
			_levelPosReal = setLevel(_levelPosDisplay,Constants._kineticsModeGridPosition.y,_yMin,_yScaler);
			_zeroPosReal = setZero(_zeroPosDisplay,Constants._kineticsModeGridPosition.x,_xMin,_xScaler);

			myRect.y = _levelPosReal-5-_magPosY;
			myZeroRect.x = _zeroPosReal-5-_magPosX;
			myBeforeZeroRect.x = _beforeZeroPosReal-5-_magPosX;
			_yZeroPos = Math.round(_levelPosReal)-1;
			_xZeroPos = Math.round(_zeroPosReal);
			
			
			
			
			
			
			
			
	//Refill the working arrays   			
			
			for(int i=1; i<=_arraySize; i++)
		{
			    _xArrayIni[i]=_tempArrayXIni[i];
			    _yArrayIni[i]=_tempArrayYIni[i];
			    _xArray[i]= _tempArrayXIni[i]-_xMin;
			    _yArray[i]= _tempArrayYIni[i]-_yMin;
		}	
			

			
			  if(_fitMarkerFirstPos>_arraySize)_fitMarkerFirstPos=_arraySize-2;
			  if(_fitMarkerLastPos>_arraySize)_fitMarkerLastPos=_arraySize-2;
			  _fitMarkerFirstPos = 3;
			  
			  recalcFitMarkerFirst(false);
			  recalcFitMarkerLast(false);
			


			  
			System.out.println("Trimming done");
		
		}
		
		
		
		
		public void smoothOneKineticCurve()
		{

			_tempArrayYIni = new float[_size];

			for(int i=1; i<=_arraySize; i++)
			{
				   _tempArrayYIni[i]= _yArrayIni[i];
			}	

			
			int count = 0;
			if(_nOfSmoothPoints!=0)
			for(int k=_fitMarkerFirstPos+1; k<_fitMarkerLastPos; k++)
			{

				float sum=0;
				count = 0;
				
				for(int j=-_nOfSmoothPoints; j<=_nOfSmoothPoints; j++)
				{
					if(((k+j)<_fitMarkerLastPos)&((k+j)>(_fitMarkerFirstPos)))
						{
							sum = sum + _yArrayIni[k+j];
							count++;
						}
					
				}
				_tempArrayYIni[k]=sum/count;

			}

			for(int i=1; i<=_arraySize; i++)
			{
				   _yArrayIni[i]=_tempArrayYIni[i];
					_yArray[i]= _yArrayIni[i]-_yMin;
			}	
			

			System.out.println("Smoothing with " + count + " points finished.");	
				

			
		}
		
		
		
		public void calcAverage()
		{

			float sum=0;
			int counter=0;

			_ifShowAverageValue = true;
			

			//System.out.println("Calculating the average value...");
			
			//N = _fitMarkerLastPos- _fitMarkerFirstPos ;
			_nOfCreatedCurvePoints = 2;
			
			_createdCurveArray = new ArrayList<float[]>();
			
			float[] _tempArrayX = new float[2];
			float[] _tempArrayY = new float[2];
			
			
			
			for(int i=_fitMarkerFirstPos; i<=_fitMarkerLastPos; i++)
			{
		 
				sum = sum + _yArrayIni[i];
				counter++;

			}
			
			sum = sum/counter;
			_graphAverage = -sum;
			
			
			_tempArrayX[0] = _xArrayIni[_fitMarkerFirstPos]-_xMin;
			_tempArrayX[1] = _xArrayIni[_fitMarkerLastPos]-_xMin;
			_tempArrayY[0] = sum-_yMin;
			_tempArrayY[1] = sum-_yMin;

			_createdCurveArray.add(_tempArrayX);
			_createdCurveArray.add(_tempArrayY);
			
			_fittingColor = Color.magenta;
			

			recalcMouseLabel();
			
			
		}
		
		
		public void shiftNSmoothKinPoints(int shift)
		{
			
			_nOfSmoothPoints = _nOfSmoothPoints + shift;
			if(_nOfSmoothPoints<0){_nOfSmoothPoints=0;}
		}
		
		
		
		
		
	
	
	
		public void shiftFitMarkerFirst(int shift)
		{
		  _fitMarkerFirstPos = _fitMarkerFirstPos + shift;
		  if(_fitMarkerFirstPos<1)_fitMarkerFirstPos=1;
		  if(_fitMarkerFirstPos>_arraySize)_fitMarkerFirstPos=_arraySize;
		  _rememberPosFirstMarker = _fitMarkerFirstPos;
		  recalcFitMarkerFirst(false);
		}
		
		public void shiftFitMarkerLast(int shift)
		{
			  _fitMarkerLastPos = _fitMarkerLastPos + shift;
			  if(_fitMarkerLastPos<1)_fitMarkerLastPos=1;
			  if(_fitMarkerLastPos>_arraySize)_fitMarkerLastPos=_arraySize;
			  _rememberPosLastMarker = _fitMarkerLastPos;
			  recalcFitMarkerLast(false);
		}
		
	
	
		public int findMarkerArrayPos(float displayX)
		{
			int index=1,counter = 0;
			
			for(int i=2; i<_xArrayIni.length; i++)
			{
				if((displayX>_xArrayIni[i-1])&(displayX<=_xArrayIni[i]))
					{
					index = i;
					counter = 1;
					}
				
			}
			if(counter==0)index=-1;
			if(displayX<_xArrayIni[1])index=1;

			return index;
			
		}
	
	
		
		
		
		public void fillCreatedCurveArray()
		{
			_createdCurveArray = new ArrayList<float[]>();
			
			int N = _fitMarkerLastPos- _fitMarkerFirstPos;
			_nOfCreatedCurvePoints = _arraySize;


			float step;
			
			if(_xArray.length>10){step = _xArray[11]-_xArray[10];}
			else{step = 1;}

			if(_fittingMode=="1exp Fit")
			{
				float[] _tempArrayX = new float[_nOfCreatedCurvePoints];
				float[] _tempArrayY = new float[_nOfCreatedCurvePoints];
				
				float _xStart = _zeroPosDisplay;

				
				for(int i=0; i<_nOfCreatedCurvePoints; i++)
				{
					_tempArrayX[i] = _xStart+step*(i)-_xMin;
					_tempArrayY[i] = -_levelPosDisplay - (float)(_A*Math.pow(Math.E,(-_k*step*i)))-_yMin;
	
				}


				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);
	
			
			}
			

			if(_fittingMode=="2Order Fit")
			{
				float[] _tempArrayX = new float[_nOfCreatedCurvePoints];
				float[] _tempArrayY = new float[_nOfCreatedCurvePoints];
				
				float _xStart = _zeroPosDisplay;

				for(int i=0; i<_nOfCreatedCurvePoints; i++)
				{
					_tempArrayX[i] = _xStart+step*(i)-_xMin;
					_tempArrayY[i] = -_levelPosDisplay - _A/(1 + step*i*_k)-_yMin;
					

				}


				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);

			}
			
			
			
			if(_fittingMode=="2Order Fit Product")
			{
				float[] _tempArrayX = new float[_nOfCreatedCurvePoints];
				float[] _tempArrayY = new float[_nOfCreatedCurvePoints];
				
				float _xStart = _zeroPosDisplay;

				for(int i=0; i<_nOfCreatedCurvePoints; i++)
				{
					_tempArrayX[i] = _xStart+step*(i)-_xMin;
					_tempArrayY[i] = -_levelPosDisplay + (-_D0 - _A*_k*step*i)/(1 + step*i*_k)-_yMin;
					

				}


				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);

			}
			
			
			
			
			
			
			
			
			System.out.println("Curve has been created...");

			_fittingColor = Color.red;
			

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		public void firstOrderFitting()
		{
			float da1, da2, da3;
			float a1, a2, a3;
			float a11=0, a12=0, a13=0, a21=0, a22=0, a23=0, a31=0, a32=0, a33=0;
			float b11, b12, b13, b21, b22, b23, b31, b32, b33;
			float z1=0, z2=0, z3=0;
			float A=0, B=0, C=0;
			float Fi0;
			int N, p;
			//float d = 0.01f;
			float det;
			float _checkSum;
			float e = 1000f;

			
			
			N = _fitMarkerLastPos- _fitMarkerFirstPos;
			
			_a10 = _levelPosDisplay;
			_a20 = _A;
			_a30 = _k;
			
			System.out.println("First Order Fitting Started...");
			

			_nOfCreatedCurvePoints = N;
			_nOfCreatedCurvePointsError = N;

			_xScalerError = _xScaler;
			_yScalerError = _yScaler;
			
			for(int j=1; j<100; j++)
			{

				_createdCurveArray = new ArrayList<float[]>();
				_createdCurveArrayError = new ArrayList<float[]>();
				
				float[] _tempArrayX = new float[N+2];
				float[] _tempArrayY = new float[N+2];

				_xArrayError = new float[N+2];
				_yArrayError = new float[N+2];

				
				
				a11=0; a12=0; a13=0; a21=0; a22=0; a23=0; a31=0; a32=0; a33=0;
				z1=0; z2=0; z3=0; _sigmaSum=0; _sigma1=0; _sigma2=0; _sigma3=0;
				p=3;
				
				
			
				for(int i=_fitMarkerFirstPos; i<=_fitMarkerLastPos; i++)
				{
			 
					Fi0 = F1exp(_a10, _a20, _a30, _xArrayIni[i]-_zeroPosDisplay);
					
					float tempX = -_zeroPosDisplay + _xArrayIni[i];

					_tempArrayX[i-_fitMarkerFirstPos] = tempX-_xMin+_zeroPosDisplay;
					_tempArrayY[i-_fitMarkerFirstPos] = -Fi0-_yMin;
					
					_xArrayError[i-_fitMarkerFirstPos]=_tempArrayX[i-_fitMarkerFirstPos];
					_yArrayError[i-_fitMarkerFirstPos]= Fi0+_yArrayIni[i];
					

					
					A = 1;
					B = (float)Math.pow(Math.E, -(_a30*tempX));
					C = _a20*(float)Math.pow(Math.E, -(_a30*tempX))*(-tempX);
					
					z1 = z1 + (-_yArrayIni[i]-Fi0)*A;
					z2 = z2 + (-_yArrayIni[i]-Fi0)*B;
					z3 = z3 + (-_yArrayIni[i]-Fi0)*C;
					_sigmaSum = _sigmaSum + (-_yArrayIni[i]-Fi0)*(-_yArrayIni[i]-Fi0);
			 
					a11 = a11 + A*A;
					a12 = a12 + A*B;
					a13 = a13 + A*C;
					a21 = a21 + A*B;
					a22 = a22 + B*B;
					a23 = a23 + C*B;
					a31 = a31 + A*C;
					a32 = a32 + B*C;
					a33 = a33 + C*C;
				
				}


			

							
				if(_fittingMode=="1exp Fit")
				{		
					if(!_if1expD0CheckBoxSelected){a11=1; a12=0; a13=0; a21=0; a31=0; z1=0;	p--;}
					if(!_if1expACheckBoxSelected){a22=1; a12=0; a23=0; a32=0; a21=0; z2=0;p--;}
					if(!_if1expKCheckBoxSelected){a33=1; a13=0; a23=0; a31=0; a32=0; z3=0;p--;}
				}
				

				
				
				det = a11*a22*a33 + a21*a32*a13 + a12*a23*a31 - a13*a22*a31 - a11*a23*a32 - a12*a21*a33;
			
				b11 = (1/det)*(a22*a33 - a23*a32);
				b12 = (1/det)*(a13*a32 - a12*a33);
				b13 = (1/det)*(a12*a23 - a13*a22);
				b21 = (1/det)*(a23*a31 - a21*a33);
				b22 = (1/det)*(a11*a33 - a13*a31);
				b23 = (1/det)*(a21*a13 - a11*a23);
				b31 = (1/det)*(a21*a32 - a22*a31);
				b32 = (1/det)*(a12*a31 - a11*a32);
				b33 = (1/det)*(a11*a22 - a12*a21);
			
			
				da1 = b11*z1 + b12*z2 + b13*z3;
				da2 = b21*z1 + b22*z2 + b23*z3;
				da3 = b31*z1 + b32*z2 + b33*z3;
			
				a1 = _a10 + da1;
				a2 = _a20 + da2;
				a3 = _a30 + da3;
			
			
				_checkSum = Math.abs(da1/a1) + Math.abs(da2/a2) + Math.abs(da3/a3);

				
				_sigmaA2 = _sigmaSum/(N-p-1);

				_sigma1 = (float)Math.sqrt(_sigmaA2*b11);
				_sigma2 = (float)Math.sqrt(_sigmaA2*b22);
				_sigma3 = (float)Math.sqrt(_sigmaA2*b33);
				


				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);
				_createdCurveArrayError.add(_xArrayError);
				_createdCurveArrayError.add(_yArrayError);
				
				_fittingColor = Color.green;
				
				repaint();
				
				
				if(_checkSum < e)
				{
					//System.out.println("The sequense is getting narrower");


					_a10 = a1;
					_a20 = a2;
					_a30 = a3;

					if(_fittingMode=="1exp Fit")
					{
						_ifFittingSuccessful=true;


					}
					
				}
				else
				{
					//System.out.println("The sequense is getting wider");

					if(_fittingMode=="1exp Fit")
					{

						_ifFittingSuccessful=false;
					}
					
					break;
				}
				

				
			}
			
			
			System.out.println("Final results of fitting:");
			System.out.println("D0 " + _a10 );
			System.out.println("A " + _a20 );
			System.out.println("k " + _a30 );
			
			_levelPosDisplay = _a10;
			_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y, _yMin, _yScaler);

	  	  	
			if(_fittingMode=="1exp Fit")
			{
		  	  _A = _a20;
		  	  _k = _a30;
			}
	  	  	
			

			
			recalcCurveScale();
			
			_magPosXError = _magPosX;
			
		}
	
	
	
		
		public void secondOrderFitting()
		{
			float da1, da2, da3;
			float a1, a2, a3;
			//float a10, a20, a30;
			float a11=0, a12=0, a13=0, a21=0, a22=0, a23=0, a31=0, a32=0, a33=0;
			float b11, b12, b13, b21, b22, b23, b31, b32, b33;
			float z1=0, z2=0, z3=0;
			float A=0, B=0, C=0;
			float Fi0;
			int N, p;
			//float d = 0.01f;
			float det;
			float _checkSum;
			float e = 1000f;
			//float sigmaA2 = 0, sigma1=0, sigma2=0, sigma3=0, sigmaSum=0;
			
			
			N = _fitMarkerLastPos- _fitMarkerFirstPos ;
			
			_a10 = _levelPosDisplay;
			_a20 = _A;
			_a30 = _k;
			
			System.out.println("Second Order Fitting Started...");
			

			_nOfCreatedCurvePoints = N;
			_nOfCreatedCurvePointsError = N;
			_xScalerError = _xScaler;
			_yScalerError = _yScaler;
			_ifShowCreatedCurve = true;
			
			for(int j=1; j<100; j++)
			{

				_createdCurveArray = new ArrayList<float[]>();
				_createdCurveArrayError = new ArrayList<float[]>();
				
				float[] _tempArrayX = new float[N+2];
				float[] _tempArrayY = new float[N+2];
				_xArrayError = new float[N+2];
				_yArrayError = new float[N+2];

				a11=0; a12=0; a13=0; a21=0; a22=0; a23=0; a31=0; a32=0; a33=0;
				z1=0; z2=0; z3=0; _sigmaSum=0; _sigma1=0; _sigma2=0; _sigma3=0;
				p=3;
				
				
			
				for(int i=_fitMarkerFirstPos; i<=_fitMarkerLastPos; i++)
				{
			 
					Fi0 = F2Order(_a10, _a20, _a30, _xArrayIni[i]-_zeroPosDisplay);
					
					float tempX = -_zeroPosDisplay + _xArrayIni[i];

					_tempArrayX[i-_fitMarkerFirstPos] = tempX-_xMin+_zeroPosDisplay;
					_tempArrayY[i-_fitMarkerFirstPos] = -Fi0-_yMin;
					_xArrayError[i-_fitMarkerFirstPos]=_tempArrayX[i-_fitMarkerFirstPos];
					_yArrayError[i-_fitMarkerFirstPos]= Fi0+_yArrayIni[i];
					
					
					A = 1;
					B = 1/(1 + _a30*tempX);
					C = -_a20*tempX/((1 + _a30*tempX)*(1 + _a30*tempX));
			 
					
					z1 = z1 + (-_yArrayIni[i]-Fi0)*A;
					z2 = z2 + (-_yArrayIni[i]-Fi0)*B;
					z3 = z3 + (-_yArrayIni[i]-Fi0)*C;
					_sigmaSum = _sigmaSum + (-_yArrayIni[i]-Fi0)*(-_yArrayIni[i]-Fi0);
			 
					a11 = a11 + A*A;
					a12 = a12 + A*B;
					a13 = a13 + A*C;
					a21 = a21 + A*B;
					a22 = a22 + B*B;
					a23 = a23 + C*B;
					a31 = a31 + A*C;
					a32 = a32 + B*C;
					a33 = a33 + C*C;
				
				}


			
				if(!_if2OrderD0CheckBoxSelected){a11=1; a12=0; a13=0; a21=0; a31=0; z1=0;p--;}
				if(!_if2OrderACheckBoxSelected){a22=1; a12=0; a23=0; a32=0; a21=0; z2=0;p--;}
				if(!_if2OrderKCheckBoxSelected){a33=1; a13=0; a23=0; a31=0; a32=0; z3=0;p--;}


				
				
				det = a11*a22*a33 + a21*a32*a13 + a12*a23*a31 - a13*a22*a31 - a11*a23*a32 - a12*a21*a33;
			
				b11 = (1/det)*(a22*a33 - a23*a32);
				b12 = (1/det)*(a13*a32 - a12*a33);
				b13 = (1/det)*(a12*a23 - a13*a22);
				b21 = (1/det)*(a23*a31 - a21*a33);
				b22 = (1/det)*(a11*a33 - a13*a31);
				b23 = (1/det)*(a21*a13 - a11*a23);
				b31 = (1/det)*(a21*a32 - a22*a31);
				b32 = (1/det)*(a12*a31 - a11*a32);
				b33 = (1/det)*(a11*a22 - a12*a21);
			
			
				da1 = b11*z1 + b12*z2 + b13*z3;
				da2 = b21*z1 + b22*z2 + b23*z3;
				da3 = b31*z1 + b32*z2 + b33*z3;
			
				a1 = _a10 + da1;
				a2 = _a20 + da2;
				a3 = _a30 + da3;
			
			
				_checkSum = Math.abs(da1/a1) + Math.abs(da2/a2) + Math.abs(da3/a3);

				
				_sigmaA2 = _sigmaSum/(N-p-1);

				_sigma1 = (float)Math.sqrt(_sigmaA2*b11);
				_sigma2 = (float)Math.sqrt(_sigmaA2*b22);
				_sigma3 = (float)Math.sqrt(_sigmaA2*b33);
				

//////////////////////				
				
				//System.out.println("Checking sum: " + _checkSum);

				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);
				_createdCurveArrayError.add(_xArrayError);
				_createdCurveArrayError.add(_yArrayError);
				
				_fittingColor = Color.green;
				
				
				
				if(_checkSum < e)
				{
					_a10 = a1;
					_a20 = a2;
					_a30 = a3;
					
					_ifFittingSuccessful = true;

				}
				else
				{

					_ifFittingSuccessful = false;

					break;
				}
				

				
			}
			
			
			System.out.println("Final results of fitting:");
			System.out.println("D0 " + _a10 );
			System.out.println("A " + _a20 );
			System.out.println("k " + _a30 );
			
			_levelPosDisplay = _a10;
			_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y ,_yMin, _yScaler);





	 	  	
			recalcCurveScale();
			_magPosXError = _magPosX;


		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void secondOrderProductFitting()
		{
			float da1, da2, da3;
			float a1, a2, a3;
			//float a10, a20, a30;
			float a11=0, a12=0, a13=0, a21=0, a22=0, a23=0, a31=0, a32=0, a33=0;
			float b11, b12, b13, b21, b22, b23, b31, b32, b33;
			float z1=0, z2=0, z3=0;
			float A=0, B=0, C=0;
			float Fi0;
			int N, p;
			//float d = 0.01f;
			float det;
			float _checkSum;
			float e = 1000f;
			//float sigmaA2 = 0, sigma1=0, sigma2=0, sigma3=0, sigmaSum=0;
			
			
			N = _fitMarkerLastPos- _fitMarkerFirstPos ;
			
			_a10 = _D0;
			_a20 = _A;
			_a30 = _k;
			
			System.out.println("Second Order Product Fitting Started...");
			

			_nOfCreatedCurvePoints = N;
			_nOfCreatedCurvePointsError = N;
			_xScalerError = _xScaler;
			_yScalerError = _yScaler;
			_ifShowCreatedCurve = true;
			
			for(int j=1; j<100; j++)
			{

				_createdCurveArray = new ArrayList<float[]>();
				_createdCurveArrayError = new ArrayList<float[]>();
				
				float[] _tempArrayX = new float[N+2];
				float[] _tempArrayY = new float[N+2];
				_xArrayError = new float[N+2];
				_yArrayError = new float[N+2];

				a11=0; a12=0; a13=0; a21=0; a22=0; a23=0; a31=0; a32=0; a33=0;
				z1=0; z2=0; z3=0; _sigmaSum=0; _sigma1=0; _sigma2=0; _sigma3=0;
				p=3;
				
				
			
				for(int i=_fitMarkerFirstPos; i<=_fitMarkerLastPos; i++)
				{
			 
					Fi0 = F2OrderProduct(_a10, _a20, _a30, _levelPosDisplay, _xArrayIni[i]-_zeroPosDisplay);
					
					float tempX = -_zeroPosDisplay + _xArrayIni[i];

					_tempArrayX[i-_fitMarkerFirstPos] = tempX-_xMin+_zeroPosDisplay;
					_tempArrayY[i-_fitMarkerFirstPos] = -Fi0-_yMin;
					_xArrayError[i-_fitMarkerFirstPos]=_tempArrayX[i-_fitMarkerFirstPos];
					_yArrayError[i-_fitMarkerFirstPos]= Fi0+_yArrayIni[i];
					
					
					A = 1/(1 + _a30*tempX);;
					B = _a30*tempX/(1 + _a30*tempX);
					C = tempX*(_a20-_a10)/((1 + _a30*tempX)*(1 + _a30*tempX));
			 
					
					z1 = z1 + (-_yArrayIni[i]-Fi0)*A;
					z2 = z2 + (-_yArrayIni[i]-Fi0)*B;
					z3 = z3 + (-_yArrayIni[i]-Fi0)*C;
					_sigmaSum = _sigmaSum + (-_yArrayIni[i]-Fi0)*(-_yArrayIni[i]-Fi0);
			 
					a11 = a11 + A*A;
					a12 = a12 + A*B;
					a13 = a13 + A*C;
					a21 = a21 + A*B;
					a22 = a22 + B*B;
					a23 = a23 + C*B;
					a31 = a31 + A*C;
					a32 = a32 + B*C;
					a33 = a33 + C*C;
				
				}


			
				if(!_if2OrderD0CheckBoxSelected){a11=1; a12=0; a13=0; a21=0; a31=0; z1=0;p--;}
				if(!_if2OrderACheckBoxSelected){a22=1; a12=0; a23=0; a32=0; a21=0; z2=0;p--;}
				if(!_if2OrderKCheckBoxSelected){a33=1; a13=0; a23=0; a31=0; a32=0; z3=0;p--;}


				
				
				det = a11*a22*a33 + a21*a32*a13 + a12*a23*a31 - a13*a22*a31 - a11*a23*a32 - a12*a21*a33;
			
				b11 = (1/det)*(a22*a33 - a23*a32);
				b12 = (1/det)*(a13*a32 - a12*a33);
				b13 = (1/det)*(a12*a23 - a13*a22);
				b21 = (1/det)*(a23*a31 - a21*a33);
				b22 = (1/det)*(a11*a33 - a13*a31);
				b23 = (1/det)*(a21*a13 - a11*a23);
				b31 = (1/det)*(a21*a32 - a22*a31);
				b32 = (1/det)*(a12*a31 - a11*a32);
				b33 = (1/det)*(a11*a22 - a12*a21);
			
			
				da1 = b11*z1 + b12*z2 + b13*z3;
				da2 = b21*z1 + b22*z2 + b23*z3;
				da3 = b31*z1 + b32*z2 + b33*z3;
			
				a1 = _a10 + da1;
				a2 = _a20 + da2;
				a3 = _a30 + da3;
			
			
				_checkSum = Math.abs(da1/a1) + Math.abs(da2/a2) + Math.abs(da3/a3);

				
				_sigmaA2 = _sigmaSum/(N-p-1);

				_sigma1 = (float)Math.sqrt(_sigmaA2*b11);
				_sigma2 = (float)Math.sqrt(_sigmaA2*b22);
				_sigma3 = (float)Math.sqrt(_sigmaA2*b33);
				

//////////////////////				
				
				//System.out.println("Checking sum: " + _checkSum);

				_createdCurveArray.add(_tempArrayX);
				_createdCurveArray.add(_tempArrayY);
				_createdCurveArrayError.add(_xArrayError);
				_createdCurveArrayError.add(_yArrayError);
				
				_fittingColor = Color.green;
				
				
				
				if(_checkSum < e)
				{
					_a10 = a1;
					_a20 = a2;
					_a30 = a3;
					
					_ifFittingSuccessful = true;

				}
				else
				{

					_ifFittingSuccessful = false;

					break;
				}
				

				
			}
			
			
			System.out.println("Final results of fitting:");
			System.out.println("A0 " + _a10 );
			System.out.println("Ainf " + _a20 );
			System.out.println("k " + _a30 );
			
			///_levelPosDisplay = _a10;
			//_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y ,_yMin, _yScaler);





	 	  	
			recalcCurveScale();
			_magPosXError = _magPosX;


		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void recalcCurveScale()
		{
			
		
			_yMaxError = -100000000;
			_yMinError = 100000000;
			
			 for(int i=1;i<(_nOfCreatedCurvePointsError); i++)
			 {

				float _yPoint =  _createdCurveArrayError.get(1)[i-1];
				 
				 
					if(_yMaxError<_yPoint){_yMaxError=_yPoint;}
					if(_yMinError>_yPoint){_yMinError=_yPoint;}

			 }
			
			 
			 float a = Math.max(Math.abs(_yMaxError), Math.abs(_yMinError));
			_yScalerError = _gridSizeYError/(a)/2;
			 
			//_yScalerError = _gridSizeYError/(_yMaxError-_yMinError);
			
		}
	
	
	
	
	
	
		
		public float F1exp(float a1, float a2, float a3, float x)
		{
			return a1 + a2*(float)Math.pow(Math.E, -(a3*x));
		}
		
		
		public float F2Order(float a1, float a2, float a3, float x)
		{
			return a1 + a2/(1 + a3*x);
		}
	
	
	
		public float F2OrderProduct(float A0, float Ainf, float k, float _levelPosDisplay, float t)
		{
			return _levelPosDisplay + (A0 + k*Ainf*t)/(1 + k*t);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void set_gridSizeX(int _gridSizeX)
	{
		this._gridSizeX = _gridSizeX;
	}

	public void set_gridSizeY(int _gridSizeY)
	{
		this._gridSizeY = _gridSizeY;
	}


	public void set_gridSizeXError(int _gridSizeXError)
	{
		this._gridSizeXError = _gridSizeXError;
	}

	public void set_gridSizeYError(int _gridSizeYError)
	{
		this._gridSizeYError = _gridSizeYError;
	}
	
	public float get_xScaler()
	{
		return _xScaler;
	}

	public float get_yScaler()
	{
		return _yScaler;
	}

	public float get_xScalerError()
	{
		return _xScalerError;
	}

	public float get_yScalerError()
	{
		return _yScalerError;
	}
	
	public float get_xScalerIni()
	{
		return _xScalerIni;
	}

	public float get_yScalerIni()
	{
		return _yScalerIni;
	}

	public float get_xMin ()
	{
		return _xMin;
	}

	public float get_yMin ()
	{
		return _yMin;
	}

	public float get_xMax ()
	{
		return _xMax;
	}

	public float get_yMax ()
	{

		return _yMax;
	}

	public float get_magPosX ()
	{
		return _magPosX;
	}

	public float get_magPosY ()
	{
		return _magPosY;
	}

	public int get_arraySize ()
	{
		return _arraySize;
	}

	
	
	public float get_xMinError ()
	{
		return _xMinError;
	}

	public float get_yMinError ()
	{
		return _yMinError;
	}

	public float get_xMaxError ()
	{
		return _xMaxError;
	}

	public float get_yMaxError ()
	{

		return _yMaxError;
	}

	public float get_magPosXError ()
	{
		return _magPosXError;
	}

	public float get_magPosYError ()
	{
		return _magPosYError;
	}
//	public void get_nOfCreatedCurvePoints ()
//	{
//		return _nOfCreatedCurvePoints;
//	}



	public int get_markerShiftStep ()
	{
		return _markerShiftStep;
	}
	
	public int get_markerShiftStepFast ()
	{
		return _markerShiftStepFast;
	}
	
	public float get_levelShiftStep ()
	{
		return _levelShiftStep;
	}
	
	public float get_levelShiftStepFast ()
	{
		return _levelShiftStepFast;
	}
	
	public float get_zeroShiftStep ()
	{
		return _zeroShiftStep;
	}
	
	public float get_zeroShiftStepFast ()
	{
		return _zeroShiftStepFast;
	}
	
	
	
	public float get_levelPosDisplay()
	{
		return _levelPosDisplay;
	}
	
	public float get_levelPosReal()
	{
		return _levelPosReal;
	}
	
	public float get_zeroPosDisplay()
	{
		return _zeroPosDisplay;
	}
	
	public float get_zeroPosReal()
	{
		return _zeroPosReal;
	}
	
	public  Rectangle2D.Float get_selectedRect()
	{
		return selectedRect;
	}
	
	public  float get_fitMarkerFirstDisplayX()
	{
		return _fitMarkerFirstDisplayX;
	}
	
	public  float get_fitMarkerLastDisplayX()
	{
		return _fitMarkerLastDisplayX;
	}

	
	public float get_fitMarkerFirstRealX ()
	{
		return _fitMarkerFirstRealX;
	}

	public float get_fitMarkerFirstRealY ()
	{
		return _fitMarkerFirstRealY;
	}

	public float get_fitMarkerLastRealX ()
	{
		return _fitMarkerLastRealX;
	}

	public float get_fitMarkerLastRealY ()
	{
		return _fitMarkerLastRealY;
	}
	
	
	public String get_mouseLabelText ()
	{
		return _mouseLabelText;
	}
	

	
	
	
	
	
	
	
//	public void get_enableLowDetail ()
//	{
//		return _enableLowDetail;
//	}


//	public void get_ifShowCreatedCurve ()
//	{
//		return _ifShowCreatedCurve;
//	}


//	public void get_enableAABox ()
//	{
//		return _enableAABox;
//	}

//	public void get_createdCurveArray ()
//	{
//		return _createdCurveArray;
//	}

	public float[] get_xArray ()
	{
		return _xArray;
	}

	public float[] get_yArray ()
	{
		return _yArray;
	}


	public void set_saveEvery(int _saveEvery)
	{
		this._saveEvery = _saveEvery;
	}
	
	
	public void set_ifNeedToRepaint(boolean _ifNeedToRepaint)
	{
		this._ifNeedToRepaint = _ifNeedToRepaint;
	}
	
	public boolean get_ifNeedToRepaint()
	{
		return _ifNeedToRepaint;
	}
	
	public void set_ifShowCreatedCurve(boolean _ifShowCreatedCurve)
	{
		this._ifShowCreatedCurve = _ifShowCreatedCurve;
	}
	
	public boolean get_ifShowCreatedCurve()
	{
		return _ifShowCreatedCurve;
	}

	
	
	public void set_fittingColor(Color _fittingColor)
	{
		this._fittingColor = _fittingColor;
	}
	
	public Color get_fittingColor()
	{
		return _fittingColor;
	}
	
	
	public void set_createdCurveArray( ArrayList<float[]> _createdCurveArray)
	{
		this._createdCurveArray = _createdCurveArray;
	}
	
	public  ArrayList<float[]> get_createdCurveArray()
	{
		return _createdCurveArray;
	}
	
	public  ArrayList<float[]> get_createdCurveArrayError()
	{
		return _createdCurveArrayError;
	}
	
	public void set_nOfCreatedCurvePoints(int _nOfCreatedCurvePoints)
	{
		this._nOfCreatedCurvePoints = _nOfCreatedCurvePoints;
	}
	
	public int get_nOfCreatedCurvePoints()
	{
		return _nOfCreatedCurvePoints;
	}
	
	public int get_nOfCreatedCurvePointsError()
	{
		return _nOfCreatedCurvePointsError;
	}
	
	
	public void set_A(float _A)
	{
		this._A = _A;
	}
	
	public float get_A()
	{
		return _A;
	}
	
	
	public void set_k(float _k)
	{
		this._k = _k;
	}
	
	public float get_k()
	{
		return _k;
	}
	
	public void set_D0(float _D0)
	{
		this._D0 = _D0;
	}
	
	public float get_D0()
	{
		return _D0;
	}
	
	public void set_fittingMode(String _fittingMode)
	{
		this._fittingMode = _fittingMode;
	}
	
	public boolean get_ifFittingSuccessful()
	{
		return _ifFittingSuccessful;
	}
	
	
	
	
	
	
	public float get_a10()
	{
		return _a10;
	}
	
	public float get_a20()
	{
		return _a20;
	}
	
	public float get_a30()
	{
		return _a30;
	}
	
	public float get_sigma1()
	{
		return _sigma1;
	}
	
	public float get_sigma2()
	{
		return _sigma2;
	}
	
	public float get_sigma3()
	{
		return _sigma3;
	}
	
	public void set_if1expD0CheckBoxSelected(boolean _if1expD0CheckBoxSelected)
	{
		this._if1expD0CheckBoxSelected = _if1expD0CheckBoxSelected;
	}
	
	
	public void set_if1expACheckBoxSelected(boolean _if1expACheckBoxSelected)
	{
		this._if1expACheckBoxSelected = _if1expACheckBoxSelected;
	}
	
	public void set_if1expKCheckBoxSelected(boolean _if1expKCheckBoxSelected)
	{
		this._if1expKCheckBoxSelected = _if1expKCheckBoxSelected;
	}
	
	
	public void set_if2OrderD0CheckBoxSelected(boolean _if2OrderD0CheckBoxSelected)
	{
		this._if2OrderD0CheckBoxSelected = _if2OrderD0CheckBoxSelected;
	}
	
	
	public void set_if2OrderACheckBoxSelected(boolean _if2OrderACheckBoxSelected)
	{
		this._if2OrderACheckBoxSelected = _if2OrderACheckBoxSelected;
	}
	
	public void set_if2OrderKCheckBoxSelected(boolean _if2OrderKCheckBoxSelected)
	{
		this._if2OrderKCheckBoxSelected = _if2OrderKCheckBoxSelected;
	}
	
	

	
	// Mouse events
	
	
	public void calcRectanglesPositions()
	{
	 	myRect.y = _levelPosReal-5-_magPosY;
	 	myRect.width = _gridSizeX;
	    myZeroRect.x = _zeroPosReal-5-_magPosX;
	    myZeroRect.height = _gridSizeY;
	    myBeforeZeroRect.x = _beforeZeroPosReal-5-_magPosX;
	    myBeforeZeroRect.height = _gridSizeY;
	    beforeZeroSelRect.width = _beforeZeroPosReal-setZero(0,Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
	    beforeZeroSelRect.x = -_magPosX+setZero(0,Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
	    beforeZeroSelRect.height = _gridSizeY;
	    fitMarkerFirstRect.x = _fitMarkerFirstRealX-9-_magPosX;
	    fitMarkerFirstRect.y = _fitMarkerFirstRealY-15-_magPosY;
	    fitMarkerLastRect.x = _fitMarkerLastRealX-9-_magPosX;
	    fitMarkerLastRect.y = _fitMarkerLastRealY-15-_magPosY;
	}
	
	
	public Cursor get_cursor()
	{
		return _cursor;
	}

	
	
	
	
	public void recalcMouseLabel()
	{
		
	      _mouseX = (_mouseX + _magPosX - Constants._kineticsModeGridPosition.x)/_xScaler + _xMin;
	      _mouseY = -(_mouseY + _magPosY - Constants._kineticsModeGridPosition.y)/_yScaler - _yMin;
	      //_mouseX = (_mouseX + _magPosX - _gridPos.x)/_xScaler + _xMin;
	      String
	      s = "" + _mouseX;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.indexOf('.')+5);
	      s = s + "     " + _mouseY;
	      if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	      s = s.substring(0, s.lastIndexOf('.')+5);
	      if(_ifShowAverageValue)
	      {
	    	  s = s + "     Average:  " + _graphAverage;
	    	  if(((s.length()-s.lastIndexOf('.'))>5)&(s.indexOf('E')==-1))
	    		  s = s.substring(0, s.lastIndexOf('.')+5);
	      }
	      
	      _mouseLabelText = s;
		
		
		
		
	}
	
	
	
	
	
	
	
	   public void mouseMoved(MouseEvent e)
	   {
			  _mouseX = e.getX();
		      _mouseY = e.getY();
		      calcRectanglesPositions();
		      

		      	int i=0;
		    	   if((fitMarkerFirstRect.getBounds2D().contains(_mouseX, _mouseY)))
		    	   {
		    		   _cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
		    		   //SetCursor();
		    		   i=1;
		    		   
		    	   }
		    	   if((fitMarkerLastRect.getBounds2D().contains(_mouseX, _mouseY))&(i==0))
		    	   {
		    		   _cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
		    		   //SetCursor();
		    		   i=1;
		    		   
		    	   }

		    	   if(myRect.getBounds2D().contains(_mouseX, _mouseY)&(i==0))
		    	   {
		    		   _cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
		    		   //SetCursor();
		    		   i=1;
		    	   }
		    	   if(myZeroRect.getBounds2D().contains(_mouseX, _mouseY)&(i==0))
		    	   {
		    		   _cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
		    		   //SetCursor();
		    		  
		    		   i=1;
		    	   }
		    	   if(myBeforeZeroRect.getBounds2D().contains(_mouseX, _mouseY)&(i==0))
		    	   {
		    		   _cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
		    		   //SetCursor();
		    		  
		    		   i=1;
		    	   }

		    	   
		    	   if(i==0)
			      {
			    	  _cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		    		   //SetCursor();
			      }
		      
			      recalcMouseLabel();
		      
	   }
	   
	   
		   
	   
	   
	   
	   
	   
	   public void mousePressed(MouseEvent e) {
			  x = e.getX();
		      y = e.getY();
		  
		      if ((e.getButton()==1))
		      { 
		    	  if(((myRect.getBounds2D().contains(x, y))||(myZeroRect.getBounds2D().contains(x, y))||
		    			  (myBeforeZeroRect.getBounds2D().contains(x, y))||(fitMarkerFirstRect.getBounds2D().contains(x, y))||
		    			  (fitMarkerLastRect.getBounds2D().contains(x, y)))&(!_beingSel)&(_ifCursorFree))
		    	  {
		    	  
			    	   	  _startSel=false;
			    	   	  _moveLevel=false;
		    			  _moveZero=false;
		    			  _moveBeforeZero=false;
		    			  _moveFitMarkerFirst=false;
		    			  _moveFitMarkerLast=false;


			    	   int i=0;
			    	   
			    	   
			    	   if((fitMarkerFirstRect.getBounds2D().contains(x, y)))
			    	   {
			    		   _moveFitMarkerFirst = true;
			    		   i=1;
			    	   }
			    	   
			    	   if((fitMarkerLastRect.getBounds2D().contains(x, y))&(i==0))
			    	   {
			    		   _moveFitMarkerLast = true;
			    		   i=1;
			    	   }
			    	   

			    	   if(myRect.getBounds2D().contains(x, y)&(i==0))
			    	   {
			    		   _moveLevel=true;
			    		   i=1;
			    	   }

			    	   if(myZeroRect.getBounds2D().contains(x, y)&(i==0))
			    	   {
			    		   _moveZero=true;
			    		   i=1;
			    	   }
			    	   if(myBeforeZeroRect.getBounds2D().contains(x, y)&(i==0))
			    	   {
			    		   _moveBeforeZero=true;
			    		   i=1;
			    	   }

			    	   if(i==1)_ifCursorFree = false;
		    	   
		    	  }
		    	  else
		    		  if((_beingSel==false)&(_ifCursorFree))
		    		  {
		    			  x1=x;
		    			  y1=y;
		    			  _startSel=true;
		    			  _beingSel=true;
		    			  _moveLevel=false;
		    			  _moveZero=false;
		    			  _moveBeforeZero=false;
		    			  _moveFitMarkerFirst=false;
		    			  _moveFitMarkerLast=false;
			    		  _movingFitMarkerFirst = false;
			    		  _movingFitMarkerLast = false;
		    			  
		    		  }			      

		      } 
		    	  

		      if(e.getButton()==2)
		      {
		    	  _moveScreen=true;
		      }

		      if(e.getButton()==3)
		      {
		    	_xScaler = _xScalerIni;
		    	_yScaler = _yScalerIni;
	    	  	_magPosX = 0;
	    	  	_magPosY = 0;
	    	  	_beingSel=false;
	    	  	_ifCursorFree = true;
      			selectedRect.x = 0;
      			selectedRect.y = 0;
      			selectedRect.width = 0;
      			selectedRect.height = 0;
		  		_levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y, _yMin, _yScaler);
		  		_zeroPosReal = setZero(_zeroPosDisplay, Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
		  		_beforeZeroPosReal = setZero(_beforeZeroPosDisplay, Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
		  		
    			_ifNeedToRepaint = true;
		      }


	    }

	    public void mouseReleased(MouseEvent e) 
	    {
		      x2 = e.getX();
		      y2 = e.getY();
		      _ifCursorFree = true;
		      _moveFitMarkerFirst = false;
		      _moveFitMarkerLast = false;
		      if(_startSel&_beingSel)
		      {
		    	  _startSel=false;
		    	  _beingSel=false;
		    	  if((x2-x1)<0){c=x1;x1=x2;x2=c;}
		    	  if((y2-y1)<0){c=y1;y1=y2;y2=c;}
		    	  
		    	  selectedRect.x = x1;
		    	  selectedRect.y = y1;
		    	  selectedRect.width = x2-x1;
		    	  selectedRect.height = y2-y1;
	  
		    	  _magX1=x1;
		    	  _magX2=x2;
		    	  _magY1=y1;
		    	  _magY2=y2;

		    	  repaint();
			    	  
		      }

		      if(_moveScreen)
		      {
		    	  _moveScreen=false;
		    	  _cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		    	  //SetCursor();
		      }
	    
	    }

	    
	    public void mouseDragged(MouseEvent e) {

	    	
	    	int dx = e.getX() - x;
	    	int dy = e.getY() - y;
	    	_mouseX = e.getX();
	    	_mouseY = e.getY();
	    	recalcMouseLabel();
	    	calcRectanglesPositions();

	      if ((myRect.getBounds2D().contains(x, y))&(!_beingSel)&(_moveLevel)) 
	      {
	    	  myRect.y += dy;
	    	  _levelPosDisplay = _levelPosDisplay - dy/_yScaler;
	    	  _levelPosReal = setLevel(_levelPosDisplay, Constants._kineticsModeGridPosition.y, _yMin, _yScaler);

	      }

	      if ((myZeroRect.getBounds2D().contains(x, y))&(!_beingSel)&(_moveZero)) 
	      {
	    	  myZeroRect.x = +dx;
	    	  _zeroPosDisplay = _zeroPosDisplay + dx/_xScaler;
	    	  _zeroPosReal = setZero(_zeroPosDisplay, Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
	      }

	      if ((myBeforeZeroRect.getBounds2D().contains(x, y))&(!_beingSel)&(_moveBeforeZero)) 
	      {
	    	  myBeforeZeroRect.x = +dx;
	    	  _beforeZeroPosDisplay = _beforeZeroPosDisplay + dx/_xScaler;
	    	  _beforeZeroPosReal = setZero(_beforeZeroPosDisplay, Constants._kineticsModeGridPosition.x, _xMin, _xScaler);
	      }
	      

	      
	      if (((fitMarkerFirstRect.getBounds2D().contains(x, y))||(_moveFitMarkerFirst))&(!_beingSel)&(!_moveLevel)&(!_moveBeforeZero)&(!_moveZero)&(!_moveFitMarkerLast)) 
	      {
	    	  _fitMarkerFirstPos = findMarkerArrayPos(_mouseX);
	    	  if(_fitMarkerFirstPos==-1)_fitMarkerFirstPos=_arraySize;
	    	  if(_fitMarkerFirstPos<1)_fitMarkerFirstPos=1;
			  _rememberPosFirstMarker = _fitMarkerFirstPos;		    	  
	    	  recalcFitMarkerFirst(false);
	      }
	      
	      
	      if (((fitMarkerLastRect.getBounds2D().contains(x, y))||(_moveFitMarkerLast))&(!_beingSel)&(!_moveLevel)&(!_moveBeforeZero)&(!_moveZero)&(!_moveFitMarkerFirst)) 
	      {
	    	  _fitMarkerLastPos = findMarkerArrayPos(_mouseX);
	    	  if(_fitMarkerLastPos==-1)_fitMarkerLastPos=_arraySize;
	    	  if(_fitMarkerLastPos<1)_fitMarkerLastPos=1;
			  _rememberPosLastMarker = _fitMarkerLastPos;
	    	  recalcFitMarkerLast(false);

	      }

	      
	      if(_moveScreen)
	      {
	    	_magPosX -= dx;
	    	_magPosY -= dy;
    	    
	    	_cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    	    //SetCursor();

	      }
	      
	      
	      x += dx;
	      y += dy;
	    
	      
	    }
	    
	    
	    
	
	
	
	
	
	

	
	
	
	
	}	

