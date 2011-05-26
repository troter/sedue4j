package jp.troter.sedue4j.util;

import org.apache.commons.lang.StringUtils;

public class QueryPartEscapeUtil {

    /** 予約語 */
    public static final String[] META_CHARRACTORS
        = new String[] {"&", "|", "-", "\\", "(", ")", "?", ":"};
    /** エスケープ済み予約語 */
    public static final String[] ESCAPED_META_CHARRACTORS
        = new String[] {"\\&", "\\|", "\\-", "\\\\", "\\(", "\\)", "\\?", "\\:"};

    /** 範囲エスケープ構文のセパレータ */
    public static final String RANGE_ESCAPE_SEPARATOR = "|";
    /** 範囲エスケープ構文の予約語 */
    public static final String[] RANGE_ESCAPE_META_CHARRACTORS
        = new String[] {"|", "\\", "?"};
    /** 範囲エスケープ構文のエスケープ済み予約語 */
    public static final String[] RANGE_ESCAPE_ESCAPED_META_CHARRACTORS
        = new String[] {"\\|", "\\\\", "\\?"};

    /**
     * 予約文字のエスケープを行う
     * @param value
     * @return
     */
    public static String escape(String value) {
        String result = StringUtils.replaceEach(value, META_CHARRACTORS, ESCAPED_META_CHARRACTORS);
        return result;
    }

    /**
     * 範囲エスケープ構文を利用して予約文字のエスケープを行う
     * @param value
     * @return
     */
    public static String escapeRange(String value) {
        String result = StringUtils.replaceEach(value, RANGE_ESCAPE_META_CHARRACTORS, RANGE_ESCAPE_ESCAPED_META_CHARRACTORS);
        return RANGE_ESCAPE_SEPARATOR + result + RANGE_ESCAPE_SEPARATOR;
    }

}
