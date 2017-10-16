package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.Constant.ConstantProperties;
import com.thoughtworks.taxi.entity.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 格式化输入FormatInput的测试类
 */
public class FormatTest {
    private static FormatInput formatInput = null;
    private static Class<FormatInput> formatInputClass = null;
    SimpleDateFormat sdf = null;

    @Before
    public void setUp() {
        formatInput = new FormatInput();
        formatInputClass = FormatInput.class;
        sdf = new SimpleDateFormat(ConstantProperties.SIMPLE_DATE_FORMAT);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFormatInput() throws ParseException {
        String input = "SubmitDate: 2030/09/01==" +
                "CAR0001|2025/04/05|Porsche|10000|F==" +
                "CAR0002|2029/10/14|Porsche|9000|F==";

        List<Car> carList = new ArrayList<>();
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        carList.add(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/10/14"), "Porsche", 9000, false);
        carList.add(car);
        Assert.assertEquals(carList, FormatInput.formatInput(input));
    }

    @Test
    public void testGetSubmitDate() throws Exception {
        String submit = "SubmitDate: 2030/09/01";
        Method method = formatInputClass.getDeclaredMethod("getSubmitDate", String.class);
        method.setAccessible(true);
        Date result = (Date) method.invoke(formatInput, submit);
        Date expected = sdf.parse("2030/09/01");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetCarInfos() throws Exception {
        Method method = formatInputClass.getDeclaredMethod("getCarInfos", String.class);
        method.setAccessible(true);
        String input = "SubmitDate: 2030/09/01==" +
                "CAR0001|2025/04/05|Porsche|10000|F==" +
                "CAR0002|2029/10/14|Porsche|9000|F==";

        List<Car> carList = new ArrayList<>();
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2025/04/05"), "Porsche", 10000, false);
        carList.add(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/10/14"), "Porsche", 9000, false);
        carList.add(car);
        Object actualList = method.invoke(formatInput, input);
        Assert.assertEquals(carList, actualList);
    }

    @Test
    public void testGetTorF() throws Exception {
        Method method = formatInputClass.getDeclaredMethod("getTorF", String.class);
        method.setAccessible(true);
        String t = "T";
        Object actual = method.invoke(formatInput, t);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void testGetTorF2() throws Exception {
        Method method = formatInputClass.getDeclaredMethod("getTorF", String.class);
        method.setAccessible(true);
        String f = "F";
        Object actual = method.invoke(formatInput, f);
        Assert.assertEquals(false, actual);
    }

    @Test
    public void testReadInput() throws Exception {
        String fileName = ConstantProperties.INPUT_FILE_NAME;
        String expected = "SubmitDate: 2030/09/01==" +
                "CAR0001|2025/04/05|Porsche|10000|F==" +
                "CAR0002|2029/10/14|Porsche|9000|F==" +
                "CAR0003|2026/08/17|Porsche|13000|F==" +
                "CAR0004|2027/11/01|BYD|23000|T==" +
                "CAR0005|2027/01/11|BYD|19500|F==" +
                "CAR0006|2029/07/01|Audi|10001|T==" +
                "CAR0007|2028/04/19|Ford|9800|F==" +
                "CAR0008|2027/07/10|Ford|15000|T==" +
                "CAR0009|2024/10/22|Ford|90300|F==";

        String actual = FormatInput.readInput(fileName);
        Assert.assertEquals(expected, actual);
    }
}