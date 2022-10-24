package com.mercedesbenz.shortening.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MShorteningUrl {
	private String actualUrl;
	private String shortendUrl;
	private Integer statusCode;
	private String statusMessage;
	private String createdDate;
}
