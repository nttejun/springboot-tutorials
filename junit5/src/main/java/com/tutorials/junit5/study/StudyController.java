package com.tutorials.junit5.study;

import com.tutorials.junit5.domain.Study;
import com.tutorials.junit5.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyController {

  final StudyRepository repository;

  @GetMapping("/study/{id}")
  public Study getStudy(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
  }

  @PostMapping("/study")
  public Study createsStudy(@RequestBody Study study) {
    return repository.save(study);
  }

}