package cn.com.ljw.controller;

//import cn.com.ljw.file.hdfs.HdfsConfig;
//import cn.com.ljw.file.hdfs.HdfsFileStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Steph_Lin
 * @date 2024/9/13
 */
@Api(tags = "文件存储中心")
@RestController
@RequestMapping("/sys/file")
@AllArgsConstructor
public class FileController {

//    private final HdfsFileStorage hdfsFileStorage;
//
//    @ApiOperation("上传文件 返回文件id")
//    @PostMapping("/upload")
//    public String uploadFile(
//            @ApiParam("相对路径") @RequestParam(required = false) String basePath,
//            @ApiParam(value = "文件") @RequestPart("file") MultipartFile file) throws Exception {
//        hdfsFileStorage.getClient(HdfsConfig.convertHdfsConfig("test", "hdfs://localhost:9090"));
//        return hdfsFileStorage.upload(basePath, file.getOriginalFilename(), file.getInputStream());
//    }

}
