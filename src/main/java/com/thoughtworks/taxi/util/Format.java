package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.Constant.ConstantString;
import com.thoughtworks.taxi.entity.Car;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 */
public class Format {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
    public static List<Car> formatInput(String submitInfos){
        return getCarInfos(submitInfos);
    }

    private static Date getSubmitDate(String submit){
        try {
            String[] strings = submit.split(":");
            String dateString = strings[1];

            return sdf.parse(dateString);
        }catch (ParseException | NullPointerException e){
            System.err.println(ConstantString.FORMAT_PARSE_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(ConstantString.UNEXPECTED_ERROR);
        }
        return null;
    }

    private static List<Car> getCarInfos(String submitInfos){
        StringTokenizer st = new StringTokenizer(submitInfos, "\\r\\n");
        String submitString = st.nextToken();
        Date submitDate = getSubmitDate(submitString);
        List<Car> carList = new ArrayList<>();
        try{
            while(st.hasMoreTokens()){
                String carInfoString = st.nextToken();
                StringTokenizer carInfoSt = new StringTokenizer(carInfoString, "|");
                if(carInfoSt.countTokens() == 5) {
                    Car car = new Car();
                    car.setSubmitDate(submitDate);
                    car.setCarNumber(st.nextToken());
                    car.setBoughtDate(sdf.parse(st.nextToken()));
                    car.setBrand(st.nextToken());
                    car.setKilos(st.nextToken());
                    car.setFixed(getTorF(st.nextToken()));
                    carList.add(car);
                }else{
                    throw new ParseException(ConstantString.WORNG_CAR_INFO, 0);
                }
            }
            return carList;
        }catch (ParseException e){
            System.err.println(ConstantString.FORMAT_PARSE_ERROR);
        }catch (Exception e){
            System.err.println(ConstantString.UNEXPECTED_ERROR);
        }
    }

    private static boolean getTorF(String tf){
        return tf.equals("T");
    }
}
