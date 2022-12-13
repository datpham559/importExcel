package importExcel.helper;

import importExcel.entity.Customer;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Nhóm KH, NCC", "Mã số thuế", "Điện thoại", "Ngừng theo dõi"};

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<Customer> excelToCustomers(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            if(sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")){
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
//            Row rowDelete = sheet.getRow(sheet.getLastRowNum());
//
//            sheet.removeRow(rowDelete);

            Iterator<Row> rows = sheet.iterator();
            List<Customer> customers = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Customer customer = new Customer();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            customer.setCustomerCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            customer.setCustomerName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            customer.setAddress(currentCell.getStringCellValue());
                            break;

                        case 3:
                            customer.setCustomerGroup(currentCell.getStringCellValue());
                            break;
                        case 4:
                            customer.setTax(currentCell.getStringCellValue());
                            break;
                        case 5:
                            customer.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 6:
                            customer.setUnfollow(currentCell.getBooleanCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                customers.add(customer);
            }

            workbook.close();

            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
