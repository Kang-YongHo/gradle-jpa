package com.example.demo.modules.education.infra;

import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.account.domain.QAccount;
import com.example.demo.modules.common.type.YN;
import com.example.demo.modules.education.application.request.EducationSearchRequest;
import com.example.demo.modules.education.domain.Education;
import com.example.demo.modules.education.domain.QEducation;
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
import org.springframework.util.StringUtils;

import java.util.Objects;

public class EducationRepositoryExtensionImpl extends QuerydslRepositorySupport implements EducationRepositoryExtension {
    private final JPAQueryFactory queryFactory;

    public EducationRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        super(Education.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Education> educations(EducationSearchRequest educationSearchRequest, Pageable pageable) {
        QEducation education = QEducation.education;
        BooleanBuilder where = new BooleanBuilder();
        where.and(education.isDelete.eq(YN.N));

        if (StringUtils.hasText(educationSearchRequest.getName())) {
            where.or(education.name.containsIgnoreCase(educationSearchRequest.getName()));
        }
        if (StringUtils.hasText(educationSearchRequest.getSubject())) {
            where.or(education.subject.containsIgnoreCase(educationSearchRequest.getSubject()));
        }

        JPQLQuery<Education> result = from(education)
                .leftJoin(education.accounts, QAccount.account).fetchJoin()
                .where(where);

        JPQLQuery<Education> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Education> queryResults = query.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
