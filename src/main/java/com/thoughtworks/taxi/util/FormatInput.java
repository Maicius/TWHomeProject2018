package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.Constant.ConstantString;
import com.thoughtworks.taxi.entity.Car;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class FormatInput {
    public static List<Car> formatInput(String submitInfos){
        return getCarInfos(submitInfos);
    }

    private static Date getSubmitDate(String submit){
        try {
            String[] strings = submit.split(ConstantString.SPLIT_SUBMIT_DATE);
            String dateString = strings[1].trim();
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
            return sdf.parse(dateString);
        }catch (ParseException | NullPointerException e){
            e.printStackTrace();
            System.err.println(ConstantString.FORMAT_PARSE_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(ConstantString.UNEXPECTED_ERROR);
        }
        return null;
    }

    private static List<Car> getCarInfos(String submitInfos){
        StringTokenizer st = new StringTokenizer(submitInfos, ConstantString.SPLIT_WORD);
        String submitString = st.nextToken();
        Date submitDate = getSubmitDate(submitString);
        List<Car> carList = new ArrayList<>();
        try{
            while(st.hasMoreTokens()){
                String carInfoString = st.nextToken();
                StringTokenizer carInfoSt = new StringTokenizer(carInfoString, ConstantString.SPLIT_CAR_INFO);
                SimpleDateFormat sdf = new SimpleDateFormat(ConstantString.SIMPLE_DATE_FORMAT);
                if(carInfoSt.countTokens() == 5) {
                    Car car = new Car();
                    car.setSubmitDate(submitDate);
                    car.setCarNumber(carInfoSt.nextToken());
                    car.setBoughtDate(sdf.parse(carInfoSt.nextToken()));
                    car.setBrand(carInfoSt.nextToken());
                    car.setKilos(Integer.parseInt(carInfoSt.nextToken()));
                    car.setFixed(getTorF(carInfoSt.nextToken()));
                    carList.add(car);
                }else{
                    throw new ParseException(carInfoString, 0);
                }
            }
            return carList;
        }catch (ParseException e){
            e.printStackTrace();
            System.err.println(ConstantString.FORMAT_PARSE_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(ConstantString.UNEXPECTED_ERROR);
        }
        return null;
    }

    private static boolean getTorF(String tf){
        return tf.equals("T");
    }

    public static String readInput(String fileName) {
        try {
            System.setIn(new FileInputStream(fileName));
            Scanner sc = new Scanner(System.in);
            StringBuilder input = new StringBuilder();
            while (sc.hasNext()) {
                input.append(sc.nextLine()).append(ConstantString.SPLIT_WORD);
            }
            return input.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(ConstantString.FILE_NOT_FOUND_ERROR + fileName);
            return null;
        }
    }
}
