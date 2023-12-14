package com.strr.util;

import com.strr.base.model.Pageable;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.*;

public class ExecutorUtil {
    public static final String COUNT_SUFFIX = "-inline-count";
    public static final String LIST_SUFFIX = "-inline-list";

    public static String genCountMsId(MappedStatement ms) {
        return ms.getId() + COUNT_SUFFIX;
    }

    public static String genListMsId(MappedStatement ms) {
        return ms.getId() + LIST_SUFFIX;
    }

    public static MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
        MappedStatement mappedStatement = null;
        try {
            mappedStatement = configuration.getMappedStatement(msId, false);
        } catch (Throwable t) {
        }
        return mappedStatement;
    }

    public static Integer executeManualCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, ResultHandler resultHandler) throws SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql countBoundSql = countMs.getBoundSql(parameter);
        List<Number> countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        return countResultList != null && !countResultList.isEmpty() ? countResultList.get(0).intValue() : 0;
    }

    public static MappedStatement newCountMappedStatement(MappedStatement ms, String newMsId) {
        MappedStatement.Builder countMs = new MappedStatement.Builder(
                ms.getConfiguration(),
                newMsId,
                ms.getSqlSource(),
                ms.getSqlCommandType()
        );
        List<ResultMap> resultMaps = Collections.singletonList(new ResultMap.Builder(ms.getConfiguration(), newMsId, int.class, new ArrayList<>()).build());
        countMs.resultMaps(resultMaps);
        return countMs.build();
    }

    public static Integer executeAutoCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql, RowBounds rowBounds, ResultHandler<?> resultHandler) throws SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        String countSql = String.format("select count(1) from (%s) t", boundSql.getSql());
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        List<Number> countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        return countResultList != null && !countResultList.isEmpty() ? countResultList.get(0).intValue() : 0;
    }

    public static MappedStatement newListMappedStatement(MappedStatement ms, String newMsId) {
        MappedStatement.Builder listMs = new MappedStatement.Builder(
                ms.getConfiguration(),
                newMsId,
                ms.getSqlSource(),
                ms.getSqlCommandType()
        );
        listMs.resultMaps(ms.getResultMaps());
        return listMs.build();
    }

    public static Pageable getPageable(Object parameter) {
        if (parameter instanceof Map<?, ?> map) {
            Collection<?> params = map.values();
            for (Object param : params) {
                if (param instanceof Pageable pageable) {
                    return pageable;
                }
            }
        }
        return null;
    }

    public static <E> List<E> pageQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler<?> resultHandler, BoundSql boundSql, Pageable pageable) throws SQLException {
        CacheKey listKey = executor.createCacheKey(ms, parameter, RowBounds.DEFAULT, boundSql);
        String listSql = String.format("%s limit %d offset %d", boundSql.getSql(), pageable.getSize(), (pageable.getPage() - 1) * pageable.getSize());
        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), listSql, boundSql.getParameterMappings(), parameter);
        return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, listKey, countBoundSql);
    }
}
