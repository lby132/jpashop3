package com.example.jpashop3.service;

import com.example.jpashop3.domain.Address;
import com.example.jpashop3.domain.Member;
import com.example.jpashop3.domain.Order;
import com.example.jpashop3.domain.OrderStatus;
import com.example.jpashop3.domain.item.Book;
import com.example.jpashop3.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception {

        final Member member = createMember();

        final Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        final Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        final Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity());

    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        final Member member = createMember();
        final Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        orderService.order(member.getId(), book.getId(), orderCount);


    }

    @Test
    void 주문취소() throws Exception {

    }


    private Book createBook(String name, int price, int stockQuantity) {
        final Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        final Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}