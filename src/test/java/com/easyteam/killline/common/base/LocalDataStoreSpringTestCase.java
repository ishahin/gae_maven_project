package com.easyteam.killline.common.base;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * Father of all tests.
 * 
 * @author EasyTeam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:app-config.xml" })
public class LocalDataStoreSpringTestCase extends LocalDataStoreTestCase {

	public static String FIELD_INVALID= "field.invalid";
	public static String FIELD_HAS_UNIQUE= "field.hasUnique";
	public static String FIELD_REQUIRED= "field.required";
	
	/**
	 * Interface to provide configuration for a web application. 
	 * This is read-only while the application is running, but may be reloaded if 
	 * the implementation supports this. This interface adds a getServletContext 
	 * method to the generic ApplicationContext interface, and defines a well-known 
	 * application attribute name that the root context must be bound to in the 
	 * bootstrap process.
	 */
	@Autowired
	public WebApplicationContext wac;
	/**
	 * Mock implementation of the HttpSession interface. Supports the Servlet 2.4 API level.
	 * Used for testing the web framework; also useful for testing application controllers.
	 */
	@Autowired
	public MockHttpSession session;
	/**
	 * Mock implementation of the HttpServletRequest interface. Supports the Servlet 2.4 API level.
	 * Used for testing the web framework; also useful for testing application controllers.
	 */
	@Autowired
	public MockHttpServletRequest request;
	/**
	 * Main entry point for server-side Spring MVC test support.
	 */
	public MockMvc mockMvc;
	/**
	 * The central class for client-side HTTP access. It simplifies communication with HTTP servers, 
	 * and enforces RESTful principles. It handles HTTP connections, leaving application code to 
	 * provide URLs (with possible template variables) and extract results.
	 */
	protected RestTemplate restTemplate;
	/**
	 * Class to handle all messages message.properties
	 */
	@Autowired
	MessageSource messageSource;
	/**
	 * Setup method
	 */
	@Before
	public void setup() {
		super.setUp();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.restTemplate = new RestTemplate(new MockMvcClientHttpRequestFactory(mockMvc));
	}

	/**
	 * Form the name of full view
	 * 
	 * @param nameView
	 * @return
	 */
	public String getNameViewFull(String nameView) {
		return "/WEB-INF/views/" + nameView + ".jsp";

	}

	/**
	 * Add Parameter
	 * 
	 * @param object object that needs to be added on request
	 * @param requestBuilder Request that will add objects requisition
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public void addParam(Object object, MockHttpServletRequestBuilder requestBuilder) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (object != null) {
			Class<? extends Object> classObject = object.getClass();
			Method[] methods = classObject.getMethods();
			for (Method method : methods) {
				/* Fetch all the bean methods that begin with "get" and "is" excluding only "getClass" */
				if (!method.getName().equals("getClass")){
					
					if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
						String nameAttribute = nameAttribute(method.getName());
						Object attributeValue = classObject.getMethod(method.getName()).invoke(object);
						
						if (attributeValue != null) {
							if (attributeValue instanceof List) {
								List lista = (List) attributeValue;
								for (Object object2 : lista) {
									/* Adds the list of attributes for the parameters of the request */
									requestBuilder.param(nameAttribute, object2.toString());
								}
							} else {
								/* adds the attribute parameters */
								requestBuilder.param(nameAttribute, attributeValue.toString());
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Get the attribute name from the method name
	 * @param name
	 * @return nameAttribute
	 */
	private String nameAttribute(String name) {
		String nameToUpper = null;
		if (name.startsWith("get"))
			nameToUpper = name.replaceAll("get", "");
		if (name.startsWith("is"))
			nameToUpper = name.replaceAll("is", "");
		return nameToUpper.replaceFirst(nameToUpper.substring(0, 1), nameToUpper.substring(0, 1).toLowerCase());
	}
	/**
	 * 
	 * @param field
	 * @param errors
	 * @return
	 */
	public String messageCode(String field,Errors errors){
		try{
		 messageSource.getMessage(errors.getFieldError(field),null);
		}catch(NoSuchMessageException e){
		fail("The message is not in message.properties");
		}
		return errors.getFieldError(field).getCode();
	}
	
}
