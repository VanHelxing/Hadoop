package test;

import org.junit.Test;

public class TestReflect {

	
	@Test
	public void test() throws Exception {
//		TestReflect testReflect = new TestReflect();
//		System.out.println(testReflect.getClass().getName());
//		System.out.println(testReflect.getClass().getSimpleName());
		
		Class<?> class1 = null;
		Class<?> class2 = null;
		Class<?> class3 = null;
		
		class1 = Class.forName("test.TestReflect");
		class2 = new TestReflect().getClass();
		class3 = TestReflect.class;
		
		System.out.println(class1.getName());
		System.out.println(class2.getName());
		System.out.println(class3.getName());
	}
	
}
