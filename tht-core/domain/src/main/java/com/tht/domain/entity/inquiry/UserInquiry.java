package com.tht.domain.entity.inquiry;

import com.tht.domain.entity.Auditable;
import com.tht.enums.inquiry.InquiryStatus;
import com.tht.enums.inquiry.InquiryStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@DynamicUpdate
public class UserInquiry extends Auditable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String contents;

    @Column
    private String userEmail;

    @Column
    private Boolean isEmailAgree;

    @Column
    @Convert(converter = InquiryStatusConverter.class)
    private InquiryStatus inquiryStatus;

    @Builder
    private UserInquiry(final String userUuid, final String contents, final String userEmail,
                        final Boolean isEmailAgree, final InquiryStatus inquiryStatus) {

        this.userUuid = userUuid;
        this.contents = contents;
        this.userEmail = userEmail;
        this.isEmailAgree = isEmailAgree;
        this.inquiryStatus = inquiryStatus;
    }

    public static UserInquiry createInquiry(final String userUuid, final String contents, final String userEmail, final boolean isEmailAgree) {

        if (!isEmailAgree) {
            throw UserInquiryException.isFalseEmailAgree();
        }

        return UserInquiry.builder()
            .userUuid(userUuid)
            .contents(contents)
            .userEmail(userEmail)
            .isEmailAgree(isEmailAgree)
            .inquiryStatus(InquiryStatus.RECEPTION)
            .build();
    }
}
