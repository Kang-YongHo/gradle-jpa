package com.example.demo.modules.account.application;

import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.account.infra.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Page<Account> list(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        return accountRepository.accounts(accountSearchRequest, pageable);
    }
}
