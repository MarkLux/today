package com.marklux.services;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by mark on 17/11/12.
 */
@Service
public class FileService {
    @Value("${qiniu.access-key}")
    private String accesskey;
    @Value("${qiniu.secret-key}")
    private String secretkey;
    @Value("${qiniu.bucket-name}")
    private String bucketname;

    private Auth qiniuAuth;

    @PostConstruct
    private void init() {
        this.qiniuAuth = Auth.create(accesskey,secretkey);
    }

    public String getUploadToken() {
        return this.qiniuAuth.uploadToken(this.bucketname);
    }
}
