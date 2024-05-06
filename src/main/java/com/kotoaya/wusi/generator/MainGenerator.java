package com.kotoaya.wusi.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 主生成器
 * @author kotoaya
 */
public class MainGenerator {
    public void doGenerator(Object data) throws TemplateException, IOException {
        String inputRootPath="E:/project/wusi";
        String outputRootPath="wusi";

        String inputPath;
        String outputPath;

        inputPath=new File(inputRootPath,"src/main/resources/java/common/utils/TxtUtil.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/common/utils/TxtUtil.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/generator/MainGenerator.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/generator/MainGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/generator/DynamicGenerator.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/generator/DynamicGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/generator/StaticGenerator.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/generator/StaticGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath=new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.doGenerator(inputPath,outputPath);

        inputPath=new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath=new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.doGenerator(inputPath,outputPath);

        inputPath=new File(inputRootPath,"src/main/resources").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/resources").getAbsolutePath();
        StaticGenerator.doGenerator(inputPath,outputPath);

        inputPath=new File(inputRootPath,".idea").getAbsolutePath();
        outputPath=new File(outputRootPath,".idea").getAbsolutePath();
        StaticGenerator.doGenerator(inputPath,outputPath);

        inputPath=new File(inputRootPath,"src/main/resources/java/pom.xml.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"pom.xml").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/meta/Meta.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/meta/Meta.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/meta/MetaManager.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/meta/MetaManager.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/subCommand/SubjectCommand.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/subCommand/SubjectCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/subCommand/AnswerCommand.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/subCommand/AnswerCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/subCommand/IntroductionCommand.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/subCommand/IntroductionCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/subCommand/TestCommand.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/subCommand/TestCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/WusiExecutor.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/WusiExecutor.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);

        inputPath=new File(inputRootPath,"src/main/resources/java/WusiApplication.java.ftl").getAbsolutePath();
        outputPath=new File(outputRootPath,"src/main/java/com/kotoaya/WusiApplication.java").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath,outputPath,data);


    }
}
