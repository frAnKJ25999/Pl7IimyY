// 代码生成时间: 2025-09-24 12:23:57
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
public class WebScraperService {

    private final RestTemplate restTemplate;

    public WebScraperService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchContent(@RequestParam String url) {
        try {
            String encodedUrl = URLEncoder.encode(url, "UTF-8");
            String encodedUrlBase64 = Base64.getEncoder().encodeToString(encodedUrl.getBytes("UTF-8"));
            return ResponseEntity.ok().body("<html>" + encodeHtml(url) + "</html>");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error encoding URL");
        }
    }

    private String encodeHtml(String url) {
        String escapedUrl = url.replace("\&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace(""", "&quot;").replace("'", "&#x27;");
        return escapedUrl;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebScraperService.class, args);
    }
}
