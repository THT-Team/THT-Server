package com.tht.api.app.facade.user;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.response.AgreementMainCategoryResponse;
import com.tht.api.app.service.AgreementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@Transactional
@RequiredArgsConstructor
public class AgreementFacade {

    private final AgreementService agreementService;

    public List<AgreementMainCategoryResponse> getMainCategoryList() {
        return agreementService.findAll()
                .stream()
                .map(agreement -> AgreementMainCategoryResponse.of(
                        agreement.getName().getValue(),
                        agreement.getSubject(),
                        agreement.isRequired(),
                        agreement.getDescription()))
                .toList();
    }
}
