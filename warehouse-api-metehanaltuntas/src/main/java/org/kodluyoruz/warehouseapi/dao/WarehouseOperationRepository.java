package org.kodluyoruz.warehouseapi.dao;

public interface WarehouseOperationRepository {

    boolean hasExistSameWarehouseCode(String warehouseCode);

    boolean isThereaWarehouse();

    boolean isThereAnyProductForThisWarehouse(Long warehouseId);
}
