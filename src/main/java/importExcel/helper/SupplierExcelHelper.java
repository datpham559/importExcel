package importExcel.helper;

import importExcel.entity.Supplier;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SupplierExcelHelper {
    public static String TYPE1 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = {"Mã khách hàng", "Tên khách hàng", "Nhóm KH, NCC", "Mã số thuế", "Điện thoại", "Ngừng theo dõi"};

    public static boolean hasExcelFormat(MultipartFile file) {

        if (TYPE1.equals(file.getContentType()) || TYPE2.equals(file.getContentType())) {
            return true;
        }
        return false;
    }

    public static List<Supplier> excelToSupplier(InputStream is) {
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
            List<Supplier> suppliers = new ArrayList<>();


            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Supplier supplier = new Supplier();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            supplier.setSupplierCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            supplier.setSupplierName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            supplier.setAddress(currentCell.getStringCellValue());
                            break;

                        case 3:
                            supplier.setGroup(currentCell.getStringCellValue());
                            break;
                        case 4:
                            supplier.setTaxCode(currentCell.getStringCellValue());
                            break;
                        case 5:
                            supplier.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 6:
                            supplier.setUnfollow(currentCell.getBooleanCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                suppliers.add(supplier);
            }

            workbook.close();

            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
