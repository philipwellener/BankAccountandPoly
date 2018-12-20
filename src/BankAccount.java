/**
 * 
 * @author Philip Wellener
 * Comp Sci
 * Period 7
 *
 */
public abstract class BankAccount 
{
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum + 1;
		balance = 0;
	}
	
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum + 1;
		balance = b;
	}
	
	public void deposit(double amt)
	{
		if(amt>=0)
			balance += amt;
		else
			throw new IllegalArgumentException();
	}
	
	public void withdraw(double amt)
	{
		if(amt>=0)
			balance -= amt;
		else
			throw new IllegalArgumentException();
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public abstract void endOfMonthUpdate();
	
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
	
	public String toString()
	{
		return accNum + "\t" + name + "\t$" + balance;
	}
}
