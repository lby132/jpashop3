package com.example.jpashop3;

import com.example.jpashop3.domain.*;
import com.example.jpashop3.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            final Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            final Book book1 = createBook("JPA1 book1", 10000);
            em.persist(book1);

            final Book book2 = createBook("JPA2 book1", 20000);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            final Delivery delivery = createDelivery(member);

            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2 () {
            final Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            final Book book1 = createBook("SPRING1 book1", 10000);
            em.persist(book1);

            final Book book2 = createBook("SPRING2 book1", 20000);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            final Delivery delivery = createDelivery(member);

            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            final Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Delivery createDelivery(Member member) {
            final Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String JPA1_book1, int price) {
            final Book book1 = new Book();
            book1.setName(JPA1_book1);
            book1.setPrice(price);
            book1.setStockQuantity(100);
            return book1;
        }

    }
}
