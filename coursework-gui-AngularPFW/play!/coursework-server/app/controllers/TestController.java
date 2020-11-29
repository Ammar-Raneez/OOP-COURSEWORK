package controllers;

import play.mvc.*;
import play.libs.Json;

public class TestController extends Controller {
    public Result test() {
        models.Test testModel = new models.Test("This message is from play!");
        return ok(Json.toJson(testModel));
    }

    public Result testWithParam(String param) {
        models.Test testModel = new models.Test("Welcome!, " + param);
        return  ok(Json.toJson(testModel));
    }
}