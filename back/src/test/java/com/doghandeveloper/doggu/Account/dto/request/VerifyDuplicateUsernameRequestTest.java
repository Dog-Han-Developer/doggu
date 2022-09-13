package com.doghandeveloper.doggu.Account.dto.request;

import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifyDuplicateUsernameRequestTest {

    private final static String PATTERN = "^[a-z0-9_\\.]{2,16}$";

    @Test
    @DisplayName("올바른 사용자 이름으로 중복 확인을 요청한다.")
    void withCorrectUsername(){
        String username = "doggu_1234.png";

        Assertions.assertThat(Pattern.matches(PATTERN, username)).isTrue();
    }

    @Test
    @DisplayName("잘못된 형식의 사용자 이름으로 중복 확인을 요청할 수 없다.")
    void withWrongUsername(){
        String username = "doggu#love";

        Assertions.assertThat(Pattern.matches(PATTERN, username)).isFalse();
    }

    @Test
    @DisplayName("잘못된 길이의 사용자 이름으로 회원가입을 할 수 없다.")
    void withWrongLengthUsername() {
        String username = "doggu_love.1234567890";

        Assertions.assertThat(Pattern.matches(PATTERN, username)).isFalse();
    }
}
