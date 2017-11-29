package co.kr.ucs.spring.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.ucs.spring.service.BoardService;
import co.kr.ucs.spring.vo.BoardVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	@Autowired 
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/board/boardListf.do", method = RequestMethod.GET)
	public String boardListfirst(Locale locale, Model model) throws Exception{
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.POST)
	@ResponseBody
//	public HashMap<String, Object> boardList(Locale locale, Model model, @RequestBody HashMap<String, Object> param) throws Exception{
//	파라미터를 파람으로 싹다 받아서 넘기고 싶은데 Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported 오류....
//	public HashMap<String, Object> boardList(Locale locale, Model model, HashMap<String, Object> param,
//			@RequestParam("cpage")int cPage,
//			@RequestParam("searchKey")String searchKey,
//			@RequestParam("searchWord")String searchWord) throws Exception{
	public HashMap<String, Object> boardList(Locale locale, Model model, @RequestBody HashMap<String, Object> param
			) throws Exception{
		int cPage =(int) param.get("cpage");
		int pageBlock = 10;
		int startRow  = (cPage - 1) * pageBlock + 1;
		int endRow    = startRow  + pageBlock - 1;
		
//		param.put("searchKey", searchKey);
//		param.put("searchWord", "%" +searchWord+ "%");
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		List<HashMap<String, Object>> list	= boardService.BoardList(param);
		int totalRows		= boardService.getTotalRows(param);
		
//		System.out.println("totalRows:"+totalRows);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalRows", totalRows);
//		System.out.println("cpage:"+cPage);
		map.put("cPage", cPage);
		

		return map;
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAc.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> boardWriteAc(Locale locale, BoardVO BoardVo) throws Exception{
		
		int flag =  boardService.boardWriteAc(BoardVo);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("success", (flag > 0)?"Y":"N");
		
		return map;
	}
	
	@RequestMapping(value = "/board/boardRead.do", method = RequestMethod.GET)
	public String boardRead(Locale locale, Model model
			,@RequestParam("seq")int seq) throws Exception{
		
		BoardVO list = boardService.BoardRead(seq);
		
		model.addAttribute("list", list);
		
		return "board/boardRead";
	}
	
}
