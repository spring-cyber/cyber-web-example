package com.oyetalk.example1.application.impl;

import com.cyber.domain.entity.PagingData;
import com.oyetalk.example1.domain.entity.Application;
import com.oyetalk.example1.application.ApplicationService;
import com.oyetalk.example1.domain.repository.ApplicationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {

	private static final Logger LOGGER = LoggerFactory.getLogger( ApplicationServiceImpl.class );

    @Autowired
    ApplicationMapper applicationMapper;
    
    
    @Override
    @Transactional(readOnly = false)
    public Integer save(Application application) {

        if( null == application) {
            LOGGER.warn("save application, but application is null...");
            return 0;
        }

        return applicationMapper.save(application);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer deleteById(Application application) {

        if( null == application) {
            LOGGER.warn("delete application, but application is null  or application id is null...");
            return 0;
        }

        return applicationMapper.deleteById(application);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateById(Application application) {

        if( null == application) {
            LOGGER.warn("update application, but application is null  or application id is null...");
            return 0;
        }

        return applicationMapper.updateById(application);
    }

    @Override
    public Application selectOne(Application application) {
        if( application == null) {
            LOGGER.warn("select application one, but application is null ...");
            return null;
        }
        application = applicationMapper.selectOne( application );
        return application;
    }
    

    @Override
    public PagingData<Application> selectPage(Application application) {
        PagingData<Application> pagingData = new PagingData<Application>();

        if( null == application ) {
            LOGGER.warn("select application page, but application is null...");
            return pagingData;
        }

        Integer queryCount = applicationMapper.selectByIndexCount( application );
        pagingData.setRow( queryCount );

        if( null != queryCount && queryCount <= 0 ) {
            LOGGER.info("select application page , but count {} == 0 ...",queryCount);
            return pagingData;
        }

        List<Application> applications =  selectByIndex( application );
        pagingData.setData(applications);
        return pagingData;
    }

    @Override
    public List<Application> selectByIndex(Application application) {
        List<Application> applications = new ArrayList<Application>();
        if( application == null) {
            LOGGER.warn("select application by index, but application is null ...");
            return applications;
        }

        applications = applicationMapper.selectByIndex( application );
        
        return applications;
    }
}
