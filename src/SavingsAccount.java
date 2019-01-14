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
	
	/**
	 * 
	 * @param n = name
	 * @param b = balance
	 * @param r = interest rate
	 * @param mb = minimum balance
	 * @param mbf = minimum balance fee
	 * constructor with initial balance
	 */
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * 
	 * @param n = name
	 * @param r = interest rate
	 * @param mb = minimum balance
	 * @param mbf = minimum balance fee
	 * constructor without initial balance
	 */
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * @param amt = amount
	 * withdraws amount
	 * void
	 */
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
	
	/**
	 * @param other = other account
	 * @param amt = amount
	 * transfers amount to other account
	 * void
	 */
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
	
	/**
	 * adds interest
	 * void
	 */
	public void addInterest()
	{
		super.deposit(getBalance()*intRate);
	}
	
	/**
	 * adds interest at end of month
	 * void
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}

}
