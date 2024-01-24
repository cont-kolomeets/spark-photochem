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



public class IconCollection {
	
	
	static ImageIcon _UpRedIcon;
	static ImageIcon _DownRedIcon;
	static ImageIcon _UpBlueIcon;
	static ImageIcon _DownBlueIcon;
	
	static ImageIcon _ArrowGrayUp;
	static ImageIcon _ArrowGrayDown;
	
	static ImageIcon _LeftRedIcon; 
	static ImageIcon _RightRedIcon; 
	static ImageIcon _LeftBlueIcon;
	static ImageIcon _RigthBlueIcon; 
	static ImageIcon _TimerIcon;
	static ImageIcon _ExitIcon;
	
	static ImageIcon _OpenFileIcon;
	static ImageIcon _MagnifyIcon; 
	static ImageIcon _MagnifyIcon1; 
	static ImageIcon _KineticsIcon; 
	static ImageIcon _DeleteIcon; 
	static ImageIcon _SaveFileIcon; 
	static ImageIcon _SaveFitIcon; 
	static ImageIcon _SaveKinIcon;
	static ImageIcon _SaveKinIcon1; 
	static ImageIcon _SaveOneIcon; 
	static ImageIcon _RadioIcon;
	static ImageIcon _ButterFlyIcon; 
	
	static ImageIcon _CreateSpectraIcon;
	static ImageIcon _ShowKinIcon; 
	static ImageIcon _ShowSpectraIcon; 
	static ImageIcon _LinesCenterIcon; 
	static ImageIcon _TrimIcon; 
	static ImageIcon _VSmoothIcon; 
	static ImageIcon _HSmoothIcon; 
	static ImageIcon _SpectraParametersIcon; 
	static ImageIcon _CalcIcon; 
	static ImageIcon _SnapShotIcon;
	static ImageIcon _RefreshIcon;
	static ImageIcon _PlusIcon;
	
	static ImageIcon _StartIcon;
	static ImageIcon _StopIcon;
	static ImageIcon _SetLimitsIcon;
	static ImageIcon _ClearAllIcon;
	static ImageIcon _OpenProjectIcon;
	static ImageIcon _OpenModelIcon;
	static ImageIcon _SaveModelIcon;
	
	
	static ImageIcon _GlobalFitIcon;
	static ImageIcon _RescaleIcon;
	static ImageIcon _RescaleIcon1;
	static ImageIcon _SeeAllIcon;
	static ImageIcon _ScaleUpIcon;
	static ImageIcon _ScaleDownIcon;
	static ImageIcon _MultiplyModeIcon;
	
	static ImageIcon _OpenSetIcon;
	static ImageIcon _SaveSetIcon;
	
	static ImageIcon _FitRK4Icon;
	static ImageIcon _UndoIcon;
	static ImageIcon _RedoIcon;
	
	static ImageIcon _ClearOneIcon;
	static ImageIcon _ClearAllSpecIcon;
	
	static ImageIcon  _ArrowReturnIcon;
	static ImageIcon  _RelationsIcon;
	static ImageIcon  _AcceptIcon;
	static ImageIcon  _CopyToClipboard;
	static ImageIcon  _ShowSlice;
	
	File fileName;
	
	
	
	public IconCollection()
	{
		JFrame frame1 = new JFrame();
		//String path = "d:/Java/workspace/USB4000/rsc/rsc/SnapShot3.png"; 
		
		URL url = frame1.getClass().getResource("/images/SnapShot3.png"); 
		_SnapShotIcon = new ImageIcon(url); 		
		
		url = frame1.getClass().getResource("/images/RelationsIcon01_32.png"); 
		_RelationsIcon = new ImageIcon(url); 		

		
		url = frame1.getClass().getResource("/images/ShowSpec01_24.png"); 
		_ShowSlice = new ImageIcon(url); 
		
		url = frame1.getClass().getResource("/images/CopyToClipboard02_32.png"); 
		_CopyToClipboard = new ImageIcon(url); 
		
		
		url = frame1.getClass().getResource("/images/StartIcon03_32.png"); 
		_AcceptIcon = new ImageIcon(url); 
		
		
		url = frame1.getClass().getResource("/images/MagnifyIcon05_24a.png"); 
		_MagnifyIcon1 = new ImageIcon(url); 	
		
		url = frame1.getClass().getResource("/images/SaveIcon06_32.png"); 
		_SaveFitIcon = new ImageIcon(url); 
		
		
		url = frame1.getClass().getResource("/images/ArrowReturn01_48.png"); 
		_ArrowReturnIcon = new ImageIcon(url);
		
		
		
		
		url = frame1.getClass().getResource("/images/ClearOneIcon01_32.png"); 
		_ClearOneIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/ClearAllIcon01_32.png"); 
		_ClearAllSpecIcon = new ImageIcon(url);
		
		
		
		
		url = frame1.getClass().getResource("/images/UndoIcon01_32.png"); 
		_UndoIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/RedoIcon01_32.png"); 
		_RedoIcon = new ImageIcon(url);
		
		
		
		url = frame1.getClass().getResource("/images/Spectra4.png"); 
		_FitRK4Icon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/OpenFile5.png"); 
		_OpenSetIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/SaveIcon03_32.png"); 
		_SaveSetIcon = new ImageIcon(url);
		
		
		
		url = frame1.getClass().getResource("/images/CalcIcon01_32.png"); 
		_GlobalFitIcon = new ImageIcon(url);		
		
		
		url = frame1.getClass().getResource("/images/RescaleIcon02_32.png"); 
		_RescaleIcon = new ImageIcon(url);		
		
		url = frame1.getClass().getResource("/images/RescaleIcon02_48a.png"); 
		_RescaleIcon1 = new ImageIcon(url);	
		
		
		url = frame1.getClass().getResource("/images/RescaleIcon01_32.png"); 
		_SeeAllIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/ScaleUp01_20.png"); 
		_ScaleUpIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/ScaleDown01_20.png"); 
		_ScaleDownIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/MultiplyIcon01_20.png"); 
		_MultiplyModeIcon = new ImageIcon(url);
		
		
		
		
		
		
		
		
		
		
		
		url = frame1.getClass().getResource("/images/ArrowGrayUp.png"); 
		_ArrowGrayUp = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/ArrowGrayDown.png"); 
		_ArrowGrayDown = new ImageIcon(url);		
		
		url = frame1.getClass().getResource("/images/Sync3.png"); 
		_RefreshIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/PlusIcon01_32.png"); 
		_PlusIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/StartIcon03_48.png"); 
		_StartIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/StopIcon02_32.png"); 
		_StopIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/SetLimits01_32.png"); 
		_SetLimitsIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/ClearIcon01_32.png"); 
		_ClearAllIcon = new ImageIcon(url);
		
		
		url = frame1.getClass().getResource("/images/OpenIcon01_32.png"); 
		_OpenProjectIcon = new ImageIcon(url);	
		
		url = frame1.getClass().getResource("/images/OpenIcon02_32.png"); 
		_OpenModelIcon = new ImageIcon(url);	
		
		url = frame1.getClass().getResource("/images/SaveIcon02_32.png"); 
		_SaveModelIcon = new ImageIcon(url);	

		//url = frame1.getClass().getResource("/images/ArrowUpRed.JPG"); 
		//_UpRedIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowDownRed.JPG"); 
		//_DownRedIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowUpBlue.JPG"); 
		//_UpBlueIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowDownBlue.JPG"); 
		//_DownBlueIcon = new ImageIcon(url);
		
		//url = frame1.getClass().getResource("/images/ArrowLeftRed.JPG"); 
		//_LeftRedIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowRightRed.JPG"); 
		//_RightRedIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowLeftBlue.JPG"); 
		//_LeftBlueIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/images/ArrowRightBlue.JPG"); 
		//_RigthBlueIcon = new ImageIcon(url);

		url = frame1.getClass().getResource("/images/Timer3.png"); 
		_TimerIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Exit1.png"); 
		_ExitIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/OpenFile1.png"); 
		_OpenFileIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Magnify.png"); 
		_MagnifyIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/Kinetics.png"); 
		//_KineticsIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Delete2.png"); 
		_DeleteIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/SaveFile.png"); 
		_SaveFileIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/SaveFile3.png"); 
		_SaveKinIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/SaveFile4.png"); 
		_SaveKinIcon1 = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/SaveOne.png"); 
		_SaveOneIcon = new ImageIcon(url);
		//url = frame1.getClass().getResource("/Radio.png"); 
		//_RadioIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/ButterFly.png"); 
		_ButterFlyIcon = new ImageIcon(url);
		
		url = frame1.getClass().getResource("/images/Mech6.png"); 
		_CreateSpectraIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Kinetics2.png"); 
		_ShowKinIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Spectra1.png"); 
		_ShowSpectraIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Center2.png"); 
		_LinesCenterIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Cut.png"); 
		_TrimIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/SmoothVert.png"); 
		_VSmoothIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Smooth1.png"); 
		_HSmoothIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Spectra3.png"); 
		_SpectraParametersIcon = new ImageIcon(url);
		url = frame1.getClass().getResource("/images/Calc1.png"); 
		_CalcIcon = new ImageIcon(url);
		
		
	}

}
