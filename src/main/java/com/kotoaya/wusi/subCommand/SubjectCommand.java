package com.kotoaya.wusi.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import com.kotoaya.wusi.common.WusiException;
import picocli.CommandLine;

import java.io.*;

import static cn.hutool.poi.excel.sax.AttributeName.s;
import static cn.hutool.poi.excel.sax.AttributeName.t;

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
        // ANSI转义序列：将光标上移一行
        final String CURSOR_UP = "\u001B[A";
        // ANSI转义序列：将光标向右移动一个字符位置
        final String CURSOR_RIGHT = "\u001B[C";
        try {
            String[][] data = TxtToArray(15, 150, "subject.txt");
            for (int row=0;row<150;row++){
                for (int col=0;col<15;col++){
                    if(data[col][row]==null){
                        System.out.println();
                        continue;
                    }
                    //刷新间隔
                    Thread.sleep(refreshInterval);
                    //光标向右移动row个字符位置
                    for (int i=0;i<row;i++){
                        System.out.print(CURSOR_RIGHT);
                    }
                    System.out.format("\33["+foreColor+";1m"+data[col][row]+"\33[0m\n");
                }
                //打印完最后一列时，直接退出循环，不要向上复位光标
                if (row==149){
                    break;
                }
                //光标向上移动15行
                for (int i=0;i<15;i++){
                    System.out.print(CURSOR_UP);
                }
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
            String subjectPath = new ClassPathResource("subject.txt").getPath();
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

    /**
     * 将txt文件内容存入数组
     * @param row_num 数组的行数
     * @param col_num 数组的列数
     * @param txtPath txt文件的名称
     * @return
     */
    private String[][] TxtToArray(int row_num,int col_num,String txtPath) throws IOException {
        String[][] data=new String[row_num][col_num];
        InputStream stream = new ClassPathResource(txtPath).getStream();
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
        return data;
    }

    public void run() {
        checkParams();
        this.printSubject();
    }
}
