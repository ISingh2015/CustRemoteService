package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.FilterCriteria;
import com.cust.common.Pagination;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.SalesManDao;
import com.cust.domain.vo.ElegantSalesMan;
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
public class SalesManDaoTest {

    Logger logger = LoggerFactory.getLogger(SalesManDaoTest.class);
    ElegantUser user;
    @Autowired
    private SalesManDao elegantSalesManDao;

    public SalesManDaoTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

//    @Test
    public void testGetSalesManById() {
        ElegantSalesMan salesMan = null;
        try {
            int salesManId = 500;
            salesMan = elegantSalesManDao.getSalesManById(user.getCompID(),salesManId);
            logger.info("SalesMan Found          : " + salesMan.getSalesManName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (salesMan != null));
    }

//    @Test
    public void testGetAllSalesMan() {
        ArrayList<ElegantSalesMan> salesManList = null;
        ServiceControl serviceControl = new ServiceControl();
        QueryCriteria queryCriteria = new QueryCriteria();

        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("salesManName");
        filterCriteria.setFilterCondition("like");
        filterCriteria.setFilterFieldValue("t%");
        filterCriteriaList.add(filterCriteria);

        queryCriteria.setFilterCriteria(filterCriteriaList);
        serviceControl.setQueryCriteria(queryCriteria);
        try {
            salesManList = elegantSalesManDao.getAllSalesMan(serviceControl,user.getCompID());
            logger.info("SalesMan Found : " + salesManList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, salesManList.isEmpty());
    }

    @Test
    public void testSaveSalesMan() {
        ArrayList<ElegantSalesMan> salesManList = null;
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        try {
            salesManList = elegantSalesManDao.getAllSalesMan(serviceControl,user.getCompID());
            salesManList = elegantSalesManDao.saveSalesManList(salesManList);
            logger.info("SalesMan Saved : " + salesManList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, salesManList.isEmpty());
    }

}
