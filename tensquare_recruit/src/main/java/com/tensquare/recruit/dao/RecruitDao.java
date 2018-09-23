package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	//查询状态为2并以创建日期降序排序，查询前4条记录
   /* @Query(value = "select * from tb_recruit where state=? order by createtime",nativeQuery = true)
    public List<Recruit> recruitlist(String status);*/
   public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
   //查询状态不为0并以创建日期降序排序，查询前12条记录 //最新职位列表
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
