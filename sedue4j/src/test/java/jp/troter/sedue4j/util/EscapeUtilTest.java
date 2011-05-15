package jp.troter.sedue4j.util;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class EscapeUtilTest {

    @Test
    public void smoke() {
        assertThat(EscapeUtil.escape("Tom & Jerry"), is("Tom \\& Jerry"));
        assertThat(EscapeUtil.escape("&|-\\()?:"), is("\\&\\|\\-\\\\\\(\\)\\?\\:"));

        assertThat(EscapeUtil.escapeRange("Tom & Jerry"), is("|Tom & Jerry|"));
        assertThat(EscapeUtil.escapeRange("&|-\\()?:"), is("|&\\|-\\\\()\\?:|"));
    }
}
