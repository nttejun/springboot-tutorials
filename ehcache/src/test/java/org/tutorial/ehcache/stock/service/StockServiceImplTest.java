package org.tutorial.ehcache.stock.service;

import org.junit.Assert;
import org.junit.Test;
import org.tutorial.ehcache.stock.model.Stock;

public class StockServiceImplTest {
    @Test
    public void getStockCount(){
        Stock stock = new Stock("stock2020021101", "우동 한 그릇", "구리 료헤이", "청조사");
        StockService stockService = new StockServiceImpl();
        Assert.assertEquals("10", stockService.getStockCount(stock));
    }

    @Test
    public void insertManyStock(){
        StockServiceImpl stockService = new StockServiceImpl();
        for(int i=1; i<500; i++){
        Stock stock = new Stock("20200211"+i, i+"번 제목", i+"번 저자", i+"번 출판사", i);
            stockService.insert(stock);
        }
    }

    @Test
    public void insertOneStock(){
        Stock stock;
        StockServiceImpl stockService = new StockServiceImpl();
            stock = new Stock("2020021100", "번 제목", "번 저자", "번 출판사", 0);
            stockService.insert(stock);
    }
}
