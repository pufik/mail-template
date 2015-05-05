package com.xdev.template.email.render;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

public class HandlebarsTemplateRenderer implements TemplateRenderer {

	private static final Logger LOG = LoggerFactory.getLogger(HandlebarsTemplateRenderer.class);

	private final Handlebars handlebars;

	public HandlebarsTemplateRenderer(Handlebars handlebars) {
		super();
		this.handlebars = handlebars;
	}

	@Override
	public String render(String templateUid, Object context) {
		try {
			LOG.debug("Render template [{}] with context [{}]", templateUid, context);

			return getTemplate(templateUid).apply(context);
		} catch (IOException e) {
			LOG.error("Can't render template with uri: " + templateUid, e);
			throw new TemplateRenderException(e);
		}
	}

	protected Template getTemplate(String templateUid) throws IOException {
		return getHandlebars().compile(templateUid);
	}

	public Handlebars getHandlebars() {
		return handlebars;
	}
}
