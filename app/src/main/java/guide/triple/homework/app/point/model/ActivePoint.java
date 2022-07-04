package guide.triple.homework.app.point.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class ActivePoint {

    private final BigDecimal activePoint;
}
