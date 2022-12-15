package importExcel.helper;

import importExcel.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static importExcel.helper.ScaleConfig.*;


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


    public static List<Receivable> excelToReceivable(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Receivable> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Receivable receivable = new Receivable();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            receivable.setCustomerCode(cell.getStringCellValue());
                            break;
                        case 1:
                            receivable.setCustomerName(cell.getStringCellValue());
                            break;
                        case 2:
                            receivable.setAccountDebit(cell.getStringCellValue());
                            break;
                        case 3:
                            receivable.setAobDebit(cell.getNumericCellValue());
                            break;
                        case 4:
                            receivable.setAobCredit(cell.getNumericCellValue());
                            break;
                        case 5:
                            receivable.setAriseDebit(cell.getNumericCellValue());
                            break;
                        case 6:
                            receivable.setAriseCredit(cell.getNumericCellValue());
                            break;
                        case 7:
                            receivable.setCloseDebit(cell.getNumericCellValue());
                            break;
                        case 8:
                            receivable.setCloseCredit(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(receivable);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Payable> excelToPayable(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Payable> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Payable payable = new Payable();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            payable.setSupplierCode(cell.getStringCellValue());
                            break;
                        case 1:
                            payable.setSupplierName(cell.getStringCellValue());
                            break;
                        case 2:
                            payable.setAccountDebit(cell.getStringCellValue());
                            break;
                        case 3:
                            payable.setAobDebit(cell.getNumericCellValue());
                            break;
                        case 4:
                            payable.setAobCredit(cell.getNumericCellValue());
                            break;
                        case 5:
                            payable.setAriseDebit(cell.getNumericCellValue());
                            break;
                        case 6:
                            payable.setAriseCredit(cell.getNumericCellValue());
                            break;
                        case 7:
                            payable.setCloseDebit(cell.getNumericCellValue());
                            break;
                        case 8:
                            payable.setCloseCredit(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(payable);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Inventory> excelToInventory(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Inventory> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Inventory inventory = new Inventory();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            inventory.setToolSupplyCode(cell.getStringCellValue());
                            break;
                        case 1:
                            inventory.setToolSupplyName(cell.getStringCellValue());
                            break;
                        case 2:
                            inventory.setToolSupplyType(cell.getStringCellValue());
                            break;
                        case 3:
                            inventory.setIncreaseReason(cell.getStringCellValue());
                            break;
                        case 4:
                            inventory.setIncreaseDate(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            inventory.setAccountVoucherNumber(cell.getStringCellValue());
                            break;
                        case 6:
                            inventory.setInstrustmentNumber((int) cell.getNumericCellValue());
                            break;
                        case 7:
                            inventory.setRemainInstrustmentNumber((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            inventory.setUnit(cell.getStringCellValue());
                            break;
                        case 9:
                            inventory.setIncreaseNumber(cell.getNumericCellValue());
                            break;
                        case 10:
                            inventory.setDecreaseAccumulateNumber(cell.getNumericCellValue());
                            break;
                        case 11:
                            inventory.setRemainNumber(cell.getNumericCellValue());
                            break;
                        case 12:
                            inventory.setToolSupplyvalue(cell.getNumericCellValue());
                            break;
                        case 13:
                            inventory.setPbRatio(cell.getNumericCellValue());
                            break;
                        case 14:
                            inventory.setPbInSeason(cell.getNumericCellValue());
                            break;
                        case 15:
                            inventory.setPbAccumulate(cell.getNumericCellValue());
                            break;
                        case 16:
                            inventory.setRemainValue(cell.getNumericCellValue());
                            break;
                        case 17:
                            inventory.setWaittingAccount(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(inventory);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<FixedProduct> excelToFixedProduct(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<FixedProduct> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 3) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                FixedProduct fixedProduct = new FixedProduct();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            fixedProduct.setFixedProductCode(cell.getStringCellValue());
                            break;
                        case 1:
                            fixedProduct.setFixedProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            fixedProduct.setFixedProductType(cell.getStringCellValue());
                            break;
                        case 3:
                            fixedProduct.setCompany(cell.getStringCellValue());
                            break;
                        case 4:
                            fixedProduct.setIncreaseDate(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 5:
                            fixedProduct.setAccountVoucherNumber(cell.getStringCellValue());
                            break;
                        case 6:
                            fixedProduct.setStartDate(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            break;
                        case 7:
                            fixedProduct.setUsingTimeNumber(cell.getNumericCellValue());
                            break;
                        case 8:
                            fixedProduct.setRemainTimeNumber(cell.getNumericCellValue());
                            break;
                        case 9:
                            fixedProduct.setPrice(cell.getNumericCellValue());
                            break;
                        case 10:
                            fixedProduct.setDepriation(cell.getNumericCellValue());
                            break;
                        case 11:
                            fixedProduct.setDepriationInSeason(cell.getNumericCellValue());
                            break;
                        case 12:
                            fixedProduct.setDepriationAccumulate(cell.getNumericCellValue());
                            break;
                        case 13:
                            fixedProduct.setRemainNumber(cell.getNumericCellValue());
                            break;
                        case 14:
                            fixedProduct.setDepriationInMonth(cell.getNumericCellValue());
                            break;
                        case 15:
                            fixedProduct.setFixedAssestsAccount(cell.getStringCellValue());
                            break;
                        case 16:
                            fixedProduct.setDepriationAccount(cell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(fixedProduct);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Stock> excelToStock(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getRow(sheet.getLastRowNum()).getCell(1).getStringCellValue().contains("Số dòng")) {
                sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
            }
            Iterator<Row> rows = sheet.iterator();
            List<Stock> list = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row current = rows.next();
                if (rowNumber < 4) {
                    rowNumber++;
                    continue;
                }
                if (current.getFirstCellNum() == 0
                        && current.getCell(0).getStringCellValue().contains("Tên kho")) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = current.cellIterator();
                Stock stock = new Stock();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            stock.setStockName(cell.getStringCellValue());
                            break;
                        case 1:
                            stock.setStockCode(cell.getStringCellValue());
                            break;
                        case 2:
                            stock.setItemName(cell.getStringCellValue());
                            break;
                        case 3:
                            stock.setUnit(cell.getStringCellValue());
                            break;
                        case 4:
                            stock.setFirstSeasonNumber(cell.getNumericCellValue());
                            break;
                        case 5:
                            stock.setFirstSeasonValue(cell.getNumericCellValue());
                            break;
                        case 6:
                            stock.setGoodReceiptNumber(cell.getNumericCellValue());
                            break;
                        case 7:
                            stock.setGoodReceiptValue(cell.getNumericCellValue());
                            break;
                        case 8:
                            stock.setGoodDeliveryNumber(cell.getNumericCellValue());
                            break;
                        case 9:
                            stock.setGoodDeliveryValue(cell.getNumericCellValue());
                            break;
                        case 10:
                            stock.setLastSeasonNumber(cell.getNumericCellValue());
                            break;
                        case 11:
                            stock.setLastSeasonValue(cell.getNumericCellValue());
                            break;
                        case 12:
                            stock.setAverage(cell.getNumericCellValue());
                            break;
                    }
                    cellIndex++;
                }
                list.add(stock);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static ByteArrayInputStream ccdcToExcel(List<Inventory> inventories) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("CCDC");
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

            for (int col = 0; col < header_CCDC.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header_CCDC.get(col));
                cell.setCellStyle(cellStyleHeader);
                sheet.setColumnWidth(col, 20 * 250);
            }
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
            int rowIdx = 1;
            for (Inventory inventory : inventories) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue("Sổ tài chính");
                row.createCell(1).setCellValue(inventory.getToolSupplyCode());
                row.createCell(2).setCellValue(inventory.getToolSupplyName());
                Cell cell = row.createCell(3);
                cell.setCellValue(inventory.getIncreaseDate());
                cell.setCellStyle(cellStyle);
                row.createCell(4).setCellValue("");
                row.createCell(5).setCellValue("");
                row.createCell(6).setCellValue("");
                row.createCell(7).setCellValue("");
                row.createCell(8).setCellValue(inventory.getInstrustmentNumber());
                row.createCell(9).setCellValue(inventory.getRemainInstrustmentNumber());
                cell = row.createCell(10);
                cell.setCellValue(inventory.getPbAccumulate());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(11);
                cell.setCellValue(inventory.getRemainValue());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(12);
                cell.setCellValue(inventory.getPbInSeason());
                cell.setCellStyle(cellStyleNumber);
                row.createCell(13).setCellValue(inventory.getWaittingAccount());
                row.createCell(14).setCellValue("");
                row.createCell(15).setCellValue("");
                row.createCell(16).setCellValue("");
                row.createCell(17).setCellValue("");
                row.createCell(18).setCellValue("");
                row.createCell(19).setCellValue("");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tscdToExcel(List<FixedProduct> fixedProducts){
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("TSCD");
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
            for (int col = 0; col < header_TSCD.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header_TSCD.get(col));
                cell.setCellStyle(cellStyleHeader);
                sheet.setColumnWidth(col, 20 * 250);
            }
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            CellStyle cellStyleNumber = workbook.createCellStyle();
            cellStyleNumber.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
            int rowIdx = 1;
            for (FixedProduct fixedProduct : fixedProducts) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue("Sổ tài chính");
                row.createCell(1).setCellValue(fixedProduct.getFixedProductCode());
                row.createCell(2).setCellValue(fixedProduct.getFixedProductName());
                row.createCell(3).setCellValue("");
                row.createCell(4).setCellValue(fixedProduct.getFixedProductType());
                Cell cell = row.createCell(5);
                cell.setCellValue(fixedProduct.getIncreaseDate());
                cell.setCellStyle(cellStyle);
                cell = row.createCell(6);
                cell.setCellValue(fixedProduct.getStartDate());
                cell.setCellStyle(cellStyle);
                row.createCell(7).setCellValue(fixedProduct.getUsingTimeNumber());
                row.createCell(8).setCellValue("tháng");
                row.createCell(9).setCellValue(fixedProduct.getRemainTimeNumber());
                row.createCell(10).setCellValue("tháng");
                cell = row.createCell(11);
                cell.setCellValue(fixedProduct.getPrice());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(12);
                cell.setCellValue(fixedProduct.getDepriation());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(13);
                cell.setCellValue(fixedProduct.getDepriationAccumulate());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(14);
                cell.setCellValue(fixedProduct.getRemainNumber());
                cell.setCellStyle(cellStyleNumber);
                cell = row.createCell(15);
                cell.setCellValue(fixedProduct.getDepriationInMonth());
                cell.setCellStyle(cellStyleNumber);
                row.createCell(16).setCellValue(fixedProduct.getFixedAssestsAccount());
                row.createCell(17).setCellValue("");
                row.createCell(18).setCellValue(fixedProduct.getDepriationAccount());
                row.createCell(19).setCellValue("");
                row.createCell(20).setCellValue("");
                row.createCell(21).setCellValue("");
                row.createCell(22).setCellValue("");
                row.createCell(23).setCellValue("");
                row.createCell(24).setCellValue("");
                row.createCell(25).setCellValue("");
                row.createCell(26).setCellValue("");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
