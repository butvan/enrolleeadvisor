package enrolleeadvisor.model;

public class Contestant {
    private Enrollee enrollee;
    private ContestType contestType;
    private Integer priority;
    private Integer achievementsScore;
    private String achievements;
    private String notes;

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getAchievementsScore() {
        return achievementsScore;
    }

    public void setAchievementsScore(Integer achievementsScore) {
        this.achievementsScore = achievementsScore;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
