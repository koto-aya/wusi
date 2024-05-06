package com.kotoaya.wusi.subCommand;

import cn.hutool.core.io.FileUtil;
import com.kotoaya.wusi.common.WusiException;
import com.kotoaya.wusi.generator.MainGenerator;
import com.kotoaya.wusi.meta.MetaManager;
import freemarker.template.TemplateException;
import picocli.CommandLine;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * 测试
 */
@CommandLine.Command(name = "aqswdefr")
public class TestCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--show-userdir"},description = "显示当前环境下的user.dir的值")
    private boolean showUserDir;
    @CommandLine.Option(names = "--java-info",description = "显示jdk相关信息")
    private boolean showJavaEnvironment;
    @CommandLine.Option(names = "--dynamic-start",description = "动态生成文件")
    private boolean dynamicGeneratorProject;

    @CommandLine.Option(names = "--generate-jar",description = "生成jar包")
    private boolean generatorJar;

    public void toJar() throws IOException {
        if (!generatorJar) return;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要生成jar包的根路径");
        String projectRootPath=sc.next();
        System.out.println("请输入jar包的输出路径");
        String jarOutputPath=sc.next();
        if (!FileUtil.exist(projectRootPath)||!FileUtil.exist(jarOutputPath)) {
            throw new WusiException("项目不存在或路径错误");
        }
        String windowCommand="mvn.cmd clean package";
        String otherCommand="mvn clean package";
        String execCommand=windowCommand;
        ProcessBuilder processBuilder = new ProcessBuilder(execCommand).command(execCommand);
        ProcessBuilder directory = processBuilder.directory(new File(projectRootPath));
        Process process = directory.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while (br.ready()){
            System.out.println(br.readLine());
        }
    }


    public void doGenerator() throws TemplateException, IOException {
        if (!dynamicGeneratorProject){
            return;
        }
        new MainGenerator().doGenerator(MetaManager.getMeta());
    }

    public void printUserDir(){
        if (!showUserDir){
            return;
        }
        System.out.println(System.getProperty("user.dir"));
    }
    public void printJavaEnvironment(){
        if (!showJavaEnvironment){
            return;
        }
        System.out.println("JDK版本："+System.getProperty("java.version"));
        System.out.println("供应商："+System.getProperty("java.vendor"));
        System.out.println("Java安装目录："+System.getProperty("java.home"));
        System.out.println("操作系统："+System.getProperty("os.name")+" "+System.getProperty("os.arch")+System.getProperty("os.version"));
    }
    @Override
    public Integer call() throws Exception {
        doGenerator();
        toJar();
        printUserDir();
        printJavaEnvironment();
        return 1;
    }
}
