package pl.piomin.java.samples.textblocks;

public class TextBlockString {

    public String getOldPrettyPrintJson() {
        return "{\n" +
               "     \"firstName\": \"Piotr\",\n" +
               "     \"lastName\": \"Mińkowski\"\n" +
               "}";
    }

    public String getNewPrettyPrintJson() {
        return """
               {
                    "firstName": "Piotr",
                    "lastName": "Mińkowski"
               }
               """;
    }
}
