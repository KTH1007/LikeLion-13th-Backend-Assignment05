package com.likelion.likelioncrud.post.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.post.api.dto.request.PostSaveRequestDto;
import com.likelion.likelioncrud.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.likelioncrud.post.api.dto.response.PostInfoResponseDto;
import com.likelion.likelioncrud.post.api.dto.response.PostListResponseDto;
import com.likelion.likelioncrud.post.application.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    // 게시물 저장
    @PostMapping("/save")
    public ApiResTemplate<String> postSave(@RequestBody @Valid PostSaveRequestDto postSaveRequestDto) {
        postService.postSave(postSaveRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.POST_SAVE_SUCCESS, SuccessCode.POST_SAVE_SUCCESS.getMessage());
    }

    // 사용자 id를 기준으로 해당 사용자가 작성한 게시글 목록 조회
    @GetMapping("/member/{memberId}")
    public ApiResTemplate<PostListResponseDto> myPostFindAll(@PathVariable("memberId") Long memberId) {
        PostListResponseDto postListResponseDto = postService.postFindMember(memberId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, postListResponseDto);
    }

    // 게시물 단건 조회
    @GetMapping("/{postId}")
    public ApiResTemplate<PostInfoResponseDto> getPost(@PathVariable("postId") Long postId) {
        PostInfoResponseDto postInfoResponseDto = postService.findPost(postId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, postInfoResponseDto);
    }

    // 게시물 수정
    @PatchMapping("/{postId}")
    public ApiResTemplate<String> postUpdate(@PathVariable("postId") Long postId,
                                             @RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        postService.postUpdate(postId, postUpdateRequestDto);

        return ApiResTemplate.successResponse(SuccessCode.POST_UPDATE_SUCCESS, SuccessCode.POST_UPDATE_SUCCESS.getMessage());
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ApiResTemplate<String> postDelete(@PathVariable("postId") Long postId) {
        postService.postDelete(postId);
        return ApiResTemplate.successResponse(SuccessCode.POST_DELETE_SUCCESS, SuccessCode.POST_DELETE_SUCCESS.getMessage());
    }
}
