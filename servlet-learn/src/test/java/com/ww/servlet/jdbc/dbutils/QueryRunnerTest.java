package com.ww.servlet.jdbc.dbutils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.ww.servlet.jdbc.pool.C3p0PoolUtils;
import com.ww.servlet.model.User;

/**
 * @Description: 使用dbutils框架的QueryRunner类完成CRUD，以及批处理
 * 
 * 该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
 * 
 * @author xiaohua
 * @date 2021年8月3日 下午1:54:26
 */
public class QueryRunnerTest {

	/**
	 * 新增操作
	 * @throws SQLException
	 */
	@Test
	public void add() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "insert user(user_name, password, email, birthday, create_user, create_time, update_time) values(?,?,?,?,?,?,?)";
		Object[] params = { "中南", "123", "yixiu@163.com", new Date(), "wanggw", new Date(), new Date() };
		queryRunner.update(sql, params);
		System.out.println("新增成功");
	}
	
	/**
	 * 删除操作
	 * @throws SQLException
	 */
	@Test
	public void delete() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "delete from user where user_id = ?";
		queryRunner.update(sql, 18);
		System.out.println("删除成功");
	}
	
	/**
	 * 更新操作
	 * @throws SQLException 
	 */
	@Test
	public void update() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "update user set user_name=? where user_id=?";
		Object[] params = { "华科", 19 };
		queryRunner.update(sql, params);
		System.out.println("更新成功");
	}
	
	/**
	 * 查询操作
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	@Test
	public void query() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "select * from user where user_id=?";
		Object[] params = { 19 };
		User user = (User) queryRunner.query(sql, params, new BeanHandler(User.class));
		System.out.println("查询成功");
		System.out.println(user);
	}
	
	/**
	 * 查询所有
	 * @throws SQLException
	 */
	@Test
	public void queryAll() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "select * from user";
		List<User> list = queryRunner.query(sql, new BeanListHandler<User>(User.class));
		if (Objects.nonNull(list)) {
			System.out.println("查询表" + list.size() + "行");
			
			for (User user : list) {
				System.out.println(user);
			}
		}
	}
	
	/**
	 * 批量插入
	 * @throws SQLException
	 */
	@Test
	public void batchInsert() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3p0PoolUtils.geDataSource());
		String sql = "insert user(user_name, password, email, birthday, create_user, create_time, update_time) values(?,?,?,?,?,?,?)";
		Object[][] params = new Object[10][];
		for (int i = 0; i < 10; i++) {
			params[i] = new Object[] { "中南" + i, "123", "yixiu@163.com", new Date(), "wanggw", new Date(), new Date() };
		}
		
		queryRunner.batch(sql, params);
		System.out.println("批量插入成功");
	}
}
