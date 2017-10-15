package com.thoughtworks.taxi.core;


import com.thoughtworks.taxi.entity.Car;
import com.thoughtworks.taxi.entity.Reminder;

import java.util.List;

/**
 * 核心计算类
 */
public class Calculate {

    private Reminder reminder = null;

    public Calculate() {
        reminder = new Reminder();
    }

    /**
     * 记录报废状态的Enum
     */
    public enum WriteOffState {
        Already,
        Nearly,
        New
    }

    /**
     * 计算车辆信息，包括是否该提醒报废或提醒保修
     *
     * @param carList
     * @return
     */
    public Reminder calculateCarInfo(List<Car> carList) {
        for (Car car : carList) {
            WriteOffState state = isWriteOff(car);
            //报废状态为接近（Nearly），则提醒用户
            //报废状态为（Already），则忽略该车
            if (state.equals(WriteOffState.Nearly)) {
                reminder.setWriteOff(car);
            } else if (state.equals(WriteOffState.New)) {
                //提醒按距离保修
                if (isDistanceRelated(car)) {
                    reminder.setDistanceRelated(car);
                }
                //提醒按时间保修
                else if (isTimeRelated(car)) {
                    reminder.setTimeRelated(car);
                }
            }
        }
        reminder.sort();
        return reminder;
    }

    /**
     * 计算是否属于报废车辆
     *
     * @param car 车辆信息
     * @return
     */
    private WriteOffState isWriteOff(Car car) {
        int writeOffYears = 6;
        final int dayTime = 1000 * 3600 * 24;
        final int year = 365;
        if (car.isFixed())
            writeOffYears = 3;
        final int boughtDays = (int) ((car.getSubmitDate().getTime() - car.getBoughtDate().getTime()) / dayTime);
        final int writeOffDays = year * writeOffYears;
        //计算已经报废的车辆
        if (boughtDays >= writeOffDays)
            return WriteOffState.Already;
            //提前一个月（不计算日期）开始提醒要报废的车辆
            //62是一年中两个月累加最可能多的天数（7月 + 8月）
        else if (boughtDays >= writeOffDays - 62 && Math.abs(car.getBoughtDate().getMonth() - car.getSubmitDate().getMonth()) <= 2)
            return WriteOffState.Nearly;
        else
            return WriteOffState.New;
    }

    /**
     * 按里程信息计算是否应该提醒报修
     *
     * @param car
     * @return
     */
    private boolean isDistanceRelated(Car car) {
        final int distanceKilos = 10000;
        final int distanceRemindBegin = 500;
        //里程超过1万的取余
        return ((car.getKilos() % distanceKilos) == 0 || distanceKilos - (car.getKilos() % distanceKilos) <= distanceRemindBegin);
    }

    /**
     * 按时间信息计算是否应该提醒报修
     *
     * @param car
     * @return
     */
    private boolean isTimeRelated(Car car) {
        final int splitYears = car.getSubmitDate().getYear() - car.getBoughtDate().getYear();
        int fixSplit = 12;
        if (car.isFixed())
            fixSplit = 3;
        else if (splitYears >= 3)
            fixSplit = 6;

        //计算两个日期之间相差的月份，由于题目要求忽略掉日期，所以不能使用car.getSubmitDate.getTime()来计算
        //注意最后要 + 1
        int monthSplit = (car.getSubmitDate().getYear() - car.getBoughtDate().getYear()) * 12
                + (car.getSubmitDate().getMonth() - car.getBoughtDate().getMonth()) + 1;

        //计算公式： 总月数 % 保修周期, 余数则是下一个保修周期到来的剩下的月数
        return ((monthSplit % fixSplit) <= 1);
    }
}
