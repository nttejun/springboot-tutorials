package com.tutorials.junit5.repository;

import com.tutorials.junit5.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
