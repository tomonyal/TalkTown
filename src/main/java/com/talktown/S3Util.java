package com.talktown;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import java.net.URI;

public class S3Util {
    public static final String BUCKET_NAME = "talktown-pictures"; // あなたのS3バケット名を設定してください
    public static final Region REGION = Region.AP_NORTHEAST_1; // 東京リージョンを使用
    public static final String ENDPOINT = "https://s3.ap-northeast-1.amazonaws.com"; // エンドポイントを設定

    public static S3Client createS3Client() {
        return S3Client.builder()
                .region(REGION)
                .endpointOverride(URI.create(ENDPOINT))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .serviceConfiguration(S3Configuration.builder().build())
                .build();
    }
}
