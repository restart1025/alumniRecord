package cn.edu.cumt.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.edu.cumt.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T>
{
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	// ����ID����ʵ��
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession()
			.get(entityClazz , id);
	}
	// ����ʵ��
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession()
			.save(entity);
	}
	// ����ʵ��
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession()
				.saveOrUpdate(entity);
	}
	// ɾ��ʵ��
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	// ɾ��ʵ��
	public void delete(Class<T> entityClazz , Serializable id)
	{
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName()
				+ " en where en.id = ?0")
			.setParameter("0" , id)
			.executeUpdate();
	}
	
	public long findCount(Class<T> entityClazz)
	{
		List<?> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params)
	{
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param params ���hql��ռλ��������params���ڴ���ռλ������
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */

	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql , int pageNo, int pageSize
		, Object... params)
	{
		// ������ѯ
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		// Ϊ����ռλ����HQL������ò���
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		// ִ�з�ҳ�������ز�ѯ���
		return query.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
}
