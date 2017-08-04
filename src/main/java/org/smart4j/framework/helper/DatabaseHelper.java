package org.smart4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.CollectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库助手类
 * Created by ithink on 2017-6-18.
 */
public class DatabaseHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private final static QueryRunner QUERY_RUNNER;
    private final static ThreadLocal<Connection> CONN_HOLDER;
    private final static BasicDataSource DATA_SOURCE;

    static {
        QUERY_RUNNER = new QueryRunner();
        CONN_HOLDER = new ThreadLocal<Connection>();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(ConfigHelper.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigHelper.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigHelper.getJdbcUsername());
        DATA_SOURCE.setPassword(ConfigHelper.getJdbcPassword());
    }

    /**
     * 获得连接
     */
    public static Connection getConnection(){
        Connection connection = CONN_HOLDER.get();

        if(connection == null){
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONN_HOLDER.set(connection);
            }
        }

        return connection;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction(){
        Connection conn = getConnection();

        if(conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.error("begin transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONN_HOLDER.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction(){
        Connection conn = getConnection();

        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e){
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONN_HOLDER.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction(){
        Connection conn = getConnection();

        if(conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e){
                LOGGER.error("rollback transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                CONN_HOLDER.remove();
            }
        }
    }

    /**
     * 执行查询
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object...params){
        List<Map<String, Object>> result;
        try {
            result = QUERY_RUNNER.query(getConnection(), sql, new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 执行更新
     */
    public static int executeUpdate(String sql, Object...params){
        int rows = 0;
        try {
            rows = QUERY_RUNNER.update(getConnection(), sql, params);
        } catch (SQLException e){
            LOGGER.error("update failure", e);
            throw new RuntimeException(e);
        }

        return rows;
    }

    /**
     * 插入实体
     */
    public static boolean insertEntity(Class<?> entityClass, Map<String, Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("nothing can be inserted into database");
            return false;
        }

        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        for(String fieldName : fieldMap.keySet()){
            columns.append(fieldName + ", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ");");

        String sql = "insert into " + entityClass.getSimpleName() + columns + " values " + values;

        Object[] params = fieldMap.values().toArray();

        return (executeUpdate(sql, params) == 1);

    }

    /**
     * 更新实体
     */
    public static boolean updateEntity(Class<?> entityClass, long id, Map<String, Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("nothing can be inserted into database");
            return false;
        }

        StringBuilder sb = new StringBuilder();
        for(String fieldName : fieldMap.keySet()){
            sb.append(fieldName + "=?, ");
        }
        sb.replace(sb.lastIndexOf(", "), sb.length(), " where id=" + id + ";");

        String sql = "update " + entityClass.getSimpleName() + " set " + sb;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     */
    public static boolean deleteEntity(Class<?> entityClass, long id){
        String sql = "delete from " + entityClass.getSimpleName() + " where id=?;";
        return executeUpdate(sql, id)==1;
    }

    /**
     * 查询实体
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object...params){
        List<T> beanList;

        try {
            beanList = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        }

        return beanList;
    }

    /**
     * 查询实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object...param){
        T entity;
        try{
            entity = QUERY_RUNNER.query(getConnection(), sql, new BeanHandler<T>(entityClass), param);
        } catch (SQLException e){
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        }

        return entity;
    }

}
