package com.form.junit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DataPointTest_2 {
	@DataPoints
	public static TestData[] DATA_PARAM = {
		new TestData(2, "soojin"),
		new TestData(33, "kim"),
		new TestData(1, "jun")
	};
	
	static int testCalledNumber = 0;
	
	@Theory
	public void test(TestData data) {
		System.out.println("param : " + data.name);
		assertThat(data.name, is(DATA_PARAM[testCalledNumber++].name));
	}
	
	static class TestData {
		String name;
		Integer age;
		
		TestData(Integer age, String name) {
			this.name = name;
			this.age = age;
		}
	}
}
