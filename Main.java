
public static void main(String[] args) throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimple("cubo1.json");
        Cube cubo;

        /* Person ben = new Person(
        (String) jsonObject.get(""),
        Integer.valueOf(jsonObject.get("age").toString()),
        (Boolean) jsonObject.get("isMarried"),
        (List<String>) jsonObject.get("hobbies"),
        (List<Person>) jsonObject.get("kids"));
*/
        //System.out.println(ben);
        }
public static Object readJsonSimple(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
        }