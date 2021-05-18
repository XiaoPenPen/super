package com.example.demo.java8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author xuchunpeng 2021/5/6
 */
public class Test {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("H:\\TestProject\\src\\main\\java\\com\\chen\\test.txt"));
             Scanner scanner2 = new Scanner(new File("H:\\TestProject\\src\\main\\java\\com\\chen\\test.txt"))) {
            while (scanner.hasNext()) {
                //逐行读取文件中的内容
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException file) {
            file.printStackTrace();
        }
    }
}
