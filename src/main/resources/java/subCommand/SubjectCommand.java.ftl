package ${basePackage}.subCommand;

import cn.hutool.core.io.resource.ClassPathResource;
import ${basePackage}.common.WusiException;
import ${basePackage}.common.utils.TxtUtil;
import picocli.CommandLine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 主题子命令
<#if author??> * @author ${author}</#if>
 */
@CommandLine.Command(name = "subject",description = "显示主题",mixinStandardHelpOptions = true)
public class SubjectCommand implements Runnable{
<#list modelConfig.models as modelInfo>
    //自定义主题
    @CommandLine.Option(names = {"${modelInfo.addr}"},description = "${modelInfo.description}",arity = "0..1",interactive = true, echo = true<#if prompt??>,prompt="${modelInfo.prompt}"</#if>)
    private ${modelInfo.fieldType} ${modelInfo.fieldName}="${modelInfo.defaultValue}";
</#list>


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
