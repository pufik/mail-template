package com.xdev.template.email.render;

public interface TemplateRenderer {

	String render(String templateUid, Object context);

}
