package org.kodluyoruz.warehouseapi.dao.impl;

import org.kodluyoruz.warehouseapi.base.AbstractWarehouseAPIRepository;
import org.kodluyoruz.warehouseapi.dao.ProductsOperationRepository;
import org.kodluyoruz.warehouseapi.model.entites.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsOperationRepositoryImpl extends AbstractWarehouseAPIRepository<ProductEntity, Long> implements ProductsOperationRepository {
    @Override
    public boolean hasExistSameProductCode(String productCode) {
        Long result = getSession()
                .createQuery("select count(*) from ProductEntity where code=:givenCode", Long.class)
                .setParameter("givenCode", productCode)
                .uniqueResult();
        return result > 0;
    }

    @Override
    public boolean isThereaProduct() {
        Long result = getSession()
                .createQuery("select count(*) from ProductEntity", Long.class)
                .uniqueResult();
        return result > 0;
    }

    @Override
    public boolean isThereAnyProductForThisId(Long id) {
        Long result = getSession()
                .createQuery("select count(*) from ProductEntity where id=:productId", Long.class)
                .setParameter("productId", id)
                .uniqueResult();
        return result > 0;
    }
}
