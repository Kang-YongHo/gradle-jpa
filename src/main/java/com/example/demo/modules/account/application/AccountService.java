package com.example.demo.modules.account.application;

import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.application.request.AccountUpdateRequest;
import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.account.infra.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Page<Account> list(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        return accountRepository.accounts(accountSearchRequest, pageable);
    }

    public Account findOne(Long id){
        return accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Long update(AccountUpdateRequest account) {
        Account findOne = accountRepository.findById(account.getId())
                .orElseThrow(NoSuchElementException::new);
        findOne.setUpdateData(account);
        return accountRepository.save(findOne).getId();
    }

    public boolean delete(Long id) {
        boolean b = accountRepository.deleteAccount(id);
        if(b){
            // TODO: 2022/05/03 교육에서 삭제
        }
        return b;
    }
}
