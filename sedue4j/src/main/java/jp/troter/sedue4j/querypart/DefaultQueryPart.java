package jp.troter.sedue4j.querypart;

import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;
import jp.troter.sedue4j.exception.EmptyQueryPartException;

public class DefaultQueryPart implements QueryPart {
    
    final QueryPart defaultQueryPart;
    final QueryPart queryPart;
    
    public DefaultQueryPart(final QueryPart defaultQueryPart, final QueryPart queryPart) {
        this.defaultQueryPart = defaultQueryPart;
        this.queryPart = queryPart;
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        try {
            return queryPart.getQuery(schemaMeta);
        } catch (EmptyQueryPartException e) {
            return defaultQueryPart.getQuery(schemaMeta);
        }
    }

    @Override
    public String getQuery() {
        return getQuery(null);
    }
}
