package enrolleeadvisor.controller.dataprovider.spbu;

import enrolleeadvisor.controller.dataprovider.CacheableDataProvider;
import enrolleeadvisor.controller.dataprovider.DataProviderException;
import enrolleeadvisor.controller.dataprovider.DataProviderNotInitializedException;
import enrolleeadvisor.controller.dataprovider.MaintainedUniversity;
import enrolleeadvisor.controller.dataprovider.jsoup.JsoupElementsParser;
import enrolleeadvisor.model.Diploma;
import enrolleeadvisor.model.Enrollee;
import enrolleeadvisor.model.StudyDirection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpbuEnrolleeProvider extends CacheableDataProvider<Enrollee> {
    private static final int MAX_BODY_SIZE = 10000000;
    private static final String URL = "https://cabinet.spbu.ru/Lists/1k_EntryLists";
    private static final String STUDY_DIRECTIONS_URL_FORMAT = "https://cabinet.spbu.ru/Lists/1k_EntryLists/data/%s.txt";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private Document document;

    @Override
    public void initialize() throws DataProviderException {
        try {
            Connection connection = Jsoup.connect(URL);
            connection.maxBodySize(MAX_BODY_SIZE);
            document = connection.get();
        } catch (IOException e) {
            throw new DataProviderException("Can't get html from url:" + URL, e);
        }
    }

    private Document getDocument() throws DataProviderNotInitializedException {
        if (document == null)
            throw new DataProviderNotInitializedException("You should complete initialize() before using getAll()");
        return document;
    }

    @Override
    public Enrollee load(String id) throws DataProviderException, IOException {
        Element trElement = getDocument().select("tbody").select("tr td[id=" + id + "]").get(0).parent();
        JsoupElementsParser parser = new JsoupElementsParser(trElement.select("td"));

        Enrollee enrollee = new Enrollee();
        enrollee.setId(id);
        enrollee.setRegistrationNumber(parser.getText(1));
        enrollee.setName(parser.getText(2));

        try {
            enrollee.setBirthDate(dateFormat.parse(parser.getText(3)));
        } catch (ParseException e) {
            throw new DataProviderException(
                    String.format("Can't parse birthDate of enrollee with registrationNumber = %s: %s",
                            enrollee.getRegistrationNumber(), URL),
                    e);
        }

        enrollee.setDiploma(parser.getText(5).equals("Да") ? Diploma.ORIGINAL : Diploma.COPY);

        List<StudyDirection> studyDirections = new ArrayList<>(3);
        for (Element aElement : Jsoup.connect(String.format(STUDY_DIRECTIONS_URL_FORMAT, id)).get().select("a")) {
            String studyDirectionId = aElement.attr("href").replace("list_", "").replaceFirst("\\.html#.*", "");
            studyDirections.add(MaintainedUniversity.SPBU.getStudyDirectionProvider().get(studyDirectionId));
        }
        enrollee.setStudyDirections(studyDirections);

        return enrollee;
    }

    @Override
    public Collection<Enrollee> getAll() throws DataProviderException {
        Collection<Enrollee> enrollees = new ArrayList<>();
        for (Element element : getDocument().select("tbody").select("tr"))
            enrollees.add(get(element.select("td").get(4).id()));
        return enrollees;
    }
}
