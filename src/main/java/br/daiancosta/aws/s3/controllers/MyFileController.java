package br.daiancosta.aws.s3.controllers;

import br.daiancosta.aws.s3.models.FileVo;
import br.daiancosta.aws.s3.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-file")
public class MyFileController {

    private final S3Service s3Service;

    @Autowired
    public MyFileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseEntity<List<FileVo>> get() {
        return new ResponseEntity<>(s3Service.listObjects(), HttpStatus.OK);
    }

}
