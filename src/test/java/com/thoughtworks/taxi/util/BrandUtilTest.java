package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.Constant.ConstantProperties;
import com.thoughtworks.taxi.entity.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * BrandUtil的测试类
 */
public class BrandUtilTest {

    private static SimpleDateFormat sdf = null;
    private List<Car> carList = null;
    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat(ConstantProperties.SIMPLE_DATE_FORMAT);
        carList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCountBrand() throws Exception {
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        carList.add(car);
        Car car1 = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Porsche", 100001, true);
        carList.add(car1);
        Car car2 = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        carList.add(car2);
        Car car3 = new Car("CAR0007", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        carList.add(car3);
        Car car4 = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Porsche", 100001, true);
        carList.add(car4);
        Car car5 = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        carList.add(car5);
        Car car6 = new Car("CAR0009", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        carList.add(car6);
        Car car7 = new Car("CAR0008", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        carList.add(car7);

        HashMap<String, List<Car>> map = BrandUtil.countBrand(carList);
        HashMap<String, List<Car>> expectedMap = new HashMap<>();
        List<Car> Audi= new ArrayList<>();
        List<Car> Porsche= new ArrayList<>();
        List<Car> BYD= new ArrayList<>();
        List<Car> Ford= new ArrayList<>();
        Audi.add(car);
        Audi.add(car7);
        expectedMap.put("Audi", Audi);
        Porsche.add(car1);
        Porsche.add(car4);
        expectedMap.put("Porsche", Porsche);
        BYD.add(car2);
        BYD.add(car5);
        expectedMap.put("BYD", BYD);
        Ford.add(car3);
        Ford.add(car6);
        expectedMap.put("Ford",Ford);

        Assert.assertEquals(expectedMap.get("Audi"), map.get("Audi"));
        Assert.assertEquals(expectedMap.get("BYD"), map.get("BYD"));
        Assert.assertEquals(expectedMap.get("Porsche"), map.get("Porsche"));
        Assert.assertEquals(expectedMap.get("Ford"), map.get("Ford"));
    }
}