package org.kodluyoruz.warehouseapi.converter;

import org.kodluyoruz.warehouseapi.base.WarehouseAPIConverter;
import org.kodluyoruz.warehouseapi.model.dto.ProductDTO;
import org.kodluyoruz.warehouseapi.model.entites.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ProductEntityToProductDTOConverter implements WarehouseAPIConverter<ProductEntity, ProductDTO> {
    @Override
    public ProductDTO convert(ProductEntity input) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(input.getId());
        productDTO.setCode(input.getCode());
        productDTO.setName(input.getName());
        productDTO.setVatRate(input.getVatRate());
        productDTO.setVatAmount(input.getVatAmount());
        productDTO.setPrice(input.getPrice());
        productDTO.setVatIncludedPrice(input.getVatIncludedPrice());
        productDTO.setWarehouseEntity(input.getWarehouseEntity());
        productDTO.setCreatedAt(Objects.isNull(input.getCreatedAt()) ? new Date() : input.getCreatedAt());
        productDTO.setUpdatedAt(input.getUpdatedAt());

        return productDTO;
    }
}
