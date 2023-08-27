package org.btg.mapper;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@QuarkusTest

class PaginationMapperTest {

    @Inject
    private PaginationMapper mapper;

    @Test
    void testFromPanacheQueryObj() {

        PanacheQuery query = mock(PanacheQuery.class);

        when(query.page()).thenReturn(new Page(2, 5));
        var response = mapper.from(query);

        assertEquals(3, response.getPage());
        assertEquals(5, response.getPageSize());
    }

}