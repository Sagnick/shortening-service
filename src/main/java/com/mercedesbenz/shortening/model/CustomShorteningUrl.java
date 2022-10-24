package com.mercedesbenz.shortening.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomShorteningUrl {
	private String actualUrl;
	private String customUrl;
}
