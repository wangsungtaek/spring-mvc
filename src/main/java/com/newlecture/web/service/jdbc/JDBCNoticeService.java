package com.newlecture.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@Service
public class JDBCNoticeService implements NoticeService {
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String uid = "scott";
//	private String pwd = "tiger";
//	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	@Autowired
	private DataSource dataSource;

	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
		
//		int start = 1 + (page-1)*10; // 1, 11, 21, 31, ..
//		int end = 10*page; //10, 20, 30, 40, ..
//		String sql = "SELECT * FROM NOTICE1";
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);
//		List<Notice> list = template.query(sql, new BeanPropertyRowMapper(Notice.class));
//		
		List<Notice> list = null;
		return list;
	}

	public int getCount() throws ClassNotFoundException, SQLException {

		
		String sql = "SELECT COUNT(*) COUNT FROM NOTICE1";
		int count = 0;
	
		//Class.forName(driver); 	// jdbc driver load (메모리에 잡히게됨)
		//Connection con = DriverManager.getConnection(url, uid, pwd); // 연결객체
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement(); //실행 도구생성
		ResultSet rs = st.executeQuery(sql); // 결과 실행
		
		if(rs.next())
			count = rs.getInt("count");
			
		rs.close();
		st.close();
		con.close();
		return count;
	}
	
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "INSERT INTO NOTICE1 (ID, TITLE, WRITER_ID, CONTENT, FILES)"
					+ "VALUES(NOTICE_SEQ.NEXTVAL,?,?,?,?)";
		
	
		//Class.forName(driver); 	// jdbc driver load (메모리에 잡히게됨)
		//Connection con = DriverManager.getConnection(url, uid, pwd); // 연결객체
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement(); //실행 도구생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();

		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "UPDATE NOTICE1"
				   + " SET title = ?,"
				   + "	   content = ?,"
				   + "	   FILES  = ?"
				   + " WHERE id = ?";
		
	
		//Class.forName(driver); 	// jdbc driver load (메모리에 잡히게됨)
		//Connection con = DriverManager.getConnection(url, uid, pwd); // 연결객체
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String sql = "DELETE NOTICE1 WHERE id = ?";
				   
		
	
		//Class.forName(driver); 	// jdbc driver load (메모리에 잡히게됨)
		//Connection con = DriverManager.getConnection(url, uid, pwd); // 연결객체
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement(); //실행 도구생성
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		return result;
	}

}
