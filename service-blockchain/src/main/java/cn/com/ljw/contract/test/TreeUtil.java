package cn.com.ljw.contract.test;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class TreeUtil {

    public TreeUtil() {
    }

    /**
     * 返回实体T 需要继承BaseTreeVO
     *
     * @param list
     * @param idProperty
     * @param parentIdProperty
     * @param <T>              T extends BaseTreeVO
     * @return
     */
    public static <T> List<T> formatTree(List<T> list, String idProperty, String parentIdProperty) {
        return formatTree(list, idProperty, parentIdProperty, "children", "leaf", null);
    }

    public static <T> List<T> formatTree(List<T> list, String idProperty, String parentIdProperty, String childProperty, String leafProperty, Predicate<T> predicate) {
        Iterator<T> iterator = list.iterator();

        while (true) {
            while (iterator.hasNext()) {
                T t = iterator.next();
                if (predicate != null && !predicate.test(t)) {
                    iterator.remove();
                } else if (leafProperty != null) {
                    BeanUtil.setFieldValue(t, leafProperty, true);
                }
            }

            List<T> nodeList = new ArrayList();

            for (T node1 : list) {
                boolean isRootNode = true;

                for (T node2 : list) {
                    if (BeanUtil.getFieldValue(node2, idProperty) != null && BeanUtil.getFieldValue(node2, idProperty).equals(BeanUtil.getFieldValue(node1, parentIdProperty))) {
                        isRootNode = false;
                        if (leafProperty != null) {
                            BeanUtil.setFieldValue(node2, leafProperty, null);
                        }
                        List<T> childList = (List<T>) BeanUtil.getFieldValue(node2, childProperty);
                        if (childList == null) {
                            childList = new ArrayList<>();
                        }
                        BeanUtil.setFieldValue(node2, childProperty, childList);
                        childList.add(node1);
                        break;
                    }
                }

                if (isRootNode) {
                    nodeList.add(node1);
                }
            }

            return nodeList;
        }
    }

    /**
     * 显示子节点有新增标签的树
     *
     * @param list
     * @param idProperty
     * @param parentIdProperty
     * @param <T>
     * @return
     */
    public static <T> List<T> formatCompareTree(List<T> list, String idProperty, String parentIdProperty) {
        return formatCompareTree(list, idProperty, parentIdProperty, "children", "leaf", null);
    }

    public static <T> List<T> formatCompareTree(List<T> list, String idProperty, String parentIdProperty, String childProperty, String leafProperty, Predicate<T> predicate) {
        Iterator<T> iterator = list.iterator();

        while (true) {
            while (iterator.hasNext()) {
                T t = iterator.next();
                if ((predicate != null && !predicate.test(t)) || (!ObjectUtils.isEmpty(BeanUtil.getFieldValue(t, "isNew")) &&
                        BeanUtil.getFieldValue(t, "isNew").toString().equals("false"))) {
                    iterator.remove();
                } else if (leafProperty != null) {
                    BeanUtil.setFieldValue(t, leafProperty, true);
                }
            }

            List<T> nodeList = new ArrayList();

            for (T node1 : list) {
                boolean isRootNode = true;

                for (T node2 : list) {
                    if (BeanUtil.getFieldValue(node2, idProperty) != null
                            && BeanUtil.getFieldValue(node2, idProperty).equals(BeanUtil.getFieldValue(node1, parentIdProperty))) {
                        isRootNode = false;
                        if (leafProperty != null) {
                            BeanUtil.setFieldValue(node2, leafProperty, null);
                        }
                        List<T> childList = (List<T>) BeanUtil.getFieldValue(node2, childProperty);
                        if (childList == null) {
                            childList = new ArrayList<>();
                        }
                        BeanUtil.setFieldValue(node2, childProperty, childList);
                        childList.add(node1);
                        break;
                    }
                }

                if (isRootNode) {
                    nodeList.add(node1);
                }
            }

            return nodeList;
        }
    }
}
