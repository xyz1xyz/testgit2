package cn.itcast.outstorage.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.location.service.LocationService;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.material.service.MaterialService;
import cn.itcast.wms.storage.service.StorageService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;
import cn.itcast.wms.storagebin.service.StorageBinService;

public class OutStorageAction extends BaseAction{

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
	//产品列表
	private List<String> materials;
	private WmsLocation location;
	private WmsStorageBin storageBin;
	
	private WmsForm form;
	private WmsFormDetail formdetail;
	private int quantity;

	
	
	
	private WmsInventoryBin inventorybin;
	
	
	  // 列表页面
		public String listUI() throws Exception {
			
			// 加载地域列表
			ActionContext.getContext().getContextMap()
							.put("locationlist", locationService.findObjects());
			// 加载产品列表
			//ActionContext.getContext().getContextMap().put("materiallist", materialService.findObjects());
			
			return "listUI";

		}
		//保存单据和单据明细表和仓位库存和仓库库存
		public String outStorage() throws ParseException
		{
			
			
			
			 //仓位库存表
			QueryHelper queryHelper=new QueryHelper(WmsInventoryBin.class,"wi");
			queryHelper.addCondition("wi.storageName=?", form.getOutStorage());
			queryHelper.addCondition("wi.storageBinName=?", formdetail.getOutstorageBinName());
			queryHelper.addCondition("wi.materialName=?", formdetail.getMaterialName());
			List<WmsInventoryBin> inventorybins=inventoryBinService.findObjects(queryHelper);
			inventorybin=inventorybins.get(0);
			
			quantity=inventorybin.getQuantity()-formdetail.getQuantity();
			inventorybin.setQuantity(quantity);
			
			inventoryBinService.update(inventorybin);
				
			//仓库库存表
			QueryHelper query=new QueryHelper(WmsInventory.class, "wi");
			query.addCondition("wi.storageName=?", form.getOutStorage());
			query.addCondition("wi.materialName=?", formdetail.getMaterialName());
			List<WmsInventory> inventorys=inventoryService.findObjects(query);
			WmsInventory in=inventorys.get(0);
			int  count=in.getQuantity()-formdetail.getQuantity();
			 in.setQuantity(count);
			inventoryService.update(in);
			
			
			//单据表
				form.setType("1");
				
				//得到业务发生时间
				String time=ServletActionContext.getRequest().getParameter("time");
				form.setOptime(new Timestamp(DateUtils.parseDate(time, "yyyy-MM-dd HH:mm").getTime()));
				form.setCreateDate(new Timestamp(new Date().getTime()));
				//保存单据表
				wmsFormService.save(form);
				
				
				//单据明细表
				formdetail.setFormId(form.getId());
				
				
				
			
				//根据仓位名和仓库名查找仓位ID
				List<WmsStorageBin> binlist=storageBinService.findObjects(formdetail.getOutstorageBinName(),form.getOutStorage());
				WmsStorageBin sb=binlist.get(0);
				/*for(WmsStorageBin ws:binlist)
				{
					System.out.println(ws.getId()+":"+ws.getName()+":"+ws.getStoreName());
				}*/
				
				//设置仓位id
				formdetail.setInstorageBinCode(sb.getId());
				//保存详细表
				wmsFormDetailService.save(formdetail);
			
			
			return "outStorage";
			
		}
		//查看库存产品数量
		public void doCheck()
		{
          try {
				
			
					QueryHelper queryHelper=new QueryHelper(WmsInventoryBin.class,"wi");
					
					queryHelper.addCondition("wi.storageName=?", form.getOutStorage());
					queryHelper.addCondition("wi.storageBinName=?", formdetail.getOutstorageBinName());
					queryHelper.addCondition("wi.materialName=?", formdetail.getMaterialName());
					List<WmsInventoryBin> inventorybins=inventoryBinService.findObjects(queryHelper);
				
					if(inventorybins.size()>0)
					{
						
						 inventorybin=inventorybins.get(0);
					
						
						 
						    
							//String result1="false1";
							int amount=inventorybin.getQuantity();
							
							String amounts=amount+"";
							//输出
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setContentType("text/html");
							ServletOutputStream outputStream = response.getOutputStream();
							outputStream.write(amounts.getBytes());	
							//outputStream.write(quantity);
							outputStream.close();
						
						 

					  }else{
						  String result="false";
						//输出
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setContentType("text/html");
							ServletOutputStream outputStream = response.getOutputStream();
							outputStream.write(result.getBytes());
							outputStream.close();
						  
					  }
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//查看有没有库存
		public void doVerify(){
			try {
				
				if(formdetail.getQuantity()!=null)
				{
					
					QueryHelper queryHelper=new QueryHelper(WmsInventoryBin.class,"wi");
					
					queryHelper.addCondition("wi.storageName=?", form.getOutStorage());
					queryHelper.addCondition("wi.storageBinName=?", formdetail.getOutstorageBinName());
					queryHelper.addCondition("wi.materialName=?", formdetail.getMaterialName());
					List<WmsInventoryBin> inventorybins=inventoryBinService.findObjects(queryHelper);
				
					if(inventorybins.size()>0)
					{
						
						 inventorybin=inventorybins.get(0);
						  quantity=inventorybin.getQuantity()-formdetail.getQuantity();
						
						  if(quantity<0)
						  {
							  //返回客户端，该产品在这个仓位没有库存,返回"false"
							String result1="false1";
							
							//输出
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setContentType("text/html");
							ServletOutputStream outputStream = response.getOutputStream();
							outputStream.write(result1.getBytes());	
							
							outputStream.close();
						  }
						 

					  }else
					  {
						
						//返回客户端，返回"false2"，出库数量超过库存数量
						 String result2="false2";
						//输出
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setContentType("text/html");
						ServletOutputStream outputStream = response.getOutputStream();
						outputStream.write(result2.getBytes());
						outputStream.close();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		return "json";
	}
	/**
	 * 根据仓库和仓位查询库存产品列表，看是否有库存
	 * @return 
	 * 
	 */
	public String findMaterials()
	{
		
		materials=inventoryBinService.findMaterials(inventorybin.getStorageName(), inventorybin.getStorageBinName());
		return "json";
	}
	public List<String> getStoragelist() {
		return storagelist;
	}
	public void setStoragelist(List<String> storagelist) {
		this.storagelist = storagelist;
	}
	public List<String> getStoragebinlist() {
		return storagebinlist;
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
	public List<String> getMaterials() {
		return materials;
	}
	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}
	public WmsInventoryBin getInventorybin() {
		return inventorybin;
	}
	public void setInventorybin(WmsInventoryBin inventorybin) {
		this.inventorybin = inventorybin;
	}
	
	
	
}
