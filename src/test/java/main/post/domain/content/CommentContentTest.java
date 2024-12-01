package main.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void givenValidCommentContent_whenCreatedCommentContent_thenReturnTextContent() {
        String commentText = "hihihi";

        CommentContent commentContent = new CommentContent(commentText);

        assertEquals(commentText, commentContent.contentText);
    }

    @Test
    void givenContentOver_whenCreatedCommentContent_thenThrow() {
        String commentText = "a".repeat(101);

        assertThrows(IllegalArgumentException.class, () -> new CommentContent(commentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"쀍", "닭"})
    void givenKoreanContentOver_whenCreatedCommentContent_thenThrow(String value) {
        String commentText = value.repeat(101);

        assertThrows(IllegalArgumentException.class, () -> new CommentContent(commentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenNull_whenCreatedCommentContent_thenThrow(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }
}