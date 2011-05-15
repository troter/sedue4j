package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public abstract class AbstractSimpleQueryPart implements QueryPart {

    protected CharSequence indexName;

    public AbstractSimpleQueryPart(CharSequence indexName) {
        this.indexName = indexName;
    }

    /**
     * クエリパートで利用できるインデックスタイプのセット
     * @return
     */
    abstract EnumSet<IndexType> getSupportIndexTypes();

    protected boolean isSupportIndexType(IndexType indexType) {
        return getSupportIndexTypes().contains(indexType);
    }

    protected IndexMeta getIndexMeta(SchemaMeta schemaMeta, CharSequence indexName) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (! isSupportIndexType(indexMeta.getIndexType())) {
            throw new RuntimeException(String.format("インデックスの種類が異なります。"));
        }
        return indexMeta;
    }
}
