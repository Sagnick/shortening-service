package com.mercedesbenz.shortening.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercedesbenz.shortening.entity.EShorteningUrl;

public interface URLRepository extends JpaRepository<EShorteningUrl, Long> {

	@Query(value = "SELECT u FROM EShorteningUrl u where u.actualUrl = :actualUrl")
	Optional<EShorteningUrl> findByActualUrl(@Param(value = "actualUrl") String actualUrl);

	@Query(value = "SELECT u FROM EShorteningUrl u where u.shortendUrl = :shortendUrl")
	Optional<EShorteningUrl> findByShortenUrl(@Param(value = "shortendUrl") String shortendUrl);

	@Query(value = "SELECT u FROM EShorteningUrl u where u.createdDate < :currentDate and u.createdDate > :previouseDate")
	Optional<List<EShorteningUrl>> findLastActivity(@Param(value = "currentDate") LocalDateTime currentDate,
			@Param(value = "previouseDate") LocalDateTime previouseDate);
}
