package ir.cactus.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "t_product")
public class Product {

    private Long id;
    private String productName;
    private Long price;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "c_productName")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Column(name = "c_productPrice")
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
