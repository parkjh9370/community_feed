package main.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenValidContent_whenCreated_thenReturnTextContent() {
        String text = "test test";

        PostContent content = new PostContent(text);

        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthOver_whenCreated_thenThrowError() {
        String context = "a".repeat(501);

        assertThrows(IllegalArgumentException.class, () -> new PostContent(context));
    }

    @ParameterizedTest
    @ValueSource(strings = {"쀍, 닭, 굶, 삵, 숢"})
    void givenContentLengthOverKorean_whenCreated_thenThrowError(String koreanWord) {
        String context = koreanWord.repeat(501);

        assertThrows(IllegalArgumentException.class, () -> new PostContent(context));
    }

    @Test
    void givenContentLengthLessThan5_whenCreated_thenThrowError() {
        String context = "a".repeat(4);

        assertThrows(IllegalArgumentException.class, () -> new PostContent(context));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenValidContent_whenUpdated_thenNotThrowError() {
        String content = "new new test";
        PostContent postContent = new PostContent(content);

        String updatedContent = "hihihi";
        postContent.updateContent(updatedContent);

        assertEquals(updatedContent, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        String content = "new test";
        PostContent postContent = new PostContent(content);

        String repeatValue = "r".repeat(501);
        assertThrows(IllegalArgumentException.class,
            () -> postContent.updateContent(repeatValue));
    }
}