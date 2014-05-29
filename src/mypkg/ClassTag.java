package mypkg;

public class ClassTag {
    private String classTag = "";

    public ClassTag(String className) {
        classTag = "class=\"" + className + "\"";
    }

    public String getClassTag() { return classTag; }
}
