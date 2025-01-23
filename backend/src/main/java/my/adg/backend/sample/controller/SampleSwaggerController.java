package my.adg.backend.sample.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.adg.backend.member.dto.request.DuplicateEmail;
import my.adg.backend.member.dto.request.MemberJoinRequest;
import my.adg.backend.member.dto.response.MemberFindResponse;

@Tag(name = "Sample", description = "Sample API")
public interface SampleSwaggerController {

}
