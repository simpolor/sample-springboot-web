package io.simpolor.upload;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class Laboratory {

    @Test
    public void test(){
        String random = RandomStringUtils.random(32);

        System.out.println(random);
    }
}
