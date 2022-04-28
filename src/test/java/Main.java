import dev.liev.mcstats.webserver.StatsServerImpl;
import dev.liev.mcstats.webserver.handlers.RequestHandler;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.toString("-hello".split("-")));
        /*

        StatsServerImpl server = new StatsServerImpl();

        try {
            server.bind("localhost", 8000, new RequestHandler[0]);
            server.start();
        } catch (IOException e) {
            System.out.println("Could not start api server: " + e.getMessage());
        }*/
    }

}
