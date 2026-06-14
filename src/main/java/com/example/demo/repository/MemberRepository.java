package com.example.demo.repository;

import com.example.demo.model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final List<Member> members;

    private long sequence = 4L;

    public MemberRepository() {
        members = new ArrayList<>();
        members.add(new Member(0L, "황명하", "myeongha@example.com"));
        members.add(new Member(1L, "홍길동", "gildong@example.com"));
        members.add(new Member(2L, "이순신", "sunshin@example.com"));
        members.add(new Member(3L, "김철수", "chulsoo@example.com"));
    }

    public List<Member> findAll() {
        return members;
    }

    public Optional<Member> findById(Long id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<Member> findByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Member save(Member member) {
        if (member.getId() == null || member.getId() == 0L) {
            member.setId(sequence++);
        } else {
            deleteById(member.getId());
        }
        members.add(member);
        return member;
    }

    public void deleteById(Long id) {
        members.removeIf(member -> member.getId().equals(id));
    }
}