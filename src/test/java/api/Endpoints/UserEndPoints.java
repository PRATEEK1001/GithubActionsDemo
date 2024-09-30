package api.Endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//To create the CRUD (cretae retrive Update Delete) operations
public class UserEndPoints {

  public static Response CreateUser(User payload){
      Response response=
                  given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                  .when()
                    .post(Routes.post_URl);

      return response;
  }

    public static Response readUser(String username){
        Response response=
                given()
                        .pathParam("username",username)
                .when()
                        .get(Routes.get_URl);

        return response;
    }

    public static Response updateUser(User payload,String username){
        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("username",username)
                        .body(payload)
                .when()
                        .put(Routes.update_URl);
        return response;
    }

    public static Response deleteUser(String username){
        Response response=
                given()
                        .pathParam("username",username)
                        .when()
                        .delete(Routes.delete_URl);
        return response;
    }

}



