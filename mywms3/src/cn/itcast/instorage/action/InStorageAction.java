package cn.itcast.instorage.action;



import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.entity.WmsForm;
import cn.itcast.instorage.entity.WmsFormDetail;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.instorage.entity.WmsInventoryBin;
import cn.itcast.instorage.service.WmsFormDetailService;
import cn.itcast.instorage.service.WmsFormService;
import cn.itcast.instorage.service.WmsInventoryBinService;
import cn.itcast.instorage.service.WmsInventoryService;
import cn.itcast.wms.employee.entity.Employee;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.location.service.LocationService;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.material.service.MaterialService;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;
import cn.itcast.wms.storagebin.service.StorageBinService;


public class InStorageAction extends BaseAction {

	@Resource
	private LocationService locationService;
	@Resource
	private StorageService storageService;
	@Resource
	private StorageBinService storageBinService;
	@Resource
	private WmsFormService wmsFormService;
	@Resource
	private WmsFormDetailService wmsFormDetailService;
	@Resource
	private MaterialService materialService;
	@Resource
	private WmsInventoryBinService inventoryBinService;
	@Resource
	private WmsInventoryService inventoryService;
	
	
	private List<String> storagelist;
	private List<String> storagebinlist;
	
	private WmsLocation location;
	//private WmsStorage storage;
	private WmsForm form;
	private WmsFormDetail formdetail;
	private WmsStorageBin storageBin;
	
	

	
	



	// 列表页面
	public String listUI() throws Exception {
		
		// 加载地域列表
		ActionContext.getContext().getContextMap()
						.put("locationlist", locationService.findObjects());
		// 加载产品列表
		ActionContext.getContext().getContextMap()
								.put("materiallist", materialService.findObjects());
		
		return "listUI";

	}
	
	


	//保存单据和单据明细表丶仓位库存表和仓库库存表
	public String inStorage() throws ParseException
	{
		//单据表
		form.setType("0");
		
		//得到业务发生时间
		String time=ServletActionContext.getRequest().getParameter("time");
		form.setOptime(new Timestamp(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm").getTime()));
		form.setCreateDate(new Timestamp(new Date().getTime()));
		//保存单据表
		wmsFormService.save(form);
		
		
		//单据详细表
		formdetail.setFormId(form.getId());
		WmsMaterial material=materialService.findObjectById(formdetail.getMaterialId());
		
		formdetail.setMaterialName(material.getName());
	
		//根据仓位名和仓库名查找ID
		List<WmsStorageBin> binlist=storageBinService.findObjects(formdetail.getInstorageBinName(),form.getInStorage());
		WmsStorageBin sb=binlist.get(0);
		/*for(WmsStorageBin ws:binlist)
		{
			System.out.println(ws.getId()+":"+ws.getName()+":"+ws.getStoreName());
		}*/
		
		//设置仓位id
		formdetail.setInstorageBinCode(sb.getId());
		//保存详细表
		wmsFormDetailService.save(formdetail);
		
		
		 //设置仓位库存表
		   //创建仓位库存对象
			WmsInventoryBin inventoryBin=new WmsInventoryBin();
			inventoryBin.setMaterialName(formdetail.getMaterialName());	
			inventoryBin.setStorageBinName(formdetail.getInstorageBinName());
			inventoryBin.setStorageName(form.getInStorage());
			QueryHelper queryHelper=new QueryHelper(WmsInventoryBin.class,"wib");
			queryHelper.addCondition("wib.storageName=?", form.getInStorage());
			queryHelper.addCondition("wib.storageBinName=?", formdetail.getInstorageBinName());
			queryHelper.addCondition("wib.materialName=?", formdetail.getMaterialName());
			List<WmsInventoryBin> inventorybins=inventoryBinService.findObjects(queryHelper);
			if(inventorybins.size()>0)
			{
			  WmsInventoryBin inventorybin=inventorybins.get(0);
			  int quantity=formdetail.getQuantity()+inventorybin.getQuantity();
			  inventorybin.setQuantity(quantity);
			  inventoryBinService.update(inventorybin);
			}
			else{
			inventoryBin.setQuantity(formdetail.getQuantity());
			inventoryBinService.save(inventoryBin);
			}
			
			
			//设置仓库库存表
			WmsInventory inventory=new WmsInventory();
			
			inventory.setStorageName(form.getInStorage());
			inventory.setMaterialName(formdetail.getMaterialName());
			
			
			QueryHelper query=new QueryHelper(WmsInventory.class, "wi");
			query.addCondition("wi.storageName=?", form.getInStorage());
			query.addCondition("wi.materialName=?", formdetail.getMaterialName());
			List<WmsInventory> inventorys=inventoryService.findObjects(query);
			
			if(inventorys.size()>0)
			{
				WmsInventory in=inventorys.get(0);
				int  count=in.getQuantity()+formdetail.getQuantity();
				
			  in.setQuantity(count);
			 inventoryService.update(in);
			}
			else{
				
				 inventory.setQuantity(formdetail.getQuantity());
				 inventoryService.save(inventory);
				
			}
			
			
			
		
		return "inStorage";
		
	}
	
	
	/**
	 * 根据地域查询仓库列表
	 * 
	 */
	public String findStorageByLocation()
	{
		
		storagelist=storageService.findStorageByAddess(location.getName());
		
		//System.out.println(storagelist);
		//System.out.println("����");
		//System.out.println(location.getName());
		return "json";
		
	}
	
	/**
	 * 根据仓库查询仓位列表
	 * 
	 */
	
	public String findBinByStorage()
	{
		
		storagebinlist=storageBinService.findBinByStorageName(storageBin.getStoreName());
		
		//System.out.println(storagelist);
		//System.out.println(storageBin.getStoreName());
		//System.out.println(location.getName()+"::");
		return "json";
	}
	
	
	
	public List<String> getStoragelist() {
		return storagelist;//返回json格式
	}

	public void setStoragelist(List<String> storagelist) {
		this.storagelist = storagelist;
	}

	public List<String> getStoragebinlist() {
		return storagebinlist;//返回json格式
	}

	public void setStoragebinlist(List<String> storagebinlist) {
		this.storagebinlist = storagebinlist;
	}






	public WmsLocation getLocation() {
		return location;
	}

	public void setLocation(WmsLocation location) {
		this.location = location;
	}


	public WmsStorageBin getStorageBin() {
		return storageBin;
	}

	public void setStorageBin(WmsStorageBin storageBin) {
		this.storageBin = storageBin;
	}

	public WmsForm getForm() {
		return form;
	}

	public void setForm(WmsForm form) {
		this.form = form;
	}



	public WmsFormDetail getFormdetail() {
		return formdetail;
	}

	public void setFormdetail(WmsFormDetail formdetail) {
		this.formdetail = formdetail;
	}
	
	
	
	
	
	
	
	
}