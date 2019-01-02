import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	
	private static boolean isNumeric(String str)
	{
	try
	{
	Double.parseDouble(str);
	return true;
	}
	catch(IllegalArgumentException e)
		{
			return false;
		}
	}

	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		boolean run = true;
		while(run)
		{
			System.out.println("Would you like to add an account, make a transaction, or terminate the program? Write: 'Add', 'Make', or 'Terminate'.");
			String ans = in.nextLine();
			
			System.out.println("What name would you like to put this account under:");
			String name = in.next();
			in.nextLine();
			
			while((!ans.equals("Add")) && (!ans.equals("Make")) && (!ans.equals("Terminate")))
			{
				System.out.println("Please enter a valid answer:");
				ans = in.next();
				in.nextLine();
			}
			
			if(ans.equals("Add"))
			{
				System.out.println("Do you want to add a Savings or Checking Account? Write: 'Savings', or 'Checking'");
				String saveorcheck = in.next();
				in.nextLine();
				while((!saveorcheck.equals("Savings")) && (!saveorcheck.equals("Checking")))
				{
					System.out.println("Please enter a valid answer:");
					saveorcheck = in.next();
					in.nextLine();
				}
				
				System.out.println("Would you like to make an initial deposit('Yes' or 'No'):");
				String yorn = in.next();
				in.nextLine();
				while((!yorn.equals("Yes")) && (!yorn.equals("No")))
				{
					System.out.println("Please enter a valid answer:");
					yorn = in.next();
					in.nextLine();
				}
				if(yorn.equals("Yes"))
				{
					System.out.println("What would you like to initially deposit");
					//Might need to fix this while loop
					while(!isNumeric(in.next()) || in.nextDouble() < 0)
					{
						System.out.println("Please enter a valid answer");
					}
					double intAmt = in.nextDouble();
					
					if(saveorcheck.equals("Savings"))
					{
						accounts.add(new SavingsAccount(name, intAmt, RATE, MIN_BAL, MIN_BAL_FEE));
					}
					
					else if(saveorcheck.equals("Checking"))
					{
						accounts.add(new CheckingAccount(name, intAmt, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
						
				}
				
				else if(yorn.equals("No"))
				{
					if(saveorcheck.equals("Savings"))
					{
						accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
					}
					if(saveorcheck.equals("Checking"))
					{
						accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
				}
				
			}
			
			if(ans.equals("Make"))
			{
				System.out.println("What type of transaction would you like to make?: ");
				String trans = in.next();
				in.nextLine();
				while((!trans.equals("Deposit")) && (!trans.equals("Withdraw"))&& (!trans.equals("Transfer"))&& (!trans.equals("Get")))
				{
					System.out.println("Please enter a valid answer:");
					trans = in.next();
					in.nextLine();
				}
				switch(trans) 
				{
				case("Deposit"):
				{
					System.out.println("How much would you like to depsoit:");
				}
				case("Withdraw"):
				{
					
				}
				case("Transfer"):
				{
					
				}
				case("Get"):
				{
					
				}
				}
			}
			
			if(ans.equals("Terminate"))
				run = false;
		}

	}

}

//Switchcase
//Switch(transcaction)
//case(withdraw)
//case(deposit)
