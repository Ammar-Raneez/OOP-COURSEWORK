package controllers;

import play.mvc.*;

public class TestController extends Controller {
    public Result test() {
        return ok(views.html.test.render());
    }
}