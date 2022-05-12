package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.naming.NamingException;

import util.ConnectionPool;



public class UserDAO {
   public boolean insert(String uid, String jsonstr) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      try {
            String sql = "insert into user(id, jsonstr) values(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            stmt.setString(2, jsonstr);
            
            int count = stmt.executeUpdate();
            
            return (count ==1) ? true : false;
      }
      finally {
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }
   
   public boolean exists(String uid) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
            String sql = "select id from user where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            
            rs = stmt.executeQuery();
            
            return rs.next();
      }
      finally {
         if(rs !=null) rs.close();
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }
   
   public boolean delete(String uid) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      try {
            String sql = "delete from user where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            
            int count = stmt.executeUpdate();
            
            return (count == 1) ? true : false;
      }
      finally {
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }
   
   public int login(String uid, String upass) throws NamingException, SQLException, ParseException {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
            String sql = "select jsonstr from user where id = ?";
            
            conn = ConnectionPool.get();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uid);
            
            rs = stmt.executeQuery();
            if(!rs.next()) return 1; //아이디가 없을 떄 1반환
            
            String jsonstr = rs.getString("jsonstr");
            JSONObject obj = (JSONObject) (new JSONParser()).parse(jsonstr); //json스트링을 json 객체로 바꾸기
            String pass = obj.get("password").toString();
            
            if(!upass.equals(pass)) return 2; //비밀번호가 맞지 않을 떄 2반환
            return 0; //정상적인 로그인
            
      }
      finally {
         if(rs !=null) rs.close();
         if(stmt != null) stmt.close();
         if(conn != null)conn.close();
      }
   }
   
   public String getList() throws NamingException, SQLException {
      
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
      String sql = "select jsonstr from user";
      stmt = conn.prepareStatement(sql);
      rs = stmt.executeQuery();
      
      /*
      ArrayList<UserObj> users = new ArrayList<UserObj>();
      while(rs.next()) {
         UserObj usrobj = new UserObj(rs.getString("id"), rs.getString("name"), rs.getString("ts"));
         users.add(usrobj);
      } return users;
      */
      JSONArray users = new JSONArray(); // == ArrayList<UserObj> users = new ArrayList<UserObj>();
      String str = "[";
      int cnt = 0;
      while(rs.next()) {
         if(cnt ++ > 0) str += ", ";
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
   
   public String get(String uid) throws NamingException, SQLException {
      Connection conn = ConnectionPool.get();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         String sql = "SELECT jsonstr FROM user WHERE id = ?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, uid);
         
         rs = stmt.executeQuery();
         return rs.next() ? rs.getString("jsonstr") : "{}";
         
         } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
         }
      }
   public boolean update(String uid, String jsonstr) throws NamingException, SQLException {
	      Connection conn = ConnectionPool.get();
	      PreparedStatement stmt = null;
	      try {
	            String sql = "update user set jsonstr = ? where id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, jsonstr);
	            stmt.setString(2, uid);
	            
	            int count = stmt.executeUpdate();
	            
	            return (count ==1) ? true : false;
	      }
	      finally {
	         if(stmt != null) stmt.close();
	         if(conn != null)conn.close();
	      }
	   }
}