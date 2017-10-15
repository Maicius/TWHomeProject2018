package com.thoughtworks.taxi.entity;

import com.thoughtworks.taxi.Constant.ConstantString;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class ReminderTest {
    PrintStream console = null;
    ByteArrayOutputStream output = null;

    @Before
    public void setUp() throws Exception {
        //重定向输出到bytes
        System.out.println("Begin Test Reminder");
        System.out.println("=====================");
        output = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(output));
    }
    @After
    public void reset() throws Exception{
        System.setOut(console);
        System.out.println("=====================");
        System.out.println("Finish Test Reminder");
    }

    @Test
    public void setTimeRelated() throws Exception {
        Reminder reminder = new Reminder();
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        reminder.setTimeRelated(car);
        Assert.assertEquals(1, reminder.getTimeRelated().size());
        Assert.assertEquals(car, reminder.getTimeRelated().get(0));
    }

    @Test
    public void setDistanceRelated() throws Exception {
        Reminder reminder = new Reminder();
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        reminder.setDistanceRelated(car);
        Assert.assertEquals(1, reminder.getDistanceRelated().size());
        Assert.assertEquals(car, reminder.getDistanceRelated().get(0));
    }

    @Test
    public void setWriteOff() throws Exception {
        Reminder reminder = new Reminder();
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        reminder.setWriteOff(car);
        Assert.assertEquals(1, reminder.getWriteOff().size());
        Assert.assertEquals(car, reminder.getWriteOff().get(0));
    }

    @Test
    public void printReminder1() throws Exception {
        Reminder reminder = new Reminder();
        String expected = "Reminder\n" +
                "==================\n" +
                "* Time-related maintenance coming soon...\n" +
                "Audi: 1 (CAR0006)\n" +
                "Porsche: 1 (CAR0002)\n" +
                "* Distance-related maintenance coming soon...\n" +
                "BYD: 1 (CAR0005)\n" +
                "Ford: 1 (CAR0007)\n" +
                "Porsche: 1 (CAR0001)\n" +
                "* Write-off coming soon...\n" +
                "BYD: 1 (CAR0004)\n" +
                "Ford: 1 (CAR0009)\n";
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        reminder.setTimeRelated(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Porsche", 100001, true);
        reminder.setTimeRelated(car);
        car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        reminder.setDistanceRelated(car);
        car = new Car("CAR0007", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        reminder.setDistanceRelated(car);
        car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Porsche", 100001, true);
        reminder.setDistanceRelated(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        reminder.setWriteOff(car);
        car = new Car("CAR0009", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        reminder.setWriteOff(car);
        reminder.printReminder();
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    public void printReminder2() throws Exception {
        Reminder reminder = new Reminder();
        String expected = "Reminder\n" +
                "==================\n" +
                "* Time-related maintenance coming soon...\n" +
                "Jeep: 2 (CAR0006, CAR0007)\n" +
                "* Distance-related maintenance coming soon...\n" +
                "Peugeot: 1 (CAR0005)\n" +
                "* Write-off coming soon...\n" +
                "BMW: 1 (CAR0002)\n" +
                "Honda: 1 (CAR0004)\n";
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0006", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Jeep", 100001, true);
        reminder.setTimeRelated(car);
        car = new Car("CAR0007", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Jeep", 100001, true);
        reminder.setTimeRelated(car);
        car = new Car("CAR0005", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Peugeot", 100001, true);
        reminder.setDistanceRelated(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BMW", 100001, true);
        reminder.setWriteOff(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Honda", 100001, true);
        reminder.setWriteOff(car);
        reminder.printReminder();
        Assert.assertEquals(expected, output.toString());

    }

    @Test
    public void sort() throws Exception {
        Reminder reminder = new Reminder();
        SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
        Car car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        reminder.setTimeRelated(car);
        reminder.setDistanceRelated(car);
        reminder.setWriteOff(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        reminder.setTimeRelated(car);
        reminder.setDistanceRelated(car);
        reminder.setWriteOff(car);
        car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        reminder.setTimeRelated(car);
        reminder.setDistanceRelated(car);
        reminder.setWriteOff(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        reminder.setTimeRelated(car);
        reminder.setWriteOff(car);
        reminder.setDistanceRelated(car);
        reminder.sort();

        Reminder sortedReminder = new Reminder();
        car = new Car("CAR0003", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Audi", 100001, true);
        sortedReminder.setTimeRelated(car);
        sortedReminder.setDistanceRelated(car);
        sortedReminder.setWriteOff(car);
        car = new Car("CAR0002", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        sortedReminder.setTimeRelated(car);
        sortedReminder.setDistanceRelated(car);
        sortedReminder.setWriteOff(car);
        car = new Car("CAR0004", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "BYD", 100001, true);
        sortedReminder.setTimeRelated(car);
        sortedReminder.setDistanceRelated(car);
        sortedReminder.setWriteOff(car);
        car = new Car("CAR0001", sdf.parse("2030/09/01"), sdf.parse("2029/07/01"), "Ford", 100001, true);
        sortedReminder.setTimeRelated(car);
        sortedReminder.setDistanceRelated(car);
        sortedReminder.setWriteOff(car);

        Assert.assertEquals(sortedReminder.getDistanceRelated(), reminder.getDistanceRelated());
        Assert.assertEquals(sortedReminder.getTimeRelated(), reminder.getTimeRelated());
        Assert.assertEquals(sortedReminder.getWriteOff(), reminder.getWriteOff());
    }

}