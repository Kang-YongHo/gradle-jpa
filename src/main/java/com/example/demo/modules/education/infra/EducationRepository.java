package com.example.demo.modules.education.infra;


import com.example.demo.modules.education.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EducationRepository extends JpaRepository<Education, Long>, EducationRepositoryExtension{

}
