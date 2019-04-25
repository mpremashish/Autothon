import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

import static io.restassured.RestAssured.*;


public class JsonCollector {

//    private static List<Integer> findFibonacci(int num) {
//        List<Integer> al = new ArrayList<>();
//        int a = 0;
//        int b = 1;
//        int next;
//        al.add(a);
//        al.add(b);
//        for (int i = 0; i < num - 2; i++) {
//            next = a + b;
//            al.add(next);
//            a = b;
//            b = next;
//        }
//        return al;
//    }

    public static Stack<JsonDataObject> findJsonObjects() {
        Stack<JsonDataObject> jsonDataObjects = new Stack<JsonDataObject>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "OAuth oauth_consumer_key=\"3QHsalYXMeQchpOhwZWc2HfrC\",oauth_token=\"1121272893663584256-RoLkgcGZs7caL56l4YrCu9QZBVbmfD\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1556169937\",oauth_nonce=\"z7fyV9pdBwd\",oauth_version=\"1.0\",oauth_signature=\"qTHTOHbIV%2BCPp7nRs%2BWVWkS%2Fdqw%3D\"");
        headers.put("Postman-Token", "0dbe5f73-da1e-42ed-8af0-72ca9be4aecc");

        RestAssured.baseURI ="https://api.twitter.com/1.1/search/tweets.json?q=stepin_forum&result_type=recent&count=50&screen_name=stepin_forum";
        RequestSpecification request = RestAssured.given();

        Response response = RestAssured.given(this.requestSpecification)
                .headers(headers)
                .when()
                .get(baseURI);


// Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());


//        Response response = given().get("https://api.twitter.com/1.1/search/tweets.json?q=stepin_forum&result_type=recent&count=50&screen_name=stepin_forum");
//        List<Integer> userId = response.jsonPath().getList("userId");
//        List<Integer> id = response.jsonPath().getList("id");
//        List<String> title = response.jsonPath().getList("title");
//        List<String> body = response.jsonPath().getList("body");

//        findFibonacci(10).forEach(i -> jsonDataObjects.push(new JsonDataObject(userId.get(i), id.get(i),
//                title.get(i), body.get(i))));
//
//        return jsonDataObjects;

    }
}