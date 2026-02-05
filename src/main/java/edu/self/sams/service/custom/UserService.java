package edu.self.sams.service.custom;

import edu.self.sams.service.SuperService;

public interface UserService extends SuperService {
    public boolean userLogin(String username, String password, String role) throws Exception;
}
