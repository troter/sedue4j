package jp.troter.sedue4j.querypart;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class AlldocsQueryPart implements QueryPart {

    protected CharSequence indexName;

    public AlldocsQueryPart(CharSequence indexName) {
        this.indexName = indexName;
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (IndexType.ALLDOCS != indexMeta.getIndexType()) {
            throw new RuntimeException(
                    String.format("インデックスの種類が異なります。 expect[%s], actual[%s]",
                            IndexType.ALLDOCS, indexMeta.getIndexType()));
        }
        return "(" + indexName + ":" + ")";
    }
}
