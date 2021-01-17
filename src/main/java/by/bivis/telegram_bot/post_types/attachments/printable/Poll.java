package by.bivis.telegram_bot.post_types.attachments.printable;

import java.util.List;

public class Poll implements Printable {
    private String question;
    private List<String> answers;

    public Poll() {
    }

    public Poll setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Poll setAnswers(List<String> answers) {
        this.answers = answers;
        return this;
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
    public String getFormattedText() {
        return String.format("❓ [Опрос] %s\n%s", question, buildAnswersString());
    }
}
