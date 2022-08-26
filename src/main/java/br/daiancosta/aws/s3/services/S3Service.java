package br.daiancosta.aws.s3.services;

import br.daiancosta.aws.s3.models.FileVo;
import br.daiancosta.aws.s3.models.FileVoBuilder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service {

    public List<FileVo> listObjects() {

        final String bucketName = System.getenv().get("bucket");
        final String region = System.getenv().get("region");
        final InstanceProfileCredentialsProvider instanceProfileCredentialsProvider = InstanceProfileCredentialsProvider.create();

        S3Client s3 = S3Client.builder().credentialsProvider(instanceProfileCredentialsProvider).region(Region.of(region)).build();

        ListObjectsRequest listObjects = ListObjectsRequest.builder().bucket(bucketName).build();
        ListObjectsResponse res = s3.listObjects(listObjects);
        List<S3Object> objects = res.contents();

        return objects.stream().map(object -> new FileVoBuilder(object.key(), object.size() + " KBs").build()).collect(Collectors.toList());
    }
}
