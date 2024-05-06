package com.kotoaya.wusi.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import com.kotoaya.wusi.common.WusiException;
import com.kotoaya.wusi.common.utils.TxtUtil;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * 答题
 */
@CommandLine.Command(name = "answer")
public class AnswerCommand implements Runnable{
    List<String> answerData;
    String[] questions;
    InputStream stream=null;
    BufferedReader br=null;
    {
        try {
            answerData= TxtUtil.TxtToList("static/answer.txt");
            InputStream stream = new ClassPathResource("static/questions.txt").getStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb=new StringBuilder();
            while (br.ready()){
                sb.append(br.readLine());
            }
            questions= sb.toString().split("\\$");
        } catch (IOException e) {
            throw new WusiException(e.getMessage());
        }finally {
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    //输出题目
    private void printQuestions() throws IOException {
        Scanner sc=new Scanner(System.in);
        int num=0;//题号
        for (String question : questions) {
            System.out.println(question);
            String userInput = sc.next();
            //检测答案是否正确
            String currentAnswer=answerData.get(num);//当前答案
            if (userInput.trim().equalsIgnoreCase(currentAnswer)) {
                System.out.println("正确");
            }else{
                System.out.println("错误,正确答案是："+currentAnswer);
            }
            ++num;
        }
    }
    @Override
    public void run() {
        try {
            printQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
