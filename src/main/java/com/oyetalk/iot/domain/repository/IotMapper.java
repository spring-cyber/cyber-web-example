package com.oyetalk.iot.domain.repository;

import com.cyber.infrastructure.repository.BaseMapper;
import com.oyetalk.iot.domain.entity.Iot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IotMapper extends BaseMapper<Iot> {

}