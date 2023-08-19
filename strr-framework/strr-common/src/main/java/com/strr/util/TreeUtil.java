package com.strr.util;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TreeUtil<S, T, K> {
    /**
     * 原数据
     */
    private final List<S> list;

    /**
     * 获取id
     */
    private Function<S, K> getId;

    /**
     * 获取父id
     */
    private Function<S, K> getPid;

    /**
     * 类型转换
     */
    private Function<S, T> transform;

    /**
     * 获取排序
     */
    private Function<T, Integer> getOrder;

    /**
     * 添加按钮
     */
    private BiFunction<S, T, Boolean> addButton;

    /**
     * 添加布局
     */
    private BiConsumer<S, T> addLayout;

    /**
     * 获取子节点
     */
    private Function<T, List<T>> getChildren;

    /**
     * 设置子节点
     */
    private BiConsumer<T, List<T>> setChildren;

    public TreeUtil(List<S> list) {
        this.list = list;
    }

    public TreeUtil<S, T, K> withGetId(Function<S, K> getId) {
        this.getId = getId;
        return this;
    }

    public TreeUtil<S, T, K> withGetPid(Function<S, K> getPid) {
        this.getPid = getPid;
        return this;
    }

    public TreeUtil<S, T, K> withTransform(Function<S, T> transform) {
        this.transform = transform;
        return this;
    }

    public TreeUtil<S, T, K> withGetOrder(Function<T, Integer> getOrder) {
        this.getOrder = getOrder;
        return this;
    }

    public TreeUtil<S, T, K> withAddButton(BiFunction<S, T, Boolean> addButton) {
        this.addButton = addButton;
        return this;
    }

    public TreeUtil<S, T, K> withAddLayout(BiConsumer<S, T> addLayout) {
        this.addLayout = addLayout;
        return this;
    }

    public TreeUtil<S, T, K> withGetChildren(Function<T, List<T>> getChildren) {
        this.getChildren = getChildren;
        return this;
    }

    public TreeUtil<S, T, K> withSetChildren(BiConsumer<T, List<T>> setChildren) {
        this.setChildren = setChildren;
        return this;
    }

    public List<T> build() {
        if (getId == null || getPid == null || getChildren == null || setChildren == null) {
            return Collections.EMPTY_LIST;
        }
        Map<K, T> itemMap = new HashMap<>();
        List<T> tree = getOrder == null ? new ArrayList<>() : new LinkedList<>();
        list.forEach(source -> {
            T target = transform == null ? (T) source : transform.apply(source);
            T parent = itemMap.get(getPid.apply(source));
            if (parent != null) {
                // 添加按钮
                if (addButton != null && addButton.apply(source, parent)) {
                    return;
                }
                List<T> children = getChildren.apply(parent);
                if (children == null) {
                    children = new LinkedList<>();
                    setChildren.accept(parent, children);
                }
                // 根据排序添加子节点
                addItemInOrder(children, target);
            } else {
                // 添加布局
                if (addLayout != null) {
                    addLayout.accept(source, target);
                }
                // 根据排序添加根节点
                addItemInOrder(tree, target);
            }
            itemMap.put(getId.apply(source), target);
        });
        return tree;
    }

    /**
     * 根据排序添加
     */
    private void addItemInOrder(List<T> targetList, T item) {
        // 无排序
        if (getOrder == null) {
            targetList.add(item);
            return;
        }
        Integer order = getOrder.apply(item);
        // order为空添加到list末尾
        if (order == null) {
            targetList.add(item);
            return;
        }
        int i = 0;
        for (T target : targetList) {
            Integer targetOrder = getOrder.apply(target);
            // 目标位置order为空 或 order小于目标位置order 则添加到目标位置之前
            if (targetOrder == null || order < targetOrder) {
                targetList.add(i, item);
                return;
            }
            i++;
        }
        targetList.add(item);
    }
}
