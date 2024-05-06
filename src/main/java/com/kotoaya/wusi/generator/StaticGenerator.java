package com.kotoaya.wusi.generator;

import cn.hutool.core.io.FileUtil;
import com.kotoaya.wusi.common.WusiException;

/**
 * 静态文件生成器类
 * @author kotoaya
 */
public class StaticGenerator {
    /**
     * 静态文件生成
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void doGenerator(String inputPath,String outputPath){
        if (!FileUtil.exist(inputPath)){
            throw new WusiException("路径未找到");
        }
        FileUtil.copy(inputPath,outputPath,true);
    }
}
