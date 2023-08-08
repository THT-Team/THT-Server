package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record UserModifyProfilePhotoRequest(

    @NotNull(message = "photoList 는 null 이어서는 안됩니다.")
    @Size(min = 2, max = 3, message = "사진은 최소 2장, 최대 3장을 등록해야합니다.")
    List<UserProfilePhotoRequest> userProfilePhotoList
) { }
