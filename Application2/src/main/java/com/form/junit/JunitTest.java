package com.form.junit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 * 
 * @author Sooo
 * @Date   2016.06.30
 * 
 * 
 * assertEquals  : 2つの値が同値であることをテストする
 * assertSame    : 2つのオブジェクトが同一であることをテストする
 * assertNotSame : 2つのオブジェクトが同一でないことをテストする
 * assertNull	 : オブジェクトがnullであることをテストする
 * assertNotNull : オブジェクトがnullでないことをテストする
 * assertTrue	 : 指定された条件がtrueであるかどうかをテストする
 * assertFalse	 : 指定された条件がfalseであるかどうかをテストする
 * fail			 : テストを失敗させる
 * 
 * BeforeClass	 : テストクラスのstatic initializerの後に呼ばれる
 * Before		 : テストクラスのconstructorの後に呼ばれる
 * Test			 : テストmethodに付与
 * After		 : テストmethod実行後に実行したいmethodに付与
 * AfterClass	 : テストクラス実行後に実行したいmethodに付与
 */
public class JunitTest {
	
	static { System.out.println("スタティックイニシャライザ call"); }
 
	{ System.out.println("インスタンスイニシャライザ call"); }
	
	public JunitTest() { System.out.println("コンストラクタ call"); }
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize call");
		try {
			super.finalize();
		}catch(Exception e) {
		}
	};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception { System.out.println("@BeforeClass call"); }
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception { System.out.println("@AfterClass call"); }
 
	@Before
	public void setUp() throws Exception { System.out.println("@Before call"); }
 
	@After
	public void tearDown() throws Exception { System.out.println("@After call"); }
	
	@Test
	public void testSample1() {
		System.out.println("@Test testSample1 call");
		Person 		person 	= new Person(2, 6, "soojin");
		Integer 	min 	= 100;
		assertEquals(2 * min, person.walk(min));
	}

	@Test
	// person.walk(100)の結果は200のところを、わざと500に変えているので納得できる結果
	public void testSample2() {
		System.out.println("@Test testSample2 call");
		Person 		person 	= new Person(2, 6, "soojin");
		Integer 	min 	= 100;
		assertEquals("person.walkがおかしいよ！", 5 * min, person.walk(min));
	}
	
	@Test(expected=ArithmeticException.class)
	// Personのrunメソッドが常にArithmeticExceptionをスローするようにし
	// person.runがArithmeticExceptionをスローした時にテストが成功するようにする
	
	// personのrunメソッドを呼び出した時のArithmeticExceptionをキャッチし成功
	// もしArithmeticExceptionが飛んでこなければ、次行で失敗するように
	// fail(String message)メソッドの呼び出しを追加しておいた
	public void testSample3() {
		System.out.println("@Test testSample3 call");
		Person 		person 	= new Person(2, 6, "soojin");
		Integer 	min 	= 100;
		
		//try {
			assertEquals("person.runがおかしいよ！", 5 * min, person.run(min));
			//fail("ArithmeticExceptionが発生するはず！");
		//}catch (ArithmeticException exception) {	
		//}
	}
	
	
	/*static final String hoge = "foo bar";
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		// 準備（≒ given）
        int a = 1;
        
        // 実行（≒ when）
        int result = a + a;
        
        // 検証（≒ then）
        // 結果 result が、期待値 2 と等しいこと。
        assertThat(result, equalTo(2));
		
		//isの例、equalToも同じ意味になるがisの方が直感的
        assertThat(hoge, is("foo bar"));
        //equalToの例
        assertThat(hoge, equalTo("foo bar"));
        //notの例
        assertThat(hoge, not(is("Foo Bar")));
	}*/

	
	
	
	
	
}
