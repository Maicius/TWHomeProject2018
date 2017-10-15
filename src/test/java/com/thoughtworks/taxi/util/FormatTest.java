package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.Constant.ConstantString;
import com.thoughtworks.taxi.entity.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FormatTest{
    @Before
    public void setUp(){
        System.out.println("Begin Test");
        System.out.println("=====================");
    }
    @After
    public void finish(){
        System.out.println("=====================");
        System.out.println("Finish");
    }

    @Test
    public void testFormatInput() throws ParseException {
        System.out.println("Begin to Test FormatInput");
        String input = "SubmitDate: 2030/09/01==" +
                "CAR0001|2025/04/05|Porsche|10000|F==" +
                "CAR0002|2029/10/14|Porsche|9000|F==";

        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        List<Car> carList = new ArrayList<>();
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        carList.add(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/10/14"), "Porsche", 9000, false);
        carList.add(car);
        Assert.assertEquals(carList, FormatInput.formatInput(input));
        System.out.println("FormatInput Test Finished");
    }
}