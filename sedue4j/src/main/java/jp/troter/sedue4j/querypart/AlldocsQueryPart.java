package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
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
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = getIndexMeta(schemaMeta, this.indexName);
        return String.format("(%s:)", indexMeta.getName());
    }

    @Override
    public String getQuery() {
        return String.format("(%s:)", indexName);
    }
}
