package mypkg;

// Import required java libraries
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class ServletExample extends HttpServlet {

    private String HTMLText = "";
    private Map<String, Object> attributeMap =  new HashMap<String, Object>();

    public String getHTMLText() {
        return HTMLText;
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
            .addParagraph("Another one! <span>Random words, Random words</span>, Random words.",
                    wrapWithClassTag("fancy"));
        updateStyle(new StylesheetCSS(attributeMap));

        out.println(HTMLText);
    }

    private void createHTMLText() {
        HTMLText += "<!DOCTYPE html><html></html>";
    }

    private void createHTMLHeadAndStyle(String title) {
        String newText = HTMLText.substring(0, HTMLText.indexOf("</html>"));
        newText += "<head></head>";
        newText += HTMLText.substring(HTMLText.indexOf("</html>"));
        HTMLText = newText;
        createHTMLTitle(title);
        createStyle();
    }

    private void createHTMLTitle(String title) {
        String newText = HTMLText.substring(0, HTMLText.indexOf("</head>"));
        newText += "<title>" + title + "</title>";
        newText += HTMLText.substring(HTMLText.indexOf("</head>"));
        HTMLText = newText;
    }

    private void createStyle() {
        String style = "<style></style>";
        String newText = HTMLText.substring(0, HTMLText.indexOf("</head>"));
        newText += style;
        newText += HTMLText.substring(HTMLText.indexOf("</head>"));
        HTMLText = newText;
    }

    private ClassTag wrapWithClassTag(String className) {
        ClassTag newHTMLClass = new ClassTag(className);
        attributeMap.put(className.toLowerCase(), newHTMLClass);
        return newHTMLClass;
    }

    private ServletExample createHTMLBody() {
        String newText = HTMLText.substring(0, HTMLText.indexOf("</html>"));
        newText += "<body></body>";
        newText += HTMLText.substring(HTMLText.indexOf("</html>"));
        HTMLText = newText;
        return this;
    }

    private ServletExample addHeader(String header, Object tag) {
        String newText = HTMLText.substring(0, HTMLText.indexOf("</body>"));
        if (tag instanceof ClassTag) {
            newText += "<h1 " + ((ClassTag) tag).getClassTag() + ">" + header + "</h1>";
        }
        else {
            newText += "<h1>" + header + "</h1>";
        }
        newText += HTMLText.substring(HTMLText.indexOf("</body>"));
        HTMLText = newText;
        return this;
    }

    private ServletExample addParagraph(String text, Object tag) {
        String newText = HTMLText.substring(0, HTMLText.indexOf("</body>"));
        if (tag instanceof ClassTag) {
            newText += "<p " + ((ClassTag) tag).getClassTag() + ">" + text + "</p>";
        }
        else {
            newText += "<p>" + text + "</p>";
        }
        newText += HTMLText.substring(HTMLText.indexOf("</body>"));
        HTMLText = newText;
        return this;
    }

    private void updateStyle(final StylesheetCSS textStyle) {
        String newStyle = textStyle.getStyleSheet();
        final String firstPart = HTMLText.substring(0, HTMLText.indexOf("</style>"));
        final String middlePart = newStyle;
        final String lastPart = HTMLText.substring(HTMLText.indexOf("</style>"));
        HTMLText = firstPart + middlePart + lastPart;
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
        hwe.updateStyle(new StylesheetCSS(hwe.attributeMap));
        System.out.println(hwe.getHTMLText());
    }
}