package com.xuecheng.utils;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception{
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.setStartReadPos(1);
        List<Row> rows = excelUtils.readExcel("F:\\exceltest\\bbb.xls");
//        List<ExcelReadImage> images = excelUtils.getReadImages();
//        if (images.size() > 0){
//            byte[] pictureData = images.get(0).getPictureData().getData();
//            new FileOutputStream("F:\\exceltest\\aa.png").write(pictureData);
//        }

        List<List<Object>> as = new ArrayList<>();
        List<Object> a = new ArrayList<>();
        a.add(12);
        a.add(23);
        List<Object> b = new ArrayList<>();
        b.add(23);
        b.add(2345);
        as.add(a);
        as.add(b);
        List<ExcelWriteImage> images = new ArrayList<>();
        ExcelWriteImage writeImage = new ExcelWriteImage();
        writeImage.setCol1((short) 3);
        writeImage.setRow1(3);

        writeImage.setCol2((short) 5);
        writeImage.setRow2(5);

        InputStream in = new FileInputStream("F:\\exceltest\\aa.png");
        byte[] data = toByteArray(in);
        writeImage.setPicData(data);
        images.add(writeImage);
        excelUtils.writeExcel(as,"F:\\exceltest\\aaa.xls",images);
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}
