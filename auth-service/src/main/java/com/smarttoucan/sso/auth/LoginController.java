package com.smarttoucan.sso.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    public LoginController() {
        credentials.put("admin", "admin");
        credentials.put("santiago", "santiago");
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest){
    	
    	String username =  JwtUtil.getSubject(httpServletRequest, jwtTokenCookieName, signingKey);
    	String redirect = httpServletRequest.getParameter("redirect");
    	
    	if (username == null || (redirect != null && !redirect.isEmpty())) {
    		return "login";
    	} else {
    		 return "redirect:/login-successful";
    	}
    }
    
    
    @RequestMapping("/login-successful")
    public String loginSuccessful(HttpServletRequest httpServletRequest, Model model){
    	 String username =  JwtUtil.getSubject(httpServletRequest, jwtTokenCookieName, signingKey);
    	 model.addAttribute("usuario", username);
    	 return "login-successful";
    }
    

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
        
        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }
}
