package cn.itcast.wms.material.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.instorage.entity.WmsInventoryBin;
import cn.itcast.instorage.service.WmsInventoryService;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.material.service.MaterialService;

public class MaterialAction extends BaseAction {

	@Resource
	private MaterialService materialService;
	@Resource
	private WmsInventoryService inventoryService;
	private WmsMaterial material;
	private String[] selected=new String[6];
	

	// 列表页面
		public String listUI() throws Exception {
			QueryHelper queryHelper = new QueryHelper(WmsMaterial.class, "e");
			try {
				if (material != null) {
					if (StringUtils.isNotBlank(material.getName())) {
						
						queryHelper.addCondition("e.name like ?", "%" + material.getName() + "%");
					}

				}
				pageResult = materialService.getPageResult(queryHelper, getPageNo(), getPageSize());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}

		//转到新增页面
		public String addUI(){
			
			return "addUI";
		}
		//保存新增
		public String add(){
			try {
				if(material != null){
				
					materialService.save(material);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//编辑页面
		public String editUI(){
			
			if (material != null && material.getId() != null) {
			
				material = materialService.findObjectById(material.getId());
				
			}
			return "editUI";
		}
		//保持编辑
		public String edit(){
			try {
				if(material != null){
					

					materialService.update(material);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//删除
		public String delete(){
			if(material != null && material.getId() != null){
				materialService.delete(material.getId());
			}
			return "list";
		}
		//批量删除
		//id是别名
		//selectedRow是一个数组，装的是id
		public String deleteSelected(){
			try {
				   for(int j=0;j<selectedRow.length;j++)
				   {
					   
				       
					   QueryHelper query=new QueryHelper(WmsMaterial.class, "wm");
					   WmsMaterial materials=materialService.findObjectById(selectedRow[j]);
					   QueryHelper query2=new QueryHelper(WmsInventory.class,"wi");
					   query2.addCondition("wi.materialName=?",materials.getName() );
					   List<WmsInventory> list=inventoryService.findObjects(query2);
					  
						if(list!=null && list.size()>0)
						{
							for(int i=0;i<list.size();i++)
							{
								WmsInventory wi=list.get(i);
								if(wi.getQuantity()>0)
								{
									//System.out.println(""+selectedRow[j]);
									//System.out.println(""+j);
									//selected[j]=selectedRow[j];
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
					materialService.delete(id);
					}
				}
			}
			return "list";
		}
		

		//校验
		public void verify(){
			try {
				//获取数据
				if(material != null && StringUtils.isNotBlank(material.getName())){
					//2查询
					QueryHelper query=new QueryHelper(WmsMaterial.class,"wl");
					query.addCondition("wl.name=?", material.getName());
					List<WmsMaterial> list=materialService.findObjects(query);
					
					String strResult = "true";
					if(list != null && list.size() > 0){
						//已经存在
						strResult = "false";
					}
					
					//查询
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
		//删除货物检验
		/**
		 * 思路：到仓库仓库表查询是否有数据，和数据是否为0
		 */
		/*public void verifyMaterial()
		{
		   try {
			   QueryHelper query=new QueryHelper(WmsMaterial.class, "wm");
			   WmsMaterial materials=materialService.findObjectById(material.getId());
			   QueryHelper query2=new QueryHelper(WmsInventory.class,"wi");
			   query2.addCondition("wi.materialName=?",materials.getName() );
			   List<WmsInventory> list=inventoryService.findObjects(query2);
			   String strResult="flase";
				if(list!=null && list.size()>0)
				{
					for(int i=0;i<list.size();i++)
					{
						WmsInventory wi=list.get(i);
						if(wi.getQuantity()>0)
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
		*/
		/**
		 * 批量删除检验
		 * 思路：在verifyMaterial（）方法上添加一个for循环，查到有哪一个被使用就不删除，没有被查到就删除
		 * 
		 */
		public void verifydeleteSelected()
		{
			 //String[] selected = new String[5];
			 try {
				   for(int j=0;j<selectedRow.length;j++)
				   {
					   
				       
					   QueryHelper query=new QueryHelper(WmsMaterial.class, "wm");
					   WmsMaterial materials=materialService.findObjectById(selectedRow[j]);
					   QueryHelper query2=new QueryHelper(WmsInventory.class,"wi");
					   query2.addCondition("wi.materialName=?",materials.getName() );
					   List<WmsInventory> list=inventoryService.findObjects(query2);
					  
						if(list!=null && list.size()>0)
						{
							for(int i=0;i<list.size();i++)
							{
								WmsInventory wi=list.get(i);
								if(wi.getQuantity()>0)
								{
									System.out.println(""+selectedRow[j]);
									System.out.println(""+j);
									//selected[j]=selectedRow[j];
									  break;//跳出当前for循环 
								}
								
								selected[j]=selectedRow[j];
							}
							
							
						}
						else{
							selected[j]=selectedRow[j];//selected[]是可以删除的数据
						}
						
						
				   }
				   /*for(int i=0;i<selected.length;i++)
				   {
					   System.out.println(selected[i]);
				   }*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	public WmsMaterial getMaterial() {
		return material;
	}
	public void setMaterial(WmsMaterial material) {
		this.material = material;
	}


	
	
}
