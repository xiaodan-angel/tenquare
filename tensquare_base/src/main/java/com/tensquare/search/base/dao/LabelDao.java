package com.tensquare.search.base.dao;

import com.tensquare.base.pojo.Label;
import com.tensquare.search.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {
}
