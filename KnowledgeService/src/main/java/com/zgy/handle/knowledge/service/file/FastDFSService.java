package com.zgy.handle.knowledge.service.file;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class FastDFSService {
    private StorageClient1 storageClient1;

    static {
        String conf_filename = "fdfs_client.conf";
        //log.info("获取到得配置文件得名称为:" + conf_filename);
        // fastDFS方式
        try {
            ClientGlobal.init(conf_filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public String uploadToFastDFS(MultipartFile file){
        String fileId = "";
        try {
            if (file.isEmpty()){
                log.error("空文件，不能上传");
            }else {
                String tempFileName = file.getOriginalFilename();
                // fastDFS方式
                byte[] fileBuf = file.getBytes();
                String fileExtName = tempFileName.substring(tempFileName.lastIndexOf("."));

                //建立连接
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getTrackerServer();
                StorageServer storageServer = tracker.getStoreStorage(trackerServer);
                storageClient1 = new StorageClient1(trackerServer,storageServer);

                // 设置元信息
                NameValuePair[] metaList = new NameValuePair[3];
                metaList[0] = new NameValuePair("fileName",tempFileName);
                metaList[1] = new NameValuePair("fileExtName", fileExtName);
                metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));

                // 上传文件，获得fileId
                fileId = storageClient1.upload_file1(fileBuf,fileExtName,metaList);
                log.info("获取到得文件id为:" + fileId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }finally {
            try {
                storageClient1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileId;
    }
}
