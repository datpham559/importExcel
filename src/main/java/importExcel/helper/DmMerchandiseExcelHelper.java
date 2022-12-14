package importExcel.helper;

import importExcel.entity.DmMerchandise;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DmMerchandiseExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Nhóm KH, NCC", "Mã số thuế", "Điện thoại", "Ngừng theo dõi"};

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<DmMerchandise> excelToDmMerchandise(InputStream is) {
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
            List<DmMerchandise> dmMerchandises = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                DmMerchandise dmMerchandise = new DmMerchandise();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            dmMerchandise.setCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            dmMerchandise.setName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            dmMerchandise.setNature(currentCell.getStringCellValue());
                            break;

                        case 3:
                            dmMerchandise.setGroup(currentCell.getStringCellValue());
                            break;
                        case 4:
                            dmMerchandise.setDescribe(currentCell.getStringCellValue());
                            break;
                        case 5:
                            dmMerchandise.setExplainBuy(currentCell.getStringCellValue());
                            break;
                        case 6:
                            dmMerchandise.setExplainSell(currentCell.getStringCellValue());
                            break;
                        case 7:
                            dmMerchandise.setMain(currentCell.getStringCellValue());
                            break;
                        case 8:
                            dmMerchandise.setWarrantyPeriod(currentCell.getStringCellValue());
                            break;
                        case 9:
                            dmMerchandise.setQuantityInventory(currentCell.getNumericCellValue());
                            break;
                        case 10:
                            dmMerchandise.setSource(currentCell.getStringCellValue());
                            break;
                        case 11:
                            dmMerchandise.setImplicitly(currentCell.getStringCellValue());
                            break;
                        case 12:
                            dmMerchandise.setWarehouseAccount(currentCell.getStringCellValue());
                            break;
                        case 13:
                            dmMerchandise.setExpenseAccount(currentCell.getStringCellValue());
                            break;
                        case 14:
                            dmMerchandise.setIncomeAccount(currentCell.getStringCellValue());
                            break;
                        case 15:
                            dmMerchandise.setDiscountAccount(currentCell.getStringCellValue());
                            break;
                        case 16:
                            dmMerchandise.setSaleAccount(currentCell.getStringCellValue());
                            break;
                        case 17:
                            dmMerchandise.setReturntAccount(currentCell.getStringCellValue());
                            break;
                        case 18:
                            dmMerchandise.setRate(currentCell.getNumericCellValue());
                            break;
                        case 19:
                            dmMerchandise.setFixedPurchasePrice(currentCell.getNumericCellValue());
                            break;
                        case 20:
                            dmMerchandise.setLatestPurchasePrice(currentCell.getNumericCellValue());
                            break;
                        case 21:
                            dmMerchandise.setUnitPriceSell1(currentCell.getNumericCellValue());
                            break;
                        case 22:
                            dmMerchandise.setUnitPriceSell2(currentCell.getNumericCellValue());
                            break;
                        case 23:
                            dmMerchandise.setUnitPriceSell3(currentCell.getNumericCellValue());
                            break;
                        case 24:
                            dmMerchandise.setFixedUnitPrice(currentCell.getNumericCellValue());
                            break;
                        case 25:
                            dmMerchandise.setUnitPriceAfterTax(currentCell.getNumericCellValue());
                            break;
                        case 26:
                            dmMerchandise.setTaxRateGtgt(currentCell.getStringCellValue());
                            break;
                        case 27:
                            dmMerchandise.setTaxRateNk(currentCell.getNumericCellValue());
                            break;
                        case 28:
                            dmMerchandise.setTaxRateXk(currentCell.getNumericCellValue());
                            break;
                        case 29:
                            dmMerchandise.setGroupTaxable(currentCell.getStringCellValue());
                            break;
                        case 30:
                            dmMerchandise.setUnfollow(currentCell.getNumericCellValue());
                            break;
                        case 31:
                            dmMerchandise.setInventoryNumber(currentCell.getNumericCellValue());
                            break;
                        case 32:
                            dmMerchandise.setCharacteristic(currentCell.getStringCellValue());
                            break;
                        case 33:
                            dmMerchandise.setInventoryValue(currentCell.getNumericCellValue());
                            break;
                        case 34:
                            dmMerchandise.setFollow(currentCell.getNumericCellValue());
                            break;
                        case 35:
                            dmMerchandise.setDiscount(currentCell.getNumericCellValue());
                            break;
                        case 36:
                            dmMerchandise.setAmountFrom(currentCell.getStringCellValue());
                            break;
                        case 37:
                            dmMerchandise.setToAmount(currentCell.getStringCellValue());
                            break;
                        case 38:
                            dmMerchandise.setPercentDiscount(currentCell.getNumericCellValue());
                            break;
                        case 39:
                            dmMerchandise.setDiscountAmount(currentCell.getNumericCellValue());
                            break;
                        case 40:
                            dmMerchandise.setConversionUnit(currentCell.getStringCellValue());
                            break;
                        case 41:
                            dmMerchandise.setPrimaryUnitConversionRate(currentCell.getNumericCellValue());
                            break;
                        case 42:
                            dmMerchandise.setCalculation(currentCell.getStringCellValue());
                            break;
                        case 43:
                            dmMerchandise.setDescribe1(currentCell.getStringCellValue());
                            break;
                        case 44:
                            dmMerchandise.setUnitPriceSell_1(currentCell.getNumericCellValue());
                            break;
                        case 45:
                            dmMerchandise.setUnitPriceSell_2(currentCell.getNumericCellValue());
                            break;
                        case 46:
                            dmMerchandise.setUnitPriceSell_3(currentCell.getNumericCellValue());
                            break;
                        case 47:
                            dmMerchandise.setFixedUnitPrice1(currentCell.getNumericCellValue());
                            break;
                        case 48:
                            dmMerchandise.setMaterialCode(currentCell.getStringCellValue());
                            break;
                        case 49:
                            dmMerchandise.setMaterialName(currentCell.getStringCellValue());
                            break;
                        case 50:
                            dmMerchandise.setDvt(currentCell.getStringCellValue());
                            break;
                        case 51:
                            dmMerchandise.setAmount(currentCell.getStringCellValue());
                            break;
                        case 52:
                            dmMerchandise.setSpecificationCode(currentCell.getStringCellValue());
                            break;
                        case 53:
                            dmMerchandise.setDisplayName(currentCell.getStringCellValue());
                            break;
                        case 54:
                            dmMerchandise.setAllowSame(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                dmMerchandises.add(dmMerchandise);
            }

            workbook.close();

            return dmMerchandises;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
