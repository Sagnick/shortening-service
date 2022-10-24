package com.mercedesbenz.shortening.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mercedesbenz.shortening.entity.EShorteningUrl;

@Service
public interface IShorteningService {
	public EShorteningUrl getShorteningUrl(String userdefinedUrl);

	public List<EShorteningUrl> getLastActivity();

	public EShorteningUrl createShorteningUrl(String actualUrl);

	public EShorteningUrl createCustomShorteningUrl(String actualUrl, String customUrl);
}
