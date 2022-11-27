package excelDemo;

import com.alibaba.excel.EasyExcel;
import excelDemo.read.ExcelListener;
import excelDemo.read.ReadData;
import excelDemo.write.DemoData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    @Test
    public void testWrite() {
        // 实现excel写操作
        // 1.设置文件夹地址
        String fileName = "/Users/zhouzilong/Documents/Java/SprintBoot/tjspace_parent/service/service_info/src/test/java/excel/test.xlsx";
        // 2.调用easy excel, 写完后会自动关流
        // 第二个参数为实体类的class
        EasyExcel.write(fileName, DemoData.class).sheet("student_list").doWrite(getData());
    }

    @Test
    public void testRead() {
        String fileName = "/Users/zhouzilong/Documents/Java/SprintBoot/tjspace_parent/service/service_info/src/test/java/excel/test.xlsx";
        EasyExcel.read(fileName, ReadData.class, new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setId(i);
            data.setName("student" + i);
            list.add(data);
        }
        return list;
    }
}
