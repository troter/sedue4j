package jp.troter.sedue4j.querypart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

import org.apache.commons.lang.StringUtils;

public class CompositeQueryPart implements QueryPart {

    protected String operator;

    protected List<QueryPart> children = new ArrayList<QueryPart>();

    public CompositeQueryPart(final String operator, final QueryPart... children) {
        this.operator = operator;
        this.children = Arrays.asList(children);
    }

    public CompositeQueryPart(final String operator, final Collection<QueryPart> children) {
        this.operator = operator;
        this.children.addAll(children);
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta, QueryPart defaultQueryPart) {
        if (children.isEmpty()) {
            if (defaultQueryPart == null) {
                throw new RuntimeException("デフォルトのクエリーが存在しないため、クエリーの組み立てできません");
            }
            return defaultQueryPart.getQuery(schemaMeta);
        }

        if (children.size() == 1) {
            return children.get(0).getQuery(schemaMeta, defaultQueryPart);
        }

        List<String> queries = new ArrayList<String>();
        for (QueryPart child : children) {
            queries.add(child.getQuery(schemaMeta, defaultQueryPart));
        }
        return String.format("(%s)", StringUtils.join(queries, operator));
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        return getQuery(schemaMeta, null);
    }

    @Override
    public String getQuery(QueryPart defaultQueryPart) {
        return getQuery(null, defaultQueryPart);
    }

    @Override
    public String getQuery() {
        return getQuery(null, null);
    }
}
