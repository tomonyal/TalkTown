package com.talktown;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Util {
    public static final String BUCKET_NAME = "talktown-pictures"; // あなたのS3バケット名を設定してください
    public static final Region REGION = Region.AP_NORTHEAST_1; // 東京リージョンを使用
    public static final String ENDPOINT = "https://s3.ap-northeast-1.amazonaws.com"; // エンドポイントを設定
        public static S3Client createS3Client() {
            ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.builder()
                    .profileName("default")
                    .build();
    
            // クレデンシャルの情報をログ出力
            AwsCredentials credentials = credentialsProvider.resolveCredentials();
            System.out.println("AWS Access Key ID: " + credentials.accessKeyId());
            // Note: Do not log secrets in production. This is for debugging purposes only.
            // System.out.println("AWS Secret Access Key: " + credentials.secretAccessKey());
    
            return S3Client.builder()
                    .region(REGION)
                    .endpointOverride(URI.create(ENDPOINT))
                    .credentialsProvider(credentialsProvider)
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

    public static void deleteFromS3(String fileName) {
        S3Client s3Client = createS3Client();
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }
}
