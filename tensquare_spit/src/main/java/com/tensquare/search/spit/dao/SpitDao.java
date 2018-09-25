package com.tensquare.search.spit.dao;

import com.tensquare.search.spit.pojo.Spit;
import com.tensquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {
}
