package org.tutorial.ehcache.stock.service;

import org.tutorial.ehcache.stock.model.Stock;


public interface StockService {
    Stock getStockCount(Stock stock);
    Stock insert(Stock stock);
}
