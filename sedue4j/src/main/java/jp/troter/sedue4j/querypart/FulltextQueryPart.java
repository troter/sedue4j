package jp.troter.sedue4j.querypart;

import org.apache.commons.lang.StringUtils;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.SchemaMeta;

public class FulltextQueryPart implements QueryPart {

    protected CharSequence indexName;
    protected String keyword;
    protected CharSequence[] sections;

    public FulltextQueryPart(CharSequence indexName, String keyword, CharSequence...sections) {
        this.indexName = indexName;
        this.keyword = keyword;
        this.sections = sections;
    }

    /**
     * TODO: 予約語のescape
     */
    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        if (indexMeta.useSectionQuery()) {
            if (sections == null || sections.length == 0) {
                return String.format("(%s:*:%s)", indexName, keyword);
            }
            return String.format("(%s:%s:%s)", indexName, StringUtils.join(sections, ","), keyword);
        }
        return String.format("(%s:%s)", indexName, keyword);
    }
}
