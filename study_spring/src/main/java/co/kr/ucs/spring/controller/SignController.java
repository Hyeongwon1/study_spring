package co.kr.ucs.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import co.kr.ucs.spring.service.SignService;
import co.kr.ucs.spring.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SignController {
	
	@Autowired 
	SignService signService;
	
	private static final Logger logger = LoggerFactory.getLogger(SignController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/sign/signUp.do", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) throws Exception{
		
		return "login/signUp";
	}
	@RequestMapping(value = "/sign/signUpok.do", method = RequestMethod.GET)
	@ResponseBody
	public String signUpok(Locale locale, UserVO UserVo) throws Exception{
		
		
		return "";
	}
	
	@RequestMapping(value = "/sign/signIn.do", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) throws Exception{
		
		UserVO UserVo = new UserVO();
		
		return "login/signIn";
	}
	
}
