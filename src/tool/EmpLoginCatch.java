package tool;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import com.employee.model.EmployeeVO;

public class EmpLoginCatch {
  private static EmpLoginCatch instance = new EmpLoginCatch();

  // 單例模型
  private EmpLoginCatch() {

  }

  public static EmpLoginCatch getInstance() {
	return instance;
  }

  // key值：登錄用戶VO，value值：登錄用戶sessionId
  private Map<EmployeeVO, String> LoginEmployeeVOSession = new HashMap<EmployeeVO, String>();

  // key值：登錄用戶sessionId，value值：登錄用戶session對象
  private Map<String, HttpSession> LoginSession = new HashMap<String, HttpSession>();

  /**
   * 通過登錄員工物件獲取對應登錄用戶的sessionId
   */
  public String getSessionIdByEmployeeVO(EmployeeVO employeeVO) {
	return LoginEmployeeVOSession.get(employeeVO);
  }

  /**
   * 通過sessionId獲取對應的session物件
   */
  public HttpSession getSessionBySessionId(String sessionId) {
	return LoginSession.get(sessionId);
  }

  /**
   * 存儲登錄名與相應的登錄sessionId至緩存物件
   */
  public void setSessionIdByEmployeeVO(EmployeeVO employeeVO, String sessionId) {
	LoginEmployeeVOSession.put(employeeVO, sessionId);
  }

  /**
   * 存儲sessionId與對應的session對象至緩存物件
   */
  public void setSessionBySessionId(String sessionId, HttpSession session) {
	LoginSession.put(sessionId, session);
  }
}
