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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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


public class GlobalFitControllerTableDataWorker {

	
	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitController _parentClass1;
	
	
	public GlobalFitControllerTableDataWorker(GlobalFitInterface _globalFitInterface, GlobalFitMath _globalFitMath, GlobalFitController _parentClass)
	{
		
		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_parentClass1 = _parentClass;
	}
	
	
	
	public void fillSetLimitsTableData()
	{

		
		
	
   	   	readSetLimitsTableContent();

		
		_globalFitInterface1._globalFitSetLimitsFrame.clearTableData();
		
		
		int _row=0, _column=0;
		//setting constants` limits
		
		if(_globalFitInterface1._globalFitFittingFrame._alterConstantsBox.isSelected())
		{
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("Constants", 0,0);
			_column = 4;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);		
			_row++;
			for(int i=0; i<_globalFitMath1.get_nOfConst(); i++)
			{
				_column=0;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("k" + (i + 1), _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitInterface1._globalFitMainInterface._constantLabelsArray[i].getText(), _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(0).get(i)[1]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(0).get(i)[1], _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(0).get(i)[2]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(0).get(i)[2], _row,_column);
				_column++;
				
				if(_globalFitMath1._variablesLimitsCollection.get(0).get(i)[0]==1)
				{
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(true), _row,_column);
				}
				else
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);
	
	
				_row++;
			}
		}
		
		if(_globalFitInterface1._globalFitFittingFrame._alterAbsCoeffBox.isSelected())
		{
			_column = 0;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("Abs coefficients", _row,_column);
			_column = 4;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);		
			_row++;
			for(int i=0; i<_globalFitMath1.get_nOfEq(); i++)
			{
				_column=0;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("X" + (i + 1), _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("-", _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("-", _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("-", _row,_column);
				_column++;
				
				if(_globalFitMath1._variablesLimitsCollection.get(1).get(i)[0]==1)
				{
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(true), _row,_column);
				}
				else
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);

				
	
				_row++;
			}

		}
		
		
		if(_globalFitInterface1._globalFitFittingFrame._alterInitConcentrationBox.isSelected())
		{
			_column = 0;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("Initial concentration", _row,_column);
			_column = 4;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);		
			_row++;
			for(int i=0; i<_globalFitMath1.get_nOfEq(); i++)
			{
				_column=0;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("X" + (i + 1) + "(0)", _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[i].getText(), _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(2).get(i)[1]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(2).get(i)[1], _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(2).get(i)[2]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(2).get(i)[2], _row,_column);
				_column++;

				if(_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[i].getText().equals("0") || _globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[i].getText().equals("0.0"))
					_globalFitMath1._variablesLimitsCollection.get(2).get(i)[0]=0;
					
				if(_globalFitMath1._variablesLimitsCollection.get(2).get(i)[0]==1)
				{
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(true), _row,_column);
				}
				else
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);

				_row++;
			}
		}
		
		if(_globalFitInterface1._globalFitFittingFrame._alterBurntConcentrationBox.isSelected())
		{
			
			_column = 0;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("dXi pulse", _row,_column);
			_column = 4;
			_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);		
			_row++;
			for(int i=0; i<_globalFitMath1.get_nOfEq(); i++)
			{
				_column=0;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt("X" + (i + 1) + "(0)", _row,_column);
				_column++;
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[i].getText(), _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(3).get(i)[1]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(3).get(i)[1], _row,_column);
				_column++;
				if(_globalFitMath1._variablesLimitsCollection.get(3).get(i)[2]!=-1)
				_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(_globalFitMath1._variablesLimitsCollection.get(3).get(i)[2], _row,_column);
				_column++;
				
				if(_globalFitMath1._variablesLimitsCollection.get(3).get(i)[0]==1)
				{
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(true), _row,_column);
				}
				else
					_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.setValueAt(new Boolean(false), _row,_column);
				_row++;
			}

		}
		
		//_globalFitInterface1._globalFitSetLimitsFrame.get_model()._data;
		
		
	}
	
	
	
	
	
	
	public void readSetLimitsTableContent()
	{
		
		//refill the array

		
		try
   		{
			String _s=" ";
			int _row=0;
			
			if(_globalFitMath1.get_ifAlterConst())
			{
				_row=0;
				while(!_s.equals("Constants"))
				{
					_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 0);
					_row++;
					if(_row>49)break;
				}
				if(_row<49)
				for(int i=0; i<10; i++)
				{
					if(i<_globalFitMath1.get_nOfConst())
					{
						boolean _option=(Boolean)_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 4);
						if(_option)
						{
							_globalFitMath1._variablesLimitsCollection.get(0).get(i)[0]= 1;
						}
						else
						_globalFitMath1._variablesLimitsCollection.get(0).get(i)[0]= 0;

						_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 2);
						if((!_s.equals(null))&(!_s.equals(" "))&(!_s.equals("")))
						{
							_globalFitMath1._variablesLimitsCollection.get(0).get(i)[1]=Float.parseFloat(_s);
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(0).get(i)[1]=-1;

						
						_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 3);
						if((!_s.equals(null))&(!_s.equals(" "))&(!_s.equals("")))
						{
							_globalFitMath1._variablesLimitsCollection.get(0).get(i)[2]=Float.parseFloat(_s);
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(0).get(i)[2]=-1;
						
						
						_row++;
					}
						
				}
				
			}
			
			
			if(_globalFitMath1.get_ifAlterAbsCoeff())
			{
				_row=0;
				while(!_s.equals("Abs coefficients"))
				{
					_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 0);
					_row++;
					if(_row>49)break;
				}
				if(_row<49)
				for(int i=0; i<10; i++)
				{
					if(i<_globalFitMath1.get_nOfEq())
					{
						boolean _option=(Boolean)_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 4);
						if(_option)
						{
							_globalFitMath1._variablesLimitsCollection.get(1).get(i)[0]= 1;
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(1).get(i)[0]= 0;
						/*
						_s = (String)_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 2);
						if((!_s.equals(null))&(!_s.equals(" ")))
						{
							_globalFitMath1._variablesLimitsCollection.get(1).get(i)[1]=Float.parseFloat(_s);
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(1).get(i)[1]=-1;
						 */
						
						
						_row++;
					}
						
				}
				
			}
			
			
			
			
			
			if(_globalFitMath1.get_ifAlterInitConcentration())
			{
				_row=0;
				while(!_s.equals("Initial concentration"))
				{
					_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 0);
					_row++;
					if(_row>49)break;
				}
				if(_row<49)
				for(int i=0; i<10; i++)
				{
					if(i<_globalFitMath1.get_nOfEq())
					{
						boolean _option=(Boolean)_globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 4);
						if(_option)
						{
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[0]= 1;
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[0]= 0;

						_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 2);
						if((!_s.equals(null))&(!_s.equals(" "))&(!_s.equals("")))
						{
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[1]=Float.parseFloat(_s);
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[1]=-1;

						
						
						_s = "" + _globalFitInterface1._globalFitSetLimitsFrame._setLimitsTable.getValueAt(_row, 3);
						if((!_s.equals(null))&(!_s.equals(" "))&(!_s.equals("")))
						{
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[2]=Float.parseFloat(_s);
						}
						else
							_globalFitMath1._variablesLimitsCollection.get(2).get(i)[2]=-1;

						
						
						_row++;
					}
						
				}
				
			}
			
			

   		}
   		catch(NumberFormatException e)
   		{
   			_parentClass1.pushWindowsBack();
   			JOptionPane.showMessageDialog(_globalFitInterface1._globalFitMainInterface, "Check the table! Can't read some values");
   			_parentClass1.setWindowsOnTop();
   		}
		

	}
	
	
	
}