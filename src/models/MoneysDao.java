package models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import session.HibernateSessionFactory;

public class MoneysDao {
	public List<Moneys> findAll() {
		List<Moneys> list=new ArrayList<Moneys>();
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			list=(List<Moneys>) myse.createQuery("from Moneys o").list();
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
	
	/**
	 * 保存Moneys对象
	 * @param money
	 * @return
	 */
	public int save(Moneys money) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			myse.save(money);
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
	
	public Moneys findById(int id) {
		Moneys moneys=null;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			String sqlString="from Moneys o where o.id= :id";
			Query query=myse.createQuery(sqlString);
			query.setParameter("id", id);//防止sql注入
			query.setMaxResults(1);//设置最大检索数
			moneys=(Moneys) query.uniqueResult();//返回单个对象
			ts.commit();
		} catch (HibernateException e) {
			if (ts!=null) {
				ts.rollback();
			}
			e.printStackTrace();
		}finally {
			myse.close();
		}
		return moneys;
	}
	
	/**
	 * 根据Id删除一个Moneys对象
	 * @param MoneysId
	 * @return
	 */
	public int delete(Integer MoneysId) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			Good good=new Good();
			good.setId(MoneysId);
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
	
	
	public int update(Moneys money) {
		int result=-1;
		Session myse=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try {
			ts=myse.beginTransaction();
			myse.saveOrUpdate(money);
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
