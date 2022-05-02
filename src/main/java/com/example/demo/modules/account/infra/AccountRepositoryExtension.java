package com.example.demo.modules.account.infra;

import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountRepositoryExtension {

    Page<Account> accounts(AccountSearchRequest accountSearchRequest, Pageable pageable);
}
