package ca.sait.lab6.dataaccess;

import ca.sait.lab6.models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class RoleDB {

    public List<Role> getAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role";
        
        try {
            
            // with prepare statement
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // with create statement
            //rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("role_id");
                String name = rs.getString("role_name");
                
                Role role = new Role(id, name);
                
                roles.add(role);
                
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }

    
    public Role get(int id) throws Exception {
        Role role = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT role_name FROM role WHERE role_id=? LIMIT 1";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            String roleName = rs.getString("role_name");

            role = new Role( id, roleName);
                
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return role;
    }
}
