package com.example.jpashop3.repository;

import com.example.jpashop3.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
