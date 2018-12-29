package hw4;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/* 
 * CrapsGame.java: This file contains the algorithm of a single game, print out
 * the process and upload all the stats inside CrapsMetricsMonitors class.
 */

public class CrapsGame {
	/*A class representing all the information for a single craps game. 
	 * This includes: 
	▪	Number of times a roll happened
	▪	A CrapsMetricsMonitor object
	*/
	private int rollNum=0;
	private CrapsMetricsMonitors cmonitor = new CrapsMetricsMonitors();
	private String threadName;
	private PrintWriter pw;
	
	
	
	public CrapsGame() {
		; //empty constructor
	}
	
	public CrapsGame(CrapsMetricsMonitors monitor,PrintWriter newpw, String newThreadName) {
	/*Constructor that initializes the class fields.
	 *  A CrapsMetricsMonitor is passed into the constructor 
	 *  since there are specific stats within a single 
	 * game that must be updated (and the same object should be
	 *  used for all metrics in the simulation).
	 */
		cmonitor = monitor;
		threadName=newThreadName;
		pw=newpw;

	
	}	
	public void natural() {	
		// the natural case: when you roll 7 or 11 on the first roll
		
		cmonitor.increaseWinNum();
		cmonitor.increaseNaturalCount();
		cmonitor.updateWinstreak();
		cmonitor.clearLosestreak();
		cmonitor.increaseBalance();
		
	}
	
	public void craps() {
		//the craps case : when you roll 2 or 3 or 12 on the first roll
		
		cmonitor.increaseLoseNum();
		cmonitor.increaseCrapsCount();
		cmonitor.updateLosestreak();
		cmonitor.clearWinstreak();
		cmonitor.decreaseBalance();
		
	}
	
	public void rollThePoint() {
		//when roll the point, you win
		
		cmonitor.increaseWinNum();
		cmonitor.updateWinstreak();
		cmonitor.clearLosestreak();
		cmonitor.increaseBalance();
		
		
	}
	
	public void crapOut() {
		//when roll a 7 before roll the point, you lose
		
		cmonitor.increaseLoseNum();
		cmonitor.updateLosestreak();
		cmonitor.clearWinstreak();
		cmonitor.decreaseBalance();
		
	}
	
	public boolean  playGame() {
	/*	Contains the algorithm for an actual game.*/
		//String fileName = "src/"+ threadName + ".txt";
		try {
			
		//pw= CrapsSimulation.passPrintWriter();	
		//PrintWriter pw = new PrintWriter(fileName);
		cmonitor.increaseGameNum(); //increase game number
		
		boolean winGame = true;
		Random rand = new Random();
		int die1 = rand.nextInt(6)+1;
		Thread.sleep(0);
		pw.write("Rolled the 1st die at "+System.currentTimeMillis()+"\n");
		
		int die2 = rand.nextInt(6)+1;
		pw.write("Rolled the 2nd die at "+System.currentTimeMillis()+"\n");
		
		int sum = die1+die2;
		pw.write("Rolled a "+sum+"\n");
		rollNum++;
		if(sum == 7 || sum==11)
		{
			natural();
			pw.write("***** Natural! You win! *****"+"\n");
			winGame=true;
		}
			
		else if(sum==2 || sum == 3 || sum == 12)
		{
			craps();
			pw.write("***** Craps! You loose. *****"+"\n");
			winGame=false;
		}
		else
		{
			int point =sum;
			boolean gameEnd = false;
			
			do 
			{
				int die3 =rand.nextInt(6)+1;
				pw.write("Rolled the 1st die at "+System.currentTimeMillis()+"\n");
				int die4 = rand.nextInt(6)+1;
				pw.write("Rolled the 2nd die at "+System.currentTimeMillis()+"\n");
				sum = die3 +die4;
				pw.write("Rolled a "+sum+"\n");

				rollNum++;
				if(point == sum)
				{
					
					rollThePoint();
					pw.write("***** Rolled the point! You win! *****"+"\n");
					gameEnd = true;
					winGame = true;
				}
			
				if(sum ==7)
				{
					crapOut();
					pw.write("***** Crap out! You loose. *****"+"\n");
					gameEnd = true;
					winGame= false;
				}
				
			}while(gameEnd==false);	
			
		}
		cmonitor.updateMaxRollnum(rollNum);
	
		
		return winGame;	
		
			
		}

	catch(InterruptedException ex) {
		Thread.currentThread().interrupt();
		return false;
	}
	
	}
	}
