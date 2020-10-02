package com.egen.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "item")
public class Item
{
    @Id
    @Column(name = "item_id")
    int itemID;

    @Column(name = "item_quantity")
    int itemQuantity;

    @Column(name = "item_unit_price")
    double itemUnitPrice;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "created_by")
    String createdBy;

    @UpdateTimestamp
    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    String modifiedBy;

    @Column(name = "soft_delete")
    boolean softDelete;

    public Item() {
        this.createdDate = LocalDateTime.now();
    }
}
