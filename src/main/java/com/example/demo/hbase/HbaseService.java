package com.example.demo.hbase;

import cn.hutool.json.JSONUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dushixiang
 * @date 2019-07-09 16:48
 */
@Service
public class HbaseService {

    @Resource
    private Connection hConn;

    public <T> List<CapRow<T>> getDataByRowKey(Table table, byte[] rowKey, Class<T> klass) throws IOException {
        Get get = new Get(rowKey);
        Result r = table.get(get);

        return rowCellToCapRow(r.rawCells(), klass);
    }

    public <T> List<CapRow<T>> rowCellToCapRow(Cell[] cells, Class<T> klass) {
        return Arrays.stream(cells)
                .map(x -> {
                    long timestamp = Long.parseLong(Bytes.toString(CellUtil.cloneQualifier(x)));
                    String jsonStr = Bytes.toString(CellUtil.cloneValue(x));
                    T object = JSONUtil.toBean(jsonStr, klass);
                    return new CapRow<T>()
                            .setResult(object)
                            .setTimestamp(new Date(timestamp));
                })
                .collect(Collectors.toList());
    }

    public <T> List<CapRow<T>> search(TableName tableName, Date startTimeDate, Date endTimeDate, String startRowKey, String endRowKey, Class<T> klass) throws IOException {
        Table table = hConn.getTable(tableName);
        List<CapRow<T>> dataList = new ArrayList<>();

        if (Objects.equals(startRowKey, endRowKey)) {
            dataList.addAll(getDataByRowKey(table, Bytes.toBytes(startRowKey), klass));
        } else {

            Scan scan = new Scan();
            scan.setStartRow(Bytes.toBytes(startRowKey));
            scan.setStopRow(Bytes.toBytes(endRowKey));

            ResultScanner results = table.getScanner(scan);
            for (Result result : results) {
                dataList.addAll(rowCellToCapRow(result.rawCells(), klass));
            }
        }

        return dataList
                .stream()
                .filter(x -> x.getTimestamp().getTime() >= startTimeDate.getTime() &&
                        x.getTimestamp().getTime() <= endTimeDate.getTime())
                .collect(Collectors.toList());
    }

}
