package secondSpringBootAppWithSpringBootData.dto.errorDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorDto {
    private String field;
    private String message;
    private Object rejectedValue;

    // добюавить
    public FieldErrorDto(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }
}
