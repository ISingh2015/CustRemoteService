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
import com.cust.domain.service.CustomerService;
import com.cust.domain.vo.ElegantCustomer;
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
public class CustomerServiceTest {

    Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    @Autowired
    private CustomerService elegantCustomerService;
    private ServiceControl serviceControl;
    ElegantUser user;

    public CustomerServiceTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

    @Test
    public void testGetCustomerById() {
        ElegantCustomer elegantCustomer = null;
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
            servicePayload = elegantCustomerService.getCustomerById(serviceControl, user.getCompID(), custId);
            List<Object> elegantCustomerList = servicePayload.getResponseValue();
            elegantCustomer = (ElegantCustomer) elegantCustomerList.get(0);
            logger.info("Customer Found          : " + elegantCustomer.getCustName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomer != null));
    }

    @Test
    public void testGetAllCustomers() {
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
        List<ElegantCustomer> elegantCustomerList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("createDate");
        filterCriteria.setFilterCondition(">=");
        filterCriteria.setFilterFieldValue("2015/07/01 00:00:00");
//            filterCriteriaList.add(filterCriteria);

        filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("createDate");
        filterCriteria.setFilterCondition("<=");
        filterCriteria.setFilterFieldValue(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        filterCriteriaList.add(filterCriteria);

        queryCriteria.setFilterCriteria(filterCriteriaList);
        serviceControl.setQueryCriteria(queryCriteria);
        try {
            servicePayload = elegantCustomerService.getAllCustomers(serviceControl, user.getCompID());
            elegantCustomerList = (ArrayList<ElegantCustomer>) servicePayload.getResponseValue().get(0);
            logger.info("Get All Customers List Found          : " + elegantCustomerList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomerList != null));
    }

    @Test
    public void testSaveCustomersList() {
        ServicePayload servicePayload;
        ArrayList<ElegantCustomer> elegantCustomerList = null;
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
        pagination.setCurrrentPageNumber(5);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);

        try {
            servicePayload = elegantCustomerService.getAllCustomers(serviceControl, user.getCompID());
            elegantCustomerList = (ArrayList<ElegantCustomer>) servicePayload.getResponseValue().get(0);
            servicePayload = elegantCustomerService.saveCustomerList(serviceControl, elegantCustomerList);
            logger.info("Customers Saved          : " + elegantCustomerList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomerList != null));
    }

}
