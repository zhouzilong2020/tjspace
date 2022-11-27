package excelDemo.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * easyExcel demo
 */
@Data
public class DemoData {
    // 设置excel表头，excel的第一行
    @ExcelProperty("学生编号")
    private Integer id;

    @ExcelProperty("学生姓名")
    private String name;

}
