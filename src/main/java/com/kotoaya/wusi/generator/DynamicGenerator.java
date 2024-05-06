package com.kotoaya.wusi.generator;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态文件生成
 */
public class DynamicGenerator {
    /**
     * 生成动态文件
     * @param inputPath 输入路径(模板文件的路径)
     * @param outputPath 输出路径
     * @param data 数据模型
     */
    public static void doGenerator(String inputPath,String outputPath,Object data) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件所在的文件夹路径
        cfg.setDirectoryForTemplateLoading(new File(inputPath).getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //获取模板文件
        Template temp = cfg.getTemplate(new File(inputPath).getName());
        if (!FileUtil.exist(outputPath)){
            FileUtil.mkdir(new File(outputPath).getParentFile());
        }
        //合并数据模型和模板文件
        Writer out = new FileWriter(outputPath);
        temp.process(data, out);
    }
}
