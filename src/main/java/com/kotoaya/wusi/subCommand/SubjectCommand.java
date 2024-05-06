package com.kotoaya.wusi.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import com.kotoaya.wusi.common.WusiException;
import com.kotoaya.wusi.common.utils.TxtUtil;
import picocli.CommandLine;

import java.io.*;

/**
 * 主题子命令
 */
@CommandLine.Command(name = "subject",description = "显示主题",mixinStandardHelpOptions = true)
public class SubjectCommand implements Runnable{
    //自定义主题
    @CommandLine.Option(names = {"-s"},description = "自定义主题",arity = "0..1",interactive = true, echo = true)
    private String context="";
    @CommandLine.Option(names = {"-c"},description = "前景色",arity = "0..1",interactive = true, prompt = "可选值31-36:",echo = true)
    private String foreColor="31";

    @CommandLine.Option(names = "-t",description = "刷新间隔",arity = "0..1",interactive = true,prompt = "输入正整数",echo = true)
    private int refreshInterval=1;

    //打印主题
    public void printSubject(){
        // ANSI转义序列：将光标上移n行
        final String CURSOR_UP = "\u001B[%dA";
        // ANSI转义序列：将光标向右移动n个字符位置
        final String CURSOR_RIGHT = "\u001B[%dC";
        try {
            String[][] data = TxtUtil.TxtToArray(15, 150, "static/subject.txt");
            for (int row=0;row<150;row++){
                for (int col=0;col<15;col++){
                    if(data[col][row]==null){
                        System.out.println();
                        continue;
                    }
                    //刷新间隔
                    Thread.sleep(refreshInterval);
                    //光标向右移动row个字符位置
                    System.out.printf(CURSOR_RIGHT,row);
                    System.out.format("\33["+foreColor+";1m"+data[col][row]+"\33[0m\n");
                }
                //打印完最后一列时，直接退出循环，不要向上复位光标
                if (row==149){
                    break;
                }
                //光标向上移动15行
                System.out.printf(CURSOR_UP,15);
            }
        }catch (Exception e){
            throw new WusiException(e.getMessage());
        }
    }

    /**
     * 参数校验
     */
    private void checkParams(){
        Integer foreColorNum = Integer.valueOf(foreColor);
        if(foreColorNum<31||foreColorNum>36){
            throw new WusiException("参数非法");
        }
        if (context.trim()!=""){
            updateSubject(this.context);
        }
    }

    /**
     * 修改主题
     * @param context 主题文本
     */
    private void updateSubject(String context){
        PrintWriter out=null;
        try {
            String subjectPath = new ClassPathResource("static/subject.txt").getPath();
            out = new PrintWriter(new BufferedWriter(new FileWriter(subjectPath)));
            out.println(context);
        }catch (IOException e){
            throw new WusiException(e.getMessage());
        }finally {
            if (out!=null){
                out.close();
            }
        }

    }

    public void run() {
        checkParams();
        this.printSubject();
    }
}
