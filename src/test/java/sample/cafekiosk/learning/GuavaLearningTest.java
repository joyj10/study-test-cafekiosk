package sample.cafekiosk.learning;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuavaLearningTest {

    @DisplayName("Guava 파티션 API 테스트 : 주어진 갯수 만큼 List 파티셔닝한다.")
    @Test
    void testOfPartition() {
        // given
        List<Integer> integers = List.of(1,2,3,4,5,6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 3);

        // thend
        assertThat(partition).hasSize(2)
                .isEqualTo(List.of(
                        List.of(1,2,3),
                        List.of(4,5,6)
                    )
                );
    }

    @DisplayName("Guava 파티션 API 테스트 : 주어진 갯수 만큼 List 파티셔닝한다.")
    @Test
    void testOfPartition2() {
        // given
        List<Integer> integers = List.of(1,2,3,4,5,6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 4);

        // then
        assertThat(partition).hasSize(2)
                .isEqualTo(List.of(
                                List.of(1,2,3,4),
                                List.of(5,6)
                        )
                );
    }

    @DisplayName("멀티맵은 데이터를 리스트로 반환한다.")
    @Test
    void testOfMultiMap() {
        // given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피", "아메리카노");
        multimap.put("커피", "카페라떼");
        multimap.put("커피", "카푸치노");
        multimap.put("베이커리", "크루와상");
        multimap.put("베이커리", "소금빵");

        // when
        Collection<String> result = multimap.get("커피");

        // then
        assertThat(result).hasSize(3)
                .isEqualTo(List.of("아메리카노","카페라떼","카푸치노"));
    }

    @DisplayName("멀티맵 삭제 기능")
    @TestFactory
    Collection<DynamicTest> testOfMultiMap2() {
        // given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피", "아메리카노");
        multimap.put("커피", "카페라떼");
        multimap.put("커피", "카푸치노");
        multimap.put("베이커리", "크루와상");
        multimap.put("베이커리", "소금빵");

        return List.of(
                DynamicTest.dynamicTest("1개 value 삭제", () -> {
                    // when
                    multimap.remove("커피", "카푸치노");

                    // then
                    Collection<String> result = multimap.get("커피");

                    assertThat(result).hasSize(2)
                            .isEqualTo(List.of("아메리카노","카페라떼"));
                }),
                DynamicTest.dynamicTest("1개 key 삭제", () -> {
                    // when
                    multimap.removeAll("커피");

                    // then
                    Collection<String> result = multimap.get("커피");

                    assertThat(result).isEmpty();
                })
        );
    }
}
