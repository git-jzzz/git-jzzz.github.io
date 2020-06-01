package cn.sb.audioInterface.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableData {
    private int code;
    private List data = new ArrayList();
    private int count;//数据库条数

    public static TableData ok(int code, List data, int count) {
        TableData tableData = new TableData();
        tableData.setCode(code);
        tableData.setData(data);
        tableData.setCount(count);
        return tableData;
    }
}
