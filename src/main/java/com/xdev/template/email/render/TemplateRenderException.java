package com.xdev.template.email.render;

public class TemplateRenderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TemplateRenderException() {
		super();
	}

	public TemplateRenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TemplateRenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemplateRenderException(String message) {
		super(message);
	}

	public TemplateRenderException(Throwable cause) {
		super(cause);
	}
}
