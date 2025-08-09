import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentFetcher {

    // رابط الـ API بتاع Google Apps Script
    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycbwtOocWxqXKoZTTrow1x5n2w2vXT7h_uwPO8OTABpHjV4Y-ecfbdEx55akwT_J8IBZv/exec";

    // دالة تجيب بيانات الطالب
    public static String fetchStudentData(String username, String password) {
        try {
            // نبني الرابط بالرُقم السري واليوزرنيم
            String fullUrl = BASE_URL + "?username=" + username + "&password=" + password;

            // نعمل اتصال
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // نقرأ الرد
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // نغلق الاتصال
            in.close();
            conn.disconnect();

            // نرجع البيانات
            return content.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // مثال على الاستخدام
    public static void main(String[] args) {
        // تقدر تغير اليوزر والباسورد هنا عشان تجرب
        String username = "ziad123";
        String password = "123456";

        String studentData = fetchStudentData(username, password);
        System.out.println("بيانات الطالب: " + studentData);
    }
}
