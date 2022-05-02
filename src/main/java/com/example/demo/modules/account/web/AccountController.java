package com.example.demo.modules.account.web;

import com.example.demo.modules.account.application.AccountService;
import com.example.demo.modules.account.application.request.AccountCreateRequest;
import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.domain.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody AccountCreateRequest accountCreateRequest) {
        Account account = accountService.create(modelMapper.map(accountCreateRequest, Account.class));
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping
    public ResponseEntity<?> list(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC)
                    Pageable pageable,
            AccountSearchRequest accountSearchRequest) {
        Page<Account> list = accountService.list(accountSearchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
