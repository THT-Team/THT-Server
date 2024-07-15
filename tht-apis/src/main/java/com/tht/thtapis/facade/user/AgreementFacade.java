package com.tht.thtapis.facade.user;

import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.user.response.AgreementMainCategoryResponse;
import com.tht.thtapis.service.AgreementService;
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
                        agreement.getDescription(),
                        agreement.getDetailLink())
                )
                .toList();
    }
}
