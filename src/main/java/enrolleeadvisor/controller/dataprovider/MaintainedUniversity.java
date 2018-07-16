package enrolleeadvisor.controller.dataprovider;

import enrolleeadvisor.controller.dataprovider.spbu.SpbuContestProvider;
import enrolleeadvisor.controller.dataprovider.spbu.SpbuEnrolleeProvider;
import enrolleeadvisor.controller.dataprovider.spbu.SpbuStudyDirectionProvider;
import enrolleeadvisor.model.Contest;
import enrolleeadvisor.model.Enrollee;
import enrolleeadvisor.model.StudyDirection;

public enum MaintainedUniversity {
    SPBU(new SpbuEnrolleeProvider(), new SpbuStudyDirectionProvider(), new SpbuContestProvider());

    private DataProvider<Enrollee> enrolleeProvider;
    private DataProvider<StudyDirection> studyDirectionProvider;
    private DataProvider<Contest> contestProvider;

    MaintainedUniversity(DataProvider<Enrollee> enrolleeProvider,
                         DataProvider<StudyDirection> studyDirectionProvider,
                         DataProvider<Contest> contestProvider) {
        this.enrolleeProvider = enrolleeProvider;
        this.studyDirectionProvider = studyDirectionProvider;
        this.contestProvider = contestProvider;
    }

    public void initialize() throws DataProviderException {
        enrolleeProvider.initialize();
        studyDirectionProvider.initialize();
        contestProvider.initialize();
    }

    public DataProvider<Enrollee> getEnrolleeProvider() {
        return enrolleeProvider;
    }

    public DataProvider<StudyDirection> getStudyDirectionProvider() {
        return studyDirectionProvider;
    }

    public DataProvider<Contest> getContestProvider() {
        return contestProvider;
    }
}
