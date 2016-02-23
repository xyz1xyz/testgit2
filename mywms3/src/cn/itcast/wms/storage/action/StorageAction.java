package cn.itcast.wms.storage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.location.service.LocationService;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;
import cn.itcast.wms.storagebin.service.StorageBinService;

public class StorageAction extends BaseAction {

	@Resource
	private StorageService storageService;
	@Resource
	private LocationService locationService;
	@Resource
	private StorageBinService storageBinService;
	private WmsStorage storage;
	private WmsStorageBin storageBin;
	//用来批量删除数据
	private String[] selected=new String[6];

	//列表页面
		public String listUI() throws Exception {
			QueryHelper queryHelper = new QueryHelper(WmsStorage.class, "e");
			try {
				if (storage != null) {
					if (StringUtils.isNotBlank(storage.getName())) {
						
						queryHelper.addCondition("e.name like ?", "%" + storage.getName() + "%");
					}

				}
				pageResult = storageService.getPageResult(queryHelper, getPageNo(), getPageSize());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}

		//新增页面
		public String addUI(){
			//加载地域
			ActionContext.getContext().getContextMap().put("locationlist", locationService.findObjects());
			
			return "addUI";
		}
		//保存新增
		public String add(){
			try {
				if(storage != null){
				
					storageService.save(storage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//编辑页面
		public String editUI(){
			//加载地域
			ActionContext.getContext().getContextMap().put("locationlist", locationService.findObjects());
			if (storage != null && storage.getId() != null) {
			
				storage = storageService.findObjectById(storage.getId());
				
			}
			return "editUI";
		}
		//保存编辑
		public String edit(){
			try {
				if(storage != null){
					

					storageService.update(storage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//删除
		public String delete(){
			if(storage != null && storage.getId() != null){
				storageService.delete(storage.getId());
			}
			return "list";
		}
		//批量删除
		public String deleteSelected(){
			try {
				   for(int j=0;j<selectedRow.length;j++)
				   {
					   
				       
					   QueryHelper query=new QueryHelper(WmsStorage.class, "ws");
					   WmsStorage storages=storageService.findObjectById(selectedRow[j]);
					   QueryHelper query2=new QueryHelper(WmsStorageBin.class,"wsb");
					   query2.addCondition("wsb.storeName=?",storages.getName() );
					   List<WmsStorageBin> list=storageBinService.findObjects(query2);
					  
						if(list.size()==0)
						{
							selected[j]=selectedRow[j];//selected[]是可以删除的数据
						}
						
						
				   }
				   for(int i=0;i<selected.length;i++)
				   {
					   System.out.println(selected[i]);
				   }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(selected != null){
				for(String id: selected){
					if(id!=null){
						storageService.delete(id);
					}
				}
			}
			return "list";
		}
		//校验
				public void verify(){
					try {
						
						if(storage != null && StringUtils.isNotBlank(storage.getName())){
							
							QueryHelper query=new QueryHelper(WmsStorage.class,"wl");
							query.addCondition("wl.name=?", storage.getName());
							//query.addCondition("wl.address=?", storage.getAddress());
							List<WmsStorage> list=storageService.findObjects(query);
							
							String strResult = "true";
							if(list != null && list.size() > 0){
								
								strResult = "false";
							}
							System.out.println(strResult);
							
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
				
			//删除仓库校验
				public void verifyStorage()
				{
					try {
						QueryHelper query=new QueryHelper(WmsStorage.class,"ws");
						//query.addCondition("wl.name=?", wmsLocation.getId());
						//根据id查询仓库名
						WmsStorage st=storageService.findObjectById(storage.getId());
						//根据地域名查询仓位表是否有使用上面查到的仓库名
						QueryHelper query2=new QueryHelper(WmsStorageBin.class, "wsb");
						query2.addCondition("wsb.storeName=?", st.getName());
						List<WmsStorageBin> list=storageBinService.findObjects(query2);
						
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
			

	public WmsStorage getStorage() {
		return storage;
	}
	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

	public WmsStorageBin getStorageBin() {
		return storageBin;
	}

	public void setStorageBin(WmsStorageBin storageBin) {
		this.storageBin = storageBin;
	}
	
}
