package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

class ReviewTextPointGrantPolicyTest {

    ReviewTextPointGrantPolicy reviewTextPointGrantPolicy = new ReviewTextPointGrantPolicy();

    @DisplayName("getPointGrantType - content size: 1 - return REVIEW_PHOTO")
    @Test
    void getPointGrantType1() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1", Arrays.asList("1234", "12345"));

        //when
        Optional<PointGrantType> result = reviewTextPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(PointGrantType.REVIEW_TEXT, result.get());
    }

    @DisplayName("getPointGrantType - content blank - return REVIEW_PHOTO")
    @Test
    void getPointGrantType2() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("", Arrays.asList("1234", "12345"));

        //when
        Optional<PointGrantType> result = reviewTextPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isEmpty());
    }

    @DisplayName("getPointGrantType - content with null - return empty")
    @Test
    void getPointGrantType3() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create(null, Arrays.asList("1234", "12345"));

        //when
        Optional<PointGrantType> result = reviewTextPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isEmpty());
    }


}