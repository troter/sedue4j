package jp.troter.sedue4j;

/**
 * sedueのスキーマ定義のメタデータを表すクラスです。
 * インデックス毎にできることが違うので各インデックスタイプに応じてサブクラスを作った方が良いかも
 */
public class IndexMeta {

    protected CharSequence name;
    protected IndexType indexType;
    protected boolean useSectionQuery;

    public IndexMeta(CharSequence name, IndexType indexType, boolean useSectionQuery) {
        this.name = name;
        this.indexType = indexType;
        this.useSectionQuery = useSectionQuery;
    }

    public CharSequence getName(){
        return name;
    }

    public IndexType getIndexType() {
        return indexType;
    }

    public boolean useSectionQuery() {
        return useSectionQuery;
    }
}
