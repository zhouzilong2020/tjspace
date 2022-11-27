package excelDemo.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * easyExcel demo
 * 对应实体类
 * 然后需要创建监听类-->easyExcel是逐行读取的
 */
@Data
public class ReadData {
    // 设置excel表头，excel的第一行
    // 标记好对应关系
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String name;

}
