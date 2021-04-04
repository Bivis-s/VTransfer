package by.bivis.telegram_bot.post_types.attachments;

import by.bivis.telegram_bot.post_types.attachments.attachable.Attachable;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.util.ArrayList;
import java.util.List;

//TODO
public class AttachableContainer extends ArrayList<Attachable> {
    private List<Attachable> attachableList = new ArrayList<>();

    public AttachableContainer(List<Attachable> attachableList) {
        this.attachableList = attachableList;
    }

    public List<Attachable> getList() {
        return attachableList;
    }

    public int size() {
        return attachableList.size();
    }

    public Attachable get(int i) {
        return attachableList.get(i);
    }

    public boolean add(Attachable attachable) {
        attachableList.add(attachable);
        return true;
    }

    public List<InputMedia> getInputMediasList() {
        List<InputMedia> inputMediaList = new ArrayList<>();
        for (Attachable attachment : attachableList) {
            inputMediaList.add(attachment.getInputMedia());
        }
        return inputMediaList;
    }

    public List<InputMedia> getInputMediasClassList(Class<?> classParameter) {
        List<InputMedia> inputMediaList = new ArrayList<>();
        for (Attachable attachment : attachableList) {
            if (attachment.getClass().isAssignableFrom(classParameter)) {
                inputMediaList.add(attachment.getInputMedia());
            }
        }
        return inputMediaList;
    }

    public List<Attachable> getAttachableClassList(Class<?> classParameter) {
        List<Attachable> attachables = new ArrayList<>();
        for (Attachable attachment : attachableList) {
            if (attachment.getClass().isAssignableFrom(classParameter)) {
                attachables.add(attachment);
            }
        }
        return attachables;
    }

    public int getClassCount(Class<?> classParameter) {
        int counter = 0;
        for (Attachable attachable : attachableList) {
            if (attachable.getClass().isAssignableFrom(classParameter)) {
                counter++;
            }
        }
        return counter;
    }
}
