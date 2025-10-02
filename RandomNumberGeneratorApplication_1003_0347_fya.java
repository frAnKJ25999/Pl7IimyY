// 代码生成时间: 2025-10-03 03:47:18
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Random;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SpringBootApplication
@RestController
public class RandomNumberGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomNumberGeneratorApplication.class, args);
    }

    @GetMapping("/random")
    public int generateRandomNumber(
            @RequestParam(defaultValue = "10") @Min(1) int min,
            @RequestParam(defaultValue = "100") @Max(1000) int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return "Error: Invalid range, min and max parameters must be positive integers with min < max";
    }
}
