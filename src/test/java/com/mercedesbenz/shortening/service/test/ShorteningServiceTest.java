package com.mercedesbenz.shortening.service.test;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercedesbenz.shortening.entity.EShorteningUrl;
import com.mercedesbenz.shortening.repository.URLRepository;
import com.mercedesbenz.shortening.service.ShorteningService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShorteningService.class)
@ContextConfiguration(classes = { ShorteningService.class })
public class ShorteningServiceTest {

	@Autowired
	ShorteningService shorteningService;

	@MockBean
	URLRepository urlRepository;

	@Test
	void testGetShorteningUrlByActualTest() {
		when(urlRepository.findByActualUrl(Mockito.anyString())).thenReturn(fetchShortendUrl());
		EShorteningUrl eShorteningUrl = shorteningService
				.getShorteningUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/");
		Assert.assertNotNull(eShorteningUrl);
	}

	@Test
	void testGetShorteningUrlByShortUrlTest() {
		when(urlRepository.findByShortenUrl(Mockito.anyString())).thenReturn(fetchShortendUrl());
		EShorteningUrl eShorteningUrl = shorteningService
				.getShorteningUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/");
		Assert.assertNotNull(eShorteningUrl);
	}

	@Test
	void testLastActivity() {
		when(urlRepository.findLastActivity(Mockito.any(), Mockito.any())).thenReturn(lastActivities());
		EShorteningUrl eShorteningUrl = shorteningService
				.getShorteningUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/");
		Assert.assertNotNull(eShorteningUrl);
	}

	private Optional<EShorteningUrl> fetchShortendUrl() {
		EShorteningUrl eShorteningUrl = EShorteningUrl.builder().id(1L)
				.actualUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/")
				.shortendUrl("http://merced.es/mgpN`i").statusCode(200).statusMessage("Success")
				.createdDate(LocalDateTime.now()).build();
		return Optional.of(eShorteningUrl);
	}

	private Optional<List<EShorteningUrl>> lastActivities() {
		List<EShorteningUrl> userActivities = new ArrayList<>();
		EShorteningUrl shortendUrl = EShorteningUrl.builder().id(1L)
				.actualUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/")
				.shortendUrl("http://merced.es/mgpN`i").statusCode(200).statusMessage("Success")
				.createdDate(LocalDateTime.now()).build();
		userActivities.add(shortendUrl);
		return Optional.of(userActivities);
	}

}
