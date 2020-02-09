package com.company;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void buildGraph() {
        test(
                new int[] {4, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
                "connect pairs: 14-0, 14-1, 14-2, 14-3, 14-4, 0-5, 0-6, 0-7, 1-8, 1-9, 2-10, 2-11, 3-12, 4-13"
        );

        test(
                new int[] {1, 2, 1},
                "connect pairs: 1-0, 1-2"
        );

        test(
                new int[] {1, 2},
                "can't connect"
        );
    }

    private void test(int[] routersSet, String expected) {
        String actual = Main.buildGraph(routersSet);
        assertEquals(expected, actual);
    }
}