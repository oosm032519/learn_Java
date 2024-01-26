package spotify;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

public class SpotifyAPITemplate {

    // Spotify for Developerのアカウントで取得したClient IDとClient Secret
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";

    // Spotify Web APIのエンドポイント
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String GENRES_URL = "https://api.spotify.com/v1/recommendations/available-genre-seeds";

    public static void main(String[] args) {
        try {
            // Client IDとClient SecretをBase64エンコードしてヘッダーにセット
            String encoded = Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
            HttpRequest tokenRequest = HttpRequest.newBuilder()
                    .uri(URI.create(TOKEN_URL))
                    .header("Authorization", "Basic " + encoded)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                    .build();

            // トークンリクエストを送信してレスポンスを受け取る
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());

            // レスポンスをJSONオブジェクトに変換してアクセストークンを取得
            JSONObject tokenJson = new JSONObject(tokenResponse.body());
            String accessToken = tokenJson.getString("access_token");

            // アクセストークンをヘッダーにセットしてジャンルリクエストを送信
            HttpRequest genresRequest = HttpRequest.newBuilder()
                    .uri(URI.create(GENRES_URL))
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();

            // ジャンルリクエストを送信してレスポンスを受け取る
            HttpResponse<String> genresResponse = client.send(genresRequest, HttpResponse.BodyHandlers.ofString());

            // レスポンスをJSONオブジェクトに変換してジャンル一覧を取得
            JSONObject genresJson = new JSONObject(genresResponse.body());
            JSONArray genresArray = genresJson.getJSONArray("genres");

            // ジャンル一覧を出力
            System.out.println("Spotifyのジャンル一覧:");
            for (int i = 0; i < genresArray.length(); i++) {
                System.out.println(genresArray.getString(i));
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
