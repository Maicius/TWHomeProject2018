package com.thoughtworks.taxi.main;

import org.junit.Before;
import org.junit.Test;

/**
 * 主程序测试类
 */
public class handleIOTest {

    @Before
    public void setUp(){
    }

    @Test
    public void testHandleIO1(){
        String[] args = {"testCase1.txt"};
        handleIO.main(args);
    }
    @Test
    public void testHandleIO2(){
        String[] args = {"testCase2.txt"};
        handleIO.main(args);
    }
}