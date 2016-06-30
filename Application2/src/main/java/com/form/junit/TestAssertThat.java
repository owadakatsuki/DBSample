package com.form.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

// org.hamcrest.CoreMatchersパッケージのMatcherを利用するためのstaticインポート
import static org.hamcrest.CoreMatchers.*;

public class TestAssertThat {

	static final String hoge = "foo bar";
	
	// is
	@Test
	public void testIs() {
		//isの例、equalToも同じ意味になるがisの方が直感的
		assertThat(hoge, is("foo bar"));
		
		assertThat(hoge, equalTo("foo bar"));
	}
	
	// not
	@Test
	public void testNot() {
		assertThat(hoge, not(is("foo bar")));
	}

	// allOf
	@Test
	public void testAllOf() {
		//allOfの例、複合条件を記載する。
		assertThat(hoge, allOf(startsWith("foo"), endsWith("bar")));
		
		//fooで始まるがBARで終わらないのでallOf()はfalseになるがnotがあるのでtrueになる
		assertThat(hoge, not(allOf(startsWith("foo"), endsWith("BAR"))));
	}
	
	// anyOf
	@Test
	public void testAnyOf() {
		//anyOfの例、startsWith("foo")はtrue,endsWith("BAR")はfalseでもorの評価なのでtrue
		assertThat(hoge, anyOf(startsWith("foo"), endsWith("BAR")));
	}
	
	// instanceOf
	@Test
	public void testInstanceOf() {
		// instanceOfの例、hogeがStringかチェック
		assertThat(hoge, instanceOf(String.class));
				
		// anyはinstanceOfのショートカット
		assertThat(hoge, any(String.class));
	}
	
	// sameInstance : 同じインスタンス（ポイントが同じ）なのかな?
	@Test
	public void testSameInstance() {
		//String dummy = "dummy";
		String dummy = hoge;
		// instanceOfの例、hogeがStringかチェック
		assertThat(hoge, sameInstance(dummy));
	}
	
	// anything : 何でもtrueになるMatcherです。
	// モックを利用しているけど、全ての振る舞いを制御できていない時等には
	// これでお茶を濁すなんて手もたまにやります。
	@Test
	public void testAnything() {
		assertThat(hoge, anything("dummy"));
		assertThat(hoge, anything());
		assertThat(null, anything());
	}
	
	// null value
	@Test
	public void testNullValue() {
		assertThat(null, nullValue());
		assertThat(hoge, notNullValue());
	}
	
	// has item
	@Test
	public void testHasItem() {
		List<String> checkTargetList = Arrays.asList(new String[]{"hoge", "foo", "bar"});
	
		assertThat(checkTargetList, hasItem("hoge"));
		//assertThat(checkTargetList, not(hasItem("HOGE")));
		assertThat(checkTargetList, hasItem("HOGE"));
	}
	
	// has items
	@Test
	public void testHasItems() {
		List<String> checkTargetList = Arrays.asList(new String[]{"hoge", "foo", "bar"});
	
		assertThat(checkTargetList, hasItems("hoge", "foo"));
		//順番は結果に影響なし
		assertThat(checkTargetList, hasItems("foo", "hoge"));
	}
	
	// contain string 
	@Test
	public void testContainsString() {
		assertThat(hoge, containsString("foo"));
		assertThat(hoge, containsString("o b"));
	}
	
	// both
	@Test
	public void testBoth() {
		//bothチェック。hoge.containsString("foo") and hoge.containsString("o b")
		assertThat(hoge, both(containsString("foo")).and(containsString("o b")));
		//assertThat(hoge, allOf(containsString("foo"), containsString("o b")));
		
		List<String> checkTargetList = Arrays.asList(new String[]{"hoge", "foo", "bar"});
		//Listに対するbothチェック,"foo"と"bar"は含むが"FOO"と"BAR"は含まない
		assertThat(checkTargetList, both(hasItems("foo", "bar")).and(not(hasItems("FOO", "BAR"))));
	}
	
	// either
	@Test
	public void testEither() {
		assertThat(hoge, either(containsString("hoge")).or(containsString("BAR")));
		//assertThat(hoge, anyOf(containsString("hoge"), (containsString("BAR"))));
	}
}
