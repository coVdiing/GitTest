package org.fkit.hrm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.UserDao;
import org.fkit.hrm.domain.User;


public class UserDaoTest {
	//获得SqlSession实例
	private static  SqlSession session = FKSqlSessionFactory.getSqlSession();
	//获得接口对象
	UserDao ud = session.getMapper(UserDao.class);
	
	public static void main(String[] args) {
		UserDaoTest test = new UserDaoTest();
		//test.testSelectByLoginnameAndPassword("admin","123456");
		//test.testSelectById(1);
		//test.testSave();
		//test.testDelete();
		//test.testUpdate();
		test.testSelectByPage();
		session.commit();
		session.close();
	}
	
	//测试根据用户名和密码查找用户,通过
	public void testSelectByLoginnameAndPassword(String loginname,String password){
		User user = ud.selectByLoginnameAndPassword(loginname, password);
		System.out.println(user);
	}
	
	//测试根据id查询用户,通过
	public void testSelectById(Integer id) {
		User user = ud.selectById(id);
		System.out.println(user);
	}
	
	//测试动态插入用户,通过
	public void testSave() {
		User user = new User();
		user.setLoginname("lao");
		user.setPassword("5");
		user.setStatus(2);
		user.setUsername("new");
		ud.save(user);
	}
	
	//测试根据id删除用户,通过
	public  void testDelete() {
		ud.deleteById(11);
	}
	
	//测试动态修改用户,通过
	public void testUpdate() {
		User user = new User();
		user.setLoginname("lao");
		user.setId(9);
		ud.update(user);	
	}
	
	//测试动态查询，通过
	public void testSelectByPage() {
		User user = new User();
		user.setUsername("韩梅梅");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user", user);
		List<User> list = ud.selectByPage(params);
		for(User obj : list) {
			System.out.println(obj);
		}
	}
}
