package com.likelion.likelioncrud.member.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.likelioncrud.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.likelioncrud.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.likelioncrud.member.api.dto.response.MemberListResponseDto;
import com.likelion.likelioncrud.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 사용자 저장
    @PostMapping("/save")
    public ApiResTemplate<String> memberSave(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        memberService.memberSave(memberSaveRequestDto);
        return ApiResTemplate.successWithNoContents(SuccessCode.MEMBER_SAVE_SUCCESS);
    }

    // 사용자 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<MemberListResponseDto> memberFindAll() {
        MemberListResponseDto memberListResponseDto = memberService.memberFindAll();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberListResponseDto);
    }

    // 회원 id를 통해 특정 사용자 조회
    @GetMapping("/{memberId}")
    public ApiResTemplate<MemberInfoResponseDto> memberFindOne(@PathVariable("memberId") Long memberId) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.memberFindOne(memberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, memberInfoResponseDto);
    }

    // 사용자 정보 수정
    @PatchMapping("/{memberId}")
    public ApiResTemplate<String> memberUpdate(@PathVariable("memberId") Long memberId,
                                                              @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.memberUpdate(memberId, memberUpdateRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.MEMBER_UPDATE_SUCCESS, SuccessCode.MEMBER_UPDATE_SUCCESS.getMessage());
    }

    // 사용자 삭제
    @DeleteMapping("/{memberId}")
    public ApiResTemplate<String> memberDelete(@PathVariable("memberId") Long memberId) {
        memberService.memberDelete(memberId);
        return ApiResTemplate.successResponse(SuccessCode.MEMBER_DELETE_SUCCESS, SuccessCode.MEMBER_DELETE_SUCCESS.getMessage());
    }
}
