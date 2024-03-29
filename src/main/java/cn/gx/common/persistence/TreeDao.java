/**
 * Copyright &copy; 2015-2020 <a href="http://www.bug.org/">JeePlus</a> All rights reserved.
 */
package cn.gx.common.persistence;

import java.util.List;

/**
 * DAO支持类实现
 * @author bug
 * @version 2014-05-16
 * @param <T>
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}