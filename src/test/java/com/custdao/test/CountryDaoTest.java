package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.FilterCriteria;
import com.cust.common.Pagination;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.CountryDao;
import com.cust.domain.vo.ElegantCountry;
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
public class CountryDaoTest {

    Logger logger = LoggerFactory.getLogger(CountryDaoTest.class);
    @Autowired
    private CountryDao elegantCountryDao;

    public CountryDaoTest() {
    }

//    @Test
    public void testGetCountryById() {
        ElegantCountry country = null;
        try {
            int countryId = 500;
            country = elegantCountryDao.getCountryById(countryId);
            logger.info("Country Found          : " + country.getCountryName());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (country != null));
    }

    @Test
    public void testGetAllCountries() {
        ArrayList<ElegantCountry> countryList = null;
        ServiceControl serviceControl = new ServiceControl();
        QueryCriteria queryCriteria = new QueryCriteria();

        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setFilterFieldName("countryName");
        filterCriteria.setFilterCondition("like");
        filterCriteria.setFilterFieldValue("a%");
        filterCriteriaList.add(filterCriteria);

        queryCriteria.setFilterCriteria(filterCriteriaList);
        serviceControl.setQueryCriteria(queryCriteria);
        try {
            countryList = elegantCountryDao.getAllCountries(serviceControl);
            logger.info("Country Found : " + countryList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, countryList.isEmpty());
    }

//    @Test
    public void testSaveCountry() {
        ArrayList<ElegantCountry> countryList = new ArrayList<> ();
        try {
            ElegantCountry country = new ElegantCountry();
            country.setCountryID(500);
            country.setCountryCd("IN");
            country.setCountryName("Test Country");
            countryList.add(country);
            countryList = elegantCountryDao.saveCountryList(countryList);
            logger.info("Country Saved : " + countryList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, countryList.isEmpty());
    }

//    @Test
    public void testDeleteCountry() {
        ArrayList<ElegantCountry> countryList = new ArrayList<> ();
        ElegantCountry country = null;
        boolean deleted=false;
        try {
            int countryId = 500;
            country = elegantCountryDao.getCountryById(countryId);
            countryList.add(country);
            deleted = elegantCountryDao.deleteCountryList(countryList);
            logger.info("Country Deleted : " + deleted);
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, deleted);
    }

}
