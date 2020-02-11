package org.tutorial.ehcache.stock.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutorial.ehcache.stock.domain.mapper.StockMapper;
import org.tutorial.ehcache.stock.domain.model.Stock;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Stock getStockCount(Stock stock) {
        return stockMapper.getStockCount(stock);
    }

    @Override
    public Stock insert(Stock stock) { return stockMapper.insert(stock); }
}
