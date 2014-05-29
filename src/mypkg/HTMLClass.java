package mypkg;

public class HTMLClass {
    private String classTag = "";

    public HTMLClass(String className) {
        classTag = "class=\"" + className + "\"";
    }

    public String getClassTag() { return classTag; }
}
