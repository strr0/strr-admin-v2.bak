package com.strr.util;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TreeUtil {
    /**
     * 根据排序添加
     */
    private static <T> void addItemInOrder(List<T> list, T item, Function<T, Integer> getOrder) {
        // 无排序
        if (getOrder == null) {
            list.add(item);
            return;
        }
        Integer order = getOrder.apply(item);
        // order为空添加到list末尾
        if (order == null) {
            list.add(item);
            return;
        }
        int i = 0;
        for (T target : list) {
            Integer targetOrder = getOrder.apply(target);
            // 目标位置order为空 或 order小于目标位置order 则添加到目标位置之前
            if (targetOrder == null || order < targetOrder) {
                list.add(i, item);
                return;
            }
            i++;
        }
        list.add(item);
    }

    /**
     * 反向建树 - 有序
     */
    public static <S, T> List<T> reverseBuildTree(List<S> list, Function<S, Integer> getId, Function<S, Integer> getPid, Function<T, Integer> getOrder,
                                                            Function<S, T> s2t, Function<T, List<T>> getChildren, BiConsumer<T, List<T>> setChildren) {
        Map<Integer, T> itemMap = new HashMap<>();
        List<T> tree = new LinkedList<>();
        list.forEach(source -> {
            T target = s2t.apply(source);
            T parent = itemMap.get(getPid.apply(source));
            if (parent != null) {
                List<T> children = getChildren.apply(parent);
                if (children == null) {
                    children = new LinkedList<>();
                    setChildren.accept(parent, children);
                }
                // 根据排序添加子节点
                addItemInOrder(children, target, getOrder);
            } else {
                // 根据排序添加根节点
                addItemInOrder(tree, target, getOrder);
            }
            itemMap.put(getId.apply(source), target);
        });
        return tree;
    }

    /**
     * 反向建树 - 无序
     */
    public static <S, T> List<T> reverseBuildTree(List<S> list, Function<S, Integer> getId, Function<S, Integer> getPid,
                                                            Function<S, T> s2t, Function<T, List<T>> getChildren, BiConsumer<T, List<T>> setChildren) {
        return reverseBuildTree(list, getId, getPid, null, s2t, getChildren, setChildren);
    }

    /**
     * 正向建树
     */
    public static <T> List<T> forwardBuildTree(List<T> list, Function<T, Integer> getId, Function<T, Integer> getPid, BiConsumer<T, List<T>> setChildren, Integer startPid) {
        List<T> tree = new ArrayList<>();
        list.forEach(item -> {
            setChildren.accept(item, forwardBuildNode(list, getPid, getId.apply(item)));
            if (Objects.equals(startPid, getPid.apply(item))) {
                tree.add(item);
            }
        });
        return tree;
    }

    /**
     * 正向建树 - 节点
     */
    private static <T> List<T> forwardBuildNode(List<T> list, Function<T, Integer> getPid, Integer pid) {
        List<T> children = new ArrayList<>();
        list.forEach(item -> {
            if (pid.equals(getPid.apply(item))) {
                children.add(item);
            }
        });
        return children.isEmpty() ? null : children;
    }
}
