package io.matefinderbackend.exception;

public class BusinessLogicRuntimeException extends RuntimeException {

	private static final Throwable DEFAULT_CAUSE = null;
	private static final boolean ENABLE_SUPPRESSION = true;
	private static final boolean WRITABLE_STACKTRACE = false;

	public BusinessLogicRuntimeException(String message) {
		super(message, DEFAULT_CAUSE, ENABLE_SUPPRESSION, WRITABLE_STACKTRACE);
	}

	public BusinessLogicRuntimeException(String message, Throwable cause) {
		super(message, cause, ENABLE_SUPPRESSION, WRITABLE_STACKTRACE);
	}
}