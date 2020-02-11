package org.tutorial.ehcache.stock.application;

import org.tutorial.ehcache.stock.domain.model.Stock;


public interface StockService {
    Stock getStockCount(Stock stock);
    Stock insert(Stock stock);
}
