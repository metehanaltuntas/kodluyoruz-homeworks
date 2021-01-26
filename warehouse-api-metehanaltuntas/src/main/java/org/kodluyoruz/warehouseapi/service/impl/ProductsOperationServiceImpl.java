package org.kodluyoruz.warehouseapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.warehouseapi.dao.ProductsOperationRepository;
import org.kodluyoruz.warehouseapi.service.ProductsOperationService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductsOperationServiceImpl implements ProductsOperationService {

    private final ProductsOperationRepository productsOperationRepository;

    @Override
    public boolean hasExistSameProductCode(String productCode) {
        return productsOperationRepository.hasExistSameProductCode(productCode);
    }

    @Override
    public boolean isThereaProduct() {
        return productsOperationRepository.isThereaProduct();
    }

    @Override
    public boolean isThereAnyProductForThisId(Long id) {
        return productsOperationRepository.isThereAnyProductForThisId(id);
    }
}
