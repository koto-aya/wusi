package ${basePackage}.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import ${basePackage}.common.WusiException;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 五四青年节介绍
<#if author??> * @author ${author}</#if>
 */
@CommandLine.Command(name = "introduce",mixinStandardHelpOptions = true)
public class IntroductionCommand implements Runnable{
    private void printIntroduction(){
        try {
            InputStream stream = new ClassPathResource("static/introduction.txt").getStream();
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
