package cn.itcast.instorage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.entity.WmsForm;
import cn.itcast.instorage.entity.WmsFormDetail;
import cn.itcast.instorage.service.WmsFormDetailService;
import cn.itcast.instorage.service.WmsFormService;

public class FormAction extends  BaseAction{

	@Resource
	private WmsFormService wmsFormService;
	@Resource
	private WmsFormDetailService wmsFormDetailService;
	private String formId;
	private WmsForm form;
	private String startTime;
	private String endTime;
	//单据表展示
	public String formUI() throws Exception
	{
		// 加载单据类型集合
				ActionContext.getContext().getContextMap()
								.put("FORM_TYPE_MAP",WmsForm.FORM_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(WmsForm.class, "w");
		queryHelper.addCondition("w.type=?", "0");
		try {
			if(form!=null)
			{
				if(StringUtils.isNotBlank(form.getInStorage()))
				{
					queryHelper.addCondition("w.inStorage like ?", "%" + form.getInStorage() + "%");
					
				}
				if(StringUtils.isNotBlank(form.getOperator()))
				{
					queryHelper.addCondition("w.operator like ?", "%" + form.getOperator() + "%");
					
				}
				if(StringUtils.isNotBlank(form.getId()))
				{
					queryHelper.addCondition("w.id like ?", "%" + form.getId() + "%");
				}
				
			}
			if(StringUtils.isNotBlank(startTime))
			{
				queryHelper.addCondition("w.createDate>=?", DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm"));
			}
			if(StringUtils.isNotBlank(endTime))
			{
				queryHelper.addCondition("w.createDate<=?", DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm"));
			}
			
			
			//按照创建时间降序排序
			queryHelper.addOrderByProperty("w.createDate", QueryHelper.ORDER_BY_DESC);
			
			pageResult = wmsFormService.getPageResult(queryHelper, getPageNo(), getPageSize());
		   
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "formUI";
		
	}
	//单据明细表展示
	public String formDetailUI()
	{
		//WmsFormDetail formdtail=wmsFormDetailService.findObjectById(formId);
		QueryHelper queryhelper=new QueryHelper(WmsFormDetail.class,"ws");
		queryhelper.addCondition("ws.formId=?", formId);
		List<WmsFormDetail> formdtail=wmsFormDetailService.findObjects(queryhelper);
		
		ActionContext.getContext().getContextMap().put("formdtail", formdtail);
		return "formDetailUI";
		
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public WmsForm getForm() {
		return form;
	}
	public void setForm(WmsForm form) {
		this.form = form;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
