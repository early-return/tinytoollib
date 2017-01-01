package info.zhiqing.tinytoollib.jav;

import info.zhiqing.tinytoollib.shared.HttpClient;
import info.zhiqing.tinytoollib.shared.ToolsUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.*;

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

            if (doc == null || doc.select("html").size() < 1) {
                return jav;
            }

            setTitle(jav, doc);

            setCoverUrl(jav, doc);

            setBaseInfo(jav, doc);

            setTags(jav, doc);

            setAtresses(jav, doc);

            setThumbnailUrls(jav, doc);

            setMagnets(jav, url, doc);

        } catch (IOException ioe) {
            System.out.println("JavFetcher fetch failed!");
        }
        return jav;
    }

    private void setTitle(JavInfo jav, Document doc) {
        jav.setTitle(doc.select("h3").first().text());
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
        jav.setPublishDate(getJavInfoBody(eles.get(1)).trim());
        jav.setLength(getJavInfoBody(eles.get(2)));
        jav.setProducer(getJavInfoBody(eles.get(4)));
        jav.setPublisher(getJavInfoBody(eles.get(5)));
    }

    private void setTags(JavInfo jav, Document doc) {
        Elements eles = doc.select(".movie .info p").get(8).select("span.genre a");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < eles.size(); i++) {
            list.add(eles.get(i).text());
        }
        jav.setTags(list.toArray(new String[0]));
    }

    private void setAtresses(JavInfo jav, Document doc) {
        Elements eles = doc.select("div.star-name a");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < eles.size(); i++) {
            list.add(eles.get(i).text());
        }
        jav.setActresses(list.toArray(new String[0]));
    }

    private void setThumbnailUrls(JavInfo jav, Document doc) {
        Elements eles = doc.select(".sample-box .photo-frame img");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < eles.size(); i++) {
            list.add(eles.get(i).attr("src"));
        }
        jav.setThumbnails(list.toArray(new String[0]));
    }

    private void setMagnets(JavInfo jav, String url, Document doc) {
        Elements eles = doc.select("script");
        String gid = eles.get(8).html().substring(10, 21);
        eles = doc.select(".screencap img");
        String img = eles.first().attr("src");
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Referer", url);
        try {
            doc = Jsoup.parse(httpClient.get(
                    baseUrl + "ajax/uncledatoolsbyajax.php?" +
                            "gid=" + gid + "&" +
                            "lang=zh&" +
                            "img=" + img + "&" +
                            "uc=0&" +
                            "floor=100",
                    headers
            ));
            eles = doc.select("a");
            List<JavMagnet> list = new ArrayList<JavMagnet>();
            for(int i = 0; i < eles.size(); i += 3) {
                list.add(new JavMagnet(
                        eles.get(i).attr("href"),
                        eles.get(i + 1).text().trim(),
                        eles.get(i + 2).text().trim()
                ));
            }
            jav.setMagnets(list.toArray(new JavMagnet[0]));
        } catch (IOException ioe) {

        }
    }

    public static void main(String[] args) {
        JavFetcher fetcher = new JavFetcher();
        Scanner in = new Scanner(System.in);
        String code = in.nextLine();
        JavInfo jav = fetcher.fetchByCode(code);

        System.out.println("标题：");
        System.out.println(jav.getTitle());
        System.out.println();


        System.out.println("封面图片：");
        System.out.println(jav.getCoverUrl());
        System.out.println();

        System.out.println("制片商：");
        System.out.println(jav.getProducer());
        System.out.println();

        System.out.println("发行商：");
        System.out.println(jav.getPublisher());
        System.out.println();

        System.out.println("标签：");
        for (String tag : jav.getTags()) {
            System.out.println(tag);
        }
        System.out.println();

        System.out.println("演员：");
        for (String atress : jav.getActresses()) {
            System.out.println(atress);
        }
        System.out.println();

        System.out.println("磁力链接：");
        for (JavMagnet magnet : jav.getMagnets()) {
            System.out.println(magnet.getUrl());
        }
        System.out.println();
    }
}
