package co.kr.ucs.spring.dao;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.ucs.spring.vo.BoardVO;
import co.kr.ucs.spring.vo.SearchVO;
import co.kr.ucs.spring.vo.UserVO;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVO> BoardList(SearchVO searchVo) {
		// TODO Auto-generated method stub
		int pageBlock = 10;
		int startRow  = (searchVo.getCpage() - 1) * pageBlock + 1;
		int endRow    = startRow  + pageBlock - 1;
		
        HashMap <Object,Object> map = new HashMap<Object, Object>();
        map.put("searchWord", "%" + searchVo.getSearchWord() + "%");
		map.put("searchKey" ,              searchVo.getSearchKey());
        map.put("startRow"  ,               startRow);
		map.put("endRow"    ,                 endRow);
		
		return sqlSession.selectList("board.boardList", map);
	}

	public int getTotalRows(SearchVO searchVo) {
		
        HashMap <Object,Object> map = new HashMap<Object, Object>();
        map.put("searchWord", "%" + searchVo.getSearchWord() + "%");
		map.put("searchKey" ,              searchVo.getSearchKey());
        
		return sqlSession.selectOne("board.getTotalRows", map);
	}

	public int BoardWrite(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardWrite", boardVo);
	}

	public BoardVO BoardRead(int seq) {
//		
//		 HashMap <Object,Object> map = new HashMap<Object, Object>();
//	     map.put("SEQ", seq);
			
		return  sqlSession.selectOne("board.boardRead", seq);
	}
	
//
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
