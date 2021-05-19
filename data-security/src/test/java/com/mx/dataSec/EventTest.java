package com.mx.dataSec;

import com.mx.dataSec.event.MyHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventTest {
    private static Logger logger = LoggerFactory.getLogger(EventTest.class);

    @Autowired
    private MyHandler myHandler;


    @Test
    public void publishOrder() {
        myHandler.dropAndLogPb(409,"conflict");
    }
}
