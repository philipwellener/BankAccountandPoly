import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	
	//Checks whether the String is numeric
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
		
		//Constants
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
			String ans = in.next();
			in.nextLine();
			
			while((!ans.equals("Add")) && (!ans.equals("Make")) && (!ans.equals("Terminate")))
			{
				System.out.println("Please enter a valid answer:");
				ans = in.next();
				in.nextLine();
			}
			
			//Add an Account
			if(ans.equals("Add"))
			{
				System.out.println("What name would you like to put this account under:");
				String name = in.next();
				in.nextLine();
				
				System.out.println("Do you want to add a Savings or Checking Account? Write: 'Savings', or 'Checking'");
				String saveorcheck = in.next();
				in.nextLine();
				while((!saveorcheck.equals("Savings")) && (!saveorcheck.equals("Checking")))
				{
					System.out.println("Please enter a valid answer:");
					saveorcheck = in.next();
					in.nextLine();
				}
				
				//With or Without Initial Deposit
				System.out.println("Would you like to make an initial deposit('Yes' or 'No'):");
				String yorn = in.next();
				in.nextLine();
				while((!yorn.equals("Yes")) && (!yorn.equals("No")))
				{
					System.out.println("Please enter a valid answer:");
					yorn = in.next();
					in.nextLine();
				}
				
				//Initial Deposit
				if(yorn.equals("Yes"))
				{
					System.out.println("What would you like to initially deposit: ");
					String intAmt = in.next();
					in.nextLine();
					
					while(!isNumeric(intAmt))
					{
						System.out.println("Please enter a valid answer");
						intAmt = in.next();
						in.nextLine();
					}

					
					if(saveorcheck.equals("Savings"))
					{
						accounts.add(new SavingsAccount(name, Double.parseDouble(intAmt), RATE, MIN_BAL, MIN_BAL_FEE));
					}
					
					else if(saveorcheck.equals("Checking"))
					{
						accounts.add(new CheckingAccount(name, Double.parseDouble(intAmt), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
						
				}
				
				//Without Initial Deposit
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
			
			//Make a Transaction
			if(ans.equals("Make"))
			{
				System.out.println("What type of transaction would you like to make?(Options: 'Deposit', 'Withdraw', 'Transfer', 'Account'): ");
				String trans = in.next();
				in.nextLine();

				while((!trans.equals("Deposit")) && (!trans.equals("Withdraw")) && (!trans.equals("Transfer")) && (!trans.equals("Account")))
				{
					System.out.println("Please enter a valid answer:");
					trans = in.next();
					in.nextLine();
				}
				
				if((trans.equals("Deposit")) && (trans.equals("Withdraw")) && (trans.equals("Transfer")))
				{
					//Get Account Number
					System.out.println("What is your account number?: ");
					String accNum = in.next();
					in.nextLine();
					
					while(!isNumeric(accNum))
					{
						System.out.println("Please enter a valid answer: ");
						accNum = in.next();
						in.nextLine();
					}
					
					//Checks whether the Account Number exisits
					boolean accExist = false;
					BankAccount myAccount = null;
					while(accExist)
					{
					for(BankAccount a : accounts)
					{
						if(Integer.parseInt(accNum) == a.getAccNum())
						{
							myAccount = a;
							accExist = true;
						}
					}
					System.out.println("Please enter an Account Number that exists:");
					accNum = in.next();
					in.nextLine();
					}
				}
				
				//Switch case of different transaction types
				switch(trans) 
				{
				
				//Deposit case
				case("Deposit"):
				{
					System.out.println("How much would you like to depsoit:");
					String damt = in.next();
					in.nextLine();
					
					while(!isNumeric(damt))
					{
						System.out.println("Please enter a valid answer: ");
						damt = in.next();
						in.nextLine();
					}
					
					try
					{
						//Parsing
						//Need to fix finding account
						accounts.get(Integer.parseInt(accNum)).deposit(Double.parseDouble(damt));
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Transaction not authorized.");
					}
					break;
				}
				
				//Withdraw case
				case("Withdraw"):
				{
					
				}
				
				//Transfer case
				case("Transfer"):
				{
					
				}
				
				//Account case
				case("Account"):
				{
					
				}
				}
			}
			
			//Terminate the program
			if(ans.equals("Terminate"))
			{
				System.out.println("Thank you for using the Bank Account Program!");
				run = false;
			}
		}

	}

}

//Switchcase
//Switch(transcaction)
//case(withdraw)
//case(deposit)

//Try Method
//Catch Error

//Is Numeric

//int.parseInt

//double

//Instance Of
