package info.zhiqing.tinytoollib.jav;

import info.zhiqing.tinytoollib.shared.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhiqing on 16-12-31.
 */
public class JavFetcher {
    private String baseUrl = "https://www.javbus5.com/";
    private HttpClient httpClient = new HttpClient();

    public JavFetcher() { }

    public JavFetcher(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
        this.baseUrl = url;
    }

    public JavInfo fetchByCode(String code) {
        JavInfo jav = new JavInfo(code);
        code = code.trim().toUpperCase();
        String url = baseUrl + code;
        try {
            Document doc = Jsoup.parse(httpClient.get(url));

            setCoverUrl(jav, doc);

            setBaseInfo(jav, doc);

            setTags(jav, doc);

            setAtresses(jav, doc);





        } catch (IOException ioe) {
            System.out.println("JavFetcher fetch failed!");
        }
        return jav;
    }

    private void setCoverUrl(JavInfo jav, Document doc) {
        Elements eles = doc.select(".screencap img");
        jav.setCoverUrl(eles.first().attr("src"));
    }

    private String getJavInfoBody(Element ele) {
        ele.select(".header").remove();
        return ele.text().trim();
    }

    private void setBaseInfo(JavInfo jav, Document doc) {
        Elements eles = doc.select(".movie .info p");
        jav.setPublishDate(new Date(getJavInfoBody(eles.get(1))));
        jav.setLength(getJavInfoBody(eles.get(2)));
        jav.setProducer(getJavInfoBody(eles.get(4)));
        jav.setPublisher(getJavInfoBody(eles.get(5)));
    }

    private void setTags(JavInfo jav, Document doc) {
        Elements eles = doc.select("span.genre a");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < eles.size(); i++) {
            list.add(eles.get(i).text());
        }
        jav.setTags(list.toArray(new String[0]));
    }

    private void setAtresses(JavInfo jav, Document doc) {
        Elements eles = doc.select("div.star-name a");
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < eles.size(); i++) {
            list.add(eles.get(i).text());
        }
        jav.setActresses(list.toArray(new String[0]));
    }

    private void setThumbnailUrls(JavInfo jav, Document doc) {
        
    }
}
