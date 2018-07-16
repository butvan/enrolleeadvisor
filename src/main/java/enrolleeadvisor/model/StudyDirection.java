package enrolleeadvisor.model;

import java.io.Serializable;
import java.util.List;

public class StudyDirection implements Serializable {
    private static final long serialVersionUID = -4014350862167613180L;

    private String id;
    private String name;
    private List<Exam> examList;
    private Integer capacity;
    private Integer quota;
    private Financing financing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Financing getFinancing() {
        return financing;
    }

    public void setFinancing(Financing financing) {
        this.financing = financing;
    }
}
