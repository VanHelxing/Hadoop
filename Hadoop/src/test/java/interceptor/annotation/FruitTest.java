package interceptor.annotation;

import org.junit.Test;


public class FruitTest {

	@Test
	public void test() {
		
		FruitInfoUtil.getFruitInfo(Apple.class);
//		Apple apple = new Apple();
//		System.out.println(apple.getFruitName());
	}
}
