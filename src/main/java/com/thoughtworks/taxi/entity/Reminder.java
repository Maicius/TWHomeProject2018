package com.thoughtworks.taxi.entity;

import com.thoughtworks.taxi.util.BrandUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 提示器
 * 用于记录三种需要提示的状态
 * timeRelated: 指需要定期保养的车辆
 * distanceRelated: 指需要提醒每一万公里保养的车辆
 * WriteOff: 指需要报废的车辆
 * 注意：此外，还需要注意一种状态：已经报废
 */
public class Reminder{
    private List<Car> timeRelated;
    private List<Car> distanceRelated;
    private List<Car> writeOff;
    public Reminder() {
        timeRelated = new ArrayList<>();
        distanceRelated = new ArrayList<>();
        writeOff = new ArrayList<>();
    }

    public List<Car> getTimeRelated() {
        return timeRelated;
    }

    public void setTimeRelated(List<Car> timeRelated) {
        this.timeRelated = timeRelated;
    }

    public void setTimeRelated(Car car) {
        this.timeRelated.add(car);
    }

    public List<Car> getDistanceRelated() {
        return distanceRelated;
    }

    public void setDistanceRelated(List<Car> distanceRelated) {
        this.distanceRelated = distanceRelated;
    }

    public void setDistanceRelated(Car car) {
        this.distanceRelated.add(car);
    }

    public List<Car> getWriteOff() {
        return writeOff;
    }

    public void setWriteOff(List<Car> writeOff) {
        this.writeOff = writeOff;
    }

    public void setWriteOff(Car car) {
        this.writeOff.add(car);
    }

    /**
     * 格式化输出函数
     */
    public void printReminder(){
        println("Reminder");
        println("==================");

        println("* Time-related maintenance coming soon...");
        printList(timeRelated);

        println("* Distance-related maintenance coming soon...");
        printList(distanceRelated);

        println("* Write-off coming soon...");
        printList(writeOff);
    }

    /**
     * 包装System.out.println函数
     * @param object
     */
    private void println(Object object){
        System.out.println(object);
    }

    /**
     * 包装System.out.print函数
     * @param object
     */
    private void print(Object object){
        System.out.print(object);
    }

    /**
     * 打印一个汽车列表
     * @param carList
     */
    private void printList(List<Car> carList){
        HashMap<String, List<Car>> map = BrandUtil.countBrand(carList);
        for(int k=0; k < carList.size(); k++){
            Car car = carList.get(k);
            print(car.getBrand() + ": " + map.get(car.getBrand()).size() + " (");
            List<Car> sameBrandCarList = map.get(car.getBrand());
            int length = sameBrandCarList.size();
            for(int i=0; i < length; i++){
                print(sameBrandCarList.get(i).getCarNumber());
                if(i != length -1)
                    print(", ");
            }
            println(")");
            k += length - 1;
        }
    }

    /**
     * 排序函数
     * 分别对三个列表进行排序
     * 注意：依赖实体类Car中重载的compareTo函数
     */
    public void sort(){
        Collections.sort(timeRelated);
        Collections.sort(distanceRelated);
        Collections.sort(writeOff);
    }
}
