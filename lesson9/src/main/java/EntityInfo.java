import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EntityInfo {
    private final String table;
    private final FieldColumn[] columns;
    private String insertQuery;
    private String updateQuery;
    private String selectQuery;

    public EntityInfo(String table, FieldColumn[] columns) {
        this.table = table;
        this.columns = columns;
        createQueries();
    }

    private void createQueries() {
        createInsertQuery();
        createUpdateQuery();
        createSelectQuery();
    }

    private void createInsertQuery() {
        String columnsString = Stream.of(columns).map(c -> c.columnName).collect(Collectors.joining(", "));
        String questionMarks = IntStream.range(0, columns.length).mapToObj(i -> "?").collect(Collectors.joining(", "));
        insertQuery = String.format(SqlTemplates.INSERT.template, table, columnsString, questionMarks);
    }

    private void createUpdateQuery() {
        String columnsString = Stream.of(columns).map(c -> c.columnName + " = ?").collect(Collectors.joining(", "));
        updateQuery = String.format(SqlTemplates.UPDATE.template, table, columnsString);
    }

    private void createSelectQuery() {
        selectQuery = String.format(SqlTemplates.SELECT.template, table);
    }

    public FieldColumn[] getColumns() {
        return columns;
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public String getUpdateQuery() {
        return updateQuery;
    }

    public String getSelectQuery() {
        return selectQuery;
    }

}
