package org.kodluyoruz.warehouseapi.dao.impl;

import org.kodluyoruz.warehouseapi.base.AbstractWarehouseAPIRepository;
import org.kodluyoruz.warehouseapi.dao.WarehouseOperationRepository;
import org.kodluyoruz.warehouseapi.model.entites.WarehouseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseOperationRepositoryImpl extends AbstractWarehouseAPIRepository<WarehouseEntity, Long> implements WarehouseOperationRepository {

    @Override
    public boolean hasExistSameWarehouseCode(String warehouseCode) {
        Long result = getSession()
                .createQuery("select count(*) from WarehouseEntity where code=:givenCode", Long.class)
                .setParameter("givenCode", warehouseCode)
                .uniqueResult();
        return result > 0;
    }

    @Override
    public boolean isThereaWarehouse() {
        Long result = getSession()
                .createQuery("select count(*) from WarehouseEntity", Long.class)
                .uniqueResult();
        return result > 0;
    }

    @Override
    public boolean isThereAnyProductForThisWarehouse(Long warehouseId) {
        Long result = getSession()
                .createQuery("select count(*) from ProductEntity where warehouse_id=:warehouseId", Long.class)
                .setParameter("warehouseId",warehouseId)
                .uniqueResult();
        return result > 0;
    }
}
