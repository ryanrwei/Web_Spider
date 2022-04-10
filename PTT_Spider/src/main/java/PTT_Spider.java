import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PTT_Spider {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.ptt.cc/bbs/hotboards.html").get();
        Elements Board = doc.select("div.b-ent>a.board"); //board

        for (Element Boards : Board) {
            String BoardName = Boards.select(".board-name").text();
            System.out.println(BoardName);
            String BoardsLink = Boards.attr("href");

            Document doc2 = Jsoup.connect("https://www.ptt.cc" + BoardsLink).cookie("over18","1").get();
            Elements Article = doc2.select("div.r-ent"); // board/article
            for (Element Articles : Article) {
                String ArticleName = Articles.select(".title>a").text();
                System.out.println(ArticleName);

                Elements ArticleLink = Articles.select(".title>a");
                String ArticleLinks = ArticleLink.attr("href");

                Document doc3 = Jsoup.connect("https://www.ptt.cc" + ArticleLinks).get();
                System.out.println("Links:" + "https://www.ptt.cc" + ArticleLinks);
                Elements Comment = doc3.select("div.push>span.f3.push-content"); // board/article/comment

                for (Element Comments : Comment) {
                    System.out.println(Comments.text());
                    break;
                }
                break;
            }
            System.out.println("--------------------------------------");
        }
    }

}
