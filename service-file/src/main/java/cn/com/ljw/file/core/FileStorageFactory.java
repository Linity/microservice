package cn.com.ljw.file.core;

import cn.com.ljw.file.hdfs.HdfsFileStorage;

/**
 * @author Steph_Lin
 * @date 2024/9/13
 */
public class FileStorageFactory {

    public static FileStorageSystem getFileStorage(String type) {
        if("hdfs".equals(type)) {
            return new HdfsFileStorage();
        }
        return null;
    }

}
