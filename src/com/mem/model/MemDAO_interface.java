package com.mem.model;
import java.util.List;

public interface MemDAO_interface {
  public void insert(MemVO memVO);
  public void update(MemVO memVO);
  public void delete(String memno);
  public MemVO findByPrimarykey (String memno);
  public MemVO memberLogin(String mAccount,String mPw); 
  public void  identify(MemVO memVO );
  public List<MemVO> getALL();
}
