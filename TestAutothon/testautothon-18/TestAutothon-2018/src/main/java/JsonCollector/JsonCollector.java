package JsonCollector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.it.Ma;
import twitter4j.*;
import twitter4j.conf.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonCollectorJava {
    public static void main(String[] args) {
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
            for (Set : hashtag.entrySet()){
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}