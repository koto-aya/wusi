package ${basePackage};

import ${basePackage}.subCommand.AnswerCommand;
import ${basePackage}.subCommand.IntroductionCommand;
import ${basePackage}.subCommand.SubjectCommand;
import ${basePackage}.subCommand.TestCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "wusi",mixinStandardHelpOptions = true)
public class WusiExecutor implements Runnable{
    private CommandLine commandLine;

    {
        commandLine=new CommandLine(this).
                addSubcommand(SubjectCommand.class).
                addSubcommand(IntroductionCommand.class).
                addSubcommand(AnswerCommand.class).
                addSubcommand(TestCommand.class);
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
