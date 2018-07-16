package enrolleeadvisor;

import enrolleeadvisor.controller.dataprovider.DataProviderException;
import enrolleeadvisor.model.Exam;
import enrolleeadvisor.view.PlainTextContestSummaryReportBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static enrolleeadvisor.model.Exam.*;

public class EnrolleeAdvisor {
    public static void main(String[] args) throws DataProviderException {
        List<Exam> exams = new ArrayList<>(Arrays.asList(MATHEMATICS, INFORMATICS, RUSSIAN));
        new PlainTextContestSummaryReportBuilder().build(System.out, exams);
    }
}
