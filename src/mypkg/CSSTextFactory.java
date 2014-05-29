package mypkg;

import java.util.Map;

public class CSSTextFactory {
    private String CSSText = "";
    private Map<String, Object> attributeMap;

    public CSSTextFactory(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public String generateCSSText() { return CSSText; }

    public CSSTextFactory addSelector(String selector) {
        if (attributeExists(selector)) {
            Object attribute = attributeMap.get(selector);

            if (attribute instanceof ClassTag) {
                CSSText += ("." + selector + "{}");
            }
        }
        else {
            CSSText += (selector + "{}");
        }

        return this;
    }

    public CSSTextFactory addPropertyToSelector(String propertyName, String argument) {
        final String firstPart = CSSText.substring(0, CSSText.lastIndexOf("{") + 1);
        final String middlePart = propertyName + ": " + argument + ";";
        final String endPart = CSSText.substring(CSSText.lastIndexOf("{") + 1, CSSText.length());
        CSSText = firstPart + middlePart + endPart;
        return this;
    }

    private boolean attributeExists(String selector) {
        return attributeMap.containsKey(selector.toLowerCase());
    }
}
