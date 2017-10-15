package com.thoughtworks.taxi.core;

import com.thoughtworks.taxi.Constant.ConstantString;
import com.thoughtworks.taxi.entity.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import static com.thoughtworks.taxi.core.Calculate.WriteOffState.Already;
import static com.thoughtworks.taxi.core.Calculate.WriteOffState.New;

public class CalculateTest {
    private static SimpleDateFormat sdf = null;
    private static Calculate calculate = null;
    private static Class<Calculate> calculateClass = null;
    @Before
    public void setUp(){
        sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        calculateClass = Calculate.class;
        calculate = new Calculate();
    }
    @Test
    public void calculateCarInfo() throws Exception {

    }


    @Test
    public void testIsWriteOff() throws Exception{
        Method method = calculateClass.getDeclaredMethod("isWriteOff", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2027/01/11"), "Audi", 19500, true);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(Already, result);
    }
    @Test
    public void testIsWriteOff2() throws Exception{
        Method method = calculateClass.getDeclaredMethod("isWriteOff", Car.class);
        method.setAccessible(true);
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        Object result = method.invoke(calculate, car);
        Assert.assertEquals(New, result);
    }

    @Test
    public void testIsTimeRelated() throws Exception{

    }

    @Test
    public void testIsDistanceRelated() throws Exception{

    }
}