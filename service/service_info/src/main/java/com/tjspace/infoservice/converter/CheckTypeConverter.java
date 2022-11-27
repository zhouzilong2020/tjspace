package com.tjspace.infoservice.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * excel中的考查字段转化为整数
 *
 * @author zhouzilong
 */
public class CheckTypeConverter implements Converter<Integer> {
    enum checkType {
        // 考查
        NO_TEST("考查", 0),
        // 考试
        TEST("考试", 1);
        private Integer val;
        private String type;

        checkType(String type, Integer val) {
            this.type = type;
            this.val = val;
        }
    }

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String cellVal = cellData.getStringValue();
        if ((checkType.NO_TEST.type).equals(cellVal)) {
            return checkType.NO_TEST.val;
        } else if ((checkType.TEST.type).equals(cellVal)) {
            return checkType.TEST.val;
        } else {
            return null;
        }
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
