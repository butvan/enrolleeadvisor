package enrolleeadvisor.controller.dataprovider.spbu;

import enrolleeadvisor.controller.dataprovider.CacheableDataProvider;
import enrolleeadvisor.controller.dataprovider.DataProviderException;
import enrolleeadvisor.controller.dataprovider.MaintainedUniversity;
import enrolleeadvisor.controller.dataprovider.jsoup.JsoupElementsParser;
import enrolleeadvisor.model.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SpbuContestProvider extends CacheableDataProvider<Contest> {
    private static final int MAX_BODY_SIZE = 10000000;

    private static final String CONTEST_URL_FORMAT = "https://cabinet.spbu.ru/Lists/1k_EntryLists" + "/list_%s.html";

    @Override
    public void initialize() throws DataProviderException {
    }

    @Override
    public Contest load(String id) throws DataProviderException, IOException {
        Contest contest = new Contest();

        StudyDirection studyDirection = MaintainedUniversity.SPBU.getStudyDirectionProvider().get(id);
        contest.setStudyDirection(studyDirection);

        contest.setContestants(new HashMap<>());
        for (ContestType contestType : ContestType.values())
            contest.getContestants().put(contestType, new ArrayList<>());

        Connection connection = Jsoup.connect(String.format(CONTEST_URL_FORMAT, id));
        connection.maxBodySize(MAX_BODY_SIZE);
        for (Element trElement : connection.get().select("tbody").select("tr")) {
            JsoupElementsParser elementsParser = new JsoupElementsParser(trElement.select("td"));
            Contestant contestant = new Contestant();

            Enrollee enrollee = MaintainedUniversity.SPBU.getEnrolleeProvider().get(trElement.id());
            contestant.setEnrollee(enrollee);
            contestant.setContestType(ContestType.get(elementsParser.getText(4)));

            contestant.setPriority(elementsParser.getInteger(5));

            List<Exam> examList = studyDirection.getExamList();
            int examListSize = examList.size();
            for (int i = 0; i < examListSize; i++) {
                String text = elementsParser.getText(8 + i);
                if (!text.equals("") && !text.contains("(О)"))
                    enrollee.getScores().put(examList.get(i), elementsParser.getInteger(8 + i));
            }

            contestant.setAchievementsScore(elementsParser.getInteger(8 + examListSize));
            contestant.setAchievements(elementsParser.getText(10 + examListSize));
            contestant.setNotes(elementsParser.getText(11 + examListSize));

            contest.getContestants().get(contestant.getContestType()).add(contestant);
        }

        return contest;
    }

    @Override
    public Collection<Contest> getAll() {
        throw new UnsupportedOperationException();
    }
}
