package com.features.model;

import java.util.List;

public interface FeaturesDAO_interface {
	public void insert(FeaturesVO features);
	public void update(FeaturesVO features);
	public FeaturesVO findByPrimaryKey(String feano);
	public List<FeaturesVO> getAll();
}
