package com.ys.global.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import com.ys.global.error.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    	logger.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(null, ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
    	logger.error("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(null, ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    	logger.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	logger.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(null, ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생함
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
    	logger.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(null, ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
    	logger.error("handleEntityInvalidException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(e.getMessage(), errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * 유효하지 않은 파라메터를 보냈을 경우 혹은 필요한 파라메터를 보내지 않았을 경우 발생
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    //protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    protected ModelAndView MissingServletRequestParameterException(MissingServletRequestParameterException e) {
    	logger.error("MissingServletRequestParameterException", e);
        //final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        //return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	ModelAndView model = new ModelAndView("/empty/common/500");
    	model.setStatus(HttpStatus.BAD_REQUEST);
        return model;
    }

    /**
     * SQL 실행이 실패했을 경우 발생
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    //protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    protected ModelAndView BadSqlGrammarException(BadSqlGrammarException e) {
    	logger.error("handleBadSqlGrammarException");
        //final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        //return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	ModelAndView model = new ModelAndView("/empty/common/500");
    	model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return model;
    }

    @ExceptionHandler(Exception.class)
    //protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    protected ModelAndView handleException(Exception e) {
    	logger.error("handleServerException", e);
        //final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        //return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	ModelAndView model = new ModelAndView("/empty/common/500");
    	model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return model;
    }

}
