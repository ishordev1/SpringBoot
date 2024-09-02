package com.collage.Dto;

import com.collage.Entity.Assignment;

import lombok.Data;

@Data
public class MaterialReferenceDto {

	 private String id;
	    private String pdfName;
	    private String description;
	    private Assignment assignment;;
}

