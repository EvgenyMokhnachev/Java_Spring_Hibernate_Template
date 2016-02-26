package database.dao;

import database.dto.User;

import java.util.UUID;

public interface UserDao extends BaseDao<User> {
    User getByEmail(String email);
    User getByUsername(String username);
    User getByIdAndKey(long id, UUID key);
}
