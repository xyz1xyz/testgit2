package cn.itcast.wms.material.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.material.service.MaterialService;

public class MaterialAction extends BaseAction {

	@Resource
	private MaterialService materialService;
	private WmsMaterial material;
	

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
		//删除
		public String deleteSelected(){
			if(selectedRow != null){
				for(String id: selectedRow){
					materialService.delete(id);
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
	public WmsMaterial getMaterial() {
		return material;
	}
	public void setMaterial(WmsMaterial material) {
		this.material = material;
	}
	
}
