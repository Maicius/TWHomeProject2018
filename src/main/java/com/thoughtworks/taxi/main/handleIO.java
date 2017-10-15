package com.thoughtworks.taxi.main;

import com.thoughtworks.taxi.Constant.ConstantString;
import com.thoughtworks.taxi.core.Calculate;
import com.thoughtworks.taxi.entity.Car;
import com.thoughtworks.taxi.entity.Reminder;
import com.thoughtworks.taxi.util.FormatInput;

import java.util.List;


public class handleIO {
    public static void main(String args[]) {
        String fileName = ConstantString.INPUT_FILE_NAME;
        try {
            //获取输入
            String input = FormatInput.readInput(fileName);
            //格式化输入，将String转化为实体类Car
            List<Car> listInfo = FormatInput.formatInput(input);
            //计算
            Calculate calculate = new Calculate();
            Reminder reminder = calculate.calculateCarInfo(listInfo);
            //输出
            reminder.printReminder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
