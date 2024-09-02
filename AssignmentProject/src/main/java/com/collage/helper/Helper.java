package com.collage.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.collage.Dto.PageableResponse;

public class Helper {
public static  <U,V> PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){
	List<U> content=page.getContent();
	List<V> contentDto=content.stream().map(obj->new ModelMapper().map(obj, type)).collect(Collectors.toList());
	
	
	PageableResponse<V> pageableResponse=new PageableResponse();
	pageableResponse.setContent(contentDto);
	pageableResponse.setLastPage(page.isLast());
	pageableResponse.setPageNumber(page.getNumber()+1);
	pageableResponse.setPageSize(page.getSize());
	pageableResponse.setTotalPageNumber(page.getTotalPages());
	pageableResponse.setTotleElements(page.getNumberOfElements());
	
	return pageableResponse;
	
	
}
}
