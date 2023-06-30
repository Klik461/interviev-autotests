package api.responseDto;

import lombok.Data;

@Data
public class PlayerDeleteResponseDto {

    private Object body;
    private String statusCode;
    private Integer statusCodeValue;
}
