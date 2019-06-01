package com.xuecheng.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * 设定开始读取的位置，默认为0
     */
    private int startReadPos = 0;


    /**
     * 读取图片信息
     */
    private List<ExcelReadImage> readImages = new ArrayList<>();

    public int getStartReadPos() {
        return startReadPos;
    }

    public void setStartReadPos(int startReadPos) {
        this.startReadPos = startReadPos;
    }

    public List<ExcelReadImage> getReadImages() {
        return readImages;
    }

    /**
     * 自动根据文件扩展名，调用对应的写入方法
     * @param rowList 导入数据
     * @param xlsPath 写入文件路径
     * @param images 图片信息
     * @throws IOException
     */
    public void writeExcel(List<List<Object>> rowList, String xlsPath, List<ExcelWriteImage> images) throws IOException {
        //扩展名为空时，
        if (xlsPath.equals("")) {
            throw new IOException("文件路径不能为空！");
        }
        //获取扩展名
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);
        try {
            if ("xls".equals(ext)) {                //使用xls方式写入
                writeExcel_xls(rowList, xlsPath, images);
            } else if ("xlsx".equals(ext)) {        //使用xlsx方式写入
                writeExcel_xlsx(rowList, xlsPath, images);
            } else {                                    //依次尝试xls、xlsx方式写入
                try {
                    writeExcel_xls(rowList, xlsPath, images);
                } catch (IOException e1) {
                    try {
                        writeExcel_xlsx(rowList, xlsPath, images);
                    } catch (IOException e2) {
                        throw e2;
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 修改Excel（97-03版，xls格式）
     *
     * @param rowList
     * @param xlsPath
     * @throws IOException
     * @Title: writeExcel_xls
     * @Date : 2014-9-11 下午01:50:38
     */
    public void writeExcel_xls(List<List<Object>> rowList, String xlsPath, List<ExcelWriteImage> images) throws IOException {
        // 判断文件路径是否为空
        if (xlsPath == null || xlsPath.equals("")) {
            throw new IOException("文件路径不能为空");
        }
        // 判断列表是否有数据，如果没有数据，则返回
        if (rowList == null || rowList.size() == 0) {
            return;
        }
        HSSFWorkbook wb = null;
        // 判断文件是否存在
        File file = new File(xlsPath);
        if (file.exists()) {
            file.delete();
        }
        wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        if (images != null && images.size() > 0) {
            writeImage_xls(wb, sheet, images);
        }
        writeExcel(wb, sheet, rowList, xlsPath);
    }

    /**
     * 修改Excel（97-03版，xls格式）
     *
     * @param rowList
     * @throws IOException
     * @Title: writeExcel_xls
     * @Date : 2014-9-11 下午01:50:38
     */
    public void writeExcel_xlsx(List<List<Object>> rowList, String xlsPath, List<ExcelWriteImage> images) throws IOException {
        // 判断文件路径是否为空
        if (xlsPath == null || xlsPath.equals("")) {
            throw new IOException("文件路径不能为空");
        }
        // 判断文件路径是否为空
        if (xlsPath == null || xlsPath.equals("")) {
            throw new IOException("文件路径不能为空");
        }
        // 判断列表是否有数据，如果没有数据，则返回
        if (rowList == null || rowList.size() == 0) {
            return;
        }
        // 读取文档
        XSSFWorkbook wb = null;
        // 判断文件是否存在
        File file = new File(xlsPath);
        if (file.exists()) {
            file.delete();
        }
        wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        if (images != null && images.size() > 0) {
            writeImage_xlsx(wb, sheet, images);
        }
        writeExcel(wb, sheet, rowList, xlsPath);
    }


    public void writeExcel(Workbook wb, Sheet sheet, List<List<Object>> rowList, String xlsPath) {
        int t = 0;//记录最新添加的行数
        for (int i = 0; i < rowList.size(); i++) {
            List list = rowList.get(i);
            if (list == null) {
                continue;
            }
            Row row = sheet.createRow(t++);
            for (int j = 0; j < list.size(); j++) {
                Cell cell = row.createCell(j);
                Object object = list.get(j);
                if (object != null) {
                    cell.setCellValue(object.toString());
                }
            }
        }
        // 统一设定合并单元格
        setMergedRegion(sheet);
        try {
            // 重新将数据写入Excel中
            FileOutputStream outputStream = new FileOutputStream(xlsPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @return
     */
    public void setMergedRegion(Sheet sheet) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            // 获取合并单元格位置
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstRow = ca.getFirstRow();
            if (startReadPos - 1 > firstRow) {
                // 如果第一个合并单元格格式在正式数据的上面，则跳过。
                continue;
            }
            int lastRow = ca.getLastRow();
            int mergeRows = lastRow - firstRow;// 合并的行数
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            // 根据合并的单元格位置和大小，调整所有的数据行格式，
            for (int j = lastRow + 1; j <= sheet.getLastRowNum(); j++) {
                // 设定合并单元格
                sheet.addMergedRegion(new CellRangeAddress(j, j + mergeRows, firstColumn, lastColumn));
                j = j + mergeRows;// 跳过已合并的行
            }
        }
    }


    /***
     * 读取单元格的值
     *
     * @Title: getCellValue
     * @Date : 2014-9-11 上午10:52:07
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                result = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                result = cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                result = cell.getBooleanCellValue();
            } else if (cell.getCellType() == CellType.FORMULA) {
                result = cell.getCellFormula();
            } else if (cell.getCellType() == CellType.ERROR) {
                result = cell.getErrorCellValue();
            }
        }
        return result.toString();
    }

    public void writeImage_xls(HSSFWorkbook wb, HSSFSheet sheet, List<ExcelWriteImage> images) {
        if (images != null && images.size() > 0) {
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            for (int i = 0; i < images.size(); i++) {
                ExcelWriteImage image = images.get(i);
                if (image != null && image.getPicData() != null) {
                    // anchor主要用于设置图片的属性
                    HSSFClientAnchor anchor = new HSSFClientAnchor(image.getDx1(), image.getDy1(), image.getDx2(), image.getDy2(), image.getCol1(), image.getRow1(), image.getCol2(), image.getRow2());
                    // 插入图片
                    patriarch.createPicture(anchor, wb.addPicture(image.getPicData(), image.getPicType()));
                }
            }
        }
    }

    public void writeImage_xlsx(XSSFWorkbook wb, XSSFSheet sheet, List<ExcelWriteImage> images) {
        if (images != null && images.size() > 0) {
            XSSFDrawing drawingPatriarch = sheet.createDrawingPatriarch();
            for (int i = 0; i < images.size(); i++) {
                ExcelWriteImage image = images.get(i);
                if (image != null && image.getPicData() != null) {
                    XSSFClientAnchor anchor = new XSSFClientAnchor(image.getDx1(), image.getDy1(), image.getDx2(), image.getDy2(), image.getCol1(), image.getRow1(), image.getCol2(), image.getRow2());
                    drawingPatriarch.createPicture(anchor, wb.addPicture(image.getPicData(), image.getPicType()));
                }
            }
        }
    }

    /**
     * 自动根据文件扩展名，调用对应的读取方法
     * @param xlsPath 文件路径
     * @return
     * @throws IOException
     */
    public List<Row> readExcel(String xlsPath) throws IOException {
        //扩展名为空时，
        if (xlsPath.equals("")) {
            throw new IOException("文件路径不能为空！");
        } else {
            File file = new File(xlsPath);
            if (!file.exists()) {
                throw new IOException("文件不存在！");
            }
        }
        //获取扩展名
        String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);
        try {
            if ("xls".equals(ext)) {                //使用xls方式读取
                return readExcel_xls(xlsPath);
            } else if ("xlsx".equals(ext)) {        //使用xlsx方式读取
                return readExcel_xlsx(xlsPath);
            } else {                                    //依次尝试xls、xlsx方式读取
                try {
                    return readExcel_xls(xlsPath);
                } catch (IOException e1) {
                    try {
                        return readExcel_xlsx(xlsPath);
                    } catch (IOException e2) {
                        throw e2;
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /***
     * 读取Excel(97-03版，xls格式)
     *
     * @throws Exception
     *
     * @Title: readExcel
     * @Date : 2014-9-11 上午09:53:21
     */
    public List<Row> readExcel_xls(String xlsPath) throws IOException {

        // 判断文件是否存在
        File file = new File(xlsPath);
        if (!file.exists()) {
            throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
        }
        HSSFWorkbook wb = null;// 用于Workbook级的操作，创建、删除Excel
        List<Row> rowList = new ArrayList<Row>();

        try {
            // 读取Excel
            wb = new HSSFWorkbook(new FileInputStream(file));

            // 读取Excel 97-03版，xls格式
            rowList = readExcel(wb,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * 通用读取Excel
     *
     * @param wb
     * @return
     * @Title: readExcel
     * @Title: type  1:xls   2:xlsx
     */
    private List<Row> readExcel(Workbook wb,int type) {
        List<Row> rowList = new ArrayList<Row>();
        Sheet sheet = null;
        try {
            sheet = wb.getSheetAt(0);
            if (type == 1){
                getPicturesByXLS((HSSFSheet) sheet);
            }else if (type ==2){
                getPicturesByXLSX((XSSFSheet) sheet);
            }
            //获取最后行号
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum > 0) {
                Row row = null;
                // 循环读取
                for (int i = startReadPos; i <= lastRowNum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        rowList.add(row);
                    }
                }
            }

        }catch (Exception e){

        }
        return rowList;
    }



    /**
     * //读取Excel 2007版，xlsx格式
     *
     * @Title: readExcel_xlsx
     * @Date : 2014-9-11 上午11:43:11
     * @return
     * @throws Exception
     */
    public List<Row> readExcel_xlsx(String xlsPath) throws IOException {
        // 判断文件是否存在
        File file = new File(xlsPath);
        if (!file.exists()) {
            throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
        }
        XSSFWorkbook wb = null;
        List<Row> rowList = new ArrayList<Row>();
        try {
            FileInputStream fis = new FileInputStream(file);
            // 去读Excel
            wb = new XSSFWorkbook(fis);
            // 读取Excel 2007版，xlsx格式
            rowList = readExcel(wb,2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowList;
    }


    /**
     * 获取图片和位置 (xlsx)
     * @param sheet
     * @return
     * @throws IOException
     */
    public void getPicturesByXLSX (XSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    ExcelReadImage readImage = new ExcelReadImage();
                    readImage.setColIndex(marker.getCol());
                    readImage.setRowIndex(marker.getRow());
//                    readImage.setSheetIndex(0);
                    readImage.setPictureData(picture.getPictureData());
                    readImages.add(readImage);
                }
            }
        }
    }


    /**
     * 获取图片和位置 (xls)
     * @param sheet
     * @return
     * @throws IOException
     */
    public void getPicturesByXLS (HSSFSheet sheet) throws IOException {
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                ExcelReadImage readImage = new ExcelReadImage();
                readImage.setColIndex(cAnchor.getCol1()+0);
                readImage.setRowIndex(cAnchor.getRow1());
                readImage.setPictureData(picture.getPictureData());
                readImages.add(readImage);
            }
        }
    }
}
