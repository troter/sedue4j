package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.SchemaMeta;
import jp.troter.sedue4j.util.QueryPartEscapeUtil;

import org.apache.commons.lang.StringUtils;

public class FulltextQueryPart extends AbstractSimpleQueryPart {

    protected String keyword;
    protected CharSequence[] sections;

    public FulltextQueryPart(CharSequence indexName, String keyword, CharSequence...sections) {
        super(indexName);
        this.keyword = keyword;
        this.sections = sections;
    }

    @Override
    protected EnumSet<IndexType> getSupportIndexTypes() {
        return EnumSet.of(IndexType.INVERTEDINDEX, IndexType.NGRAM, IndexType.CSA, IndexType.HSA, IndexType.UNIGRAM);
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        String escapedKeyword = QueryPartEscapeUtil.urlEncode(QueryPartEscapeUtil.escape(keyword));
        if (schemaMeta == null) {
            if (sections != null && sections.length != 0) {
                String sectionCondition = StringUtils.join(sections, ",");
                return String.format("(%s:%s:%s)", indexName, sectionCondition, escapedKeyword);
            }
            return String.format("(%s:%s)", indexName, escapedKeyword);
        }

        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (! indexMeta.useSectionQuery()) {
            return String.format("(%s:%s)", indexMeta.getName(), escapedKeyword);
        }

        String sectionCondition = "*";
        if (sections != null && sections.length != 0) {
            sectionCondition = StringUtils.join(sections, ",");
        }
        return String.format("(%s:%s:%s)", indexMeta.getName(), sectionCondition, escapedKeyword);
    }

    @Override
    public String getQuery() {
        return getQuery(null);
    }
}
