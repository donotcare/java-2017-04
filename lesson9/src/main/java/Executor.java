import model.DataSet;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Map;

public class Executor {
    private Map<Class, EntityInfo> entities;

    public Executor() {
        init();
    }

    private void init() {
        entities = new EntityLoader().scanPackage("model");
    }

    public <T extends DataSet> void save(T dataSet) {
        EntityInfo entityInfo = entities.get(dataSet.getClass());
        try (Connection conn = ConnectionPool.getConnection()) {
            String sql;
            if (dataSet.getId() == null) {
                sql = entityInfo.getInsertQuery();
                PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepareStatementParams(entityInfo, dataSet, stm);
                stm.executeUpdate();
                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dataSet.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating failed, no ID obtained.");
                    }
                }
            } else {
                sql = entityInfo.getUpdateQuery();
                PreparedStatement stm = conn.prepareStatement(sql);
                prepareStatementParams(entityInfo, dataSet, stm);
                stm.setLong(entityInfo.getColumns().length + 1, dataSet.getId());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T extends DataSet> void prepareStatementParams(EntityInfo entityInfo, T dataSet, PreparedStatement stm) throws Exception {
        int index = 1;
        for (FieldColumn column : entityInfo.getColumns()) {
            Field field = dataSet.getClass().getDeclaredField(column.fieldName);
            field.setAccessible(true);
            Object value = field.get(dataSet);
            stm.setObject(index++, value);
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        EntityInfo entityInfo = entities.get(clazz);
        try (Connection conn = ConnectionPool.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(entityInfo.getSelectQuery());
            stm.setObject(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                T obj = clazz.newInstance();
                obj.setId(id);
                for (FieldColumn column : entityInfo.getColumns()) {
                    Object value = rs.getObject(column.columnName);
                    Field field = obj.getClass().getDeclaredField(column.fieldName);
                    field.setAccessible(true);
                    field.set(obj, value);

                }
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
