package org.fkit.hrm.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.EmployeeDao;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.junit.Test;

public class EmployeeTest {

	 private SqlSession session = FKSqlSessionFactory.getSqlSession();
	 private EmployeeDao ed = session.getMapper(EmployeeDao.class);
	
	//测试根据参数查询员工总数,通过
	@Test
	public  void testCount() {
		Map<String,Object> params = new HashMap<String,Object>();
		Employee employee = new Employee();
		employee.setCardId("2");
		employee.setName("爱");
		params.put("employee", employee);
		System.out.println(ed.count(params));
		session.close();
	}
	
	//测试根据参数动态查询员工,通过
	@Test
	public void testSelectWithParam() {
		Map<String,Object> params = new HashMap<String,Object>();
		Employee employee = new Employee();
		employee.setCardId("2");
		System.out.println(employee);
		params.put("employee", employee);
		List<Employee> list = ed.selectByPage(params);
		for(Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	//测试动态插入员工,通过
	@Test
	public void testSave() {
		Employee emp = new Employee();
		Dept dept = new Dept();
		dept.setId(1);
		Job job = new Job();
		job.setId(2);
		emp.setName("jay");
		emp.setDept(dept);
		emp.setJob(job);
		emp.setBirthday(new Date(System.currentTimeMillis()));
		emp.setCardId("132156465");
		emp.setAddress("广东深圳");
		emp.setPhone("465164");
		emp.setEmail("165465@qq.com");
		ed.save(emp);
		session.commit();
		session.close();
	}
	
	//测试根据id删除员工,通过
	@Test
	public void testDeleteById() {
		ed.deleteById(4);
		session.commit();
		session.close();
	}
	
	//测试根据id查询员工,通过
	@Test
	public void testSelectById() {
		Employee emp = new Employee();
		emp = ed.selectById(2);
		System.out.println(emp);
	}
	
	//测试动态修改员工,通过
	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setId(2);
		emp.setRace("汉");
		ed.update(emp);
		session.commit();
		session.close();
	}
}
