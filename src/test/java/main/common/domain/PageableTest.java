package main.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PageableTest {

    @Test
    void givenPageableIndexIsNull_whenGetOffset_thenShouldBeReturn0() {
        Pageable pageable = new Pageable();

        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        assertEquals(0, offset);
        assertEquals(10, limit);
    }

    @Test
    void givenPageableIndexIs10_whenGetOffset_thenShouldBeReturn10() {
        Pageable pageable = new Pageable(2, 10);

        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        assertEquals(10, offset);
        assertEquals(10, limit);
    }
}
