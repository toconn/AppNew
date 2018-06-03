package ua.core.transactions;

/**
 * Wrapper class for a transaction class. This wrapper class
 * tracks the number of begins and commits calls and will only call
 * commit after the # commits = # of begins.
 * 
 * If a rollback is called anywhere, rollback will be called instead 
 * of commit on the final commit.
 * 
 * @author Tadhg
 *
 */
public class UaTransactionWrapper implements IUaTransaction {
	
	private IUaTransaction	transaction	= null;

	private	int				transCount		= 0;
	private boolean			transRolledBack	= false;
	
	
	
	public UaTransactionWrapper (IUaTransaction transaction) {
		
		this.transaction = transaction;
	}
	

	
	public void begin() {
		
		if (this.transCount == 0) {
			
			// Start the da transaction if first time here.
			
			this.transaction.begin();
			this.transRolledBack	= false;
		}
		
		
		// Increment transaction counter....
		
		this.transCount++;
	}

	

	public void commit() {

		if (this.transCount > 0)
			
			// If the count is greater than 1, decrement the counter.
			
			this.transCount--;
		
		
		if (this.transCount == 0)
			
			// If the counter is 0, commit the transaction.
			
			doCommit();
	}
	
	
	
	/**
	 * This method calls the actual commit statement or rollback if 
	 * rollback was called.
	 * 
	 */
	private void doCommit(){
		
		if (! this.transRolledBack)
			
			this.transaction.commit();
		
		else
			
			this.transaction.rollback();
		
		
		// Reset transaction defaults.
		
		this.transCount			= 0;
		this.transRolledBack	= false;
	}


	
	public void forceCommit() {

		doCommit();
	}


	
	public void rollback() {
		
		this.transRolledBack = true;

		commit();
	}
}
