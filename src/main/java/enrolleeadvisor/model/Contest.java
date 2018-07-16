package enrolleeadvisor.model;

import java.util.List;
import java.util.Map;

public class Contest {
    private String id;
    private StudyDirection studyDirection;
    private Map<ContestType, List<Contestant>> contestants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StudyDirection getStudyDirection() {
        return studyDirection;
    }

    public void setStudyDirection(StudyDirection studyDirection) {
        this.studyDirection = studyDirection;
    }

    public Map<ContestType, List<Contestant>> getContestants() {
        return contestants;
    }

    public void setContestants(Map<ContestType, List<Contestant>> contestants) {
        this.contestants = contestants;
    }
}
