package com.easyteam.killline.common.base;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author EasyTeam
 *
 */
public class LocalDataStoreTestCase {

	/**
	 * Helper class for testing against local app engine services. 
	 * Construct the helper with one LocalServiceTestConfig instance for each service that you wish to access as part of your test. 
	 * Then call setUp() before each test executes and tearDown() after each test executes. 
	 * No specific test-harness is assumed, but here's a JUnit 3 example that uses task queues and the datastore.
	 */
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
}
