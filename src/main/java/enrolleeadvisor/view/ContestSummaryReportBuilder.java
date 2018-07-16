package enrolleeadvisor.view;

import enrolleeadvisor.model.Exam;

import java.io.OutputStream;
import java.util.List;

public interface ContestSummaryReportBuilder {
    void build(OutputStream outputStream, List<Exam> examList);
}
