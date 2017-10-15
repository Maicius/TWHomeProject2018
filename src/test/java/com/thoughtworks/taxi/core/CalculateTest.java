package com.thoughtworks.taxi.core;

import com.thoughtworks.taxi.Constant.ConstantProperties;
import com.thoughtworks.taxi.entity.Car;
import com.thoughtworks.taxi.entity.Reminder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.taxi.core.Calculate.WriteOffState.*;

public class CalculateTest {
    private static SimpleDateFormat sdf = null;
    private static Calculate calculate = null;
    private static Class<Calculate> calculateClass = null;

    @Before
    public void setUp() {
        sdf = new SimpleDateFormat(ConstantProperties.SIMPLE_DATE_FORMAT);
        calculateClass = Calculate.class;
        calculate = new Calculate();
    }

    /**
     * 主测试
     *
     * @throws Exception
     */
    @Test
    public void testCalculateCarInfo() throws Exception {
        List<Car> carList = new ArrayList<>();
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        carList.add(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/10/14"), "Porsche", 9000, false);
        carList.add(car);
        car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2026/08/17"), "Porsche", 13000, false);
        carList.add(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2027/11/01"), "BYD", 23000, true);
        carList.add(car);
        car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 19500, true);
        carList.add(car);
        car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 10001, true);
        carList.add(car);
        car = new Car("CAR0007", sdf.parse("2030/09/01"), sdf.parse("2028/04/19"), "Ford", 9800, false);
        carList.add(car);
        car = new Car("CAR0008", sdf.parse("2030/09/01"), sdf.parse("2027/07/10"), "Porsche", 15000, true);
        carList.add(car);
        car = new Car("CAR0009", sdf.parse("2030/09/01"), sdf.parse("2024/10/22"), "Ford", 90300, false);
        carList.add(car);
        Reminder actual = calculate.calculateCarInfo(carList);

        //按照车辆品牌首字母顺序排序加载各个list
        Reminder expected = new Reminder();
        car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 10001, true);
        expected.setTimeRelated(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/10/14"), "Porsche", 9000, false);
        expected.setTimeRelated(car);
        car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 19500, true);
        expected.setDistanceRelated(car);
        car = new Car("CAR0007", sdf.parse("2030/09/01"), sdf.parse("2028/04/19"), "Ford", 9800, false);
        expected.setDistanceRelated(car);
        car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        expected.setDistanceRelated(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2027/11/01"), "BYD", 23000, true);
        expected.setWriteOff(car);
        car = new Car("CAR0009", sdf.parse("2030/09/01"), sdf.parse("2024/10/22"), "Ford", 90300, false);
        expected.setWriteOff(car);

        Assert.assertEquals(expected.getTimeRelated(), actual.getTimeRelated());
        Assert.assertEquals(expected.getWriteOff(), actual.getWriteOff());
        Assert.assertEquals(expected.getDistanceRelated(), actual.getDistanceRelated());
    }

    /**
     * 测试无报废信息的车辆
     *
     * @throws Exception
     */
    @Test
    public void testIsWriteOff() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isWriteOff", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 19500, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(New, result);
    }

    /**
     * 测试快要报废的车辆
     *
     * @throws Exception
     */
    @Test
    public void testIsWriteOff2() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isWriteOff", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2027/11/01"), "BYD", 23000, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(Nearly, result);
    }

    /**
     * 测试已经报废的车辆
     *
     * @throws Exception
     */
    @Test
    public void testIsWriteOff3() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isWriteOff", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0008", sdf.parse("2030/09/01"), sdf.parse("2027/07/10"), "Porsche", 15000, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(Already, result);
    }

    @Test
    public void testIsTimeRelated() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isTimeRelated", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 10001, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsTimeRelated2() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isTimeRelated", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2026/08/17"), "Porsche", 13000, false);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsDistanceRelated() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isDistanceRelated", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 10001, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsDistanceRelated2() throws Exception {
        Method method = calculateClass.getDeclaredMethod("isDistanceRelated", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(true, result);
    }
}