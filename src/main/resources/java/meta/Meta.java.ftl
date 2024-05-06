[#ftl ]
package ${basePackage}.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 元数据信息类
[#if author??] * @author ${author}[/#if]
 */
@NoArgsConstructor
@Data
public class Meta {

    private String name;
    private String description;
    private String basePackage;
    private String author;
    private String version;
    private String createTime;
    private FileConfig fileConfig;
    private ModelConfig modelConfig;

    @NoArgsConstructor
    @Data
    public static class FileConfig {
        private String inputRootPath;
        private String outputRootPath;
        private String type;
        private List<FileInfo> files;

        @NoArgsConstructor
        @Data
        public static class FileInfo {
            private String inputPath;
            private String outputPath;
            private String type;
            private String generator;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ModelConfig {
        private List<ModelInfo> models;

        @NoArgsConstructor
        @Data
        public static class ModelInfo {
            private String fieldName;
            private String fieldType;
            private String defaultValue;
            private String description;
            private String prompt;
            private String addr;
        }
    }
}
