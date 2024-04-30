package com.kotoaya.wusi.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import com.kotoaya.wusi.common.WusiException;
import picocli.CommandLine;

import java.io.*;

/**
 * 五四青年节介绍
 */
@CommandLine.Command(name = "introduce",mixinStandardHelpOptions = true)
public class IntroductionCommand implements Runnable{
    private void printIntroduction(){
        try {
            InputStream stream = new ClassPathResource("introduction.txt").getStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(stream));
            while(br.ready()){
                System.out.println(br.readLine());
            }
        }catch (Exception e){
            throw new WusiException(e.getMessage());
        }
    }

    @Override
    public void run() {
        printIntroduction();
    }
}
