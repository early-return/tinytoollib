package info.zhiqing.tinytoollib.jav;

import java.util.Date;

/**
 * Created by zhiqing on 16-12-31.
 */
public class JavMagnet {
    private String url;
    private long size;
    private Date date;

    public JavMagnet() {

    }
    public JavMagnet(String url) {
        this.url = url;
    }
    public JavMagnet(String url, long size, Date date) {
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
