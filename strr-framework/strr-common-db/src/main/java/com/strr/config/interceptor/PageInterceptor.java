package com.strr.config.interceptor;

import com.strr.base.model.Page;
import com.strr.base.model.Pageable;
import com.strr.util.ExecutorUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 分页插件
 */
@Component
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement)args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds)args[2];
        ResultHandler<?> resultHandler = (ResultHandler<?>)args[3];
        Executor executor = (Executor)invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        if (args.length == 4) {
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            cacheKey = (CacheKey)args[4];
            boundSql = (BoundSql)args[5];
        }

        Pageable pageable = ExecutorUtil.getPageable(parameter);
        if (pageable == null) {
            return invocation.proceed();
        }

        Page page = pageable.page();
        MappedStatement countMs = ExecutorUtil.newCountMappedStatement(ms, ExecutorUtil.genCountMsId(ms));
        Integer count = ExecutorUtil.executeAutoCount(executor, countMs, parameter, boundSql, rowBounds, resultHandler);
        page.setTotal(count);
        if (count > 0) {
            MappedStatement listMs = ExecutorUtil.newListMappedStatement(ms, ExecutorUtil.genListMsId(ms));
            List list = ExecutorUtil.pageQuery(executor, listMs, parameter, rowBounds, resultHandler, boundSql, pageable);
            page.setContent(list);
        }

        return Collections.singletonList(page);
    }
}
