package importExcel.helper;

import importExcel.entity.SymmetricalAccount;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SymmetricalAccountExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Nhóm KH, NCC", "Mã số thuế", "Điện thoại", "Ngừng theo dõi"};

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

            if(sheet.getRow(sheet.getLastRowNum()).getCell(0).getStringCellValue().contains("Số dòng")){
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
                if (rowNumber < 11 ||( rowNumber > 33 && rowNumber < 42)) {
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
                    if(cellIdx == 10){
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
}
