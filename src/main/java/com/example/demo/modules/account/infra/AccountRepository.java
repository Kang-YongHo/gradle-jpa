package com.example.demo.modules.account.infra;

import com.example.demo.modules.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryExtension {

}
