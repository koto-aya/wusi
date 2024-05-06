[#ftl ]
package ${basePackage}.subCommand;

import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * 测试
[#if author??] * @author ${author}[/#if]
 */
@CommandLine.Command(name = "aqswdefr")
public class TestCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--show-userdir"},description = "显示当前环境下的user.dir的值")
    private boolean showUserDir;
    @CommandLine.Option(names = "--java-info",description = "显示jdk相关信息")
    private boolean showJavaEnvironment;
    @CommandLine.Option(names = "--dynamic-start",description = "动态生成文件")
    private boolean dynamicGeneratorProject;

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
        printUserDir();
        printJavaEnvironment();
        return 1;
    }
}
