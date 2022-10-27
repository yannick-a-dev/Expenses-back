package com.arrays.arrays.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	protected LocalDateTime timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String reason;
	protected String message;
	protected String developerMessage;
	protected Map<?, ?> data;
}
