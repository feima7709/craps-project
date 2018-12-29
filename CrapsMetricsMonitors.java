package hw4;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* Name: Fei Ma
 * Student ID: 86911681
 * CrapsMetricsMonitors.java: This file contains all the statistics of the entire 
 * simulation. It contains getters, setters and some functions to modify the
 * statistics. Eventually it prints a summary.
 */

public class CrapsMetricsMonitors {
	/*o	A class representing all of the statistics
	 *  gathered during a single simulation*/
	
	/*▪	Any methods you need to update / increment / decrement the class fields.
These should be called throughout your simulation.
*/
	private double curBalance;
	private double maxBalance;
	private int maxBalanceRollNum;
	
	private double curBet;
	private double totalBet;
	
	private int gameNum; //number of games played.
	
	private int totalLoseNum;
	private int totalWinNum;
	
	private int curWinstreak;
	private int curLosestreak;
	private int maxWinstreak;
	private int maxLosestreak;

	private int maxRollnum;
	
	private int naturalNum;
	private int crapsNum;
	
	private PrintWriter pw;
	
	
	
	
	public CrapsMetricsMonitors(){
		;//empty construcotr
	}
	
	public CrapsMetricsMonitors(double balance, double bet, int winstreak, int losestreak, PrintWriter newpw)
	{
		curBalance = balance;
		maxBalance =balance;
		maxBalanceRollNum = 0 ;
		curBet= bet;
		totalBet =bet;
		gameNum =0;
		totalLoseNum = 0;
		totalWinNum =0;
		curWinstreak = winstreak;
		maxWinstreak =winstreak;
		curLosestreak =losestreak;
		maxLosestreak = losestreak;
	
		maxRollnum =0;
		naturalNum =0;
		crapsNum = 0;
		
		pw= newpw;
		
	}
	
	
	public void increaseGameNum()
	//keep track of the game number
	{
		gameNum++;
	}
	
	public void increaseWinNum()
	//increase Winning number by 1 if win.
	{
		totalWinNum++;
	}
	
	public void increaseLoseNum()
	//increase lose number by 1 if lose.
	{
		totalLoseNum++;
	}
	
	
	
	public void updateMaxRollnum(int roll)
	//update max roll number if its larger than the max number before.
	{
		if (roll>maxRollnum)
			maxRollnum=roll;
		
	}
	
	public void increaseNaturalCount()
	//increase natural roll count.
	{
		naturalNum++;
	}
	
	public void increaseCrapsCount()
	//increase Craps roll count.
	{
		crapsNum++;
	}
	public void updateWinstreak() 
	// increase current win streak and update maxmium win streak.
	{
		curWinstreak++;
		if (curWinstreak > maxWinstreak )
			maxWinstreak= curWinstreak;
	}
	
	public void clearWinstreak()
	//clear the win streak to 0
	{
		curWinstreak = 0;
	}
	//getter and setter for current win streak and current lose streak
	
	public int getWinstreak()
	{
		return curWinstreak;
	}
	
	public void setWinstreak(int winstreak)
	{
		curWinstreak= winstreak;
	}
	
	public int getLosestreak()
	{
		return curLosestreak;
	}
	public void setLosestreak(int losestreak)
	{
		curLosestreak = losestreak;
	}
	
	
	public void updateLosestreak()
	// increase current lose streak and update maxmium lose streak.
	{
		curLosestreak++;
		if (curLosestreak > maxLosestreak)
			maxLosestreak = curLosestreak;
	
	}
	public int getGameNum()
	{
		return gameNum;
	}
	public void clearLosestreak()
	//clear the lose streak to 0
	{
		curLosestreak=0;
	}
	
	public double getBalance()
	//return current balance
	{
		return curBalance;
	}
	
	public void setBalance(double balance)
	//setter for balance.
	{
		curBalance =balance;
	}
	public void increaseBalance()
	// increase balance if won
	{
		curBalance += curBet;
		if (curBalance>maxBalance)
		{
			maxBalance= curBalance;
			maxBalanceRollNum = gameNum;
		}
	}
	
	public void decreaseBalance()
	//decrease balance if lose
	{
		curBalance -= curBet;
	}
	public double getBet()
	// return current bet.
	{
		return curBet;
	}
	
	public void setBet(double bet)
	//setter for bet
	{
		curBet= bet;
		totalBet += bet;
	}
	public void  printStatistics() {
	// Prints all of the statistics for the simulation 
		//String fileName = "src/"+ threadName + ".txt";
		try {
			
			//PrintWriter pw = new PrintWriter(fileName);
			Thread.sleep(0);
			pw.write(String.format(
			"*****************************\n" + 
			"*** SIMULATION STATISTICS ***\n" + 
			"******************************\n"+
			"Games played: %d\n"+
			"Games won: %d\n"+
			"Games lost: %d\n"+
			"Maximum Rolls in a single game: %d\n"+
			"Natural Count: %d\n"+
			"Craps Count: %d\n"+
			"Maximum Winning Streak: %d\n"+
			"Maximum Loosing Streak: %d\n"+
			"Maximum balance: $%.2f \n"+
			"during game %d\n\n",gameNum,totalWinNum,
			totalLoseNum,maxRollnum,naturalNum,
			crapsNum,maxWinstreak,maxLosestreak,maxBalance,
			maxBalanceRollNum));
	
		}catch(InterruptedException ex)
		{
				Thread.currentThread().interrupt();
		}
	}
	public void reset()
	/*A method to reset the state of the CrapsMetricsMonitor object. 
	 * This will be called if the user wants to 
	 * start a new simulation after one was just completed.
	 */
	{
		curBalance = 0.0;
		maxBalance =0.0;
		maxBalanceRollNum = 0 ;
		curBet= 0.0;
		totalBet =0.0;
		gameNum =0;
		totalLoseNum = 0;
		totalWinNum =0;
		curWinstreak = 0;
		maxWinstreak =0;
		curLosestreak =0;
		maxLosestreak = 0;
		maxRollnum =0;
		naturalNum =0;
		crapsNum = 0;
	}

}
