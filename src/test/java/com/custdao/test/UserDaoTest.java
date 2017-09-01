package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.UserDao;
import com.cust.domain.vo.ElegantHitCounter;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.domain.vo.ElegantUserActivation;
import com.cust.domain.vo.ElegantUserLogin;
import com.cust.util.CustMailManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
public class UserDaoTest {

    Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
    ElegantHitCounter elegantHitCounter;
    @Autowired
    private UserDao elegantUserDao;

    @Autowired
    private CustMailManager custMailer;

    public UserDaoTest() {
        elegantHitCounter = new ElegantHitCounter();
        elegantHitCounter.setHitID(5000);
    }

    @Test
    public void testGetAllUsers() {
        ArrayList<ElegantUser> userList = null;
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(2);
        serviceControl.setPagination(pagination);

        try {
            long compId = 6000;
            userList = elegantUserDao.getAllUsers(serviceControl, compId);
            Collections.sort(userList);
            for (ElegantUser user : userList) {
                logger.info("User " + user.getUserID() + " Access Rights List Size " + user.getElegantUserAccessList().size());
                for (ElegantUserAccess elegantUserAccess : user.getElegantUserAccessList()) {
                    logger.info("Access " + elegantUserAccess.getMenuId() + "-" + elegantUserAccess.getMenuAllowed());
                }
            }
            logger.info("Users Found : " + userList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, userList.isEmpty());
    }

//    @Test
    public void testGetUserByIdOrEmail() {
        ElegantUser user = null;
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(2);
        serviceControl.setPagination(pagination);

        try {
            String userLogin = "ISingh";
            user = elegantUserDao.getLoginByUserNameOrEmail(userLogin);
            logger.info("User Found : " + user.getUserLoginPwd());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, user == null);
    }

//    @Test
    public void testSaveUsers() {
//        ArrayList<ElegantUser> userList = null;
        ArrayList<ElegantUser> userList = new ArrayList<ElegantUser>();
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        try {
            long compId = 6000;
            userList = elegantUserDao.getAllUsers(serviceControl, compId);
//            for (ElegantUser user : userList) {
//                logger.info("User " + user.getUserID() + " Access Rights List Size " + user.getElegantUserAccessList().size() );
//                for (ElegantUserAccess elegantUserAccess : user.getElegantUserAccessList()) {
//                    logger.info("Access " + elegantUserAccess.getMenuId() + "-" + elegantUserAccess.getMenuAllowed() );
//                }
//            }
            logger.info("Users Found : " + userList.size());

//            logger.info("Users List : " + userList.size());            
//            ElegantUser user = new ElegantUser();
//            user.setCompID(compId);
//            user.setUserID(64004);
//            user.setUserLoginName("TestUser");
//            user.setUserLoginPwd("TestPassword");
//            user.setAccountType(0);
//            userList.add(user);
//            ElegantUserAccess elegantUserAccess = userList.get(1).getElegantUserAccessList().get(0);
//            elegantUserAccess.setMenuAllowed(false);
//            logger.info(elegantUserAccess.getCompID() + "-" + elegantUserAccess.getUserID() + "-"+ elegantUserAccess.getUserAccessId() + "-" + elegantUserAccess.getMenuId() +"-" + elegantUserAccess.getMenuAllowed() );
            userList = elegantUserDao.saveUserList(userList, true);
            logger.info("Users Saved : " + userList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, userList.isEmpty());
    }

//    @Test
    public void testSendEmail() {
        try {

            custMailer.sendMail("eleganinfo@gmail.com", "inderjitsanhotra@gmail.com", "","","Mail from Elegant Inventory", "Test Email for receiving password reset code<br><br><a href=http://www.elegantsoftware.in/pges/contactPage.irp/>To get in touch, Click here</a>");
            logger.info("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
//            logger.info("Mail Sender Error" + e.getMessage());
        }
    }

//    @Test
    public void testSaveUserAcctivation() {
        ArrayList<ElegantUserActivation> userList = new ArrayList<ElegantUserActivation>();
        try {
            long compId = 6000;

            ElegantUserActivation user = new ElegantUserActivation();
            user.setCompID(compId);
            user.setUserID(1000);
            userList.add(user);
            userList = elegantUserDao.saveUserActivation(userList, "inderjitsanhotra@gmail.com");
            logger.info("User Activation Saved : " + userList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, userList.isEmpty());
    }

//    @Test
    public void testSaveUserLogin() {
        ArrayList<ElegantUserLogin> userLoginList = new ArrayList<ElegantUserLogin>();
        try {
            long compId = 6000, userId = 64002;
            ElegantUserLogin user = new ElegantUserLogin();
            user.setCompID(compId);
            user.setUserID(userId);
            user.setLoginIP("192.168.0.10");
            user.setLoginDate(new Date());
            userLoginList.add(user);
            userLoginList = elegantUserDao.saveUserLogin(userLoginList);
            logger.info("User Login Saved : " + userLoginList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, userLoginList.isEmpty());
    }

//    @Test
    public void testSaveHitCounter() {
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        try {
            elegantHitCounter = elegantUserDao.getHitCount(elegantHitCounter);
            long newHitCounter = elegantHitCounter.getHitCounter() + 1;
            elegantHitCounter.setHitCounter(newHitCounter);
            elegantHitCounter = elegantUserDao.saveHitCount(elegantHitCounter);
            logger.info("Saved Hit Count: " + elegantHitCounter.getHitCounter());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, elegantHitCounter.getHitCounter() == 0);
    }

//    @Test
    public void testGetHitCounter() {
        try {
            elegantHitCounter = elegantUserDao.getHitCount(elegantHitCounter);

            logger.info("Got Hit Count: " + elegantHitCounter.getHitCounter());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, elegantHitCounter.getHitCounter() == 0);
    }

}
