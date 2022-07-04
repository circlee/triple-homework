package guide.triple.homework.app.point;

import guide.triple.homework.app.point.model.ActivePoint;
import guide.triple.homework.core.point.service.UserPointOverallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
@RestController
public class UserPointController {

    private final UserPointOverallService userPointOverallService;

    @GetMapping("/activePoint")
    public ActivePoint getUserActivePoint(@PathVariable("userId") String userId) {

        BigDecimal activePoint = userPointOverallService.getUserActivePoint(userId);
        return ActivePoint.of(activePoint);
    }

}
