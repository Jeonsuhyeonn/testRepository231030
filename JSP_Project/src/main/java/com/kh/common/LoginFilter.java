package com.kh.common;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.kh.member.model.vo.Member;
/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/myPage.me", "/update.me","/delete.me"})
public class LoginFilter extends HttpFilter implements Filter {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// HttpServletRequest로 다운캐스팅하기
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if (loginUser != null) { // 로그인이 되어있다면
			// 요청을 그대로 유지
			chain.doFilter(request, response);			
		} else { // 로그인이 되어있지 않다면
			// 흐름을 바꿔주는 작업 처리
			session.setAttribute("alertMsg", "로그인이 필요한 서비스입니다. 로그인 후 이용해주세요");
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath());
			
		}
		
		
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}