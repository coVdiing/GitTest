package org.fkit.hrm.dao;

import static org.fkit.hrm.util.common.HrmConstants.JOBTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.JobDynaSqlProvider;
import org.fkit.hrm.domain.Job;

public interface JobDao {
	//根据id查询职位
	@Select("select * from "+JOBTABLE+" where id = #{id}")
	public Job selectById(@Param("id") int id);
	
	//查询所有职位
	@Select("select * from "+JOBTABLE+" ")
	public List<Job> selectAllJob();
	
	//动态查询
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWithParam")
	public List<Job> selectByPage(Map<String,Object> params);
	
	//动态查询总数量
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	public Integer count(Map<String,Object> params);
	
	//根据id删除职位
	@Delete("delete from "+JOBTABLE+" where id = #{id}")
	public void deleteById(Integer id);
	
	//动态插入职位
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	public void save(Job job);
	
	//动态修改职位
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	public void update(Job job);
}
