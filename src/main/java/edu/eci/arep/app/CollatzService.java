package edu.eci.arep.app;

import static spark.Spark.*;

public class CollatzService {

    public static void main(String... args){
        port(getPort());
        staticFileLocation("/");
        get("collatzsequence", (req,res) -> {
            return "{\n" +
                    "\n" +
                    " \"operation\": \"collatzsequence\",\n" +
                    "\n" +
                    " \"input\":  "+ req.queryParams("value") +",\n" +
                    "\n" +
                    " \"output\":  \"" + collatzCalculation(req.queryParams("value")) + "\"\n" +
                    "\n" +
                    "}";
        });
    }

    private static String collatzCalculation(String number) {
        int n = Integer.parseInt(number);
        String result = number + " -> ";
        while (n != 1) {
            if (n % 2 == 0) {
                n = n/2;
            } else {
                n = (3*n) + 1;
            }
            if (n == 1) {
                result = result + n;
            } else {
                result = result + n + " -> ";
            }
        }
        return result;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}