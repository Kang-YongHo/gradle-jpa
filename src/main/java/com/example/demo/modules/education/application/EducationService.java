package com.example.demo.modules.education.application;

import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.account.infra.AccountRepository;
import com.example.demo.modules.education.application.request.EducationSearchRequest;
import com.example.demo.modules.education.application.request.EducationUpdateRequest;
import com.example.demo.modules.education.domain.Education;
import com.example.demo.modules.education.infra.EducationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final AccountRepository accountRepository;
    public Education create(Education education) {
        return educationRepository.save(education);
    }

    public Page<Education> list(EducationSearchRequest educationSearchRequest, Pageable pageable) {
        return educationRepository.educations(educationSearchRequest, pageable);
    }

    public Education detail(Long id) {
        return educationRepository.findById(id)
                .orElseThrow();
    }

    public Education update(EducationUpdateRequest educationUpdateRequest) {
        Education education = educationRepository.findById(educationUpdateRequest.getId()).orElseThrow();
        education.setName(education.getName());
        education.setSubject(education.getSubject());
        return educationRepository.save(education);
    }

    public Education join(Long id, Long accountId) {
        Education education = educationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Account account = accountRepository.findById(accountId).orElseThrow();

        education.addAccount(account);
        return educationRepository.save(education);
    }

    public Education leave(Long id, Long accountId) {
        Education education = educationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Account account = accountRepository.findById(accountId).orElseThrow();

        education.removeAccount(account);
        return educationRepository.save(education);
    }
}

