package com.backend.toyswap.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.backend.toyswap.model.Toy;
import com.backend.toyswap.model.User;
import com.backend.toyswap.repository.ToyRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class ToyService {

    @Autowired
    private ToyRepository toyRepository;

    @Autowired
    private UserService userService;
    
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    
    private AmazonS3 s3client;
    	
    @SuppressWarnings("deprecation")
	@PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
    }
    
    public List<Toy> getAll(long id) {
        //  get all toys and return them as List<Toy>
        return toyRepository.findAllForUserId(id);
    }

    public List<Toy> getByName(String name, long id) {
        //  get a toy by name
        return toyRepository.findAllForUserId(id).stream()
                .filter(toy -> toy.getToy_Name().toLowerCase().contains(name.toLowerCase()) )
                .collect(Collectors.toList());
    }


    public Optional<Toy> getByID(Long id) {
        //  get a toy by ID if it exists
        return toyRepository.findById(id);
    }
    
    public void deleteById(Long id) {
        //  delete the toy by id
        toyRepository.deleteById(id);
    }

    public Toy create(Toy newToy) {
        //  save the toy to DB and return the saved post
    	User user = newToy.getUser();
    	user.setBalance((user.getBalance() + newToy.getToy_Price()));
    	userService.update(user);
    	// upload image to amazon s3
    	MultipartFile imgFile = newToy.getToyImage();
        String  path = getImagePath(imgFile);
        // setting the URL for the image
        newToy.setToy_Photo(path);
        return toyRepository.save(newToy);
    }
    
    public List<Toy> getAllByUserId(Long userId) {
        //  get all toys by user id and return them as List<Toy>
        return toyRepository.findAllByUserId(userId);
    }
    
    private String getImagePath(MultipartFile imgFile) {
        String fileNameWithOutExt = FilenameUtils.removeExtension(imgFile.getOriginalFilename());
        String filename = fileNameWithOutExt + "_" + new Date().getTime() + "."
                + FilenameUtils.getExtension(imgFile.getOriginalFilename());
        File tempFile = this.convert(imgFile);
        s3client.putObject(new PutObjectRequest(bucketName, filename, tempFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String imagePath = s3client.getUrl(this.bucketName, filename).toString();
        tempFile.delete();
        return imagePath;
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

}
