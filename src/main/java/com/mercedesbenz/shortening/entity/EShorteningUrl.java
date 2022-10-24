package com.mercedesbenz.shortening.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHORTENING_URL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EShorteningUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private String actualUrl;
	private String shortendUrl;
	private Integer statusCode;
	private String statusMessage;
	private LocalDateTime createdDate;
}
