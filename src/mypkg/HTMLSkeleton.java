package mypkg;

import java.util.HashMap;
import java.util.Map;

public class HTMLSkeleton {
    private String HTMLCode = "";
    private Map<String, Object> attributeMap =  new HashMap<String, Object>();

    public HTMLSkeleton() {

    }

    public void createHTMLText() {
        HTMLCode += "<!DOCTYPE html><html></html>";
    }

    public void createHTMLHeadAndStyle(String title) {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</html>"));
        newText += "<head></head>";
        newText += HTMLCode.substring(HTMLCode.indexOf("</html>"));
        HTMLCode = newText;
        createHTMLTitle(title);
        createStyle();
    }

    private void createHTMLTitle(String title) {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</head>"));
        newText += "<title>" + title + "</title>";
        newText += HTMLCode.substring(HTMLCode.indexOf("</head>"));
        HTMLCode = newText;
    }

    private void createStyle() {
        String style = "<style></style>";
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</head>"));
        newText += style;
        newText += HTMLCode.substring(HTMLCode.indexOf("</head>"));
        HTMLCode = newText;
    }

    public HTMLSkeleton createHTMLBody() {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</html>"));
        newText += "<body></body>";
        newText += HTMLCode.substring(HTMLCode.indexOf("</html>"));
        HTMLCode = newText;
        return this;
    }

    public HTMLSkeleton addHeader(String header, Object tag) {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</body>"));
        if (tag instanceof ClassTag) {
            newText += "<h1 " + ((ClassTag) tag).getClassTag() + ">" + header + "</h1>";
        }
        else {
            newText += "<h1>" + header + "</h1>";
        }
        newText += HTMLCode.substring(HTMLCode.indexOf("</body>"));
        HTMLCode = newText;
        return this;
    }

    public HTMLSkeleton addParagraph(String text, Object tag) {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</body>"));
        if (tag instanceof ClassTag) {
            newText += "<p " + ((ClassTag) tag).getClassTag() + ">" + text + "</p>";
        }
        else if (tag instanceof NestedParagraph) {
            newText += "<p>" + ((NestedParagraph) tag).toString() + "</p>";
        }
        else {
            newText += "<p>" + text + "</p>";
        }
        newText += HTMLCode.substring(HTMLCode.indexOf("</body>"));
        HTMLCode = newText;
        return this;
    }

    public ClassTag wrapWithClassTag(String className) {
        ClassTag newHTMLClass = new ClassTag(className);
        attributeMap.put(className.toLowerCase(), newHTMLClass);
        return newHTMLClass;
    }

    public void updateStyle(final StylesheetCSS styleSheet) {
        String newStyle = styleSheet.getCode();
        final String firstPart = HTMLCode.substring(0, HTMLCode.indexOf("</style>"));
        final String middlePart = newStyle;
        final String lastPart = HTMLCode.substring(HTMLCode.indexOf("</style>"));
        HTMLCode = firstPart + middlePart + lastPart;
    }

    public String getHTMLCode() {
        return HTMLCode;
    }

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }
}
