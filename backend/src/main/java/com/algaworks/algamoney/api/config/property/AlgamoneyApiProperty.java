package com.algaworks.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

	private final Security security = new Security();
	
	public Security getSecurity() {
		return security;
	}
	
	
	public static class Security{
	
		private boolean enableHttps;
		
		private String allowedOfOrigin;

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
	

}
