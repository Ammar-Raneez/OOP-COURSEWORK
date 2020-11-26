package controllers;

import play.mvc.*;

public class TestController extends Controller {
    public Result test() {
        //ok - status code 200
        //this return is of type Result
        //renders the test html file inside the views folder passing two parameters
        return ok(views.html.test.render("some parameter text", "second parameter"));
    }
}