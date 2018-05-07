package models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import session.HibernateSessionFactory;

public class GoodsDao {
	//查找所有
	public List<Good> findAll() {
		List<Good> list=new ArrayList<Good>();
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			Criteria criteria=myse.createCriteria(Good.class);
			criteria.addOrder(Order.asc("id"));//desc降序
			list=criteria.list();
			ts.commit();
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return list;
	}
	
	public Good findById(int id) {
		Good good=null;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			String sqlString="from Good o where o.id= :id";
			Query query=myse.createQuery(sqlString);
			query.setParameter("id", id);//防止sql注入
			query.setMaxResults(1);//设置最大检索数
			good=(Good) query.uniqueResult();//返回单个对象
			ts.commit();
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return good;
	}
	/**
	 * 保存Good对象
	 * @param good
	 * @return
	 */
	public int save(Good good) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			myse.save(good);
			ts.commit();
			result=1;
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
				result=0;
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return result;
	}
	
	/**
	 * 根据Id删除一个Good对象
	 * @param GoodId
	 * @return
	 */
	public int delete(Integer GoodId) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			Good good=new Good();
			good.setId(GoodId);
			myse.delete(good);
			ts.commit();
			result=1;
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
				result=0;
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return result;
	}
	
	
	public int update(Good good) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			myse.saveOrUpdate(good);
			ts.commit();
			result=1;
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
				result=0;
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return result;
	}
}
