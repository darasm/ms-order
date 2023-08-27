package org.btg.mapper;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest

class PaginationMapperTest {

    @Inject
    private PaginationMapper mapper;

    @Test
    void testFromPanacheQueryObj() {

        PanacheQuery query = Mockito.mock(PanacheQuery.class);

        Mockito.when(query.page()).thenReturn(new Page(2, 5));
        var response = mapper.from(query);

        Assertions.assertEquals(3, response.getPage());
        Assertions.assertEquals(5, response.getPageSize());
    }

}