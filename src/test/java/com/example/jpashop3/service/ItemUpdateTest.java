package com.example.jpashop3.service;

import com.example.jpashop3.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    void updateTest() throws Exception {
        final Book book = em.find(Book.class, 1L);

        book.setName("dfsdds");


    }
}
