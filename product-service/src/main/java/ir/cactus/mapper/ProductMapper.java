package ir.cactus.mapper;


import ir.cactus.domain.dto.ProductRequestDTO;
import ir.cactus.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductRequestDTO toDto(Product product);

}
