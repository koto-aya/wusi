package com.kotoaya.wusi;

import com.kotoaya.wusi.subCommand.IntroductionCommand;
import com.kotoaya.wusi.subCommand.SubjectCommand;
import picocli.CommandLine;

import javax.security.auth.Subject;
import java.io.*;

@CommandLine.Command(name = "wusi",mixinStandardHelpOptions = true)
public class WusiExecutor implements Runnable{
    private CommandLine commandLine;

    {
        commandLine=new CommandLine(this).
                addSubcommand(SubjectCommand.class).
                addSubcommand(IntroductionCommand.class);
    }

    /**
     * 最终执行命令的方法
     * @param args 用户传入参数
     * @return
     */
    public int doExecute(String[] args){
        return commandLine.execute(args);
    }

    @Override
    public void run() {
        //未输入子命令时显示友好提示
        System.out.println("输入--help查看帮助手册");
    }
}
