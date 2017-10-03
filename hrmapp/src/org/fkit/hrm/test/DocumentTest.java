package org.fkit.hrm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.DocumentDao;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.User;
import org.junit.Test;

public class DocumentTest {
	private SqlSession session = FKSqlSessionFactory.getSqlSession();
	private DocumentDao dd = session.getMapper(DocumentDao.class);
	
	//测试动态插入文档，通过
	@Test
	public void testSave() {
		Document document = new Document();
		document.setFileName("闪光少女");
		document.setTitle("生命被你照亮");
		document.setRemark("这一闪生命全都被你照亮，这一身洒满一夜星光，让所有伤感都得以回甘");
		dd.save(document);
		session.commit();
		session.close();
	}
	
	//测试动态查询,通过
	@Test
	public void testSelectByPage() {
		Map<String,Object> params = new HashMap<String,Object>();
		Document document = new Document();
		document.setTitle("生命");
		params.put("document", document);
		List<Document> documents = dd.selectByPage(params);
		for(Document doc : documents) {
			System.out.println(doc);
		}
	}
	
	//测试动态查询数量,通过
	@Test
	public void testCount() {
		Map<String,Object> params = new HashMap<String,Object>();
		Document document = new Document();
		document.setTitle("生命");
		params.put("document", document);
		System.out.println(dd.count(params));
	}
	
	//测试根据id查询文档
	@Test
	public void testSelectById() {
		Document document = dd.selectById(1);
		System.out.println(document);
	}
	
	//测试动态修改文档,通过
	@Test
	public void testUpdate() {
		Document document = new Document();
		document.setId(1);
		User user = new User();
		user.setId(12);
		document.setUser(user);
		dd.update(document);
		session.commit();
		session.close();
	}
	
	//测试根据id删除文档,通过
	@Test
	public void testDeleteById() {
		dd.deleteById(1);
		session.commit();
		session.close();
	}
}
