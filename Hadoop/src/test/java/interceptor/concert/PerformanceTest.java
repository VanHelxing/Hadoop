package interceptor.concert;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hadoop.interceptor.concert.Performance;
import com.hadoop.interceptor.concert.ShowConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShowConfig.class)
public class PerformanceTest {
	
	@Resource
	private Performance singingShow;
	
	@Test
	public void test() {
		singingShow.perform("海辛");
	}
}
