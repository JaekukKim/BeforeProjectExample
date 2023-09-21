package com.web.exampleshinwoo.controller;

import com.web.exampleshinwoo.model.request.MemberRequest;
import com.web.exampleshinwoo.model.response.MemberResponse;
import com.web.exampleshinwoo.model.response.Response;
import com.web.exampleshinwoo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/getmembers")
    public Response<List<MemberResponse.MemberResponseDto>> getMembers() {

        return Response.<List<MemberResponse.MemberResponseDto>>builder()
                .data(memberService.getMembers())
                .build();
    }

    @PostMapping("/signup")
    public Response<Void> signup(@RequestBody MemberRequest.signUpMember memberRequest) {
        memberService.signUp(memberRequest);

        return Response.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .statusMessage(HttpStatus.CREATED.getReasonPhrase())
                // 아마 프론트에서 Created라는 문자열로 검증할 듯 예상.
                .build();
    }
}
