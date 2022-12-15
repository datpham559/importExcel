package importExcel.helper;

import importExcel.entity.Supplier;
import importExcel.entity.SymmetricalAccount;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static importExcel.helper.ScaleConfig.scales;


public class SymmetricalAccountExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Số tài khoản", "Tên tài khoản", "Dư nợ", "Dư có"};
    static String SHEET = "Số dư đầu kỳ";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<SymmetricalAccount> excelToSymmetricalAccount(InputStream is) {
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
            List<SymmetricalAccount> symmetricalAccounts = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber < 11 || (rowNumber > 33 && rowNumber < 42)) {
                    rowNumber++;
                    continue;
                }

                if (currentRow.getCell(0).getStringCellValue().contains("Cộng"))
                    break;

                Iterator<Cell> cellsInRow = currentRow.iterator();

                SymmetricalAccount symmetricalAccount = new SymmetricalAccount();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            symmetricalAccount.setAccountNumber(currentCell.getStringCellValue());
                            break;

                        case 1:
                            symmetricalAccount.setAccountName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            symmetricalAccount.setFirstDebt(currentCell.getNumericCellValue());
                            break;

                        case 3:
                            symmetricalAccount.setFisrtYes(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            symmetricalAccount.setDebtArises(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            symmetricalAccount.setArisesYes(currentCell.getNumericCellValue());
                            break;
                        case 6:
                            symmetricalAccount.setAccumulatedDebt(currentCell.getNumericCellValue());
                            break;
                        case 7:
                            symmetricalAccount.setAccumulatedYes(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            symmetricalAccount.setLastDebt(currentCell.getNumericCellValue());
                            break;
                        case 9:
                            symmetricalAccount.setLastYes(currentCell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                    if (cellIdx == 10) {
                        break;
                    }
                }

                symmetricalAccounts.add(symmetricalAccount);
                rowNumber++;
            }

            workbook.close();

            return symmetricalAccounts;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream symmetricalToExcel(List<SymmetricalAccount> symmetricalAccounts) {
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
            for (SymmetricalAccount symmetricalAccount : symmetricalAccounts) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(symmetricalAccount.getAccountNumber());
                row.createCell(1).setCellValue(symmetricalAccount.getAccountName());

                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));
                String convert2 = formatter.format(symmetricalAccount.getFirstDebt());
                Cell cell = row.createCell(2);
                cell.setCellValue(convert2.substring(0,convert2.length()-1));

                NumberFormat formatter3 = NumberFormat.getCurrencyInstance(new Locale("de", "DE"));
                String convert3 = formatter3.format(symmetricalAccount.getFisrtYes());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(convert3.substring(0, convert3.length()-1));

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }
}
