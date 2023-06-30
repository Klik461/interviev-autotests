package api.tests;

import api.BaseTest;
import api.requestDto.PlayerRequestDeleteDto;
import api.requestDto.PlayerRequestDto;
import api.responseDto.PlayerResponseDto;
import api.steps.PlayerSteps;
import api.utils.Editor;
import org.testng.annotations.Test;

public class PlayerNegativeTests extends BaseTest {

    @Test
    public void checkNegativeCreate() {
        PlayerSteps.createPlayer(PlayerRequestDto.builder().role("TestRole").build(), 400);
    }

    @Test
    public void checkDeleteNegative() {
        PlayerRequestDto playerRequestDto = PlayerRequestDto.builder().build();

        PlayerSteps.createPlayer(playerRequestDto);
        PlayerResponseDto responseDto = PlayerSteps.getAllPlayers().stream()
                .filter(player -> player.getScreenName().equals(playerRequestDto.getScreenName()))
                .findFirst()
                .orElseThrow();

        PlayerSteps.deletePlayers(PlayerRequestDeleteDto.builder()
                .playerId(responseDto.getId()).build(), Editor.admin);
    }

    @Test
    public void checkNegativeGetById() {
        PlayerSteps.getPlayerById(999999999, 404);
    }
}
