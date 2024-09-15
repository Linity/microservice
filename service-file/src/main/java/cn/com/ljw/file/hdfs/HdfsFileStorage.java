package cn.com.ljw.file.hdfs;

import cn.com.ljw.file.core.FileStorageSystem;
import cn.com.ljw.file.exception.FileStorageRuntimeException;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Steph_Lin
 * @date 2024/9/13
 */
@Slf4j
public class HdfsFileStorage extends FileStorageSystem {

    private HdfsConfig config;

    @Override
    public Boolean exist(String fullPath) {
        try {
            return getClient(config).exists(new Path(processPathForSave(fullPath)));
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String upload(String basePath, Long fileSize, String fileName, InputStream inputStream) throws IOException {
        final String fullPath = concatPathForSave(config.getRootPath(), basePath, fileName);
        FSDataOutputStream out = getClient(config).create(new Path(fullPath));
        byte[] bytes = new byte[1 << 11];
        int read;
        while ((read = inputStream.read(bytes)) > 0) {
            out.write(bytes, 0, read);
        }
        out.close();
        return fullPath;
    }

    @Override
    public InputStream download(String fullPath) throws IOException {
        return getClient(config).open(new Path(processPathForSave(fullPath)));
    }

    @Override
    public boolean delete(String fullPath) throws IOException, UnsupportedOperationException {
        final String path = processPathForSave(fullPath);
        if (StrUtil.isBlank(path))
            throw new UnsupportedOperationException("不允许删除根目录！");
        log.info("删除文件:{}", fullPath);

        return getClient(config).delete(new Path(path), true);
    }

    public FileSystem getClient(HdfsConfig config) {
//        Preconditions.assertTrue(StrUtil.isBlank(config.getEndpoint()), "hdfs地址不能为空");
//        Preconditions.assertTrue(StrUtil.isBlank(config.getRootPath()), "hdfs根路径不能为空");
//        Preconditions.assertTrue(StrUtil.isBlank(config.getAccessKeyId()), "hdfs用户名不能为空");
        this.config = config;
        final Configuration conf = new Configuration();
        conf.set("fs.defaultFS", config.getEndpoint());
        System.setProperty("HADOOP_USER_NAME", config.getAccessKeyId());

        try {
            final FileSystem fileSystem = FileSystem.get(conf);
//            log.info("初始化成功 {}", config.getEndpoint());
            return fileSystem;
        } catch (IOException e) {
            log.error("获取hdfs客户端失败 {} ", e);
            throw new FileStorageRuntimeException(e);
        }

    }
}
