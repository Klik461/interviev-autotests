package api.steps;

import api.BaseApi;
import api.requestDto.PlayerRequestDto;
import api.responseDto.PlayerDeleteResponseDto;
import api.responseDto.PlayerResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.List;

@Log4j
public class PlayerSteps extends BaseApi {

    private static final String URL = "player/";

    @Step("Create player")
    public static PlayerResponseDto createPlayer(Object body, int... status) {
        log.info("Create player");
        Response response = post(body, URL + "create/supervisor");

        assertStatusCode(response, HttpStatus.SC_CREATED, getNegativeStatus(status));

        return deserialization(response, PlayerResponseDto.class);
    }

    @Step("Update player")
    public static PlayerResponseDto updatePlayer(Object body, int... status) {
        log.info("Update player");
        Response response = put(body, URL + "update/supervisor");

        assertStatusCode(response, HttpStatus.SC_OK, getNegativeStatus(status));

        return response.as(PlayerResponseDto.class);
    }

    @Step("Get all players")
    public static List<PlayerResponseDto> getAllPlayers() {
        log.info("Get all players");
        Response response = get(URL + "get/all");

        assertStatusCode(response, HttpStatus.SC_OK, 0);

        return deserializationList(response, "players", PlayerResponseDto.class);
    }

    @Step("Get player by Id")
    public static PlayerResponseDto getPlayerById(int id, int... status) {
        log.info("Get player by Id");
        Response response = get(URL + "get?playerId=" + id);

        assertStatusCode(response, HttpStatus.SC_OK, getNegativeStatus(status));

        return deserialization(response, PlayerResponseDto.class);
    }

    @Step("Delete player")
    public static void deletePlayers(Object body, String editorName, int... status) {
        log.info("Delete player");
        Response response = delete(body, URL + "delete/" + editorName);

        assertStatusCode(response, HttpStatus.SC_NO_CONTENT, getNegativeStatus(status));
    }

    @Step("Assert player")
    public static void assertPlayer(PlayerResponseDto playerResponseDto, PlayerRequestDto requestDto) {
        Assert.assertEquals(requestDto.getAge(), playerResponseDto.getAge());
        Assert.assertEquals(requestDto.getGender(), playerResponseDto.getGender());
        Assert.assertEquals(requestDto.getLogin(), playerResponseDto.getLogin());
        Assert.assertEquals(requestDto.getPassword(), playerResponseDto.getPassword());
        Assert.assertEquals(requestDto.getRole(), playerResponseDto.getRole());
        Assert.assertEquals(requestDto.getScreenName(), playerResponseDto.getScreenName());
    }
}
