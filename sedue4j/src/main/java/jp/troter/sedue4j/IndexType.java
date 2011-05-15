package jp.troter.sedue4j;

public enum IndexType {
    /**
     * 登録文書の全件取得
     */
    ALLDOCS,
    /**
     * 転置ファイル方式の全文検索
     */
    INVERTEDINDEX,
    /**
     * N-gram方式の全文検索
     */
    NGRAM,
    /**
     * 圧縮接尾辞配列方式の全文検索
     */
    CSA,
    /**
     * 接尾辞配列方式の全文検索
     */
    HSA,
    /**
     * 関連文書検索
     */
    HOTATE,
    /**
     * 連想検索
     */
    REFLEXA,
    /**
     * 協調フィルタリング
     */
    ROW2ROW,
    /**
     * 協調フィルタリング
     */
    COL2ROW,
    /**
     * 類似画像検索
     */
    LIMAGE,
    /**
     * リアルタイム検索
     */
    REALTIME,
    /**
     * リアルタイム追記に対応した全文検索
     */
    UNIGRAM,
    /**
     * フィールド
     */
    FIELD,
    ;
}
