package importExcel.helper;

import importExcel.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
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

    public static List<Payable> excelToPayable(InputStream is){
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

    public static List<Inventory> excelToInventory(InputStream is){
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

    public static List<FixedProduct> excelToFixedProduct(InputStream is){
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
                            fixedProduct.setAccount(cell.getStringCellValue());
                            break;
                        case 16:
                            fixedProduct.setFixedAssestsAccount(cell.getStringCellValue());
                            break;
                        case 17:
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

    public static List<Stock> excelToStock(InputStream is){
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
                        && current.getCell(0).getStringCellValue().contains("Tên kho")){
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
}
