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
import com.cust.domain.service.ProductService;
import com.cust.domain.vo.ElegantProduct;
import com.cust.domain.vo.ElegantUser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ProductServiceTest {

    Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);
    @Autowired
    private ProductService elegantProductService;
    private ServiceControl serviceControl;
    ElegantUser user;

    public ProductServiceTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

//    @Test
    public void testGetProductById() {
        ElegantProduct elegantProduct = null;
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
            servicePayload = elegantProductService.getProductById(serviceControl, user.getCompID(), custId);
            List<Object> elegantProductList = servicePayload.getResponseValue();
            elegantProduct = (ElegantProduct) elegantProductList.get(0);
            logger.info("Product Found          : " + elegantProduct.getProdName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantProduct != null));
    }

//    @Test
    public void testGetAllProducts() {
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
        ArrayList<ElegantProduct> elegantProductList = null;
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
            servicePayload = elegantProductService.getAllProducts(serviceControl, user.getCompID());
            elegantProductList = (ArrayList<ElegantProduct>) servicePayload.getResponseValue().get(0);
            logger.info("Get All Products List Found          : " + elegantProductList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantProductList != null));
    }

//    @Test
    public void testSaveProductsList() {
        ServicePayload servicePayload;
        ArrayList<ElegantProduct> elegantProductList = null;
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
            servicePayload = elegantProductService.getAllProducts(serviceControl, user.getCompID());
            elegantProductList = (ArrayList<ElegantProduct>) servicePayload.getResponseValue().get(0);
            servicePayload = elegantProductService.saveProductList(serviceControl, elegantProductList);
            logger.info("Products Saved          : " + elegantProductList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantProductList != null));
    }

    @Test
    public void testGetProductStockById() {
        Double elegantBuySellStock = null;
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
            int prodId = 36;
            long currBill=7;
           
            servicePayload = elegantProductService.getProductStockById(serviceControl, 6000, prodId,currBill);
            List<Object> elegantStockList = servicePayload.getResponseValue();
            elegantBuySellStock = (Double) elegantStockList.get(0);
            logger.info("BuySell product stock Found          : " + elegantBuySellStock);
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantBuySellStock != null));
    }

}
