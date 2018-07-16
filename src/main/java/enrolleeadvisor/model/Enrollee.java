package enrolleeadvisor.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Enrollee {
    private String id;
    private String registrationNumber;
    private String name;
    private Date birthDate;
    private Diploma diploma;
    private List<StudyDirection> studyDirections;
    private Map<Exam, Integer> scores;
    private List<Status> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Diploma getDiploma() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma = diploma;
    }

    public List<StudyDirection> getStudyDirections() {
        return studyDirections;
    }

    public void setStudyDirections(List<StudyDirection> studyDirections) {
        this.studyDirections = studyDirections;
    }

    public Map<Exam, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Exam, Integer> scores) {
        this.scores = scores;
    }

    public List<Status> getResults() {
        return results;
    }

    public void setResults(List<Status> results) {
        this.results = results;
    }
}
