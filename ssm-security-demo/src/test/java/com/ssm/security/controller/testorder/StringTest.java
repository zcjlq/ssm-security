package com.ssm.security.controller.testorder;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 贾令强
 * @since 2018-12-01 13:55
 */
public class StringTest {
    private static final Logger log = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void test1() {
        String sort = "text,memo";
        String order = "asc,desc";
        if (StringUtils.isNotBlank(sort)) {
            String[] sorts = sort.split(",");
            String[] orders = order.split(",");
            StringBuilder sq = new StringBuilder("order by t.");
            for (int i = 0; i < sorts.length; i++) {
                sq.append(sorts[i]).append(" ").append(orders[i]).append(",");
            }
            sq.deleteCharAt(sq.length() - 1);
            log.info(sq.toString());
        }
    }
}
