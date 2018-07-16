package enrolleeadvisor.controller.dataprovider.spbu;

import enrolleeadvisor.controller.dataprovider.DataProviderException;
import enrolleeadvisor.controller.dataprovider.MaintainedUniversity;
import enrolleeadvisor.controller.dataprovider.RestorableDataProvider;
import enrolleeadvisor.controller.dataprovider.jsoup.JsoupElementParser;
import enrolleeadvisor.model.Exam;
import enrolleeadvisor.model.Financing;
import enrolleeadvisor.model.StudyDirection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpbuStudyDirectionProvider extends RestorableDataProvider<StudyDirection> {
    private static final String STORE_FILE_NAME = "SpbuStudyDirections.ser";

    private static final String CONTEST_URL_FORMAT = "https://cabinet.spbu.ru/Lists/1k_EntryLists" + "/list_%s.html";

    private static final String NAME_REGEX = "(?<=<b>Направление:</b>).*?(?=<br)";
    private static final String CAPACITY_REGEX = "(?<=<b>КЦП по конкурсу:</b>).*?(?=<br)";
    private static final String QUOTA_REGEX = "(?<=<b>в т.ч. квотные места:</b>).*?(?=<br)";
    private static final String FINANCING_REGEX = "(?<=<b>Основа обучения:</b>).*?(?=<br)";
    private static final String EXAM_REGEX = "(?<=<b> ВИ \\d?: ).*?(?=</b>)";

    public SpbuStudyDirectionProvider() {
        super(STORE_FILE_NAME);
    }

    public static void main(String[] args) throws IOException, DataProviderException {
        MaintainedUniversity.SPBU.initialize();
        SpbuStudyDirectionProvider studyDirectionProvider = (SpbuStudyDirectionProvider) MaintainedUniversity.SPBU.getStudyDirectionProvider();
        studyDirectionProvider.clearCache();
        MaintainedUniversity.SPBU.getEnrolleeProvider().getAll();
        studyDirectionProvider.store();
    }

    @Override
    public StudyDirection load(String id) throws DataProviderException, IOException {
        String contestURL = String.format(CONTEST_URL_FORMAT, id);
        JsoupElementParser parser = new JsoupElementParser(Jsoup.connect(contestURL).get().select("body").select("p").get(0));

        StudyDirection studyDirection = new StudyDirection();
        studyDirection.setId(id);
        studyDirection.setName(parser.getText(NAME_REGEX));
        studyDirection.setCapacity(parser.getInteger(CAPACITY_REGEX));
        studyDirection.setFinancing(Financing.get(parser.getText(FINANCING_REGEX)));

        if (parser.getText(QUOTA_REGEX) != null)
            studyDirection.setQuota(parser.getInteger(QUOTA_REGEX));

        List<Exam> examList = new ArrayList<>(3);
        for (String alias : parser.getTexts(EXAM_REGEX))
            examList.add(Exam.get(alias));
        studyDirection.setExamList(examList);

        return studyDirection;
    }
}
