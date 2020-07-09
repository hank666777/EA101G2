package com.suggest.model;

import java.util.List;

public interface SugestDAO_interface {
     public void insert (SugestVO sugestVO);
     public void update (SugestVO sugestVO);
     public void delete (String suggestno);
     public SugestVO findbyPrimarykey(String suggestno);
     public List<SugestVO> findbyMemno(String memno);
     public List<SugestVO> getAll();
}
