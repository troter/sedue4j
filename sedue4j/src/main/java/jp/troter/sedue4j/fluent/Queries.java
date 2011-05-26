package jp.troter.sedue4j.fluent;

import jp.troter.sedue4j.QueryPart;
import jp.troter.sedue4j.querypart.AlldocsQueryPart;
import jp.troter.sedue4j.querypart.CompositeQueryPart;
import jp.troter.sedue4j.querypart.FulltextQueryPart;
import jp.troter.sedue4j.querypart.HotateQueryPart;

public class Queries {

    public static final String OPERATOR_OR = "%7C";

    public static final String OPERATOR_AND = "%26";

    public static final String OPERATOR_NOT = "%2D";

    public static QueryPart alldocs(CharSequence indexName) {
        return new AlldocsQueryPart(indexName);
    }

    public static QueryPart fulltext(CharSequence indexName, String keyword, CharSequence... sections) {
        return new FulltextQueryPart(indexName, keyword, sections);
    }

    public static QueryPart hotate(CharSequence indexName, String... articleIds) {
        return new HotateQueryPart(indexName, articleIds);
    }

    public static CompositeQueryPart or(final QueryPart... children) {
        return new CompositeQueryPart(OPERATOR_OR, children);
    }

    public static CompositeQueryPart and(final QueryPart... children) {
        return new CompositeQueryPart(OPERATOR_AND, children);
    }

    public static CompositeQueryPart andNot(final QueryPart hitQueryPart, final QueryPart notHitQueryPart) {
        return new CompositeQueryPart(OPERATOR_NOT, new QueryPart[]{hitQueryPart, notHitQueryPart});
    }
}
