package by.bivis.telegram_bot.post_types.attachments.attachable;

import by.bivis.telegram_bot.Tools;
import by.bivis.telegram_bot.post_types.attachments.Attachment;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;

public class Video extends Attachment implements Attachable {

    private final InputMediaVideo video;
    private final String title;
    private final String description;
    private final int duration;

    public Video(InputMediaVideo video, String title, String description, int duration) {
        this.video = video;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Video(String videoPath, String title, String description, int duration) {
        this.video = new InputMediaVideo(videoPath);
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    @Override
    public InputMedia getMedia() {
        return video;
    }

    @Override
    public boolean hasText() {
        return true;
    }

    //TODO ADD JAVADOC
    @Override
    public String getText() {
        if (!description.equals("")) {
            return String.format("[%s] %s\n%s", Tools.getFormattedDuration(duration), title, description);
        } else {
            return String.format("[%s] %s", Tools.getFormattedDuration(duration), title);
        }
    }
}
