package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public MemberService(MemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        Optional<Member> emailOwner = memberRepository.findByEmail(updatedMember.getEmail());
        if (emailOwner.isPresent() && !emailOwner.get().getId().equals(id)) {
            throw new IllegalStateException("이미 존재하는 이메일로 수정할 수 없습니다.");
        }

        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setModifiedDate(LocalDateTime.now());

        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        if (articleRepository.existsByAuthorId(id)) {
            throw new IllegalArgumentException("작성한 게시물이 존재하는 회원은 삭제할 수 없습니다.");
        }

        memberRepository.deleteById(id);
    }
}