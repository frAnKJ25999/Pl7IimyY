// 代码生成时间: 2025-09-23 11:21:58
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SpringBootApplication
public class ExcelGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelGeneratorApplication.class, args);
    }

    @RestController
    @RequestMapping("/api/excel")
    public static class ExcelApiController extends ResponseEntityExceptionHandler {

        private static final String EXCEL_FILE_NAME = "ExampleExcel.xlsx";

        @GetMapping("/download")
        public ResponseEntity<byte[]> downloadExcelFile() {
            try {
                Workbook workbook = new XSSFWorkbook();
                // Add your logic to create the Excel workbook here
                // ...

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                workbook.write(bos);
                byte[] data = bos.toByteArray();
                workbook.close();
                bos.close();

                return ResponseEntity.ok()
                        .header("Content-Disposition", "attachment; filename="" + EXCEL_FILE_NAME + """)
                        .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                        .body(data);
            } catch (IOException e) {
                throw new RuntimeException("Error while creating Excel file", e);
            }
        }

        @Override
        protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex,
                HttpHeaders headers, HttpStatus status,
                WebRequest request) {
            return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
