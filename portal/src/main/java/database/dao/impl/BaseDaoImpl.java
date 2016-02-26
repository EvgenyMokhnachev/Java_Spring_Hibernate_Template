package database.dao.impl;

import database.dao.BaseDao;
import database.dto.BaseDto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class BaseDaoImpl<T extends BaseDto> implements BaseDao<T> {
    @Autowired
    protected SessionFactory sessionFactory;
    protected Class<T> type;

    protected Session session(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long add(T item){
        return (long) session().save(item);
    }

    @Override
    public T get(long id){
        return (T) session().get(type, id);
    }

    @Override
    public void update(T item){
        session().update(item);
    }

    @Override
    public T getByField(String column, Object value){

        return (T)session().createCriteria(type)
                .add(Restrictions.eq(column, value))
                .uniqueResult();
    }

    @Override
    public void delete(T item){
        session().delete(item);
    }

    @Override
    public List<T> get(int limit, int offset, List<Order> order, List<Criterion> criterions){
        Criteria c = session().createCriteria(type);
        if(order != null) for(Order orderItem : order){
            c.addOrder(orderItem);
        }
        if(limit != 0) c.setMaxResults(limit);
        if(offset != 0) c.setFirstResult(offset);
        if(criterions != null) for(Criterion criterion : criterions) c.add(criterion);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return c.list();
    }

    @Override
    public T merge(T item){
        return (T)session().merge(item);
    }

    @Override
    public T load(Long id){
        return (T)session().load(type, id);
    }

}
