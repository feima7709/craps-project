package hw4;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* Name: Fei Ma
 * Student ID: 86911681
 * Labs4.java: This file contains the main method. It asks user for userName, balance, bet and construct Thread objects
 * and calls .start() on it.
 */

public class Lab4 {
	
	public static void main(String[] args) {
		
		String userName;
		double balance;
		double bet;
		
		
		boolean continueGame=true;
		
		while(continueGame==true) {
		//****************Get username, balance, bet **************************
		Scanner s= new Scanner(System.in);
		System.out.println("Welcome to SimCraps! Enter your user name: ");
		userName = s.nextLine();
		
		
		while (userName.matches("\\s*"))
		{
			System.out.println("Invalid user name! must include at least 1 non-space character");
			userName = s.nextLine();
		}
		
	
		System.out.println("Hello "+userName+"!\n");
		System.out.println("Enter the amount of money you will bring to the table: ");
		balance =s.nextDouble();
		
		System.out.println(String.format("Enter the bet amount between $1 and $%.2f:",balance) );
		bet = s.nextDouble();
		
		while (bet>balance || bet <1) {
			System.out.println(String.format("Invalid bet! Please enter a bet between $1 and $%.2f:",balance));
			bet = s.nextDouble();
		}
		
		//***********************Start 5 multi-thread*****************
		//CrapsSimulation newgame = new CrapsSimulation ();
		//newgame.run();
		String fileName = "src/"+ "console.txt";
		try {
		PrintWriter threadpw = new PrintWriter(fileName);	
		
		Thread t1= new CrapsSimulation(userName, balance, bet,"Thread-1",threadpw);
		System.out.println("Thread1 starts at "+System.currentTimeMillis());
		t1.start();
		System.out.println("Thread1 ends at "+System.currentTimeMillis());
		
		
		Thread t2= new Thread(new CrapsSimulation(userName, balance, bet,"Thread-2",threadpw));
		System.out.println("Thread2 starts at "+System.currentTimeMillis());
		t2.start();
		System.out.println("Thread2 ends at "+System.currentTimeMillis());
		Thread t3= new Thread(new CrapsSimulation(userName, balance, bet,"Thread-3",threadpw));
		System.out.println("Thread3 starts at "+System.currentTimeMillis());
		t3.start();
		System.out.println("Thread3 ends at "+System.currentTimeMillis());
		Thread t4= new Thread(new CrapsSimulation(userName, balance, bet,"Thread-4",threadpw));
		System.out.println("Thread4 starts at "+System.currentTimeMillis());
		t4.start();
		System.out.println("Thread4 ends at "+System.currentTimeMillis());
		Thread t5= new Thread(new CrapsSimulation(userName, balance, bet,"Thread-5",threadpw));
		System.out.println("Thread5 starts at "+System.currentTimeMillis());
		t5.start();
		System.out.println("Thread5 ends at "+System.currentTimeMillis());
		threadpw.close();
		}
		catch(FileNotFoundException e)
		{	
			e.printStackTrace();
		}
	
		//***************After Game****************
		System.out.println("Replay? Enter 'y' or 'n': ");

		String n = s.next();
		
		while(!n.equals("n") && !n.equals("y"))
		{
			System.out.println("Replay? Enter 'y' or 'n': ");
			n = s.next();	
		}
		if (n.equals("y"))
			continueGame= true;
		if(n.equals("n"))
			continueGame=false;
		}
		
	}

}
