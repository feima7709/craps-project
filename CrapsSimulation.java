package hw4;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/* Name: Fei Ma

 * Student ID: 86911681
 * CrapsSimulation.java: CrapsSimulation controls the entire simulation, it calls
 * CrapsGame to process each game and calls CrapsMetricsMonitors to upload statistics
 * It keeps runs until the user quit
 */

/*	A class representing all the information for the Simulation. This includes:
▪	A CrapsGame object
▪	A CrapsMetricsMonitor object
▪	The user’s name
▪	The user’s balance
▪	The user’s bet
▪	The current win streak
▪	The current lose streak
*/

public class CrapsSimulation extends Thread implements Runnable {
	private String threadName;
	private CrapsGame crapsgame= new CrapsGame();
	private CrapsMetricsMonitors crapsMetricsMonitor = new CrapsMetricsMonitors();
	private String userName;
	private double balance;
	private double bet;
	private int currentWinStreak;
	private int currentLoseStreak;
	private PrintWriter pw;
	private static PrintWriter threadpw;
	private int gamecount;
	
	
	
	public CrapsSimulation(String newUserName, double newBalance, double newBet, String name, PrintWriter threadPW) 
	/* CrapsSimiulation constructor.
	Constructor that initializes all fields to default values 
	and constructs any objects used */
	{ 		
		
		userName = newUserName;
		balance = newBalance;
		bet = newBet;
		threadName= name;
		currentWinStreak = 0;
		currentLoseStreak = 0;	
		
		crapsMetricsMonitor = new CrapsMetricsMonitors(balance, bet, currentWinStreak, 
				currentLoseStreak,pw);
		crapsgame = new CrapsGame(crapsMetricsMonitor,pw, threadName);
		threadpw=threadPW;
		
	}
	
	
	//getters an setters 
	
	/*public PrintWriter passPrintWriter()
	{
		return pw;
	}*/
	public void setBalance(double newbalance)
	{
		balance =newbalance;
	}
	
	public double getBalance() 
	{
		return balance;
	}
	
	public void setBet(double newbet)
	{
		bet=newbet;
	}
	
	public double getBet()
	{
		return bet;
	}
	
	public int getWinStreak()
	{
		return currentWinStreak;
	}
	
	public void setWinStreak(int newWinstreak)
	{
		currentWinStreak=newWinstreak;
	}
	
	public int getLoseStreak()
	{
		return currentLoseStreak;
	}
	
	public void setLoseStreak(int newLosestreak)
	{
		currentLoseStreak= newLosestreak;
	}
	
	/*public static void setGameCount()
	{
		gamecount+=CrapsMetricsMonitors.getGameNum();
	}*/
	

	public void run()
	/*Main loop of a single simulation run. 
	 * This is where the user inputs their name, balance, and bet, 
	 * runs the simulation, and continues to do 
	 *  so if the user wants to run it again.
	 */
	{	
		
		try {
			
		Thread.sleep(0);
		threadpw.write(threadName+" starts at "+System.currentTimeMillis()+"\n");
		
		String fileName = "src/"+ threadName + ".txt";
		//File myFile = new File(fileName); 
		try {
			PrintWriter pw = new PrintWriter(fileName);
			
			crapsMetricsMonitor = new CrapsMetricsMonitors(balance, bet, currentWinStreak, 
					currentLoseStreak,pw);
			crapsMetricsMonitor.setBalance(balance);
			crapsMetricsMonitor.setBet(bet);
			
			//start a single game
			while(balance!=0)
			{	Thread.sleep(0);
				pw.write(userName+"'s balance: "+ balance+
					". Playing a new game..."+"\n");
			if (bet>balance)
			{
				bet=balance;
				crapsMetricsMonitor.setBet(bet);
			}
			
			pw.write(userName+" bets $"+ bet+"\n");
			pw.write("Bet is made at "+System.currentTimeMillis()+"\n");
			
			crapsgame = new CrapsGame(crapsMetricsMonitor,pw, threadName);
			boolean winGame =crapsgame.playGame();
			balance=crapsMetricsMonitor.getBalance();
			
			
			gamecount++;
			currentLoseStreak=crapsMetricsMonitor.getWinstreak();
			currentWinStreak=crapsMetricsMonitor.getLosestreak();
			
			}
			//end a single game
			
			pw.write(userName+"'s balance : $"+ balance+"\n");
			crapsMetricsMonitor.printStatistics();
		
				
			pw.close();
			crapsMetricsMonitor.reset();
			
			Thread.sleep(0);
			threadpw.write(threadName+" ends at "+System.currentTimeMillis()+"\n");
	
		}
		
		
		catch(FileNotFoundException e)
		{	System.out.println("123");
			e.printStackTrace();
		}catch(InterruptedException ex)
		{
				Thread.currentThread().interrupt();
		}
	
		
	
		}catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
	}
	

	}
}
	


