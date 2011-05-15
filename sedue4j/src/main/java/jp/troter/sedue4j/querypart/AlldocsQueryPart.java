package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class AlldocsQueryPart implements QueryPart {

    protected CharSequence indexName;

    public AlldocsQueryPart(CharSequence indexName) {
        this.indexName = indexName;
    }

    protected EnumSet<IndexType> validIndexType() {
        return EnumSet.of(IndexType.ALLDOCS);
    }

    protected boolean isValidIndexType(IndexType indexType) {
        return validIndexType().contains(indexType);
    }

    protected IndexMeta getIndexMeta(SchemaMeta schemaMeta, CharSequence indexName) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (! isValidIndexType(indexMeta.getIndexType())) {
            throw new RuntimeException(String.format("インデックスの種類が異なります。"));
        }
        return indexMeta;
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = getIndexMeta(schemaMeta, this.indexName);
        return String.format("(%s:)", indexMeta.getName());
    }
}
