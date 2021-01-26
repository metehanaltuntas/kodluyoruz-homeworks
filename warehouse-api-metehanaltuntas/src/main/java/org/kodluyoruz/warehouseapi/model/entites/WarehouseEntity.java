package org.kodluyoruz.warehouseapi.model.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.warehouseapi.model.enums.WarehouseStatusEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "WAREHOUSE")
public class WarehouseEntity extends BaseEntity {

    @Column(name = "CODE", unique = true, length = 50, nullable = false)
    private String code;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS", length = 7, nullable = false)
    private WarehouseStatusEnum status = WarehouseStatusEnum.ACTIVE;

}
