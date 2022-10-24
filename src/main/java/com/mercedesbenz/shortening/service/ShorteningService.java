package com.mercedesbenz.shortening.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercedesbenz.shortening.entity.EShorteningUrl;
import com.mercedesbenz.shortening.repository.URLRepository;
import com.mercedesbenz.shortening.utility.ShorteningUtility;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShorteningService implements IShorteningService {
	@Autowired
	private URLRepository urlRepository;

	@Override
	public EShorteningUrl getShorteningUrl(String userdefinedUrl) {
		Optional<EShorteningUrl> dbUrl = urlRepository.findByShortenUrl(userdefinedUrl);
		if (!dbUrl.isPresent()) {
			dbUrl = urlRepository.findByActualUrl(userdefinedUrl);
		}

		if (dbUrl != null && dbUrl.isPresent()) {
			EShorteningUrl shorteningUrl = dbUrl.get();
			EShorteningUrl response = EShorteningUrl.builder().id(shorteningUrl.getId())
					.actualUrl(shorteningUrl.getActualUrl()).shortendUrl(shorteningUrl.getShortendUrl())
					.statusCode(shorteningUrl.getStatusCode()).statusMessage(shorteningUrl.getStatusMessage())
					.createdDate(shorteningUrl.getCreatedDate()).build();
			return response;
		}
		EShorteningUrl response = EShorteningUrl.builder().actualUrl(userdefinedUrl).shortendUrl("Not Applicable")
				.statusCode(404).statusMessage("Url does not exits!!!").createdDate(LocalDateTime.now()).build();
		return response;
	}

	@Override
	public List<EShorteningUrl> getLastActivity() {
		Optional<List<EShorteningUrl>> dbUrls = urlRepository.findLastActivity(LocalDateTime.now(),
				LocalDateTime.now().minusDays(1));
		if (dbUrls != null && dbUrls.isPresent()) {
			List<EShorteningUrl> shorteningUrls = dbUrls.get();
			List<EShorteningUrl> responses = new ArrayList<>();
			for (EShorteningUrl shorteningUrl : shorteningUrls) {
				EShorteningUrl response = EShorteningUrl.builder().id(shorteningUrl.getId())
						.actualUrl(shorteningUrl.getActualUrl()).shortendUrl(shorteningUrl.getShortendUrl())
						.statusCode(shorteningUrl.getStatusCode()).statusMessage(shorteningUrl.getStatusMessage())
						.createdDate(shorteningUrl.getCreatedDate()).build();
				responses.add(response);
			}

			return responses;
		}

		EShorteningUrl response = EShorteningUrl.builder().actualUrl("Not Applicable").shortendUrl("Not Applicable")
				.statusCode(404).statusMessage("No User Activity").createdDate(LocalDateTime.now()).build();
		return Arrays.asList(response);
	}

	@Override
	public EShorteningUrl createShorteningUrl(String actualUrl) {
		// Validation checks to determine if the supplied URL is valid
		if (!isValidUrl(actualUrl)) {
			log.error("Malformed Url provided");
			EShorteningUrl response = EShorteningUrl.builder().id(-1L).actualUrl(actualUrl).shortendUrl("Incorrect Url")
					.statusCode(400).statusMessage("Malformed Url provided").createdDate(LocalDateTime.now()).build();

			// returns a custom body with error message and bad request status code
			return response;
		}

		Optional<EShorteningUrl> dbUrl = urlRepository.findByActualUrl(actualUrl);

		if (!dbUrl.isPresent()) {
			ShorteningUtility utility = new ShorteningUtility();
			String shortenedURL = utility.shortenURL(actualUrl);
			EShorteningUrl dbEntity = EShorteningUrl.builder().actualUrl(actualUrl).shortendUrl(shortenedURL)
					.statusCode(200).statusMessage("Success").createdDate(LocalDateTime.now()).build();
			EShorteningUrl response = urlRepository.save(dbEntity);
			return response;
		}
		EShorteningUrl shorteningUrl = dbUrl.get();
		EShorteningUrl response = EShorteningUrl.builder().id(shorteningUrl.getId()).actualUrl(actualUrl)
				.shortendUrl(shorteningUrl.getShortendUrl()).statusCode(shorteningUrl.getStatusCode())
				.statusMessage(shorteningUrl.getStatusMessage()).createdDate(shorteningUrl.getCreatedDate()).build();
		return response;
	}

	private Boolean isValidUrl(String url) {
		try {
			URL obj = new URL(url);
			obj.toURI();
			return true;
		} catch (MalformedURLException exception) {
			return false;
		} catch (URISyntaxException exception) {
			return false;
		}
	}

	@Override
	public EShorteningUrl createCustomShorteningUrl(String actualUrl, String customUrl) {
		// Validation checks to determine if the supplied URL is valid
		if (!isValidUrl(actualUrl)) {
			log.error("Malformed Url provided");
			EShorteningUrl response = EShorteningUrl.builder().id(-1L).actualUrl(actualUrl).shortendUrl("Incorrect Url")
					.statusCode(400).statusMessage("Malformed Url provided").createdDate(LocalDateTime.now()).build();

			// returns a custom body with error message and bad request status code
			return response;
		}

		if (StringUtils.isEmpty(customUrl)) {
			log.error("Malformed Url provided");
			EShorteningUrl response = EShorteningUrl.builder().id(-1L).actualUrl(actualUrl)
					.shortendUrl("Incorrect Custom Url").statusCode(400).statusMessage("Malformed Url provided")
					.createdDate(LocalDateTime.now()).build();
			// returns a custom body with error message and bad request status code
			return response;
		}

		Optional<EShorteningUrl> dbUrl = urlRepository.findByActualUrl(actualUrl);

		if (!dbUrl.isPresent()) {
			EShorteningUrl dbEntity = EShorteningUrl.builder().actualUrl(actualUrl).shortendUrl(customUrl)
					.statusCode(200).statusMessage("Success").createdDate(LocalDateTime.now()).build();
			EShorteningUrl response = urlRepository.save(dbEntity);
			return response;
		}
		EShorteningUrl shorteningUrl = dbUrl.get();
		EShorteningUrl response = EShorteningUrl.builder().id(shorteningUrl.getId()).actualUrl(actualUrl)
				.shortendUrl(shorteningUrl.getShortendUrl()).statusCode(shorteningUrl.getStatusCode())
				.statusMessage(shorteningUrl.getStatusMessage()).createdDate(shorteningUrl.getCreatedDate()).build();
		return response;
	}
}