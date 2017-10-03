package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.domain.Document;

public interface DocumentDao {
	//动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="USER_ID",property="user",
				one=@One(select="org.fkit.hrm.dao.UserDao.selectById",
				fetchType=FetchType.EAGER)),
	})
	public List<Document> selectByPage(Map<String,Object> params);
	
	//动态查询数量
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	public Integer count(Map<String,Object> params);
	
	//动态插入文档
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	public void save(Document document);
	
	//根据id查找文档
	@Select("SELECT * FROM "+DOCUMENTTABLE+" WHERE ID = #{id} ")
	public Document selectById(Integer id);
	
	//根据id删除文档
	@Delete("DELETE FROM "+DOCUMENTTABLE+" WHERE ID = #{id} ")
	public void deleteById(Integer id);
	
	//动态修改文档
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	public void update(Document document);
}
