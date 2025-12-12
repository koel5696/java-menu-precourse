package menu.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoachNames {
    private final List<Coach> coachNames;

    public CoachNames(List<String> coachNames) {
        this.coachNames = coachNames.stream()
                .map(String::trim)
                .map(Coach::new)
                .toList();
        validateCoachCount();
    }

    public List<Coach> getCoachNames() {
        return coachNames;
    }

    private void validateCoachCount() {
        Set<String> uniqueCoachNames = new HashSet<>(
                coachNames.stream()
                        .map(Coach::getName)
                        .toList()
        );

        if (coachNames.size() < 2 || coachNames.size() > 5) {
            throw new IllegalArgumentException("[ERROR] 코치 인원 수는 2~5명까지 입니다.");
        }

        if (uniqueCoachNames.size() != coachNames.size()) {
            throw new IllegalArgumentException("[ERROR] 코치 이름은 유일해야 합니다.");
        }
    }
}
