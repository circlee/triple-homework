package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.domain.UserReviewPointDetailRepository;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ReviewPointGrantSupporterTest {


    ReviewTextPointGrantPolicy reviewTextPointGrantPolicy = new ReviewTextPointGrantPolicy();
    ReviewPhotoPointGrantPolicy reviewPhotoPointGrantPolicy = new ReviewPhotoPointGrantPolicy();
    UserReviewPointDetailRepository userReviewPointDetailRepository = Mockito.mock(UserReviewPointDetailRepository.class);
    ReviewFirstReviewInPlacePointGrantPolicy reviewFirstReviewInPlacePointGrantPolicy = new ReviewFirstReviewInPlacePointGrantPolicy(userReviewPointDetailRepository);

    ReviewPointGrantSupporter reviewPointGrantSupporter = new ReviewPointGrantSupporter(
            Arrays.asList(
                    reviewTextPointGrantPolicy
                    , reviewPhotoPointGrantPolicy
                    , reviewFirstReviewInPlacePointGrantPolicy
            )
    );



    @DisplayName("pointGrantTypes - REVIEW_TEXT, REVIEW_PHOTO")
    @Test
    void getPointGrantTypes() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(1L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList("1234", "12345"));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(2, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_TEXT));
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_PHOTO));

    }

    @DisplayName("pointGrantTypes - REVIEW_TEXT")
    @Test
    void getPointGrantTypes_only_REVIEW_TEXT() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(1L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList(""));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(1, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_TEXT));
    }

    @DisplayName("pointGrantTypes - REVIEW_PHOTO")
    @Test
    void getPointGrantTypes_only_REVIEW_PHOTO() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(1L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("", Arrays.asList("1"));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(1, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_PHOTO));
    }

    @DisplayName("pointGrantTypes - REVIEW_TEXT, REVIEW_PHOTO + first review")
    @Test
    void getPointGrantTypes_firstReview() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(0L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList("1234", "12345"));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(3, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_TEXT));
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_PHOTO));
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.FIRST_REVIEW_IN_PLACE));

    }

    @DisplayName("pointGrantTypes - REVIEW_TEXT + first review")
    @Test
    void getPointGrantTypes_only_REVIEW_TEXT_firstReview() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(0L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("1234", Arrays.asList(""));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(2, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_TEXT));
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.FIRST_REVIEW_IN_PLACE));
    }

    @DisplayName("pointGrantTypes - REVIEW_PHOTO + first review")
    @Test
    void getPointGrantTypes_only_REVIEW_PHOTO_firstReview() {

        //given
        Mockito.when(userReviewPointDetailRepository.countByPlaceIdAndActive(Mockito.anyString(), Mockito.anyBoolean()))
                .thenReturn(0L);
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("", Arrays.asList("1"));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(2, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_PHOTO));
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.FIRST_REVIEW_IN_PLACE));
    }
}