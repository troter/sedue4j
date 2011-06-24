package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class AlldocsQueryPart extends AbstractSimpleQueryPart {

    public AlldocsQueryPart(CharSequence indexName) {
        super(indexName);
    }

    @Override
    protected EnumSet<IndexType> getSupportIndexTypes() {
        return EnumSet.of(IndexType.ALLDOCS);
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta, QueryPart defaultQueryPart) {
        if (schemaMeta == null) {
            return String.format("(%s:)", indexName);
        }
        IndexMeta indexMeta = getIndexMeta(schemaMeta, this.indexName);
        return String.format("(%s:)", indexMeta.getName());
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
