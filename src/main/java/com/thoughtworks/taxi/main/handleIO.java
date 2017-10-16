package com.thoughtworks.taxi.main;

import com.thoughtworks.taxi.Constant.ConstantProperties;
import com.thoughtworks.taxi.core.Calculate;
import com.thoughtworks.taxi.entity.Car;
import com.thoughtworks.taxi.entity.Reminder;
import com.thoughtworks.taxi.util.FormatInput;

import java.util.List;

/**
 * 程序入口
 */
public class handleIO {
    public static void main(String args[]) {
        String fileName;
        //根据控制台的输入选择读取的文件
        //若控制台输入为空，则读取默认文件:testCase1.txt
        if(args.length == 0)
            fileName = ConstantProperties.INPUT_FILE_NAME;
        else
            fileName = args[0];
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
