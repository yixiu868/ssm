package com.ww.servlet.jdbc.dbutils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.Test;

import com.ww.servlet.jdbc.pool.C3p0PoolUtils;

/**
 * @Description: 该接口用于处理java.sql.ResultSet，将数据按要求转换为另一种形式
 * 
 * ArrayHandler：把结果集中的第一行数据转成对象数组。
 * ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
 * BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
 * BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 * ColumnListHandler：将结果集中某一列的数据存放到List中。
 * KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
 * MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 * MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 * 
 * @author xiaohua
 * @date 2021年8月3日 下午2:35:48
 */
public class ResultSetHandlerTest {

	/**
	 * ArrayHandler
	 * @throws SQLException
	 */
	@Test
	public void testArrayHandler() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "select * from user";
		Object[] result = (Object[]) queryRunner.query(sql, new ArrayHandler());
		System.out.println(Arrays.asList(result));
	}
	
	/**
	 * ArrayListHandler
	 * @throws SQLException
	 */
	@Test
	public void testArrayListHandler() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "select * from user";
		List<Object[]> list = (List<Object[]>) queryRunner.query(sql, new ArrayListHandler());
		for (Object[] objects : list) {
			System.out.println(Arrays.asList(objects));
		}
	}
	
	/**
	 * ColumnListHandler
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testColumnListHandler() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "select * from user";
		List list = (List) queryRunner.query(sql, new ColumnListHandler("user_id"));
		System.out.println(list);
	}
}
