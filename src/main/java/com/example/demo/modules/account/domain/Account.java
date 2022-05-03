package com.example.demo.modules.account.domain;

import com.example.demo.modules.account.application.request.AccountUpdateRequest;
import com.example.demo.modules.common.type.YN;
import com.example.demo.modules.education.domain.Education;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String age;
    private String location;

    @Enumerated(EnumType.STRING)
    private YN isDelete = YN.N;

    //DDD가 추구하는 방향
    public void setUpdateData(AccountUpdateRequest account){
        this.name = account.getName();
        this.email = account.getEmail();
        this.age = account.getAge();
        this.location = account.getLocation();
    }
}
