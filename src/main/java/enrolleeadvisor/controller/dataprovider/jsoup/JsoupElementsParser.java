package enrolleeadvisor.controller.dataprovider.jsoup;

import org.jsoup.select.Elements;

public class JsoupElementsParser {
    private Elements elements;

    public JsoupElementsParser(Elements elements) {
        this.elements = elements;
    }

    public String getText(int index) {
        return elements.get(index).html().trim();
    }

    public Integer getInteger(int index) {
        return Integer.parseInt(getText(index));
    }

    public Integer getDoubleAsInteger(int index) {
        return Integer.parseInt(getText(index).replaceFirst("(\\.|,)\\d*", ""));
    }
}
