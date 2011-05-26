package jp.troter.sedue4j.fluent;

import static jp.troter.sedue4j.fluent.Queries.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import jp.troter.sedue4j.IndexMeta;
import jp.troter.sedue4j.IndexType;
import jp.troter.sedue4j.QueryPart;
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
        assertThat(alldocs("all_idx").getQuery(), is("(all_idx:)"));
        // alldocs with schema
        assertThat(alldocs("all_idx").getQuery(getSchemaMeta()), is("(all_idx:)"));

        // fulltext
        assertThat(fulltext("fulltext_search_idx", "東京").getQuery(), is("(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)"));
        assertThat(fulltext("fulltext_search_idx", "東京", "metadata", "title").getQuery(), is("(fulltext_search_idx:metadata,title:%E6%9D%B1%E4%BA%AC)"));
        // fulltext with schema
        assertThat(fulltext("fulltext_search_idx", "東京").getQuery(getSchemaMeta()), is("(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)"));
        assertThat(fulltext("fulltext_search_idx", "東京", "metadata", "title").getQuery(getSchemaMeta()), is("(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)"));

        // fulltext use section query
        assertThat(fulltext("fulltext_search_idx_section", "東京").getQuery(), is("(fulltext_search_idx_section:%E6%9D%B1%E4%BA%AC)"));
        assertThat(fulltext("fulltext_search_idx_section", "東京", "*").getQuery(), is("(fulltext_search_idx_section:*:%E6%9D%B1%E4%BA%AC)"));
        assertThat(fulltext("fulltext_search_idx_section", "東京", "metadata", "title").getQuery(), is("(fulltext_search_idx_section:metadata,title:%E6%9D%B1%E4%BA%AC)"));
        // fulltext use section query with schema
        assertThat(fulltext("fulltext_search_idx_section", "東京").getQuery(getSchemaMeta()), is("(fulltext_search_idx_section:*:%E6%9D%B1%E4%BA%AC)"));
        assertThat(fulltext("fulltext_search_idx_section", "東京", "metadata", "title").getQuery(getSchemaMeta()), is("(fulltext_search_idx_section:metadata,title:%E6%9D%B1%E4%BA%AC)"));

        // hotate
        assertThat(hotate("hotate_idx", "article_id1", "article_id2").getQuery(), is("(hotate_idx:article_id1,article_id2)"));
        // hotate with schema
        assertThat(hotate("hotate_idx", "article_id1", "article_id2").getQuery(getSchemaMeta()), is("(hotate_idx:article_id1,article_id2)"));
    }

    @Test
    public void composite() {
        // or
        assertThat(or(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(),
                is("((all_idx:)|(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));
        assertThat(or(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京"), hotate("hotate_idx", "article_id1", "article_id2")).getQuery(),
                is("((all_idx:)|(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)|(hotate_idx:article_id1,article_id2))"));
        // or with schema
        assertThat(or(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(getSchemaMeta()),
                is("((all_idx:)|(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));
        assertThat(or(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京"), hotate("hotate_idx", "article_id1", "article_id2")).getQuery(getSchemaMeta()),
                is("((all_idx:)|(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)|(hotate_idx:article_id1,article_id2))"));

        // and
        assertThat(and(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(),
                is("((all_idx:)%26(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));
        assertThat(and(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京"), hotate("hotate_idx", "article_id1", "article_id2")).getQuery(),
                is("((all_idx:)%26(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)%26(hotate_idx:article_id1,article_id2))"));
        // and with schema
        assertThat(and(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(getSchemaMeta()),
                is("((all_idx:)%26(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));
        assertThat(and(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京"), hotate("hotate_idx", "article_id1", "article_id2")).getQuery(getSchemaMeta()),
                is("((all_idx:)%26(fulltext_search_idx:%E6%9D%B1%E4%BA%AC)%26(hotate_idx:article_id1,article_id2))"));

        // andNot
        assertThat(andNot(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(),
                is("((all_idx:)-(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));
        // andNot with schema
        assertThat(andNot(alldocs("all_idx"), fulltext("fulltext_search_idx", "東京")).getQuery(getSchemaMeta()),
                is("((all_idx:)-(fulltext_search_idx:%E6%9D%B1%E4%BA%AC))"));

        QueryPart composite = andNot(and(or(fulltext("fulltext_search_idx", "京都"), fulltext("fulltext_search_idx", "大阪")),
                                         fulltext("fulltext_search_idx", "奈良")),
                                     fulltext("fulltext_search_idx_section", "京都", "title"));
        // complex
        assertThat(composite.getQuery(),
                is("((((fulltext_search_idx:%E4%BA%AC%E9%83%BD)|(fulltext_search_idx:%E5%A4%A7%E9%98%AA))%26(fulltext_search_idx:%E5%A5%88%E8%89%AF))-(fulltext_search_idx_section:title:%E4%BA%AC%E9%83%BD))"));
        // complex with schema
        assertThat(composite.getQuery(getSchemaMeta()),
                is("((((fulltext_search_idx:%E4%BA%AC%E9%83%BD)|(fulltext_search_idx:%E5%A4%A7%E9%98%AA))%26(fulltext_search_idx:%E5%A5%88%E8%89%AF))-(fulltext_search_idx_section:title:%E4%BA%AC%E9%83%BD))"));
    }
}
