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
//import org.zerock.myapp.domain.LoginDTO;
//import org.zerock.myapp.domain.UsersVO;
//import org.zerock.myapp.mapper.UsersMapper;
//import org.zerock.myapp.persistence.LoginDAO;
//
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.log4j.Log4j2;
//
//@NoArgsConstructor
//@Log4j2
//
//@Component
//public class AuthInterceptor implements HandlerInterceptor {
//	
//	@Setter(onMethod_ = @Autowired)
//	private UsersMapper dao;
//	@Setter(onMethod_ = @Autowired)
//	private LoginDAO login;
//	
//	
//	// HTTP request 가 Request Mapping 대로, Controller의 Handler에 위임되기 직전에 가로채서
//	// 이 요청을 보낸 웹 브라우저가 인증을 완료한 상태인지 체크
//	//	NO인증이면 로그인창으로 밀어버림 => 인터셉터의 역할
//	@Override
//	   public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
//	         throws Exception {   
//		// 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
//	      log.trace("==============================================================");
//	      log.trace("preHandle(req, res, {}) invoked.", handler);
//	      log.trace("==============================================================");
//	      
////	      HttpSession session =  req.getSession(); // 웹 브라우저에 할당되어있는 세션 획득
//	      
//	      // 요청을 보낸 브라우저에 대한 세션이 있으면 있는걸 반환
//	      // 없으면 null 을 반환
////	      HttpSession session =  req.getSession(false); // 없으면 만들라가 트루, fasle는 없으면 널 반환
//	      // 매개변수 있는 세션과 비교 : 없으면 널을 반환
//	      
//	      HttpSession session =  req.getSession(); // 자동록인으로 인해 수정
//	      
////	      일단 세션이 있으면 아래에서 인증정보
////	      if(session == null) { // 아예 세션 자체가 없으면
////	    	  log.info("세션없은 null 임");
////	    	 res.sendRedirect("/common/login");
////	    	return false;
////	    	
////	      } 
//	      
////	      ==============세션에 인증정보가 있는지 확인 =========================
//	      
//	      String sessionId = session.getId();
//    	  log.info("\n1. sessionId : {} ",sessionId);
//	      
//	      UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
//	      if(vo != null) { // 인증된 웹브라우저
//    		  log.info("\n*******************************************************"
//    		  		+ "\n******************** 인증완료 *************************"
//    		  		+ "\n*******************************************************");
//    		  return true;
//    	  } else {	// 세션은 있으나 인증이 안된 웹브라우저
//    		  log.info("NO authentication found. Redirect to Login.");
//    		  
////    		  ******************************************
////    		  * 자동로그인 (Remember-Me) 처리
////    		  *****************************************
////    		  * 필요한 처리는 아래와 같이 2가지
////    		  	1) 현재 웹브라우저의 금고상자(세션객체) 안에
////    		  		이전 웹 브라우저가 종료되기 전에 인증되었던 Credintial(=UserVO)를 복구
////    		  		이 처리가 자동로그인기능에서 핵심이 되는 로직
////    		  	2) (1) 처리가 몬족되는지 확인 => 무사통과 (if satisfied)
//    		  
////    		  Step1. 스프링이 제공하는 유틸리티 클래스의 WebUtils의 기능을 이용
////    		  		 웹브라저가 보낸 HTTP request 메시지에 있는 쿠키값을 쉽게 얻어냄.
//    		  
//    		  Cookie rememberMeCookie = WebUtils.getCookie(req, "__REMEMBER_ME__");
//    		  
//    		  if(rememberMeCookie != null) {
//    			  // 자동로그인 설정된 웹브라우저의 요청이란 의미
//    			  // 이 if 블록에서, 위(1) 핵심로직 (Credential Recovery)
//    			  log.info("\nRemember -Auto credential recovery");
//    			  
////    		  Step2. Step.1 에서 얻어낸 자동로그인 쿠키의 이름과 값을 지역변수에 보관
////    			  	세션ID => 웹브라우저의 이름으로 쿠키값 설정
//    			  String cookieName = rememberMeCookie.getName();
//    			  String cookieValue = rememberMeCookie.getValue();
//    			  log.info("\t+ cookieName: {}, cookieValue: {}", cookieName, cookieValue);
//    			  
////    		Step3. step2. 에서 얻어낸 자동로그인 쿠키값(세션ID)으로 tbl_user 테이블 조회 =>
////    			   정확히 1개의 User가 select = > 마치, userid/userpw 로 사용자 조회한것과 동일
////    			   만일 사용자가 발견된다면 , 이 사용자에대한 UServo 객체를 획득하고 
////    			   획득한 UserVO객체를, 현재 웹브라우저에 대응되는 세션객체에 
////    			   __AUTH__ 이름으로 UserVO 객체를 Credential로 설정
//    			  Objects.requireNonNull(this.login);
//    			  
//    			  // 자동로그인요청한 웹브라우저의 Credential(인증객체) 확보
//    			  UsersVO uvo = this.login.selectUserByRememberMe(cookieValue);
//    			  log.info("\nUserVO = {}",uvo);
//    			  
////    	  	Step4. Credential Recovery
//    			  if(uvo != null) {
//    				  
//    				  session.setAttribute("__AUTH__", uvo);
//    				  log.info("Credential Recovery Done Successfully.");
//    				  
//    				  return true;
//    			  } //innerIf : 정상적인 자동로그인 요청에 대한 처리인 경우
//    			  
//    		  } // outter if : 자동로그인 처리대상요청인 경우
//	    	
////    	      ==================================================================
////    	      * 자동로그인 설정이 안되어 있는 경우와, 위의 조건을 만족하지 못하는 경우
////    	      	즉시, 로그인 창으로 밀어버림(리다이렉션)
//    		  log.info("\t2. No credential found. Redirect to Login.");
//    		  
//    		  
//    		 String query = req.getQueryString() ;
//    		 log.info("\n\nquery : {} ",query);
//    		 if(query != null) {
//    			 req.getSession().setAttribute("redirectUri", req.getRequestURI()+"?"+query);
//    			
//    		 } else {
//    			 req.getSession().setAttribute("redirectUri",req.getRequestURI());
//    		 }
//    		 
//    		 
//    		  res.sendRedirect("/common/loginPost");
//        	  return false; // 원래 요청을 처리하지 못하도록 함
//    		  
//    	  } // if- else
// 
//	} // preHandle
//
//	
//} // end class
