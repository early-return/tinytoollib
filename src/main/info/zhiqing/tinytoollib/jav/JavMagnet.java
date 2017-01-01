package info.zhiqing.tinytoollib.jav;

import java.util.Date;

/**
 * Created by zhiqing on 16-12-31.
 */
public class JavMagnet {
    private String url;
    private String size;
    private String date;

    public JavMagnet() {

    }
    public JavMagnet(String url) {
        this.url = url;
    }
    public JavMagnet(String url, String size, String date) {
        this.url = url;
        this.size = size;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
