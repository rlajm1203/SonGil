package com.jnu.mcd.ddobagi.common.exception;

import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody.FailureBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/** javax.validation.Valid 또는 @Validated binding error가 발생할 경우 */
	@ExceptionHandler(BindException.class)
	protected ApiResponse<FailureBody> handleBindException(BindException e) {
		log.warn("handleBindException", e);
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		return ApiResponseGenerator.fail(e.getBindingResult(), code, HttpStatus.BAD_REQUEST);
	}

	/** 주로 @RequestParam enum으로 binding 못했을 경우 발생 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ApiResponse<FailureBody> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException e) {
		log.warn("handleMethodArgumentTypeMismatchException", e);
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		return ApiResponseGenerator.fail(e.getMessage(), code, HttpStatus.BAD_REQUEST);
	}

	//DTO가 생성되다가 터져버려도 이 예외가 발생함
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ApiResponse<FailureBody> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		String errorMessage = "요청의 바디를 확인하세요.";
		Throwable cause = e.getCause();
		if (cause != null && cause.getCause() instanceof IllegalArgumentException) {
			errorMessage = cause.getCause().getMessage();
		}

		log.warn("요청 바디 예외", e);
		return ApiResponseGenerator.fail(errorMessage, code, HttpStatus.BAD_REQUEST);
	}

	/** 지원하지 않은 HTTP method 호출 할 경우 발생 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ApiResponse<FailureBody> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		log.warn("handleHttpRequestMethodNotSupportedException", e);
		String code = String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value());
		return ApiResponseGenerator.fail(e.getMessage(), code, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/** 비즈니스 로직 실행 중 오류 발생 */
	@ExceptionHandler(value = {BusinessException.class})
	protected ApiResponse<FailureBody> handleConflict(BusinessException e) {
		log.warn("BusinessException", e);
		return ApiResponseGenerator.fail(e.getMessage(), e.getCode(), e.getHttpStatus());
	}

	/** 나머지 예외 발생 */
	@ExceptionHandler(Exception.class)
	protected ApiResponse<FailureBody> handleException(Exception e) {
		log.error("Exception", e);
		String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ApiResponseGenerator.fail(e.getMessage(), code, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	protected ApiResponse<FailureBody> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
		log.warn("MethodArgumentNotValidException", e);
		return ApiResponseGenerator.fail(
				e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
				code,
				HttpStatus.BAD_REQUEST);
	}
}
