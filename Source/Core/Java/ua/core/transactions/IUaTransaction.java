package ua.core.transactions;


public interface IUaTransaction {

	public void begin ();
	public void commit ();
	public void forceCommit ();
	public void rollback ();
}
