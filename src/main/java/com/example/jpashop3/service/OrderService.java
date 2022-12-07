package com.example.jpashop3.service;

import com.example.jpashop3.domain.Delivery;
import com.example.jpashop3.domain.Member;
import com.example.jpashop3.domain.Order;
import com.example.jpashop3.domain.OrderItem;
import com.example.jpashop3.domain.item.Item;
import com.example.jpashop3.repository.ItemRepository;
import com.example.jpashop3.repository.MemberRepository;
import com.example.jpashop3.repository.OrderRepository;
import com.example.jpashop3.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        final Member member = memberRepository.findOne(memberId);
        final Item item = itemRepository.findOne(itemId);

        final Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        final OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        final Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId) {
        final Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
