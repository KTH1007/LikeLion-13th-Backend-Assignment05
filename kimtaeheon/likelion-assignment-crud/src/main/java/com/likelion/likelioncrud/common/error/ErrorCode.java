package com.likelion.likelioncrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // 404 NOT FOUND
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. messageId = ", "NOT_FOUND_404"),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. postId = ", "NOT_FOUND_404"),
    TAG_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 태그가 없습니다. tag = ", "NOT_FOUND_404"),

    // 400 BAD_REQUEST
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검증에 실패하였습니다.", "BAD_REQUEST_400"),
    TAG_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "중복된 태그입니다..", "BAD_REQUEST_400"),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR_500");


    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
