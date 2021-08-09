package com.tutorials.junit5.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.tutorials.junit5.domain.Member;
import com.tutorials.junit5.repository.StudyRepository;
import java.util.Optional;
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

  @Test
  void createStudyServiceStubbing(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("test@gmail.com");

    when(memberService.findById(1L)).thenReturn(Optional.of(member));

    /***
     * 기능 : when()
     * findById parameter 값이 1L 이면 Optional.of(member)를 리턴하여 newmember에 객체를 할당
     */
    Optional<Member> newmember = memberService.findById(1L);
    assertEquals("test@gmail.com", newmember.get().getEmail());

    /***
     * 기능 : doThrow()
     * validate parameter 값으로 2L 호출이되면 예외가 발생하도록 stubbing
     */
    doThrow(new IllegalArgumentException()).when(memberService).validate(2L);
    assertThrows(IllegalArgumentException.class, () -> {
      memberService.validate(2L);
    });

    /***
     * 기능 : when()
     * 첫번째, 어떤 것이든 조회되는 경우
     * 두번째, 예외가 발생하는 경우
     * 세번째, 값이 없는 null 반환하는 경우
     * 3가지를 순차적으로 통과되어야만 테스트는 성공한다
     */
    when(memberService.findById(any()))
        .thenReturn(Optional.of(member)) // 첫번째
        .thenThrow(new RuntimeException()) // 두번째
        .thenReturn(Optional.empty()); // 세번째

    Optional<Member> byId = memberService.findById(1L);
    assertEquals("test@gmail.com", byId.get().getEmail());

    assertThrows(RuntimeException.class, () -> {
      memberService.findById(2L);
    });

    assertEquals(Optional.empty(), memberService.findById(1L));

  }
}