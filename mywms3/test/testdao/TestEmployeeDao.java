package testdao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.itcast.wms.location.dao.LocationDao;
import cn.itcast.wms.location.entity.WmsLocation;

public class TestEmployeeDao {

	private static LocationDao locationDao;
	private WmsLocation location=new WmsLocation();
	private List<WmsLocation> locationlist=new ArrayList<WmsLocation>();
	static{
	ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
	locationDao=(LocationDao) ac.getBean("locationDao");
	}
	@Test
	public void testinsert()
	{
		location.setName("西湖");
		locationDao.save(location);
		
	}
	@Test
	public void testfindObjectById()
	{
		location=locationDao.findObjectById("40284e5f5208571c015208571e4f0000");
		System.out.println(location.getId()+":"+location.getName());
	}
	@Test
	public void testupdate()
	{
		location=locationDao.findObjectById("40284e5f5208571c015208571e4f0000");
		location.setName("北京");
		locationDao.update(location);
		System.out.println(location.getName());
	}
	@Test
	public void testfindObjects()
	{
		locationlist=locationDao.findObjects();
		for(WmsLocation w:locationlist)
		{
			System.out.println(w.getName());
		}
		
	}
	@Test
	public void testfindObjectsDyc()
	{
		String hql="from WmsLocation where name=?";
		List<Object> parameters=new ArrayList<Object>();
		parameters.add("北京");
		locationlist=locationDao.findObjects(hql, parameters);
		for(WmsLocation w:locationlist)
		{
			System.out.println(w.getId()+":"+w.getName());
		}
	}
	
	
}
