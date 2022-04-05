package application;

import java.util.Timer;
import java.util.TimerTask;

public class Caller extends ControllerExpress implements Runnable{
	public void start() {
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			public void run() {
				//newProblem();
			}
		}, 1000);
	}

	@Override
	public void run() {
		start();
		
	}
	
	
}
