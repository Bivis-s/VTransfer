package by.bivis.telegram_bot.post_types.attachments.printable;

import java.util.List;

public class Poll implements Printable {
    private final String question;
    private final List<String> answers;

    public Poll(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    private String buildAnswersString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            sb.append("✅ ").append(answers.get(i));
            if (i < answers.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String getText() {
        return String.format("❓ [Опрос] %s\n%s", question, buildAnswersString());
    }
}
