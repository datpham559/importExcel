package importExcel.helper;

import importExcel.entity.DmMerchandise;
import importExcel.entity.Supplier;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static importExcel.helper.ScaleConfig.scales;


public class DmMerchandiseExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã VTHH", "Tên VTHH", "Tính chất", "ĐVT chính", "Loại VTHH", "Thời hạn bảo hành", "Số lượng tồn tối thiểu",
            "Xuất xứ hàng hóa", "Theo dõi tồn kho theo mã quy cách", "Kho", "TK Kho", "TK chi phí", "TK doanh thu", "Thuế suất thuế GTGT",
            "Thuế xuất thuế NK", "Thuế xuất thuế XK", "Tỷ lệ CKBH(%)", "Tỷ lệ CKMH(%)", "Nhóm HHDV chịu thuế TTĐB", "Nhóm ngành nghề tính thuế GTGT",
            "Giá bán cố định", "Giá bán 1", "Giá bán 2", "Giá bán 3", "Số lượng từ", "Số lượng đến", "Loại chiết khấu", "%hoặc số tiền CK", "Loại tiền",
            "Đơn vị tính", "Đơn giá mua", "ĐVT chuyển đổi", "Tỷ lệ chuyển đổi", "Phép tính", "Giá bán cố định theo ĐVT chuyển đổi", "Giá bán 1 theo ĐVT chuyển đổi",
            "Giá bán 2 theo ĐVT chuyển đổi", "Giá bán 3 theo ĐVT chuyển đổi", "Mã VTHH lắp ráp/ tháo dỡ", "Đơn vị tính VTHH lắp ráp/ tháo dỡ", "Số lượng VTHH lắp ráp/ tháo dỡ",
            "Đơn giá VTHH lắp ráp/ tháo dỡ", "Thành tiền VTHH lắp ráp/ tháo dỡ", "Tên mã quy cách", "Mô tả mã quy cách"};

    static String SHEET = "Vật tư hàng hóa";

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

            if (sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")) {
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

    public static ByteArrayInputStream dmMerchandiseToExcel(List<DmMerchandise> dmMerchandises) {
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
                sheet.setColumnWidth(col, 20 * 400);

            }

            int rowIdx = 1;
            for (DmMerchandise dmMerchandise : dmMerchandises) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(dmMerchandise.getCode());
                row.createCell(1).setCellValue(dmMerchandise.getName());
                row.createCell(2).setCellValue(dmMerchandise.getNature());
                row.createCell(3).setCellValue(dmMerchandise.getDvt());
                row.createCell(4).setCellValue(dmMerchandise.getGroup());
                row.createCell(5).setCellValue(dmMerchandise.getWarrantyPeriod());
                row.createCell(6).setCellValue(dmMerchandise.getQuantityInventory());
                row.createCell(7).setCellValue(dmMerchandise.getSource());
                if (dmMerchandise.getFollow() == 0) {
                    row.createCell(8).setCellValue("Không");
                } else {
                    row.createCell(8).setCellValue("Có");
                }
                row.createCell(9).setCellValue(dmMerchandise.getImplicitly());
                row.createCell(10).setCellValue(dmMerchandise.getWarehouseAccount());
                row.createCell(11).setCellValue(dmMerchandise.getExpenseAccount());
                row.createCell(12).setCellValue(dmMerchandise.getIncomeAccount());
                row.createCell(13).setCellValue(dmMerchandise.getTaxRateGtgt());
                row.createCell(14).setCellValue(dmMerchandise.getTaxRateNk());
                row.createCell(15).setCellValue(dmMerchandise.getTaxRateXk());
                row.createCell(16).setCellValue("");
                row.createCell(17).setCellValue(dmMerchandise.getRate());
                row.createCell(18).setCellValue(dmMerchandise.getGroupTaxable());
                row.createCell(19).setCellValue("");
                row.createCell(20).setCellValue(dmMerchandise.getFixedUnitPrice());
                row.createCell(21).setCellValue(dmMerchandise.getUnitPriceSell1());
                row.createCell(22).setCellValue(dmMerchandise.getUnitPriceSell2());
                row.createCell(23).setCellValue(dmMerchandise.getUnitPriceSell3());
                row.createCell(24).setCellValue(dmMerchandise.getAmountFrom());
                row.createCell(25).setCellValue(dmMerchandise.getToAmount());
                row.createCell(26).setCellValue("");
                row.createCell(27).setCellValue("");
                row.createCell(28).setCellValue("VND");
                row.createCell(29).setCellValue("");
                row.createCell(30).setCellValue(dmMerchandise.getFixedPurchasePrice());
                row.createCell(31).setCellValue(dmMerchandise.getConversionUnit());
                row.createCell(32).setCellValue(dmMerchandise.getPrimaryUnitConversionRate());
                row.createCell(33).setCellValue(dmMerchandise.getCalculation());
                row.createCell(34).setCellValue(dmMerchandise.getFixedUnitPrice1());
                row.createCell(35).setCellValue(dmMerchandise.getUnitPriceSell_1());
                row.createCell(36).setCellValue(dmMerchandise.getUnitPriceSell_2());
                row.createCell(37).setCellValue(dmMerchandise.getUnitPriceSell_3());
                row.createCell(38).setCellValue("");
                row.createCell(39).setCellValue("");
                row.createCell(40).setCellValue("");
                row.createCell(41).setCellValue("");
                row.createCell(42).setCellValue("");
                row.createCell(43).setCellValue(dmMerchandise.getSpecificationCode());
                row.createCell(44).setCellValue(dmMerchandise.getSpecificationCode());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }
}
