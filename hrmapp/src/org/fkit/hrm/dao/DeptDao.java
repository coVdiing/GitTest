package org.fkit.hrm.dao;

import static org.fkit.hrm.util.common.HrmConstants.DEPTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.DeptDynaSqlProvider;
import org.fkit.hrm.domain.Dept;

public interface DeptDao {
	//动态查询
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWithParam")
	List<Dept> selectByPage(Map<String,Object> params);
	
	//动态查询总数量
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	public Integer count(Map<String,Object> params);
	
	//查询所有部门
	@Select("select * from "+DEPTTABLE+" ")
	public List<Dept> selectAllDept();
	
	//根据id查找部门
	@Select("select * from "+DEPTTABLE+" where ID = #{id}")
	public Dept selectById(Integer id);
	
	//根据id删除部门
	@Delete("delete from "+DEPTTABLE+" where ID = #{id}")
	public void deleteById(Integer id);
	
	//动态插入部门
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	public void save(Dept dept);
	
	//动态修改部门
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	public void update(Dept dept);
}
