package com.xdev.inregration.render;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xdev.template.email.render.TemplateRenderer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class HandlebarsTemplateRendererTest {

	@Resource(name = "templateRenderer")
	private TemplateRenderer templateRenderer;

	private static final String TEST_TEMPLATE = "test-template";
	private static final String EXPECTED_NAME = "Iurii";
	private static final String EXPECTED_CONTENT = String.format("Hello my name is %s!!!", EXPECTED_NAME);

	@Test
	public void shouldRenderTemplateForRegisterEmail() {
		Map<String, Object> context = new HashMap<>();
		context.put("name", EXPECTED_NAME);
 
		String actualContent = templateRenderer.render(TEST_TEMPLATE, context);
		assertEquals(EXPECTED_CONTENT, actualContent);
	}
}
