package com.tutorials.junit5.service;

import com.tutorials.junit5.domain.Member;
import com.tutorials.junit5.domain.Study;
import java.util.Optional;

public interface MemberService {

  Optional<Member> findById(Long memberId);

  void validate(Long memberId);

  void notify(Study newstudy);

  void notify(Member member);
}
