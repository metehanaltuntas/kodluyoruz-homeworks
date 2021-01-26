package org.kodluyoruz.warehouseapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.warehouseapi.base.WarehouseAPIResponseError;
import org.kodluyoruz.warehouseapi.base.WarehouseAPIResponseHolder;
import org.kodluyoruz.warehouseapi.converter.ProductDTOToProductEntityConverter;
import org.kodluyoruz.warehouseapi.converter.ProductEntityToProductDTOConverter;
import org.kodluyoruz.warehouseapi.dao.ProductCRUDRepository;
import org.kodluyoruz.warehouseapi.model.dto.BaseIDDTO;
import org.kodluyoruz.warehouseapi.model.dto.ProductDTO;
import org.kodluyoruz.warehouseapi.model.entites.ProductEntity;
import org.kodluyoruz.warehouseapi.service.ProductCRUDService;
import org.kodluyoruz.warehouseapi.service.ProductsOperationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCRUDServiceImpl implements ProductCRUDService {

    private final ProductCRUDRepository productCRUDRepository;
    private final ProductsOperationService productsOperationService;
    private final ProductEntityToProductDTOConverter productEntityToProductDTOConverter;
    private final ProductDTOToProductEntityConverter productDTOToProductEntityConverter;

    @Override
    public WarehouseAPIResponseHolder<Collection<ProductDTO>> list() {
        Collection<ProductEntity> productEntities = productCRUDRepository.list();
        if (CollectionUtils.isEmpty(productEntities)) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NOT_FOUND);
        }
        List<ProductDTO> productDTOList = productEntities
                .stream()
                .map(productEntityToProductDTOConverter::convert)
                .collect(Collectors.toList());
        return new WarehouseAPIResponseHolder<>(productDTOList, HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<ProductDTO> create(ProductDTO data) {
        if (Objects.isNull(data)) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT);
        }
        String productName = data.getName();
        String productStatus = data.getStatus().toString();
        String productCode = data.getCode();
        String productPrice = data.getPrice().toString();
        String productVatAmount = data.getVatAmount().toString();
        String productVatIncludedPrice = data.getVatIncludedPrice().toString();
        String productVatRate = data.getVatRate().toString();
        String productWareouse = data.getWarehouseEntity().toString();

        if (productName.isEmpty() || productStatus.isEmpty() || productCode.isEmpty() || productPrice.isEmpty() ||
                productVatAmount.isEmpty() || productVatIncludedPrice.isEmpty() || productVatRate.isEmpty() ||productWareouse.isEmpty()) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT);
        }

        boolean isExist = productsOperationService.hasExistSameProductCode(data.getCode());

        if (isExist) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("You can not insert with same Product Code")
                    .build());
        }

        ProductEntity productEntity = productDTOToProductEntityConverter.convert(data);
        productCRUDRepository.create(productEntity);

        return new WarehouseAPIResponseHolder<>(productEntityToProductDTOConverter
                .convert(productEntity), HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<ProductDTO> update(ProductDTO data) {

        // herhangi bir ürün yoksa update olmaz
        boolean isAnyProductsExists = productsOperationService.isThereaProduct();
        if (!isAnyProductsExists) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("NO_DATA")
                    .message("Sorry,There is no warehouse.")
                    .build());
        }

        // status deleted işaretlenmişse ve depoda ürün varsa deleted işaretleme
        String warehouseStatus = data.getStatus().toString();
        if (warehouseStatus.equals("DELETED")) {
            boolean thereAnyProductForThisId = productsOperationService.isThereAnyProductForThisId(data.getId());
            if (thereAnyProductForThisId){
                return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                        .builder()
                        .code("THERE_IS_A_PRODUCT_HERE")
                        .message("Sorry, you cannot delete this item because this item is in stock.")
                        .build());
            }
        }

        // güncelleme yaparken ürün codu değiştirilirse ve aynı kod ile kayıtlı başka bir ürün varsa kaydı güncelleme
        boolean isExist = productsOperationService.hasExistSameProductCode(data.getCode());
        if (isExist) {
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("DUPLICATE_DATA")
                    .message("Sorry, you can't update this record. There is another record with this code.")
                    .build());
        }

        ProductEntity updateEntity = productDTOToProductEntityConverter.convert(data);
        updateEntity.setUpdatedAt(new Date());
        ProductEntity updatedEntity = productCRUDRepository.update(updateEntity);
        return new WarehouseAPIResponseHolder<>(productEntityToProductDTOConverter.convert(updatedEntity),
                HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<?> delete(BaseIDDTO data) {
        // ürün kontrolü yapıyoruz
        boolean thereAnyProductForThisId = productsOperationService.isThereAnyProductForThisId(data.getId());
        if (thereAnyProductForThisId){
            return new WarehouseAPIResponseHolder<>(HttpStatus.NO_CONTENT, WarehouseAPIResponseError
                    .builder()
                    .code("WAREHOUSE_HAVE_PRODUCTS")
                    .message("Sorry, you can't update this record. There is another record with this code.")
                    .build());
        }

        productCRUDRepository.delete(data.getId());
        return new WarehouseAPIResponseHolder<>(HttpStatus.OK);
    }
}
