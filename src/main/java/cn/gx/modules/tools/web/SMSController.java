/**
 * Copyright &copy; 2015-2020 <a href="http://www.bug.org/">JeePlus</a> All rights reserved.
 */
package cn.gx.modules.tools.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gx.common.sms.SMSUtils;
import cn.gx.common.web.BaseController;
import cn.gx.modules.sys.entity.SystemConfig;
import cn.gx.modules.sys.service.SystemConfigService;

/**
 * 发送短信
 * @author lgf
 * @version 2016-01-07
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/sms")
public class SMSController extends BaseController {

	@Autowired
	private SystemConfigService systemConfigService;
	
	/**
	 * 打开短信页面
	 */
	@RequestMapping(value = {"index", ""})
	public String index( HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/tools/sendSMS";
	}

	/**
	 * 发送短信
	 */
	@RequestMapping("send")
	public String send(String tels,  HttpServletResponse response, String content, Model model) throws Exception {
		SystemConfig config = systemConfigService.get("1");
		String result = "";
		try{
				String resultCode = SMSUtils.send(config.getSmsName(),config.getSmsPassword(), tels, content);
				if (!resultCode.equals("100")) {
					
					result = "短信发送失败，错误代码："+resultCode+"，请 联系管理员。";
				
				}else{
					result = "短信发送成功";
				}
			} catch (IOException e) {
				result = "因未知原因导致短信发送失败，请联系管理员。";
			}
			
			model.addAttribute("result", result);
			return "modules/tools/sendSMSResult";
	}
		
}