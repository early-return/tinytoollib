package info.zhiqing.tinytoollib.jav;

import java.util.Date;

/**
 * Created by zhiqing on 16-12-31.
 */
public class JavInfo {
    private String code;
    private String title;
    private String coverUrl;
    private String publishDate;
    private String length;
    private String publisher;
    private String producer;
    private String[] tags;
    private String[] actresses;
    private String[] thumbnails;
    private JavMagnet[] magnets;

    public JavInfo() {}
    public JavInfo(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getActresses() {
        return actresses;
    }

    public void setActresses(String[] actresses) {
        this.actresses = actresses;
    }

    public String[] getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String[] thumbnails) {
        this.thumbnails = thumbnails;
    }

    public JavMagnet[] getMagnets() {
        return magnets;
    }

    public void setMagnets(JavMagnet[] magnets) {
        this.magnets = magnets;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
