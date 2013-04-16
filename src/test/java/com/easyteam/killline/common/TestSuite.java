package com.easyteam.killline.common;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/** 
 * Test pattern constituted by layers:<br/>
 * <ul>
 * <li><b>Factory: </b>Instantiates the entity needed for the test and sets its attributes, which are received by parameter. 
 * In some cases, usually when dealing with a master entity, not detail, this layer saves the entity before returning it. 
 * When dealing with a non detail, the entity is not saved, because it will be done in the factory master. 
 * </li>
 * <li><b>Populator: </b>Populates an entity with the data needed for testing. To create the entity, calls its factory.</li>
 * <li><b>TestCase: </b>Contains test cases, specifying the inputs and expected outputs. Depends on a testing procedure.</li>
 * <li><b>TestProcedure: </b>It is the test procedure for the test case (TestCase). 
 * Specifies what to do with the inputs of the test and makes assertions with the expected outputs.
 * </li>
 * </ul>
 * */
@RunWith(Suite.class)
@SuiteClasses({
	
})
public class TestSuite {
	
	/**
	 * Execute populator
	 */
	@BeforeClass
	public static void populator() {
		
	}

}
