package cn.itcast.wms.location.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;


import cn.itcast.instorage.entity.WmsForm;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.location.service.LocationService;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;

public class LocationAction extends BaseAction {

	@Resource
	private LocationService locationService;
	@Resource 
	private StorageService storageService;
	private WmsLocation wmsLocation;
	private WmsStorage wmsStorage;
	//用来批量删除数据
	private String[] selected=new String[6];
	//列表页面
		public String listUI() throws Exception {
			
			// 加载类型集合
			ActionContext.getContext().getContextMap()
							.put("CATOGERY_MAP",WmsLocation.CATOGERY_MAP);
			QueryHelper queryHelper = new QueryHelper(WmsLocation.class, "w");
			try {
				if (wmsLocation != null) {
					if (StringUtils.isNotBlank(wmsLocation.getName())) {
						
						queryHelper.addCondition("w.name like ?", "%" + wmsLocation.getName() + "%");
					}

				}
				pageResult = locationService.getPageResult(queryHelper, getPageNo(), getPageSize());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}

		//转到增加页面
		public String addUI(){
			// 加载类型集合
						ActionContext.getContext().getContextMap()
										.put("CATOGERY_MAP",WmsLocation.CATOGERY_MAP);
			return "addUI";
		}
		//保持新增
		public String add(){
			try {
				if(wmsLocation != null){
				
					locationService.save(wmsLocation);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//转到编辑页面
		public String editUI(){
			
			if (wmsLocation != null && wmsLocation.getId() != null) {
			    
				wmsLocation = locationService.findObjectById(wmsLocation.getId());
				
			}
			return "editUI";
		}
		//保持编辑
		public String edit(){
			try {
				if(wmsLocation != null){
					//查找仓库表并修改
					QueryHelper query=new QueryHelper(WmsStorage.class, "ws");
					query.addCondition("ws.addressId=?", wmsLocation.getId());
					List<WmsStorage> storages=storageService.findObjects(query);
					for(WmsStorage ws:storages)
					{
						ws.setAddress(wmsLocation.getName());
						storageService.update(ws);
					}
					

					locationService.update(wmsLocation);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//删除
		public String delete(){
			if(wmsLocation != null && wmsLocation.getId() != null){
				locationService.delete(wmsLocation.getId());
			}
			return "list";
		}
		//批量删除
		public String deleteSelected(){
			try {
				   for(int j=0;j<selectedRow.length;j++)
				   {
					   
				       
					   QueryHelper query=new QueryHelper(WmsLocation.class, "wl");
					   WmsLocation location=locationService.findObjectById(selectedRow[j]);
					   QueryHelper query2=new QueryHelper(WmsStorage.class,"ws");
					   query2.addCondition("ws.address=?",location.getName() );
					   List<WmsStorage> list=storageService.findObjects(query2);
					  
						if(list.size()==0)
						{
							selected[j]=selectedRow[j];//selected[]是可以删除的数据
						}
						
						
						
				   }
				  /* for(int i=0;i<selected.length;i++)
				   {
					   System.out.println(selected[i]);
				   }*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(selected != null){
				for(String id: selected){
					if(id!=null){
						locationService.delete(id);
					}
				}
			}
			return "list";
		}
		
		//校验
		public void verify(){
			try {
			
				if(wmsLocation != null && StringUtils.isNotBlank(wmsLocation.getName())){
					
					QueryHelper query=new QueryHelper(WmsLocation.class,"wl");
					query.addCondition("wl.name=?", wmsLocation.getName());
					List<WmsLocation> list=locationService.findObjects(query);
					
					String strResult = "true";
					if(list != null && list.size() > 0){
						//数据已存在
						WmsLocation loca=list.get(0);
						if(loca.getId().equals(wmsLocation.getId()))
						    strResult = "true";
						else
							strResult = "false";
						
					}
					
					//输出
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write(strResult.getBytes());
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//删除地域的校验
		public void verifyLocation()
		{
			try {
				QueryHelper query=new QueryHelper(WmsLocation.class,"wl");
				//query.addCondition("wl.name=?", wmsLocation.getId());
				//根据id查询地域名
				WmsLocation location=locationService.findObjectById(wmsLocation.getId());
				//根据地域名查询仓库表是否有使用上面查到的地域
				QueryHelper query2=new QueryHelper(WmsStorage.class, "ws");
				query2.addCondition("ws.address=?", location.getName());
				List<WmsStorage> list=storageService.findObjects(query2);
				String strResult="true";
				if(list!=null&&list.size()>0)
				{
					//数据已存在
					strResult = "false";
				}
				//输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
			
			
		}
		
		
		public WmsLocation getWmsLocation() {
			return wmsLocation;
		}

		public void setWmsLocation(WmsLocation wmsLocation) {
			this.wmsLocation = wmsLocation;
		}

		public WmsStorage getWmsStorage() {
			return wmsStorage;
		}

		public void setWmsStorage(WmsStorage wmsStorage) {
			this.wmsStorage = wmsStorage;
		}

		

	
}
