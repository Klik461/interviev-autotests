package api.tests;

import api.BaseTest;
import api.requestDto.PlayerRequestDeleteDto;
import api.requestDto.PlayerRequestDto;
import api.responseDto.PlayerResponseDto;
import api.steps.PlayerSteps;
import api.utils.Editor;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlayerPositiveTests extends BaseTest {

    @Test
    public void checkCreate() {
        PlayerRequestDto player = PlayerRequestDto.builder().build();

        PlayerResponseDto playerResponseDto = PlayerSteps.createPlayer(player);

        PlayerSteps.assertPlayer(playerResponseDto, player);
    }

    @Test
    public void checkGetAll() {
        PlayerRequestDto playerRequestDto = PlayerRequestDto.builder().build();

        PlayerSteps.createPlayer(playerRequestDto);

        PlayerResponseDto responseDto = PlayerSteps.getAllPlayers().stream()
                .filter(player -> player.getScreenName().equals(playerRequestDto.getScreenName()))
                .findFirst()
                .orElseThrow();

        PlayerSteps.assertPlayer(responseDto, playerRequestDto);
    }

    @Test
    public void checkGetById() {
        PlayerRequestDto playerRequestDto = PlayerRequestDto.builder().build();

        PlayerResponseDto responseDto = PlayerSteps.createPlayer(playerRequestDto);
        PlayerResponseDto responseGetDto = PlayerSteps.getPlayerById(responseDto.getId());

        PlayerSteps.assertPlayer(responseGetDto, playerRequestDto);
    }

    @Test
    public void checkDelete() {
        PlayerRequestDto playerRequestDto = PlayerRequestDto.builder().build();

        PlayerSteps.createPlayer(playerRequestDto);
        PlayerResponseDto responseDto = PlayerSteps.getAllPlayers().stream()
                .filter(player -> player.getScreenName().equals(playerRequestDto.getScreenName()))
                .findFirst()
                .orElseThrow();

        PlayerSteps.deletePlayers(PlayerRequestDeleteDto.builder()
                .playerId(responseDto.getId()).build(), Editor.admin);

        long count = PlayerSteps.getAllPlayers().stream()
                .filter(player -> player.getScreenName().equals(playerRequestDto.getScreenName())).count();

        Assert.assertEquals(count, 0);
    }
}
