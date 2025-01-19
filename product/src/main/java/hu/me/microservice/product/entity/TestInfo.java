package hu.me.microservice.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// Ez a "@Data" dekorátor egyedül arra kell hogy a privát mezőkből készítsen egy lekérdező "getId()" és beállító "setId(1L)" metódust
@Data
@Entity
@Table(name = "test_info")
public class TestInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "status")
    private Integer status;
}
