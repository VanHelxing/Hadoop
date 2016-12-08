package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hadoop.memcache.TestDao2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-mybatis.xml")
public class TestSMMemcached {
	
	@Autowired
	private TestDao2 testDao2;
	
//	@Autowired
//	private TestDao testDao;
//	
//	@Test
//	public void test() {
//		try {
//			
//			System.out.println(testDao.getUserById(21) + "----------------");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void test() {
		try {
			//System.out.println(testDao2.getUserById(21) + "---------------");
			
			System.out.println(testDao2.getList("xiaoshis"));
		} catch (Exception e) {
		}
	}
}
