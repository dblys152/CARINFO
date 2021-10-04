package com.ys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class MyBatisTransactionManager extends DefaultTransactionDefinition {
	private static final long serialVersionUID = -1375151959664915520L;
	
	@Autowired PlatformTransactionManager transactionManager;
	
	TransactionStatus status;
	
	public void start() throws TransactionException {
		status = transactionManager.getTransaction(this); 
	}
	
	public void commit() throws TransactionException {
		if (!status.isCompleted()) {
			transactionManager.commit(status);
		}
	}
	 
	public void rollback() throws TransactionException {
		if (!status.isCompleted()) {
			transactionManager.rollback(status);
		}
	}
	
	public void end() throws TransactionException {
		rollback();
	}
}
