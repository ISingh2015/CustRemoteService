package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.ProductDao;
import com.cust.domain.dao.ProductDao;
import com.cust.domain.vo.ElegantProduct;
import com.cust.domain.vo.ElegantUser;
import java.util.ArrayList;
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
public class ProductDaoTest {

    Logger logger = LoggerFactory.getLogger(ProductDaoTest.class);
    ElegantUser user;
    @Autowired
    private ProductDao elegantProductDao;

    public ProductDaoTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

//    @Test
    public void testSaveProduct() {
        ArrayList<ElegantProduct> supplierList = new ArrayList<ElegantProduct>();
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        try {
            ElegantProduct product = new ElegantProduct();
            product.setCompID(user.getCompID());
            product.setUserID(user.getUserID());
            product.setCatNo("00100");
            product.setSubCatNo("00010");
            
//            product.setSupID(-1);
//            product.setCreateDate(new Date());
            product.setProdName("Test Product");
            product.setMakeFlag(0);
            product.setStyle(0);
            product.setActive(0);
            supplierList.add(product);
//            productList = elegantProductDao.getAllProducts(serviceControl, user.getCompID(), user.getUserID());
            supplierList = elegantProductDao.saveProductList(supplierList);
            logger.info("Products Saved : " + supplierList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, supplierList.isEmpty());
    }

//    @Test
    public void testGetProductById() {
        ElegantProduct product = null;
        try {
            int supId = 1;
            product = elegantProductDao.getProductById(user.getCompID(),supId);
            logger.info("Product Found : " + product.getProdID());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (product != null));
    }

    @Test
    public void testGetAllProducts() {
        ArrayList<ElegantProduct> productList = null;
        ServiceControl serviceControl = new ServiceControl();

        try {
            productList = elegantProductDao.getAllProducts(serviceControl, user.getCompID());
            logger.info("Products Found : " + productList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, productList.isEmpty());
    }

}
