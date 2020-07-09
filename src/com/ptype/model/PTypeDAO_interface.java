package com.ptype.model;

import java.util.*;

public interface PTypeDAO_interface {
		public void insert(PTypeVO PTypeVO);
		public void update(PTypeVO PTypeVO);
		public void delete (String pTno);
		public PTypeVO findByPrimaryKey(String pTno);
		public List<PTypeVO>getAll();
}
