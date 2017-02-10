package com.vzwcorp.pricinglab.loader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;

import com.vzwcorp.pricinglab.loader.config.RacDataSource;
import com.vzwcorp.pricinglab.loader.util.CpfContext;
import com.vzwcorp.pricinglab.loader.util.CpfContextHolder;

public class RacDataSourceTest {
	private RacDataSource racDataSource;
	private DataSource defaultDataSource, auxiliaryDataSource;
	private Connection defaultConnection, auxiliaryConnection;

	@Before
	public void setTargetDataSources() throws SQLException {
		racDataSource = new RacDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		defaultDataSource = mock(DataSource.class);
		auxiliaryDataSource = mock(DataSource.class);
		targetDataSources.put(0, auxiliaryDataSource);
		targetDataSources.put(1, defaultDataSource);
		racDataSource.setDefaultTargetDataSource(targetDataSources.get(1));
		racDataSource.setTargetDataSources(targetDataSources);
		racDataSource.afterPropertiesSet();

		defaultConnection = mock(Connection.class);
		auxiliaryConnection = mock(Connection.class);

		doReturn(defaultConnection).when(defaultDataSource).getConnection();
		doReturn(auxiliaryConnection).when(auxiliaryDataSource).getConnection();
	}

	@Test
	public void dataSourceFromNoneCpfContext() throws SQLException {
		CpfContextHolder.clearCpfContext();
		assertEquals("No context return first DataSource", racDataSource.getConnection(), auxiliaryConnection);
	}

	@Test
	public void dataSourceFromEmptyCpfContext() throws SQLException {
		CpfContextHolder.clearCpfContext();
		CpfContextHolder.setCpfContext(new CpfContext());
		assertEquals("Empty context return first DataSource", racDataSource.getConnection(), auxiliaryConnection);
	}

	@Test
	public void dataSourceFromCpfContext() throws SQLException {
		CpfContextHolder.clearCpfContext();
		CpfContext cpfContext = new CpfContext();
		cpfContext.setCustomerId(4);
		racDataSource.setNumberOfNodes(3);
		CpfContextHolder.setCpfContext(cpfContext);
		assertEquals("Customer 4 return second DataSource", racDataSource.getConnection(), defaultConnection);
	}
}
