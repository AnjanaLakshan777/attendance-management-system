package edu.self.sams.service.custom;

import edu.self.sams.service.SuperService;

public interface UserService extends SuperService {
    public boolean userLogin(String userId, String password, String role) throws Exception;
}
