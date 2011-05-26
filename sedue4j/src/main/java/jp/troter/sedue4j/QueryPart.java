package jp.troter.sedue4j;


public interface QueryPart {

    /**
     * スキーマ定義から上手にクエリー組み立てしたい
     * @param schemaMeta
     * @return
     */
    String getQuery(SchemaMeta schemaMeta);

    /**
     * スキーマ定義なしでクエリー組み立を行う
     * @return
     */
    String getQuery();
}
