package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.interest.request.ModifiedInterestsRequest;
import java.util.ArrayList;
import java.util.List;

public class ModifiedInterestsRequestFixture {

    public static ModifiedInterestsRequest make() {
        return new ModifiedInterestsRequest(
            List.of(10, 2, 8)
        );
    }

    public static ModifiedInterestsRequest ofSize(int size) {

        int n = size;

        List<Integer> request = new ArrayList<>();

        while (n-- > 0 ) {
            request.add(n);
        }

        return new ModifiedInterestsRequest(request);
    }
}
