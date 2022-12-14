package ca.sait.lab6.services;

import ca.sait.lab6.dataaccess.RoleDB;
import ca.sait.lab6.models.Role;
import java.util.List;

public class RoleService {
    private RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    public Role get(int id) throws Exception {
        Role role = roleDB.get(id);
        return role;
    }
}
