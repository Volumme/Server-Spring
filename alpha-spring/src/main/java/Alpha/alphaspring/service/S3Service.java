package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.FileDetail;
import Alpha.alphaspring.Utils.AmazonS3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {
    @Autowired
    private AmazonS3Utils amazonS3Utils;
    @Autowired
    private StaticURIService staticURIService;

    public FileDetail save(String name, MultipartFile multipartFile) {
        FileDetail fileDetail = FileDetail.multipartOf(multipartFile);
        amazonS3Utils.store(fileDetail.getPath(), multipartFile);
        staticURIService.save(name, fileDetail.getPath());
        return fileDetail;
    }

    public byte[] load(String uri) throws IOException {
        return amazonS3Utils.getObject(uri);
    }
}
