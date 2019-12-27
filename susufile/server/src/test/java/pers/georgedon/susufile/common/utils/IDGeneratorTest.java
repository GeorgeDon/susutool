package pers.georgedon.susufile.common.utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

class IDGeneratorTest {


    @Test
    public void test01() {
        System.out.println(IDGenerator.createId().getBytes(Charset.forName("UTF-8")).length);
    }

}