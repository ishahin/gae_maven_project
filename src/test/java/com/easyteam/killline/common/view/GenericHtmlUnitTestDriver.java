/**
 * 
 */
package com.easyteam.killline.common.view;

import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents a generic firefox test driver
 * 
 * @author Pedro Oliveira
 * 
 */
public class GenericHtmlUnitTestDriver {

	/**
	 * Web driver
	 */
	protected WebDriver driver;
	/**
	 * Wait default
	 */
	protected WebDriverWait waitDefault;
	/**
	 * URL Base
	 */
	protected String baseUrl = "http://";
	/**
	 * Errors buffer
	 */
	protected StringBuffer verificationErrors = new StringBuffer();
	/**
	 * 
	 */
	protected boolean acceptNextAlert = true;

	/**
	 * Use Firefox Driver and 8 seconds for timeout
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.driver = (WebDriver) new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		this.waitDefault = new WebDriverWait(driver, 8);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	/**
	 * @param by
	 * @return true if element is present
	 */
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * @return text from alert
	 */
	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alert.getText();
		} finally {
			acceptNextAlert = true;
		}
	}

}
