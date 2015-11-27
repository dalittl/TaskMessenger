package com.acnc.mm.dao.db.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Base class for leveraging Spring JDBC
 * @author Kunta L.
 *
 */


public  class BaseSpringJdbcDAO {

	
	private DataSource threatDS;
	


	private PlatformTransactionManager transactionManager;
	
	


	/**
	 * Returns a Transaction Status to be used in transaction
	 * @return <code>TransactionStatus</code> for demarcated transactions
	 */
	protected TransactionStatus createTransactionDefinition (){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("newTransaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		if (transactionManager != null)
			return transactionManager.getTransaction(def);
		else
			throw new JDBCException("transactionManager is null, it was not properly initialized.");
		
	}


	/**
	 * @return the threatDS
	 */

	
	public DataSource getThreatDS() {
		return threatDS;
	}


	/**
	 * @param threatDS the threatDS to set
	 */
	public void setThreatDS(DataSource threatDS) {
		this.threatDS = threatDS;
	}


	/**
	 * @return the transactionManager
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}


	/**
	 * @param transactionManager the transactionManager to set
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
/*	*//**
	 * 
	 * @return <code>SimpleJdbcTemplate</code> associated with <code>threat data source</code>
	 *//*
	protected JdbcTemplate getJdbcTemplate (){
		return new JdbcTemplate (getThreatDS());
	}*/
	
	/**
	 * 
	 * @return <code>JdbcTemplate</code> associated with <code>threat data source</code>
	 */
	
	protected JdbcTemplate getJdbcTemplate (){
		return new JdbcTemplate (getThreatDS());
	}
	
     
	
}
