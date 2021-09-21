package com.algaworks.algamoney.api.storage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algamoney.api.config.property.AlgamoneyApiProperty;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;

@Component
public class S3 {

	private static final Logger logger = LoggerFactory.getLogger(S3.class);
	
	@Autowired
	private AlgamoneyApiProperty property;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	public String saveTemporarily(MultipartFile file) {
		
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		
		ObjectMetadata  objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		
		String uniqueName = createUniqueName(file.getOriginalFilename()); 
		
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					property.getS3().getBucket(), 
					uniqueName,
					file.getInputStream(),
					objectMetadata)
					.withAccessControlList(acl);
			
			putObjectRequest.setTagging(new ObjectTagging(
					Arrays.asList(new Tag("expirar", "true"))));
			
			amazonS3.putObject(putObjectRequest);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Arquivo {} enviado com sucesso para o S3",
						file.getOriginalFilename());
			}
			
			return uniqueName;
			
		} catch (IOException e) {
			throw new RuntimeException("Problemas ao tentar enviar arquivos para o S3",  e);
		}
	}
	
	public String setUpUrl(String object) {
		return "\\\\" + property.getS3().getBucket() + 
				".s3.amazonaws.com/" + object;
	}
	

	public void save(String object) {
		SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(
				property.getS3().getBucket(),
				object,
				new ObjectTagging(Collections.emptyList()));
		
		amazonS3.setObjectTagging(setObjectTaggingRequest);
		
	}

	public void delete(String object) {
	
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
				property.getS3().getBucket(), object);
		
		amazonS3.deleteObject(deleteObjectRequest);
	}
	
	public void update(String oldObject, String newObject) {
		if (StringUtils.hasText(oldObject)) {
			this.delete(oldObject);
		}
		
		save(newObject);
	}
	
	private String createUniqueName(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}



}
