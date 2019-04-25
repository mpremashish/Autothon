package JsonCollector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import static io.restassured.RestAssured.*;

import java.util.*;


public class JsonCollector {

    private static List<Integer> findFibonacci(int num) {
        List<Integer> al = new ArrayList<>();
        int a = 0;
        int b = 1;
        int next;
        al.add(a);
        al.add(b);
        for (int i = 0; i < num - 2; i++) {
            next = a + b;
            al.add(next);
            a = b;
            b = next;
        }
        return al;
    }

    public static Stack<JsonDataObject> findJsonObjects() {
        Stack<JsonDataObject> jsonDataObjects = new Stack<JsonDataObject>();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("3QHsalYXMeQchpOhwZWc2HfrC");
        cb.setOAuthConsumerSecret("sDE8sk6kj0aegjy2fLIZXV5oZRZY5AOH6XcX1LcyYP0tjjNqXT");
        cb.setOAuthAccessToken("1121272893663584256-RoLkgcGZs7caL56l4YrCu9QZBVbmfD");
        cb.setOAuthAccessTokenSecret("7eKt0SbuW0pVzD2F9TTXyrYVDD9bFH9RsUHKIg8yRGemO");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        ObjectMapper mapper=new ObjectMapper();

        Paging paging = new Paging(1, 50);
        int retweethighest=0;
        String retweet = "";
        HashMap<String,Integer> hashtag = new HashMap<>();
        int Likehighest=0;
        try {
            List<Status> statuses = twitter.getUserTimeline(
                    "stepin_forum", paging);
            int count=0;
            for (Status status : statuses) {
                count=count+1;
                if (retweethighest<status.getRetweetCount()){
                    retweethighest=status.getRetweetCount();
                    retweet=status.getText();
                }
                if (Likehighest<status.getFavoriteCount()){
                    Likehighest=status.getFavoriteCount();
                }
                for (HashtagEntity h:status.getHashtagEntities()){
                    if (hashtag.containsKey(h.getText())){
                        int value =hashtag.get(h.getText())+1;
                        hashtag.put(h.getText(),value);
                    }
                    if(h.getText().toLowerCase().matches("[a-zA-Z0-9_]*$")&&h.getText().toLowerCase().matches(".*\\S.*")){
                        System.out.println(h.getText());
                        hashtag.put(h.getText(),1);
                    }
                }

            }
            System.out.println(count);
            System.out.println(retweethighest);
            System.out.println(Likehighest);
            List<Map.Entry<String, Integer> > list =
                    new LinkedList<>(hashtag.entrySet());

            // Sort the list
            Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2)
                {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            // put data from sorted list to hashmap
            HashMap<String, Integer> temp = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            int i=0;
            for (Map.Entry<String, Integer> e : temp.entrySet()){
                if(i<9){
                    System.out.println(e.getValue()+e.getKey());
                }
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return jsonDataObjects;

    }
}