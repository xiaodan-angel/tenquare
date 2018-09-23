package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
	@Query(value = "select * from `tb_problem` where id in(select problemid from `tb_pl` where  labelid=? )  order by replytime desc",nativeQuery = true)
    public Page<Problem> newreply(Integer labelid, Pageable pageable);
	//热门问答列表
    @Query(value = "select * from `tb_problem` where id in(select problemid from `tb_pl` where  labelid=?) order by reply desc",nativeQuery = true)
    public Page<Problem> hotrely(Integer labelId,Pageable pageable);
    //等待回答列表
    @Query(value = "select * from `tb_problem` where reply=0 and id in (select problemid from `tb_pl` where labelid=?)",nativeQuery = true)
    public Page<Problem> waitlist(Integer labelId,Pageable pageable);
}
