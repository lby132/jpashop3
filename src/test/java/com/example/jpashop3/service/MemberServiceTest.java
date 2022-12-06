package com.example.jpashop3.service;

import com.example.jpashop3.domain.Member;
import com.example.jpashop3.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        final Member member = new Member();
        member.setName("김");

        final Long savedId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void 중복회원예외() throws Exception {
        final Member member1 = new Member();
        member1.setName("kim");
        final Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        final IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }
}