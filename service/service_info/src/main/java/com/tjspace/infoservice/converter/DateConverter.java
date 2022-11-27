package com.tjspace.infoservice.converter;


import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.tjspace.infoservice.entity.BO.StartEndDate;

/**
 * 将excel中的开始日期字段转化为起始-结束时间
 *
 * @author zhouzilong
 */
public class DateConverter implements Converter<StartEndDate> {


    @Override
    public Class supportJavaTypeKey() {
        return StartEndDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public StartEndDate convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String date = cellData.getStringValue();
        // convert to StartEndDate
        return null;
    }

    @Override
    public CellData convertToExcelData(StartEndDate startEndDate, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
