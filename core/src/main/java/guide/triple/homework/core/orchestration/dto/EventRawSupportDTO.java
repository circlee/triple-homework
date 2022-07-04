package guide.triple.homework.core.orchestration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventRawSupportDTO {

    private EVENT_TYPE type;

    private EVENT_ACTION action;

    private String reviewId;

    private String userId;

    private String placeId;

    private String content;

    private List<String> attachedPhotoIds;

    private String rawJson;


    public enum EVENT_TYPE {
        REVIEW
    }

    public enum EVENT_ACTION {
        ADD,
        MOD,
        DELETE
    }

}
