package jp.troter.sedue4j.fluent;

import static jp.troter.sedue4j.fluent.Queries.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.SchemaMeta;

import org.junit.Test;

public class QueriesTest {

    public SchemaMeta getSchemaMeta() {
        SchemaMeta s = new SchemaMeta("text");
        s.add(new IndexMeta("all_idx", IndexType.ALLDOCS, false));
        s.add(new IndexMeta("fulltext_search_idx", IndexType.HSA, false));
        s.add(new IndexMeta("fulltext_search_idx_section", IndexType.HSA, true));
        s.add(new IndexMeta("hotate_idx", IndexType.HOTATE, false));
        return s;
    }

    @Test
    public void smoke() {
        // alldocs
        assertThat(alldocs("all_idx").getQuery(getSchemaMeta()), is("(all_idx:)"));

        // fulltext
        assertThat(fulltext("fulltext_search_idx", "東京").getQuery(getSchemaMeta()), is("(fulltext_search_idx:東京)"));
        assertThat(fulltext("fulltext_search_idx", "東京", "metadata", "title").getQuery(getSchemaMeta()), is("(fulltext_search_idx:東京)"));

        // fulltext use section query
        assertThat(fulltext("fulltext_search_idx_section", "東京").getQuery(getSchemaMeta()), is("(fulltext_search_idx_section:*:東京)"));
        assertThat(fulltext("fulltext_search_idx_section", "東京", "metadata", "title").getQuery(getSchemaMeta()), is("(fulltext_search_idx_section:metadata,title:東京)"));

        // hotate
        assertThat(hotate("hotate_idx", "article_id1", "article_id2").getQuery(getSchemaMeta()), is("(hotate_idx:article_id1,article_id2)"));
    }
}
