/**
 * 
 */
package it.sdeluca.activemqembedded.config;

import org.apache.activemq.store.jdbc.Statements;
import org.apache.activemq.store.jdbc.adapter.PostgresqlJDBCAdapter;

/**
 * @author B.Conetta
 * Classe utilizzata per l'integrazione di Apache ActiveMQ con postgres.
 * Risolve i problemi che si hanno utilizzando direttamente PostgresqlJDBCAdapter 
 */
public class CustomPostgresqlJDBCAdapter extends PostgresqlJDBCAdapter {
	
    protected String messageTableName = "activemq_msgs";
    protected String durableSubAcksTableName = "activemq_acks";
    protected String lockTableName = "activemq_lock";

    @Override
    public void setStatements(Statements statements) {
    	super.setStatements(statements);
        super.getStatements().setMessageTableName(messageTableName);
        super.getStatements().setDurableSubAcksTableName(durableSubAcksTableName);
        super.getStatements().setLockTableName(lockTableName);
    }
}
