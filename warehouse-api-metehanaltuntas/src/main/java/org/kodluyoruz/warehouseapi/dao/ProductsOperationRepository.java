package org.kodluyoruz.warehouseapi.dao;

public interface ProductsOperationRepository {
    boolean hasExistSameProductCode(String productCode);
    boolean isThereaProduct();
    boolean isThereAnyProductForThisId(Long id);
}
