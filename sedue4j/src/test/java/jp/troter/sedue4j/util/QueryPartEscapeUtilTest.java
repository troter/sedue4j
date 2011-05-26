package jp.troter.sedue4j.util;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class QueryPartEscapeUtilTest {

    @Test
    public void smoke() {
        assertThat(QueryPartEscapeUtil.escape("Tom & Jerry"), is("Tom \\& Jerry"));
        assertThat(QueryPartEscapeUtil.escape("&|-\\()?:"), is("\\&\\|\\-\\\\\\(\\)\\?\\:"));

        assertThat(QueryPartEscapeUtil.escapeRange("Tom & Jerry"), is("|Tom & Jerry|"));
        assertThat(QueryPartEscapeUtil.escapeRange("&|-\\()?:"), is("|&\\|-\\\\()\\?:|"));
    }
}
