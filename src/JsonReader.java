import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public JSONObject connectionUrlToJSON(String turn) { //throws Exception
		try {
			URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + turn); // URL 접속 소스
			HttpsURLConnection conn = null;
			HostnameVerifier hnv = new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			}; 
			HttpsURLConnection.setDefaultHostnameVerifier(hnv); 
			conn = (HttpsURLConnection) url.openConnection(); // 실제로 연결하는 코드
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			String iLine = br.readLine();
			JSONParser ps = new JSONParser();
			JSONObject jObj = (JSONObject) ps.parse(iLine); 
			return jObj;
		} catch (Exception e) {
			System.out.println("접속 실패");
			return null;
		}
	}

}
