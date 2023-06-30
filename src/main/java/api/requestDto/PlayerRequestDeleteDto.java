package api.requestDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerRequestDeleteDto {

    private Integer playerId;
}
