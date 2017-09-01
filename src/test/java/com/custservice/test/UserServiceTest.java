package com.custservice.test;

import com.cust.common.ApplicationException;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.service.UserService;
import com.cust.domain.vo.ElegantHitCounter;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserLogin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
public class UserServiceTest {

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    private UserService elegantUserService;
    private ServiceControl serviceControl;
    ElegantHitCounter elegantHitCounter;

    public UserServiceTest() {
        elegantHitCounter = new ElegantHitCounter();
        elegantHitCounter.setHitID(5000);
    }

//    @Test
    public void testGetAllUsers() {
        ServicePayload servicePayload;
        ArrayList<ElegantUser> elegantUserListFromLoad = null;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);

        try {
            servicePayload = elegantUserService.getAllUsers(serviceControl, 6000);
            List<Object> elegantUserList = servicePayload.getResponseValue();
            elegantUserListFromLoad = (ArrayList<ElegantUser>) elegantUserList.get(0);
            logger.info("User List Found : " + elegantUserListFromLoad.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantUserListFromLoad != null));
    }

//    @Test
    public void testSaveUserLogin() {
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId(UUID.randomUUID().toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        ArrayList<ElegantUserLogin> elegantUserLoginList = new ArrayList<ElegantUserLogin>();
        ElegantUserLogin elegantUser = null;
        try {
            long compId = 6000, userId = 64002;
            ElegantUserLogin user = new ElegantUserLogin();
            user.setCompID(compId);
            user.setUserID(userId);
            user.setLoginIP("192.168.0.10");
            user.setLoginDate(new Date());
            elegantUserLoginList.add(user);

            servicePayload = elegantUserService.saveUserLoginList(serviceControl, elegantUserLoginList);
            elegantUserLoginList = (ArrayList<ElegantUserLogin>) servicePayload.getResponseValue().get(0);
            elegantUser = elegantUserLoginList.get(0);
            logger.info("User Login Found          : " + elegantUser.getLoginIP());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantUser != null));
    }

//    @Test
    public void testGetUserByCriteria() {
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId(UUID.randomUUID().toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        QueryCriteria queryCriteria = new QueryCriteria();

        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("mobileNo");
        filterCriteria.setFilterCondition("=");
        filterCriteria.setFilterFieldValue("11111111");
        filterCriteriaList.add(filterCriteria);

        queryCriteria.setFilterCriteria(filterCriteriaList);
        serviceControl.setQueryCriteria(queryCriteria);

        ElegantUser elegantUser = null;
        try {
            servicePayload = elegantUserService.getUserByCriteria(serviceControl);
            List<Object> elegantUserList = servicePayload.getResponseValue();
            elegantUser = ((ArrayList<ElegantUser>) elegantUserList.get(0)).get(0);
            logger.info("User Found          : " + elegantUser.getUserName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantUser != null));
    }

    @Test
    public void testGetHitCount() {
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId(UUID.randomUUID().toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        QueryCriteria queryCriteria = new QueryCriteria();

        try {
            servicePayload = elegantUserService.getHitCount(serviceControl, elegantHitCounter);
            List<Object> elegantHitCounterList = servicePayload.getResponseValue();
            elegantHitCounter = (ElegantHitCounter) elegantHitCounterList.get(0);
            logger.info("Hit Count Found          : " + elegantHitCounter.getHitCounter());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantHitCounter.getHitCounter() != 0));
    }

    @Test
    public void testSaveHitCount() {
        ServicePayload servicePayload;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setSessionId(UUID.randomUUID().toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);
        QueryCriteria queryCriteria = new QueryCriteria();

        try {
            servicePayload = elegantUserService.getHitCount(serviceControl, elegantHitCounter);
            List<Object> elegantHitCounterList = servicePayload.getResponseValue();
            elegantHitCounter = (ElegantHitCounter) elegantHitCounterList.get(0);
            long newHitCount = elegantHitCounter.getHitCounter() + 1;
            servicePayload = elegantUserService.saveHitCount(serviceControl, elegantHitCounter);
            logger.info("Save Hit Count : " + elegantHitCounter.getHitCounter());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantHitCounter.getHitCounter() != 0));
    }

//    @Test
    public void testGetUserByLoginOrEmail() {
        ServicePayload servicePayload;
        ArrayList<ElegantUser> elegantUserListToSave= new ArrayList<> ();
        ElegantUser elegantUser = null;
        serviceControl = new ServiceControl();
        DBInfo dbInfo = new DBInfo();
        dbInfo.setDbName("custService");

        SessionInfo sessionInfo = new SessionInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Inderjit");
        userInfo.setLoginId("ISingh");
        sessionInfo.setUserInfo(userInfo);
        serviceControl.setDbInfo(dbInfo);
        serviceControl.setSessionInfo(sessionInfo);

        try {
            String userName="ISingh";
            servicePayload = elegantUserService.getLoginByUserNameOrEmail(serviceControl,userName);
            List<Object> elegantUserList = servicePayload.getResponseValue();
            elegantUser = (ElegantUser) elegantUserList.get(0);
            logger.info("User Found : " + elegantUser.getUserLoginPwd());
            elegantUserListToSave.add(elegantUser);
            elegantUserService.saveUserGenPassword(serviceControl, elegantUserListToSave);
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, (elegantUser==null));
    }

}
