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





public class GlobalFitMovingAdapter extends MouseAdapter
{
	
	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _globalFitController1;
	
	
	
	
	
	
	public GlobalFitMovingAdapter(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _globalFitController)
	{
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_globalFitController1 = _globalFitController;
	}
	
	
	
	
	
	
	public void mouseMoved(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			_globalFitMath1.mouseMoved(e);
			_globalFitInterface1._globalFitMainInterface._graphPanel.set_mouseLabelText(_globalFitMath1.get_mouseLabelText());
			_globalFitInterface1._globalFitMainInterface._graphPanel.setCursor(_globalFitMath1.get_cursor());
		}
		
	}

	public void mousePressed(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			_globalFitMath1.mousePressed(e);
			if(_globalFitMath1.get_ifNeedToRepaint())
			{
				
				_globalFitController1.graphPanelRefresh();
				
				_globalFitMath1.set_ifNeedToRepaint(false);
			}
		}

		
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			_globalFitMath1.mouseReleased(e);

			_globalFitController1.graphPanelRefresh();

			_globalFitMath1.set_ifNeedToRepaint(false);
		}


	}
	
	 public void mouseDragged(MouseEvent e)
	 {
		if(!_globalFitMath1.get_ifLockGraphPanels())
		{
			 _globalFitMath1.mouseDragged(e);
			 _globalFitInterface1._globalFitMainInterface._graphPanel.setCursor(_globalFitMath1.get_cursor());
			
			 _globalFitController1.checkStatus();
			 _globalFitController1.graphPanelRefresh();	
		}
		
		
		if(_globalFitMath1._moveSlice && _globalFitInterface1._globalFitSliceFrame.isVisible())
		{
			_globalFitController1.slicePanelRefresh();
			try
			{
				_globalFitInterface1._globalFitSliceFrame._currentTimeValueLabel.setText(MethodsCollection.cutStringAfterPoint("" + _globalFitMath1.get_kinCollectionX().get(0).get(_globalFitMath1.get_globalFitPosOfCurrentTimePoint()),5));
			}
			catch(ArrayIndexOutOfBoundsException ex)
			{
				
			}
		}




	 }
}