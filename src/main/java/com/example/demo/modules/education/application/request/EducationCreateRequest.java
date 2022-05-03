package com.example.demo.modules.education.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationCreateRequest {

    private String name;
    private String subject;
}
