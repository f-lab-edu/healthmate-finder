package com.hamryt.helparty.config;

import com.hamryt.helparty.util.DatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        
        return isReadOnly ? DatabaseType.SLAVE : DatabaseType.MASTER;
    }
}
