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
    @CommandLine.Option(names = {"--fg"},description = "前景色",arity = "0..1",interactive = true, prompt = "可选值16-255(默认为彩色):",echo = true)
    private Integer foreColor=-1;

    @CommandLine.Option(names = "--bg",description = "背景色",arity = "0..1",interactive = true,prompt = "可选值16-255(默认为黑色):",echo = true)
    private Integer backgroundColor=16;

    @CommandLine.Option(names = "-t",description = "刷新间隔",arity = "0..1",interactive = true,prompt = "输入正整数",echo = true)
    private int refreshInterval=1;

    //打印主题
    public void printSubject(){
        // ANSI转义序列：将光标上移n行
        final String CURSOR_UP = "\u001B[%dA";
        // ANSI转义序列：将光标向右移动n个字符位置
        final String CURSOR_RIGHT = "\u001B[%dC";
        try {
            String[][] data = TxtUtil.TxtToArray(13, 150, "static/subject.txt");
            for (int row=0;row<150;row++){
                for (int col=0;col<13;col++){
                    if(data[col][row]==null){
                        System.out.printf(CURSOR_RIGHT,row);
                        printSubjectWithColor(-1,backgroundColor," ");
                        continue;
                    }
                    //刷新间隔
                    Thread.sleep(refreshInterval);
                    //光标向右移动row个字符位置
                    System.out.printf(CURSOR_RIGHT,row);
                    //TODO 打印指定颜色字体
                    //如果颜色不在16~255范围内，输出彩色内容
                    if (foreColor<0||foreColor>255){
                        printSubjectWithColor(row+96,backgroundColor,data[col][row]);
                    }else{
                        printSubjectWithColor(foreColor,backgroundColor,data[col][row]);
                    }
                }
                //打印完最后一列时，直接退出循环，不要向上复位光标
                if (row==149){
                    break;
                }
                //光标向上移动15行
                System.out.printf(CURSOR_UP,13);
            }
        }catch (Exception e){
            throw new WusiException(e.getMessage());
        }
    }

    /**
     * 打印彩色字体
     * @param foreColor 前景色
     * @param context 要打印的内容
     */
    private void printSubjectWithColor(int foreColor,int backgroundColor,String context){
        String stringWithForeColor = CommandLine.Help.Ansi.ON.string("@|fg("+foreColor+") "+context+"|@");
        if (backgroundColor<16||backgroundColor>255){
            backgroundColor=16;
        }
        String string=CommandLine.Help.Ansi.ON.string("@|bg("+backgroundColor+") "+stringWithForeColor+"|@");
        System.out.println(string);
    }

    public void run() {
        this.printSubject();
    }
}
