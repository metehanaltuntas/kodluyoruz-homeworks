package org.kodluyoruz.warehouseapi.controller;


import lombok.RequiredArgsConstructor;
import org.kodluyoruz.warehouseapi.config.SwaggerClient;
import org.kodluyoruz.warehouseapi.model.dto.BaseIDDTO;
import org.kodluyoruz.warehouseapi.base.WarehouseAPIResponseHolder;
import org.kodluyoruz.warehouseapi.model.dto.ProductDTO;
import org.kodluyoruz.warehouseapi.service.ProductCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@SwaggerClient
@RequestMapping("/products")
public class ProductCRUDController {

    private final ProductCRUDService productCRUDService;

    @GetMapping
    public WarehouseAPIResponseHolder<Collection<ProductDTO>> getAllProducts() {
        return productCRUDService.list();
    }

    @PostMapping()
    public WarehouseAPIResponseHolder<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return productCRUDService.create(productDTO);
    }

    @PutMapping
    public WarehouseAPIResponseHolder<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        return productCRUDService.update(productDTO);
    }

    @DeleteMapping
    public WarehouseAPIResponseHolder<?> delete(@RequestBody BaseIDDTO id) {
        return productCRUDService.delete(id);
    }
}
