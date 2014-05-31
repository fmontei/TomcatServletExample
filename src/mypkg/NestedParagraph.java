package mypkg;

public class NestedParagraph implements NestedElement {
    private String paragraph;

    public NestedParagraph(String paragraph, NestedElement element) {
        this.paragraph = paragraph.substring(0, paragraph.lastIndexOf("</p>") + 1) + element.toString() + "</p>";
    }

    public NestedParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String toString() {
        return "<p>" + getParagraph() + "</p>";
    }

    public String getParagraph() {
        return paragraph;
    }
}
