package com.algaworks.algamoney.api.repositories.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.algaworks.algamoney.api.AlgamoneyApiApplication;
import com.algaworks.algamoney.api.models.FinancialRelease;
import com.algaworks.algamoney.api.storage.S3;

public class FinancialAttachListener {

	@PostLoad
	public void postLoad(FinancialRelease financial) {
		if (StringUtils.hasText(financial.getAttach())) {
			
			S3 s3 = AlgamoneyApiApplication.getBean(S3.class);
			financial.setUrlAttach(s3.setUpUrl(financial.getAttach()));
			
		}
	}
}
