package menu.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coaches {
    private final List<Coach> coaches;

    public Coaches(List<String> coaches) {
        this.coaches = coaches.stream()
                .map(String::trim)
                .map(Coach::new)
                .toList();
        validateCoachCount();
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    private void validateCoachCount() {
        Set<String> uniqueCoaches = new HashSet<>(
                coaches.stream()
                        .map(Coach::getName)
                        .toList()
        );

        if (coaches.size() < 2 || coaches.size() > 5) {
            throw new IllegalArgumentException("[ERROR] 코치 인원 수는 2~5명까지 입니다.");
        }
        //Coach의 본인 유일성 판단 기준을 외부에 뺏긴 상태.
        // 코치 이름 중복 확인에 대한 객체지향성 우려. getter로 가져와 중복 여부를 체크하고 있는 구조 문제!!
        if (uniqueCoaches.size() != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 코치 이름은 유일해야 합니다.");
        }
    }
}
