package com.dev.sample.exception;

/**
 * Handle Try catch and finally blocks effectively
 */
public class TryCatch_Sample1 {

	public static void main(String[] args) {
		System.out.println(sayHello());
	}

	public static String sayHello () {
		try {
			System.out.println("main print");
			throw new InterruptedException("Interrupting");
		}catch (Exception ex) {
			ex.printStackTrace();
			System.err.flush();
			System.out.println("printing from exception");
			return "hello with exception";
		} finally {
			System.out.println("printing from finally");
		}
	}
}
