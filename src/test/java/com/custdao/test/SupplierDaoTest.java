package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.SupplierDao;
import com.cust.domain.vo.ElegantSupplier;
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
public class SupplierDaoTest {

    Logger logger = LoggerFactory.getLogger(SupplierDaoTest.class);
    ElegantUser user;
    @Autowired
    private SupplierDao elegantSupplierDao;

    public SupplierDaoTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

    @Test
    public void testSaveSupplier() {
        ArrayList<ElegantSupplier> supplierList = new ArrayList<ElegantSupplier>();
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        try {
            ElegantSupplier supplier = new ElegantSupplier();
            supplier.setCompID(user.getCompID());
            supplier.setUserID(user.getUserID());            
//            supplier.setSupID(-1);
//            supplier.setCreateDate(new Date());
            supplier.setSupName("Test Supplier");
            supplier.setFrozen(0);
            supplierList.add(supplier);
//            supplierList = elegantSupplierDao.getAllSuppliers(serviceControl, user.getCompID(), user.getUserID());
            supplierList = elegantSupplierDao.saveSupplierList(supplierList);
            logger.info("Suppliers Saved : " + supplierList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, supplierList.isEmpty());
    }

    @Test
    public void testGetSupplierById() {
        ElegantSupplier supplier = null;
        try {
            int supId = 1;
            supplier = elegantSupplierDao.getSupplierById(user.getCompID(),supId);
            logger.info("Supplier Found : " + supplier.getSupID());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (supplier != null));
    }

    @Test
    public void testGetAllSuppliers() {
        ArrayList<ElegantSupplier> supplierList = null;
        ServiceControl serviceControl = new ServiceControl();

        try {
            supplierList = elegantSupplierDao.getAllSuppliers(serviceControl, user.getCompID());
            logger.info("Suppliers Found : " + supplierList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, supplierList.isEmpty());
    }

}
