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

/**
 * 
 * @author Sooo
 * @Date   2016.06.30
 * 
 * org.junit.runners.Suite : まとめて実行させたいテストクラスの指定と実行を行う
 * org.junit.experimental.categories.Categories
 *    基本的な考え方はSuiteと同様、
 *    テスト対象のメソッドやクラスをカテゴリ付けすることで、
 *    テストスイートから実行する対象を細かく制御できるようになる
 *    
 * org.junit.experimental.runners.Enclosed
 *    テストケースを分類し、グループ化出来るようになる   
 * 
 * org.junit.runners.Parameterized
 *    パラメータ化したリストを引数として、
 *    任意のテストメソッドを順次に実行したい場合に利用する
 *    
 * org.junit.experimental.theories.Theories
 * 	　　Parameterizedと似ているが、
 * 	　　指定したFixtureの各要素を引数として、
 *    任意のテストメソッドを順次的に実行したい場合に利用する
 *    
 */

@RunWith(Theories.class)
public class DataPointTest {
	/*@DataPoint
	public static String DATA_PARAM = "TOKYO";
	
	
	@Theory
	public void test(String param) {
		assertThat(param, is(DATA_PARAM));
	}
	
	@Theory
	public void test2(String param) {
		assertThat(param, is(DATA_PARAM));
	}*/
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");
	}
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}
 
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}
 
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
	
	@DataPoints
	public static String[] DATA_PARAM = {"TOKYO", "OSAKA"};

	@DataPoints
	public static boolean[] DATA_PARAM_BOOLEAN = {true, false};
	
	@DataPoints
	public static Integer[] DATA_PARAM_INTEGER = {1, 99};
	
	@DataPoints
	public static Double[] DATA_PARAM_DOUBLE = {1.2d, 2.4d};
	
	@DataPoints 
	public static long[] DATA_PARAM_LONG = {3, 82, 102};
	
	// テストを通すために
	// testメソッドが呼び出された回数をカウントするメンバ(static intじゃないと失敗します)
	static Integer testCalledNumber = 0;
	
	@Theory
	public void test(String param) {
		System.out.println("param : " + param);
		assertThat(param, is(DATA_PARAM[testCalledNumber++]));
	}
	
	@Theory
	public void test(Integer param) {
		System.out.println("param : " + param);
	}
	
	@Theory
	public void test(Boolean param) {
		System.out.println("param : " + param);
	}
	
	@Theory
	public void test(Double param) {
		System.out.println("param : " + param);
	}
	
	@Theory
	public void test(long param) {
		System.out.println("param : " + param);
	}
}
