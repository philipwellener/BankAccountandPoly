import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	
	//Checks whether the String is numeric
	/**
	 * Checks whether the string is number
	 * @param str = number
	 * @return true if string is numeric, else false
	 */
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
				String name = in.nextLine();
				
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
					
					while(!isNumeric(intAmt) || (Double.parseDouble(intAmt)<0))
					{
						System.out.println("Please enter a valid answer: ");
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
					else if(saveorcheck.equals("Checking"))
					{
						accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
					}
				}
				
			}
			
			//Make a Transaction
			BankAccount myAccount = null;
			if(ans.equals("Make"))
			{
				System.out.println("What type of transaction would you like to make?(Options: 'Deposit', 'Withdraw', 'Transfer', 'Information'): ");
				String trans = in.next();
				in.nextLine();

				while((!trans.equals("Deposit")) && (!trans.equals("Withdraw")) && (!trans.equals("Transfer")) && (!trans.equals("Information")))
				{
					System.out.println("Please enter a valid answer:");
					trans = in.next();
					in.nextLine();
				}
				
				String accNum = null;
				boolean restart = false;
				if((trans.equals("Deposit")) || (trans.equals("Withdraw")) || (trans.equals("Transfer")))
				{
					//Get Account Number
					System.out.println("What is your account number?: ");
					accNum = in.next();
					in.nextLine();
					
					while(!isNumeric(accNum))
					{
						System.out.println("Please enter a valid answer: ");
						accNum = in.next();
						in.nextLine();
					}
					
					//Checks whether the Account Number exists
					boolean accExist = false;
					while(!accExist)
					{
					String numorinfo = null;
					
					//Ends loop if account exists
					if(!accExist)
					{
					for(BankAccount a : accounts)
					{
						if(Integer.parseInt(accNum) == a.getAccNum())
						{
							myAccount = a;
							accExist = true;
						}
					}
					}

					
					//Re-prompts if account does not exists
					if(!accExist)
					{
					System.out.println("That account number or name is not associated with an account. Would you like to reenter you account number, get your account info, or restart the program? Type 'Number' or 'Information' or 'Restart'");
					numorinfo = in.next();
					in.nextLine();
					
					while((!numorinfo.equals("Number")) &&  (!numorinfo.equals("Information")) && (!numorinfo.equals("Restart")))
					{
						System.out.println("That is not a valid response. Please type 'Number' or 'Information' or 'Restart'");
						numorinfo = in.next();
						in.nextLine();
					}
					
					if(numorinfo.equals("Number"))
					{
					System.out.println("Please enter an account number that exists:");
					accNum = in.next();
					in.nextLine();
					}
					
					else if(numorinfo.equals("Information"))
					{
						ArrayList<BankAccount> _myAccount = new ArrayList<BankAccount>();
						System.out.println("What is your name?: ");
						String name = in.nextLine();
						for(BankAccount a : accounts)
						{
							if(name.equals(a.getName()))
							{
								_myAccount.add(a);
							}
						}
						
						if(_myAccount.size()>0)
						{
							for(BankAccount a : _myAccount)
							{
								if(a instanceof SavingsAccount)
									System.out.println("Savings Account:\n" + a.toString() + "\n");
								if(a instanceof CheckingAccount)
									System.out.println("Checking Account:\n" + a.toString() + "\n");
							}
						}
					}
					else if(numorinfo.equals("Restart"))
					{
						restart = true;
						accExist = true;
					}
					}
					}
				}
				
				if(restart)
					continue;
				
				//Switch case to handle different transaction types
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
						myAccount.deposit(Double.parseDouble(damt));
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
					System.out.println("How much would you like to withdraw:");
					String wamt = in.next();
					in.nextLine();
					
					while(!isNumeric(wamt))
					{
						System.out.println("Please enter a valid answer: ");
						wamt = in.next();
						in.nextLine();
					}
					
					try
					{
						myAccount.withdraw(Double.parseDouble(wamt));
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Transaction not authorized.");
					}
					break;
				}
				
				//Transfer case
				case("Transfer"):
				{
					System.out.println("What account number would you like to transfer money to: ");
					String otherAccNum = in.next();
					in.nextLine();
					while(!isNumeric(otherAccNum))
					{
						System.out.println("Please enter a valid answer: ");
						otherAccNum = in.next();
						in.nextLine();
					}
					BankAccount otherAccount = null;
					for(BankAccount a : accounts)
					{
						if(Integer.parseInt(otherAccNum) == a.getAccNum())
						{
							otherAccount = a;
						}
					}
					if(otherAccount == null)
					{
						System.out.println("This is not a valid account number.");
					}
					else
					{
						System.out.println("How much would you like to transfer: ");
						String tamt = in.next();
						in.nextLine();
						try
						{
							myAccount.transfer(otherAccount, Double.parseDouble(tamt));
						}
						catch(IllegalArgumentException e)
						{
							System.out.println("Transaction not authorized.");
						}
						break;
					}
				}
				
				//Information case
				case("Information"):
				{
					ArrayList<BankAccount> _myAccount = new ArrayList<BankAccount>();
					System.out.println("What is your name?: ");
					String name = in.nextLine();
					for(BankAccount a : accounts)
					{
						if(name.equals(a.getName()))
						{
							_myAccount.add(a);
						}
					}
					
					if(_myAccount.size()>0)
					{
						for(BankAccount a : _myAccount)
						{
							if(a instanceof SavingsAccount)
								System.out.println("Savings Account:\n" + a.toString() + "\n");
							if(a instanceof CheckingAccount)
								System.out.println("Checking Account:\n" + a.toString() + "\n");
						}
					}
					else
					{
						System.out.println("There are no acccount(s) under that name.\n");
					}
					
				}
				}
			}
			
			//Terminate the program
			if(ans.equals("Terminate"))
			{
				System.out.println("\nThank you for using the Bank Account Program!");
				run = false;
			}
		}

	}

}
