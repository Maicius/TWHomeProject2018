package com.thoughtworks.taxi.main;

import com.thoughtworks.taxi.core.CalculateTest;
import com.thoughtworks.taxi.entity.CarTest;
import com.thoughtworks.taxi.entity.ReminderTest;
import com.thoughtworks.taxi.util.BrandUtilTest;
import com.thoughtworks.taxi.util.FormatTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试集
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({handleIOTest.class, CalculateTest.class, CarTest.class, ReminderTest.class, BrandUtilTest.class, FormatTest.class})
public class TestSuit {

}
