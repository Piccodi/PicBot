package parsers;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ParsePictures {

    private String userAgent = "your user agent";
    private String url;
    private Connection session;

    public ParsePictures(){
        session = Jsoup.newSession().userAgent(userAgent);
    }

    public void setUrl(String query) {

        String[] q = query.split(" ");
        StringBuilder part = new StringBuilder();

        for(int i = 0; i < q.length; i++){
            part.append(q[i]);
            if(i < q.length - 1) part.append("%20");
        }

        String u = "https://yandex.ru/images/search?text=";
        System.out.println(u + part);
        this.url = u + part;
    }

    List<String> resultURLs = new ArrayList<String>();

    public List<String> parsing(Integer num){

        try{

            Document doc = session.newRequest().url(url).get();
            Elements elements = doc.select("img");

            int i = 0;
            for(Element elem: elements){
                System.out.println(elem.attr("src"));
                resultURLs.add("https:" + elem.attr("src"));
                if(i >= num) break;
                i++;
            }
            resultURLs.remove(0);
            if (num > elements.size()) resultURLs.remove(resultURLs.size()-1);

            if(resultURLs.isEmpty()) resultURLs.add("ничего не найдено!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultURLs;
    }

}

