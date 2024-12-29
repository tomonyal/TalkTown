package com.talktown;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Util {
    public static final String BUCKET_NAME = "talktown-pictures"; // あなたのS3バケット名を設定してください
    public static final Region REGION = Region.AP_NORTHEAST_1; // 東京リージョンを使用
    public static final String ENDPOINT = "https://s3.ap-northeast-1.amazonaws.com"; // エンドポイントを設定

    public static S3Client createS3Client() {
        return S3Client.builder()
                .region(REGION)
                .endpointOverride(URI.create(ENDPOINT))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    public static String uploadToS3(String fileName, InputStream is) throws IOException {
        S3Client s3Client = createS3Client();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(is, is.available()));
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
    }
}
