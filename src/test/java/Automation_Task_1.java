
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class Automation_Task_1 {

    @Test
    public void Assertion_number_of_element() {
        Response response = given()
                // .header("Host","https://cdn.jsdelivr.net")
                .header("User-Agent", "rest-assured/5.3.0")
                .header("Accept", "*/*")
                // .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json");

        String res = response.asString();
        res = res.substring(1, res.length() - 1);
        String strArray[] = res.split(",");

        System.out.println("String : " + res);

        for (int i = 0; i < strArray.length; i++) {
            System.out.print(strArray[i]);
        }
        Map<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < strArray.length; i++) {
            String parts[] = strArray[i].split(":");
            String Abr = parts[0].trim();
            String ff = parts[1].trim();
            Abr = Abr.replaceAll("\\p{Punct}", "");
            ff = ff.replaceAll("\\p{Punct}", "");
            hashMap.put(Abr, ff);
        }
        Assert.assertTrue(hashMap.size()>20);

    }

    @Test
    public void Test_Particular_Currency_is_present(){
        Response response = given()
                // .header("Host","https://cdn.jsdelivr.net")
                .header("User-Agent", "rest-assured/5.3.0")
                .header("Accept", "*/*")
                // .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json");

        String res = response.asString();
        res = res.substring(1, res.length() - 1);
        String strArray[] = res.split(",");

        System.out.println("String : " + res);

        for (int i = 0; i < strArray.length; i++) {
            System.out.print(strArray[i]);
        }
        Map<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < strArray.length; i++) {
            String parts[] = strArray[i].split(":");
            String Abr = parts[0].trim();
            String ff = parts[1].trim();
            Abr = Abr.replaceAll("\\p{Punct}", "");
            ff = ff.replaceAll("\\p{Punct}", "");
            hashMap.put(Abr, ff);
        }
        Assert.assertTrue(hashMap.containsValue("United States dollar"));
        Assert.assertFalse(hashMap.containsValue("British Pound"));


    }
    @Test
    public void test_to_extract_abbreviation(){
        Response response = given()
                // .header("Host","https://cdn.jsdelivr.net")
                .header("User-Agent", "rest-assured/5.3.0")
                .header("Accept", "*/*")
                // .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json");

        String res = response.asString();
        res = res.substring(1, res.length() - 1);
        String strArray[] = res.split(",");

        System.out.println("String : " + res);

        for (int i = 0; i < strArray.length; i++) {
            System.out.print(strArray[i]);
        }
        Map<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < strArray.length; i++) {
            String parts[] = strArray[i].split(":");
            String Abr = parts[0].trim();
            String ff = parts[1].trim();
            hashMap.put(Abr, ff);
        }
        ArrayList<String> CAbrList = new ArrayList<String>();
        Set keys = hashMap.keySet();
        Iterator i = keys.iterator();
        while(i.hasNext()) {
            CAbrList.add(i.next().toString());
        }
        System.out.println(CAbrList);
        System.out.println("The number of elements returned by Api is  " + CAbrList.size());
        int value =0;
        while (CAbrList.size() > value) {
           String currency = CAbrList.get(value).replaceAll("\\p{Punct}", "");
            Response response2 = given()
                    // .header("Host","https://cdn.jsdelivr.net")
                    .header("User-Agent", "rest-assured/5.3.0")
                    .header("Accept", "*/*")
                    // .header("Accept-Encoding","gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+currency+".json");
           value++ ;
           Assert.assertEquals(response2.getStatusCode(),200);
        }
    }


  @Test
   public void Task(){
      Response response = given()
              // .header("Host","https://cdn.jsdelivr.net")
              .header("User-Agent", "rest-assured/5.3.0")
              .header("Accept", "*/*")
              // .header("Accept-Encoding","gzip, deflate, br")
              .header("Connection", "keep-alive")
              .contentType(ContentType.JSON)
              .accept(ContentType.JSON)
              .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json");

      Map<String,String> jsonResponse = response.jsonPath().getMap("$");

      System.out.println(jsonResponse);
      List<String> Abr  = new ArrayList<>();
      Set keys =  jsonResponse.keySet();
      Iterator i = keys.iterator();
      while (i.hasNext()) {
          Abr.add(i.next().toString());
      }
      System.out.println(Abr);
      Map<String,List<Float>> CAP = new HashMap<>();
      List<Float> rates = new ArrayList<>();
      int value =0;
      while(Abr.size()>value){
          String CA = Abr.get(value);
          Response response2 = given()
                  // .header("Host","https://cdn.jsdelivr.net")
                  .header("User-Agent", "rest-assured/5.3.0")
                  .header("Accept", "*/*")
                  // .header("Accept-Encoding","gzip, deflate, br")
                  .header("Connection", "keep-alive")
                  .contentType(ContentType.JSON)
                  .accept(ContentType.JSON)
                  .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+CA+".json");

          Map<String,Float> CER = response2.jsonPath().getMap(CA);

          Collection getValues = CER.values();
          i = getValues.iterator();
          while (i.hasNext()) {
              rates.add(Float.parseFloat(i.next().toString()));
          }
          CAP.put(CA,rates);
         value++ ;
      }
       int k=0;
      while(Abr.size()>k){
          String key = Abr.get(k);
          if(k+1==Abr.size()){
              break;
          }
          String key1 = Abr.get(k+1);
          System.out.println(k);
          if(CAP.get(key).size()==CAP.get(key1).size()){
              k++;
              System.out.println(k);
          }
          else{
              System.out.println("The number of currency that can be exchanged for the currency" +key + "is not equal to currency"+key1);
          }

      }


    }

    @Test
    public void test(){
        Response response2 = given()
                // .header("Host","https://cdn.jsdelivr.net")
                .header("User-Agent", "rest-assured/5.3.0")
                .header("Accept", "*/*")
                // .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd.json");

        Map<String,Float> CER = response2.jsonPath().getMap("usd");
        List<Float> rates = new ArrayList<>();

        Collection getValues = CER.values();
        Iterator j = getValues.iterator();
        while (j.hasNext()) {
            System.out.println(j.next());
            rates.add(Float.parseFloat(j.next().toString()));
        }
           System.out.println(rates);
        }
    }






