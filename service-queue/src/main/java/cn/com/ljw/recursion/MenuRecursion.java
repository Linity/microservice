package cn.com.ljw.recursion;

import java.util.ArrayList;
import java.util.List;

public class MenuRecursion {

    //子节点
    static List<Menu> childMenu = new ArrayList<>();

//    static List<Menu> childMenuOne = new ArrayList<>();

    /**
     * 获取某个父节点下面的所有子节点
     * @param menuList
     * @param pid
     * @return
     */
    public static List<Menu> treeMenuList(List<Menu> menuList, int pid) {
        for (Menu mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (Integer.valueOf(mu.getPid()) == pid) {
                //递归遍历下一级
                treeMenuList(menuList, Integer.valueOf(mu.getId()));
                childMenu.add(mu);
            }
        }
        return childMenu;
    }

    public static List<Menu> getMenuOne(List<Menu> childMenuOne, List<Menu> menuList, int pid) {
        for (Menu mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (Integer.valueOf(mu.getPid()) == pid) {
                //递归遍历下一级
                childMenuOne.add(mu);
            }
        }
        return childMenuOne;
    }

    static List<Menu> menuList = new ArrayList<>();

    static List<String> menuName = new ArrayList<>();

    static List<Menu> hasChilds = new ArrayList<>();

    public static List<String> getMenuList(List<Menu> hasChilds, List<String> MenuName) {
        for (Menu mu : hasChilds) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getHasChild()) {

                List<Menu> menus = getMenuOne(new ArrayList<>(), menuList, Integer.parseInt(mu.getId()));

                //递归遍历下一级
                getMenuList(menus, menuName);
            }
            if(!mu.getHasChild()) {
                menuName.add(mu.getName());
            }
        }
        return menuName;
    }


    public static void main(String args[]) {
        List<Menu> menuList = getMenu();

//        List<Menu> menuOne = getMenuOne(menuList, 5);
        List<String> childList = getMenuList(hasChilds, menuName);
        for (String m : childList) {
            System.out.println(m);
        }
//        for (Menu m : menuOne) {
//            System.out.println(m.getId() + "   " + m.getName());
//        }
    }

    public static List<Menu> getMenu() {

        Menu mu = new Menu();
        mu.setId("1");
        mu.setName("目录");
        mu.setPid("0");
        mu.setHasChild(true);
        Menu mu1 = new Menu();
        mu1.setId("2");
        mu1.setName("目录1");
        mu1.setPid("1");
        mu1.setHasChild(true);
        Menu mu2 = new Menu();
        mu2.setId("3");
        mu2.setName("目录1.1");
        mu2.setPid("2");
        mu2.setHasChild(false);
        Menu mu3 = new Menu();
        mu3.setId("4");
        mu3.setName("目录1.2");
        mu3.setPid("2");
        mu3.setHasChild(false);
        Menu mu4 = new Menu();
        mu4.setId("5");
        mu4.setName("目录2");
        mu4.setPid("1");
        mu4.setHasChild(true);
        Menu mu5 = new Menu();
        mu5.setId("6");
        mu5.setName("目录2.1");
        mu5.setPid("5");
        mu5.setHasChild(false);
        Menu mu6 = new Menu();
        mu6.setId("7");
        mu6.setName("目录2.2");
        mu6.setPid("5");
        mu6.setHasChild(true);
        Menu mu7 = new Menu();
        mu7.setId("8");
        mu7.setName("目录2.2.1");
        mu7.setPid("7");
        mu7.setHasChild(false);
        menuList.add(mu);
        menuList.add(mu1);
        menuList.add(mu2);
        menuList.add(mu3);
        menuList.add(mu4);
        menuList.add(mu5);
        menuList.add(mu6);
        menuList.add(mu7);

        hasChilds.add(mu);
//        hasChilds.add(mu1);
//        hasChilds.add(mu2);
//        hasChilds.add(mu4);

        return menuList;
    }

}
