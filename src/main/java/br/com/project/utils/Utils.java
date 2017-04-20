package br.com.project.utils;

public class Utils {

	public static final void sleep (int time){
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			
		}

	}
	
	public static final int getRandomNumber(int minimum, int maximum){
		return (int)(minimum + (Math.random() * maximum));
	}
	
}
