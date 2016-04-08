package com.dqys.persistent.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by pan on 16-4-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/spring-core.xml"})
@Rollback(true)
@Transactional(transactionManager = "transactionManager")
public abstract class BaseTest {

    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        //System.out.println("beforetransaction");
    }

    @Before
    public void setUpTestDataWithinTransaction() {
        //System.out.println("before");
    }

    @After
    public void tearDownWithinTransaction() {
        //System.out.println("after");
    }

    @AfterTransaction
    public void verifyFinalDatabaseState() {
        //System.out.println("aftertransaction");
    }
}
