package jp.troter.sedue4j.querypart;

import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class CompositeQueryPart implements QueryPart {

    protected String operator;
    protected QueryPart[] children;

    public CompositeQueryPart(final String operator, final QueryPart... children) {
        this.operator = operator;
        this.children = children;
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        // TODO Auto-generated method stub
        return null;
    }
}
