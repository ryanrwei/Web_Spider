import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NFT_spider {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.ptt.cc/bbs/Kaohsiung/index.html").get();

        Elements titles = doc.select("div.r-ent>div.title>a");
        for (Element title : titles) {
            String linkHref = title.attr("href");
            System.out.println(title.text());
            System.out.println("link:" + "https://www.ptt.cc" + linkHref);
        }
    }
}
