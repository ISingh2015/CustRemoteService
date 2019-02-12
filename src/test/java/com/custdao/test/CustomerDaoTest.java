package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.CustomerDao;
import com.cust.domain.vo.ElegantCustomer;
import com.cust.domain.vo.ElegantUser;
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
@DatabaseSetup({"classpath:CustTestData.xml"})

public class CustomerDaoTest {

    Logger logger = LoggerFactory.getLogger(CustomerDaoTest.class);
    ElegantUser user;
    @Autowired
    private CustomerDao elegantCustomerDao;

    public CustomerDaoTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

    @Test
    public void testGetCustomerById() {
        ElegantCustomer elegantCustomer = null;
        try {
            int custId = 001;
            elegantCustomer = elegantCustomerDao.getCustomerById(user.getCompID(),custId);
            logger.info("Customer Found          : " + elegantCustomer.getCustName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomer != null));
    }

    @Test
    public void testGetAllCustomers() {
        List<ElegantCustomer> elegantCustomerList = null;
        ServiceControl serviceControl = new ServiceControl();
        try {

            elegantCustomerList = elegantCustomerDao.getAllCustomers(serviceControl, user.getCompID());
            logger.info("Customers Found          : " + elegantCustomerList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomerList != null));
    }

    @Test
    public void testSaveCustomers() {
        ArrayList<ElegantCustomer> elegantCustomerList = null;
        ServiceControl serviceControl = new ServiceControl();

        try {
            elegantCustomerList = (ArrayList<ElegantCustomer>)elegantCustomerDao.getAllCustomers(serviceControl,user.getCompID());
            if (!elegantCustomerList.isEmpty()) {
                elegantCustomerList = elegantCustomerDao.saveCustomerList(elegantCustomerList);
            logger.info("Customers Saved          : " + elegantCustomerList.size());                
            }
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (elegantCustomerList != null));
    }

}
