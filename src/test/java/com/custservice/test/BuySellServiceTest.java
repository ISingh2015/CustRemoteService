package com.custservice.test;

import com.cust.common.ApplicationException;
import com.cust.common.FilterCriteria;
import com.cust.common.Pagination;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.service.BuySellService;
import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.vo.ElegantUser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class BuySellServiceTest {

    Logger logger = LoggerFactory.getLogger(BuySellServiceTest.class);
    @Autowired
    private BuySellService elegantBuySellService;
    private ServiceControl serviceControl;
    ElegantUser user;

    public BuySellServiceTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

//    @Test
    public void testGetBuySellById() {
        ElegantBuySell elegantBuySell = null;
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
            int custId = 001;
            servicePayload = elegantBuySellService.getBuySellById(serviceControl, user.getCompID(), custId);
            List<Object> elegantBuySellList = servicePayload.getResponseValue();
            elegantBuySell = (ElegantBuySell) elegantBuySellList.get(0);
            logger.info("BuySell Found          : " + elegantBuySell.getBillNo());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantBuySell != null));
    }

    @Test
    public void testGetAllBuySell() {
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
        ArrayList<ElegantBuySell> elegantBuySellList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("billDt");
        filterCriteria.setFilterCondition(">=");
        filterCriteria.setFilterFieldValue("2015/07/01 00:00:00");
//            filterCriteriaList.add(filterCriteria);

        filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("billDt");
        filterCriteria.setFilterCondition("<=");
        filterCriteria.setFilterFieldValue(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        filterCriteriaList.add(filterCriteria);

        queryCriteria.setFilterCriteria(filterCriteriaList);
        serviceControl.setQueryCriteria(queryCriteria);
        try {
            servicePayload = elegantBuySellService.getAllBuySell(serviceControl, user.getCompID(),false);
            elegantBuySellList = (ArrayList<ElegantBuySell>) servicePayload.getResponseValue().get(0);
            logger.info("Get All BuySells List Found          : " + elegantBuySellList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantBuySellList != null));
    }

    @Test
    public void testSaveBuySellsList() {
        ServicePayload servicePayload;
        ArrayList<ElegantBuySell> elegantBuySellList = null;
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
            servicePayload = elegantBuySellService.getAllBuySell(serviceControl, user.getCompID(),false);
            elegantBuySellList = (ArrayList<ElegantBuySell>) servicePayload.getResponseValue().get(0);
            servicePayload = elegantBuySellService.saveBuySellList(serviceControl, elegantBuySellList, true);
            logger.info("BuySells Saved          : " + elegantBuySellList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantBuySellList != null));
    }


}
