package interface_packadge;



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


public class GlobalFitExtGraphPanelMovingAdapter extends MouseAdapter
{
	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;
	
	
	
	
	
	
	public GlobalFitExtGraphPanelMovingAdapter(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
			_globalFitInterface1 = _globalFitInterface;
			_globalFitMath1 = _globalFitMath;
			_globalFitController1 = _globalFitController;
	}
	
	
	
	
	
	public void mouseMoved(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			if(!_globalFitMath1.get_ifShowExtInputPanel())
			{
				_globalFitMath1.extGraphPanelMouseMoved(e);
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_mouseLabelText(_globalFitMath1.get_mouseLabelTextExt());
				_globalFitInterface1._globalFitExtFrame._extGraphPanel.setCursor(_globalFitMath1.get_cursorExt());
			}
			 
			
			showAbsValueLabel();
		}
	}

	public void mousePressed(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			_globalFitMath1.makeCopyOf_xSAbsSpecCollectionYFiltered();
			_globalFitMath1.extGraphPanelMousePressed(e);
			
			_globalFitController1.extGraphPanelRefresh();
			
			
			
			_globalFitMath1.set_globalFitPosOfSelectedKin(_globalFitMath1.get_numberOfLastClickedRect());
			_globalFitInterface1._globalFitMainInterface._graphPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
			
			if(_globalFitInterface1._globalFitSigmaFrame.isVisible())
			{
				_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.set_globalFitPosOfSelectedKin(_globalFitMath1.get_globalFitPosOfSelectedKin());
				_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.repaint();
			}
			
			try
			{
				if(_globalFitMath1.get_availWaveCollection().size()!=0)
					_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel.setText("" + _globalFitMath1.get_kinCollectionX().get(_globalFitMath1.get_globalFitPosOfSelectedKin()).get(0));

			}
			catch(ArrayIndexOutOfBoundsException a)
			{
				System.out.println("GlobalFitExtGraphPanelMovingAdapter: Array index ouf bounds");
			}
			_globalFitController1.graphPanelRefresh();
		}



	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			_globalFitMath1.extGraphPanelMouseReleased(e);
			_globalFitInterface1._globalFitExtFrame._undoButton.setEnabled(true);
			
			_globalFitController1.extGraphPanelRefresh();
		}



	}
	
	 public void mouseDragged(MouseEvent e)
	 {
			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				 _globalFitMath1.extGraphPanelMouseDragged(e);
				 _globalFitInterface1._globalFitMainInterface._graphPanel.setCursor(_globalFitMath1.get_cursor());
				 
				 _globalFitController1.extGraphPanelRefresh();
				 showAbsValueLabel();
				 
				 if(_globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected())
					 _globalFitController1.fitRK4();
			}

		 
		 



	 }
	 
	 
	 public void mouseClicked(MouseEvent e)
	 {
			if(!_globalFitMath1.get_ifLockGraphPanels())
			{
				 if(e.getClickCount()>1)
				 {
					 
					 _globalFitInterface1._globalFitInputExtFrame._inputExtField.setText("");
					 //_globalFitInterface1._globalFitInputExtFrame._inputExtField.
					 //_globalFitInterface1._globalFitExtFrame._inputExtField.set
					 _globalFitInterface1._globalFitInputExtFrame.setVisible(true);
					 _globalFitInterface1._globalFitInputExtFrame.setLocation(
							 _globalFitInterface1._globalFitExtFrame.getLocation().x + _globalFitInterface1._globalFitExtFrame._extGraphPanel.getLocation().x + Math.round(_globalFitMath1.get_mouseXExt()+5),
							 _globalFitInterface1._globalFitExtFrame.getLocation().y + _globalFitInterface1._globalFitExtFrame._extGraphPanel.getLocation().y + Math.round(_globalFitMath1.get_mouseYExt()+5));
					 
					 _globalFitInterface1._globalFitInputExtFrame._inputExtWavelengthLabel.setText("" + _globalFitMath1.get_wavelengthValueOfDraggedMarker());
					 _globalFitInterface1._globalFitInputExtFrame._inputExtField.setText("" + (-_globalFitMath1.get_absValueOfDraggedMarker()));
					 //_globalFitMath1.set_ifShowExtInputPanel(true);
					 
					 
					 _globalFitInterface1._globalFitExtFrame.repaint();
					 
					 
					 if(_globalFitInterface1._globalFitExtFrame._updateAutoBox.isSelected())
						 _globalFitController1.fitRK4();
				 }
			}
		 
		 //System.out.println(e.getClickCount() + " clicks");

	 }
	 
	 

	
	
	
	

		public void showAbsValueLabel()
		{
			if(_globalFitMath1.get_ifShowAbsValueLabel())
			 {
				 String text = " " + (-_globalFitMath1.get_absValueOfDraggedMarker());
				 _globalFitInterface1._globalFitExtFrame._extGraphPanel.add( _globalFitInterface1._globalFitExtFrame._absValueLabel);
				 _globalFitInterface1._globalFitExtFrame._absValueLabel.setText(text);
				 _globalFitInterface1._globalFitExtFrame._absValueLabel.setBounds(Math.round(_globalFitMath1.get_mouseXExt()+5), Math.round(_globalFitMath1.get_mouseYExt()-35), 100, 20);

				 text = " " + (_globalFitMath1.get_wavelengthValueOfDraggedMarker())+ " nm";
				 _globalFitInterface1._globalFitExtFrame._extGraphPanel.add( _globalFitInterface1._globalFitExtFrame._wavelengthValueLabel);
				 _globalFitInterface1._globalFitExtFrame._wavelengthValueLabel.setText(text);
				 _globalFitInterface1._globalFitExtFrame._wavelengthValueLabel.setBounds(Math.round(_globalFitMath1.get_mouseXExt()+5), Math.round(_globalFitMath1.get_mouseYExt()-55), 100, 20);

			 }
			else
			{
				 _globalFitInterface1._globalFitExtFrame._extGraphPanel.remove( _globalFitInterface1._globalFitExtFrame._wavelengthValueLabel);
				 _globalFitInterface1._globalFitExtFrame._extGraphPanel.remove( _globalFitInterface1._globalFitExtFrame._absValueLabel);

			}

		}

	
	
	
	
	
	
	
	
}
