package org.kodluyoruz.warehouseapi.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.kodluyoruz.warehouseapi.model.entites.WarehouseEntity;
import org.kodluyoruz.warehouseapi.model.enums.ProductStatus;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO extends BaseIDDTO {

    private String code;
    private String name;
    private BigDecimal vatRate;
    private BigDecimal vatAmount;
    private BigDecimal price;
    private BigDecimal vatIncludedPrice;
    private ProductStatus status;

    private WarehouseEntity warehouseEntity;

}
