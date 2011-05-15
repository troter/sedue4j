package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

import org.apache.commons.lang.StringUtils;

public class FulltextQueryPart implements QueryPart {

    protected CharSequence indexName;
    protected String keyword;
    protected CharSequence[] sections;

    public FulltextQueryPart(CharSequence indexName, String keyword, CharSequence...sections) {
        this.indexName = indexName;
        this.keyword = keyword;
        this.sections = sections;
    }

    protected EnumSet<IndexType> validIndexType() {
        return EnumSet.of(IndexType.INVERTEDINDEX, IndexType.NGRAM, IndexType.CSA, IndexType.HSA, IndexType.UNIGRAM);
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

    /**
     * TODO: 予約語のescape
     */
    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (indexMeta.useSectionQuery()) {
            String sectionCondition = "*";
            if (sections != null && sections.length != 0) {
                sectionCondition = StringUtils.join(sections, ",");
            }
            return String.format("(%s:%s:%s)", indexMeta.getName(), sectionCondition, keyword);
        }
        return String.format("(%s:%s)", indexMeta.getName(), keyword);
    }
}
