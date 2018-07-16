package enrolleeadvisor.controller.dataprovider.jsoup;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsoupElementParser {
    private Element element;

    public JsoupElementParser(Element element) {
        this.element = element;
    }

    public String getText(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element.html());
        return matcher.find() ? matcher.group().trim() : null;
    }

    public List<String> getTexts(String regex) {
        List<String> texts = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element.html());
        while (matcher.find())
            texts.add(matcher.group().trim());
        return texts;
    }

    public Integer getInteger(String regex) {
        return Integer.parseInt(getText(regex));
    }
}
