
package com.mwp.core.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * <p>
 * <b>WorkbookUtils </b>is a helper of Microsoft Excel,it's based on POI project
 * </p>
 * @author rainsoft
 * @version $Revision: 1.1 $ $Date: 2010/09/09 09:08:15 $
 */
public class WorkbookUtils
{
	public static void main(String[] args) {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(FileUtils.openInputStream(new File("e:/TEST_MEETING.xlsx")));
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(WorkbookUtils.getStringCellValue(sheet, 0, 0));
			System.out.println("ok");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private WorkbookUtils()
    {
    }

    public static HSSFWorkbook newWorkbook(boolean createSheet)
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        if (createSheet)
        {
            wb.createSheet();
        }
        return wb;
    }

    /**
     * Open an excel file by real fileName
     * @param fileName
     * @return HSSFWorkbook
     * @throws IOException
     * @throws InvalidFormatException 
     * @throws ExcelException
     */
    public static Workbook openWorkbook(String fileName) throws IOException, InvalidFormatException
    {
        InputStream in = null;
        Workbook wb = null;
        try
        {
            in = new FileInputStream(fileName);
            wb = WorkbookFactory.create(in);
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
        return wb;
    }

    /**
     * Open an excel from InputStream
     * @param in
     * @return HSSFWorkbook
     * @throws IOException
     * @throws InvalidFormatException 
     * @throws ExcelException
     */
    public static Workbook openWorkbook(InputStream in) throws  InvalidFormatException, IOException
    {
        return WorkbookFactory.create(in);
    }

    /**
     * Save the Excel to OutputStream
     * @param wb
     *        HSSFWorkbook
     * @param out
     *        OutputStream
     * @throws IOException
     * @throws ExcelException
     */
    public static void saveWorkbook(HSSFWorkbook wb, OutputStream out) throws IOException
    {
        wb.write(out);
    }

    public static void saveWorkbook(HSSFWorkbook wb, String fileName) throws IOException
    {
        FileOutputStream output = null;
        try
        {
            output = new FileOutputStream(fileName);
            wb.write(output);
        }
        finally
        {
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * Set value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @param value
     *        String
     */
    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, String value)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        cell.setCellValue(new HSSFRichTextString(value));
    }

    public static void setCellStyle(HSSFSheet sheet, int rowNum, int colNum, HSSFCellStyle style)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cell.setCellStyle(style);
    }

    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, String value, HSSFCellStyle style)
    {
        setCellValue(sheet, rowNum, colNum, value);
        setCellStyle(sheet, rowNum, colNum, style);
    }

    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, int value, HSSFCellStyle style)
    {
        setCellValue(sheet, rowNum, colNum, value);
        setCellStyle(sheet, rowNum, colNum, style);
    }

    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, double value, HSSFCellStyle style)
    {
        setCellValue(sheet, rowNum, colNum, value);
        setCellStyle(sheet, rowNum, colNum, style);
    }

    /**
     * get value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @return String
     */
    public static String getStringCellValue(Sheet sheet, int rowNum, int colNum)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        {
            java.math.BigDecimal value = new BigDecimal(cell.getNumericCellValue());
            return String.valueOf(value);
        }
        return cell.getRichStringCellValue().toString();
    }

    /**
     * set value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @param value
     *        double
     */
    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, double value)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        cell.setCellValue(value);
    }

    /**
     * get value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @return double
     */
    public static double getNumericCellValue(HSSFSheet sheet, int rowNum, int colNum)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        return cell.getNumericCellValue();
    }

    /**
     * set value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @param value
     *        Date
     */
    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, Date value)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        cell.setCellValue(value);
    }

    /**
     * get value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @return Date
     */
    public static Date getDateCellValue(HSSFSheet sheet, int rowNum, int colNum)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        return cell.getDateCellValue();
    }

    /**
     * set value of the cell
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @param value
     *        boolean
     */
    public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, boolean value)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        cell.setCellValue(value);
    }

    /**
     * get value of the cell
     * @param sheet
     * @param rowNum
     * @param colNum
     * @return boolean value
     */
    public static boolean getBooleanCellValue(HSSFSheet sheet, int rowNum, int colNum)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        return cell.getBooleanCellValue();
    }

    /**
     * get Row, if not exists, create
     * @param rowCounter
     *        int
     * @param sheet
     *        HSSFSheet
     * @return HSSFRow
     */
    public static Row getRow(int rowCounter, Sheet sheet)
    {
        Row row = sheet.getRow((short) rowCounter);
        if (row == null)
        {
            row = sheet.createRow((short) rowCounter);
        }
        return row;
    }

    /**
     * get Cell, if not exists, create
     * @param row
     *        HSSFRow
     * @param column
     *        int
     * @return HSSFCell
     */
    public static Cell getCell(Row row, int column)
    {
        Cell cell = row.getCell(column);

        if (cell == null)
        {
            cell = row.createCell(column);
        }
        return cell;
    }

    /**
     * get cell, if not exists, create
     * @param sheet
     *        HSSFSheet
     * @param rowNum
     *        int
     * @param colNum
     *        int
     * @return HSSFCell
     */
    public static Cell getCell(Sheet sheet, int rowNum, int colNum)
    {
        Row row = getRow(rowNum, sheet);
        Cell cell = getCell(row, colNum);
        return cell;
    }

    public static HSSFCellStyle createBorder(HSSFWorkbook workbook)
    {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        return cellStyle;
    }

}
