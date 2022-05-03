package com.example.demo.modules.education.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class EducationUpdateRequest {

    @Setter
    private Long id;
    private String name;
    private String subject;
}
