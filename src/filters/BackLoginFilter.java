package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeVO;

public class BackLoginFilter implements Filter {
  private FilterConfig config;

  public void destroy() {
	config = null;
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	  throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;

	HttpSession session = req.getSession();

	// 設定網頁的到期時間，一旦過期則必須到伺服器上重新呼叫
	res.setDateHeader("Expires", -1);
	// Cache-Control 指定請求和響應應遵循的快取機制 no-cache指示請求或響應訊息是不能快取的
	res.setHeader("Cache-Control", "no-cache");
	// 用於設定禁止瀏覽器從本地快取中呼叫頁面內容，設定後一旦離開頁面就無法從Cache中再調出
	res.setHeader("Pragma", "no-cache");

	EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");

	if (employeeVO == null) {
	  session.setAttribute("location", req.getRequestURI());
	  res.sendRedirect(req.getContextPath() + "/backlogin.jsp");
	  return;
	} else {
	  chain.doFilter(request, response);
	}
  }

  public void init(FilterConfig config) throws ServletException {
	this.config = config;
  }

}
