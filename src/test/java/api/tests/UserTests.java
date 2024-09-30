package api.tests;

import api.Endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTests {

    User objUser;
    Faker ObjFaker;

    @BeforeClass
    public void setData(){
        objUser = new User();
        ObjFaker = new Faker();

        objUser.setFirstName(ObjFaker.name().firstName());
        objUser.setLastName(ObjFaker.name().lastName());
        objUser.setUsername(ObjFaker.name().username());
        objUser.setEmail(ObjFaker.internet().safeEmailAddress());
        objUser.setPassword(ObjFaker.internet().password(5,12));
        objUser.setPhone(ObjFaker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPost_User(){

        Response res=UserEndPoints.CreateUser(objUser);
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGet_User(){

        Response res=UserEndPoints.readUser(objUser.getUsername());
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdate_User(){
        objUser.setUsername("PRTK");
        objUser.setFirstName("Prateek");
        objUser.setLastName("Gupta");

        Response res=UserEndPoints.updateUser(objUser,objUser.getUsername());
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(),200);
       // Assert.assertEquals(res.then().log().body(),200);
        System.out.println("Body: date after update is "+res.then().log().body());

        Response res1=UserEndPoints.readUser(objUser.getUsername());
        res1.then().log().all();

    }

    @Test(priority = 4)
    public void DeleteTest(){

        Response res=UserEndPoints.deleteUser(objUser.getUsername());
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(),200);
    }



}
