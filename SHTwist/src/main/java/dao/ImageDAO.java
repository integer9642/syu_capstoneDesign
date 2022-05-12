package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class ImageDAO {
   public boolean insertTop(String jsonstr) throws NamingException, SQLException, ParseException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         synchronized(this) { //상호배제
            // phase 1. add "no" property -----------------------------
            String sql = "SELECT no FROM top ORDER BY no DESC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            int max = (!rs.next()) ? 0 : rs.getInt("no");
            stmt.close(); rs.close();
            
            JSONParser parser = new JSONParser();
            JSONObject jsonobj = (JSONObject)parser.parse(jsonstr);
            jsonobj.put("no", max + 1); //jsonstr에 no속성 값 추가
            
            // phase 2. add "user" property ------------------------------
            String uid = jsonobj.get("id").toString();
            
            sql = "SELECT jsonstr FROM top WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
               String usrstr = rs.getString("jsonstr");
               JSONObject usrobj = (JSONObject) parser.parse(usrstr); //스트링에서 객체로 바꿔줌 
               usrobj.remove("password");
               usrobj.remove("ts");
               jsonobj.put("user", usrobj); //jsonstr에 user속성 값 추가
            }
            stmt.close(); rs.close();
            
            // phase 3. insert jsonobj to the table ------------------------
            sql = "insert into top(no, id, jsonstr) values(?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, max + 1);
            stmt.setString(2, uid);
            stmt.setString(3, jsonobj.toJSONString());
            
            int count = stmt.executeUpdate();
            
            return (count ==1) ? true : false;
         }
      }
      finally {
         if(rs != null) rs.close();
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }   
   
   
   public String getTopList() throws NamingException, SQLException {
         
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
          String sql = "select * from top order by no desc";
         stmt = conn.prepareStatement(sql);
          rs = stmt.executeQuery();
        
          
          String str = "[";
          int cnt = 0;
          while(rs.next()) {
             if (cnt++ > 0) str += ", ";
             str += rs.getString("jsonstr");
          }
          return str + "]";
      }
      finally {
         if(rs != null) stmt.close();
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }
   
   public String getTopGroup(String frids, String maxNo) throws NamingException, SQLException {
	      
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	          String sql =  "SELECT jsonstr FROM top WHERE id IN ("+ frids + ")";
	         if (maxNo != null) {
	            sql += " AND no < " + maxNo;
	         }
	         sql += " ORDER BY no DESC LIMIT 3";
	            
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	          
	          String str = "[";
	          int cnt = 0;
	          while(rs.next()) {
	             if (cnt++ > 0) str += ", ";
	             str += rs.getString("jsonstr");
	          }
	          return str + "]";
	      }
	      finally {
	         if(rs != null) stmt.close();
	         if(stmt != null) stmt.close();
	         if(conn != null)conn.close();
	      }
	   }
   
   public boolean insertBottom(String jsonstr) throws NamingException, SQLException, ParseException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	         synchronized(this) { //상호배제
	            // phase 1. add "no" property -----------------------------
	            String sql = "SELECT no FROM bottom ORDER BY no DESC LIMIT 1";
	            stmt = conn.prepareStatement(sql);
	            rs = stmt.executeQuery();
	            
	            int max = (!rs.next()) ? 0 : rs.getInt("no");
	            stmt.close(); rs.close();
	            
	            JSONParser parser = new JSONParser();
	            JSONObject jsonobj = (JSONObject)parser.parse(jsonstr);
	            jsonobj.put("no", max + 1); //jsonstr에 no속성 값 추가
	            
	            // phase 2. add "user" property ------------------------------
	            String uid = jsonobj.get("id").toString();
	            
	            sql = "SELECT jsonstr FROM bottom WHERE id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, uid);
	            rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	               String usrstr = rs.getString("jsonstr");
	               JSONObject usrobj = (JSONObject) parser.parse(usrstr); //스트링에서 객체로 바꿔줌 
	               usrobj.remove("password");
	               usrobj.remove("ts");
	               jsonobj.put("user", usrobj); //jsonstr에 user속성 값 추가
	            }
	            stmt.close(); rs.close();
	            
	            // phase 3. insert jsonobj to the table ------------------------
	            sql = "insert into bottom(no, id, jsonstr) values(?, ?, ?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, max + 1);
	            stmt.setString(2, uid);
	            stmt.setString(3, jsonobj.toJSONString());
	            
	            int count = stmt.executeUpdate();
	            
	            return (count ==1) ? true : false;
	         }
	      }
	      finally {
	         if(rs != null) rs.close();
	         if(stmt != null) stmt.close();
	         if(conn != null)conn.close();
	      }
	   }   
	   
	   
	   public String getBottomList() throws NamingException, SQLException {
	         
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	          String sql = "select * from bottom order by no desc";
	         stmt = conn.prepareStatement(sql);
	          rs = stmt.executeQuery();
	        
	          
	          String str = "[";
	          int cnt = 0;
	          while(rs.next()) {
	             if (cnt++ > 0) str += ", ";
	             str += rs.getString("jsonstr");
	          }
	          return str + "]";
	      }
	      finally {
	         if(rs != null) stmt.close();
	         if(stmt != null) stmt.close();
	         if(conn != null)conn.close();
	      }
	   }
	   
	   public String getBottomGroup(String frids, String maxNo) throws NamingException, SQLException {
		      
		      Connection conn = ConnectionPool.get();
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      try {
		          String sql =  "SELECT jsonstr FROM bottom WHERE id IN ("+ frids + ")";
		         if (maxNo != null) {
		            sql += " AND no < " + maxNo;
		         }
		         sql += " ORDER BY no DESC LIMIT 3";
		            
		         stmt = conn.prepareStatement(sql);
		         rs = stmt.executeQuery();
		          
		          String str = "[";
		          int cnt = 0;
		          while(rs.next()) {
		             if (cnt++ > 0) str += ", ";
		             str += rs.getString("jsonstr");
		          }
		          return str + "]";
		      }
		      finally {
		         if(rs != null) stmt.close();
		         if(stmt != null) stmt.close();
		         if(conn != null)conn.close();
		      }
		   }
   
}