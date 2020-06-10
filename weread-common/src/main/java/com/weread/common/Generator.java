package com.weread.common;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成
 * 
 * @author ShenHuaJie
 */
public class Generator {
	/**
	 * 测试 run 执行 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("d:\\codegen");
		gc.setFileOverride(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setOpen(false);
		gc.setAuthor("Chenk");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceImplName("%sServiceImpl");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
//		dsc.setDbType(DbType.ORACLE);
//		dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
//		dsc.setUsername("yst_manager");
//		dsc.setPassword("yst_manager");
//		dsc.setUrl("jdbc:oracle:thin:@10.213.40.31:1521:mobile");
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/readworld?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setTablePrefix("sys_");// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(new String[] { "read_book","sys_user"}); // 需要生成的表
//		strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
//		 strategy.setExclude(new String[]{"sys_role_menu","sys_role_user"}); // 排除生成的表
		// 自定义实体父类
		strategy.setSuperEntityClass("com.weread.service.base.BaseEntity");
		// 自定义实体，公共字段
//		strategy.setSuperEntityColumns(
//				new String[] { "del_flag","remarks", "create_by", "create_date", "update_by", "update_date" });
		// 自定义 mapper 父类
//		strategy.setSuperMapperClass("org.ibase4j.core.base.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.weread.service.base.IBaseService");
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass("com.weread.service.base.BaseService");
		// 自定义 controller 父类
//		strategy.setSuperControllerClass("org.ibase4j.core.base.AbstractController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.weread.sys");
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setXml("mapper.xml");
		pc.setServiceImpl("service.impl");
		pc.setService("service");
//		pc.setController("web");
		mpg.setPackageInfo(pc);
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
//		InjectionConfig cfg = new InjectionConfig() {
//			public void initMap() {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("providerClass", "ISysProvider");
//				map.put("providerClassPackage", "org.ibase4j.provider.ISysProvider");
//				this.setMap(map);
//			}
//		};
//		mpg.setCfg(cfg);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("tpl/entity.java.vm");
		tc.setMapper("tpl/mapper.java.vm");
		tc.setXml("tpl/mapper.xml.vm");
		tc.setService("tpl/service.java.vm");
		tc.setController("tpl/controller.java.vm");
//		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
	}
}
