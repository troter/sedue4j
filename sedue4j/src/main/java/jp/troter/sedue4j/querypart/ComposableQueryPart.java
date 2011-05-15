package jp.troter.sedue4j.querypart;

import java.util.ArrayList;
import java.util.List;

import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class ComposableQueryPart implements QueryPart {

    protected List<QueryPart> children = new ArrayList<QueryPart>();

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
