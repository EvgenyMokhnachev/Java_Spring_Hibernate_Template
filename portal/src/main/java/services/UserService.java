package services;

import database.dto.User;

public interface UserService {

    User registerUser(String username, String email, String password);

}
