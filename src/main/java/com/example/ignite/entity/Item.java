package com.example.ignite.entity;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
public class Item implements Serializable {

    private static final long serialVersionUID = -2974414641088735500L;

    @QuerySqlField(index = true)
    private String itemCode;

    @QuerySqlField
    private String areaCode;

    @QuerySqlField
    private String zoneCode;

    private Long demand;

    @QuerySqlField
    private Long inventory;

    @QuerySqlField
    private Double doi;

    @QuerySqlField
    private Long qtyToReachDoi;
}
