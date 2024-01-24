package math_packadge;

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


public class GlobalFitDirectRectangle {

	GlobalFitMath M;
	public boolean _isNew = true;
	public boolean _isSorted = false;
	public boolean _isCube = true;
	public boolean _isExploredAround = false;
	//public boolean _isReasonableToDivide = true;
	public int _nOfDimentions = 0;
	public ArrayList<float[]> _data = new ArrayList<float[]>();
	public int _indexSorted = -1;
	public float _functionValue = 1e9f;
	public float _distance = 1f;
	public boolean[] _sideTypes; //true - long (can be reduced), false - alredy reduced (cannot be reduced);
	public float[] _sideLengths;
	public ArrayList<float[]> _surroundingFuncValCollection;
	public ArrayList<float[]> _surroundingCentersCollection;
	ArrayList<Integer> _availDimentions;
	public float _K;
	
	public GlobalFitDirectRectangle(int _nOfDimentions, GlobalFitMath M)
	{
		this.M =M;
		this._nOfDimentions =_nOfDimentions;
		createDataSlots();
	}
	
	
	public void createDataSlots()
	{
		_sideTypes = new boolean[_nOfDimentions];
		_sideLengths = new float[_nOfDimentions];

		for(int i=0; i<_nOfDimentions; i++)
		{
			float[] _temp = new float[5]; // 0 - ai, 1 - bi, 2 - ci;
			_data.add(_temp);
			_sideTypes[i]=true;
		}

		
	}
	
	
	public final void setFunctionValAtCenter(float _val)
	{
		_functionValue = _val;
		_isNew = false;
	}
	
	public void calcDistance()
	{
		float sum = 0;
		for(int i=0; i<_nOfDimentions; i++)
		{
			sum += (float)Math.pow(((_data.get(i)[0]-_data.get(i)[1])/2f),2);
		}
		_distance = (float)Math.sqrt(sum);
	}
	
	
	public void setAllSidesToLength(float _length)
	{
		for(int i=0; i<_nOfDimentions; i++)
		{
			_sideLengths[i]=_length;
		}
	}
	
	
	public void assignSideTypes()
	{
		if(!isCube())
		{
			boolean _long = true;
			for(int i=0; i<_nOfDimentions; i++)
				if(_sideLengths[0]<_sideLengths[i])
					_long = false;
			if(_long)
				for(int i=0; i<_nOfDimentions; i++)
				{
					if(_sideLengths[0]==_sideLengths[i])
					{
						_sideTypes[i]=true;
					}
					else
					{
						_sideTypes[i]=false;
					}
				}
			
			if(!_long)
				for(int i=0; i<_nOfDimentions; i++)
				{
					if(_sideLengths[0]==_sideLengths[i])
					{
						_sideTypes[i]=false;
					}
					else
					{
						_sideTypes[i]=true;
					}
				}
		}
	}
	
	
	public final boolean ifSideCanBeReduced(int _dimention)
	{
		return _sideTypes[_dimention];
	}
	
	public final void set_interval(int _dimention, float ai, float bi, float ci)
	{
		_data.get(_dimention)[0]=ai;
		_data.get(_dimention)[1]=bi;
		_data.get(_dimention)[2]=ci;
	}
	
	public final float get_intervalCenter(int _dimention)
	{
		return _data.get(_dimention)[2];
	}
	
	
	public float getDistance()
	{
		return _distance;
	}
	
	public float getOneThird(int _dimention)
	{
		return (float)Math.abs((_data.get(_dimention)[1]-_data.get(_dimention)[0])/3);
	}
	
	
	public float getSide(int _dimention)
	{
		return (float)Math.abs(_data.get(_dimention)[1]-_data.get(_dimention)[0]);
	}
	
	
	public float getLongSideLength()
	{
		float _result = -1f;
		
		for(int i=0; i<this._nOfDimentions; i++)
			if(_sideTypes[i])
			{
				_result = _sideLengths[i];
				break;
			}
		
		return _result;
	}
	
	public float getShortSideLength()
	{
		float _result = -1f;
		
		for(int i=0; i<this._nOfDimentions; i++)
			if(!_sideTypes[i])
			{
				_result = _sideLengths[i];
				break;
			}
		
		return _result;
	}
	
	
	public float getCenterValue(int _dimention)
	{
		return _data.get(_dimention)[2];
	}
	
	public void fillSideCollection()
	{
		for(int i=0; i<_nOfDimentions; i++)
		{
			_sideLengths[i] = getSide(i);
		}
	}
	
	
	public boolean checkSides()
	{
		fillSideCollection();
		
		boolean _result=true;
		
		for(int i=0; i<_nOfDimentions; i++)
		{
			if(!compareValues(_sideLengths[0], _sideLengths[i], 0.01f))
				_result = false;
		}
		
		set_isCube(_result);
		assignSideTypes();
		
		return _result;
	}
	
	
	public void calcK(float _epsilon, float _minFunctionCurrent)
	{
		float fe = _minFunctionCurrent*(1f-_epsilon);
		_K = (this._functionValue-fe)/this._distance;
	}
	
	public final float get_K()
	{
		return this._K;
	}
	
	public final void set_isNew(boolean _isNew)
	{
		this._isNew = _isNew;
	}
	
	public final boolean isNew()
	{
		return _isNew;
	}
	
	
	public final void set_isCube(boolean _isCube)
	{
		this._isCube = _isCube;
	}
	
	public final boolean isCube()
	{
		return _isCube;
	}
	
	public final void set_isExploredAround(boolean _isExploredAround)
	{
		this._isExploredAround = _isExploredAround;
	}
	
	public final boolean isExploredAround()
	{
		return _isExploredAround;
	}
	
	
	public final void set_isSorted(boolean _isSorted)
	{
		this._isSorted = _isSorted;
	}
	
	public final boolean isSorted()
	{
		return _isSorted;
	}
	
	public final void setGroupIndex(int _indexSorted)
	{
		this._indexSorted = _indexSorted;
		_isSorted = true;
	}
	
	public final int getGroupIndex()
	{
		return _indexSorted;
	}
	

	public final float get_functionValue()
	{
		return _functionValue;
	}
	
	
	
	
	
	public boolean compareValues(float v1, float v2, float accuracy)
	{
		boolean _result = false;
		
		if((float)Math.abs(v1-v2)<(v1*accuracy))
			_result = true;
		
		return _result;
	}
	
	
	
	/*public boolean checkIfReasonableToDivide(float _epsilon)
	{
		boolean _result = false;
		
		if(this.isExploredAround())
		{
			for(int i=0; i<this._nOfDimentions; i++)
				for(int j=0; j<2; j++)
					if((this._functionValue - this._surroundingFuncValCollection.get(i)[j])>_epsilon)
						_result = true;
		}
		
		this.set_ifReasonalbeToDivide(_result);
		return _result;
	}
	
	
	public final void set_ifReasonalbeToDivide(boolean _isReasonableToDivide)
	{
		this._isReasonableToDivide = _isReasonableToDivide;
	}
	
	public boolean isReasonableToDivide()
	{
		return this._isReasonableToDivide;
	}*/
	
	public final void set_surroundingFuncValCollection(ArrayList<float[]> _surroundingFuncValCollection)
	{
		this._surroundingFuncValCollection = _surroundingFuncValCollection;
	}
	
	
	public float getEarlierExploredFuncValue(int _dimention, int _position)
	{
		return _surroundingFuncValCollection.get(_dimention)[_position];
	}
	
	
	public final void set_surroundingCentersCollection(ArrayList<float[]> _surroundingCentersCollection)
	{
		this._surroundingCentersCollection = _surroundingCentersCollection;
	}
	
	
	public float getEarlierExploredCenterValue(int _dimention, int _position)
	{
		return _surroundingCentersCollection.get(_dimention)[_position];
	}
	
	
	
	
	
	public ArrayList<float[]> getEarlierExploredFuncValueCollection()
	{
		return _surroundingFuncValCollection;
	}
	
	
	public ArrayList<float[]> getEarlierExploredCentersCollection()
	{
		return _surroundingCentersCollection;
	}
	
	
	public ArrayList<Integer> getEarlierExploredAvailDimentions()
	{
		return _availDimentions;
	}
	
	
	public final void set_availDimentions(ArrayList<Integer> _availDimentions)
	{
		this._availDimentions = _availDimentions;
	}
	
	public void printOutInformation()
	{
		System.out.println("Dim = " + _nOfDimentions + ". Group index = " + _indexSorted + ". isCube: " + _isCube + ". isSorted: " + _isSorted + ". Dist = " + _distance + ". FuncVal = " + _functionValue);
		
		for(int i=0; i<_nOfDimentions; i++)
		{
			System.out.println("Dimention: " + i + ".   ai = " + _data.get(i)[0] + " bi = " + _data.get(i)[1] +" ci = " + _data.get(i)[2]);
		}
	}
	
	
	
}
