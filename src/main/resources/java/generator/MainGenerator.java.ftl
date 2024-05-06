package ${basePackage}.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 主生成器
<#if author??> * @author ${author}</#if>
 */
public class MainGenerator {
    public void doGenerator(Object data) throws TemplateException, IOException {
        String inputRootPath="${fileConfig.inputRootPath}";
        String outputRootPath="${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

<#list fileConfig.files as fileInfo>
        inputPath=new File(inputRootPath,"${fileInfo.inputPath}").getAbsolutePath();
        outputPath=new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generator=="dynamic">
        DynamicGenerator.doGenerator(inputPath,outputPath,data);
    <#else >
        StaticGenerator.doGenerator(inputPath,outputPath);
    </#if>

</#list>

    }
}
