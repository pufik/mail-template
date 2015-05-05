package com.xdev.template.email.render;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

@RunWith(MockitoJUnitRunner.class)
public class HandlebarsTemplateRendererTest {

	private static final String TEMPLATE_UID = "some-test-template";
	private static final String EXPECTED_CONTENT = "my expected content";

	@Mock
	private Handlebars handlebars;

	@Mock
	private Template template;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@InjectMocks
	private HandlebarsTemplateRenderer unit;

	@Test
	public void shouldRenderTemplateByUidAndContext() throws Exception {
		Map<String, String> context = new HashMap<>();
		given(handlebars.compile(anyString())).willReturn(template);
		given(template.apply(context)).willReturn(EXPECTED_CONTENT);

		String actualContent = unit.render(TEMPLATE_UID, context);

		verify(handlebars).compile(TEMPLATE_UID);
		verify(template).apply(context);
		assertEquals(EXPECTED_CONTENT, actualContent);
	}
	
	@Test
	public void shouldThrowExceptionWhenRenderTemplateByUidAndContext() throws Exception {
		Map<String, String> context = new HashMap<>();
		given(handlebars.compile(anyString())).willThrow(new IOException());
		expectedException.expect(TemplateRenderException.class);
		
		unit.render(TEMPLATE_UID, context);
	}
}
