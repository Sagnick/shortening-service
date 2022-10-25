package com.mercedesbenz.shortening.controller.test;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercedesbenz.shortening.controller.ShorteningController;
import com.mercedesbenz.shortening.entity.EShorteningUrl;
import com.mercedesbenz.shortening.service.ShorteningService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShorteningController.class)
@ContextConfiguration(classes = { ShorteningController.class })
class ShorteningControllerTest {

	@MockBean
	private ShorteningService shorteningService;

	@Test
	void testGetShorteningUrl() throws Exception {
		when(this.shorteningService.getShorteningUrl(Mockito.anyString())).thenReturn(fetchShortendUrl());
	}

	@Test
	void testLastActivity() throws Exception {
		when(this.shorteningService.getLastActivity()).thenReturn(lastActivities());
	}

	@Test
	void testCreateShorteningUrl() throws Exception {
		when(this.shorteningService.createShorteningUrl(Mockito.anyString())).thenReturn(shortendUrl());
	}

	@Test
	void testCustomShorteningUrl() throws Exception {
		when(this.shorteningService.createCustomShorteningUrl(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(shortendUrl());
	}

	private EShorteningUrl fetchShortendUrl() {
		EShorteningUrl eShorteningUrl = EShorteningUrl.builder().id(1L)
				.actualUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/")
				.shortendUrl("http://merced.es/mgpN`i").statusCode(200).statusMessage("Success")
				.createdDate(LocalDateTime.now()).build();
		return eShorteningUrl;
	}

	private List<EShorteningUrl> lastActivities() {
		List<EShorteningUrl> userActivities = new ArrayList<>();
		EShorteningUrl shortendUrl = EShorteningUrl.builder().id(1L)
				.actualUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/")
				.shortendUrl("http://merced.es/mgpN`i").statusCode(200).statusMessage("Success")
				.createdDate(LocalDateTime.now()).build();
		userActivities.add(shortendUrl);
		return userActivities;
	}

	private EShorteningUrl shortendUrl() {
		EShorteningUrl shortendUrl = EShorteningUrl.builder().id(1L)
				.actualUrl("https://group.mercedes-benz.com/karriere/berufserfahrene/direkteinstieg/")
				.shortendUrl("http://merced.es/custom").statusCode(200).statusMessage("Success")
				.createdDate(LocalDateTime.now()).build();
		return shortendUrl;
	}
}
