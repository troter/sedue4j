package jp.troter.sedue4j;

import java.util.HashMap;
import java.util.Map;

/**
 * sedueのスキーマ定義のメタデータを表すクラスです。
 */
public class SchemaMeta {

    protected String name;

    protected Map<CharSequence, IndexMeta> indexMetaMap = new HashMap<CharSequence, IndexMeta>();

    public SchemaMeta(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 名前を指定してIndexMetaを取得
     * @param indexName
     * @return
     */
    public IndexMeta getIndexMeta(CharSequence indexName) {
        if (indexMetaMap.containsKey(indexName)) {
            return indexMetaMap.get(indexName);
        }
        // TODO 専用の例外
        throw new RuntimeException("指定した名前のindexは存在しません");
    }

    public void add(IndexMeta indexMeta) {
        indexMetaMap.put(indexMeta.getName(), indexMeta);
    }
}
