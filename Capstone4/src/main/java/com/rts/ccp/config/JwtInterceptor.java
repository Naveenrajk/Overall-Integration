package com.rts.ccp.config;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.rts.ccp.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
    
    
    

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	System.out.println(request.getRequestURI());
    	System.out.println(request.getHeader("Authorization"));
    	
    	if(isURIAllowed(request.getRequestURI())==false) {
    		String token = request.getHeader("Authorization");
            System.out.println(token);
            if (token != null && token.startsWith("Bearer ")) {
                String newToken = token.substring(7).trim();
                try {
                    if (jwtUtil.verifyToken(newToken) != null) {
                        return true;
                    } else {
                        logger.error("Token verification failed.");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }
                } catch (Exception e) {
                    logger.error("Exception while verifying token: {}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            } else {
                logger.error("Invalid token format." +token);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
    	}else {
    		return true;
    	}
    }
    
    private boolean isURIAllowed(String uri) {
    	List<String> list = Arrays.asList("forgetPassword", "login","ForgetVerifyOTP","updateForgetPassword","insertPassword","PerformInsert");
    	System.out.println(list);
    	for(int i=0;i<list.size();i++) {
    		System.out.println(list.get(i));
    		if(uri.contains(list.get(i))){
    			return true;
    		}
    	}return false;
    }
    
}