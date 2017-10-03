package org.fkit.hrm.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.DeptDao;
import org.fkit.hrm.domain.Dept;

public class DeptDaoTest {
	//获得SqlSession实例
		private static  SqlSession session = FKSqlSessionFactory.getSqlSession();
		//获得接口对象
		DeptDao dd = session.getMapper(DeptDao.class);
		
	public static void main(String[] args) {
		DeptDaoTest test = new DeptDaoTest();
		//test.testSelectAllDept();
		//test.testSelectById();
//		test.testSave();
//		test.testDeleteById();
		test.testUpdate();
		session.commit();
		session.close();
	}
	
	//测试查询所有部门,通过
	public void testSelectAllDept() {
		List<Dept> list = dd.selectAllDept();
		for(Dept dept : list) {
			System.out.println(dept);
		}
	}
	
	//测试根据id查询部门,通过
	public void testSelectById() {
		Dept dept = dd.selectById(3);
		System.out.println(dept);
	}
	
	//动态插入部门,通过
	public void testSave() {
		Dept dept = new Dept();
		dept.setName("动画部");
		dept.setRemark("鉴定评论动画，制作动画");
		System.out.println("正在增加...");
		dd.save(dept);
	}
	
	//测试根据id删除部门，通过
	public void testDeleteById() {
		System.out.println("正在删除...");
		dd.deleteById(10);
	}
	
	//测试动态更新,通过
	public void testUpdate() {
		Dept dept = new Dept();
		dept.setId(9);
		dept.setName("动漫部");
		dept.setRemark("霓虹动漫世界第一，国产动漫正在崛起");
		System.out.println("正在更新");
		dd.update(dept);
	}
}
