package by.bivis.vkParser.JSONs.tools;

public class FormatText {
    public static String format(String text) {
        return text
                .replaceAll("\\*", "\\*")
                .replaceAll("\\[", "\\[")
                .replaceAll("\\\\" + "_", "\\_")
                .replaceAll("`", "\\`")
                .replaceAll("˜", "\\˜")
                .replaceAll("!", "\\!")
                .replaceAll("\\?", "\\?")
                .replaceAll("\\.",  "\\.")
                .replaceAll("—", "\\—");
    }
}
