package com.hh.dao.impl;

import com.hh.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 11 - 15:21
 * <p>
 * Description:
 * 1.
 * 2.
 */
public abstract class BaseDao {
    //使用DBUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    //  增删改操作
    public int update(String sql,Object ...args){
        Connection connection= JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查询返回1个javabean的sql语句
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查询返回多个javabean的sql语句
    public <T>List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    * 执行返回一行一列的sql语句
    * */
    public Object queryForSingleValue(String sql, Object ...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
