package com.example.demo.modules.account.infra;

import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountRepositoryExtension {

    Page<Account> accounts(AccountSearchRequest accountSearchRequest, Pageable pageable);

    boolean deleteAccount(Long id);
}
