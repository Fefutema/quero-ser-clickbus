package br.com.futema.exceptions;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ExceptionDetails exceptionDetails;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, ExceptionDetails details) {
		super(message);
		this.exceptionDetails = details;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, ExceptionDetails details) {
		super(message, cause);
		this.exceptionDetails = details;
	}

	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

}
