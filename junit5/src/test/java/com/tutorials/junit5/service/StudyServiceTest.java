package com.tutorials.junit5.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tutorials.junit5.repository.StudyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/***
 * MockExtention : ExtendWith로 Mock 선언해 놓아야 어노테이션으로 Mock 만들어서 사용할 수 있다
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  /***
   * Mocking
   * 구현체가 없지만 인터페이스만 알고 있을 때
   * 인터페이스를 사용하는 나의 코드가 올바르게 동작하는지 확인하기 위한 방법
   */
  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  void createStudyService() {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }

  @Test
  void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }


}