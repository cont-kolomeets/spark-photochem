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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.*;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;



public class GlobalFitSetLimitsFrame extends JFrame{

	
	
	public int _frameSizeX=580, _frameSizeY = 355;
	
	IconCollection _iconCollection = new IconCollection();
	public boolean _DEBUG = false;
	MyTableModel _model = new MyTableModel();
	public JTable _setLimitsTable = new JTable(_model);
	
	public JButton _refreshButton = new JButton("Refresh", _iconCollection._RefreshIcon);
	public JButton _closeButton = new JButton("Close", _iconCollection._ExitIcon);
	
	
	public JScrollPane _scrollPane = new JScrollPane(_setLimitsTable);
	//DefaultTableModel model = new DefaultTableModel();
	

	
	/*
	{
			{"Constants", " ", " ", new Boolean(true)},
			{"k1", 0.0, 0.0, new Boolean(true)},
			{"k2", 0.0, 0.0, new Boolean(true)},
			{"k3", 0.0, 0.0, new Boolean(true)},
	};
	*/
	
	
	
	public GlobalFitSetLimitsFrame()
	{
		createGlobalFitSetLimitsFrameInterface();
	}
	
	
	public void createGlobalFitSetLimitsFrameInterface()
	{
		
		
		
		this.setTitle("Set limits");
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_frameSizeX-500, 50);
		this.setBackground(Color.DARK_GRAY);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		_setLimitsTable.setFillsViewportHeight(true);
		
		
		_scrollPane.setBounds(0, 0, _frameSizeX-5, _frameSizeY-80);
		
		
		
		//_model.addRow(new Object[] {"Variable name", "Current value", "Limits in %", "Set variable"});
		
		//Object[] _data1 = {"k1", new Integer(20), new Integer(20), new Boolean(true)};
		
		
		//_model.addColumn("Variable name");
		//_model.addColumn("Current value");
		//_model.addColumn("Limits in %");
		//_model.addColumn("Set variable");
		//_setLimitsTable.setValueAt(new Integer(20), 2, 2);
		//_model.addRow(_data1);
		
		
		//_setLimitsTable.setDefaultRenderer(String.class, new CustomRenderer());

		
		
		_model.clearData();
		
		
		_refreshButton.setBounds(290, 275, 130, 45);
		_closeButton.setBounds(420, 275, 130, 45);
		
		this.add(_scrollPane);
		this.add(_refreshButton);
		this.add(_closeButton);
		
	}
	
	
	
	
	
	public MyTableModel get_model()
	{
		return _model;
	}
	
	
 	public void clearTableData()
	{
		for(int i=0; i<50; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(j==4)
				{
					_setLimitsTable.setValueAt(new Boolean(false), i , j);
				}
				else
				{
					_setLimitsTable.setValueAt(" ", i , j);
				}

			}
		}
	}
	
	
	
	
	public class MyTableModel extends AbstractTableModel
	{
		public String[] _columnNames = {"Variable name", "Current value", "Min value", "Max value", "Set variable"};
		public Object[][] _data = new Object[50][5];
		
		
        public int getColumnCount() {
            return _columnNames.length;
        }
 
        public int getRowCount() {
            return _data.length;
        }
 
        public String getColumnName(int col) {
            return _columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return _data[row][col];
        }
        
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
		
		
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
        
        public void setValueAt(Object value, int row, int col) {
            if (_DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
 
            _data[row][col] = value;
            fireTableCellUpdated(row, col);
 
            if (_DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
 
        
    	public Object[][] get_data()
    	{
    		return _data;
    	}
    	
    	public void clearData()
    	{
    		for(int i=0; i<50; i++)
    		{
    			for(int j=0; j<5; j++)
    			{
    				if(j==4)
    				{
    					_model._data[i][j] = new Boolean(false);
    				}
    				else
    				{
    					_model._data[i][j] = " ";
    				}

    			}
    		}
    	}
    	
        
        //public void setNewTableData(Object[][] _newData)
       // {
        	//_data = _newData;
        	//System.out.println("Data renewed");
       // }
        
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + _data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
		
		
		
		
		
		
		
	}
	
	
	class CustomRenderer extends DefaultTableCellRenderer 
	{
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        c.setBackground(new java.awt.Color(100, 250, 100));
	        return c;
	    }
	}

	
	
	
	
}
