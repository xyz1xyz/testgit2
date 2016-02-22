package cn.itcast.inventorybin.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.entity.WmsInventoryBin;
import cn.itcast.instorage.service.WmsInventoryBinService;

public class InventoryBinAction extends BaseAction {

	@Resource
	private WmsInventoryBinService inventoryBinService;
	private WmsInventoryBin inventoryBin;

	// 仓位库存展示
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(WmsInventoryBin.class, "e");
		try {
			if (inventoryBin != null) {
				if (StringUtils.isNotBlank(inventoryBin.getStorageName())) {

					queryHelper.addCondition("e.storageName like ?", "%"
							+ inventoryBin.getStorageName() + "%");
				}
				if (StringUtils.isNotBlank(inventoryBin.getStorageBinName())) {

					queryHelper.addCondition("e.storageBinName like ?", "%"
							+ inventoryBin.getStorageBinName() + "%");
				}
				if (StringUtils.isNotBlank(inventoryBin.getMaterialName())) {

					queryHelper.addCondition("e.materialName like ?", "%"
							+ inventoryBin.getMaterialName() + "%");
				}

			}
			pageResult = inventoryBinService.getPageResult(queryHelper,
					getPageNo(), getPageSize());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";

	}

	public WmsInventoryBin getInventoryBin() {
		return inventoryBin;
	}

	public void setInventoryBin(WmsInventoryBin inventoryBin) {
		this.inventoryBin = inventoryBin;
	}

}
