package cn.itcast.inventory.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.instorage.service.WmsInventoryService;

public class InventoryAction extends BaseAction{

	@Resource
	private WmsInventoryService inventoryService;

	private WmsInventory inventory;
	// 仓库库存展示
		public String listUI() throws Exception {
			QueryHelper queryHelper = new QueryHelper(WmsInventory.class, "e");
			queryHelper.addCondition("e.quantity>?", 0);
			try {
				if (inventory != null) {
					if (StringUtils.isNotBlank(inventory.getStorageName())) {

						queryHelper.addCondition("e.storageName like ?", "%"
								+ inventory.getStorageName() + "%");
					}
					
					if (StringUtils.isNotBlank(inventory.getMaterialName())) {

						queryHelper.addCondition("e.materialName like ?", "%"
								+ inventory.getMaterialName() + "%");
					}
					

				}
				pageResult = inventoryService.getPageResult(queryHelper,
						getPageNo(), getPageSize());

			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}
		public WmsInventory getInventory() {
			return inventory;
		}
		public void setInventory(WmsInventory inventory) {
			this.inventory = inventory;
		}
		
		
}
