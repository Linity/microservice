package cn.com.ljw.contract.test;

import cn.hutool.json.JSONUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import sun.plugin.javascript.navig.Array;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Steph_Lin
 * @date 2023/10/14
 */
public class TestUtil {

    public static void main(String[] args) {

        String data = "[\n" +
                "    {\n" +
                "      \"id\": 3627,\n" +
                "      \"parentId\": 0,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"PDO\",\n" +
                "      \"resourceShowName\": \"产研运协同菜单\",\n" +
                "      \"resourceName\": \"产研运协同菜单\",\n" +
                "      \"resourcePath\": \"/pdo\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2022-11-28 13:59:44\",\n" +
                "      \"resourceNav\": \"|0|3627|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3671,\n" +
                "      \"parentId\": 5193,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"basicData\",\n" +
                "      \"resourceShowName\": \"系统管理\",\n" +
                "      \"resourceName\": \"系统管理\",\n" +
                "      \"resourcePath\": \"/basicData\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 10,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"https://cloudstorage-oss-sit.oss-cn-hangzhou.aliyuncs.com/user-center/1ddc9c38e38e4bdbaf6e5c1a7d26b684.png\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2022-11-29 18:50:31\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|3671|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3692,\n" +
                "      \"parentId\": 5195,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/analysis/person\",\n" +
                "      \"resourceShowName\": \"战队人员分析\",\n" +
                "      \"resourceName\": \"战队人员分析\",\n" +
                "      \"resourcePath\": \"/analysis/Person\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2022-12-05 13:07:42\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|5195|3692|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3693,\n" +
                "      \"parentId\": 5199,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/analysis/iteration\",\n" +
                "      \"resourceShowName\": \"迭代交付分析\",\n" +
                "      \"resourceName\": \"迭代交付分析\",\n" +
                "      \"resourcePath\": \"/analysis/Iteration\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2022-12-05 13:09:48\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|3693|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3975,\n" +
                "      \"parentId\": 4156,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"WorkManage\",\n" +
                "      \"resourceShowName\": \"资源投入管理\",\n" +
                "      \"resourceName\": \"资源投入管理\",\n" +
                "      \"resourcePath\": \"/WorkingHours/WorkManage\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 6,\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-02-14 17:47:26\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4156|3975|\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3992,\n" +
                "      \"parentId\": 3627,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"appStore\",\n" +
                "      \"resourceShowName\": \"客户端发布\",\n" +
                "      \"resourceName\": \"客户端发布\",\n" +
                "      \"resourcePath\": \"/appStore\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 12,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-02-22 14:35:44\",\n" +
                "      \"resourceNav\": \"|0|3627|3992|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3993,\n" +
                "      \"parentId\": 3992,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"offlineResourceList\",\n" +
                "      \"resourceShowName\": \"离线包管理\",\n" +
                "      \"resourceName\": \"离线包管理\",\n" +
                "      \"resourcePath\": \"/appStore/offlineResource/list\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-02-22 14:37:08\",\n" +
                "      \"resourceNav\": \"|0|3627|3992|3993|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4050,\n" +
                "      \"parentId\": 5199,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"Efficiency\",\n" +
                "      \"resourceShowName\": \"迭代效能分析\",\n" +
                "      \"resourceName\": \"迭代效能分析\",\n" +
                "      \"resourcePath\": \"/analysis/efficiency\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-07 09:57:22\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|4050|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4156,\n" +
                "      \"parentId\": 5193,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"costAnalysis\",\n" +
                "      \"resourceShowName\": \"成本管理\",\n" +
                "      \"resourceName\": \"成本管理\",\n" +
                "      \"resourcePath\": \"/costAnalysis\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 2,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-23 18:04:26\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4156|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4157,\n" +
                "      \"parentId\": 4156,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"workCostAnalysis\",\n" +
                "      \"resourceShowName\": \"人力资源成本分析\",\n" +
                "      \"resourceName\": \"人力资源成本分析\",\n" +
                "      \"resourcePath\": \"/WorkingHours/CostAnalysis\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-23 18:12:04\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4156|4157|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4158,\n" +
                "      \"parentId\": 5193,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"personManage\",\n" +
                "      \"resourceShowName\": \"人员管理\",\n" +
                "      \"resourceName\": \"人员管理\",\n" +
                "      \"resourcePath\": \"/personManage\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 3,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-23 18:16:24\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4158|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4160,\n" +
                "      \"parentId\": 4156,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"InputAnalysis\",\n" +
                "      \"resourceShowName\": \"战队成员投入分析\",\n" +
                "      \"resourceName\": \"战队成员投入分析\",\n" +
                "      \"resourcePath\": \"/WorkingHours/InputAnalysis\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 5,\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-23 18:21:23\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4156|4160|\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4167,\n" +
                "      \"parentId\": 3671,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"dict\",\n" +
                "      \"resourceShowName\": \"数据字典\",\n" +
                "      \"resourceName\": \"数据字典\",\n" +
                "      \"resourcePath\": \"/system/DictList\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-03-24 14:18:26\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|3671|4167|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5047,\n" +
                "      \"parentId\": 4156,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"costAnalyaisList\",\n" +
                "      \"resourceShowName\": \"人力成本财务报表\",\n" +
                "      \"resourceName\": \"人力成本财务报表\",\n" +
                "      \"resourcePath\": \"/WorkingHours/CostAnalysisList\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-04-17 15:26:13\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4156|5047|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5112,\n" +
                "      \"parentId\": 3671,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"noticeConfig\",\n" +
                "      \"resourceShowName\": \"公告配置\",\n" +
                "      \"resourceName\": \"公告配置\",\n" +
                "      \"resourcePath\": \"/notice-config\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-05 19:41:20\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|3671|5112|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5113,\n" +
                "      \"parentId\": 3671,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"developConfig\",\n" +
                "      \"resourceShowName\": \"开发模块配置\",\n" +
                "      \"resourceName\": \"开发模块配置\",\n" +
                "      \"resourcePath\": \"/develop-config\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-05 19:42:57\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|3671|5113|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5193,\n" +
                "      \"parentId\": 3627,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"operationManagement\",\n" +
                "      \"resourceShowName\": \"运营管理\",\n" +
                "      \"resourceName\": \"运营管理\",\n" +
                "      \"resourcePath\": \"/operationManagement\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 11,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-15 17:22:09\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5195,\n" +
                "      \"parentId\": 5193,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"team\",\n" +
                "      \"resourceShowName\": \"战队管理\",\n" +
                "      \"resourceName\": \"战队管理\",\n" +
                "      \"resourcePath\": \"/team\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 6,\n" +
                "      \"resourceIcon\": \"https://cloudstorage-oss-sit.oss-cn-hangzhou.aliyuncs.com/user-center/bbfa95a5e51c431b828cd32d06443f0d.png\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-15 17:30:53\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|5195|\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5196,\n" +
                "      \"parentId\": 5195,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"TeamList\",\n" +
                "      \"resourceShowName\": \"战队清单管理\",\n" +
                "      \"resourceName\": \"战队清单管理\",\n" +
                "      \"resourcePath\": \"/team/TeamList\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-15 17:30:53\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|5195|5196|\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5199,\n" +
                "      \"parentId\": 3627,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"measureManage\",\n" +
                "      \"resourceShowName\": \"度量管理\",\n" +
                "      \"resourceName\": \"度量管理\",\n" +
                "      \"resourcePath\": \"/measureManage\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 9,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-15 17:59:12\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5665,\n" +
                "      \"parentId\": 5199,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/agility-improvement\",\n" +
                "      \"resourceShowName\": \"敏捷提效管理\",\n" +
                "      \"resourceName\": \"敏捷提效管理\",\n" +
                "      \"resourcePath\": \"/agility-improvement\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-05-18 10:01:35\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|5665|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8246,\n" +
                "      \"parentId\": 4158,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"new-employee\",\n" +
                "      \"resourceShowName\": \"新员工管理\",\n" +
                "      \"resourceName\": \"新员工管理\",\n" +
                "      \"resourcePath\": \"/new-employee/manage\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-07-05 17:48:03\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4158|8246|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8261,\n" +
                "      \"parentId\": 5199,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/efficiency-management\",\n" +
                "      \"resourceShowName\": \"敏捷提效\",\n" +
                "      \"resourceName\": \"敏捷提效\",\n" +
                "      \"resourcePath\": \"/efficiency\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-07-07 13:19:02\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|8261|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8262,\n" +
                "      \"parentId\": 8261,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/target-manage\",\n" +
                "      \"resourceShowName\": \"目标管理\",\n" +
                "      \"resourceName\": \"目标管理\",\n" +
                "      \"resourcePath\": \"/efficiency/targetManage\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-07-07 13:20:28\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|8261|8262|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8263,\n" +
                "      \"parentId\": 8261,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/process-data\",\n" +
                "      \"resourceShowName\": \"过程数据\",\n" +
                "      \"resourceName\": \"过程数据\",\n" +
                "      \"resourcePath\": \"/efficiency/processData\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 1,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-07-07 13:22:24\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|8261|8263|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8264,\n" +
                "      \"parentId\": 8261,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/result-analysis\",\n" +
                "      \"resourceShowName\": \"结果分析\",\n" +
                "      \"resourceName\": \"结果分析\",\n" +
                "      \"resourcePath\": \"/efficiency/resultAnalysis\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 2,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-07-07 13:24:39\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|8261|8264|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9141,\n" +
                "      \"parentId\": 4158,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"personnel-list\",\n" +
                "      \"resourceShowName\": \"人员清单管理\",\n" +
                "      \"resourceName\": \"人员清单管理\",\n" +
                "      \"resourcePath\": \"/personnel/list\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-08-01 15:08:45\",\n" +
                "      \"resourceNav\": \"|0|3627|5193|4158|9141|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10508,\n" +
                "      \"parentId\": 8261,\n" +
                "      \"resourceType\": \"1\",\n" +
                "      \"resourceCode\": \"/teamEffectiveness\",\n" +
                "      \"resourceShowName\": \"战队效能\",\n" +
                "      \"resourceName\": \"战队效能\",\n" +
                "      \"resourcePath\": \"/efficiency/teamEffectiveness\",\n" +
                "      \"resourceModule\": \"PDO\",\n" +
                "      \"resourceSort\": 0,\n" +
                "      \"remark\": \"\",\n" +
                "      \"resourceIcon\": \"\",\n" +
                "      \"resourceIconIsShow\": 1,\n" +
                "      \"createTime\": \"2023-09-22 17:04:24\",\n" +
                "      \"resourceNav\": \"|0|3627|5199|8261|10508|\",\n" +
                "      \"methods\": \"\",\n" +
                "      \"protocols\": \"\"\n" +
                "    }\n" +
                "  ]";
        List<UserCenterResourceForm> userCenterResourceForms = JSONUtil.toList(data, UserCenterResourceForm.class);
//        List<UserCenterResourceForm> apiList = userCenterResourceForms.stream().filter(userCenterResourceForm -> userCenterResourceForm.getResourceType().equals("4")).collect(Collectors.toList());
//        List<Integer> parentIds = apiList.stream().map(UserCenterResourceForm::getParentId).collect(Collectors.toList());
//        //根据parentId找到接口对应的菜单列表
//        List<UserCenterResourceForm> menuList = userCenterResourceForms.stream().filter(userCenterResourceForm -> parentIds.contains(userCenterResourceForm.getId()) &&
//                userCenterResourceForm.getResourceType().equals("1")).collect(Collectors.toList());
//
//        Map<Integer, List<UserCenterResourceForm>> menuMap =menuList.stream() .collect(Collectors.groupingBy(UserCenterResourceForm::getParentId));
//        List<CreateApiResourceForm> apiResourceFormList = new ArrayList<>();
//
//        List<AppMenuApiResourceSaveBatchForm> menuApiResourceSaveBatchForms = new ArrayList<>();
//        for (Map.Entry<Integer, List<UserCenterResourceForm>> entry : menuMap.entrySet()) {
//            Integer parentId = entry.getKey();
//            Optional<String> first = menuList.stream().filter(userCenterResourceForm -> parentId.equals(userCenterResourceForm.getParentId())).map(UserCenterResourceForm::getResourceCode).findFirst();
//            String menuCode = null;
//            if (first.isPresent()){
//                 menuCode = first.get();
//            }
//            List<UserCenterResourceForm> relApiList = apiList.stream().filter(userCenterResourceForm -> parentId.equals(userCenterResourceForm.getParentId())).collect(Collectors.toList());
//            String finalMenuCode = menuCode;
//            relApiList.forEach(api -> {
//                CreateApiResourceForm apiResourceForm = new CreateApiResourceForm();
//                apiResourceForm.setApiResourceCode(api.getResourceCode());
//                apiResourceForm.setAppCode("appCode");
//                apiResourceForm.setDescription(api.getRemark());
//                apiResourceForm.setSort(api.getResourceSort());
//                apiResourceForm.setApiResourceUrl(api.getResourcePath());
//                apiResourceForm.setApiResourceName(api.getResourceName());
//                apiResourceForm.setRequestMethod(api.getMethods());
//                apiResourceForm.setApiResourceCode("");
//                apiResourceFormList.add(apiResourceForm);
//
//
//                AppMenuApiResourceSaveBatchForm menuApiResourceSaveBatchForm = new AppMenuApiResourceSaveBatchForm();
//                menuApiResourceSaveBatchForm.setApiResourceCode(apiResourceForm.getApiResourceCode());
//                menuApiResourceSaveBatchForm.setMenuCode(finalMenuCode);
//                menuApiResourceSaveBatchForms.add(menuApiResourceSaveBatchForm);
//            });
//
//        }
//
//        if(!CollectionUtils.isEmpty(apiResourceFormList)){
//            List<String> apiCodes = apiResourceFormList.stream().map(CreateApiResourceForm::getApiResourceCode).collect(Collectors.toList());
//            //查库  去掉apiResourceFormList已经存在的
//
//
//            //menuApiResourceSaveBatchForm移除已经存在的apiCode
//
//
//
//        }

        List<Integer> parentIds = userCenterResourceForms.stream().map(UserCenterResourceForm::getParentId).collect(Collectors.toList());
//        List<String> resCodes = userCenterResourceForms.stream().map(UserCenterResourceForm::getResourceCode).collect(Collectors.toList());
        Map<Integer, String> collect = userCenterResourceForms.stream().collect(Collectors.toMap(UserCenterResourceForm::getId, UserCenterResourceForm::getResourceCode));

        String parentResourceNav = null;
        Optional<UserCenterResourceForm> first = userCenterResourceForms.stream().filter(userCenterResourceForm -> userCenterResourceForm.getParentId() == 0).findFirst();
        if (first.isPresent()){
            parentResourceNav = first.get().getResourceNav();
        }
        String finalParentResourceNav = parentResourceNav;
        userCenterResourceForms.forEach(userCenterResourceForm -> {
            String resourceNav = userCenterResourceForm.getResourceNav();
            List<String> resCodes = new ArrayList<>();
            if (!StringUtils.isEmpty(finalParentResourceNav)){
                resourceNav =   resourceNav.replace(finalParentResourceNav,"|0|");
            }
            String[] split = resourceNav.split("\\|");
            for (int i = 0; i < split.length; i++) {
                if (ObjectUtils.isEmpty(split[i])){
                    continue;
                }
                Integer each = Integer.valueOf(split[i]);

                String resCode ;
                if (each == 0){
                    resCode = "-1";
                }else {
                    resCode =  collect.get(each);
                }
                resCodes.add(resCode );
            }

            String collect1 = resCodes.stream().collect(Collectors.joining("#"));
            System.out.println(collect1);
            userCenterResourceForm.setResourceConnectPath(collect1);

        });

        System.out.println(JSONUtil.toJsonPrettyStr(userCenterResourceForms));
    }
}
