package cn.itcast.wms.storagebin.action;

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
import cn.itcast.instorage.entity.WmsFormDetail;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.instorage.entity.WmsInventoryBin;
import cn.itcast.instorage.service.WmsFormDetailService;
import cn.itcast.instorage.service.WmsInventoryBinService;
import cn.itcast.instorage.service.WmsInventoryService;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;
import cn.itcast.wms.storagebin.service.StorageBinService;

public class StorageBinAction extends BaseAction {

	@Resource
	private StorageBinService storageBinService;
	@Resource
	private StorageService storageService;
	@Resource
	private WmsFormDetailService formDetailService;
	@Resource
	private WmsInventoryBinService inventoryBinService;
	private WmsFormDetail formDetail;
	private WmsStorageBin storageBin;
	private WmsForm form;
	//用来批量删除数据
	private String[] selected=new String[6];
	// 列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(WmsStorageBin.class, "e");
		try {
			if (storageBin != null) {
				if (StringUtils.isNotBlank(storageBin.getName())) {

					queryHelper.addCondition("e.name like ?",
							"%" + storageBin.getName() + "%");
				}
				if (StringUtils.isNotBlank(storageBin.getStoreName())) {

					queryHelper.addCondition("e.storeName like ?",
							"%" + storageBin.getStoreName() + "%");
				}

			}
			pageResult = storageBinService.getPageResult(queryHelper,
					getPageNo(), getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";

	}

	// 新增页面
	public String addUI() {
		// 加载仓库列表
		ActionContext.getContext().getContextMap()
				.put("storagelist", storageService.findObjects());

		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (storageBin != null) {

				WmsStorage ws=storageService.findObjectById(storageBin.getStoreId());
				storageBin.setStoreName(ws.getName());
				storageBinService.save(storageBin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 编辑页面
	public String editUI() {
		// 加载仓库列表
		ActionContext.getContext().getContextMap()
				.put("storagelist", storageService.findObjects());
		if (storageBin != null && storageBin.getId() != null) {

			storageBin = storageBinService.findObjectById(storageBin.getId());

		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if (storageBin != null) {

				 //修改仓位库存表的仓库名
                QueryHelper query2=new QueryHelper(WmsInventoryBin.class, "wsb");
                query2.addCondition("wsb.storeBinId=?", storageBin.getId());
                List<WmsInventoryBin> wibs=inventoryBinService.findObjects(query2);
                for(WmsInventoryBin wib:wibs)
                {
              	  wib.setStorageBinName(storageBin.getName());
              	  inventoryBinService.update(wib);
              	  
                }
                
              
				storageBinService.update(storageBin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (storageBin != null && storageBin.getId() != null) {
			storageBinService.delete(storageBin.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		try {
			   for(int j=0;j<selectedRow.length;j++)
			   {
				   QueryHelper query=new QueryHelper(WmsStorageBin.class, "wsb");
				   WmsStorageBin storagebin=storageBinService.findObjectById(selectedRow[j]);
				   QueryHelper query2=new QueryHelper(WmsInventoryBin.class,"wib");
				   query2.addCondition("wib.storageBinName=?",storagebin.getName() );
				   query2.addCondition("wib.storageName=?",storagebin.getStoreName());
				   
				   List<WmsInventoryBin> list=inventoryBinService.findObjects(query2);
				  
				   if( list!=null && list.size()>0)
					{
						for(int i=0;i<list.size();i++)
						{
							WmsInventoryBin wib=list.get(i);
							if(wib.getQuantity()>0)
							{
								
								  break;//跳出当前for循环 
							}
							
							if(i==list.size())
								selected[j]=selectedRow[j];//selected[]是可以删除的数据
								
						}
						
						
					}
					else{
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
					storageBinService.delete(id);
				}
			}
		}
		return "list";
	}
	

	//校验仓位
	public void verify(){
		try {
			//获取仓库名和仓位名
			if(storageBin != null && StringUtils.isNotBlank(storageBin.getName())){
				//根据仓库和仓位查询
				QueryHelper query=new QueryHelper(WmsStorageBin.class,"wl");
				
				query.addCondition("wl.name=?", storageBin.getName());
				query.addCondition("wl.storeName=?", storageBin.getStoreName());
				List<WmsStorageBin> list=storageBinService.findObjects(query);
				
				String strResult = "true";
				if(list != null && list.size() > 0){
					//说明该仓库下的该仓位号已经存在
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
	//检验仓位名是否放了货物，若放不给删除。
	public void verifyStorageBin()
	{
		//思路：根据id查询仓位名，再到单据明细表查询仓位名是否使用
		//思路2(正确):到仓位库存表查看仓位，仓库名和数量。数量为0可以删除
		try {
			QueryHelper query=new QueryHelper(WmsStorageBin.class,"wsb");
			//query.addCondition("wl.name=?", wmsLocation.getId());
			//根据id查询仓位名
			WmsStorageBin sb=storageBinService.findObjectById(storageBin.getId());
			/*//到单据表和单据明细表查询仓位名和仓库名是否已经使用
			QueryHelper query2=new QueryHelper(WmsFormDetail.class, "wfd");
			query2.addTable(WmsForm.class, "wf");
			query2.addSeletObject("inStorage","instorageBinName");
			query2.addCondition("wfd.instorageBinName=?", sb.getName());
			query2.addCondition("wf.inStorage=?", sb.getStoreName());
		
			List<WmsStorageBin> list=storageBinService.findObjectsTwo(query2);*/
			QueryHelper query2=new QueryHelper(WmsInventoryBin.class,"wib");
			query2.addCondition("wib.storageName=?", sb.getStoreName());
			query2.addCondition("wib.storageBinName=?", sb.getName());
			List<WmsInventoryBin> list=inventoryBinService.findObjects(query2);
			String strResult="flase";
			if(list!=null && list.size()>0)
			{
				for(int i=0;i<list.size();i++)
				{
					WmsInventoryBin win=list.get(i);
					if(win.getQuantity()>0)
					{
						  strResult="flase";
						  break;//跳出for循环 
					}
					else
					{
						strResult="true";
						
					}	
				}	
			}
			else{
				strResult="true";
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

	public WmsStorageBin getStorageBin() {
		return storageBin;
	}

	public void setStorageBin(WmsStorageBin storageBin) {
		this.storageBin = storageBin;
	}

	public WmsFormDetail getFormDetail() {
		return formDetail;
	}

	public void setFormDetail(WmsFormDetail formDetail) {
		this.formDetail = formDetail;
	}

	public WmsForm getForm() {
		return form;
	}

	public void setForm(WmsForm form) {
		this.form = form;
	}
	

}
