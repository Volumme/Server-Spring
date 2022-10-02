package Alpha.alphaspring.service;

import Alpha.alphaspring.Utils.AmazonS3Utils;
import Alpha.alphaspring.domain.StaticURI;
import Alpha.alphaspring.repository.StaticURIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticURIService {
//    @Value("${S3.baseURI}")
//    private String baseURI;
    @Autowired
    private StaticURIRepository staticURIRepository;

    @Autowired
    private AmazonS3Utils amazonS3Utils;

    public void save(String name, String path){
        staticURIRepository.save(StaticURI
                .builder()
                .name(name)
                .uri(path)
                .type(null)
                .user(null)
                .build()
        );
    }

    public StaticURI findByName(String name){
        return staticURIRepository.findByName(name).orElseThrow(()-> new RuntimeException("no file exists"));
    }

}
