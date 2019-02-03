package dbService.dao;

import dbService.DBException;
import dbService.dataSets.UserProfile;
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

    public UserProfile getUserById(long id) throws HibernateException {
        return (UserProfile) session.get(UserProfile.class, id);
    }

    public UserProfile getUser(String login) throws HibernateException{
        CriteriaBuilder builder = session.getCriteriaBuilder();
        //создаем критерий
        CriteriaQuery<UserProfile> criteriaQuery = builder.createQuery(UserProfile.class);
        //определяем переменную диапазона для FROM
        Root<UserProfile> root = criteriaQuery.from(UserProfile.class);
        //какой тип результата запроса будет (можно поля задавать
        criteriaQuery.select(root);
        //задаем where
        criteriaQuery.where(builder.equal(root.get("login"), login));
        //собственно сам запрос
        Query<UserProfile> query = session.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (Exception nre) {
            return null;
        }
    }


    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new UserProfile(login,password));
    }
}
