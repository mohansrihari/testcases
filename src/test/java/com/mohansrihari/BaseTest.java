package com.mohansrihari;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:entity-manger-context-test.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
public abstract class BaseTest {
	protected final Log logger = LogFactory.getLog(getClass());

}
