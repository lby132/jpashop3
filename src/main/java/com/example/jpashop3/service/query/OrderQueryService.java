package com.example.jpashop3.service.query;

import com.example.jpashop3.api.OrderApiController;
import com.example.jpashop3.domain.Order;
import com.example.jpashop3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public List<OrderDto> ordersV3() {
        final List<Order> orders = orderRepository.findAllWithItem();
        final List<OrderDto> collect = orders.stream().map(OrderDto::new).collect(toList());
        return collect;
    }
}
