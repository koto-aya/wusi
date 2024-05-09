package com.kotoaya.wusi.common.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtUtil {
    /**
     * 将txt文件内容存入二维数组
     * @param row_num 数组的行数
     * @param col_num 数组的列数
     * @param txtName txt文件的名称
     * @return
     */
    public static String[][] TxtToArray(int row_num,int col_num,String txtName) throws IOException {
        String[][] data=new String[row_num][col_num];
        InputStream stream = new ClassPathResource(txtName).getStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        for (int row=0;row<row_num;row++){
            for (int col=0;col<col_num;col++){
                if (!bufferedReader.ready()){
                    break;
                }
                char readChar=(char)bufferedReader.read();
                if (readChar=='\r'){
                    continue;
                }
                if (readChar=='\n'){
                    break;
                }
                data[row][col]= String.valueOf(readChar);
            }
        }
        stream.close();
        bufferedReader.close();
        return data;
    }

    /**
     * txt文件转换成列表
     * @param txtName txt文件名称
     * @return
     */
    public static List<String> TxtToList(String txtName) throws IOException {
        List<String> data=new ArrayList<>();
        InputStream stream = new ClassPathResource(txtName).getStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        while (bufferedReader.ready()){
            data.add(String.valueOf((char)bufferedReader.read()));
        }
        stream.close();
        bufferedReader.close();
        return data;
    }

}
