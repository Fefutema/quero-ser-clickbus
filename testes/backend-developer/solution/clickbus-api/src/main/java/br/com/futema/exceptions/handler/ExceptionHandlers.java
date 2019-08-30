package br.com.futema.exceptions.handler;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.futema.exceptions.BusinessException;
import br.com.futema.exceptions.ExceptionDetails;
import br.com.futema.exceptions.NotFoundException;

@ControllerAdvice
@RestController
public class ExceptionHandlers extends ResponseEntityExceptionHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);
	
	@ResponseBody
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("MethodArgumentNotValidException: ", ex);
		
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		String[] detailsMsg = errors.stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).toArray(String[]::new);
		
		ExceptionDetails details = new ExceptionDetails(new Date(), request.getContextPath(), "Validation Failed");
		details.setDetails(detailsMsg);

		return ResponseEntity.badRequest().body(details);
	}
	
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionDetails> notFoundHandler(NotFoundException ex, WebRequest request) {
		LOGGER.error("NotFoundError: ", ex);
		
		String resourcePath = null;
		if (request instanceof ServletWebRequest) {
			resourcePath = ((ServletWebRequest) request).getRequest().getRequestURI();
		}
		
		ExceptionDetails details = new ExceptionDetails(new Date(), resourcePath, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
	}
	
	@ResponseBody
	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ExceptionDetails> handleBusinessException(BusinessException ex, WebRequest request) {
		LOGGER.error("BusinessError: ", ex);
		
		String resourcePath = null;
		if (request instanceof ServletWebRequest) {
			resourcePath = ((ServletWebRequest) request).getRequest().getRequestURI();
		}
		
		ExceptionDetails errorDetails = new ExceptionDetails(new Date(), resourcePath, ex.getMessage());
		
		return ResponseEntity.badRequest().body(errorDetails);
	}
}
