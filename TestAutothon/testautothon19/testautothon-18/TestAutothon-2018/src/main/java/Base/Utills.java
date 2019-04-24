package Base;

import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import static io.restassured.RestAssured.*;

public class Utills {

	public static List<Integer> findFibonacci(int num) {
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

	public static ResponseBodyExtractionOptions get(String endpoiint) {
		return given().get(endpoiint).then().contentType("application/json").extract().body();
	}

	public int Post(String endpoiint, String payload) {
		Response res = given().body(payload).post(endpoiint).then().extract().response();
		return res.getStatusCode();
	}



}
