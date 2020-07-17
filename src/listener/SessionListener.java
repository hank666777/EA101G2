package listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.employee.model.EmployeeVO;

import tool.EmpLoginCatch;

//@WebListener
public class SessionListener implements HttpSessionAttributeListener {

	private static final String LOGIN_EMP = "employeeVO";

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		// 監聽到session屬性值發生添加操作,獲取對應操作的屬性名
		// 員工姓名
		String habeName = hsbe.getName();
		System.out.println("hsbe.getName(): " + hsbe.getName());
		
		if ("employeeVO".equals(hsbe.getName())) {
			if (LOGIN_EMP.equals(habeName)) {
// 獲取添加的屬性值，即用戶登錄名(EmployeeVO)
				EmployeeVO attrVal = (EmployeeVO) hsbe.getValue();
				System.out.println("attrVal: " + attrVal.getEmpno());

				// 獲取該次操作的session對象
				HttpSession session = hsbe.getSession();
				// 該次操作的session對象ID
				String sessionId = session.getId();
// 從暫存對象裏面，獲得該用戶登錄名對應的sessionID值
				String sessionId2 = EmpLoginCatch.getInstance().getSessionIdByEmployeeVO(attrVal);

				if (null == sessionId2) {// 未獲得結果，不需要清理前次登錄用戶會話信息

				} else {
					HttpSession session2 = EmpLoginCatch.getInstance().getSessionBySessionId(sessionId2);// 獲取前次該用戶登錄對應的session對象
					session2.invalidate();// 清理前次登錄用戶會話存儲信息，使得前次登錄失效
				}

				// 完成該次登錄用戶登錄名、sessionID，session物件的暫存物件存儲
				EmpLoginCatch.getInstance().setSessionIdByEmployeeVO(attrVal, sessionId);
				EmpLoginCatch.getInstance().setSessionBySessionId(sessionId, session);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}
}
