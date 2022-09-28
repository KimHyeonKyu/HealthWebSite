package com.example.healthwebsite.jdbc;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BuyerInformDTO {
    int bid;
    String goods_name;
    int price;
    Timestamp buy_date;
}
