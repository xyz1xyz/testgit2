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
	private WmsStorageBin storageBin;

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
		if (selectedRow != null) {
			for (String id : selectedRow) {
				storageBinService.delete(id);
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

	public WmsStorageBin getStorageBin() {
		return storageBin;
	}

	public void setStorageBin(WmsStorageBin storageBin) {
		this.storageBin = storageBin;
	}

}
