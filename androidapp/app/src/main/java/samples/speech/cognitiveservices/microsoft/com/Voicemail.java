package samples.speech.cognitiveservices.microsoft.com;

public class Voicemail {
    public String text;
    public Category category;
    public Voicemail(String text) {
        this.text = text;
        this.category = null;
    }

    public Voicemail(String text, Category category) {
        this(text);
        this.category = category;
    }
    public Voicemail(String text, String category) {
        this(text, Category.get(category));
    }

}
