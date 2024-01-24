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
import javax.swing.JComboBox;
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.beans.*;
import java.util.Random;


public class GlobalFitFittingFrame extends JFrame{

	
	public int _frameSizeX=300, _frameSizeY = 416;
	
	public IconCollection _iconCollection = new IconCollection();
	
	public JPanel _variblesPanel = new JPanel();
	public JPanel _progressPanel = new JPanel();
	public JPanel _sliderBasicPanel = new JPanel();
	public JPanel _sliderGradientAdvancedPanel = new JPanel();
	public JPanel _sliderDirectAdvancedPanel = new JPanel();
	public JPanel _weightsPanel = new JPanel();
	public JPanel _selectAlgorithmPanel = new JPanel();

	//public JPanel _sliderBasicEtchedPanel = new JPanel();
	//public JPanel _sliderAdvancedEtchedPanel = new JPanel();
	//public JPanel _sliderAdvancedDirectEtchedPanel = new JPanel();
	
	
	public JLabel _alterLabel = new JLabel("    Variables:");
	
	public JLabel _globalSigma = new JLabel("0");	
	public JLabel _globalSigmaName = new JLabel("Global sigma =");

	public JCheckBox _alterConstantsBox = new JCheckBox("constants");
	public JCheckBox _alterAbsCoeffBox = new JCheckBox("abs coefficients");
	public JCheckBox _alterInitConcentrationBox = new JCheckBox("Xi(0)");
	public JCheckBox _alterBurntConcentrationBox = new JCheckBox("dXi pulse");
	
	public JCheckBox _useWeightBox = new JCheckBox("Use weight for noisy curves");
	public JCheckBox _advancedSettingsBox = new JCheckBox("Advanced settings");
	public JCheckBox _advancedSettingsBox1 = new JCheckBox("Advanced settings");
	public JCheckBox _advancedSettingsDirectBox = new JCheckBox("Advanced settings");
	
	public String[] _modeString = {"Gradient", "Direct"};
	public JComboBox _selectAlgorithmBox = new JComboBox(_modeString);
	public JLabel _selectAlgorithmLabel = new JLabel("Calculation algorithm:");
	


	public JButton _startFittingButton = new JButton("Start", _iconCollection._StartIcon);
	public JButton _stopFittingButton = new JButton("Stop", _iconCollection._StopIcon);

	public JProgressBar _progressBarSlow = new JProgressBar();
	public JProgressBar _progressBarFast = new JProgressBar();
	

	
	public JSlider _accuracySlider = new JSlider();
	public JLabel _accuracySliderName = new JLabel("Accuracy");
	
	public JSlider _minorIterationsSlider = new JSlider();
	public JLabel _minorIterationsSliderName = new JLabel("Lattice resolution");
	
	public JSlider _majorIterationsSlider = new JSlider();
	public JLabel _majorIterationsSliderName = new JLabel("N of Iterations");	
	
	public JSlider _slopeSlider = new JSlider();
	public JLabel _slopeSliderName = new JLabel("Range reduction coefficient");
	
	
	
	public JSlider _nOfIterationsSlider = new JSlider();
	public JLabel _nOfIterationsSliderName = new JLabel("N of Iterations");
	
	public JSlider _cubeSideSlider = new JSlider();
	public JLabel _cubeSideSliderName = new JLabel("Hypercube side coefficient");

	
	
	public JSlider _directNOfIterationsSlider = new JSlider();
	public JLabel _directNOfIterationsSliderName = new JLabel("N of Iterations");
	
	public JSlider _directLowLimitSlider = new JSlider();
	public JLabel _directLowLimitSliderName = new JLabel("Range lower limit, %");	
	
	public JSlider _directHighLimitSlider = new JSlider();
	public JLabel _directHighLimitSliderName = new JLabel("Range upper limit, %");
	
	
	
	public JButton _setLimitsButton = new JButton("Set limits", _iconCollection._SetLimitsIcon);
	public JButton _defineRelationsButton = new JButton("Relations", _iconCollection._RelationsIcon);
	
	
	Color _BGColor1 = new Color(255,255,255);
	Color _BGColor2 = Color.WHITE;
	Color _BGColor3 = new Color(100,100,100);
	
	
	//public JLabel _rangeLabel = new JLabel("Set range, %");
	
	
	public GlobalFitFittingFrame()
	{
		createGlobalFitFittingFrameInterface();
		createVariablesPanelInterface();
		createProgressPanelInterface();
		createSelectAlgorithmPanelInterface();
		createSliderBasicPanelInterface();
		createSliderGradientAdvancedPanelInterface();
		createSliderDirectAdvancedPanelInterface();
		createWeightsPanelInterface();
		setPanelsBounds();
	}
	
	public void createGlobalFitFittingFrameInterface()
	{
		this.setTitle("Global fit");
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		this.setResizable(false);
		this.setLocation(Constants._screenDimentions.width-_frameSizeX, 30);
		this.setBackground(Color.lightGray);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		this.add(_variblesPanel);
		this.add(_progressPanel);
		this.add(_selectAlgorithmPanel);
		this.add(_sliderBasicPanel);
		this.add(_weightsPanel);
		
	}
	
	
	public void setPanelsBounds()
	{
		_variblesPanel.setBounds(0,0, _frameSizeX-5, 90);
		_progressPanel.setBounds(0,90, _frameSizeX-5, 120);
		_selectAlgorithmPanel.setBounds(0, 210, _frameSizeX-5, 40);
		_sliderBasicPanel.setBounds(0,250, _frameSizeX-5,103);
		_sliderGradientAdvancedPanel.setBounds(0,250, _frameSizeX-5,183);
		_sliderDirectAdvancedPanel.setBounds(0,250, _frameSizeX-5,183+80);
		_weightsPanel.setBounds(0,353, _frameSizeX-5, 30);
	}

	
	public void createVariablesPanelInterface()
	{
		_variblesPanel.setBackground(_BGColor2);
		_variblesPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_variblesPanel.setLayout(null);
		
		_alterLabel.setBounds(25,5,150,20);
		_alterConstantsBox.setBounds(5,25,150,20);
		_alterAbsCoeffBox.setBounds(5,45,150,20);
		_alterInitConcentrationBox.setBounds(5,65,150,20);
		//_alterInitConcentrationBox.setEnabled(false);
		_alterBurntConcentrationBox.setBounds(5,85,150,20);
		_alterBurntConcentrationBox.setEnabled(false);
		
		_setLimitsButton.setBounds(160,5, 130, 40);
		//_setLimitsButton.setEnabled(false);
		
		_defineRelationsButton.setBounds(160,45, 130, 40);
		_defineRelationsButton.setEnabled(false);
		
		_variblesPanel.add(_alterLabel);
		_variblesPanel.add(_alterConstantsBox);
		_variblesPanel.add(_alterAbsCoeffBox);
		_variblesPanel.add(_alterInitConcentrationBox);
		//_variblesPanel.add(_alterBurntConcentrationBox);
		_variblesPanel.add(_setLimitsButton);
		_variblesPanel.add(_defineRelationsButton);

		
	}
	
	public void createProgressPanelInterface()
	{
		_progressPanel.setBackground(_BGColor1);
		_progressPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_progressPanel.setLayout(null);
		
		_progressBarSlow.setBounds(5,30, 40, 80);
		_progressBarSlow.setMaximum(100);
		_progressBarSlow.setMinimum(0);
		_progressBarSlow.setOrientation(1);
		_progressBarSlow.setValue(0);
		
		_progressBarFast.setBounds(50,30, 40, 80);
		_progressBarFast.setMaximum(100);
		_progressBarFast.setMinimum(0);
		_progressBarFast.setOrientation(1);
		
		_startFittingButton.setBounds(160,30, 130, 80);
		_stopFittingButton.setBounds(160,30, 130, 80);

		_globalSigmaName.setBounds(5,5,100,20);
		_globalSigma.setBounds(105,5,150,20);
		_globalSigma.setBackground(Color.white);
		_globalSigma.setOpaque(true);
		_globalSigma.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.DARK_GRAY));
		
		_progressPanel.add(_globalSigma);
		_progressPanel.add(_globalSigmaName);
		_progressPanel.add(_startFittingButton);
		_progressPanel.add(_progressBarSlow);
		_progressPanel.add(_progressBarFast);


	}

	
	public void createSelectAlgorithmPanelInterface()
	{
		
		_selectAlgorithmPanel.setBorder(BorderFactory.createEtchedBorder());
		_selectAlgorithmPanel.setLayout(null);
		
		_selectAlgorithmLabel.setBounds(5,8,130,23);
		_selectAlgorithmBox.setBounds(130,8,160,23);
		_selectAlgorithmBox.setSelectedIndex(0);
		_selectAlgorithmBox.setEnabled(false);
		
		_selectAlgorithmPanel.add(_selectAlgorithmLabel);
		_selectAlgorithmPanel.add(_selectAlgorithmBox);	
		
		
	}
	
	
	public void createSliderBasicPanelInterface()
	{
		_sliderBasicPanel.setBackground(_BGColor2);
		_sliderBasicPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_sliderBasicPanel.setLayout(null);
		
		accuracySliderSettings();
		
		_advancedSettingsBox.setBounds(10,5,200,20);
			
		_sliderBasicPanel.add(_accuracySlider);
		_sliderBasicPanel.add(_accuracySliderName);
		_sliderBasicPanel.add(_advancedSettingsBox);
	}
	
	
	
	
	
	
	public void createSliderGradientAdvancedPanelInterface()
	{
		_sliderGradientAdvancedPanel.setBackground(_BGColor2);
		_sliderGradientAdvancedPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_sliderGradientAdvancedPanel.setLayout(null);
		
		_advancedSettingsBox1.setBounds(10,5,200,20);

		
		nOfIterationsConstSliderSettings();
		cubeSideConstSliderSettings();
		
		_sliderGradientAdvancedPanel.add(_nOfIterationsSlider);
		_sliderGradientAdvancedPanel.add(_cubeSideSlider);
		_sliderGradientAdvancedPanel.add(_nOfIterationsSliderName);
		_sliderGradientAdvancedPanel.add(_cubeSideSliderName);
		_sliderGradientAdvancedPanel.add(_advancedSettingsBox1);
	}
	
	
	
	public void createSliderDirectAdvancedPanelInterface()
	{
		_sliderDirectAdvancedPanel.setBackground(_BGColor2);
		_sliderDirectAdvancedPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_sliderDirectAdvancedPanel.setLayout(null);
		

		
		_advancedSettingsDirectBox.setBounds(10,5,200,20);
		
		directNOfIterationsSliderSettings();
		directLowLimitSliderSettings();
		directHighLimitSliderSettings();
		
		_sliderDirectAdvancedPanel.add(_directLowLimitSlider);
		_sliderDirectAdvancedPanel.add(_directHighLimitSlider);
		_sliderDirectAdvancedPanel.add(_directNOfIterationsSlider);
		_sliderDirectAdvancedPanel.add(_advancedSettingsDirectBox);
		_sliderDirectAdvancedPanel.add(_directLowLimitSliderName);
		_sliderDirectAdvancedPanel.add(_directHighLimitSliderName);
		_sliderDirectAdvancedPanel.add(_directNOfIterationsSliderName);

	}
	
	
	
	public void majorIterationsSliderSettings()
	{
		_majorIterationsSlider.setBounds(5,105, _frameSizeX-25, 50);
		_majorIterationsSlider.setMinimum(2);
		_majorIterationsSlider.setMaximum(20);
		_majorIterationsSlider.setPaintLabels(true);
		_majorIterationsSlider.setPaintTicks(true);
		_majorIterationsSlider.setPaintTrack(true);
		_majorIterationsSlider.setSnapToTicks(true);
		_majorIterationsSlider.setValue(10);
		_majorIterationsSlider.setEnabled(true);
		_majorIterationsSlider.setMajorTickSpacing(2);
		_majorIterationsSlider.setMinorTickSpacing(1);
		
		_majorIterationsSliderName.setBounds(95,155,100,20);
	}
	
	
	public void slopeSliderSettings()
	{
		_slopeSlider.setBounds(5,185, _frameSizeX-25, 50);
		_slopeSlider.setMinimum(2);
		_slopeSlider.setMaximum(10);
		_slopeSlider.setPaintLabels(true);
		_slopeSlider.setPaintTicks(true);
		_slopeSlider.setPaintTrack(true);
		_slopeSlider.setSnapToTicks(true);
		_slopeSlider.setValue(2);
		_slopeSlider.setEnabled(true);
		_slopeSlider.setMajorTickSpacing(2);
		_slopeSlider.setMinorTickSpacing(1);
		_slopeSlider.setEnabled(false);
		
		_slopeSliderName.setBounds(65,235,200,20);
	}
	
	
	public void minorIterationsSliderSettings()
	{
		_minorIterationsSlider.setBounds(5,25, _frameSizeX-25, 50);
		_minorIterationsSlider.setMinimum(2);
		_minorIterationsSlider.setMaximum(20);
		_minorIterationsSlider.setPaintLabels(true);
		_minorIterationsSlider.setPaintTicks(true);
		_minorIterationsSlider.setPaintTrack(true);
		_minorIterationsSlider.setSnapToTicks(true);
		_minorIterationsSlider.setValue(10);
		_minorIterationsSlider.setEnabled(true);
		_minorIterationsSlider.setMajorTickSpacing(2);
		//_minorIterationsSlider.setMinorTickSpacing(1);
		
		_minorIterationsSliderName.setBounds(90,75,100,20);
	}
	
	
	
	public void directNOfIterationsSliderSettings()
	{
		
		_directNOfIterationsSlider.setBounds(5,25, _frameSizeX-25, 50);
		_directNOfIterationsSlider.setMinimum(2);
		_directNOfIterationsSlider.setMaximum(20);
		_directNOfIterationsSlider.setPaintLabels(true);
		_directNOfIterationsSlider.setPaintTicks(true);
		_directNOfIterationsSlider.setPaintTrack(true);
		_directNOfIterationsSlider.setSnapToTicks(true);
		_directNOfIterationsSlider.setValue(10);
		_directNOfIterationsSlider.setEnabled(true);
		_directNOfIterationsSlider.setMajorTickSpacing(2);
		_directNOfIterationsSlider.setMinorTickSpacing(1);
		
		_directNOfIterationsSliderName.setBounds(100,75,100,20);
	}
	
	
	public void directLowLimitSliderSettings()
	{
		_directLowLimitSlider.setBounds(5,105, _frameSizeX-25, 50);
		_directLowLimitSlider.setMinimum(10);
		_directLowLimitSlider.setMaximum(90);
		_directLowLimitSlider.setPaintLabels(true);
		_directLowLimitSlider.setPaintTicks(true);
		_directLowLimitSlider.setPaintTrack(true);
		_directLowLimitSlider.setSnapToTicks(true);
		_directLowLimitSlider.setValue(10);
		_directLowLimitSlider.setEnabled(true);
		_directLowLimitSlider.setMajorTickSpacing(10);
		_directLowLimitSlider.setMinorTickSpacing(2);
		
		_directLowLimitSliderName.setBounds(90,155,120,20);		
	}
	
	public void directHighLimitSliderSettings()
	{
		_directHighLimitSlider.setBounds(5,185, _frameSizeX-25, 50);
		_directHighLimitSlider.setMinimum(100);
		_directHighLimitSlider.setMaximum(500);
		_directHighLimitSlider.setPaintLabels(true);
		_directHighLimitSlider.setPaintTicks(true);
		_directHighLimitSlider.setPaintTrack(true);
		_directHighLimitSlider.setSnapToTicks(true);
		_directHighLimitSlider.setValue(190);
		_directHighLimitSlider.setEnabled(true);
		_directHighLimitSlider.setMajorTickSpacing(100);
		_directHighLimitSlider.setMinorTickSpacing(10);
		
		_directHighLimitSliderName.setBounds(90,235,200,20);
		
	}
	
	
	public void accuracySliderSettings()
	{
		_accuracySlider.setBounds(5,25, _frameSizeX-25, 50);
		_accuracySlider.setMinimum(0);
		_accuracySlider.setMaximum(20);
		_accuracySlider.setPaintLabels(true);
		_accuracySlider.setPaintTicks(true);
		_accuracySlider.setPaintTrack(true);
		_accuracySlider.setSnapToTicks(true);
		_accuracySlider.setValue(5);
		_accuracySlider.setEnabled(true);
		_accuracySlider.setMajorTickSpacing(5);
		_accuracySlider.setMinorTickSpacing(1);
		
		_accuracySliderName.setBounds(120,75,100,20);
	}
	
	
	
	public void nOfIterationsConstSliderSettings()
	{
		_nOfIterationsSlider.setBounds(5,25, _frameSizeX-25, 50);
		_nOfIterationsSlider.setMinimum(0);
		_nOfIterationsSlider.setMaximum(50);
		_nOfIterationsSlider.setPaintLabels(true);
		_nOfIterationsSlider.setPaintTicks(true);
		_nOfIterationsSlider.setPaintTrack(true);
		_nOfIterationsSlider.setSnapToTicks(true);
		_nOfIterationsSlider.setValue(10);
		_nOfIterationsSlider.setEnabled(true);
		_nOfIterationsSlider.setMajorTickSpacing(10);
		_nOfIterationsSlider.setMinorTickSpacing(2);
		
		_nOfIterationsSliderName.setBounds(90,75,100,20);
	}
	
	
	public void cubeSideConstSliderSettings()
	{
		_cubeSideSlider.setBounds(5,105, _frameSizeX-25, 50);
		_cubeSideSlider.setMinimum(1);
		_cubeSideSlider.setMaximum(10);
		_cubeSideSlider.setPaintLabels(true);
		_cubeSideSlider.setPaintTicks(true);
		_cubeSideSlider.setPaintTrack(true);
		_cubeSideSlider.setSnapToTicks(true);
		_cubeSideSlider.setValue(1);
		_cubeSideSlider.setEnabled(true);
		_cubeSideSlider.setMajorTickSpacing(1);
		//_cubeSideSlider.setMinorTickSpacing(1);
		
		_cubeSideSliderName.setBounds(65,155,160,20);
	}
	
	
	
	
	
	
	
	public void createWeightsPanelInterface()
	{
		_weightsPanel.setBackground(_BGColor2);
		_weightsPanel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.darkGray));
		_weightsPanel.setLayout(null);
		
		_useWeightBox.setBounds(10, 5, 200, 20);
		_useWeightBox.setOpaque(true);
		_useWeightBox.setBorder(BorderFactory.createLineBorder(Color.red));
		
		_weightsPanel.add(_useWeightBox);
	}
	
	
	

	
	public void setAdvancedGradientPanel()
	{
		int shift=80;
		this.remove(_sliderBasicPanel);
		this.remove(_sliderDirectAdvancedPanel);
		this.add(_sliderGradientAdvancedPanel);
		this.setSize(new Dimension(_frameSizeX,_frameSizeY+shift));
		_weightsPanel.setBounds(0,353+shift, _frameSizeX-5, 30);
		this.repaint();
	}
	
	public void setAdvancedDirectPanel()
	{
		int shift=80+80;
		this.remove(_sliderBasicPanel);
		this.remove(_sliderGradientAdvancedPanel);
		this.add(_sliderDirectAdvancedPanel);
		this.setSize(new Dimension(_frameSizeX,_frameSizeY+shift));
		_weightsPanel.setBounds(0,353+shift, _frameSizeX-5, 30);
		this.repaint();
	}
	
	
	public void setBasicPanel()
	{
		this.remove(_sliderGradientAdvancedPanel);
		this.remove(_sliderDirectAdvancedPanel);
		this.add(_sliderBasicPanel);
		this.setSize(new Dimension(_frameSizeX,_frameSizeY));
		_weightsPanel.setBounds(0,353, _frameSizeX-5, 30);
		this.repaint();
	}
	
	
	
	
	
}
