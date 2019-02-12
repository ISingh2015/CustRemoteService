package com.custservice.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.service.CountryService;
import com.cust.domain.vo.ElegantCountry;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,DbUnitTestExecutionListener.class})
@DatabaseSetup({"classpath:CountryTestData.xml"})
public class CountryServiceTest {

    Logger logger = LoggerFactory.getLogger(CountryServiceTest.class);
    @Autowired
    private CountryService elegantCountryService;
    private ServiceControl serviceControl;

    public CountryServiceTest() {
    }

    @Test
    public void testGetCountryId() {
        ElegantCountry elegantCountry = null;
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);

        try {
            int countryId=500;
            servicePayload = elegantCountryService.getCountryById(serviceControl, countryId);
            List<Object> elegantCountryList = servicePayload.getResponseValue();
            elegantCountry = (ElegantCountry) elegantCountryList.get(0);
            logger.info("Country Found          : " + elegantCountry.getCountryName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCountry != null));
    }

    @Test
    public void testGetAllCountry() {
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        
        ArrayList<ElegantCountry> elegantCountryList=null;
        try {
//            int userId = 64002;
            servicePayload = elegantCountryService.getAllCountries(serviceControl);
            elegantCountryList = (ArrayList<ElegantCountry>) servicePayload.getResponseValue().get(0);
            logger.info("Country List Found          : " + elegantCountryList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCountryList != null));
    }

    @Test
    public void testSaveCountry() {
        ServicePayload servicePayload;        
        ArrayList<ElegantCountry> elegantCountryList = null;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        
        try {
//            int userId = 64002;
            servicePayload = elegantCountryService.getAllCountries(serviceControl);
            elegantCountryList = (ArrayList<ElegantCountry>) servicePayload.getResponseValue().get(0);
            servicePayload = elegantCountryService.saveCountryList(serviceControl, elegantCountryList);
            logger.info("Country Saved          : " + elegantCountryList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCountryList != null));
    }

}
