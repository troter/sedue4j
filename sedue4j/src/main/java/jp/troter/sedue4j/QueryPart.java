package jp.troter.sedue4j;


public interface QueryPart {

    /**
     * スキーマ定義から上手にクエリー組み立てしたい
     * @param schemaMeta
     * @return
     */
    String getQuery(SchemaMeta schemaMeta);
}
