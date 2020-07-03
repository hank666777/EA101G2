package com.features.model;

import java.io.Serializable;

public class FeaturesVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String feano;
	private String feaName;
	
	public FeaturesVO() {
		super();
	}

	public FeaturesVO(String feano, String feaname) {
		super();
		this.feano = feano;
		this.feaName = feaname;
	}

	public String getFeano() {
		return feano;
	}

	public void setFeano(String feano) {
		this.feano = feano;
	}

	public String getFeaName() {
		return feaName;
	}

	public void setFeaName(String feaname) {
		this.feaName = feaname;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
