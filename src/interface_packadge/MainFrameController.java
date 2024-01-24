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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.imageio.*;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class MainFrameController {
	
	public static final long serialVersionUID = 001;
	public MainFrame _mainFrame1;
	
   
    
    
    
    public MainFrameController(MainFrame _mainFrame)
	{
		_mainFrame1 = _mainFrame;
		
		_mainFrame1.addComponentListener(new ComponentAdapter() 
	    {
			 
	        public void componentResized(ComponentEvent evt) {
	          Component c = (Component) evt.getSource();
	          _mainFrame1.refreshComponentsSize();
	          
	        }
	    });
		
		
		
		
		
		
		
		_mainFrame1._globalFitInterface._globalFitMainInterface._modeBox.addActionListener(new ActionListener()
    	{
    	@Override
    	public void actionPerformed(ActionEvent e){
    		
    		
    		int s = _mainFrame1._globalFitInterface._globalFitMainInterface._modeBox.getSelectedIndex();
    		
    		if(s==1)
    		{
    			_mainFrame1.showKineticsModeInterface();
    			_mainFrame1._kineticsModeInterface._modeBox.setSelectedIndex(1);
   			
    		}
       		if(s==2)
    		{
       			//
  
     		}

      		if(s==3)
    		{
 
  
           	
    			
    		}
     		if(s==4)
    		{
 

    			
    		}

    	
    	
    	}});
		
		
		
		_mainFrame1._kineticsModeInterface._modeBox.addActionListener(new ActionListener()
    	{
    	@Override
    	public void actionPerformed(ActionEvent e){
    		
    		
    		int s = _mainFrame1._kineticsModeInterface._modeBox.getSelectedIndex();
    		
    		if(s==1)
    		{
    			//
   			
    		}
       		if(s==2)
    		{
       			_mainFrame1.showGlobalFitInterface();
       			_mainFrame1._globalFitInterface._globalFitMainInterface._modeBox.setSelectedIndex(2);
  
     		}

      		if(s==3)
    		{
 
  
           	
    			
    		}
     		if(s==4)
    		{
 

    			
    		}

    	
    	
    	}});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
	
	
    /*public void resizeAllInnerComponents(MainFrame _mainFrame)
	{
		_mainFrame.refreshComponentsSize();
		
	}*/
	
	

}
