package co.kr.ucs.spring.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.ucs.spring.dao.BoardDao;
import co.kr.ucs.spring.vo.BoardVO;
import co.kr.ucs.spring.vo.SearchVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;


	public List<HashMap<String, Object>> BoardList(HashMap<String, Object> param){

		return boardDao.BoardList(param);
	}


	public int getTotalRows(HashMap<String, Object> param) {
		
		return boardDao.getTotalRows(param);
	}


	public int boardWriteAc(BoardVO boardVo) {
		
		return boardDao.BoardWrite(boardVo);
	}


	public BoardVO BoardRead(int seq) {
		// TODO Auto-generated method stub
		return boardDao.BoardRead(seq);
	}


//	public int Signchk(String userId, String userPw) throws ClassNotFoundException {
//		
//		Object[] sqlParameter = new Object[] {userId};
//		
//		String chkSql = "SELECT COUNT(*) FROM CM_USER WHERE USER_ID = ?";
//		int flag = 0;
//	
//		if(userPw != null){
//			sqlParameter = new Object[] {userId,userPw};
//			chkSql += (" AND USER_PW = ?");
//		}
//		
//			flag = jdbcTemplate.queryForObject(chkSql, sqlParameter, Integer.class);
//			System.out.println("chk success");
//	
//		return flag;
//	}
//	
//
//	public UserVO SignIn(String userId, String userPw) {
//
//		String CheckSql = "SELECT * FROM CM_USER WHERE USER_ID=? AND USER_PW=?";
//		
//		return jdbcTemplate.queryForObject(CheckSql,new Object[]{userId,userPw},new RowMapper<UserVO>(){
//					public UserVO mapRow(ResultSet rs, int rowCnt) throws SQLException{
//						UserVO vo = new UserVO();
//						vo.setUser_id(rs.getString("user_id"));
//						vo.setUser_nm(rs.getString("user_nm"));
//						
//						return vo; 
//						
//					}
//				});
		
//		Map<String, String> SImap = new HashMap<>();
//		SImap.put("USER_ID",vo2.getUser_id());
//		SImap.put("USER_PW",vo2.getUser_nm());
//		
//		return SImap;
//	}

}
