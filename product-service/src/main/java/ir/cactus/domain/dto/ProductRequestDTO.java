package ir.cactus.domain.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductRequestDTO implements Serializable {

    private String productName;
    private Long productPrice;
}
