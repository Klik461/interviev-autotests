package api.requestDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerRequestDto {

    @Builder.Default
    private int age = 20;
    @Builder.Default
    private String gender = "male";
    @Builder.Default
    private String login = "supervisor";
    @Builder.Default
    private String password = "supervisor";
    @Builder.Default
    private String role = "supervisor";
    @Builder.Default
    private String screenName = "Name" + System.currentTimeMillis();
}
