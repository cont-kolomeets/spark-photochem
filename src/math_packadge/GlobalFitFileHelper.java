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



public class GlobalFitFileHelper {
	
	GlobalFitMath M;
	
	public GlobalFitFileHelper(GlobalFitMath M)
	{
		this.M =M;
	}
	
	
	
	
	

	public String openFile() 
	{
	    String s="No file",s1="TimeSeries";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Data files", "txt", "TimeSeries", "asc", "fss");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(M.file);
	    int returnVal = chooser.showOpenDialog(M);
	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	M.file = chooser.getSelectedFile();
	       
	       if(M._saveNameEmpty)
	       {
	    	   M._saveNameEmpty = false;
	    	   M.saveName = M.file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=M.file.getName();
	       s1 = s.substring(s.lastIndexOf('.')+1);

    		   charSeq = "TimeSeries";
    		   charSeq1 = "txt";
    		   charSeq2 = "asc";
    		   if((s.contains(charSeq))||(s.contains(charSeq1))||(s.contains(charSeq2)))
    		   {
	    		   System.out.println("Open file");
	    		   proccessGlobalFitKinFile(M.file);
	    		   M._filePath = M.file.getPath();
	    		   //_levelLabel.setText(s);
	    		   //_status.setText("Status: ready - " + file.getName());
	    		   //_graphPanel.repaint();

    		   }
    		   else
    		   {
    			   JOptionPane.showMessageDialog(M, "Select diferent mode!");

	    		   
	    	   }

	    }
	    return s;
	
	}
	
	
	
	
	public String globalFitOpenProject() 
	{
	    String s="No file",s1="TimeSeries";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("project", "project");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(M.file);
	    int returnVal = chooser.showOpenDialog(M);
	    
	   // chooser.repaint();
	   // chooser.requestFocus();
	   // chooser.requestFocusInWindow();
	   // chooser.setVisible(true);

	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	M.file = chooser.getSelectedFile();
	       
	       if(M._saveNameEmpty)
	       {
	    	   M._saveNameEmpty = false;
	    	   M.saveName = M.file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=M.file.getName();
	       s1 = s.substring(s.lastIndexOf('.')+1);

    		   charSeq = "project";
    		   if(s.contains(charSeq))
    		   {
	    		   System.out.println("Open file");
	    		   proccessGlobalFitProjectFile(M.file);
	    		   M._filePath = M.file.getPath();
	    		   addOpenRecent(M._filePath);
    		   }
    		   else
    		   {
    			   JOptionPane.showMessageDialog(M, "Need a file *.project!");

	    		   
	    	   }

	    }
	    return s;
	
	}
	
	
	
	
	public void addOpenRecent(String _filePath)
	{
		if(!_filePath.equals(null))
			{
				File directory = new File ("SPARK_temp.txt");
			 	
    			try 
    			{ 
    			 	String _saveRecentPath = directory.getAbsolutePath();	
        			
    				//System.out.println("Remembering opened file name. Path: " +  _saveRecentPath);
    				
    			 	
    			 	//check for repeated names
    				String line="";
    				boolean ifAlreadyRecorded = false;
    				BufferedReader in;
    				BufferedWriter out;
    				
    				
    				if(directory.exists())
    				{
    					System.out.println("File extists.");
    				}
    				else
    				{
    					System.out.println("Creating file.");
    					
        				out = new BufferedWriter(new FileWriter(_saveRecentPath, true));
          				 
        				out.newLine();
        				out.write(_filePath);
        				out.close();
    				}
    				

    				
    				
    				
    				in = new BufferedReader(new FileReader(_saveRecentPath));
    				   
    				
    				while ((line = in.readLine()) != null)
    				{
    					if(line.equals(_filePath))
    						ifAlreadyRecorded = true;
       				}
    				
    				in.close();
    			 	
    			 	
    				
    				
    				
    				//adding path
    				if(!ifAlreadyRecorded)
    				{
        				out = new BufferedWriter(new FileWriter(_saveRecentPath, true));
       				 
        				out.newLine();
        				out.write(_filePath);
        				out.close();
    				}

    				
    				
    				
    				//memorizing
    				
    				line="";
    				in = new BufferedReader(new FileReader(_saveRecentPath));
    				M._openedRecent = new ArrayList<String>();
    				
    				while ((line = in.readLine()) != null)
    				{
    					if(!line.equals(""))
    						M._openedRecent.add(line);
    				}
    				
    				in.close();
    				

    				out = new BufferedWriter(new FileWriter(_saveRecentPath, false));
    				ArrayList<String> _openedRecentTemp = new ArrayList<String>();
    				
    				for(int i=Math.max(0,(M._openedRecent.size()-10)); i<M._openedRecent.size(); i++)
    				{
        				out.write(M._openedRecent.get(i));
        				_openedRecentTemp.add(M._openedRecent.get(i));
        				if(i!=(M._openedRecent.size()-1))
        				out.newLine();
    				}

    				out.close();
    				
    				M._openedRecent.clear();
    				M._openedRecent = _openedRecentTemp;
    				
    				
    			} catch (IOException e) { } 
			
			}
	}
	
	
	
	public void readOpenedRecentFile()
	{
			try 
			{ 
				File directory = new File ("SPARK_temp.txt");
				String _saveRecentPath = directory.getAbsolutePath();	
    			
				//memorizing
				
				String line="";
				BufferedReader in = new BufferedReader(new FileReader(_saveRecentPath));
				M._openedRecent = new ArrayList<String>();
				
				while ((line = in.readLine()) != null)
				{
					M._openedRecent.add(line);
				}
				
				in.close();
				
			} catch (IOException e) { } 
		
		
	}
	
	
	
	
	public void addNoise()
	{
		if(!M._filePath.equals(null))
			{
			 	File directory = new File ("c:/examples/Demo_01.txt");

			 	ArrayList<Float> _xList = new ArrayList<Float>();
			 	ArrayList<Float> _yList = new ArrayList<Float>();
			 	float _y;
			
    			try 
    			{ 

    				//memorizing
    				
    				String line="";
    				String[] lines;
    				BufferedReader in = new BufferedReader(new FileReader(directory));
    				
    				while ((line = in.readLine()) != null)
    				{
    					if(!line.equals(""))
    					{
    						lines = line.split("	");
    						_xList.add(Float.parseFloat(lines[0].replace(',', '.')));
       						_y = Float.parseFloat(lines[1].replace(',', '.'));
       						_y = _y+ 0.002f-0.004f*(float)Math.random();
       						_yList.add(_y);
    					}
    				}
    				
    				in.close();
    				
    				BufferedWriter out = new BufferedWriter(new FileWriter(directory, false));
    				
    				for(int i=0; i<_xList.size(); i++)
    				{
        				out.write("" + _xList.get(i) + "	" + _yList.get(i) );
        				out.newLine();
    				}

    				out.close();
    				
    				
    			} catch (IOException e) { } 
			
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String globalFitOpenModel() 
	{
	    String s="No file",s1="TimeSeries";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("model", "model");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(M.file);
	    int returnVal = chooser.showOpenDialog(M);
	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	M.file = chooser.getSelectedFile();
	       
	       if(M._saveNameEmpty)
	       {
	    	   M._saveNameEmpty = false;
	    	   M.saveName = M.file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=M.file.getName();
	       s1 = s.substring(s.lastIndexOf('.')+1);

    		   charSeq = "model";
    		   if(s.contains(charSeq))
    		   {
	    		   System.out.println("Open file");
	    		   proccessGlobalFitModelFile(M.file);
	    		   M._filePath = M.file.getPath();
    		   }
    		   else
    		   {
    			   JOptionPane.showMessageDialog(M, "Need a file *.model!");

	    		   
	    	   }

	    }
	    return s;
	
	}
	
	
	
	
	
	
	public String globalFitOpenSpectra() 
	{
	    String s="No file",s1="TimeSeries";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("spec", "spec");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(M.file);
	    int returnVal = chooser.showOpenDialog(M);
	    //int a = JOptionPane.showConfirmDialog(null, null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	M.file = chooser.getSelectedFile();
	       
	       if(M._saveNameEmpty)
	       {
	    	   M._saveNameEmpty = false;
	    	   M.saveName = M.file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=M.file.getName();
	       s1 = s.substring(s.lastIndexOf('.')+1);

    		   charSeq = "spec";
    		   if(s.contains(charSeq))
    		   {
	    		   System.out.println("Open file");
	    		   proccessGlobalFitSpectraFile(M.file);
	    		   M._filePath = M.file.getPath();
    		   }
    		   else
    		   {
    			   JOptionPane.showMessageDialog(M, "Need a file *.spec!");

	    		   
	    	   }

	    }
	    return s;
	
	}
	
	
	
	
	public String globalFitOpenOneSpectrum() 
	{
	    String s="No file";
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv, txt", "csv", "txt");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(M.file);
	    int returnVal = chooser.showOpenDialog(M);
	    //int a = JOptionPane.showConfirmDialog(null, null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) 
	    {
	    	M.file = chooser.getSelectedFile();
	       
	       if(M._saveNameEmpty)
	       {
	    	   M._saveNameEmpty = false;
	    	   M.saveName = M.file;
	       }


	       CharSequence charSeq,charSeq1,charSeq2;
	       
	       s=M.file.getName();
	       //s1 = s.substring(s.lastIndexOf('.')+1);

    		   charSeq = "csv";
    		   charSeq1 = "txt";
    		   if((s.contains(charSeq))||(s.contains(charSeq1)))
    		   {
	    		   System.out.println("Open file");
	    		   proccessGlobalFitOneSpectrumFile(M.file, s.substring(s.length()-3, s.length()));
	    		   M._filePath = M.file.getPath();
    		   }
    		   else
    		   {
    			   JOptionPane.showMessageDialog(M, "Unable to read the file!");

	    		   
	    	   }

	    }
	    return s;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	public void globalFitSaveProject()
	{
		String newName;
		
		//Writing n of eq
		//Writing n of constants
		//Writing diff eq labels
		//Writing concentrations
		//Writing burnt concentrations
		//Writing constants
		//Writing cuvette thickness
		//Writing X labels
		//Writing spectra
		//Writing kinetics data


		

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_project.project"; 
	    }
	    else
	    {
	    	newName = "Unnamed.project";
	    }
 

	       	File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("project", "project");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        //JOptionPane.showConfirmDialog("The file exists! Overwrite?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	        //final JOptionPane optionPane = new JOptionPane("The file exists! Overwrite?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	        //int a = (Integer)optionPane.showConfirmDialog(JOptionPane.QUESTION_MESSAGE);
	        
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
	    	            M.saveName = saveFile;
	    	           //file = saveFile;
	    	    		System.out.println("New Path is: " + newName);
	    	    		addOpenRecent(newName);
	    	    		
	    	    		try 
	    				{ 
	    					
	    					//BufferedReader in = new BufferedReader(new FileReader(file));
	    					BufferedWriter out = new BufferedWriter(new FileWriter(newName));
	    					String line="";


	    					
	    					//Writing diff eq labels
	    					
	    					System.out.println("Writing labels");
	    					line = "___diffeq";
	    					out.write(line);
	    					out.newLine();
	    					
	    					line = "_nOfEq"+ M._nOfEq;
	    					out.write(line);
	    					out.newLine();
	    					
	    					for(int i=0; i<M._nOfEq; i++)
	    					{
	    						if(M._eqLabelsContent[i]!=null)
	    						{
	    							line = M._eqLabelsContent[i];
	    						}
	    						else
	    						{
	    							line = "";
	    						}

	    						out.write(line);
	    						out.newLine();
	    					}
	    					
	    					
	    					line = "___concentrations";
	    					out.write(line);
	    					out.newLine();
	    					
	    					for(int i=0; i<M._nOfEq; i++)
	    					{
	    						if(M._concentrationLabelsContent[i]!=null)
	    						{
	    							line = M._concentrationLabelsContent[i];
	    						}
	    						else
	    						{
	    							line = "";
	    						}

	    						out.write(line);
	    						out.newLine();
	    					}
	    					
	    					
	    					line = "___beforepulseconcentrations";
	    					out.write(line);
	    					out.newLine();
	    					
	    					for(int i=0; i<M._nOfEq; i++)
	    					{
	    						if(M._beforePulseConcentrationLabelsContent[i]!=null)
	    						{
	    							line = M._beforePulseConcentrationLabelsContent[i];
	    						}
	    						else
	    						{
	    							line = "";
	    						}

	    						out.write(line);
	    						out.newLine();
	    					}
	    					
	    					
	    					
	    					
	    					
	    					line = "___constants";
	    					out.write(line);
	    					out.newLine();
	    					
	    					line = "_nOfConst"+ M._nOfConst;
	    					out.write(line);
	    					out.newLine();
	    					
	    					for(int i=0; i<M._nOfConst; i++)
	    					{
	    						if(M._constantLabelsContent[i]!=null)
	    						{
	    							line = M._constantLabelsContent[i];
	    						}
	    						else
	    						{
	    							line = "";
	    						}

	    						out.write(line);
	    						out.newLine();
	    					}
	    					
	    					
	    					line = "___cuvettethickness";
	    					out.write(line);
	    					out.newLine();
	    					line = "" + M._cuvetteThickness;
	    					out.write(line);
	    					out.newLine();
	    					
	    					
	    					line = "___timescale";
	    					out.write(line);
	    					out.newLine();
	    					line = "" + M._solvedCurveTimeScale;
	    					out.write(line);
	    					out.newLine();
	    					
	    					
	    					
	    					line = "___xlabels";
	    					out.write(line);
	    					out.newLine();
	    					for(int i=0; i<M._nOfEq; i++)
	    					{
	    						if(M._xSNamesArray[i]!=null)
	    						{
	    							line = M._xSNamesArray[i];
	    						}
	    						else
	    						{
	    							line = "";
	    						}

	    						out.write(line);
	    						out.newLine();
	    					}
	    					
	    					
	    					



	    					
	    					
	    					
	    					//Writing kinetics data
	    					if(M._availWaveCollection.size()!=0)
	    					{
	    						System.out.println("Writing kinetics data");
	    						line = "___kinetics";
	    						out.write(line);
	    						out.newLine();
	    						
	    						line = "_nOfKins"+M._availWaveCollection.size();
	    						out.write(line);
	    						out.newLine();
	    						
	    						for(int i=0; i<M._kinCollectionX.size();i++)
	    						{
	    							for(int j=0; j<M._kinCollectionX.get(i).size(); j++)
	    							{
	    								line = "" + M._kinCollectionX.get(i).get(j) + "	" + M._kinCollectionY.get(i).get(j);
	    								out.write(line);
	    								out.newLine();
	    							}
	    							line = "end";
	    							out.write(line);
	    							out.newLine();
	    						}
	    						
	    					//Writing markers
	    						line = "___markers";
	    						out.write(line);
	    						out.newLine();

	    						for(int i=0; i<M._markersCollection.size(); i++)
	    						{
	    							line = "" + M._markersCollection.get(i).get(0);
		    						out.write(line);
		    						out.newLine();
	    							line = "" + M._markersCollection.get(i).get(1);
		    						out.write(line);
		    						out.newLine();
	    							line = "end";
		    						out.write(line);
		    						out.newLine();
	    						}
	    						


	    					}
	    					
	    					
	    					//Writing spectra data
	    					if(M._availWaveCollection.size()!=0)
	    					{
	    						System.out.println("Writing spectra data");
	    						line = "___spectra";
	    						out.write(line);
	    						out.newLine();
	    						
	    						for(int i=0; i<M._availWaveCollection.size();i++)
	    						{
	    							line = "" + M._availWaveCollection.get(i);
	    							
	    							for(int j=0; j<M._xSAbsSpecCollectionYFiltered.size(); j++)
	    							{
	    								line = line + "	" + (-M._xSAbsSpecCollectionYFiltered.get(j).get(i));

	    							}
	    							
	    							out.write(line);
	    							out.newLine();

	    						}
	    						
	    						line = "end";
	    						out.write(line);
	    						out.newLine();

	    					}
	    					
	    					//in.close(); //closing files
	    					out.close();
	    					
	    					//_status.setText("Converted successfuly. Saved as: " + newName);

	    				
	    				} catch (IOException e) { } 	
	             }
	                
	                
	            

	        
	        }
		
		
	}
	
	
	
	
	public void globalFitSaveModel()
	{
		String newName;
		

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_model.model"; 
	    }
	    else
	    {
	    	newName = "Unnamed.model";
	    }
 

	       File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("model", "model");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        if (rval == JFileChooser.APPROVE_OPTION) 
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
		            M.saveName = saveFile;
		           //file = saveFile;
		    		System.out.println("New Path is: " + newName);
		    		
		    		try 
					{ 
						
						//BufferedReader in = new BufferedReader(new FileReader(file));
						BufferedWriter out = new BufferedWriter(new FileWriter(newName));
						String line="";


						
						//Writing diff eq labels
						
						System.out.println("Writing labels");
						line = "___diffeq";
						out.write(line);
						out.newLine();
						
						line = "_nOfEq"+ M._nOfEq;
						out.write(line);
						out.newLine();
						
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._eqLabelsContent[i]!=null)
							{
								line = M._eqLabelsContent[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
						
						line = "___concentrations";
						out.write(line);
						out.newLine();
						
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._concentrationLabelsContent[i]!=null)
							{
								line = M._concentrationLabelsContent[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
						
						line = "___beforepulseconcentrations";
						out.write(line);
						out.newLine();
						
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._beforePulseConcentrationLabelsContent[i]!=null)
							{
								line = M._beforePulseConcentrationLabelsContent[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
						
						
						
						
						line = "___constants";
						out.write(line);
						out.newLine();
						
						line = "_nOfConst"+ M._nOfConst;
						out.write(line);
						out.newLine();
						
						for(int i=0; i<M._nOfConst; i++)
						{
							if(M._constantLabelsContent[i]!=null)
							{
								line = M._constantLabelsContent[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
						
						line = "___cuvettethickness";
						out.write(line);
						out.newLine();
						line = "" + M._cuvetteThickness;
						out.write(line);
						out.newLine();
						
						
						line = "___timescale";
						out.write(line);
						out.newLine();
						line = "" + M._solvedCurveTimeScale;
						out.write(line);
						out.newLine();
						
						
						line = "___xlabels";
						out.write(line);
						out.newLine();
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._xSNamesArray[i]!=null)
							{
								line = M._xSNamesArray[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
		
						
						//in.close(); //closing files
						out.close();
						
						//_status.setText("Converted successfuly. Saved as: " + newName);

					
					} catch (IOException e) { } 	


	            }
	    		
				
	        
	        }
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void globalFitSaveAllSpec()
	{
		String newName;

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_spectra.spec"; 
	    }
	    else
	    {
	    	newName = "Unnamed.spec";
	    }
 

	       File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("spec", "spec");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        if (rval == JFileChooser.APPROVE_OPTION) 
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
		            M.saveName = saveFile;
		           //file = saveFile;
		    		System.out.println("New Path is: " + newName);
		    		
		    		try 
					{ 
						
						//BufferedReader in = new BufferedReader(new FileReader(file));
						BufferedWriter out = new BufferedWriter(new FileWriter(newName));
						String line="";

						line = "___xlabels";
						out.write(line);
						out.newLine();
						for(int i=0; i<M._nOfEq; i++)
						{
							if(M._xSNamesArray[i]!=null)
							{
								line = M._xSNamesArray[i];
							}
							else
							{
								line = "";
							}

							out.write(line);
							out.newLine();
						}
						
						
						

						if(M._availWaveCollection.size()!=0)
						{
							System.out.println("Writing spectra data");
							line = "___spectra";
							out.write(line);
							out.newLine();
							
							for(int i=0; i<M._availWaveCollection.size();i++)
							{
								line = "" + M._availWaveCollection.get(i);
								
								for(int j=0; j<M._xSAbsSpecCollectionYFiltered.size(); j++)
								{
									line = line + "	" + (-M._xSAbsSpecCollectionYFiltered.get(j).get(i));
									if(M._ifShowErrorExt)
										line = line + "	" + (M._xSAbsSpecErrorCollection.get(j).get(i));

								}
								
								out.write(line);
								out.newLine();

							}
							
							line = "end";
							out.write(line);
							out.newLine();

						}


						out.close();


					
					} catch (IOException e) { } 	

	            }


	    		
				
	        
	        }
	
	
	}
	
	
	
	
	
	public void globalFitSaveOneSpec()
	{
		String newName;

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_spectrum.txt"; 
	    }
	    else
	    {
	    	newName = "Unnamed.txt";
	    }
 

	       File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        if (rval == JFileChooser.APPROVE_OPTION) 
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
		            M.saveName = saveFile;
		           //file = saveFile;
		    		System.out.println("New Path is: " + newName);
		    		
		    		try 
					{ 
						
						//BufferedReader in = new BufferedReader(new FileReader(file));
						BufferedWriter out = new BufferedWriter(new FileWriter(newName));
						String line="";

						line = "___xlabels";
						out.write(line);
						out.newLine();

							if(M._xSNamesArray[M._globalFitPosOfSelectedSpec]!=null)
							{
								line = M._xSNamesArray[M._globalFitPosOfSelectedSpec];
							}
							else
							{
								line = "";
							}
							out.write(line);
							out.newLine();

						
						

						if(M._availWaveCollection.size()!=0)
						{
							System.out.println("Writing spectra data");
							line = "___spectra";
							out.write(line);
							out.newLine();
							
							for(int i=0; i<M._availWaveCollection.size();i++)
							{
								line = "" + M._availWaveCollection.get(i);
								

									line = line + "	" + (-M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).get(i));

									if(M._ifShowErrorExt)
										line = line + "	" + (M._xSAbsSpecErrorCollection.get(M._globalFitPosOfSelectedSpec).get(i));
								
								out.write(line);
								out.newLine();

							}
							
							line = "end";
							out.write(line);
							out.newLine();

						}


						out.close();


					
					} catch (IOException e) { } 	

		    		
	            }

				
	        
	        }
	
	
	}
	
	
	
	
	public void globalFitSaveOneTransientSlice()
	{
		String newName;

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_transient_spec.txt"; 
	    }
	    else
	    {
	    	newName = "Unnamed_transient_spec.txt";
	    }
 

	       File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        if (rval == JFileChooser.APPROVE_OPTION) 
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
		            M.saveName = saveFile;
		           //file = saveFile;
		    		System.out.println("New Path is: " + newName);
		    		
		    		try 
					{ 
						
						//BufferedReader in = new BufferedReader(new FileReader(file));
						BufferedWriter out = new BufferedWriter(new FileWriter(newName));
						String line="";

						if(M._availWaveCollection.size()!=0)
						{
							
							for(int i=0; i<M._availWaveCollection.size();i++)
							{
								line = "" + M._availWaveCollection.get(i);
								
								line = line + "	" + (-M.get_kinCollectionY().get(i).get(M.get_globalFitPosOfCurrentTimePoint()));

								out.write(line);
								out.newLine();
							}
						}
						out.close();
					
					} catch (IOException e) { } 	
	            }
	        }
	}
	
	
	
	
	
	
	
	public void globalFitSaveAllSlices()
	{
		String newName;

	    if(!M._filePath.equals(""))
	    {
			newName = M._filePath.subSequence(0,M._filePath.lastIndexOf('.'))+ "_transient_spec.txt"; 
	    }
	    else
	    {
	    	newName = "Unnamed_transient_spec.txt";
	    }
 

	       File saveFile = new File(newName);
	        JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		    chooser.setFileFilter(filter);
	 
	        chooser.setSelectedFile(saveFile);
	        chooser.setCurrentDirectory(M.saveName);
	        int rval = chooser.showSaveDialog(M);
	        if (rval == JFileChooser.APPROVE_OPTION) 
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
		            M.saveName = saveFile;
		           //file = saveFile;
		    		System.out.println("New Path is: " + newName);
		    		
		    		try 
					{ 
						
						//BufferedReader in = new BufferedReader(new FileReader(file));
						BufferedWriter out = new BufferedWriter(new FileWriter(newName));
						String line="";

						if(M._availWaveCollection.size()!=0)
						{
							
							for(int i=0; i<M._availWaveCollection.size();i++)
							{
								line = "" + M._availWaveCollection.get(i);
								
								line = line + "	" + (-M.get_kinCollectionY().get(i).get(M.get_globalFitPosOfCurrentTimePoint()));
								
				    			   if(M.get_ifShowTransientSpec())
				        			   line= line + "	" + (-M.get_kinCollectionY().get(i).get(M.get_globalFitPosOfCurrentTimePoint()));
				        		   if(M.get_ifShowCalcSpec())
				        		   {
				        			   if(M.get_solvedCurveCollectionY().size()!=0)
				        				   line= line + "	" +(-M.get_solvedCurveCollectionY().get(i).get(M.get_globalFitPosOfCurrentTimePoint()));
				        		   }
				            	   if(M.get_ifShowContributionsSpec())
				            	   {
				           			   if(M.get_solvedCurveContributionCollectionY().size()!=0)
				           				   if(M.get_solvedCurveContributionCollectionY().get(0).size()!=0)
				           					   for(int j=0; j<M.get_solvedCurveContributionCollectionY().size(); j++)
				           						 line= line + "	" + (-M.get_solvedCurveContributionCollectionY().get(j).get(i).get(M.get_globalFitPosOfCurrentTimePoint()));
				            		   
				            	   }


								out.write(line);
								out.newLine();
							}
						}
						out.close();
					
					} catch (IOException e) { } 	
	            }
	        }
	}	
	
	
	
	
	
	
	
	
	
	
	
	

	public void proccessGlobalFitProjectFile(File file)
	{
		
		int _lc = 0, _index=0, _nOfKins=0, _indexOfKin=0, _nOfSpec=0;		
		float _xPoint=0, _yPoint=0;
		String[] _values;
		String _line;
		String _status="none";
		
		
		M._arraySize = 0;
		M._xMin=100000;
		M._xMax=-100000;
		M._yMin=100000;
		M._yMax=-100000;
		M._magPosX=0;
		M._magPosY=0;
		
		if(M._availWaveCollection.size()==0)
		{
			M._globalFitXMin=100000;
			M._globalFitXMax=-100000;
			M._globalFitYMin=100000;
			M._globalFitYMax=-100000;
		}
			
		ArrayList<ArrayList<Float>> _tempSuperArrayX = new ArrayList<ArrayList<Float>>();
		ArrayList<ArrayList<Float>> _tempSuperArrayY = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> _tempArrayX = new ArrayList<Float>();
		ArrayList<Float> _tempArrayY = new ArrayList<Float>();

		
		M.globalFitClearAll();
		//createVariablesLimitsArray();
		
	
		System.out.println("Start reading the File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));

			while ((_line = in.readLine()) != null)
			{ 
				_lc++; // Line counter

				
				_values = _line.split("	");		//split the string
				
				//System.out.println(_values[0]);
				
				
				if(_status.equals("___diffeq"))
				{
					if(_index==-1)
					{
						M._nOfEqOpened = Integer.parseInt(_values[0].substring(6));
						
						
					}
					
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._eqLabelsContent[_index]=_values[0];
					_index++;
				}
				
				
				if(_status.equals("___constants"))
				{
					if(_index==-1)
						M._nOfConstOpened = Integer.parseInt(_values[0].substring(9));
					
					if((_index>-1)&(_index<M._nOfConstOpened))
						M._constantLabelsContent[_index]=_values[0];
					_index++;
				}				
				
				
				if(_status.equals("___cuvettethickness"))
				{

					if(_index==0)
						M._cuvetteThickness = Float.parseFloat(_values[0]);
					_index++;
				}	
				
				if(_status.equals("___timescale"))
				{
					if(_index==0)
						M._solvedCurveTimeScale = Float.parseFloat(_values[0]);
					_index++;
				}	
				
				
				if(_status.equals("___concentrations"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._concentrationLabelsContent[_index]=_values[0];
					_index++;
				}
				
				
				if(_status.equals("___beforepulseconcentrations"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._beforePulseConcentrationLabelsContent[_index]=_values[0];
					_index++;
				}		
				
				
				if(_status.equals("___xlabels"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._xSNamesArray[_index]=_values[0];
					_index++;
				}	
				
				
				
				
				
				if(_status.equals("___kinetics"))
				{
					if(_index==-1)
					{
						_nOfKins = Integer.parseInt(_values[0].substring(8));
						
						System.out.println("nOfKins = " + _nOfKins);
						//for(int i=0; i<_nOfKins; i++)
						//{
							//_tempSuperArrayX.add(_tempArrayX);
							//_tempSuperArrayY.add(_tempArrayY);
						//}
						//System.out.println("size1 = " + _tempSuperArrayX.size());
						_indexOfKin=0;
					}
					
					if(_values[0].equals("end"))
					{
						_index=-1;
						_indexOfKin++;
						
						float[] temp = new float[4];
						temp[0]=M._xMin; temp[1]=M._xMax; temp[2]=M._yMin; temp[3]= M._yMax;
						M._kinShiftCollection.add(temp);
						
						
						ArrayList<Integer> _tempMarkersArray = new ArrayList<Integer>();
						_tempMarkersArray.add(1);
						_tempMarkersArray.add((_tempArrayX.size()-1));
						M._markersCollection.add(_tempMarkersArray);
						
						
						if(M._globalFitXMax<M._xMax){M._globalFitXMax=M._xMax;}	
						if(M._globalFitYMax<M._yMax){M._globalFitYMax=M._yMax;}
						if(M._globalFitXMin>M._xMin){M._globalFitXMin=M._xMin;}	
						if(M._globalFitYMin>M._yMin){M._globalFitYMin=M._yMin;}	
						
						
						//float[] temp1 = new float[4];
						//temp1[0]=_xMin; temp1[1]=_xMax; temp1[2]=_yMin; temp1[3]= _yMax;
						//_kinShiftCollection.add(temp1);
						
						_tempSuperArrayX.add(_tempArrayX);
						_tempSuperArrayY.add(_tempArrayY);
						
						
						M.addNewWavelengthInXSSpec(_tempArrayX.get(0));
						
						_tempArrayX = new ArrayList<Float>();
						_tempArrayY = new ArrayList<Float>();
						
						M._xMin=100000;
						M._xMax=-100000;
						M._yMin=100000;
						M._yMax=-100000;
						
						
					}
					//if((_index>-1)&(_index<_nOfEqOpened))
					//_xSNamesArray[_index]=_values[0];
					
					if((_index>-1)&(_indexOfKin<_nOfKins))
					{
						_xPoint = Float.parseFloat(_values[0]);
						_yPoint = Float.parseFloat(_values[1]);
						_tempArrayX.add(_xPoint);
						_tempArrayY.add(_yPoint);
						
						if(_index>0)
						{
							if(M._xMax<_xPoint){M._xMax=_xPoint;}
							if(M._yMax<_yPoint){M._yMax=_yPoint;}
							if(M._xMin>_xPoint){M._xMin=_xPoint;}
							if(M._yMin>_yPoint){M._yMin=_yPoint;}
						}

					}
					
						
					_index++;
				}	
				
				
				
				if(_status.equals("___spectra"))
				{
	
					if(_values[0].equals("end"))
					{
						_index=-2;
					}
					
					
					if((_index>-1))
					{
						
						//_availWaveCollection.add(Float.parseFloat(_values[0]));
						
						for(int i=1; i<_values.length; i++)
						{
							M._xSAbsSpecCollectionYFiltered.get(i-1).set(_index, -Float.parseFloat(_values[i]));
						}


					}
					
						
					_index++;
				}	
				
				
				if(_status.equals("___markers"))
				{
					
					if(_values[0].equals("end"))
					{
						_indexOfKin++;
						_index=-1;
					}
					
					
					if((_index==0)&(_indexOfKin<_nOfKins))
					{
						M._markersCollection.get(_indexOfKin).set(0, Integer.parseInt(_values[0]));
					}
					
					if((_index==1)&(_indexOfKin<_nOfKins))
					{
						M._markersCollection.get(_indexOfKin).set(1, Integer.parseInt(_values[0]));
					}
						
					_index++;
				}	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

				
				if(_values[0].equals("___diffeq"))
				{
					_status="___diffeq";
					_index=-1;
				}

				if(_values[0].equals("___constants"))
				{
					_status="___constants";
					_index=-1;
				}			

				if(_values[0].equals("___cuvettethickness"))
				{
					_status="___cuvettethickness";
					_index=0;
				}	
				
				if(_values[0].equals("___timescale"))
				{
					_status="___timescale";
					_index=0;
				}	
				
						
				if(_values[0].equals("___concentrations"))
				{
					_status="___concentrations";
					_index=0;
				}					
					
				if(_values[0].equals("___beforepulseconcentrations") || _values[0].equals("___burntconcentrations"))
				{
					_status="___beforepulseconcentrations";
					_index=0;
				}				
				
				if(_values[0].equals("___xlabels"))
				{
					_status="___xlabels";
					_index=0;
				}					
				
				if(_values[0].equals("___kinetics"))
				{
					_status="___kinetics";
					_index=-1;
				}
				
				if(_values[0].equals("___markers"))
				{
					_status="___markers";
					_indexOfKin=0;
					_index=0;
				}	
				
				if(_values[0].equals("___spectra"))
				{
					_status="___spectra";
					_index=0;
					
					
					if(M._nOfEqOpened>M._nOfEq)
					{
						for(int i=0; i<(M._nOfEqOpened-M._nOfEq); i++)
							M.addNewXDefaultSpec();
					}
					
				}
			
			
			}
			in.close(); //closing files
		} catch (IOException e) 
		
		{
			
			//JOptionPane.showConfirmDialog(this,"Can't open the file.");

		} 
		
		
		
		
		
		
		M._globalFitPosOfSelectedKin = 0;
		
		M._kinCollectionX = _tempSuperArrayX;
		M._kinCollectionY = _tempSuperArrayY;
		System.out.println("size = " + M._availWaveCollection.size());
		
		M._xScaler = M._gridSizeX/(M._globalFitXMax-M._globalFitXMin);
		M._yScaler = M._gridSizeY/(M._globalFitYMax-M._globalFitYMin);
		M._xScalerIni = M._xScaler;
		M._yScalerIni = M._yScaler;
		
		M._markerShiftStep = Math.round(M._arraySize/300);
		M._markerShiftStepFast = Math.round(M._arraySize/20);
		
		M._zeroShiftStep = (M._xMax-M._xMin)/500;
		M._zeroShiftStepFast = Math.round((M._xMax-M._xMin)/20);

		
		//_xArray.add(_xArrayIni.get(0)); 
		//_yArray.add(_yArrayIni.get(0));
		 
		//for(int i=1;i<(_xArrayIni.size()); i++)
		// {
			
		//	 _xArray.add(_xArrayIni.get(i));//-_xMin); 
		//	 _yArray.add(_yArrayIni.get(i));//-_yMin);
		// }
		
		//float[] temp = new float[4];
		//temp[0]=_xMin; temp[1]=_xMax; temp[2]=_yMin; temp[3]= _yMax;
		//_kinShiftCollection.add(temp);
		//_kinCollectionXIni.add(_xArrayIni);
		//_kinCollectionYIni.add(_yArrayIni);
		//_kinCollectionX.add(_xArray);
		//_kinCollectionY.add(_yArray);
		
		
		
		
		///need to sort according to wavelengths
		
		M.sortKinCollections();
		
		
		
		
		
		 //_levelPosDisplay = 0f;
		 //_zeroPosDisplay = 0f;
		// _beforeZeroPosDisplay = 0f;
		M._levelPosReal = M.setLevel(M._levelPosDisplay,Constants._kineticsModeGridPosition.y,M._globalFitYMin,M._yScaler);
		M._zeroPosReal = M.setZero(M._zeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
		M._slicePosReal = M.setZero(M._slicePosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);

		M.myRect.y = M._levelPosReal-5-M._magPosY;
		M.myZeroRect.x = M._zeroPosReal-5-M._magPosX;
		M.myBeforeZeroRect.x = M._beforeZeroPosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
		M.sliceRect.x = M._slicePosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
		M._yZeroPos = Math.round(M._levelPosReal);
		M._xZeroPos = Math.round(M._zeroPosReal);
		 
		
		
		M._globalFitPosOfSelectedSpec=0;
		
		
		
		M.extGraphPanelRecalcSpecBounds();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//System.out.println("nOfEqOpened= " + _nOfEqOpened);
		//System.out.println("nOfConstOpened= " + _nOfConstOpened);
		//System.out.println("_cuvetteThickness= " + _cuvetteThickness);
		//System.out.println("nOfEq= " + _nOfEq);
		//System.out.println("nOfConst= " + _nOfConst);

		
		
	}
	
	
	
	public void proccessGlobalFitModelFile(File file)
	{
		
		int _lc = 0, _index=0;			
		String[] _values;
		String _line;
		String _status="none";
			
		System.out.println("Start reading the File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));

			while ((_line = in.readLine()) != null)
			{ 
				_lc++; // Line counter

				
				_values = _line.split("	");		//split the string
				
				System.out.println(_values[0]);
				
				
				if(_status.equals("___diffeq"))
				{
					if(_index==-1)
						M._nOfEqOpened = Integer.parseInt(_values[0].substring(6));
					
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._eqLabelsContent[_index]=_values[0];
					_index++;
				}
				
				
				if(_status.equals("___constants"))
				{
					if(_index==-1)
						M._nOfConstOpened = Integer.parseInt(_values[0].substring(9));
					
					if((_index>-1)&(_index<M._nOfConstOpened))
						M._constantLabelsContent[_index]=_values[0];
					_index++;
				}				
				
				
				if(_status.equals("___cuvettethickness"))
				{
					if(_index==0)
						M._cuvetteThickness = Float.parseFloat(_values[0]);
					_index++;
				}	
				
				if(_status.equals("___timescale"))
				{
					if(_index==0)
						M._solvedCurveTimeScale = Float.parseFloat(_values[0]);
					//System.out.println(_solvedCurveTimeScale);
					_index++;
				}	
				
				
				if(_status.equals("___concentrations"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._concentrationLabelsContent[_index]=_values[0];
					_index++;
				}
				
				
				if(_status.equals("___beforepulseconcentrations"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._beforePulseConcentrationLabelsContent[_index]=_values[0];
					_index++;
				}		
				
				
				if(_status.equals("___xlabels"))
				{
			
					if((_index>-1)&(_index<M._nOfEqOpened))
						M._xSNamesArray[_index]=_values[0];
					_index++;
				}	
				
				

				
				if(_values[0].equals("___diffeq"))
				{
					_status="___diffeq";
					_index=-1;
				}

				if(_values[0].equals("___constants"))
				{
					_status="___constants";
					_index=-1;
				}			

				if(_values[0].equals("___cuvettethickness"))
				{
					_status="___cuvettethickness";
					_index=0;
				}					
				
				if(_values[0].equals("___timescale"))
				{
					_status="___timescale";
					_index=0;
				}	
						
				if(_values[0].equals("___concentrations"))
				{
					_status="___concentrations";
					_index=0;
				}					
					
				if(_values[0].equals("___beforepulseconcentrations") || _values[0].equals("___burntconcentrations"))
				{
					_status="___beforepulseconcentrations";
					_index=0;
				}				
				
				if(_values[0].equals("___xlabels"))
				{
					_status="___xlabels";
					_index=0;
				}					
				

			
			
			}
			in.close(); //closing files
				
		} catch (IOException e) 
		
		{
			
			//JOptionPane.showConfirmDialog(this,"Can't open the file.");

		} 
		
		
		//System.out.println("nOfEqOpened= " + _nOfEqOpened);
		//System.out.println("nOfConstOpened= " + _nOfConstOpened);
		//System.out.println("_cuvetteThickness= " + _cuvetteThickness);
		//System.out.println("nOfEq= " + _nOfEq);
		//System.out.println("nOfConst= " + _nOfConst);

		
		
	}
	
	
	
	
	
	
	public void proccessGlobalFitSpectraFile(File file)
	{
		
		int _lc = 0, _index=0, _availWavelengthCounter=0,_nOfKins=0, _indexOfKin=0, _nOfSpec=0;		
		float _xPoint=0, _yPoint=0;
		String[] _values;
		String _line;
		String _status="none";
		
		//M._xSAbsSpecCollectionXIni = new ArrayList<ArrayList<Float>>();
		//M._xSAbsSpecCollectionYIni = new ArrayList<ArrayList<Float>>();
		M._xSAbsSpecCollectionYFiltered = new ArrayList<ArrayList<Float>>();
		M._absRectCollection = new ArrayList<ArrayList<Rectangle2D.Float>>();
		
	
		
		for(int i=0; i<M._nOfEq; i++)
			M.addNewXDefaultSpec();
		
		M.extGraphPanelSetDefaultBounds();
		
	
		
		
		System.out.println("Start reading the File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));

			while ((_line = in.readLine()) != null)
			{ 
				_lc++; // Line counter

				
				_values = _line.split("	");		//split the string
				
				//System.out.println(_values[0]);
				
				

				
				if(_status.equals("___xlabels"))
				{
			
					if((_index>-1))
						M._xSNamesArray[_index]=_values[0];
					_index++;
					M._nOfEqOpened=_index-1;
				}	
				
				
				
				
				if(_status.equals("___spectra"))
				{
	
					if(_values[0].equals("end"))
					{
						_index=-2;
					}
					
					
					if((_index>-1))
					{
						
						//_availWaveCollection.add(Float.parseFloat(_values[0]));
						
						
						int _pos = findIndexOfWavelengthAvailable(Float.parseFloat(_values[0]));
						
						if(_pos!=-1)
						{
							for(int i=1; i<_values.length; i++)
							{
								
								M._xSAbsSpecCollectionYFiltered.get(i-1).set(_pos, -Float.parseFloat(_values[i]));
							}
							_availWavelengthCounter++;
						}


					}
					
						
					_index++;
				}	
				
				
				
				
				
				///************************************************/

					
				if(_values[0].equals("___xlabels"))
				{
					_status="___xlabels";
					_index=0;
				}					
				

				if(_values[0].equals("___spectra"))
				{
					_status="___spectra";
					_index=0;
					_availWavelengthCounter=0;
					
					
					if(M._nOfEqOpened>M._nOfEq)
					{
						for(int i=0; i<(M._nOfEqOpened-M._nOfEq); i++)
							M.addNewXDefaultSpec();
					}
					
				}
			
			
			}
			in.close(); //closing files
				
		} catch (IOException e) 
		
		{
			
			//JOptionPane.showConfirmDialog(this,"Can't open the file.");

		} 
		
		
		M.extGraphPanelRecalcSpecBounds();
		
		
		
	}
	
	
	
	
	
	
	
	public void proccessGlobalFitOneSpectrumFile(File file, String _fileType)
	{
		
		int _lc = 0, _index=0, _availWavelengthCounter=0,_nOfKins=0, _indexOfKin=0, _nOfSpec=0;		
		float _xPoint=0, _yPoint=0;
		String[] _values;
		String _line;
		String _status="none";
		
	
		

		
		System.out.println("Start reading the File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));

			
			
			if((_fileType.equals("txt"))||(_fileType.equals("TXT")))
			{
				while ((_line = in.readLine()) != null)
				{ 
					_lc++; // Line counter

					
					_values = _line.split("	");		//split the string
					

					
					if(_status.equals("___xlabels"))
					{
				
						if((_index>-1)&(_index<1))
							M._xSNamesArray[_index]=_values[0];
						_index++;
					}	
					
					
					
					
					if(_status.equals("___spectra"))
					{
		
						if(_values[0].equals("end"))
						{
							_index=-2;
						}
					
						if((_index>-1))
						{
						
							int _pos = findIndexOfWavelengthAvailable(Float.parseFloat(_values[0]));
							
							if(_pos!=-1)
							{
								M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).set(_pos, -Math.max(0,Float.parseFloat(_values[1].replace(',', '.'))));

							}

						}
							
						_index++;
					}	
					

					///************************************************/

						
					if(_values[0].equals("___xlabels"))
					{
						_status="___xlabels";
						_index=0;
					}					
					

					if(_values[0].equals("___spectra"))
					{
						_status="___spectra";
						_index=0;
						_availWavelengthCounter=0;
						
						
						if(M._nOfEqOpened>M._nOfEq)
						{
							for(int i=0; i<(M._nOfEqOpened-M._nOfEq); i++)
								M.addNewXDefaultSpec();
						}
						
					}
				
				
				}
				in.close(); //closing files
				
				if(_status.equals("none"))
				{
					// read as txt
					in = new BufferedReader(new FileReader(file));
					
					while ((_line = in.readLine()) != null)
					{ 
						_lc++; // Line counter
						
						_values = _line.split("	");		//split the string

						int _pos = findIndexOfWavelengthAvailable(Float.parseFloat(_values[0].replace(',', '.')));
								
						if(_pos!=-1)
						{
							M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).set(_pos, -Math.max(0,Float.parseFloat(_values[1].replace(',', '.'))));
						}
					}
					in.close(); //closing files
					
				}
		
				
			}
			
			
			
			if((_fileType.equals("csv"))||(_fileType.equals("CSV")))
			{
				System.out.println("Opening .csv file");
				
				while ((_line = in.readLine()) != null)
				{ 
					_lc++; // Line counter
					
					_values = _line.split(";");		//split the string

					
					if(_lc>1)
					{
						int _pos = findIndexOfWavelengthAvailable(Float.parseFloat(_values[0]));
						
						if(_pos!=-1)
						{
							M._xSAbsSpecCollectionYFiltered.get(M._globalFitPosOfSelectedSpec).set(_pos, -Math.max(0,Float.parseFloat(_values[1].replace(',', '.'))));
						}
					}

				}
				in.close(); //closing files

				
			}
			
			
			
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
				
		} catch (IOException e) 
		
		{
			
			JOptionPane.showConfirmDialog(M,"Unable to open the file!");

		} 
		
		
		M.extGraphPanelRecalcSpecBounds();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	public int findIndexOfWavelengthAvailable(float _wavelength)
	{
		int _result=-1;
		

		for(int i=0; i<M._availWaveCollection.size(); i++)
		{
			if(Math.abs(M._availWaveCollection.get(i)-_wavelength)<1)
			{
				_result = i;
			}
		}
		
		return _result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void proccessGlobalFitKinFile(File file)
	{

		ArrayList<Float> _xArrayIni = new ArrayList<Float>();
		ArrayList<Float> _yArrayIni = new ArrayList<Float>();
		ArrayList<Float> _xArray = new ArrayList<Float>();
		ArrayList<Float> _yArray = new ArrayList<Float>();
		
		

		M._arraySize = 0;
		M._xMin=100000;
		M._xMax=-100000;
		M._yMin=100000;
		M._yMax=-100000;
		M._magPosX=0;
		M._magPosY=0;
		
		if(M._availWaveCollection.size()==0)
		{
			M._globalFitXMin=100000;
			M._globalFitXMax=-100000;
			M._globalFitYMin=100000;
			M._globalFitYMax=-100000;
		}
		
		int _lc = 0, extent1;
		String s = "",s1,s2,s3;
		float _xPoint=0, _yPoint = 0;
		String[] values;		

		System.out.println("Start reading the File...");
		
		try 
		{ 
			
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			
			String line;
			String fileType="raw";
			float _wavelength=0;

		 	int j=-1;
			while(j!=0)
			{
				try
			 	{
					String input = JOptionPane.showInputDialog("Enter wavelength:");	  
					_wavelength = Float.parseFloat(input.replace(',', '.'));
					j=0;
				}
			 	catch(NumberFormatException e)
			 	{
			 		JOptionPane.showMessageDialog(M, "Enter a proper value!");
			 		j=-1;
			 	}
			}



			
			
			_xArrayIni.add(_wavelength+0f);
			_yArrayIni.add(0f);
			//_globalFitSelectedWavelength = _wavelength;
			M._globalFitPosOfSelectedKin = M._kinCollectionX.size();
	
		
			
			while ((line = in.readLine()) != null)
			{ 
				_lc++; // Line counter

				// Identifying the file type
				values = line.split(" ");		//split the string

				if (_lc==1)
				{
					CharSequence _charSeq;
					
					values = line.split(" ");		//split the string
					if(line.length()>4)
					{
						s=line.substring(0,5);
						_charSeq = "Start"; // file starts with "Start time: Tue Mar 10 12:31:26 MSK 2009" (e.g.)
					}
					else
					{
						_charSeq = "************";
					}


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
					
					M._arraySize++;
					values = line.split("	");		//split the string
					s = values[1];	
					s1 = s.substring(0, s.indexOf('E')-1); //Remove the part after E
					s2 = s.substring(s.indexOf('E')+1, s.length()); // Memorize the part after E
					extent1 = Integer.parseInt(s2);
					s3 = values[2].replace(',','.');					
					_xPoint = (float)(Float.parseFloat(s1.replace(',','.'))*Math.pow(10, extent1));
					_yPoint = -Float.parseFloat(s3);
					_xArrayIni.add(_xPoint);
					_yArrayIni.add(_yPoint);
					if(M._xMax<_xPoint){M._xMax=_xPoint;}
					if(M._yMax<_yPoint){M._yMax=_yPoint;}
					if(M._xMin>_xPoint){M._xMin=_xPoint;}
					if(M._yMin>_yPoint){M._yMin=_yPoint;}
				}
				

				if(fileType=="modified")
				{
					M._arraySize++;
					if(line.indexOf("	")!=-1)
					{
						values = line.split("	");		//split the string
					}
					else
					{
						values = line.split(" ");		//split the string
					}

					s = values[0];	
					if(s.equals("nm"))
						continue; // skip the description line
						
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

					_xArrayIni.add(_xPoint);
					_yArrayIni.add(_yPoint);
					if(M._xMax<_xPoint){M._xMax=_xPoint;}
					if(M._yMax<_yPoint){M._yMax=_yPoint;}
					if(M._xMin>_xPoint){M._xMin=_xPoint;}
					if(M._yMin>_yPoint){M._yMin=_yPoint;}
				}
			
			
				if(fileType=="asc")
				{
					M._arraySize++;
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

					_xArrayIni.add(_xPoint);
					_yArrayIni.add(_yPoint);
	
					if(M._xMax<_xPoint){M._xMax=_xPoint;}
					if(M._yMax<_yPoint){M._yMax=_yPoint;}
					if(M._xMin>_xPoint){M._xMin=_xPoint;}
					if(M._yMin>_yPoint){M._yMin=_yPoint;}
				}
			
			
			
			}
			in.close(); //closing files

			
		/*	System.out.println("0:  " + _xArrayIni[0]);
			System.out.println("1:  " + _xArrayIni[1]);
			System.out.println("2566:  " + _xArrayIni[2566]);
			System.out.println("2567:  " + _xArrayIni[2567]);
			System.out.println("2568:  " + _xArrayIni[2568]);
			System.out.println("2569:  " + _xArrayIni[2569]);
			System.out.println("2570:  " + _xArrayIni[2570]);
			System.out.println("2571:  " + _xArrayIni[2571]);
	   */
			
			
	
			
			if(M._globalFitXMax<M._xMax){M._globalFitXMax=M._xMax;}	
			if(M._globalFitYMax<M._yMax){M._globalFitYMax=M._yMax;}
			if(M._globalFitXMin>M._xMin){M._globalFitXMin=M._xMin;}	
			if(M._globalFitYMin>M._yMin){M._globalFitYMin=M._yMin;}			
			
			
			M._xScaler = M._gridSizeX/(M._globalFitXMax-M._globalFitXMin);
			M._yScaler = M._gridSizeY/(M._globalFitYMax-M._globalFitYMin);
			M._xScalerIni = M._xScaler;
			M._yScalerIni = M._yScaler;
			
			M._markerShiftStep = Math.round(M._arraySize/300);
			M._markerShiftStepFast = Math.round(M._arraySize/20);
			
			M._zeroShiftStep = (M._xMax-M._xMin)/500;
			M._zeroShiftStepFast = Math.round((M._xMax-M._xMin)/20);

			
			_xArray.add(_xArrayIni.get(0)); 
			_yArray.add(_yArrayIni.get(0));
			 
			for(int i=1;i<(_xArrayIni.size()); i++)
			 {
				
				 _xArray.add(_xArrayIni.get(i));//-_xMin); 
				 _yArray.add(_yArrayIni.get(i));//-_yMin);
			 }
			
			float[] temp = new float[4];
			temp[0]=M._xMin; temp[1]=M._xMax; temp[2]=M._yMin; temp[3]= M._yMax;
			M._kinShiftCollection.add(temp);
			
			
			ArrayList<Integer> _tempMarkersArray = new ArrayList<Integer>();
			_tempMarkersArray.add(1);
			_tempMarkersArray.add((_xArray.size()-1));
			M._markersCollection.add(_tempMarkersArray);
			//_kinCollectionXIni.add(_xArrayIni);
			//_kinCollectionYIni.add(_yArrayIni);
			M._kinCollectionX.add(_xArray);
			M._kinCollectionY.add(_yArray);
			

			M._solvedCurveCollectionX = new ArrayList<Float>();
			M._solvedCurveCollectionY = new ArrayList<ArrayList<Float>>();
			M._solvedCurveContributionCollectionY = new ArrayList<ArrayList<ArrayList<Float>>>();
			M._sigmaCollection = new ArrayList<Float>();
			
			///need to sort according to wavelengths
			M.sortKinCollections();
			
			
			
	 
			 
			 
			 //_levelPosDisplay = 0f;
			 //_zeroPosDisplay = 0f;
			 //_beforeZeroPosDisplay = 0f;
			M._levelPosReal = M.setLevel(M._levelPosDisplay,Constants._kineticsModeGridPosition.y,M._globalFitYMin,M._yScaler);
			M._zeroPosReal = M.setZero(M._zeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
			M._beforeZeroPosReal = M.setZero(M._beforeZeroPosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);
			M._slicePosReal = M.setZero(M._slicePosDisplay,Constants._kineticsModeGridPosition.x,M._globalFitXMin,M._xScaler);

			M.myRect.y = M._levelPosReal-5-M._magPosY;
			M.myZeroRect.x = M._zeroPosReal-5-M._magPosX;
			M.myBeforeZeroRect.x = M._beforeZeroPosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
			M.sliceRect.x = M._slicePosReal-5-M._magPosX;//setZero(0f,Constants._kineticsModeGridPosition.x,_globalFitXMin,_xScaler);
			M._yZeroPos = Math.round(M._levelPosReal);
			M._xZeroPos = Math.round(M._zeroPosReal);
			 
			 
			M._fitMarkerFirstPos = 1;
			M._fitMarkerLastPos = M._arraySize-2;
			 
			// recalcFitMarkerFirst(false);
			// recalcFitMarkerLast(false);
	    	// checkFitMarkerPos();
			 
			 
			 
			 
			System.out.println("The file was converted into an array");

		
			
			
			
			M.addNewWavelengthInXSSpec(_wavelength);
			
			
			
			//getParams();
		
		
		
		
		} catch (IOException e) 
		
		{
			
			//JOptionPane.showConfirmDialog(this,"Can't open the file.");

		} 
		
		
	}
	
	
	
	
	
	
	
	

}
