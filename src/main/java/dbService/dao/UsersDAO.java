package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String username){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        //создаем критерий
        CriteriaQuery<UsersDataSet> criteriaQuery = builder.createQuery(UsersDataSet.class);
        //определяем переменную диапазона для FROM
        Root<UsersDataSet> root = criteriaQuery.from(UsersDataSet.class);
        //какой тип результата запроса будет (можно поля задавать
        criteriaQuery.select(root);
        //задаем where
        criteriaQuery.where(builder.equal(root.get("name"), username));
        //собственно сам запрос
        Query<UsersDataSet> query = session.createQuery(criteriaQuery);
        UsersDataSet result = query.getSingleResult();
        return result.getId();
    }


    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }
}
