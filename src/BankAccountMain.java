import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final double FREE_TRANSACTIONS = 10;
		
		boolean run = true;
		while(run)
		{
			System.out.println("Would you like to add an account, make an account, or terminate the program? Write: 'Add', 'Make', or 'Terminate'.");
			String ans = in.nextLine();
			while((!ans.equals("Add")) && (!ans.equals("Make")) && (!ans.equals("Terminate")))
			{
				System.out.println("Please enter a valid answer:");
				ans = in.next();
				in.nextLine();
			}
			if(ans.equals("Add"))
			{
				System.out.println("Do you want to add a Savings or Checking Account?");
				
				
				
				
			}
			
			
			
			if(ans.equals("Make"))
			{
				System.out.println("Do you want to make a Savings or Checking Account?");
				
				
				
				
			}
			
			if(ans.equals("Terminate"))
				run = false;
		}

	}

}
