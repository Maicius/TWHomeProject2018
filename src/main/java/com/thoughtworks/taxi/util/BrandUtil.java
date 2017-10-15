package com.thoughtworks.taxi.util;

import com.thoughtworks.taxi.entity.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrandUtil {
    public static HashMap<String, List<Car>> countBrand(List<Car> carList) {
        HashMap<String, List<Car>> map = new HashMap<>();
        carList.forEach(car -> {
            String brand = car.getBrand();
            if (!map.containsKey(brand)) {
                List<Car> list = new ArrayList<>();
                list.add(car);
                map.put(brand, list);
            } else {
                List<Car> list = map.get(brand);
                list.add(car);
                map.replace(brand, list);
            }
        });
        return map;
    }
}
