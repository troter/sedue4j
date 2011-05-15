package jp.troter.sedue4j.querypart;

import java.util.EnumSet;

import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.SchemaMeta;
import jp.troter.sedue4j.util.EscapeUtil;

import org.apache.commons.lang.StringUtils;

public class HotateQueryPart extends AbstractSimpleQueryPart {

    protected String[] articleIds;

    public HotateQueryPart(CharSequence indexName, String... articleIds) {
        super(indexName);
        this.articleIds = articleIds;
    }

    @Override
    EnumSet<IndexType> getValidIndexType() {
        return EnumSet.of(IndexType.HOTATE);
    }

    @Override
    public String getQuery(SchemaMeta schemaMeta) {
        IndexMeta indexMeta = schemaMeta.getIndexMeta(indexName);
        String escapedArticleIds = EscapeUtil.escape(StringUtils.join(articleIds, ","));
        return String.format("(%s:%s)", indexMeta.getName(), escapedArticleIds);
    }
}