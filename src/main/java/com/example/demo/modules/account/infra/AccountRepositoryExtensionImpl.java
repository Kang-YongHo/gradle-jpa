package com.example.demo.modules.account.infra;

import com.example.demo.modules.account.application.request.AccountSearchRequest;
import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.account.domain.QAccount;
import com.example.demo.modules.common.type.YN;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Repository
public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements AccountRepositoryExtension {
    private final JPAQueryFactory queryFactory;

    public AccountRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        super(Account.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Account> accounts(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        QAccount account = QAccount.account;

        BooleanBuilder where = new BooleanBuilder();
        where.and(account.isDelete.eq(YN.N));

        if(StringUtils.hasText(accountSearchRequest.getName())){
            where.or(account.name.containsIgnoreCase(accountSearchRequest.getName()));
        }
        if (StringUtils.hasText(accountSearchRequest.getEmail())) {
            where.or(account.email.containsIgnoreCase(accountSearchRequest.getEmail()));
        }

        JPAQuery<Account> result = queryFactory.select(
                        Projections.bean(Account.class,
                                account.id,
                                account.name,
                                account.email,
                                account.age,
                                account.location
                        ))
                .from(account)
                .where(where);

        JPQLQuery<Account> query = Objects
                .requireNonNull(getQuerydsl())
                .applyPagination(pageable, result);

        QueryResults<Account> queryResults = query.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public boolean deleteAccount(Long id) {
        QAccount account = QAccount.account;
        return queryFactory.update(account)
                .set(account.isDelete, YN.Y)
                .where(account.id.eq(id)
                        .and(account.isDelete.eq(YN.N)))
                .execute() == 1L;
    }
}
