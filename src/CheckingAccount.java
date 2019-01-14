/**
 * 
 * @author Philip Wellener
 * Comp Sci
 * Period 7
 *
 */
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	
	private int numTransactions = 0;
	
	/**
	 * 
	 * @param n = name
	 * @param b = balance
	 * @param odf = over draft fee
	 * @param tf = transaction fee
	 * @param freeTrans = free transaction
	 * constructor with initial balance
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * 
	 * @param n = name
	 * @param odf = over draft fee
	 * @param tf = transaction fee
	 * @param freeTrans = free transaction
	 * constructor without initial balance
	 */
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * @param amt = amount
	 * deposits amount
	 * void
	 */
	public void deposit(double amt)
	{
		if(amt <= 0)
			throw new IllegalArgumentException();
		numTransactions ++;
		if(numTransactions>FREE_TRANS)
			amt -= TRANSACTION_FEE;
		super.deposit(amt);
	}
	
	/**
	 * @param amt = amount
	 * withdraws amount
	 * void
	 */
	public void withdraw(double amt)
	{
		if(getBalance() < 0 || amt<=0)
			throw new IllegalArgumentException();
		else if(getBalance() >= 0)
		{
			super.withdraw(amt);
			if(numTransactions >= FREE_TRANS)
				super.withdraw(TRANSACTION_FEE);
			if(getBalance()<0)
				super.withdraw(OVER_DRAFT_FEE);
			numTransactions++;
		}
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
				numTransactions++;
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * resets number of transactions
	 * void
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
}
