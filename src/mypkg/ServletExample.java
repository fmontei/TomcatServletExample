package mypkg;

// Import required java libraries
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class ServletExample extends HttpServlet {

    private String HTMLCode = "";
    private Map<String, Object> attributeMap =  new HashMap<String, Object>();

    public String getHTMLCode() {
        return HTMLCode;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // Set refresh, autoload time as 5 seconds
        //response.setIntHeader("Refresh", 5);

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        //out.println("<h1 style=\"color: blue; font-size: 16px; text-align: right\">" + message + "</h1>");

        createHTMLText();
        createHTMLHeadAndStyle("Test Title");
        createHTMLBody()
            .addHeader("Hello World!!!", null)
            .addHeader("Header 1", null)
            .addParagraph("This is a new paragraph! It's awesome!", wrapWithClassTag("fancy"))
            .addParagraph("Another paragraph!", wrapWithClassTag("ugly"))
            .addHeader("Header 2", wrapWithClassTag("ugly"))
            .addParagraph("Another one! <span>Random words, Random words</span>, Random words.", wrapWithClassTag("fancy"));

        StylesheetCSS CSSCode = new StylesheetCSS(attributeMap);
        updateStyle(CSSCode);
        out.println(HTMLCode);
    }

    private void createHTMLText() {
        HTMLCode += "<!DOCTYPE html><html></html>";
    }

    private void createHTMLHeadAndStyle(String title) {
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

    private ServletExample createHTMLBody() {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</html>"));
        newText += "<body></body>";
        newText += HTMLCode.substring(HTMLCode.indexOf("</html>"));
        HTMLCode = newText;
        return this;
    }

    private ServletExample addHeader(String header, Object tag) {
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

    private ServletExample addParagraph(String text, Object tag) {
        String newText = HTMLCode.substring(0, HTMLCode.indexOf("</body>"));
        if (tag instanceof ClassTag) {
            newText += "<p " + ((ClassTag) tag).getClassTag() + ">" + text + "</p>";
        }
        else {
            newText += "<p>" + text + "</p>";
        }
        newText += HTMLCode.substring(HTMLCode.indexOf("</body>"));
        HTMLCode = newText;
        return this;
    }

    private ClassTag wrapWithClassTag(String className) {
        ClassTag newHTMLClass = new ClassTag(className);
        attributeMap.put(className.toLowerCase(), newHTMLClass);
        return newHTMLClass;
    }

    private void updateStyle(final StylesheetCSS styleSheet) {
        String newStyle = styleSheet.getCode();
        final String firstPart = HTMLCode.substring(0, HTMLCode.indexOf("</style>"));
        final String middlePart = newStyle;
        final String lastPart = HTMLCode.substring(HTMLCode.indexOf("</style>"));
        HTMLCode = firstPart + middlePart + lastPart;
    }

    // Test HTML and CSS code
    public static void main(String[] args) {
        ServletExample hwe = new ServletExample();
        hwe.createHTMLText();
        hwe.createHTMLHeadAndStyle("Test Title");
        hwe.createHTMLBody()
            .addHeader("Hello World!!!", null)
            .addHeader("Header 1", null)
            .addParagraph("This is a new paragraph! It's awesome!", hwe.wrapWithClassTag("fancy"))
            .addParagraph("Another paragraph!", hwe.wrapWithClassTag("ugly"))
            .addHeader("Header 2", hwe.wrapWithClassTag("ugly"))
            .addParagraph("Another <span>one</span>!", hwe.wrapWithClassTag("fancy"));

        StylesheetCSS CSSCode = new StylesheetCSS(hwe.attributeMap);
        hwe.updateStyle(CSSCode);
        System.out.println(hwe.getHTMLCode());
    }
}