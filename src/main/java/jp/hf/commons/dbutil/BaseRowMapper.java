package jp.hf.commons.dbutil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapperの基本クラス このクラスを継承してEntityを作成してください。
 * 
 * @author at
 * 
 * @param <T>
 */
public abstract class BaseRowMapper<T extends Object> implements RowMapper<T> {
    @SuppressWarnings("rawtypes")
    private Set setAvailableColumns;
    private ResultSet rs;
    private static String prefix;

    public BaseRowMapper() {
        super();
        prefix = "";
    }

    public BaseRowMapper(String prefix) {
        BaseRowMapper.prefix = prefix;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void init(ResultSet rs) throws SQLException {
        this.rs = rs;
        setAvailableColumns = new HashSet();
        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1, n = meta.getColumnCount() + 1; i < n; i++) {
            setAvailableColumns.add(meta.getColumnName(i));
        }
    }

    public T mapRow(ResultSet rs, int rownum) throws SQLException {
        if (setAvailableColumns == null) {
            init(rs);
        }
        return mapRowImpl(rs, rownum);
    }

    public abstract T mapRowImpl(ResultSet rs, int rownum) throws SQLException;

    public boolean column(String stringName) {
        return (setAvailableColumns.contains(stringName));
    }

    public String getString(String stringName) throws SQLException {
        if (column(prefix + stringName)) {
            return rs.getString(prefix + stringName);
        } else {
            return null;
        }
    }

    public Long getLong(String stringName) throws SQLException {
        if (column(prefix + stringName)) {
            return rs.getLong(prefix + stringName);
        } else {
            return new Long(0L);
        }
    }

    public double getDouble(String stringName) throws SQLException {
        if (column(prefix + stringName)) {
            return rs.getDouble(prefix + stringName);
        } else {
            return new Double(0d);
        }
    }

    public Integer getInteger(String stringName) throws SQLException {
        if (column(prefix + stringName)) {
            return rs.getInt(prefix + stringName);
        } else {
            return new Integer(0);
        }
    }

    public LocalDateTime getDate(String stringName) throws SQLException {
        if (column(prefix + stringName)) {
            try {
                rs.getTimestamp(prefix + stringName).getTime();
            } catch (Exception e) {
                return null;
            }
            return rs.getTimestamp(prefix + stringName).toLocalDateTime();
        } else {
            return null;
        }
    }
}
