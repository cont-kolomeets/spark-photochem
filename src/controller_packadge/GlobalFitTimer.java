package controller_packadge;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.TimerTask;
import java.util.Timer;




public class GlobalFitTimer{

	private final Timer timer = new Timer();

	public GlobalFitTimer()
	{

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

	
	
    
	public void initialization() {

	    SwingTimerTask updatePanTask = new SwingTimerTask() {
		public void doRun() 
		{
			Toolkit.getDefaultToolkit().beep();
			System.out.println("Beep!");
		}};


	    timer.schedule(updatePanTask, 0, 2000); //task, delay, period
	}

	
	
	
	
	
	
	
}
