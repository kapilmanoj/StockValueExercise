package com.kapil.test;

import org.junit.Test;

import com.kapil.CompanyShareAnalyser;
/**
 * TestCase for CompanyShareAnalyser
 * @author Kapil
 *
 */
public class CompanyShareAnalyserTest {

	CompanyShareAnalyser testObj = new CompanyShareAnalyser();
	
	@Test
	public void positiveScenario() throws Exception {
		testObj.run("./test.csv");
	}
	
	/*@Test
	public void negativeScenario() throws Exception {
		testObj.run(null);
	}*/
}
