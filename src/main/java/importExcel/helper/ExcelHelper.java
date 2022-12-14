package importExcel.helper;

import importExcel.dto.DataToTable;
import importExcel.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static importExcel.helper.ScaleConfig.scales;


public class ExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Nhóm KH, NCC", "Mã số thuế", "Quy mô", "Loại đối tượng", "Điện thoại", "Ngừng theo dõi"};
    static String SHEET = "Khach_hang";

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

            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }

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

    public static DataToTable excelToTable(InputStream is) {
        try {
            DataToTable dataToTable = new DataToTable();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            List<Accreditative> accreditatives = new ArrayList<>();
            List<DepositCollection> depositCollections = new ArrayList<>();
            List<PaySlip> paySlips = new ArrayList<>();
            List<Receipt> receipts = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

//                Accreditative accreditative = new Accreditative();
//                DepositCollection depositCollection = new DepositCollection();
//                PaySlip paySlip = new PaySlip();
//                Receipt receipt = new Receipt();
                Cell cell1 = currentRow.getCell(1);
                if (cell1.getStringCellValue().equals("Ủy nhiệm chi")) {
                    accreditatives.add(setValue(currentRow));
                }
                if(cell1.getStringCellValue().equals("Phiếu thu")){
                    receipts.add(setValueReceipt(currentRow));
                }

            }
            workbook.close();
            if (accreditatives.size() > 0) {
                dataToTable.setAccreditatives(accreditatives);
            }
            if (receipts.size() > 0) {
                dataToTable.setReceipts(receipts);
            }

            return dataToTable;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static Accreditative setValue(Row currentRow) {
        Accreditative accreditative = new Accreditative();

        Iterator<Cell> cellsInRow = currentRow.iterator();
        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
            Cell currentCell = cellsInRow.next();

            switch (cellIdx) {
                case 0:
                  continue;
                case 1:
                    accreditative.setVoucherType(currentCell.getStringCellValue());
                    break;
                case 2:
                    accreditative.setVoucherNo(currentCell.getStringCellValue());
                    break;
                default:
                    break;
            }
            cellIdx++;
        }
        return accreditative;
    }
    public static Receipt setValueReceipt(Row currentRow) {
        Receipt receipt = new Receipt();

        Iterator<Cell> cellsInRow = currentRow.iterator();
        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
            Cell currentCell = cellsInRow.next();

            switch (cellIdx) {
                case 0:
                    continue;
                case 1:
                    receipt.setVoucherType(currentCell.getStringCellValue());
                    break;
                case 2:
                    receipt.setVoucherNo(currentCell.getStringCellValue());
                    break;
                default:
                    break;
            }
            cellIdx++;
        }
        return receipt;
    }
    public static ByteArrayInputStream customersToExcel(List<Customer> customers) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 400);
            CellStyle cellStyleHeader = workbook.createCellStyle();
            Font fontHeader = workbook.createFont();
            fontHeader.setBold(true);
            cellStyleHeader.setFont(fontHeader);
            cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
            cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyleHeader.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyleHeader.setBorderBottom(BorderStyle.THIN);
            cellStyleHeader.setBorderTop(BorderStyle.THIN);
            cellStyleHeader.setBorderRight(BorderStyle.THIN);
            cellStyleHeader.setBorderLeft(BorderStyle.THIN);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                cell.setCellStyle(cellStyleHeader);
                if (col == 1 || col == 2) {
                    sheet.setColumnWidth(1, 25 * 700);
                    sheet.setColumnWidth(2, 25 * 1000);
                } else {
                    sheet.setColumnWidth(col, 20 * 250);
                }
            }

            int rowIdx = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(customer.getCustomerCode());
                row.createCell(1).setCellValue(customer.getCustomerName());
                row.createCell(2).setCellValue(customer.getAddress());
                row.createCell(3).setCellValue(customer.getCustomerGroup());
                row.createCell(4).setCellValue(customer.getTax());
                boolean checkContainScale = false;
                for (String scale : scales) {
                    if (customer.getCustomerName().toLowerCase().contains(scale.toLowerCase())) {
                        checkContainScale = true;
                        break;
                    }
                }
                if (checkContainScale) {
                    row.createCell(5).setCellValue("Tổ chức");
                } else {
                    row.createCell(5).setCellValue("Cá nhân");
                }
                row.createCell(6).setCellValue("Khách hàng");
                row.createCell(7).setCellValue(customer.getPhoneNumber());
                row.createCell(8).setCellValue(customer.isUnfollow());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
