package org.fkit.hrm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.fkit.hrm.dao.NoticeDao;
import org.fkit.hrm.domain.Notice;
import org.junit.Test;

public class NoticeDaoTest {
	private SqlSession session = FKSqlSessionFactory.getSqlSession();
	private NoticeDao nd = session.getMapper(NoticeDao.class);
	
	//测试插入公告,通过
	@Test
	public void testSave() {
		Notice notice = new Notice();
		notice.setContent("我只是一个配角，不重要，就算最后会把我的戏份删掉，我只会躲在墙角，不哭不闹");
		notice.setTitle("你的 配角");
		nd.save(notice);
		session.commit();
		session.close();
	}
	
	//测试动态查询，通过
	@Test
	public void testSelectWithParam(){
		Map<String,Object> params = new HashMap<String,Object>();
		Notice notice = new Notice();
		notice.setTitle("配角");
		params.put("notice", notice);
		List<Notice> list = nd.selectByPage(params);
		for(Notice notices : list) {
			System.out.println(notices);
		}
	}
	
	//测试动态查询总数量，通过
	@Test
	public void testCount() {
		Map<String,Object> params = new HashMap<String,Object>();
		Notice notice = new Notice();
		notice.setTitle("配角");
		params.put("notice", notice);
		System.out.println(nd.count(params));
	}
	
	//测试根据id查询公告,通过
	@Test
	public void testSelectById() {
		Notice notice = nd.selectById(1);
		System.out.println(notice);
	}
	
	//测试根据id删除公告,通过
	@Test
	public void testDeleteById() {
		nd.deleteById(1);
		session.commit();
		session.close();
	}
	
	//测试动态更新,通过
	@Test
	public void testUpdate() {
		Notice notice = new Notice();
		notice.setContent("怪时间很不凑巧，为什么在他之后才遇到，你刻意对我的好，我知道，只是寂寞的解药");
		notice.setTitle("你的 配角");
		notice.setId(2);
		nd.update(notice);
		session.commit();
		session.close();
	}
}
