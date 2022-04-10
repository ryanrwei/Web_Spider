import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import org.jsoup.nodes.Element;
import java.util.HashSet; // Import the HashSet class

/*
1.先進入一個page
2.找出該網址的all_links
3. for i in all_links:
    if i not in page:
        newPage = i
        page.add(newPage)
        recur(newPage)

System.out.println(boardLink.charAt(0));
System.out.println(((Object)boardLink.charAt(0)).getClass().getSimpleName()); // show var type
*/


class PTT_WholeSpider{

    static HashSet<String> linkSet = new HashSet<String>();

    static void recur(Document doc) throws IOException{
        Elements Boards = doc.select("a"); //board

        for (Element Board : Boards){
            String boardLink = Board.attr("href");

            if (!boardLink.isEmpty()){  //boardLink(String) contains nothing
                if (boardLink.charAt(0) != '/'){ //https://www.ptt.cccontact.html exist, which not contain '/'
                    continue; }
            }

            switch(boardLink){  //can't visit
                case "/man/SMTH/S62462E56.html": continue;
                case "/man/index.html": continue;
                case "/bbs/joke/M.1308670085.A.18B.html": continue;
                case "/bbs/SYSOP/M.1301538882.A.971.html": continue;
                case "/man/SYSOP/DEBB/M.1264469665.A.02F.html": continue;
            }

            if ( ! (linkSet.contains("https://www.ptt.cc" + boardLink)) ){
                System.out.println("Links:" + "https://www.ptt.cc" + boardLink);
                linkSet.add("https://www.ptt.cc" + boardLink);
                Document docSub = Jsoup.connect("https://www.ptt.cc" + boardLink).get();
                recur(docSub);
            }
        }
        System.out.println("-----------------------------------"); //出現代表已到達某分支最深
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.ptt.cc/bbs/index.html").get();
        recur(doc);
    }
}