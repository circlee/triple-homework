package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

class ReviewPhotoPointGrantPolicyTest {

    ReviewPhotoPointGrantPolicy reviewPhotoPointGrantPolicy = new ReviewPhotoPointGrantPolicy();

    @DisplayName("getPointGrantType - photo size: 2 - return REVIEW_PHOTO")
    @Test
    void getPointGrantType1() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList("1234", "12345"));

        //when
        Optional<PointGrantType> result = reviewPhotoPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(PointGrantType.REVIEW_PHOTO, result.get());
    }

    @DisplayName("getPointGrantType - photo size: 1 - return REVIEW_PHOTO")
    @Test
    void getPointGrantType2() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList("12345"));

        //when
        Optional<PointGrantType> result = reviewPhotoPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(PointGrantType.REVIEW_PHOTO, result.get());
    }

    @DisplayName("getPointGrantType - photo size: 0 - return empty")
    @Test
    void getPointGrantType3() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList());

        //when
        Optional<PointGrantType> result = reviewPhotoPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isEmpty());
    }

    @DisplayName("getPointGrantType - photo size:1 with blank value - return empty")
    @Test
    void getPointGrantType4() {

        //given
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList(" "));

        //when
        Optional<PointGrantType> result = reviewPhotoPointGrantPolicy.getPointGrantType(reviewEventDTO);

        //then
        Assertions.assertTrue(result.isEmpty());
    }
}