package com.custdao.test;

import com.cust.common.ApplicationException;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.domain.dao.BuySellDao;
import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.vo.ElegantBuySellConsolidation;
import com.cust.domain.vo.ElegantBuySellDetails;
import com.cust.domain.vo.ElegantMailList;
import com.cust.domain.vo.ElegantMarketMail;
import com.cust.domain.vo.ElegantUser;
import com.cust.util.CustMailManager;
import java.util.ArrayList;
import java.util.Calendar;
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
public class BuySellDaoTest {

    Logger logger = LoggerFactory.getLogger(BuySellDaoTest.class);
    ElegantUser user;

    @Autowired
    private CustMailManager custMailer;
    @Autowired
    private BuySellDao elegantBuySellDao;

    public BuySellDaoTest() {
        user = new ElegantUser();
        user.setCompID(6000);
        user.setUserID(64002);
    }

//    @Test
    public void testSaveBuySell() {
        ArrayList<ElegantBuySell> buySellList = new ArrayList<ElegantBuySell>();
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);
        ElegantBuySell buySell = null;
        try {
            int billId = 1;
            buySell = elegantBuySellDao.getBuySellById(user.getCompID(), billId);
            buySellList.add(buySell);
            buySellList = elegantBuySellDao.saveBuySellList(buySellList, true);
            logger.info("Buy sell Saved : " + buySellList.size());
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, buySellList.isEmpty());
    }

    @Test
    public void testGetBuySellById() {
        ElegantBuySell buySell = null;
        try {
            int billId = 1;
            buySell = elegantBuySellDao.getBuySellById(user.getCompID(), billId);
            logger.info("Buy Sell Found by Id: " + buySell.getBillNo());
            for (ElegantBuySellDetails elegantBuySellDet : buySell.getBuySellDetailsList()) {
                logger.info("Details Items " + elegantBuySellDet.getBillID() + " Srl Found " + elegantBuySellDet.getSrl() + " product ID " + elegantBuySellDet.getProductId() + " product Name " + elegantBuySellDet.getProductName());
            }
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, (buySell != null));
    }

//    @Test
    public void testGetAllBuySell() {
        ArrayList<ElegantBuySell> buySellList = null;
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);

        try {
            buySellList = elegantBuySellDao.getAllBuySell(serviceControl, user.getCompID(), false);

            for (ElegantBuySell buySell : buySellList) {
                logger.info("Buy Sell Found All : " + buySell.getBillNo());
                if (buySell.getBuySellDetailsList() != null) {
                    for (ElegantBuySellDetails elegantBuySellDet : buySell.getBuySellDetailsList()) {
                        logger.info("Details Items " + elegantBuySellDet.getBillID() + " Srl Found " + elegantBuySellDet.getSrl() + " product ID " + elegantBuySellDet.getProductId() + " product Name " + elegantBuySellDet.getProductName());
                    }
                }
            }
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, buySellList.isEmpty());
    }

//   @Test
    public void testGetAllBuySellForReport() {
        ArrayList<ElegantBuySellConsolidation> buySellList = null;
        ServiceControl serviceControl = new ServiceControl();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date toDt = calendar.getTime();
        calendar.add(Calendar.DATE, -550);
        Date frmDt = calendar.getTime();
        System.out.println(toDt.toString() + " - " + frmDt.toString());
        try {
            buySellList = elegantBuySellDao.getAllBuySellForReport(serviceControl, user.getCompID(), 0, 0, "", frmDt, toDt, "Order");
            for (ElegantBuySellConsolidation buySell : buySellList) {
                System.out.println("Stored Proc --> " + buySell.getBuyersellerName());
            }
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(false, buySellList.isEmpty());
    }

//    @Test
    public void testDeleteBuySell() {
        ArrayList<ElegantBuySell> buySellList = new ArrayList<ElegantBuySell>();
        ElegantBuySell buySell = null;
        boolean deleted = false;
        try {
            int billId = 7;
            buySell = elegantBuySellDao.getBuySellById(user.getCompID(), billId);
            buySellList.add(buySell);
            deleted = elegantBuySellDao.deleteBuySellList(buySellList);
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
        }
        Assert.assertEquals(true, !deleted);
    }

//    @Test
    public void testCustEmailList() {
        ArrayList<ElegantMailList> buySellMailList = null;
        ServiceControl serviceControl = new ServiceControl();
        Pagination pagination = new Pagination();
        pagination.setCurrrentPageNumber(1);
        pagination.setMaxPageSize(10);
        serviceControl.setPagination(pagination);

        try {
            buySellMailList = elegantBuySellDao.getGlobalMailingList(serviceControl);
            logger.info("Got Email address successfully " + buySellMailList.size());
            if (!buySellMailList.isEmpty()) {
                for (ElegantMailList elegantMailList : buySellMailList) {
                    custMailer.sendMail("eleganinfo@gmail.com", "inderjitsanhotra@gmail.com","","", "Mail from Elegant Inventory", "Test Email for receiving password reset code:");
                    logger.info("Sending Email to address " + elegantMailList.getEmailAddress());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
//            logger.info("Mail Sender Error" + e.getMessage());
        }

        Assert.assertEquals(
                true, buySellMailList != null);
    }

//    @Test
//    public void testSaveCustEmailList() {
//        ArrayList<ElegantMailList> buySellMailList = null;
//        ServiceControl serviceControl = new ServiceControl();
//        Pagination pagination = new Pagination();
//        pagination.setCurrrentPageNumber(1);
//        pagination.setMaxPageSize(10);
//        serviceControl.setPagination(pagination);
//        ArrayList<ElegantMarketMail> elegantMarketMailList = new ArrayList<>();
//        try {
//            buySellMailList = elegantBuySellDao.getGlobalMailingList(serviceControl);
//            logger.info("Got Email address successfully " + buySellMailList.size());
//            if (!buySellMailList.isEmpty()) {
//                ElegantMarketMail elegantMarketMail=null;
//                for (ElegantMailList elegantMail : buySellMailList) {
//                    elegantMarketMail = new ElegantMarketMail ();
//                    elegantMarketMail.setEmailId(elegantMail.getEmailId());
//                    elegantMarketMail.setEmailAddress(elegantMail.getEmailAddress());
//                    elegantMarketMail.setEmailMessage("");
//                    elegantMarketMailList.add(elegantMarketMail);
//                }
//                elegantMarketMailList = elegantBuySellDao.saveGlobalMailingList(serviceControl, elegantMarketMailList);
//            }
//            logger.info("Save Email address successfully " + elegantMarketMailList.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Assert.assertEquals(true, !elegantMarketMailList.isEmpty());
//    }
//    @Test
    public void testSaveCustEmailList() {
        ServiceControl serviceControl = new ServiceControl();
        ArrayList<ElegantMarketMail> elegantMarketMailList = new ArrayList<>();
        ElegantMarketMail elegantMarketMail = null;
        try {
            elegantMarketMail = new ElegantMarketMail();
            elegantMarketMail.setEmailToAddress("inderjitsanhotra@gmail.com");
            elegantMarketMail.setEmailCcAddress("inderjitsanhotra@gmail.com");
            elegantMarketMail.setEmailBccAddress("inderjitsanhotra@gmail.com");            
            elegantMarketMail.setEmailSubject("Test Subject");
            elegantMarketMail.setEmailMessage("Test Message");
            elegantMarketMailList.add(elegantMarketMail);
            elegantMarketMailList = elegantBuySellDao.saveGlobalMailingList(serviceControl, elegantMarketMailList);
            logger.info("Save Email address successfully " + elegantMarketMailList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(
                true, !elegantMarketMailList.isEmpty());
    }

}
