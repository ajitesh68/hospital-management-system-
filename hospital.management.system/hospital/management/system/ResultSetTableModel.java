package hospital.management.system;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetTableModel extends AbstractTableModel {

    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int columnCount;
    private List<List<Object>> cachedData;
    private int rowCount = -1;

    public ResultSetTableModel(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        this.metaData = resultSet.getMetaData();
        this.columnCount = metaData.getColumnCount();
        this.cachedData = new ArrayList<>();
        this.rowCount = loadData();
    }

    private int loadData() throws SQLException {
        int rows = 0;
        while (resultSet.next()) {
            List<Object> rowData = new ArrayList<>();
            for (int col = 1; col <= columnCount; col++) {
                rowData.add(resultSet.getObject(col));
            }
            cachedData.add(rowData);
            rows++;
        }
        return rows;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int column) {
        try {
            return metaData.getColumnName(column + 1);  // ResultSet columns are 1-based
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < rowCount) {
            return cachedData.get(rowIndex).get(columnIndex);
        }
        return null;
    }
}

