package guide.triple.homework.core.point.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public enum PointGrantType {
    REVIEW_TEXT(BigDecimal.ONE),
    REVIEW_PHOTO(BigDecimal.ONE),
    FIRST_REVIEW_IN_PLACE(BigDecimal.ONE)
    ;

    @Getter
    private final BigDecimal pointValue;



}
