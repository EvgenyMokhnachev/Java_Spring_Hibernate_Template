package database.dao.impl;

import database.dao.UserDao;
import database.dto.User;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public UserDaoImpl() {
        this.type = User.class;
    }

    @Override
    public User getByEmail(String email) {
        return getByField("email", email);
    }

    @Override
    public User getByUsername(String username) {
        return getByField("username", username);
    }

    @Override
    public User getByIdAndKey(long id, UUID key) {
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("id", id));
        criterions.add(Restrictions.eq("key", key));
        List<User> users = get(0, 0, null, criterions);
        return users.size() > 0 ? users.get(0) : null;
    }


}
