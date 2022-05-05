package io.matefinderbackend.exception;

public class BusinessLogicException extends Exception {

	private static final Throwable DEFAULT_CAUSE = null;
	private static final boolean ENABLE_SUPPRESSION = true;
	private static final boolean WRITABLE_STACKTRACE = false;

	public BusinessLogicException(String message) {
		super(message, DEFAULT_CAUSE, ENABLE_SUPPRESSION, WRITABLE_STACKTRACE);
	}

	public BusinessLogicException(String message, Throwable cause) {
		super(message, cause, ENABLE_SUPPRESSION, WRITABLE_STACKTRACE);
	}
}
