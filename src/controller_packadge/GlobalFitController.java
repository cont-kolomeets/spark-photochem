package controller_packadge;

import interface_packadge.GlobalFitExtGraphPanelMovingAdapter;
import interface_packadge.GlobalFitInterface;
import interface_packadge.GlobalFitMovingAdapter;
import interface_packadge.GlobalFitProgressBarMonitoring;
import interface_packadge.GlobalFitSlicePanelMovingAdapter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import math_packadge.GlobalFitMath;
import math_packadge.MethodsCollection;

public class GlobalFitController {

	GlobalFitInterface _globalFitInterface1;
	GlobalFitMath _globalFitMath1;
	GlobalFitMovingAdapter _mA;
	GlobalFitExtGraphPanelMovingAdapter _mAExt;
	GlobalFitSlicePanelMovingAdapter _mASlice;
	TransferToClipboard _transferToClipboard;
	GlobalFitMovingAdapterSigma _mASigma = new GlobalFitMovingAdapterSigma();
	// ProgressBarMonitoring _progressBarMonitoring = new
	// ProgressBarMonitoring();
	GlobalFitProgressBarMonitoring _globalFitProgressBarMonitoring;

	GlobalFitControllerAbsInputFrame _globalFitControllerAbsInputFrame;
	GlobalFitControllerConstantPanel _globalFitControllerConstantPanel;
	GlobalFitControllerDefineRelationsFrame _globalFitControllerDefineRelationsFrame;
	GlobalFitControllerDiffEqPanel _globalFitControllerDiffEqPanel;
	GlobalFitControllerExtFrame _globalFitControllerExtFrame;
	GlobalFitControllerFittingFrame _globalFitControllerFittingFrame;
	GlobalFitControllerGraphPanel _globalFitControllerGraphPanel;
	GlobalFitControllerGraphPanelScaleFrame _globalFitControllerGraphPanelScaleFrame;
	GlobalFitControllerKinControlPanel _globalFitControllerKinControlPanel;
	GlobalFitControllerMenuBar _globalFitControllerMenuBar;
	GlobalFitControllerModelButtons _globalFitControllerModelButtons;
	GlobalFitControllerPanelRefresher _globalFitControllerPanelRefresher;
	GlobalFitControllerRK4OptionsPanel _globalFitControllerRK4OptionsPanel;
	GlobalFitControllerSetLimitsFrame _globalFitControllerSetLimitsFrame;
	GlobalFitControllerSigmaFrame _globalFitControllerSigmaFrame;
	GlobalFitControllerSliceFrame _globalFitControllerSliceFrame;
	GlobalFitControllerToolBar _globalFitControllerToolBar;
	GlobalFitOpenRecentListCreator _globalFitOpenRecentListCreator;
	GlobalFitControllerTableDataWorker _globalFitControllerTableDataWorker;

	Timer timer = new Timer();

	public int i = 0;

	public GlobalFitController(GlobalFitInterface _globalFitInterface,
			GlobalFitMath _globalFitMath) {

		_globalFitInterface1 = _globalFitInterface;
		_globalFitMath1 = _globalFitMath;
		_globalFitProgressBarMonitoring = new GlobalFitProgressBarMonitoring(
				_globalFitInterface1, _globalFitMath1, this);
		_mAExt = new GlobalFitExtGraphPanelMovingAdapter(_globalFitInterface1,
				_globalFitMath1, this);
		_mASlice = new GlobalFitSlicePanelMovingAdapter(_globalFitInterface1,
				_globalFitMath1, this);
		_mA = new GlobalFitMovingAdapter(_globalFitInterface1, _globalFitMath1,
				this);
		_transferToClipboard = new TransferToClipboard(_globalFitInterface1,
				_globalFitMath1, this);

		_globalFitControllerAbsInputFrame = new GlobalFitControllerAbsInputFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerConstantPanel = new GlobalFitControllerConstantPanel(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerDefineRelationsFrame = new GlobalFitControllerDefineRelationsFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerDiffEqPanel = new GlobalFitControllerDiffEqPanel(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerExtFrame = new GlobalFitControllerExtFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerFittingFrame = new GlobalFitControllerFittingFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerGraphPanel = new GlobalFitControllerGraphPanel(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerGraphPanelScaleFrame = new GlobalFitControllerGraphPanelScaleFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerKinControlPanel = new GlobalFitControllerKinControlPanel(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerMenuBar = new GlobalFitControllerMenuBar(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerModelButtons = new GlobalFitControllerModelButtons(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerPanelRefresher = new GlobalFitControllerPanelRefresher(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerRK4OptionsPanel = new GlobalFitControllerRK4OptionsPanel(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerSetLimitsFrame = new GlobalFitControllerSetLimitsFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerSigmaFrame = new GlobalFitControllerSigmaFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerSliceFrame = new GlobalFitControllerSliceFrame(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerToolBar = new GlobalFitControllerToolBar(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitOpenRecentListCreator = new GlobalFitOpenRecentListCreator(
				_globalFitInterface1, _globalFitMath1, this);
		_globalFitControllerTableDataWorker = new GlobalFitControllerTableDataWorker(
				_globalFitInterface1, _globalFitMath1, this);

		createOpenedRecentList();

		addMenuBarListeners();
		addToolBarListeners();
		addGraphPanelListeners();
		addGlobalFitSliceFrameListeners();
		addGraphPanelScaleFrameListeners();
		addModelButtonListeners();
		addKinControlButtonListeners();
		addRK4OptionsListener();
		addSigmaFrameListeners();
		addDiffEqPanelListeners();
		addExtFrameListeners();
		addConstantPanelListener();
		addGlobalFitFittingFrameListeners();
		addGlobalFitSetLimitsFrameListeners();
		addAbsInputFrameListenters();
		addGlobalFitDefineRelationsFrameListeners();

		_globalFitInterface1._globalFitMainInterface._graphPanel
				.addMouseMotionListener(_mA);
		_globalFitInterface1._globalFitMainInterface._graphPanel
				.addMouseListener(_mA);
		_globalFitInterface1._globalFitExtFrame._extGraphPanel
				.addMouseMotionListener(_mAExt);
		_globalFitInterface1._globalFitExtFrame._extGraphPanel
				.addMouseListener(_mAExt);
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel
				.addMouseMotionListener(_mASigma);
		_globalFitInterface1._globalFitSigmaFrame._sigmaPanel
				.addMouseListener(_mASigma);
		_globalFitInterface1._globalFitSliceFrame._slicePanel
				.addMouseMotionListener(_mASlice);
		_globalFitInterface1._globalFitSliceFrame._slicePanel
				.addMouseListener(_mASlice);

		graphPanelRefresh();

	}

	public void createOpenedRecentList() {
		_globalFitOpenRecentListCreator.createOpenedRecentList();
	}

	public void refreshOpenedRecentList() {
		_globalFitOpenRecentListCreator.refreshOpenedRecentList();
	}

	public void openRecentProject(File file) {
		_globalFitOpenRecentListCreator.openRecentProject(file);
	}

	public void openProject() {

		pushWindowsBack();
		String fileName = _globalFitMath1.globalFitOpenProject();
		_globalFitInterface1._globalFitMainInterface._status
				.setText("Opened project file: " + fileName);

		if ((_globalFitMath1.get_kinCollectionX().size() != 0)
				& (!fileName.equals("No file"))) {
			_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel
					.setText(""
							+ _globalFitMath1.get_kinCollectionX().get(
									_globalFitMath1
											.get_globalFitPosOfSelectedKin())
									.get(0));
			_globalFitInterface1._globalFitMainInterface._thicknessField
					.setText("" + _globalFitMath1.get_cuvetteThickness());

			for (int i = 0; i < 9; i++)
				_globalFitInterface1._globalFitExtFrame._xSButtonsArray[i]
						.setBorder(BorderFactory.createLineBorder(new Color(
								150, 150, 150), 1));
			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[0]
					.doClick();

			graphPanelRefresh();
			extGraphPanelRefresh();
			setAllLabelsContent();

		}
		setWindowsOnTop();

		createOpenedRecentList();
	}

	public void setWaitingCursorForAllWindows(boolean _option) {
		if (_option) {
			_globalFitInterface1._globalFitMainInterface.setCursor(Cursor
					.getPredefinedCursor(Cursor.WAIT_CURSOR));
			_globalFitInterface1._globalFitFittingFrame.setCursor(Cursor
					.getPredefinedCursor(Cursor.WAIT_CURSOR));
			_globalFitInterface1._globalFitMainInterface._graphPanel
					.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			_globalFitInterface1._globalFitExtFrame.setCursor(Cursor
					.getPredefinedCursor(Cursor.WAIT_CURSOR));
		} else {
			_globalFitInterface1._globalFitMainInterface.setCursor(Cursor
					.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			_globalFitInterface1._globalFitFittingFrame.setCursor(Cursor
					.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			_globalFitInterface1._globalFitMainInterface._graphPanel
					.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			_globalFitInterface1._globalFitExtFrame.setCursor(Cursor
					.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public void addMenuBarListeners() {
		_globalFitControllerMenuBar.addMenuBarListeners();
	}

	public void addToolBarListeners() {
		_globalFitControllerToolBar.addToolBarListeners();
	}

	public void addGraphPanelListeners() {
		_globalFitControllerGraphPanel.addGraphPanelListeners();
	}

	public void prepareSliceFrameToShow() {
		_globalFitInterface1._globalFitSliceFrame.setVisible(true);
		_globalFitMath1.slicePanelRecalcSpecBounds();
		_globalFitMath1.set_ifShowSlice(true);
		graphPanelRefresh();
		slicePanelRefresh();
		_globalFitMath1.set_timeStep(1);
		_globalFitInterface1._globalFitSliceFrame._timeStepValueLabel
				.setText(MethodsCollection.cutStringAfterPoint(""
						+ _globalFitMath1.calcTimeInterval(), 5));

		for (int i = 0; i < 10; i++) {
			_globalFitInterface1._globalFitSliceFrame._xSNamesArray[i]
					.setText(_globalFitInterface1._globalFitExtFrame._xSNamesArray[i]
							.getText());
		}
		// _globalFitInterface1._globalFitSliceFrame.displayNXS(_globalFitMath1.
		// get_nOfEq());
	}

	public void addGlobalFitSliceFrameListeners() {
		_globalFitControllerSliceFrame.addGlobalFitSliceFrameListeners();
	}

	public void addGraphPanelScaleFrameListeners() {
		_globalFitControllerGraphPanelScaleFrame
				.addGraphPanelScaleFrameListeners();
	}

	public void addModelButtonListeners() {
		_globalFitControllerModelButtons.addModelButtonListeners();
	}

	public void addKinControlButtonListeners() {
		_globalFitControllerKinControlPanel.addKinControlButtonListeners();
	}

	public void addConstantPanelListener() {
		_globalFitControllerConstantPanel.addConstantPanelListener();
	}

	public void addRK4OptionsListener() {
		_globalFitControllerRK4OptionsPanel.addRK4OptionsListener();
	}

	public void addSigmaFrameListeners() {
		_globalFitControllerSigmaFrame.addSigmaFrameListeners();
	}

	public void addExtFrameListeners() {
		_globalFitControllerExtFrame.addExtFrameListeners();
	}

	public void addAbsInputFrameListenters() {
		_globalFitControllerAbsInputFrame.addAbsInputFrameListenters();
	}

	public void addDiffEqPanelListeners() {
		_globalFitControllerDiffEqPanel.addDiffEqPanelListeners();
	}

	public void addGlobalFitFittingFrameListeners() {
		_globalFitControllerFittingFrame.addGlobalFitFittingFrameListeners();
	}

	public void addGlobalFitDefineRelationsFrameListeners() {
		_globalFitControllerDefineRelationsFrame
				.addGlobalFitDefineRelationsFrameListeners();
	}

	public void addGlobalFitSetLimitsFrameListeners() {

		_globalFitControllerSetLimitsFrame
				.addGlobalFitSetLimitsFrameListeners();
	}

	public void shiftNOfEq(int shift, boolean _ifAddNexXSpec) {
		_globalFitMath1.set_nOfEq(_globalFitMath1.get_nOfEq() + shift);
		if (_globalFitMath1.get_nOfEq() < 1)
			_globalFitMath1.set_nOfEq(1);
		if (_globalFitMath1.get_nOfEq() > 10)
			_globalFitMath1.set_nOfEq(10);

		if (_globalFitMath1.get_globalFitPosOfSelectedSpec() > (_globalFitMath1
				.get_nOfEq() - 1))
			_globalFitInterface1._globalFitExtFrame._xSButtonsArray[0]
					.doClick();

		if (shift == 1) {
			if (_ifAddNexXSpec)
				_globalFitMath1.addNewXDefaultSpec();
			extGraphPanelRefresh();

			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_eqLabelsArray()[_globalFitMath1.get_nOfEq() - 1]);
			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_concentrationLabelsArray()[_globalFitMath1
							.get_nOfEq() - 1]);
			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_beforePulseConcentrationLabelsArray()[_globalFitMath1
							.get_nOfEq() - 1]);

			_globalFitInterface1._globalFitExtFrame._extFXPanel
					.add(_globalFitInterface1._globalFitExtFrame
							.get_xSButtonsArray()[_globalFitMath1.get_nOfEq() - 1]);
			_globalFitInterface1._globalFitExtFrame._extFXPanel
					.add(_globalFitInterface1._globalFitExtFrame
							.get_xSNamesArray()[_globalFitMath1.get_nOfEq() - 1]);

			if (_globalFitMath1.get_nOfEq() == 6) {
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.add(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[3]);
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.add(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[4]);
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.add(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[5]);
			}

		} else {

			_globalFitMath1.removeOneXSpec();
			extGraphPanelRefresh();

			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_eqLabelsArray()[_globalFitMath1.get_nOfEq()]);
			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_concentrationLabelsArray()[_globalFitMath1
							.get_nOfEq()]);
			_globalFitInterface1._globalFitMainInterface._diffEqPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_beforePulseConcentrationLabelsArray()[_globalFitMath1
							.get_nOfEq()]);

			_globalFitInterface1._globalFitExtFrame._extFXPanel
					.remove(_globalFitInterface1._globalFitExtFrame
							.get_xSButtonsArray()[_globalFitMath1.get_nOfEq()]);
			_globalFitInterface1._globalFitExtFrame._extFXPanel
					.remove(_globalFitInterface1._globalFitExtFrame
							.get_xSNamesArray()[_globalFitMath1.get_nOfEq()]);

			if (_globalFitMath1.get_nOfEq() == 5) {
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.remove(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[3]);
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.remove(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[4]);
				_globalFitInterface1._globalFitMainInterface._diffEqPanel
						.remove(_globalFitInterface1._globalFitMainInterface
								.get_eqLabelsNameArray()[5]);
			}
		}
		_globalFitInterface1._globalFitMainInterface._diffEqPanel.repaint();

		_globalFitInterface1._globalFitExtFrame._extFXPanel.repaint();

		String s = "" + _globalFitMath1.get_nOfEq();
		_globalFitInterface1._globalFitMainInterface._nOfEqLabel.setText(s);

	}

	public void shiftNOfConst(int shift) {
		_globalFitMath1.set_nOfConst(_globalFitMath1.get_nOfConst() + shift);
		if (_globalFitMath1.get_nOfConst() < 1)
			_globalFitMath1.set_nOfConst(1);
		if (_globalFitMath1.get_nOfConst() > 10)
			_globalFitMath1.set_nOfConst(10);

		if (shift == 1) {
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_constantLabelsArray()[_globalFitMath1
							.get_nOfConst() - 1]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_constantErrorLabelsArray()[_globalFitMath1
							.get_nOfConst() - 1]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_constantLabelsNameArray()[_globalFitMath1
							.get_nOfConst() - 1]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_constantValueUpButton()[_globalFitMath1
							.get_nOfConst() - 1]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.add(_globalFitInterface1._globalFitMainInterface
							.get_constantValueDownButton()[_globalFitMath1
							.get_nOfConst() - 1]);

		} else {
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_constantLabelsArray()[_globalFitMath1
							.get_nOfConst()]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_constantErrorLabelsArray()[_globalFitMath1
							.get_nOfConst()]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_constantLabelsNameArray()[_globalFitMath1
							.get_nOfConst()]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_constantValueUpButton()[_globalFitMath1
							.get_nOfConst()]);
			_globalFitInterface1._globalFitMainInterface._constantsPanel
					.remove(_globalFitInterface1._globalFitMainInterface
							.get_constantValueDownButton()[_globalFitMath1
							.get_nOfConst()]);

		}
		_globalFitInterface1._globalFitMainInterface._constantsPanel.repaint();

		String s = "" + _globalFitMath1.get_nOfConst();
		_globalFitInterface1._globalFitMainInterface._nOfConstLabel.setText(s);

	}

	public void graphPanelRefresh() {
		_globalFitControllerPanelRefresher.graphPanelRefresh();
	}

	public void extGraphPanelRefresh() {
		_globalFitControllerPanelRefresher.extGraphPanelRefresh();
	}

	public void slicePanelRefresh() {
		_globalFitControllerPanelRefresher.slicePanelRefresh();
	}

	public void errorPanelRefresh() {
		_globalFitControllerPanelRefresher.errorPanelRefresh();
	}

	public void sigmaPanelSetParameters() {
		_globalFitControllerPanelRefresher.sigmaPanelSetParameters();
	}

	public void pushWindowsBack() {
		_globalFitInterface1._globalFitExtFrame.toBack();
		_globalFitInterface1._globalFitFittingFrame.toBack();
		_globalFitInterface1._globalFitGraphPanelScaleFrame.toBack();
		_globalFitInterface1._globalFitInputExtFrame.toBack();
		_globalFitInterface1._globalFitSetLimitsFrame.toBack();
		_globalFitInterface1._globalFitSigmaFrame.toBack();
		_globalFitInterface1._globalFitSliceFrame.toBack();
	}

	public void setWindowsOnTop() {
		_globalFitInterface1._globalFitExtFrame.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitFittingFrame.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitGraphPanelScaleFrame
				.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitInputExtFrame.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitSetLimitsFrame.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitSigmaFrame.setAlwaysOnTop(true);
		_globalFitInterface1._globalFitSliceFrame.setAlwaysOnTop(true);
	}

	public void warningMessage(String line) {
		pushWindowsBack();
		JOptionPane.showMessageDialog(
				_globalFitInterface1._globalFitMainInterface, line);
		setWindowsOnTop();
	}

	public void openFile() {

		pushWindowsBack();

		_globalFitInterface1._globalFitMainInterface._graphPanel
				.set_ifShowCreatedCurve(false);
		String _fileName = _globalFitMath1.openFile();
		System.out.println("FileNameIs: " + _fileName);
		_globalFitInterface1._globalFitMainInterface
				.setStatusText("Opened file:  " + _fileName);

		//_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel
		// .setText("" + _globalFitMath1.get_globalFitSelectedWavelength());
		if (_globalFitMath1.get_kinCollectionX().size() != 0) {
			_globalFitInterface1._globalFitMainInterface._globalFitWavelengthLabel
					.setText(""
							+ _globalFitMath1.get_kinCollectionX().get(
									_globalFitMath1
											.get_globalFitPosOfSelectedKin())
									.get(0));
			_globalFitMath1.extGraphPanelSetXSSpecBoundsX();
			extGraphPanelRefresh();

			graphPanelRefresh();

		}

		setWindowsOnTop();

	}

	public void readThicknessValue() {
		String text = _globalFitInterface1._globalFitMainInterface._thicknessField
				.getText();

		if (!text.equals("")) {

			try {
				_globalFitMath1.set_cuvetteThickness(Float.parseFloat(text
						.replace(',', '.')));
			} catch (NumberFormatException e) {

				pushWindowsBack();
				JOptionPane.showMessageDialog(
						_globalFitInterface1._globalFitMainInterface,
						"Can't read cuvette thickness value!");
				setWindowsOnTop();
				text = "1";
				_globalFitInterface1._globalFitMainInterface._thicknessField
						.setText(text);
				_globalFitInterface1._globalFitMainInterface._thicknessField
						.repaint();
				_globalFitMath1.set_cuvetteThickness(Float.parseFloat(text
						.replace(',', '.')));
			}

		}
	}

	public void fitRK4() {
		String text = "";
		if (_globalFitMath1.get_kinCollectionX().size() != 0) {

			readThicknessValue();

			String _warning = "";
			_warning = _globalFitMath1.readDiffEqLabelsData(true);

			if (!_warning.equals("")) {
				pushWindowsBack();
				JOptionPane.showMessageDialog(
						_globalFitInterface1._globalFitMainInterface, _warning);
				setWindowsOnTop();
			}

			if (_globalFitMath1.get_ifCalcCurveShifts())
				_globalFitMath1.calcCurveShifts();
			if (_globalFitMath1.get_ifUseWeight())
				_globalFitMath1.calcWeightsForCurves();

			// checkStatus();

			_globalFitMath1.fitRK4();

			if (_globalFitMath1.get_ifCalcContributions())
				_globalFitMath1.fillContributionArrays();

			text = "" + _globalFitMath1.calcGlobalSigma();
			_globalFitInterface1._globalFitFittingFrame._globalSigma
					.setText(text);
			_globalFitInterface1._globalFitMainInterface._globalSigma
					.setText(text);

			if (_globalFitInterface1._globalFitSigmaFrame.isVisible()) {
				System.out.println("calc sigma");
				_globalFitMath1.calcSigmaPanelBounds();
				sigmaPanelSetParameters();
				_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.repaint();
			}

			if (_globalFitInterface1._globalFitSliceFrame.isVisible()) {
				slicePanelRefresh();
			}

			graphPanelRefresh();
			errorPanelRefresh();

		}

	}

	public void showSigma() {
		if (_globalFitMath1.get_sigmaCollection().size() != 0) {
			_globalFitMath1.calcSigmaPanelBounds();
			sigmaPanelSetParameters();
			_globalFitInterface1._globalFitSigmaFrame._sigmaPanel.repaint();
			_globalFitInterface1._globalFitSigmaFrame.setVisible(true);
		} else {
			pushWindowsBack();
			JOptionPane.showMessageDialog(
					_globalFitInterface1._globalFitMainInterface,
					"Nothing to show!!!");
			setWindowsOnTop();
		}
	}

	public void fillSetLimitsTableData() {
		_globalFitControllerTableDataWorker.fillSetLimitsTableData();
	}

	public void readSetLimitsTableContent() {
		_globalFitControllerTableDataWorker.readSetLimitsTableContent();
	}

	public void getXSLabelsContent() {

		for (int i = 0; i < _globalFitMath1.get_nOfEq(); i++)
			_globalFitMath1.get_xSNamesArray()[i] = _globalFitInterface1._globalFitExtFrame
					.get_xSNamesArray()[i].getText();
	}

	public void setAllLabelsContent() {

		while (_globalFitMath1.get_nOfConst() != _globalFitMath1
				.get_nOfConstOpened()) {
			// System.out.println(_globalFitMath1.get_nOfConst() + "  " +
			// _globalFitMath1.get_nOfConstOpened());

			if (_globalFitMath1.get_nOfConstOpened() > _globalFitMath1
					.get_nOfConst()) {
				shiftNOfConst(1);
			} else {
				shiftNOfConst(-1);
			}

		}

		while (_globalFitMath1.get_nOfEq() != _globalFitMath1.get_nOfEqOpened()) {
			if (_globalFitMath1.get_nOfEqOpened() > _globalFitMath1.get_nOfEq()) {
				shiftNOfEq(1, false);
			} else {
				shiftNOfEq(-1, false);
			}

		}

		for (int i = 0; i < _globalFitMath1.get_nOfEq(); i++) {
			_globalFitInterface1._globalFitMainInterface._eqLabelsArray[i]
					.setText(_globalFitMath1._eqLabelsContent[i]);
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[i]
					.setText(_globalFitMath1._concentrationLabelsContent[i]);
			_globalFitInterface1._globalFitMainInterface._beforePulseConcentrationLabelsArray[i]
					.setText(_globalFitMath1._beforePulseConcentrationLabelsContent[i]);
			_globalFitInterface1._globalFitExtFrame._xSNamesArray[i]
					.setText(_globalFitMath1._xSNamesArray[i]);

		}

		for (int i = 0; i < _globalFitMath1.get_nOfConst(); i++) {
			_globalFitInterface1._globalFitMainInterface._constantLabelsArray[i]
					.setText(_globalFitMath1._constantLabelsContent[i]);
			_globalFitInterface1._globalFitMainInterface._constantErrorLabelsArray[i]
					.setText("0");
			_globalFitMath1._constantErrorValues[i] = 0;
		}

		float a = _globalFitMath1.get_solvedCurveTimeScale();
		if (a == 1E6f)
			_globalFitInterface1._globalFitMainInterface._scaleModeBox
					.setSelectedIndex(0);
		if (a == 1E3f)
			_globalFitInterface1._globalFitMainInterface._scaleModeBox
					.setSelectedIndex(1);
		if (a == 1f)
			_globalFitInterface1._globalFitMainInterface._scaleModeBox
					.setSelectedIndex(2);

		_globalFitInterface1._globalFitMainInterface._thicknessField.setText(""
				+ _globalFitMath1.get_cuvetteThickness());

	}

	public void checkStatus() {
		if (_globalFitMath1.get_ifEnableCurveBounds()) {
			_globalFitMath1.checkmarkers();
			if (_globalFitMath1.get_ifStatusFine()) {
				_globalFitInterface1._globalFitMainInterface._statusPanel
						.setBackground(Color.green);
				_globalFitInterface1._globalFitMainInterface._statusPanel
						.repaint();
			} else {
				_globalFitInterface1._globalFitMainInterface._statusPanel
						.setBackground(Color.red);
				_globalFitInterface1._globalFitMainInterface._statusPanel
						.repaint();
			}
		}

	}

	public void globalFitRefreshAllOutPuts(boolean _ifCalcRK4) {
		String s;

		for (int i = 0; i < _globalFitMath1.get_nOfConst(); i++) {
			// System.out.println("constant = " +
			// _globalFitMath1.get_constantValues()[i]);
			s = "" + _globalFitMath1.get_constantValues()[i];
			_globalFitInterface1._globalFitMainInterface._constantLabelsArray[i]
					.setText(MethodsCollection.cutStringAfterPoint(s, 6));
			_globalFitMath1.get_constantLabelsContent()[i] = s;
		}

		for (int i = 0; i < _globalFitMath1.get_nOfEq(); i++) {
			// System.out.println("concentration = " +
			// _globalFitMath1.get_concentrationValues()[i]);
			s = "" + _globalFitMath1.get_concentrationValues()[i];
			_globalFitInterface1._globalFitMainInterface._concentrationLabelsArray[i]
					.setText(MethodsCollection.cutStringAfterPoint(s, 4));
			_globalFitMath1.get_concentrationLabelsContent()[i] = s;
		}

		if (_ifCalcRK4)
			_globalFitMath1.fitRK4();

		refreshGraphPanelsWithSync();

		s = "" + _globalFitMath1.calcGlobalSigma();
		_globalFitInterface1._globalFitFittingFrame._globalSigma.setText(s);
		_globalFitInterface1._globalFitMainInterface._globalSigma.setText(s);

	}

	public void refreshGraphPanelsWithSync() {
		lockGraphPanels(false); // enable graph panels to repaint

		_globalFitInterface1._globalFitMainInterface._graphPanel
				.set_ifDone(false);
		graphPanelRefresh();
		while (!_globalFitInterface1._globalFitMainInterface._graphPanel
				.get_ifDone()) {
		}

		_globalFitInterface1._globalFitMainInterface._errorPanel
				.set_ifDone(false);
		errorPanelRefresh();
		while (!_globalFitInterface1._globalFitMainInterface._errorPanel
				.get_ifDone()) {
		}

		//_globalFitInterface1._globalFitExtFrame._extGraphPanel.set_ifDone(false
		// );
		extGraphPanelRefresh();
		// while(!_globalFitInterface1._globalFitExtFrame._extGraphPanel.
		// get_ifDone())
		// {}

		lockGraphPanels(true); // enable graph panels to repaint
	}

	public void lockGraphPanels(boolean lock) {
		_globalFitInterface1._globalFitMainInterface._graphPanel
				.set_ifLockGraphPanels(lock);
		_globalFitMath1.set_ifLockGraphPanels(lock);
	}

	public void globalFitRefreshErrorLabels() {
		String s;

		for (int i = 0; i < _globalFitMath1.get_nOfConst(); i++) {
			s = "" + _globalFitMath1.get_constantErrorValues()[i];
			_globalFitInterface1._globalFitMainInterface._constantErrorLabelsArray[i]
					.setText(MethodsCollection.cutStringAfterPoint(s, 6));
		}
	}

	public class GlobalFitMovingAdapterSigma extends MouseAdapter {

		public void mouseMoved(MouseEvent e) {
			_globalFitMath1.sigmaPanelMouseMoved(e);
			_globalFitInterface1._globalFitSigmaFrame._sigmaPanel
					.set_mouseLabelText(_globalFitMath1
							.get_mouseLabelTextSigma());

		}

	}

	private static abstract class SwingTimerTask extends java.util.TimerTask {
		public abstract void doRun();

		public void run() {
			if (!EventQueue.isDispatchThread()) {
				EventQueue.invokeLater(this);
			} else {
				doRun();
			}
		}
	}

	public class CatCounter implements Runnable {

		public int _nOfCats = 0;

		public void run() {
			synchronized (_globalFitMath1.get_syncObject()) {
				for (;;) {
					_nOfCats++;
					System.out.println("------- Adding a cat. Total number = "
							+ _nOfCats);

				}
			}

		}

	}

}
