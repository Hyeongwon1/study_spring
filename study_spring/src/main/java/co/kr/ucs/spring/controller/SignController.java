package co.kr.ucs.spring.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
	
	@RequestMapping(value = "/sign/signUpAc.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> signUpAc(Locale locale, UserVO UserVo) throws Exception{
		
		int flag = signService.SignUpAc(UserVo);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("success", (flag > 0)?"Y":"N");
		
		return map;
	}
	
	@RequestMapping(value = "/sign/signIn.do", method = RequestMethod.GET)
	public String signIn(Locale locale, Model model) throws Exception{
		logger.info("signIn");
		
		return "login/signIn";
	}
	
	@RequestMapping(value = "/sign/signInAc.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> signInAc(HttpSession session,Locale locale, Model model, UserVO UserVo) throws Exception{
	
		UserVO vo = signService.SignInAc(UserVo);
		System.out.println("list:"+vo);
		 
		Map<String, String> map = new HashMap<String, String>();
		
			
		if(vo!=null){
			session.setAttribute("loginId",vo.getUser_id());
			map.put("success", "1");
		}else {
			map.put("success", "0");
		}
		
		return map;
	}
	
}
