package org.kodluyoruz.warehouseapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.kodluyoruz.warehouseapi.model.entites.ProductEntity;
import org.kodluyoruz.warehouseapi.model.enums.WarehouseStatusEnum;

import java.util.Collection;

/**
 * Neden Serializable kullandık? Ayrıca araştırınız.
 */
@Getter
@Setter
public class WarehouseDTO extends BaseIDDTO {

    private String name;
    private String code;
    private WarehouseStatusEnum status;
}
