package mypkg;

import java.util.Map;

public class StylesheetCSS {
    private Map<String, Object> attributeMap;
    CSSTextFactory cssTextFactory;

    public StylesheetCSS(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
        createStylesheet();
    }

    // Create CSS Stylesheet here
    private void createStylesheet() {
        cssTextFactory = new CSSTextFactory(attributeMap);
        cssTextFactory.addSelector("fancy")
                .addPropertyToSelector("font-family", "Verdana, serif")
                .addPropertyToSelector("font-style", "underline")
                .addPropertyToSelector("color", "blue");
        cssTextFactory.addSelector("ugly")
                .addPropertyToSelector("font-style", "italic")
                .addPropertyToSelector("font", "16px orange Impact")
                .addPropertyToSelector("background-color", "orange");
    }

    public String getStyleSheet() {
        String textStyle = cssTextFactory.generateCSSText();
        return textStyle;
    }
}
