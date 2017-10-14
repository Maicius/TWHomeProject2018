package com.thoughtworks.taxi.main;

import com.thoughtworks.taxi.Constant.ConstantString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class handleIO {
    public static void main(String args[]){
        String fileName = "/Users/maicius/code/TWHomeProject/src/main/java/com/thoughtworks/taxi/main/testCase1";
        try {
            System.setIn(new FileInputStream(fileName));

        }catch(FileNotFoundException e){
            System.out.println(ConstantString.FILE_NOT_FOUND_ERROR + fileName);
        }


    }

}
