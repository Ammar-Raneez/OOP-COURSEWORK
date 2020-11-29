package controllers;

import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's hello page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        //ok - status code 200
        //this return is of type Result rendering the index html file
        return ok(views.html.index.render());
    }

    //renders the test html file inside the views folder passing two parameters
    public Result testAnother() {
        return ok(views.html.test.render("some parameter text", "second parameter"));
    }
}
