package org.tutorial.ehcache.stock.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.tutorial.ehcache.stock.model.Stock;

@Repository
@Mapper
public interface StockMapper {

    String SELECT_UUID_TITLE_AUTHOR_PUBLISHER_FROM_STOCK = "SELECT UUID, TITLE, AUTHOR, PUBLISHER, STOCK FROM STOCK";

    @Results(id = "STOCK", value = {
            @Result(property = "uuid", column = "UUID"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "author", column = "AUTHOR"),
            @Result(property = "publisher", column = "PUBLISHER"),
            @Result(property = "stock", column = "STOCK")
    })
    @Select(value = SELECT_UUID_TITLE_AUTHOR_PUBLISHER_FROM_STOCK + "WHERE UUID = #{stock.uuid}")
    Stock getStockCount(@Param("stock") Stock stock);

    @Results(id = "STOCK", value = {
            @Result(property = "uuid", column = "UUID"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "author", column = "AUTHOR"),
            @Result(property = "publisher", column = "PUBLISHER"),
            @Result(property = "stock", column = "STOCK")
    })
    @Insert(value = "INSERT INTO STOCK (UUID, TITLE, AUTHOR, PUBLISHER, STOCK) VALUES (#{stock.uuid}, #{stock.title}, #{stock.author}, #{stock.publisher}, #{stock.stock})")
    Stock insert(@Param("stock") Stock stock);
}
