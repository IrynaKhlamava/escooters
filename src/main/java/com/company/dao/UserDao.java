package com.company.dao;

import com.company.api.dao.IUserDao;
import com.company.model.AppUser;
import com.company.model.AppUser_;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Log4j2
@Repository
public class UserDao extends AbstractDao<AppUser> implements IUserDao {

    @Override
    protected Class<AppUser> getClazz() {
        return AppUser.class;
    }

    @Override
    public void update(Long id, AppUser update) {
        AppUser user = getById(id);
        Optional.ofNullable(update.getName()).ifPresent(user::setName);
        Optional.ofNullable(update.getPhone()).ifPresent(user::setPhone);
        Optional.ofNullable(update.getDiscount()).ifPresent(user::setDiscount);
        Optional.ofNullable(update.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(update.getRoles()).ifPresent(user::setRoles);
        entityManager.merge(user);
    }

    @Override
    public AppUser findByUsername(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppUser> query = builder.createQuery(AppUser.class);
        Root<AppUser> root = query.from(AppUser.class);
        query.select(root).where(builder.equal(root.get(AppUser_.LOGIN), login));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            log.info("User not found");
            throw new DaoException("User not found", e);
        }
    }

    @Override
    public AppUser checkUserByUsername(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AppUser> query = builder.createQuery(AppUser.class);
        Root<AppUser> root = query.from(AppUser.class);
        query.select(root).where(builder.equal(root.get(AppUser_.LOGIN), login));
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            log.info("User not found");
            return null;
        }
    }

}
