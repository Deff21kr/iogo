//package org.zerock.myapp.interceptor;
//
//import java.util.Objects;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.util.WebUtils;
//import org.zerock.myapp.domain.UsersVO;
//import org.zerock.myapp.persistence.LoginDAO;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//
//@Log4j2
//@NoArgsConstructor
//
//@Component
//public class LogoutInterceptor implements HandlerInterceptor {
//	@Autowired private LoginDAO login;
//	
//	
//	
//	// 로그아웃 요청을 보낸 웹브라우저가 가지고 있는, 세션객체(금고상자)를 파괴하는 작업을
//	// 아래의 전처리에서 수행하자!!.그리고 이 요청은 뒤로 보내지 않음.
//	// (*주의사항*) 이미 인증된 사용자가 명시적으로 "로그아웃" 을 요구하지 않는 한,
//	//              금고상자(세션객체, HttpSession) 자체를 파괴시켜서는 안됨!!!
//	@Override
//	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//		log.trace("==============================================================");
//		log.trace("preHandler(req, res, {}) invoked.", handler);
//		log.trace("==============================================================");
//		
//		// Step.1 현재 로그아웃 요청을 보낸 웹브라우저에 대응하는 세션객체 획득
//		HttpSession session = req.getSession(false);
//		UsersVO userVO = (UsersVO) session.getAttribute("__AUTH__");
//		
//		if(session != null) { // 로그아웃처리수행			
//			session.invalidate();	// 금고상자(즉, 세션객체)자체를 삭제해버림!!
//		} // if
//		// 자동로그인과 관련된 로직이 추가되어야 함
//		// 왜? 자동로그인 설정된 웹브라우저가 "명시적으로" 로그아웃을 요청했으니,
//		// 자동로그인 설정 "해제" 시켜줘야 함.
//		
//		// Step.2 현재 로그아웃요청한 웹브라우저가 "자동로그인설정" 대상인지 확인
//		Cookie rememberMeCookie = WebUtils.getCookie(req, "__REMEMBER_ME__");
//		if(rememberMeCookie != null) {	// 자동로그인 설정 해제
//			// Step.3 웹브라우저가 보관하는 자동로그인쿠키(rememberMeCookie)를 파괴
//			rememberMeCookie.setMaxAge(1);
//			rememberMeCookie.setPath("/");
//			
//			res.addCookie(rememberMeCookie);	// 만료기간이 변경된 기존 쿠키를 다시 브라우저로 전송
//			
//			log.info("\t+ Remember-Me Cookie Destroyed.");
//			
//			// Step.4 tbl_user 테이블에 설정된 자동로그인 쿠키값과 만료일시 컬럼의 값을 null로 변경
//			Objects.requireNonNull(userVO);
//			
//			this.login.updateUserWithRememberMe(userVO.getID(), null, null);
//			log.info("\t+ Remember-Me Released Successfully.");
//		} // if
//				
//		res.sendRedirect("/common/loginPost");	// 로그아웃처리 후, 로그인 창으로 다시 밀어버림
//		return false;		// 이미 결정됨: 로그아웃 처리는 여기서 하겟다!!!!란 의도
//	} // preHandle
//
//	
//} // end class
