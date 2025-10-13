// 代码生成时间: 2025-10-13 18:25:08
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class GameResourceService {

    // 模拟数据库存储游戏资源
    private List<String> resources = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(GameResourceService.class, args);
    }

    // 获取所有游戏资源
    @GetMapping("/resources")
    public ResponseEntity<List<String>> getAllResources() {
        return ResponseEntity.ok(resources);
    }

    // 根据ID获取游戏资源
    @GetMapping("/resources/{id}")
    public ResponseEntity<String> getResourceById(@PathVariable String id) {
        try {
            int resourceId = Integer.parseInt(id);
            String resource = resources.get(resourceId);
            return ResponseEntity.ok(resource);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 添加游戏资源
    @PostMapping("/resources")
    public ResponseEntity<String> addResource(@RequestBody String resource) {
        resources.add(resource);
        return ResponseEntity.ok(resource);
    }

    // 异常处理
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
    }
}
