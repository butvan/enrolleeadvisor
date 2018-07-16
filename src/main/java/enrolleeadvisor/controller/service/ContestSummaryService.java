package enrolleeadvisor.controller.service;

import enrolleeadvisor.controller.dataprovider.DataProviderException;
import enrolleeadvisor.controller.dataprovider.MaintainedUniversity;
import enrolleeadvisor.model.ContestSummary;
import enrolleeadvisor.model.Exam;
import enrolleeadvisor.model.StudyDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContestSummaryService {
    public List<ContestSummary> getContestSummary(List<Exam> exams) throws DataProviderException {
        List<ContestSummary> contestSummaries = new ArrayList<>();
        for (MaintainedUniversity maintainedUniversity : MaintainedUniversity.values())
            contestSummaries.addAll(getContestSummary(exams, maintainedUniversity));
        return contestSummaries;
    }

    public List<ContestSummary> getContestSummary(List<Exam> exams, MaintainedUniversity maintainedUniversity) throws DataProviderException {
        return maintainedUniversity.getStudyDirectionProvider().getAll().stream()
                .filter(studyDirection -> studyDirection.getExamList().containsAll(exams))
                .map(studyDirection -> getContestSummary(maintainedUniversity, studyDirection))
                .collect(Collectors.toList());
    }

    private ContestSummary getContestSummary(MaintainedUniversity maintainedUniversity, StudyDirection studyDirection) {
        ContestSummary contestSummary = new ContestSummary();
//        DataProvider dataProvider = maintainedUniversity.getDataProvider();

        return contestSummary;
    }
}
