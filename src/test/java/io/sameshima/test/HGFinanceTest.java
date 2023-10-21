package io.sameshima.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import io.sameshima.hgbrasil.HGFinance;
import io.sameshima.hgbrasil.service.api.CacheConfig;

class HGFinanceTest {

	@Test
	void testInitializationWithAPIKey() {
		HGFinance hgFinance = new HGFinance("");

		assertNotNull(hgFinance.getTickersServices());
		assertNotNull(hgFinance.getTaxesServices());
		assertNotNull(hgFinance.getStockPricesServices());
		assertNotNull(hgFinance.getStockDividendsServices());
	}

	@Test
	void testInitializationWithAPIKeyAndCacheConfig() {
		CacheConfig mockedCacheConfig = mock(CacheConfig.class);

		HGFinance hgFinance = new HGFinance("", mockedCacheConfig);

		assertNotNull(hgFinance.getTickersServices());
		assertNotNull(hgFinance.getTaxesServices());
		assertNotNull(hgFinance.getStockPricesServices());
		assertNotNull(hgFinance.getStockDividendsServices());
	}
}