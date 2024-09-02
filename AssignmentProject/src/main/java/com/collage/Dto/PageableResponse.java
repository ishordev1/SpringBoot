package com.collage.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {
private List<T> content;
private int totalPageNumber;
private int totleElements;
private int pageNumber;
private boolean lastPage;
//it means how much data want in single page
private int pageSize;

}
