import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GPT {

    private static final String API_URL = "https://api.chatanywhere.tech/v1/chat/completions";
    private static final String API_KEY = "sk-DLOf152iP5IaSARAaoMtqMNdkgX7uMz31eYL9VE7uBmyZVjO"; // 替換為你的API密鑰
    private String prompt;
    public String getChatGPTResponse(String prompt) throws Exception {
        String jsonMessages = "[" +
                "{\"role\": \"system\", \"content\": \"幫我把以下文字做100字以內的大鋼\"}," +
                "{\"role\": \"user\", \"content\": \"" + prompt + "\"}" +
                "]";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);
        httpPost.setHeader("Content-Type", "application/json");

        StringEntity requestEntity = new StringEntity("{\"messages\": " + jsonMessages + ", \"model\": \"gpt-3.5-turbo\"}", "UTF-8");
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        if (responseEntity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonResponse = new JSONObject(result.toString());
            if (jsonResponse.has("error")) {
                JSONObject error = jsonResponse.getJSONObject("error");
                String errorMessage = error.getString("message");
                throw new Exception("API Error: " + errorMessage);
            } else if (jsonResponse.has("choices")) {
                JSONArray choices = jsonResponse.getJSONArray("choices");
                String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
                return content;
            } else {
                throw new Exception("choices not found in the response.");
            }
        }

        return null;
    }
}
