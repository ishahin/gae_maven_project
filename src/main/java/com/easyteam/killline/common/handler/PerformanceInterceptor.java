/**
 * 
 */
package com.easyteam.killline.common.handler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Pedro Almir
 * 
 */
public class PerformanceInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation method) throws Throwable {
		long startTime = System.currentTimeMillis();
		
		try {
			Object result = method.proceed();
			return result;
		} finally {
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
			System.out.println("Method Name: " + method.getMethod().getName() + " Execution Time:- " + executionTime + " ms");
		}
	}

}
