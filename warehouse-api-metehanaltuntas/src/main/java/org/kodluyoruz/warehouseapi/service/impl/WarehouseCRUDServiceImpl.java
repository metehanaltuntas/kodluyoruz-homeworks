package org.kodluyoruz.warehouseapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.warehouseapi.base.WarehouseAPIResponseError;
import org.kodluyoruz.warehouseapi.base.WarehouseAPIResponseHolder;
import org.kodluyoruz.warehouseapi.converter.WarehouseDTOToWarehouseEntityConverter;
import org.kodluyoruz.warehouseapi.converter.WarehouseEntityToWarehouseDTOConverter;
import org.kodluyoruz.warehouseapi.dao.WarehouseCRUDRepository;
import org.kodluyoruz.warehouseapi.model.dto.BaseIDDTO;
import org.kodluyoruz.warehouseapi.model.dto.WarehouseDTO;
import org.kodluyoruz.warehouseapi.model.entites.WarehouseEntity;
import org.kodluyoruz.warehouseapi.service.WarehouseCRUDService;
import org.kodluyoruz.warehouseapi.service.WarehouseOperationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class WarehouseCRUDServiceImpl implements WarehouseCRUDService {

    private final WarehouseCRUDRepository warehouseCRUDRepository;
    private final WarehouseOperationService warehouseOperationService;
    private final WarehouseEntityToWarehouseDTOConverter warehouseEntityToWarehouseDTOConverter;
    private final WarehouseDTOToWarehouseEntityConverter warehouseDTOToWarehouseEntityConverter;


    @Override
    public WarehouseAPIResponseHolder<Collection<WarehouseDTO>> list() {
        Collection<WarehouseEntity> warehouseEntities = warehouseCRUDRepository.list();
        if (CollectionUtils.isEmpty(warehouseEntities)) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NOT_FOUND);
        }
        List<WarehouseDTO> warehouseDTOList = warehouseEntities
                .stream()
                .map(warehouseEntityToWarehouseDTOConverter::convert)
                .collect(Collectors.toList());
        return new WarehouseAPIResponseHolder<>(warehouseDTOList, HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<WarehouseDTO> create(WarehouseDTO data) {
        if (Objects.isNull(data)) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT);
        }
        String warehouseName = data.getName();

        if (warehouseName.isEmpty()) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT);
        }
        boolean isExist = warehouseOperationService.hasExistSameWarehouseCode(data.getCode());

        if (isExist) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("You can not insert with same Warehouse Code")
                    .build());
        }

        WarehouseEntity warehouseEntity = warehouseDTOToWarehouseEntityConverter.convert(data);
        warehouseCRUDRepository.create(warehouseEntity);

        return new WarehouseAPIResponseHolder<>(warehouseEntityToWarehouseDTOConverter
                .convert(warehouseEntity), HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<WarehouseDTO> update(WarehouseDTO data) {
        // depoda hiç ürün var mı
        boolean isAnyWarehouseExists = warehouseOperationService.isThereaWarehouse();
        if (!isAnyWarehouseExists) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("NO_DATA")
                    .message("Sorry,There is no warehouse.")
                    .build());
        }

        // status deleted işaretlenmişse depoda ürün varsa deleted işaretleme
        String warehouseStatus = data.getStatus().toString();
        if (warehouseStatus.equals("DELETED")) {
            boolean thereAnyProductForThisWarehouse = warehouseOperationService.isThereAnyProductForThisWarehouse(data.getId());
            if (thereAnyProductForThisWarehouse){
                return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                        .builder()
                        .code("WAREHOUSE_HAVE_PRODUCTS")
                        .message("Sorry, you can't delete this warehouse because it contains products. Please transfer the products first.")
                        .build());
            }
        }

        // güncelleme yaparken depo codu değiştirilirse ve aynı kod ile kayıtlı başka bir depo varsa kaydı güncelleme
        boolean isExist = warehouseOperationService.hasExistSameWarehouseCode(data.getCode());
        if (isExist) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("Sorry, you can't update this record. There is another record with this code.")
                    .build());
        }

        WarehouseEntity updateEntity = warehouseDTOToWarehouseEntityConverter.convert(data);
        updateEntity.setUpdatedAt(new Date());
        WarehouseEntity updatedEntity = warehouseCRUDRepository.update(updateEntity);
        return new WarehouseAPIResponseHolder<>(warehouseEntityToWarehouseDTOConverter.convert(updatedEntity),
                HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<?> delete(BaseIDDTO id) {

        // ürün kontrolü yapıyoruz
        boolean thereAnyProductForThisWarehouse = warehouseOperationService.isThereAnyProductForThisWarehouse(id.getId());
        if (thereAnyProductForThisWarehouse){
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("WAREHOUSE_HAVE_PRODUCTS")
                    .message("Sorry, you can't delete this warehouse because it contains products. Please transfer the products first.")
                    .build());
        }

        warehouseCRUDRepository.delete(id.getId());
        return new WarehouseAPIResponseHolder<>(HttpStatus.OK);
    }
}
