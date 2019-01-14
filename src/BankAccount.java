/**
 * 
 * @author Philip Wellener
 * Comp Sci
 * Period 7
 *
 */
public abstract class BankAccount 
{
	/**
	 * fields
	 */
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	/**
	 * constructor without starting balance
	 * @param n = name
	 */
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = 0;
	}
	
	/**
	 * constructor with starting balance
	 * @param n = name
	 * @param b = balance
	 */
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum ++;
		balance = b;
	}
	
	/**
	 * deposits amount
	 * @param amt = amount
	 * void
	 */
	public void deposit(double amt)
	{
		if(amt>=0)
			balance += amt;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * withdraws amount
	 * @param amt = amount
	 * void
	 */
	public void withdraw(double amt)
	{
		if(amt>=0)
			balance -= amt;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * gets name of account
	 * @return name of account
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * gets balance
	 * @return balance
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * abstract
	 * end of month update
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfers amount to other account from this account
	 * @param other = other account
	 * @param amt = amount
	 * void
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (getBalance()>=amt)
		{
			other.deposit(amt);
			this.withdraw(amt);
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * returns account information
	 * @return account number, name, balance
	 */
	public String toString()
	{
		return "Account Number: " + accNum + "\tName:" + name + "\tBalance: $" + balance;
	}
	
	/**
	 * returns this account number
	 * @return account number
	 */
	public int getAccNum()
	{
		return accNum;
	}
}
