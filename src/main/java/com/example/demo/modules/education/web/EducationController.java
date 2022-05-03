package com.example.demo.modules.education.web;

import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.education.application.EducationService;
import com.example.demo.modules.education.application.request.EducationCreateRequest;
import com.example.demo.modules.education.application.request.EducationSearchRequest;
import com.example.demo.modules.education.application.request.EducationUpdateRequest;
import com.example.demo.modules.education.domain.Education;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/education")
public class EducationController {

    private final EducationService eduCationService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EducationCreateRequest educationCreateRequest) {
        return ResponseEntity.ok(eduCationService.create(modelMapper.map(educationCreateRequest, Education.class)));
    }

    @GetMapping
    public ResponseEntity<?> list(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            EducationSearchRequest educationSearchRequest) {
        return ResponseEntity.ok(eduCationService.list(educationSearchRequest, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> list(@PathVariable Long id){
        return ResponseEntity.ok(eduCationService.detail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(EducationUpdateRequest educationUpdateRequest, @PathVariable Long id){
        educationUpdateRequest.setId(id);
        return ResponseEntity.ok(eduCationService.update(educationUpdateRequest));
    }

    @GetMapping("/{id}/join")
    public ResponseEntity<?> join(@PathVariable Long id, Long accountId){
        return ResponseEntity.ok(eduCationService.join(id, accountId));
    }

    @GetMapping("/{id}/leave")
    public ResponseEntity<?> leave(@PathVariable Long id, Long accountId){
        return ResponseEntity.ok(eduCationService.leave(id, accountId));
    }

}
