package firstSpringBootApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String name;
    private Category category;
    private Double price;
    private Boolean isInStock;
}
