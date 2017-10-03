package org.fkit.hrm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.JobDao;
import org.fkit.hrm.domain.Job;
import org.junit.Test;

public class JobDaoTest {
	private SqlSession session = FKSqlSessionFactory.getSqlSession();
	private JobDao jd = session.getMapper(JobDao.class);
	
	//测试根据id查询职位，通过
	@Test
	public void testSelectById() {
		Job job = jd.selectById(8);
		System.out.println(job);
	}
	
	//测试查询所有职位,通过
	@Test
	public void testSelectAll() {
		List<Job> jobs = jd.selectAllJob();
		for(Job job : jobs) {
			System.out.println(job);
		}
	}
	
	//测试动态查询,通过
	@Test
	public void testSelectWithParam() {
		Map<String,Object> params = new HashMap<String,Object>();
		Job job = new Job();
		job.setName("理");
		params.put("job", job);
		List<Job> jobs = jd.selectWithParam(params);
		for(Job job2 : jobs) {
			System.out.println(job2);
		}
	}
	
	//测试动态查询总数量，通过
	@Test
	public void testCount() {
		Map<String,Object> params = new HashMap<String,Object>();
		Job job = new Job();
		job.setName("开");
		params.put("job", job);
		System.out.println(jd.count(params));
	}
	
	//测试动态插入职位,通过
	@Test
	public void testSave() {
		Map<String,Object> params = new HashMap<String,Object>();
		Job job = new Job();
		job.setName("游戏体验师");
		job.setRemark("试玩游戏，并根据游戏体验提交报告，总结游戏优缺点");
		jd.save(job);
		session.commit();
		session.close();
	}
	
	//测试根据id删除职位，通过
	@Test
	public void testDeleteById() {
		jd.deleteById(11);
		session.commit();
		session.close();
	}
	
	//测试动态修改职位
	@Test
	public void testUpdate() {
		Job job = new Job();
		job.setName("职业玩家");
		job.setId(12);
		job.setRemark("游戏天赋出众，gamer的杰出代表");
		jd.update(job);
		session.commit();
		session.close();
	}
	
}
