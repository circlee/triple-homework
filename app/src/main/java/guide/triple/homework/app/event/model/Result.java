package guide.triple.homework.app.event.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Result {

    public static final Result SUCCESS = Result.builder().result("success").build();

    private final String result;
}
