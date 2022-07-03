package guide.triple.homework.core.point.service;

import guide.triple.homework.core.point.service.dto.EventDTO;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class UserPointOverallServiceTest {

    @Autowired
    UserPointOverallService userPointOverallService;

    @Test
    void addUserPointByEvent() {
        //given
        String userId = "1111-1111-1111";
        String reviewId = "review-1111-1111";
        String placeId = "place-1111-1111";
        Long eventId = 1111L;
        EventDTO eventDTO = createReviewEventDto(userId, reviewId, placeId, eventId);

        //when
        userPointOverallService.addUserPointByEvent(eventDTO);

        //then
        BigDecimal activePoint = userPointOverallService.getUserActivePoint(userId);

        Assertions.assertEquals(BigDecimal.valueOf(2), activePoint);
    }

    @Test
    void addUserPointByEvent2() {
        //given
        String userId = "1111-1111-1111";
        String reviewId = "review-1111-1111";
        String reviewId2 = "review-1111-1222";
        String placeId = "place-1111-1111";
        Long eventId = 1111L;
        EventDTO eventDTO1 = createReviewEventDto(userId, reviewId, placeId, eventId);
        EventDTO eventDTO2 = createReviewEventDto(userId, reviewId2, placeId, eventId);

        //when
        userPointOverallService.addUserPointByEvent(eventDTO1);
        userPointOverallService.addUserPointByEvent(eventDTO2);

        //then
        BigDecimal activePoint = userPointOverallService.getUserActivePoint(userId);

        Assertions.assertEquals(BigDecimal.valueOf(4), activePoint);
    }

    @Test
    void addUserPointByEvent3() {
        //given
        String userId = "1111-1111-1111";
        String reviewId = "review-1111-1111";
        String placeId = "place-1111-1111";
        Long eventId = 1112L;
        EventDTO eventDTO1 = createReviewEventDto(userId, reviewId, placeId, eventId, "1234", Arrays.asList("123"));
        EventDTO eventDTO2 = createReviewEventDto(userId, reviewId, placeId, eventId, "123", Arrays.asList());
        EventDTO eventDTO3 = createReviewEventDto(userId, reviewId, placeId, eventId, "", Arrays.asList());
        EventDTO eventDTO4 = createReviewEventDto(userId, reviewId, placeId, eventId, "1234", Arrays.asList("123"));
        EventDTO eventDTO5 = createReviewEventDto(EventDTO.EVENT_ACTION.DELETE, userId, reviewId, placeId, eventId, "1234", Arrays.asList("123"));

        //when
        userPointOverallService.addUserPointByEvent(eventDTO1);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(2), userPointOverallService.getUserActivePoint(userId));

        //when
        userPointOverallService.addUserPointByEvent(eventDTO2);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(1), userPointOverallService.getUserActivePoint(userId));

        //when
        userPointOverallService.addUserPointByEvent(eventDTO3);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(0), userPointOverallService.getUserActivePoint(userId));

        //when
        userPointOverallService.addUserPointByEvent(eventDTO4);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(2), userPointOverallService.getUserActivePoint(userId));

        //when
        userPointOverallService.addUserPointByEvent(eventDTO5);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(0), userPointOverallService.getUserActivePoint(userId));
    }



    @Test
    void getUserActivePoint() {


    }

    private ReviewEventDTO createReviewEventDto(String userId, String reviewId, String placeId, Long eventId) {

        return createReviewEventDto(userId, reviewId, placeId, eventId, "asd", Arrays.asList("123"));
    }

    private ReviewEventDTO createReviewEventDto(String userId, String reviewId, String placeId, Long eventId, String content, List<String> photoIds) {

        return createReviewEventDto(EventDTO.EVENT_ACTION.ADD, userId, reviewId, placeId, eventId, content, photoIds);
    }

    private ReviewEventDTO createReviewEventDto(EventDTO.EVENT_ACTION eventAction, String userId, String reviewId, String placeId, Long eventId, String content, List<String> photoIds) {

        return ReviewEventDTO.builder()
                .eventId(eventId)
                .userId(userId)
                .reviewId(reviewId)
                .placeId(placeId)
                .eventAction(eventAction)
                .content(content)
                .attachedPhotoIds(photoIds)
                .build();
    }


}