package com.banking.accounts.exception;

import com.banking.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers,
			final HttpStatusCode status, final WebRequest request)
	{
		final Map<String, String> validationErrors = new HashMap<>();
		final List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

		allErrors.forEach(error -> {
			final String fieldname = ((FieldError) error).getField();
			final String errorMessage = error.getDefaultMessage();
			validationErrors.put(fieldname, errorMessage);
		});

		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { CustomerAlreadyExistsException.class })
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(final CustomerAlreadyExistsException exception,
			final WebRequest request)
	{
		final ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				request.getDescription(false),
				HttpStatus.BAD_REQUEST,
				exception.getMessage(),
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(final ResourceNotFoundException exception,
			final WebRequest request)
	{
		final ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				request.getDescription(false),
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorResponseDto> handleGlobalException(final Exception exception,
			final WebRequest request)
	{
		final ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				request.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR,
				exception.getMessage(),
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}