package cn.com.ljw.file.hdfs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Steph_Lin
 * @date 2024/9/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HdfsConfig {

    private String accessKeyId;
    private String endpoint;
    private String rootPath;

    public static HdfsConfig convertHdfsConfig(String userName, String url) {
        return HdfsConfig.builder()
                .accessKeyId(userName)
                .endpoint(url)
                .rootPath("/")
                .build();
    }

}
