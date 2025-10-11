// 代码生成时间: 2025-10-12 02:55:22
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DataDeduplicationMergeService {

    @GetMapping("/health")
    public String healthCheck() {
        return "Service is up and running";
    }

    @PostMapping("/deduplication")
    public List<Map<String, Object>> deduplicateData(@RequestBody List<Map<String, Object>> dataList) {
        try {
            // 假设每个Map中的键值对代表一条记录，我们可以根据需要进行去重
            Set<?> uniqueKeys = dataList.stream()
                .map(Map::keySet)
                .flatMap(List::stream)
                .collect(Collectors.toSet());

            return dataList.stream()
                .collect(Collectors.groupingBy(map -> uniqueKeys.containsAll(map.keySet()) ? map : null))
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error during deduplication process", e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DataDeduplicationMergeService.class, args);
    }
}
