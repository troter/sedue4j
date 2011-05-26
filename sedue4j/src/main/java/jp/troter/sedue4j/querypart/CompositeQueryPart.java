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
    public String getQuery(SchemaMeta schemaMeta) {
        List<String> queries = new ArrayList<String>();
        for (QueryPart child : children) {
            queries.add(child.getQuery(schemaMeta));
        }
        return String.format("(%s)", StringUtils.join(queries, operator));
    }

    @Override
    public String getQuery() {
        List<String> queries = new ArrayList<String>();
        for (QueryPart child : children) {
            queries.add(child.getQuery());
        }
        return String.format("(%s)", StringUtils.join(queries, operator));
    }
}
