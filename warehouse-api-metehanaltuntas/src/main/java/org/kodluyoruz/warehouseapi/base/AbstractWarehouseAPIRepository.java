package org.kodluyoruz.warehouseapi.base;

import org.hibernate.Session;
import org.kodluyoruz.warehouseapi.model.entites.BaseEntity;
import org.kodluyoruz.warehouseapi.model.enums.WarehouseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@Transactional(readOnly = true)
public abstract class AbstractWarehouseAPIRepository<T extends BaseEntity, ID extends Long>
        implements WarehouseAPICRUDBaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entity;


    @SuppressWarnings("unchecked")
    public AbstractWarehouseAPIRepository() {
        this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<T> list() {
        return getSession().createQuery("from " +
                entity.getName()+ " where status = 'ACTIVE'", entity).getResultList();
    }

    @Override
    @Transactional
    public T create(T entity) {
        getSession().persist(entity);
        return entity;
    }

    // update metodu da aynı şekilde
    @Override
    @Transactional
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    // delete işleminde datalar fiziksel olarak silinmez. status bilgisini deleted' a çekiyoruz. o id' ye sahip olan nesne için
    @Override
    @Transactional
    public void delete(ID id) {
        getSession()
                .createQuery("update " + entity.getName() + " set status = 'DELETED' where id=:entityId")
                .setParameter("entityId", id)
                .executeUpdate();
    }
}
