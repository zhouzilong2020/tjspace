package codegenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * 代码生成器
 *
 * @since 2018/12/13
 */
public class CodeGenerator {


    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");// 得到当前路径
        gc.setOutputDir(projectPath + "/src/main/java/");    // 输出目录

        gc.setAuthor("Zilong Zhou");
        gc.setOpen(false);                  //生成后是否打开资源管理器
        gc.setFileOverride(false);          //重新生成时文件是否覆盖


        gc.setServiceName("%sService");     //去掉Service接口的首字母I（IService-->Service)


        gc.setIdType(IdType.ID_WORKER_STR);     //主键策略，mybatis来生成的随机19位的唯一，varChar则加一个str
        gc.setDateType(DateType.ONLY_DATE); //定义生成的实体类中日期类型
        gc.setSwagger2(true);               //开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置,无法使用到application
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.224.53.131:3306/tjspace?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        // com.tjspace.infoservice
        pc.setParent("com.tjspace");        // 包名
        pc.setModuleName("ucenterservice");     //模块名
        // com.tjspace.infoservice.com.tjspace.msmservice.controller/com.tjspace.msmservice.entity...
        pc.setController("com.tjspace.msmservice.controller");
        pc.setEntity("com.tjspace.msmservice.entity");
        pc.setService("com.tjspace.msmservice.service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置(逆向工程)
        StrategyConfig strategy = new StrategyConfig();

        // 改表的名称，可以有多个表
//        info :: ("info_course", "info_major", "info_section", "info_teacher", "info_user");
        strategy.setInclude("info_user"); // 表的名称，逆向生成！

        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略：数据库a_a --> aA
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
