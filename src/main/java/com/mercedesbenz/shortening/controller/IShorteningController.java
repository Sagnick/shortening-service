package com.mercedesbenz.shortening.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercedesbenz.shortening.model.CustomShorteningUrl;
import com.mercedesbenz.shortening.model.MShorteningUrl;
import com.mercedesbenz.shortening.model.ShorteningUrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Shortening Controller", description = "Rest APIs related to shortening url for user whose information is available as part of request header")
public interface IShorteningController {
	@ApiOperation(value = "Get actual url for shorten url")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 404, message = "Data Not Found"),
			@ApiResponse(code = 500, message = "Internal Server error.Please see detailed error in response") })
	@GetMapping("/shortening")
	public ResponseEntity<MShorteningUrl> getShorteningUrl(
			@RequestParam(value = "URL", required = true) @ApiParam(value = "Get Actual url from shorten url") String userdefinedUrl);

	@ApiOperation(value = "Get user activity for last day")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 404, message = "Data Not Found"),
			@ApiResponse(code = 500, message = "Internal Server error.Please see detailed error in response") })
	@GetMapping("/userActivity")
	public ResponseEntity<List<MShorteningUrl>> getLastActivity();

	@ApiOperation(value = "Create shortening url for actual url")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 404, message = "Data Not Found"),
			@ApiResponse(code = 500, message = "Internal Server error.Please see detailed error in response") })
	@PostMapping(value = "/shortening", produces = "application/json", consumes = "application/json")
	public ResponseEntity<MShorteningUrl> createShorteningUrl(
			@ApiParam(name = "URL", value = "Url which needs to be shortend", required = true) @RequestBody @Valid ShorteningUrl userdefinedUrl);

	@ApiOperation(value = "Create custom shortening url for actual url")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 404, message = "Data Not Found"),
			@ApiResponse(code = 500, message = "Internal Server error.Please see detailed error in response") })
	@PostMapping(value = "/customShortening", produces = "application/json", consumes = "application/json")
	public ResponseEntity<MShorteningUrl> createCustomShorteningUrl(
			@ApiParam(name = "URL", value = "Url which needs to be shortend", required = true) @RequestBody @Valid CustomShorteningUrl userdefinedUrl);

}
