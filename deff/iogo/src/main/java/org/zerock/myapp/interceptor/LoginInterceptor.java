//package org.zerock.myapp.interceptor;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.zerock.myapp.domain.UsersVO;
//import org.zerock.myapp.persistence.LoginDAO;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@NoArgsConstructor
//@Log4j2
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//	@Autowired
//	private LoginDAO dao;
//	
//
//	/*
//	 * 로그인 인터셉터의 목적은 무엇일까? 목적 : 전처리는 이미 사용자가 로그인창에서 아이디, 암호를 입력,전송 했다는 것은 분명한 사실이기에
//	 * 로그인 요청을 먼저 가로채서 전처리에서 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체가 있다면 무조건 파괴시키는 동작이 피룡
//	 * 
//	 */
//	@Override
//	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//		log.trace("=====================================================");
//		log.trace("preHandler(req, res, {}) invoked.", handler);
//		log.trace("=====================================================");
//		
//		// Step.1 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체 획득
//		HttpSession session = req.getSession(false);
//		String qe = req.getQueryString() ;
//		String uri = (String) req.getSession().getAttribute("redirectUri");
//		log.info("\n\n잠만 : {}\nqe3 : {}",qe,uri);
//		if(session != null) {
//			log.info("\t+ sessionId: {}", session.getId());
//			
//			// Step.2 기존 세션은 무조건 파괴시켜서, 새로운 credential은 새로운 객체에 저장하도록 해줍니다.
//			session.invalidate();
//			log.info("\t+ Session({}) Destroyed.", session.getId());
//		} // if
//		 req.getSession().setAttribute("redirectUri",uri);
//		return true; // 인증로직은 기존대로 컨트롤러의 핸들러가 처리하도록 해줘야 함
//	} // preHandle
//
//	
//	// (**** 후처리 ****)
//	// 목적: 자동로그인 처리를 위한 로직을 구현해야 합니다. 왜!? 인증성공 이후에, rememberMe 옵션이 true이면
//	//       자동로그인 가능하도록 처리를 해줘야 하기 때문에...
//	//       
//	@Override
//	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView)
//			throws Exception {
//		log.trace("==============================================================");
//		log.trace("postHandle(req, res, {}, {}) invoked.", handler, modelAndView);
//		log.trace("==============================================================");
//		
//		// Step.1 인증성공여부에 따라 자동로그인 처리를 수행 => 
//		//        조건1: 세션에 Credential이 존재해야 함(*인증성공*)
//		//        조건2: rememberMe 옵션이 on 되어있는지 확인
//		boolean authencated = isAuthenticated(req);			// 조건1 확인
//		boolean rememberMe = checkRememberMeOption(req);	// 조건2 검사
//		log.info("\t+ Remember-Me: authencated({}), rememberMe({})", authencated, rememberMe);	
//		
//		
//
//		if(authencated && rememberMe) {	// 자동로그인 처리조건을 만족한다면....
//			
//			
//			// Step.2 자동로그인설정을 요청한 웹브라우저의 이름인 세션ID 획득
//			HttpSession session = req.getSession(false);
//			String sessionId = session.getId();				// 쿠키값으로 사용
//			
//			
//			// Step.3 Step.2에서 얻은 세션ID를 값으로 하는 자동로그인 쿠키 생성
//			Cookie rememberMeCookie = new Cookie("__REMEMBER_ME__", sessionId);	
//			
//			// 0: 만료기간없음, 1: 1초 (in seconds)
//			final int maxAge = 1*60*60*24*7;			
//			rememberMeCookie.setMaxAge(maxAge);	// 유효기간설정: 1주일
//			
//			rememberMeCookie.setPath("/");
//			
//			
//			// Step.4 응답메시지의 "Set-Cookie" 헤더에 쿠키를 값으로 설정
//			res.addCookie(rememberMeCookie);
//			
//			// Step.5 tbl_user 테이블에 현재 사용자의 추가정보로, 쿠키값/만료일시 기록
//			long currDateTime = System.currentTimeMillis();
//			long expireDateTime = currDateTime + (maxAge * 1000L);
//			
//			Timestamp rememberMeCookieMaxAge = new Timestamp(expireDateTime);
//			
//			int affectedLines = this.dao.updateUserWithRememberMe(
//				getUserId(req),
//				rememberMeCookie.getValue(), 
//				rememberMeCookieMaxAge
//			);
//			
//			boolean isUpdated = (affectedLines == 1);
//			log.info("\t+ isUpdated: {}", isUpdated);
////			
////			String redirectUri = (String) req.getSession().getAttribute("redirectUri");
////			if (redirectUri != null) {
////				   res.sendRedirect(redirectUri);
////				} else {
////				   // redirectUri가 없다면 로그인 이후의 기본 페이지로 리다이렉트
////				   res.sendRedirect("/");
////				}
////			
//			
//		} else {
//	        // 로그인 실패 시 처리
//	        String result = (String) req.getSession().getAttribute("__RESULT__");
//	        if (result != null && result.equals("실패")) {
//	            // 로그인 실패 메시지를 모델에 추가
//	            modelAndView.addObject("errorMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
//	            // 로그인 페이지로 리다이렉트
//	            res.sendRedirect("/common/loginPost");
//	            return; // 리다이렉트 후에는 메서드를 종료하여 중복 호출을 방지합니다.
//	        }
//		}
//		
//	} // postHandle
//	
//	
//	// 인증정보로부터 사용자 아이디 반환
//	private String getUserId(HttpServletRequest req) {	// 인증성공되었는가!?
//		log.trace("getUserId(req) invoked.");
//		
//		HttpSession session = req.getSession(false);
//		
//		UsersVO userVO = (UsersVO) session.getAttribute("__AUTH__");
//		log.info("\t+ userVO: {}", userVO);
//		
//		return userVO.getID();
//	} // getUserId
//	
//	
//	// 조건1: 인증성공여부 반환
//	private boolean isAuthenticated(HttpServletRequest req) throws IOException {	// 인증성공되었는가!?
//		log.trace("isAuthenticated(req) invoked.");
//		
//		HttpSession session = req.getSession(false);
//		if(session != null) {
//			Object obj = session.getAttribute("__AUTH__");
//			log.info("\t+ obj: {}", obj);
//			return obj != null;
//		} else {
//			session=req.getSession(true);
//			session.setAttribute("__RESULT__","비회원");
//			Object obj = session.getAttribute("__RESULT__");
//			log.info("\t+ obj: {}", obj);
//			return obj != null;
//		}
//		
//	} // isAuthenticated
//	
//	// 조건2: 자동로그인 옵션이 on/off 반환
//	private boolean checkRememberMeOption(HttpServletRequest req) {	// 자동로그인 옵션이 켜져있는가?
//		log.trace("checkRememberMeOption(req) invoked.");
//		
//		String rememberMe = req.getParameter("rememberMe");		// if checkbox checked, return "on"
//		log.info("\n\nrememberMe : {} ",rememberMe);
//		return rememberMe != null;
//	} // checkRememberMeOption
//	
//	
//	
//
//	
//} // end class