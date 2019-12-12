package com.cognizant.userauthservice.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.userauthservice.model.MyError;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.info("MethodArgumentNotValidException");
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.info("HttpMessageNotReadableException");
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	@ExceptionHandler(FavouriteEmptyException.class)
	public final ResponseEntity<MyError> handleUserNotFoundException(FavouriteEmptyException ex, WebRequest request) {
		LOGGER.info("CartEmptyException");
		MyError details = new MyError(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<MyError> handleUserNotFoundException(UserAlreadyExistsException ex,
			WebRequest request) {
		LOGGER.info("UserAlreadyExistsException");
		MyError details = new MyError(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(details, HttpStatus.NOT_ACCEPTABLE);
	}

}
