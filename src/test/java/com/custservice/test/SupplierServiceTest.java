package com.custservice.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.service.SupplierService;
import com.cust.domain.vo.ElegantSupplier;
import com.cust.domain.vo.ElegantUser;
import java.util.ArrayList;
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
public class SupplierServiceTest {

    Logger logger = LoggerFactory.getLogger(SupplierServiceTest.class);
    private ElegantUser user;
    private ServiceControl serviceControl;
    @Autowired
    private SupplierService elegantSupplierService;

    public SupplierServiceTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

    @Test
    public void testGetSupplierById() {
        ElegantSupplier elegantSupplier = null;
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
            int supId = 1;
            servicePayload = elegantSupplierService.getSupplierById(serviceControl, user.getCompID(), supId);
            elegantSupplier = (ElegantSupplier) servicePayload.getResponseValue().get(0);
            logger.info("Supplier Found : " + elegantSupplier.getSupID());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantSupplier != null));
    }

    @Test
    public void testGetAllSuppliers() {
        ArrayList<ElegantSupplier> supplierList = null;
        ServiceControl serviceControl = new ServiceControl();
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
            servicePayload = elegantSupplierService.getAllSuppliers(serviceControl, user.getCompID());
            List<Object> elegantCustomerList = servicePayload.getResponseValue();
            supplierList = (ArrayList<ElegantSupplier>) elegantCustomerList.get(0);

            logger.info("Suppliers Found : " + supplierList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, supplierList.isEmpty());
    }

    @Test
    public void testSaveSupplier() {
        ArrayList<ElegantSupplier> supplierList = null;
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
        try {
            servicePayload = elegantSupplierService.getAllSuppliers(serviceControl, user.getCompID());
            supplierList = (ArrayList<ElegantSupplier>) servicePayload.getResponseValue().get(0);
            servicePayload = elegantSupplierService.saveSupplierList(serviceControl, supplierList);
            logger.info("Suppliers Saved : " + supplierList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, supplierList.isEmpty());
    }

}
