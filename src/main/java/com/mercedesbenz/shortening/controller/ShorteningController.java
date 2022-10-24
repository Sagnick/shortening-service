package com.mercedesbenz.shortening.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercedesbenz.shortening.entity.EShorteningUrl;
import com.mercedesbenz.shortening.model.CustomShorteningUrl;
import com.mercedesbenz.shortening.model.MShorteningUrl;
import com.mercedesbenz.shortening.model.ShorteningUrl;
import com.mercedesbenz.shortening.service.IShorteningService;

@RestController
@RequestMapping(path = "/v1")
public class ShorteningController implements IShorteningController {

	@Autowired
	IShorteningService shorteningService;

	@Autowired
	HttpServletRequest request;

	@Override
	public ResponseEntity<MShorteningUrl> getShorteningUrl(String userdefinedUrl) {
		EShorteningUrl response = this.shorteningService.getShorteningUrl(userdefinedUrl);
		if (response != null && (response.getStatusCode().equals(200) || response.getStatusCode().equals(201))) {
			MShorteningUrl shorteningUrl = MShorteningUrl.builder().actualUrl(response.getActualUrl())
					.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
					.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString())
					.build();
			return ResponseEntity.ok(shorteningUrl);
		}
		MShorteningUrl shorteningUrl = MShorteningUrl.builder().actualUrl(response.getActualUrl())
				.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
				.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString()).build();
		return ResponseEntity.badRequest().body(shorteningUrl);
	}

	@Override
	public ResponseEntity<List<MShorteningUrl>> getLastActivity() {
		List<EShorteningUrl> responses = this.shorteningService.getLastActivity();
		List<MShorteningUrl> userActivities = new ArrayList<>();
		for (EShorteningUrl response : responses) {
			MShorteningUrl userActivity = MShorteningUrl.builder().actualUrl(response.getActualUrl())
					.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
					.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString())
					.build();
			userActivities.add(userActivity);
		}
		return ResponseEntity.ok(userActivities);
	}

	@Override
	public ResponseEntity<MShorteningUrl> createShorteningUrl(@Valid ShorteningUrl userdefinedUrl) {
		String actualUrl = userdefinedUrl.getActualUrl().trim();
		EShorteningUrl response = this.shorteningService.createShorteningUrl(actualUrl);
		if (response != null && (response.getStatusCode().equals(200) || response.getStatusCode().equals(201))) {
			MShorteningUrl shorteningUrl = MShorteningUrl.builder().actualUrl(response.getActualUrl())
					.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
					.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString())
					.build();

			return ResponseEntity.ok(shorteningUrl);
		}
		MShorteningUrl shorteningUrl = MShorteningUrl.builder().actualUrl(response.getActualUrl())
				.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
				.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString()).build();
		return ResponseEntity.badRequest().body(shorteningUrl);
	}

	@Override
	public ResponseEntity<MShorteningUrl> createCustomShorteningUrl(@Valid CustomShorteningUrl userdefinedUrl) {
		String domain = "http://merced.es/";
		String actualUrl = userdefinedUrl.getActualUrl().trim();
		String customUrl = domain + userdefinedUrl.getCustomUrl().trim();
		EShorteningUrl response = this.shorteningService.createCustomShorteningUrl(actualUrl, customUrl);
		if (response != null && (response.getStatusCode().equals(200) || response.getStatusCode().equals(201))) {
			MShorteningUrl result = MShorteningUrl.builder().actualUrl(response.getActualUrl())
					.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
					.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString())
					.build();

			return ResponseEntity.ok(result);
		}
		MShorteningUrl result = MShorteningUrl.builder().actualUrl(response.getActualUrl())
				.shortendUrl(response.getShortendUrl()).statusCode(response.getStatusCode())
				.statusMessage(response.getStatusMessage()).createdDate(response.getCreatedDate().toString()).build();
		return ResponseEntity.badRequest().body(result);
	}
}