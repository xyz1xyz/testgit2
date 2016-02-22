package cn.itcast.wms.sn.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;

import cn.itcast.wms.location.service.LocationService;
import cn.itcast.wms.material.service.MaterialService;
import cn.itcast.wms.sn.entity.WmsSn;
import cn.itcast.wms.sn.service.SnService;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;

public class SnAction extends BaseAction {

	@Resource
	private SnService snService;
	@Resource
	private MaterialService materialService;
	@Resource
	private LocationService locationService;

	private WmsSn sn;

	//列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(WmsSn.class, "e");
		try {
			if (sn != null) {
				if (StringUtils.isNotBlank(sn.getMaterialName())) {

					queryHelper.addCondition("e.materialName like ?",
							"%" + sn.getMaterialName() + "%");
				}
				if (StringUtils.isNotBlank(sn.getAddressName())) {

					queryHelper.addCondition("e.addressName like ?",
							"%" + sn.getAddressName() + "%");
				}
				
				if (StringUtils.isNotBlank(sn.getSn())) {

					queryHelper.addCondition("e.sn like ?",
							"%" + sn.getSn() + "%");
				}

			}
			pageResult = snService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";

	}

	// 新增页面
	public String addUI() {
		// 加载货物列表
		ActionContext.getContext().getContextMap()
				.put("materiallist", materialService.findObjects());
		// 加载地域列表
		ActionContext.getContext().getContextMap()
				.put("locationlist", locationService.findObjects());
		
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (sn != null) {

				snService.save(sn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 编辑页面
	public String editUI() {
		// 加载货物
		ActionContext.getContext().getContextMap()
				.put("materiallist", materialService.findObjects());
		//加载地域
		ActionContext.getContext().getContextMap()
				.put("locationlist", locationService.findObjects());
		
		if (sn != null && sn.getId() != null) {

			sn = snService.findObjectById(sn.getId());

		}
		return "editUI";
	}

	//保存编辑
	public String edit() {

		try {
			if (sn != null) {

				snService.update(sn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (sn != null && sn.getId() != null) {
			snService.delete(sn.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				snService.delete(id);
			}
		}
		return "list";
	}
	//校验地域和货物
		public void verify(){
			try {
			
				if(sn != null){
					
					QueryHelper query=new QueryHelper(WmsSn.class,"wl");
					query.addCondition("wl.addressName=?",sn.getAddressName() );
					
					query.addCondition("wl.materialName=?", sn.getMaterialName());
					
					List<WmsSn> list=snService.findObjects(query);
					
					String result = "true";
					if(list != null && list.size() > 0){
						//货物和地域已经存在
						result = "false";
					}
					
					
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write(result.getBytes());
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//校验二维码
		public void verifySN(){
			try {
				//获取二维码
				if(sn != null && StringUtils.isNoneBlank(sn.getSn())){
					//2根据获取的二维码查询
					QueryHelper query=new QueryHelper(WmsSn.class,"wl");
					
					query.addCondition("wl.sn=?", sn.getSn());
					List<WmsSn> list=snService.findObjects(query);
					
					String strResult = "true";
					if(list != null && list.size() > 0){
						//说明二维码已经存在
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

	public WmsSn getSn() {
		return sn;
	}

	public void setSn(WmsSn sn) {
		this.sn = sn;
	}

}
