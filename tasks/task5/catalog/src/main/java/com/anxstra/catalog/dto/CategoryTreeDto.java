package com.anxstra.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryTreeDto(Long id, String name, String type, Long parent, List<CategoryTreeDto> children) {

}
