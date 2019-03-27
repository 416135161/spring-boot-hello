package com.kfit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import sun.awt.SunHints;

public class TestExcel {

    // 读取，全部sheet表及数据
    @Test
    public void showExcel() throws Exception {
        initMap();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/sjning/Documents/未命名文件夹/D2血脂发给宁.xlsx")));
        XSSFSheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                XSSFRow row = sheet.getRow(j);
                if (row != null) {
                    System.out.print(row.getRowNum() + "\t");
                    String[] cells = new String[5];
                    for (int k = 1; k < row.getLastCellNum(); k++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                        if (row.getCell(k) != null) { // getCell 获取单元格数据
                            System.out.print(row.getCell(k) + "\t");
                        } else {
                            System.out.print("\t");
                        }
                        cells[k - 1] = row.getCell(k).toString();
                    }

                    if (cells[0] == null || cells.equals("")) {
                        continue;
                    }
                    double finalValue = getFinalValue(cells);
                    System.out.print(finalValue + "\t");
                    Cell cell = row.createCell(6); // 创建指定单元格对象。如本身有数据会替换掉
                    cell.setCellValue(finalValue); // 设置内容
                }
                System.out.println(""); // 读完一行后换行
            }
            System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
        }

        FileOutputStream fo = new FileOutputStream("/Users/sjning/Documents/未命名文件夹/宁.xlsx"); // 输出到文件
        workbook.write(fo);
    }

    Map<String, Double> map1 = new HashMap<>();
    Map<String, Double> map2 = new HashMap<>();
    Map<String, Double> map3 = new HashMap<>();
    Map<String, Double> map4 = new HashMap<>();
    Map<String, Double> map5 = new HashMap<>();
    double N3 = 0.022;


    private void initMap() {
        map1.put("1.0", 0.0);
        map1.put("2.0", 0.099);
        map1.put("3.0", 0.246);

        map2.put("1.0", 0.0);
        map2.put("2.0", 0.105);
        map2.put("3.0", 0.208);

        map3.put("1.0", 0.0);
        map3.put("2.0", 0.074);
        map3.put("3.0", 0.193);

        map4.put("1.0", 0.0);
        map4.put("2.0", 0.092);
        map4.put("3.0", 0.236);

        map5.put("1.0", 0.0);
        map5.put("2.0", 0.086);
        map5.put("3.0", 0.205);
    }


    private double getFinalValue(String[] cells) {
        double value = 0;
        value = 1 - map1.get(cells[0])
                - map2.get(cells[1])
                - map3.get(cells[2])
                - map4.get(cells[3])
                - map5.get(cells[4]);
        //当有某一项等于3时，减去N3
        if (isEnyoneBigger3(cells)) {
            value -= N3;
        }
        //当不是所有项都为1时，减去常数项
        if (value != 1.0) {
            value -= 0.039;
        }

        return value;
    }

    private boolean isEnyoneBigger3(String[] cells) {
        for (int i = 0; i < cells.length; i++) {
            if (new BigDecimal(cells[i]).doubleValue() >= 3)
                return true;
        }
        return false;
    }

    // 读取，指定sheet表及数据
    @Test
    public void showExcel2() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/sjning/Documents/未命名文件夹/D2血脂.xlsx")));
        XSSFSheet sheet = null;
        int i = workbook.getSheetIndex("Sheet4"); // sheet表名
        sheet = workbook.getSheetAt(i);
        Map<String, Value> map = new HashMap<>();
        for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
            // 获取最后一行的行标
            XSSFRow row = sheet.getRow(j);
            if (row != null) {
                String key = "";
                Value value = new Value();
                for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
                    // 是获取最后一个不为空的列是第几个
                    if (row.getCell(k) != null) { // getCell 获取单元格数据
                        String cellConent = row.getCell(k).toString();
                        System.out.print(cellConent + "\t");
                        if (k == 0) {
                            key = cellConent;
                        } else if (k == 1) {
                            value.v1 = new BigDecimal(cellConent).doubleValue();
                        } else if (k == 2) {
                            value.v2 = new BigDecimal(cellConent).doubleValue();
                        } else if (k == 3) {
                            value.v3 = new BigDecimal(cellConent).doubleValue();
                        }
                    } else {
                        System.out.print("\t");
                    }
                }
                insetMap(map, key, value);

            }
            System.out.println("");

        }
        insertExcel3(map);

    }

    private void insetMap(Map<String, Value> map, String key, Value value) {
        if (map.containsKey(key) && map.get(key) != null) {
            Value value0 = map.get(key);
            if (value0.v1 > value.v1) {
                value.v1 = value0.v1;
            }
            if (value0.v2 > value.v2) {
                value.v2 = value0.v2;
            }
            if (value0.v3 < value.v3) {
                value.v3 = value0.v3;
            }
            map.put(key, value);
        } else {
            map.put(key, value);
        }

    }

    // 写入，往指定sheet表的单元格

    public void insertExcel3(Map<String, Value> map) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/sjning/Documents/未命名文件夹/D2血脂.xlsx"))); // 读取的文件
        XSSFSheet sheet = workbook.createSheet("Sheet4-总结"); // sheet表名

        int i = 0;
        ArrayList<Value> list = new ArrayList<>();
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            entry.getValue().index = new BigDecimal(entry.getKey()).intValue();
            list.add(entry.getValue());
            i++;
        }
        Collections.sort(list, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.index - o2.index;
            }
        });
        i = 0;
        for (; i < list.size(); i++) {
            Value value = list.get(i);
            XSSFRow row = sheet.getRow(i); // 获取指定的行对象，无数据则为空，需要创建
            if (row == null) {
                row = sheet.createRow(i); // 该行无数据，创建行对象
            }
            for (int k = 0; k < 3; k++) {
                Cell cell = row.createCell(k); // 创建指定单元格对象。如本身有数据会替换掉
                if (k == 0) {
                    cell.setCellValue(value.index); // 设置内容
                } else if (k == 1) {
                    cell.setCellValue(value.v1); // 设置内容
                } else if (k == 2) {
                    cell.setCellValue(value.v2); // 设置内容
                } else if (k == 3) {
                    cell.setCellValue(value.v3); // 设置内容
                }

            }
        }

        FileOutputStream fo = new FileOutputStream("/Users/sjning/Documents/未命名文件夹/D2血脂.xlsx"); // 输出到文件
        workbook.write(fo);

    }

    public class Value {
        public int index;
        public double v1;
        public double v2;
        public double v3;
    }


    // 写入，往指定sheet表的单元格
    @Test
    public void insertExcelRuand() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/sjning/Documents/未命名文件夹/工作簿2.xlsx"))); // 读取的文件
        int n = workbook.getSheetIndex("Sheet1"); // sheet表名
        XSSFSheet sheet = workbook.getSheetAt(n);

        int k = 0;
        for (int i = 1; i < 100; i++) {
            XSSFRow row = sheet.getRow(i); // 获取指定的行对象，无数据则为空，需要创建
            if (row == null) {
                row = sheet.createRow(i); // 该行无数据，创建行对象
            }
            Cell cell = row.getCell(1);
            if (cell == null) {
                cell = row.createCell(1);
            }
            DecimalFormat df = new DecimalFormat("0.00");
            float aaa = getAround(0.60, 0.85);

            cell.setCellValue(df.format(aaa));

            Cell cell2 = row.getCell(2);
            if (cell2 == null) {
                cell2 = row.createCell(2);
            }
            float aaa2 = getAround(0.82, 0.98);

            cell2.setCellValue(df.format(aaa2));

            System.out.println(df.format(aaa2) + "\t");

        }

        FileOutputStream fo = new FileOutputStream("/Users/sjning/Documents/未命名文件夹/工作簿2.xlsx"); // 输出到文件
        workbook.write(fo);

    }

    java.util.Random r = new java.util.Random(20);

    private float getAround(double a, double b) {

        float aaa;
        while (true) {
            aaa = r.nextFloat();
            if (aaa > a && aaa < b) {
                break;
            }
        }
        return aaa;
    }

    @Test
    public void testCombination() {

        System.out.println(getCombination(10, 4) * getCombination(4, 1));
    }

    /**
     * 算组合
     *
     * @param n
     * @param k
     * @return
     */
    public int getCombination(int n, int k) {
        if (n < k) {
            return 0;
        }
        int result = 0;
        result = getFactorial(n) / (getFactorial(k) * getFactorial(n - k));
        return result;
    }

    /**
     * 算级数
     *
     * @param n
     * @return
     */
    public int getFactorial(int n) {
        int s = 1;
        for (int i = 1; i <= n; i++) {
            s *= i;
        }
        return s;
    }


}
