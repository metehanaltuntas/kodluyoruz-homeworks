package org.kodluyoruz.warehouseapi.service;

public interface WarehouseOperationService {
    boolean hasExistSameWarehouseCode(String warehouseCode);

    boolean isThereaWarehouse();

    boolean isThereAnyProductForThisWarehouse(Long warehouseId);

}
