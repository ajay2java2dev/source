package com.vzwcorp.pricinglab.loader.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.vzwcorp.pricinglab.loader.util.CpfContext;
import com.vzwcorp.pricinglab.loader.util.CpfContextHolder;

public class RacDataSource extends AbstractRoutingDataSource {
	int numberOfNodes = 1;

	@Override
	protected Object determineCurrentLookupKey() {
		CpfContext cpfContext = (CpfContext) CpfContextHolder.getCpfContext();

		// no RequestContext, get default DataSource
		if (null == cpfContext) {
			return 0;
		}

		Integer key = cpfContext.getUbsrDataSourceKey();
		if (key == -1) {

			// calculate DataSource key by modulus
			if (numberOfNodes == 1) {
				key = 0;
			} else {
				key = (int) cpfContext.getCustomerId() % numberOfNodes;
			}
			cpfContext.setUbsrDataSourceKey(key);
		}

		return key;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

}
