package excelDemo.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExcelListener extends AnalysisEventListener<ReadData> {
    // 一行一行读取excel的内容
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        // readData是一行的数据
        System.out.println("-------" + readData + "-------");
    }

    // 读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
