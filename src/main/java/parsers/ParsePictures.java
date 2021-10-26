package parsers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ParsePictures {

    //private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36 OPR/79.0.4143.73";
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 YaBrowser/21.9.1.684 Yowser/2.5 Safari/537.36";
    private String url;
    private Connection session;

    public ParsePictures(){
        session = Jsoup.newSession().userAgent(userAgent);
    }

    public void setUrl(String query) {

        String[] q = query.split(" ");
        StringBuilder part = new StringBuilder("");

        for(int i = 0; i < q.length; i++){
            part.append(q[i]);
            if(i < q.length - 1) part.append("%20");
        }

        String u = "https://yandex.ru/images/search?text=";
        System.out.println(u + part.toString());
        this.url = u + part.toString();
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
            resultURLs.remove(0); //опасные действия в принципе
            if (num > elements.size()) resultURLs.remove(resultURLs.size()-1);

            if(resultURLs.isEmpty()) resultURLs.add("ничего не найдено!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultURLs;
    }

}

