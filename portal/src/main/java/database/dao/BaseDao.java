package database.dao;

import database.dto.BaseDto;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.util.List;

public interface BaseDao<T extends BaseDto> {
    long add(T item);
    T get(long id);
    T getByField(String column, Object value);
    void update(T item);
    void delete(T item);
    T merge(T item);
    T load(Long id);
    List<T> get(int limit, int offset, List<Order> order, List<Criterion> criterions);
}
