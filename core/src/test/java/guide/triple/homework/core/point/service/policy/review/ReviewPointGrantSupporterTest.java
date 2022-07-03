package guide.triple.homework.core.point.service.policy.review;

import guide.triple.homework.core.point.domain.PointGrantType;
import guide.triple.homework.core.point.service.dto.ReviewEventDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Set;

@SpringBootTest
class ReviewPointGrantSupporterTest {

    @Autowired
    ReviewPointGrantSupporter reviewPointGrantSupporter;

    @DisplayName("pointGrantTypes - REVIEW_TEXT, REVIEW_PHOTO")
    @Test
    void getPointGrantTypes() {

        //given
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
        ReviewEventDTO reviewEventDTO = ReviewPointGrandTestUtil.create("", Arrays.asList("1"));

        //when
        Set<PointGrantType> grantTypeSet = reviewPointGrantSupporter.getPointGrantTypes(reviewEventDTO);

        //then
        Assertions.assertEquals(1, grantTypeSet.size());
        Assertions.assertTrue(grantTypeSet.contains(PointGrantType.REVIEW_PHOTO));
    }
}