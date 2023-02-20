package com.lin.garbagesorting.common;


import com.lin.garbagesorting.utils.StringUtil;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoCreateBean {
    //通用包名
    private static final String common_pkg = "com.lin.base";

    //  mysql 驱动类
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //  数据库登录用户名
    private static final String USER = "root";

    //  数据库登录密码
    private static final String PASSWORD = "123456";

    // 数据库连接地址
    private static final String URL = "jdbc:mysql://localhost:3306/garbage_sorting?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    private static String tablename;    //表明
    private static String tabledesc;    //表注释
    private static String objname;      //实体名

    private static String ignoreTablePrefix = "wrp_";//忽略表明前缀
    private static String selTablePrefix = "wrp_";//选择生成表明前缀
    private String[] colnames; // 列名数组

    private String[] colTypes; // 列名类型数组

    private String[] colComment; // 列名描述数组

    private String[] colDefault; // 列名默认值数组

    private Boolean[] colNull; // 列名是否必填数组

    private int[] colSizes; // 列名大小数组

    private boolean f_util = false; // 是否需要导入包java.util.*

    private boolean f_sql = false; // 是否需要导入包java.sql.*

    SimpleDateFormat aDate = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    /**
     * 获取指定数据库中包含的表 TBlist
     *
     * @return 返回所有表名(将表名放到一个集合中)
     * @throws Exception
     * @packageName com.util
     */
    public List<String> TBlist() throws Exception {
        // 访问数据库 采用 JDBC方式
        Class.forName(DRIVER);

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        DatabaseMetaData md = con.getMetaData();

        List<String> list = null;

        ResultSet rs = md.getTables(null, null, null, null);
        if (rs != null) {
            list = new ArrayList<String>();
        }

        while (rs.next()) {
//            System.out.println("|表" + (i++) + ":" + rs.getString("TABLE_NAME"));
            String tableName = rs.getString("TABLE_NAME");
            String tmp_tableName = tableName;
            if (StringUtil.isNotEmpty(selTablePrefix) && !tableName.startsWith(selTablePrefix)) {
                continue;
            }
            if (StringUtil.isNotEmpty(ignoreTablePrefix)) {
                tmp_tableName = tmp_tableName.replaceFirst(ignoreTablePrefix, "");
            }
            String[] tempTableName = tmp_tableName.split("_");
//            String [] tempTableName = tableName.substring(tableName.indexOf("_") + 1).split("_");
            String realName = "";
            for (String n : tempTableName) {
                realName += initcap(n);

            }
            list.add(realName + "|" + tableName);
        }
        rs = null;
        md = null;
        con = null;
        return list;
    }

    public void GenEntity(List<String> TBlist, String packageName) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSetMetaData rsmd = null;

        // 访问数据库 采用 JDBC方式
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        for (int k = 0; k < TBlist.size(); k++) {
            objname = TBlist.get(k).split("\\|")[0];
            tablename = TBlist.get(k).split("\\|")[1];
            String strsql = "select * from " + tablename;
            pstmt = conn.prepareStatement(strsql);
            rsmd = pstmt.getMetaData();
            int size = rsmd.getColumnCount();
            ResultSet rs = pstmt.executeQuery("show full columns from " + tablename);

            List<GenTargetDto> fullList = new ArrayList<>();
            while (rs.next()) {
                GenTargetDto genTargetDto = new GenTargetDto();
                genTargetDto.setColumnName(rs.getString("Field"));
                genTargetDto.setColumnType(rs.getString("Type"));
                genTargetDto.setColumnDefault(StringUtils.isEmpty(rs.getString("Default")) ? "" : rs.getString("Default"));
                genTargetDto.setColumnComment(rs.getString("Comment"));
                genTargetDto.setColumnNull(rs.getString("Null").equalsIgnoreCase("NO"));
                fullList.add(genTargetDto);
            }

            // 字段名
            colnames = new String[size];
            //字段类型
            colTypes = new String[size];
            //字段注释
            colComment = new String[size];
            //字段默认值
            colDefault = new String[size];
            //字段是否允许为空
            colNull = new Boolean[size];
            colSizes = new int[size];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colComment[i] = fullList.get(i).getColumnComment();
                colDefault[i] = fullList.get(i).getColumnDefault();
                colNull[i] = fullList.get(i).getColumnNull();
                String tn = rsmd.getColumnTypeName(i + 1);
                if (tn.equalsIgnoreCase("int unsigned"))
                    tn = "INT";
                else if (tn.equalsIgnoreCase("tinyint unsigned"))
                    tn = "INT";
                else if (tn.equalsIgnoreCase("decimal unsigned"))
                    tn = "DECIMAL";
                colTypes[i] = tn;
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("time") || colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image")
                        || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            ResultSet rs2 = pstmt.executeQuery("SHOW CREATE TABLE " + tablename);
            if (rs2 != null && rs2.next()) {
                String createDDL = rs2.getString(2);
                String comment = null;
                int index = createDDL.indexOf("COMMENT='");
                if (index < 0) {
                    tabledesc = "";
                } else {
                    comment = createDDL.substring(index + 9);
                    tabledesc = comment.substring(0, comment.length() - 1);
                }
            }
            markerBean(objname, parse(), packageName);
            markerMapper(objname, packageName);
            markerMapperXml(objname, packageName);
            // markerServer(objname, packageName);
            markerServerImpl(objname, packageName);
        }
        pstmt = null;
        rsmd = null;
        conn = null;
    }

    /**
     * 解析处理(生成实体类主体代码)
     */
    private String parse() {
        StringBuffer sb = new StringBuffer("\n");
        sb.append("import " + common_pkg + ".BaseDao;\n");
        sb.append("import io.swagger.annotations.ApiModel;\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;\n\n");
        sb.append("import javax.validation.constraints.*;\n");
        sb.append("import javax.persistence.Table;\n");
        sb.append("import javax.persistence.Column;\n");
        sb.append("import java.io.Serializable;\n");
        sb.append("import java.math.BigDecimal;\n");
        if (f_util) {
            sb.append("import java.util.Date;\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n\r\n\r\n");
        }
        sb.append("\n");
        sb.append("/**\n");
        sb.append(" * Created by LGeneratorins on " + aDate.format(new Date()) + "\n");
        sb.append(" */\n\n");
        sb.append("@ApiModel(value=\"" + tabledesc + "\")").append("\n");
        sb.append("@Table(name=\"" + tablename + "\")").append("\n");
//        sb.append("@ApiModel(value=\"user对象\",description=\"用户对象user\")");
        sb.append("public class " + objname + "Dao extends BaseDto implements Serializable {\r\n\n");
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n").append("\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("\t@Override\n");
        sb.append("\tpublic String toString() {\n");
        sb.append("\t\treturn \"" + objname + "{\" +\n");
        String toStr = "";
        for (String col : colnames) {
            String filed = col;
            if (filed.indexOf("_") > -1) {
                filed = toHump(filed);
            }
            toStr += "\t\t\t\t\"" + toLowerCaseFirstOne(filed) + "=\" + " + (filed.equalsIgnoreCase("id") ? "getId()" : toLowerCaseFirstOne(filed)) + " + " + "\", \" + \n";
        }
        toStr = toStr.substring(0, toStr.length() - 8);
        sb.append(toStr).append("\n");
        sb.append("\t\t\t\t'}';\n");
        sb.append("\t}\r\n");
        sb.append("}\r\n");

        return sb.toString();

    }

    /**
     * 创建java 文件 将生成的属性 get/set 方法 保存到 文件中 markerBean
     *
     * @param className 类名称
     * @param content   类内容 包括属性 getset 方法
     */
    public void markerBean(String className, String content, String packageName) {
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/dao/";

        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + "Dao.java";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write("package\t" + packageName.replace("/", ".") + ".dao;\r\n");
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成xml
     *
     * @param className
     * @param packageName
     */
    public void markerMapperXml(String className, String packageName) {
        StringBuffer content = new StringBuffer();
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        content.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DaD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
        content.append("<mapper namespace=\"" + packageName.replace("/", ".") + ".dao." + objname + "Mapper\" >\n");
        content.append("\t<resultMap id=\"" + objname + "ResultMap\" type=\"" + packageName.replace("/", ".") + ".dao." + objname + "Dao\" >\n");
        for (int i = 0; i < colnames.length; i++) {
            String tp = colTypes[i];
            if (tp.equalsIgnoreCase("INT"))
                tp = "INTEGER";
            else if (tp.equalsIgnoreCase("DATETIME"))
                tp = "DATE";
            content.append("\t\t<result column=\"" + colnames[i] + "\" property=\"" + toLowerCamelCase(colnames[i]) + "\" jdbcType=\"" + tp + "\" />\n");
        }
        content.append("\t</resultMap>\n");
        content.append("</mapper>");
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/mapper/";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + "Mapper.xml";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write(content.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成mapper接口
     *
     * @param className
     * @param packageName
     */
    public void markerMapper(String className, String packageName) {
        StringBuffer content = new StringBuffer();
        content.append("package\t" + packageName.replace("/", ".") + ".dao;\r\n\n");
        content.append("import " + common_pkg + ".IMapper;\n");
        content.append("import " + packageName.replace("/", ".") + ".dao." + objname + "Dao;\n");
        content.append("\n");
        content.append("/**\n");
        content.append(" * Created by LGeneratorins on " + aDate.format(new Date()) + "\n");
        content.append(" */\n\n");
        content.append("public interface " + objname + "Mapper extends IMapper<" + objname + "Dao> {\n\n");
        content.append("}\n");
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/dao/";

        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + "Mapper.java";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write(content.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成service
     *
     * @param className
     * @param packageName
     */
    public void markerServer(String className, String packageName) {
        StringBuffer content = new StringBuffer();
        content.append("package " + packageName.replace("/", ".") + ".service;\n\n");
        content.append("\n");
        content.append("/**\n");
        content.append(" * Created by LGeneratorins on " + aDate.format(new Date()) + "\n");
        content.append(" */\n\n");
        content.append("public interface " + objname + "Service {\n");
        content.append("\n");
        content.append("}\n\n");
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/service/";

        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + "Service.java";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write(content.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成service实现
     *
     * @param className
     * @param packageName
     */
    public void markerServerImpl(String className, String packageName) {
        StringBuffer content = new StringBuffer();
        String pkgeasy = packageName.replace("/", ".");
//        content.append("package " + pkgeasy + ".service.impl;\n\n");
        content.append("package " + pkgeasy + ".service;\n\n");
        content.append("import " + common_pkg + ".BaseService;\n");
        content.append("import  " + common_pkg + ".IMapper;\n");
        content.append("import " + pkgeasy + ".dao." + objname + "Dao;\n");
        content.append("import " + pkgeasy + ".dao." + objname + "Mapper;\n");
        // content.append("import " + pkgeasy + ".service." + objname + "Service;\n");
        content.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        content.append("import org.springframework.stereotype.Service;\n");
        content.append("\n");
        content.append("/**\n");
        content.append(" * Created by LGeneratorins on " + aDate.format(new Date()) + "\n");
        content.append(" */\n\n");
        content.append("@Service\n");
//        content.append("public class " + objname + "ServiceImpl extends BaseServiceSupport<" + objname + "Dto> implements " + objname + "Service {\n");
        content.append("public class " + objname + "Service extends BaseService<" + objname + "Dao> {\n");
        content.append("\n");
        content.append("\t@Autowired\n");
        content.append("\tprivate " + objname + "Mapper " + toLowerCaseFirstOne(objname) + "Mapper;\n");
        content.append("\n");
        content.append("\tpublic IMapper<" + objname + "Dao> getMapper() {\n");
        content.append("\t\treturn " + toLowerCaseFirstOne(objname) + "Mapper;\n");
        content.append("\t}\n");
        content.append("}\n");
        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/service/impl/";
//        String folder = System.getProperty("user.dir") + "/src/" + packageName + "/service/impl/";

        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = folder + className + "Service.java";
//        String fileName = folder + className + "ServiceImpl.java";

        try {
            File newdao = new File(fileName);
            FileWriter fw = new FileWriter(newdao);
            fw.write(content.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toLowerCamelCase(String value) {
        if (value == null || "".equals(value.trim())) {
            return "";
        }
        int len = value.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (i == 0 && c >= 65 && c <= 90) {
                sb.append(Character.toLowerCase(c));
                while (i + 1 < len && value.charAt(i + 1) >= 65 && value.charAt(i + 1) <= 90) {
                    sb.append(Character.toLowerCase(value.charAt(i + 1)));
                    i++;
                }
                continue;
            }
            if (c == 95) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(value.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 生成所有的方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            if (!colnames[i].equals("id")) {
                if (!colnames[i].equalsIgnoreCase("id")) {
                    String filed = colnames[i];
                    if (filed.indexOf("_") > -1) {
                        filed = toHump(filed);
                    }
                    String lowerFiled = toLowerCaseFirstOne(filed);
                    sb.append("\tpublic void set" + initcap(filed) + "("
                            + sqlType2JavaType(colTypes[i]) + " " + lowerFiled
                            + "){\r\n");
                    sb.append("\t\tthis." + lowerFiled + " = " + lowerFiled + ";\r\n");
                    sb.append("\t}\r\n");

                    sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                            + initcap(filed) + "(){\r\n");
                    sb.append("\t\treturn " + lowerFiled + ";\r\n");
                    sb.append("\t}\r\n");
                }
            }
        }
        sb.append("\tpublic " + objname + "Dao(){\n");
        sb.append("\t\tsuper();\n");
        sb.append("\t}\n");
    }

    /**
     * 解析输出属性
     *
     * @return
     */
    private void processAllAttrs(StringBuffer sb) {
        String valStr = objname + "{";
        for (int i = 0; i < colnames.length; i++) {
            String filed = colnames[i];
            filed = toLowerCaseFirstOne(filed);
            if (filed.indexOf("_") > -1) {
                filed = toHump(filed);
            }
            if (!filed.equalsIgnoreCase("id")) {
                sb.append("\t@ApiModelProperty(value=\"" + colComment[i] + "\", hidden=false, required=" + colNull[i] + ", dataType=\"" + sqlType2JavaType(colTypes[i]) + "\", example = \"\")\r\n");
                sb.append("\t@Column(name=\"" + colnames[i] + "\")\r\n");
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + filed + ";\r\n\n");
            }
            valStr += "\"" + filed + "\"" + '=' + filed + ", ";
        }
        valStr = valStr.substring(0, valStr.length() - 2) + "}";
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 转驼峰
     */

    private String toHump(String str) {
        str = str.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("_(\\w)");
        Matcher m = p.matcher(str);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")
                || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("TIMESTAMP")
                || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("time")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "Clob";
        }
        return null;
    }

    public static void addA(Integer ab) {
        System.out.println(ab);
    }

    public static void main(String[] args) throws Exception {
        AutoCreateBean auto = new AutoCreateBean();
        //List<String> list = auto.TBlist();//所有表
        List<String> list = new ArrayList<>();
//        list.add("PeopleCollection|wrp_people_hitcard_statistics");//单张表
//        list.add("Worker|wrp_worker");//单张表
//       list.add("CashSurplusRecord|wrp_fin_cash_surplus_record");//单张表

        list.add("Office|office");
        /*list.add("Police|wrp_sys_police");
        list.add("Menu|wrp_sys_menu");
        list.add("RoleMenu|wrp_sys_role_menu");
        list.add("User|wrp_sys_user");
        list.add("Role|wrp_sys_user_role");*/
        auto.GenEntity(list, "com.lin");
        System.out.println(System.getProperty("user.dir"));
    }
}
