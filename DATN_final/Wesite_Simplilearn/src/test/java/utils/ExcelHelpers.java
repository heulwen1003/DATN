package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

public class ExcelHelpers {
    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;

    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<String, Integer>();
    
  //1. WORKING WITH EXCEL FILE
    //Cau truc 1 file Excel bao gom: WorkBook, WrokSheet, Row,
    //Column, Cell
    //Thu vien Apache POI cung cap cac class de lam viec voi cac
    //thanh phan nay cua Excel
    
    //Phuong thuc tao file Excel neu chua co
    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }

            fis = new FileInputStream(ExcelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }

            this.excelFilePath = ExcelPath; 

            //adding all the column header names to the map 'columns'
            sh.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
  
    //Lay du lieu trong mot o su dung SO COT, SO HANG
    public String getCellData(int rownum, int colnum) throws Exception{
        try{
            cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()){
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        CellData = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        CellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
                case ERROR:
                	CellData=String.valueOf(cell.getErrorCellValue());
                	break;
                case FORMULA:
                	CellData=String.valueOf(cell.getCellFormula());
                	break; 
                default: 
                	break;
            }
            return CellData;
        }catch (Exception e){
            return"";
        }
    }
    
  //Lay du lieu trong mot o su dung TEN COT, SO HANG
    public String getCellData(int rownum,String columnName) throws Exception {
        return getCellData(rownum, columns.get(columnName));
    }

  //Ghi du lieu vao mot o  
    public void setCellData(String text, int rownum, int colnum) throws Exception {
        try{
            row  = sh.getRow(rownum);
            if(row ==null)
            {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }
}
