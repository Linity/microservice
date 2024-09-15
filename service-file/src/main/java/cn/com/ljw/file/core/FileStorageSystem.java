package cn.com.ljw.file.core;

import cn.com.ljw.file.support.FileNameUtil;
import cn.com.ljw.file.support.FilePathUtil;
import cn.hutool.core.util.IdUtil;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Steph_Lin
 * @date 2024/9/13
 */
public abstract class FileStorageSystem {
    /**
     * 文件保存时拼装全路径
     * @param baseRootPath     根路径
     * @param relativePath     相对路径
     * @param fileName         文件名称
     * @return
     */
    protected String concatPathForSave(String baseRootPath, String relativePath, String fileName){
//        Assert.checkNotEmpty(fileName, "文件名称不能为空");
        fileName = FileNameUtil.cleanInvalid(fileName);

        String fullPath = FilePathUtil.pathJoin(baseRootPath, relativePath, fileName);
        fullPath = processPathForSave(fullPath);
        if(exist(fullPath)){
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            for(int i = 0; i < 100; i++){
                String name = sdf.format(new Date())+i+fileName;
                if(i > 90){
                    name = IdUtil.fastSimpleUUID()+fileName;
                }
                fullPath = FilePathUtil.pathJoin(baseRootPath, relativePath, name);
                fullPath = processPathForSave(fullPath);
                if(!exist(fullPath)){
                    break;
                }
            }
        }
        return fullPath;
    }

    protected String processPathForSave(String fullPath){
        return fullPath;
    }

    /**
     * 文件是否存在
     * @param fullPath 文件全路径
     * @return
     */
    public abstract Boolean exist(String fullPath);


    /**
     * 上传文件
     * @param basePath          相对地址
     * @param fileSize          文件大小
     * @param fileName          文件名称
     * @param inputStream       输入文件流
     * @return
     * @throws IOException
     */
    public abstract String upload(String basePath, Long fileSize, String fileName, InputStream inputStream) throws IOException;

    /**
     * 上传文件
     * @param basePath          相对地址
     * @param fileName          文件名称
     * @param inputStream       输入文件流
     * @return
     * @throws IOException
     */
    public String upload(String basePath, String fileName, InputStream inputStream) throws IOException{
        return upload(basePath, Long.valueOf(inputStream.available()), fileName, inputStream);
    }

    /**
     * 下载文件
     *
     * @param fullPath 文件唯一标识(全路径)
     * @return 输出流
     * @throws IOException 发生IO异常时抛出
     */
    public abstract InputStream download(String fullPath) throws IOException;

    /**
     * 删除文件
     *
     * @param fullPath 文件唯一标识(全路径)
     * @return 是否删除成功
     * @throws IOException                   发生IO异常时抛出
     * @throws UnsupportedOperationException 当目标文件系统不支持删除文件操作时抛出
     */
    public abstract boolean delete(String fullPath) throws IOException, UnsupportedOperationException;
}
