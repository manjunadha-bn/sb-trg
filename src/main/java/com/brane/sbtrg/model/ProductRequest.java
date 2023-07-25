package com.brane.sbtrg.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductRequest {

	@Schema(example = "TV", defaultValue = "TV")
	private String name;

	@Schema(example = "Sony", defaultValue = "SONY")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}