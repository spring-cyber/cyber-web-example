package com.oyetalk.iot.application.impl;

import com.cyber.domain.entity.PagingData;
import com.oyetalk.iot.application.IotService;
import com.oyetalk.iot.domain.entity.Iot;
import com.oyetalk.iot.domain.repository.IotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class IotServiceImpl implements IotService {

	private static final Logger LOGGER = LoggerFactory.getLogger( IotServiceImpl.class );

    @Autowired
    IotMapper iotMapper;
    
    
    @Override
    @Transactional(readOnly = false)
    public Integer save(Iot iot) {

        if( null == iot) {
            LOGGER.warn("save iot, but iot is null...");
            return 0;
        }

        return iotMapper.save(iot);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer deleteById(Iot iot) {

        if( null == iot) {
            LOGGER.warn("delete iot, but iot is null  or iot id is null...");
            return 0;
        }

        return iotMapper.deleteById(iot);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateById(Iot iot) {

        if( null == iot) {
            LOGGER.warn("update iot, but iot is null  or iot id is null...");
            return 0;
        }

        return iotMapper.updateById(iot);
    }

    @Override
    public Iot selectOne(Iot iot) {
        if( iot == null) {
            LOGGER.warn("select iot one, but iot is null ...");
            return null;
        }
        iot = iotMapper.selectOne( iot );
        return iot;
    }
    

    @Override
    public PagingData<Iot> selectPage(Iot iot) {
        PagingData<Iot> pagingData = new PagingData<Iot>();

        if( null == iot ) {
            LOGGER.warn("select iot page, but iot is null...");
            return pagingData;
        }

        Integer queryCount = iotMapper.selectByIndexCount( iot );
        pagingData.setRow( queryCount );

        if( null != queryCount && queryCount <= 0 ) {
            LOGGER.info("select iot page , but count {} == 0 ...",queryCount);
            return pagingData;
        }

        List<Iot> iots =  selectByIndex( iot );
        pagingData.setData(iots);
        return pagingData;
    }

    @Override
    public List<Iot> selectByIndex(Iot iot) {
        List<Iot> iots = new ArrayList<Iot>();
        if( iot == null) {
            LOGGER.warn("select iot by index, but iot is null ...");
            return iots;
        }

        iots = iotMapper.selectByIndex( iot );
        
        return iots;
    }
}
