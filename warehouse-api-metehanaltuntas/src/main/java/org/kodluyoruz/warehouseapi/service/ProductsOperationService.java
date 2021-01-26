package org.kodluyoruz.warehouseapi.service;

public interface ProductsOperationService {
    boolean hasExistSameProductCode(String warehouseCode);
    boolean isThereaProduct();
    boolean isThereAnyProductForThisId(Long id);
}
