package com.tht.thtapis.fixture.user;

import com.tht.thtapis.facade.user.request.ModifiedIdealTypeRequest;

import java.util.ArrayList;
import java.util.List;

public class ModifiedIdealTypeRequestFixture {

    public static ModifiedIdealTypeRequest make() {
        return new ModifiedIdealTypeRequest(
            List.of(10, 2, 8)
        );
    }

    public static ModifiedIdealTypeRequest ofSize(int size) {

        int n = size;

        List<Integer> request = new ArrayList<>();

        while (n-- > 0 ) {
            request.add(n);
        }

        return new ModifiedIdealTypeRequest(request);
    }
}
