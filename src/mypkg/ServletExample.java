package mypkg;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class ServletExample extends HttpServlet {

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

        HTMLSkeleton html = new HTMLSkeleton();
        html.createHTMLText();
        html.createHTMLHeadAndStyle("Test Title");
        html.createHTMLBody()
            .addHeader("Hello World!!!", null)
            .addHeader("Header 1", null)
            .addParagraph("This is a new paragraph! It's awesome!", html.wrapWithClassTag("fancy"))
            .addParagraph("Another paragraph!", html.wrapWithClassTag("ugly"))
            .addHeader("Header 2", html.wrapWithClassTag("ugly"))
            .addParagraph("Another one! <span>Random words, Random words</span>, Random words.", html.wrapWithClassTag("fancy"));

        StylesheetCSS CSSCode = new StylesheetCSS(html.getAttributeMap());
        html.updateStyle(CSSCode);
        out.println(html.getHTMLCode());
    }

    // Test HTML and CSS code
    public static void main(String[] args) {
        HTMLSkeleton html = new HTMLSkeleton();
        html.createHTMLText();
        html.createHTMLHeadAndStyle("Test Title");
        html.createHTMLBody()
                .addHeader("Hello World!!!", null)
                .addHeader("Header 1", null)
                .addParagraph("This is a new paragraph! It's awesome!", new NestedParagraph("NESTED PARAGRAPH", new NestedParagraph("BLAHBLBHALBHAB")))
                .addParagraph("Another paragraph!", html.wrapWithClassTag("ugly"))
                .addHeader("Header 2", html.wrapWithClassTag("ugly"))
                .addParagraph("Another one! <span>Random words, Random words</span>, Random words.", html.wrapWithClassTag("fancy"));

        StylesheetCSS CSSCode = new StylesheetCSS(html.getAttributeMap());
        html.updateStyle(CSSCode);
        System.out.println(html.getHTMLCode());
    }
}