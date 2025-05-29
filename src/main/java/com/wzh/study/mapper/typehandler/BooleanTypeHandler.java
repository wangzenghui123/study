package com.wzh.study.mapper.typehandler;



import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanTypeHandler implements TypeHandler<Boolean> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter ? 1 : 0);
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return value == 1;

    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return value == 1;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return value == 1;
    }
}
