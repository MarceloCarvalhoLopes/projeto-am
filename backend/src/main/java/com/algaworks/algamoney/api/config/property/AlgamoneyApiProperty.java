package com.algaworks.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private final Security security = new Security();
	
	private final Mail mail = new Mail();
	
	private final S3 s3 = new S3();
	
	public S3 getS3() {
		return s3;
	}
	
	
	public Mail getMail() {
		return mail;
	}

	public Security getSecurity() {
		return security;
	}
	
	public static class S3{
		
		private String accesKeyId;
		
		private String secretAccessKey;
		
		private String bucket = "aw-testedev-algamoney-files";
		
		

		public String getBucket() {
			return bucket;
		}

		public void setBucket(String bucket) {
			this.bucket = bucket;
		}

		public String getAccesKeyId() {
			return accesKeyId;
		}

		public void setAccesKeyId(String accesKeyId) {
			this.accesKeyId = accesKeyId;
		}

		public String getSecretAccessKey() {
			return secretAccessKey;
		}

		public void setSecretAccessKey(String secretAccessKey) {
			this.secretAccessKey = secretAccessKey;
		}
		
		
	}

	public static class Security{
	
		private boolean enableHttps;
		
		private String allowedOfOrigin = "http://localhost:4200";

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		public String getAllowedOfOrigin() {
			return allowedOfOrigin;
		}

		public void setAllowedOfOrigin(String allowedOfOrigin) {
			this.allowedOfOrigin = allowedOfOrigin;
		}
		
	}
	
	
	public static class Mail{
		
		private String host;
		private Integer port;
		private String username;
		private String password;
		
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
	

}
