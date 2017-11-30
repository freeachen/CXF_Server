package cn.it.ws.cxf.a;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**开发语言排行描述服务
 * @author 
 */
public class LanguageServiceImpl implements LanguageService {
	/* (non-Javadoc)
	 * @see cn.it.ws.cxf.a.LanguageService#getLanguage(int)
	 */
	@Override
	public String getLanguage(int position){
		String language=null;
		switch (position) {
		case 1:
			language="java"; 
			break;
		case 2:
			language="C";
			break;
		case 3:
			language="Objective-C";
			break;  
		case 4:
			language="C#";
			break;

		default:
			break;
		}
		return language;
	}
	/**通过cxf框架发布webservice
	 *  1. ServerFactoryBean
	 *    - 不设置注解也可以发布webservice服务， 不支持注解
	 *    - 不支持拦截器的添加
	 *  2. JaxWsServerFactoryBean
	 *    - 支持注解
	 *    - 可以添加拦截器
	 *  3. webservice 访问流程：
	 *    1. 检测本地代理描述的wsdl是否与服务端的wsdl一致 ，俗称为握手
	 *    2. 通过soap协议实现通信 ，采用的是post请求 ， 数据封装在满足soap规约的xml中
	 *    3. 返回数据 同样采用的是soap通信，  数据封装在满足soap规约的xml中
	 * @param args
	*/
	public static void main(String[] args) {
		LanguageService languageService=new LanguageServiceImpl();
		
		//方式1 ServerFactoryBean不能对注解起作用
//		ServerFactoryBean bean=new ServerFactoryBean();
//		bean.setAddress("http://192.168.233.1:9999/ws/cxf/languangeService");
//		bean.setServiceClass(LanguageService.class);//对外提供webservcie的业务类或者接口
//		bean.setServiceBean(languageService);//服务的实现bean
//		bean.create();//创建，发布webservice
		System.out.println("wsdl地址:http://192.168.233.1:9999/ws/cxf/languangeService?WSDL");
		
		
		// 方式2:  JaxWsServerFactoryBean 能认识注解
		JaxWsServerFactoryBean bean=new JaxWsServerFactoryBean();
		bean.setAddress("http://192.168.233.1:8888/ws/cxf/languangeService");
		bean.setServiceClass(LanguageService.class);//对外提供webservcie的业务类或者接口
		bean.setServiceBean(languageService);//服务的实现bean
		bean.getInInterceptors().add(new LoggingInInterceptor()); //添加输入拦截器  :输入显示日志信息的拦截器
		bean.getOutInterceptors().add(new LoggingOutInterceptor()); //添加输出拦截器  :输出显示日志信息的拦截器
		bean.create();//创建，发布webservice
		
		
		System.out.println("wsdl地址: http://192.168.233.1:8888/ws/cxf/languangeService?WSDL");
	}

}
