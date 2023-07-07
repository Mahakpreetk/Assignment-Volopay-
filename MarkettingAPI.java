import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class MarkettingAPI {

    // Sample dataset
    private static final Sale[] dataset;

    static {
        dataset = loadDatasetFromExcel("data.xlsx");
    }

    public static void main(String[] args) {
        SpringApplication.run(MarkettingAPI.class, args);
    }

    @RestController
    public static class TotalItemsController {

        @GetMapping("/api/total_items")
        public int getTotalItemsSold(
                @RequestParam("start_date") String startDate,
                @RequestParam("end_date") String endDate,
                @RequestParam("department") String department) {

            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            int totalItemsSold = 0;
            for (Sale sale : dataset) {
                LocalDate saleDate = sale.getDate();
                if (saleDate.isAfter(start) && saleDate.isBefore(end) && sale.getDepartment().equals(department)) {
                    totalItemsSold += sale.getQuantity();
                }
            }

            return totalItemsSold;
        }
    }

    public static class Sale {
        private LocalDate date;
        private String department;
        private int quantity;

        public Sale(LocalDate date, String department, int quantity) {
            this.date = date;
            this.department = department;
            this.quantity = quantity;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getDepartment() {
            return department;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    private static Sale[] loadDatasetFromExcel(String filePath) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            int numRows = sheet.getLastRowNum();
            Sale[] dataset = new Sale[numRows];

            for (int i = 1; i <= numRows; i++) {
                Row row = sheet.getRow(i);
                LocalDate date = LocalDate.parse(row.getCell(0).getStringCellValue());
                String department = row.getCell(1).getStringCellValue();
                int quantity = (int) row.getCell(2).getNumericCellValue();

                dataset[i - 1] = new Sale(date, department, quantity);
            }

            return dataset;
        } catch (IOException e) {
            e.printStackTrace();
            return new Sale[0]; // Return an empty array in case of an error
        }
    }
}
