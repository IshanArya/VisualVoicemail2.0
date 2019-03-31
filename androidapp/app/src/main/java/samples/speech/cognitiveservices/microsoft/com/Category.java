package samples.speech.cognitiveservices.microsoft.com;

import java.util.HashMap;

public enum Category {
    NOT_SPAM("not-spam"),
    LOW_PRIORITY("low-priority"),
    URGENT("urgent"),
    SPAM("spam");

    String category;
    private static HashMap<String, Category> map = new HashMap<>();

    Category(String category) {
        this.category = category;
    }

    static {
        for(Category c : Category.values()) {
            map.put(c.category, c);
        }
    }

    public static Category get(String category) {
        return map.get(category);
    }



}
