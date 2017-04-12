package com.inc.xy;

import com.inc.xy.rest.controller.XYController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author caio
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class XYIncApplicationTest {
    @Autowired
    private XYController controller;
    
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNull();
    }
}
