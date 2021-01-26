package org.kodluyoruz.warehouseapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.warehouseapi.dao.WarehouseOperationRepository;
import org.kodluyoruz.warehouseapi.service.WarehouseOperationService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class WarehouseOperationServiceImpl implements WarehouseOperationService {

    private final WarehouseOperationRepository warehouseOperationRepository;

    @Override
    public boolean hasExistSameWarehouseCode(String warehouseCode) {
        return warehouseOperationRepository.hasExistSameWarehouseCode(warehouseCode);
    }

    @Override
    public boolean isThereaWarehouse() {
        return warehouseOperationRepository.isThereaWarehouse();
    }

    @Override
    public boolean isThereAnyProductForThisWarehouse(Long warehouseId) {
        return warehouseOperationRepository.isThereAnyProductForThisWarehouse(warehouseId);
    }
}
