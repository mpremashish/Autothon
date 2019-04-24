package JsonCollector;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


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
        Response response = given().get("http://jsonplaceholder.typicode.com/posts");
        List<Integer> userId = response.jsonPath().getList("userId");
        List<Integer> id = response.jsonPath().getList("id");
        List<String> title = response.jsonPath().getList("title");
        List<String> body = response.jsonPath().getList("body");

        findFibonacci(10).forEach(i -> jsonDataObjects.push(new JsonDataObject(userId.get(i), id.get(i),
                title.get(i), body.get(i))));

        return jsonDataObjects;

    }
}