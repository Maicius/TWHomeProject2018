package com.thoughtworks.taxi.entity;

import com.thoughtworks.taxi.Constant.ConstantProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * 实体类Car的测试类
 * 主要测试重载的equals和compareTo函数
 */
public class CarTest {

    private static SimpleDateFormat sdf = null;
    @Before
    public void setUp(){
        sdf = new SimpleDateFormat(ConstantProperties.SIMPLE_DATE_FORMAT);
    }
    @Test
    public void equals() throws Exception {
        Car car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        Car car1 = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        Car car2 = new  Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/11"), "BYD", 100001, true);
        Car car3 = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, false);

        Assert.assertEquals(true, car.equals(car1));
        Assert.assertEquals(false, car1.equals(car2));
        Assert.assertEquals(false, car1.equals(car3));
    }

    @Test
    public void compareTo() throws Exception {
        Car car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        Car car1 = new  Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/11"), "BYD", 100001, true);
        Car car2 = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        Car car3 = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        int compare = car.compareTo(car1);
        Assert.assertEquals(-1, compare);
        compare = car.compareTo(car3);
        Assert.assertEquals(0, compare);
        compare = car2.compareTo(car);
        Assert.assertEquals(5, compare);
    }

}