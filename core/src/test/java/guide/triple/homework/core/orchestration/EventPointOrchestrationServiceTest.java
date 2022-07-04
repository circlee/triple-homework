package guide.triple.homework.core.orchestration;

import com.fasterxml.jackson.databind.ObjectMapper;
import guide.triple.homework.core.orchestration.dto.EventRawSupportDTO;
import guide.triple.homework.core.point.service.UserPointOverallService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@SpringBootTest
class EventPointOrchestrationServiceTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private EventPointOrchestrationService eventPointOrchestrationService;

    @Autowired
    private UserPointOverallService userPointOverallService;

    @Test
    void handleEvent() throws Exception {
        //given
        String rawJson = " {\n" +
                "            \"type\": \"REVIEW\",\n" +
                "                \"action\": \"ADD\",\n" +
                "                \"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\",\n" +
                "                \"content\": \"좋아요!\",\n" +
                "                \"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"],\n" +
                "                \"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\",\n" +
                "                \"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"\n" +
                "        }";

        EventRawSupportDTO eventRawSupportDTO = om.readValue(rawJson, EventRawSupportDTO.class);
        eventRawSupportDTO.setRawJson(rawJson);

        // when
        eventPointOrchestrationService.handleEvent(eventRawSupportDTO);

        // then
        BigDecimal result = userPointOverallService.getUserActivePoint("3ede0ef2-92b7-4817-a5f3-0c575361f745");
        Assertions.assertEquals(BigDecimal.valueOf(3L), result);
    }


}