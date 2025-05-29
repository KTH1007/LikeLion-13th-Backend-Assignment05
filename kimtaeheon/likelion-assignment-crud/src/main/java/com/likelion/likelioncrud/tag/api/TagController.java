package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    // 태그 저장
    @PostMapping("/save")
    public ApiResTemplate<String> tagSave(@RequestBody @Valid TagSaveRequestDto tagSaveRequestDto) {
        tagService.tagSave(tagSaveRequestDto);
        return ApiResTemplate.successResponse(SuccessCode.TAG_SAVE_SUCCESS, SuccessCode.TAG_SAVE_SUCCESS.getMessage());
    }

    // 태그 전체 조회
    @GetMapping()
    public ApiResTemplate<TagListResponseDto> getAllTags() {
        TagListResponseDto tagListResponseDto = tagService.getAllTags();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagListResponseDto);
    }

    // 태그 단건 조회
    @GetMapping("/{tagId}")
    public ApiResTemplate<TagInfoResponseDto> getTag(@PathVariable("tagId") Long tagId) {
        TagInfoResponseDto tagInfoResponseDto = tagService.getTag(tagId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, tagInfoResponseDto);
    }

    // 태그 수정
    @PatchMapping("/{tagId}")
    public ApiResTemplate<String> tagUpdate(@PathVariable("tagId") Long tagId,
                                            @RequestBody TagUpdateRequestDto tagUpdateRequestDto) {
        tagService.tagUpdate(tagId, tagUpdateRequestDto);

        return ApiResTemplate.successResponse(SuccessCode.TAG_UPDATE_SUCCESS, SuccessCode.TAG_UPDATE_SUCCESS.getMessage());
    }

    // 태그 삭제
    @DeleteMapping("/{tagId}")
    public ApiResTemplate<String> tagDelete(@PathVariable("tagId") Long tagId) {
        tagService.tagDelete(tagId);
        return ApiResTemplate.successResponse(SuccessCode.TAG_DELETE_SUCCESS, SuccessCode.TAG_DELETE_SUCCESS.getMessage());
    }
}
