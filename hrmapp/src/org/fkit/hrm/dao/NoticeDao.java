package org.fkit.hrm.dao;

import static org.fkit.hrm.util.common.HrmConstants.NOTICETABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.NoticeDynaSqlProvider;
import org.fkit.hrm.domain.Notice;

public interface NoticeDao {
	//动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="org.fkit.hrm.dao.UserDao.selectById",fetchType=FetchType.EAGER)
				)
	})
	public List<Notice> selectByPage(Map<String,Object> params);
	
	//动态查询总数量
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	public Integer count(Map<String,Object> params);
	
	//根据id查询公告
	@Select("select * from " + NOTICETABLE+" where id = #{id} ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="org.fkit.hrm.dao.UserDao.selectById",fetchType=FetchType.EAGER)
				)
	})
	public Notice selectById(Integer id);
	
	//根据id删除公告
	@Delete("delete from " +NOTICETABLE+" where id = #{id} ")
	public void deleteById(Integer id);
	
	//动态插入公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	public void save(Notice notice);
	
	//动态修改公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	public void update(Notice notice);
}
