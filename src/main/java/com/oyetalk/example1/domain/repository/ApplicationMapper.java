package com.oyetalk.example1.domain.repository;

import com.oyetalk.example1.domain.entity.Application;
import com.cyber.infrastructure.repository.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {

}