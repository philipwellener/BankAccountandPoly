/**
 * 
 * @author Philip Wellener
 * Comp Sci
 * Period 7
 *
 */
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public void withdraw(double amt)
	{
		if(getBalance()-amt < 0)
			throw new IllegalArgumentException();
		if(getBalance()-amt<MIN_BAL)
		{
			super.withdraw(amt+MIN_BAL_FEE);
			return;
		}
		super.withdraw(amt);
	}
	
	public void transfer(BankAccount other, double amt)
	{
		if ((other.getName()).equals(getName()))
		{
			if(getBalance()>=amt)
			{
				super.transfer(other, amt);
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public void addInterest()
	{
		super.deposit(getBalance()*intRate);
	}
	
	public void endOfMonthUpdate()
	{
		addInterest();
	}

}
