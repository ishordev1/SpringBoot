package com.collage.Dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ImageResponse {
    private String imageName;
    private String message;
    private boolean success;
    private HttpStatus Status;
}
