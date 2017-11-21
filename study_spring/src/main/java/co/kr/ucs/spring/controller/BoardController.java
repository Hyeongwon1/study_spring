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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.ucs.spring.service.BoardService;
import co.kr.ucs.spring.vo.BoardVO;
import co.kr.ucs.spring.vo.SearchVO;

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
		logger.info("boardListf");
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> boardList(Locale locale, Model model
			,@RequestParam("cpage")String cupage
			,@RequestParam("searchKey")String searchKey
			,@RequestParam("searchWord")String searchWord) throws Exception{
		
		SearchVO SearchVo = new SearchVO();
		
		System.out.println("searchKey:"+searchKey);
		System.out.println("searchWord:"+searchWord);
		
		String cpage = "1";
		if(cupage != null){
			cpage = cupage;
		}
		SearchVo.setSearchKey(searchKey);
		SearchVo.setSearchWord(searchWord);
		SearchVo.setCpage(Integer.parseInt(cpage));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<BoardVO> list = boardService.BoardList(SearchVo);
		int totalRows = boardService.getTotalRows(SearchVo);
		System.out.println("totalRows:"+totalRows);
		map.put("list", list);
		map.put("totalRows", totalRows);
		System.out.println("cpage:"+SearchVo.getCpage());
		map.put("cPage", cpage);

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
